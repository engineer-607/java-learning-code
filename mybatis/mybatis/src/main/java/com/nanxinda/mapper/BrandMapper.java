package com.nanxinda.mapper;

import com.nanxinda.pogo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/// 可以用注解完成简单的功能，但是复杂功能还是需要配置文件
/// 查询：@Select/添加：@Insert/修改：@Update/删除：@Delete
public interface BrandMapper {
    public List<Brand> selectAll();
    public Brand selectById(int id);

    /**
     * 条件查询
     *   参数接受
     *   1.散装参数：如果方法中有多个参数，需要使用@Param("SQL参数占位符名称")
     *   2.对象参数
     * @param status
     * @param companyName
     * @param brandName
     * @return
     */
    /// 传参分析：
    /// *单个参数：
    /// 1.POJO类型：直接使用属性名和参数占位符名称一致
    /// 2.Map集合：直接使用键名和参数占位符名称一致
    /// 3.Collection：
    //map.put("collection", object);//map.put("collection",collection集合)
    //Optional.ofNullable(actualParamName).ifPresent((name) -> map.put(name, object));//map.put("arg0",collection集合)
    /// 4.List：
    //map.put("collection", object);//map.put("collection",collection集合)
    //Optional.ofNullable(actualParamName).ifPresent((name) -> map.put(name, object));//map.put("arg0",list集合)
    //map.put("list", object);//map.put("list",list集合)
    /// 5.Array：
    //map.put("array", object);//map.put("array",数组)
    //Optional.ofNullable(actualParamName).ifPresent((name) -> map.put(name, object));//map.put("arg0",数组)

    //    public static Object wrapToMapIfCollection(Object object, String actualParamName) {
    //        if (object instanceof Collection) {
    //            MapperMethod.ParamMap<Object> map = new MapperMethod.ParamMap();
    //            map.put("collection", object);
    //            if (object instanceof List) {
    //                map.put("list", object);
    //            }
    //
    //            Optional.ofNullable(actualParamName).ifPresent((name) -> map.put(name, object));
    //            return map;
    //        } else if (object != null && object.getClass().isArray()) {
    //            MapperMethod.ParamMap<Object> map = new MapperMethod.ParamMap();
    //            map.put("array", object);
    //            Optional.ofNullable(actualParamName).ifPresent((name) -> map.put(name, object));
    //            return map;
    //        } else {
    //            return object;
    //        }
    //    }
    /// 6.其他类型：直接使用
    /// *多个参数：会将其封装成Map集合
    //源码：
    //    public Object getNamedParams(Object[] args) {
    //        int paramCount = this.names.size();
    //        if (args != null && paramCount != 0) {
    //            if (!this.hasParamAnnotation && paramCount == 1) {
    //                Object value = args[(Integer)this.names.firstKey()];
    //                return wrapToMapIfCollection(value, this.useActualParamName ? (String)this.names.get(0) : null);
    //            } else {
    //            创建map集合
    //                Map<String, Object> param = new MapperMethod.ParamMap();
    //                int i = 0;
    //
    //                for(Map.Entry<Integer, String> entry : this.names.entrySet()) {
    //            将key->arg0和value->对应的参数存储到集合中
    //                    param.put(entry.getValue(), args[(Integer)entry.getKey()]);
    //                    String genericParamName = "param" + (i + 1);
    //                    if (!this.names.containsValue(genericParamName)) {
    //            将key->param0和value->对应的参数存储到集合中
    //                        param.put(genericParamName, args[(Integer)entry.getKey()]);
    //                    }
    //
    //                    ++i;
    //                }
    //
    //                return param;
    //            }
    //        } else {
    //            return null;
    //        }
    //    }
    /// 在没有加入@Param注解时
    /// arg0 参数值1
    /// param1 参数值1
    /// param2 参数值2
    /// arg1 参数值2
    /// 使用@Param注解，将Param里面的名称替换掉Map集合中默认的arg键名

    public List<Brand> selectByCondition(@Param("status") int status,@Param("companyName")String companyName,@Param("brandName")String brandName);

    public List<Brand> selectByConditionUsingObject(Brand brand);

    public List<Brand> selectByConditionUsingMap(Map map);

    /**
     * 单条件查询动态查询（客户在不同的条件查询中做选择，在status、companyName、brandName三个字段
     * 中选择一个来查询数据
     * @param brand
     * @return
     */
    public List<Brand> selectByConditionSingle(Brand brand);
    /**
     * 添加
     */
    public void add(Brand brand);

    /**
     * 添加
     * @return 影响的行数
     */
    public int update(Brand brand);

    /**
     * 根据id进行单个删除
     */
    public void deleteById(@Param("id") int id);

    /**
     * 根据id进行批量删除
     */
    public void deleteByIds(@Param("ids") int[] ids);

}
