package com.nanxinda;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://127.0.0.1:3306/itcast?useSSL=false&serverTimezone=Asia/Shanghai",
                        "ubuntu",
                        "123456"
                )//NOTE 创建代码生成器，告知连接的数据库参数（数据库连接地址、数据库用户名、数据库密码）
                .globalConfig(builder -> {
                    builder.author("庞绍祥")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
                })//NOTE .globalConfig(...)配置全局信息
                  // author("庞绍祥")设置代码作者
                  // outputDir()设置生成代码存放地，System.getProperty("user.dir") 表示当前项目根目录
                  // System.getProperty("user.dir") + "/src/main/java"等价于D:/spring/mybatisplus/generator/src/main/java
                .packageConfig(builder -> {
                    builder.parent("com.nanxinda")
                            .entity("domain")
                            .mapper("dao")
                            .xml("mapper");
                })//NOTE .packageConfig(...)作用配置生成的类配置的包
                  // builder.parent("com.nanxinda")设置父包名
                  // entity("domain")设置实体类包名、mapper("dao")设置 Mapper 接口的包名
                  // xml("mapper")设置 Mapper XML 的包/目录名
                .strategyConfig(builder -> {
                    builder.addInclude("tb_stu_user")
                            .addTablePrefix("tb_stu_")
                            .entityBuilder()
                            .enableLombok()
//                            .enableTableFieldAnnotation()
                            .versionColumnName("version")
                            .logicDeleteColumnName("deleted")
                            .controllerBuilder()
                            .enableRestStyle()
                    ;
                })//NOTE .strategyConfig(...)配置生成策略
                  // addInclude("tb_stu_user")指定生成tb_stu_user表
                  // entityBuilder()表示开始实体类的具体配置（前面的builder是整体策略配置）
                  // enableLombok()表示使用Lombok（@Data）、enableTableFieldAnnotation()添加注解使字段和数据库列的映射关系更加明确
                  // versionColumnName("version")乐观锁字段，数据库列名 logicDeleteColumnName("deleted")逻辑删除字段，数据库列名
                  // controllerBuilder()Controller生成策略
                  // enableRestStyle()开启REST风格
                .templateEngine(new FreemarkerTemplateEngine())//NOTE 设置用 Freemarker 模板生成代码。
                .execute();//NOTE 开始执行

    }
}
