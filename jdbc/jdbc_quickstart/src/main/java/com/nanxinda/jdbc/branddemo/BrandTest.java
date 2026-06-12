package com.nanxinda.jdbc.branddemo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

//进行增删改查
public class BrandTest {
    public static void main(String[] args) throws Exception {
        //1.导入maven依赖
        //2.定义配置文件
        //3.加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));

        //4.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //5.获取数据库连接Connection
        Connection connection =  dataSource.getConnection();
        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Brand> list = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            Brand brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            list.add(brand);
        }
        for (Brand brand : list) {
            System.out.println(brand);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


}
