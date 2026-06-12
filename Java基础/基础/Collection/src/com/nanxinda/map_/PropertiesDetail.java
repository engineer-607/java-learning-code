package com.nanxinda.map_;

import java.util.Properties;
@SuppressWarnings({"all"})
public class PropertiesDetail {
    public static void main(String[] args) {
        /*
        1.Properties类继承自Hashtable类并且实现Map接口，也是键值对的形式来保存数据
        2.使用特点和Hashtable类似
        3.Properties还可以使用从xxx.properties文件中，加载数据到Properties类对象
        并进行读取和修改（xxx.properties 文件通常作为配置文件）
         */
        Properties properties = new Properties();
        //增加
        properties.put("john",100);
//        properties.put(null,"abc");//NullPointerException
//        properties.put("abc",null);//NullPointerException
        properties.put("lucy",100);
        properties.put("lic",100);
        properties.put("lic",88);
        //如果有相同的key，value被替换
        System.out.println("properties="+properties);
        //通过key获取对应值
        System.out.println(properties.get("lic"));//88
        //删除
        properties.remove("lic");
        System.out.println("properties="+properties);

    }
}
