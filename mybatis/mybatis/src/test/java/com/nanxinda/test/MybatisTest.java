package com.nanxinda.test;

import com.nanxinda.mapper.BrandMapper;
import com.nanxinda.pogo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void testSelect() throws IOException {
        //接收参数
        int id = 1;
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        //参数处理
        brandName="%"+brandName+"%";
        companyName="%"+companyName+"%";

        //封装对象
        Brand brandObject = new Brand();
        brandObject.setBrandName(brandName);
        brandObject.setStatus(status);
        brandObject.setCompanyName(companyName);

        //封装对象(单条件查询)
        Brand brand2 = new Brand();
        brand2.setStatus(status);

        //设置map集合
        Map map = new HashMap();
        map.put("status",status);
        map.put("companyName",companyName);
        map.put("brandName",brandName);

        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        List<Brand> list = brandMapper.selectAll();
        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);
        List<Brand> brandList = brandMapper.selectByConditionUsingObject(brandObject);
        List<Brand> brandList1 = brandMapper.selectByConditionUsingMap(map);
        List<Brand> brandList2 = brandMapper.selectByConditionSingle(brand2);
        for (Brand brand : list) {
            System.out.println(brand);
        }
        System.out.println("=============");
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);
        System.out.println("=============");
        for (Brand brand1 : brands) {
            System.out.println(brand1);
        }
        System.out.println("=============");
        for (Brand brand1 : brandList) {
            System.out.println(brand1);
        }
        System.out.println("==============");
        for (Brand brand1 : brandList1) {
            System.out.println(brand1);
        }
        System.out.println("==============");
        for (Brand brand1 : brandList2) {
            System.out.println(brand1);
        }
    }
    @Test
    public void testInsert() throws IOException {
        //设置数据
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int ordered = 100;

        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象
        /// 如果需要自动提交事务，需要传入true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        brandMapper.add(brand);
        /// mybatis在执行insert语句后，默认是不会将数据库中自动产生的数据（比如自增的id、某个字段的默认值）
        ///回写到brand对象中，如果需要获取添加的数据的id需要配置主键回填 (useGeneratedKeys)
        System.out.println(brand.getId());
        /// [DEBUG] 16:58:18.806 [main] o.a.i.t.j.JdbcTransaction - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@4264b240]
        /// 这行日志代表没有设置自动提交事务，需要进行手动提交事务
        sqlSession.commit();
    }
    @Test
    public void testUpdate() throws IOException {
        //设置上传数据
        String description = "小丑中的战斗机";
        int id = 6;
        //封装对象
        Brand brand = new Brand();
        brand.setDescription(description);
        brand.setId(id);
        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        int update = brandMapper.update(brand);
        System.out.println(update);
    }
    @Test
    public void testDelete() throws IOException {
        int id = 6;
        int[] ints = {7,8,9};
        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        brandMapper.deleteByIds(ints);
    }

}
