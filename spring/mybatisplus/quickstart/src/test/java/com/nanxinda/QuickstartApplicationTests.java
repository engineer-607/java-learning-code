package com.nanxinda;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanxinda.dao.UserDao;
import com.nanxinda.domain.User;
import com.nanxinda.domain.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class QuickstartApplicationTests {
    @Autowired
    UserDao userDao;

    //NOTE 查询全部
    @Test
    void testGetAll() {
        //NOTE 按条件查询(lt小于、gt大于、ge大于等于、le小于等于、eq等于、ne不等于)
        //NOTE 方式一：传字符串
        QueryWrapper qw = new QueryWrapper();
        qw.lt("age",21);
        List<User> users = userDao.selectList(qw);
        System.out.println(users);
        System.out.println("=========================");

        //NOTE 方式二：lambda格式按条件查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().lt(User::getAge,21);
        List<User> list = userDao.selectList(queryWrapper);
        System.out.println(list);
        System.out.println("=========================");

        //NOTE 方式三：lambda对象按条件查询
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        //并列关系：大于18小于21
//        lqw.lt(User::getAge,21).ge(User::getAge,18);
        //或者关系：小于18或者大于21
        lqw.lt(User::getAge,21).or().ge(User::getAge,18);
        List<User> list1 = userDao.selectList(lqw);
        System.out.println(list1);


    }

    //NOTE 根据Id查询
    @Test
    void getByIdTest(){
        //NOTE 对null值的处理
        UserQuery userQuery = new UserQuery();
        userQuery.setAge2(19);
        userQuery.setAge(30);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //NOTE 如果第一个参数为true(userQuery.getAge()!=null)，查询条件才会加上后面的内容(age<=30)
        lqw.le(userQuery.getAge()!=null,User::getAge,userQuery.getAge())
           .ge(userQuery.getAge2()!=null,User::getAge,userQuery.getAge2());
//        User user = userDao.selectById(lqw);
        List<User> users = userDao.selectList(lqw);
        //User user = userDao.selectById(1L);
        System.out.println(users);
    }

    //NOTE 查询投影
    @Test
    void conditionalSelect(){
        //NOTE select方法专门设置查询字段名（lambda格式）
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.select(User::getAge,User::getName,User::getAge);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);

        //NOTE 不使用lambda格式设置查询字段名
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name","age","tel");
        List<User> list = userDao.selectList(lqw);
        System.out.println(list);

        //NOTE 按性别进行分组查询各组总量
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("count(*) as count,gender");
        queryWrapper1.groupBy("gender");
        List<Map<String,Object>> mapList = userDao.selectMaps(queryWrapper1);
        System.out.println(mapList);

    }

    //NOTE 条件查询
    //  1.eq匹配:(用户登录)
    //   lqw.eq(User::getName, userQuery.getName())
    //      .eq(User: :getPassword, userQuery.getPassword());
    //  2.le ge匹配或 between匹配(购物设定价格区间、户籍设定年龄区间)
    //  lqw.between(User::getAge,userQuery.getAge(),userQuery.getAge2());
    //  3.like模糊匹配(likeLeft、likeRight)（查信息、搜索信息）
    //  lqw.likeLeft(User::getTel,userQuery.getTel());（%userQuery.getTel()）

    //NOTE 多数据的删除和查询
    //    查询：List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList);
    //    删除：int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    //NOTE 修改
    @Test
    void insertTest(){
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("123456");
        user.setName("测试用户");
        user.setAge(20);
        user.setGender("男");
        user.setPhone("18100000010");
        user.setEmail("testuser@qq.com");
        user.setCreateTime(LocalDateTime.now());
        userDao.insert(user);
    }
    //NOTE 修改
    @Test
    void updateTest(){
        //NOTE 方式一：实体对象中添加乐观锁
//        User user = new User();
//        user.setId(9);
//        user.setName("庞绍祥");
//        user.setVersion(1);
//        int update = userDao.updateById(user);
//        System.out.println(update);
        //NOTE 方式二：获取将要修改的数据的实体类对象
        User user = userDao.selectById(9L);
        user.setName("jerryCoder");
        int update = userDao.updateById(user);
        System.out.println(update);
        /// ==>  Preparing: UPDATE tb_stu_user SET username=?, password=?, name=?, age=?, gender=?, phone=?, email=?, version=? WHERE id=? AND version=? AND deleted=0
        /// ==> Parameters: jerry(String), jerry123(String), jerryCoder(String), 26(Integer), 男(String), 18100000009(String), jerry@gmail.com(String), 2(Integer), 9(Integer), 1(Integer)
        /// 修改数据会将version+1，这是其他持久化层想要修改这个数据时条件依然是version为1，这就无法修改，进而避免多个对象对同一个条
        /// 数据进行修改

    }

    //NOTE 删除
    @Test
    void deleteTest(){
        //NOTE 有 @TableLogic 时，BaseMapper 的删除方法会变成逻辑删除，
        // 也就是执行 UPDATE deleted = 1；没有逻辑删除字段时才是真正的 DELETE。
        // 不过这个判断主要是在 MyBatis-Plus 启动解析实体、注入 SQL 时完成的，
        // 不是代理类每次临时判断。
        int i = userDao.deleteById(11L);
        System.out.println(i);
    }

    //NOTE MP分页查询功能
    @Test
    void getByPageTest(){
        IPage page = new Page(2,3);
        userDao.selectPage(page, null);
        System.out.println("当前页码："+page.getCurrent());
        System.out.println("每页数据总量："+page.getSize());
        System.out.println("总页数："+page.getPages());
        System.out.println("数据总量："+page.getTotal());
        System.out.println("当前页数据："+page.getRecords());
    }
}
