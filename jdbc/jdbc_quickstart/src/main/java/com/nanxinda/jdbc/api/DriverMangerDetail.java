package com.nanxinda.jdbc.api;

public class DriverMangerDetail {
    /*
    1.Maven添加依赖
    <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.33</version>
    </dependency>
    maven会帮忙下好mysql-connector-j-8.0.33.jar这个jar包
    并放到classpath下，同时，这个jar包的META-INF/services/java.sql.Driver文件写明实现了java.sql.Driver这个接口驱动实现类
    （com.mysql.cj.jdbc.Driver）
    2.执行Connection connection = DriverManager.getConnection(url, username, password);
    这行代码时DriverManager 内部会先调用 DriverManager.loadInitialDrivers()，在这个方法中，
    Java中的SPI会自动查找到所有实现上面那个文件中定义的驱动实现类，然后JVM会加载驱动实现类，并执行静态代码块，
    在静态代码块中有 DriverManager.registerDriver(new Driver());让Driver注册到DriverManager
    之后才会真正执行getConnection,getConnection会遍历已注册的 Driver，找出匹配 url 的驱动，建立连接
    （注：老旧的项目中需要手动操作class.forName("com.mysql.jdbc.Driver")，另外com.mysql.jdbc.Driver这个其实是兼容老旧
    项目而保留下来的空壳类，真正将驱动类注册到DriverManager的是其父类com.mysql.cj.jdbc.Driver，也就是那个文件中定义的，当加载
    这个子类的时，父类会先进行初始化，也就是实现了将驱动类注册进入的功能
    这个是com.mysql.jdbc.Driver
    public class Driver extends com.mysql.cj.jdbc.Driver {
        public Driver() throws SQLException {
        }

        static {
            System.err.println("Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.");
        }
    }
    这个是真正的驱动类com.mysql.cj.jdbc.Driver
    public class Driver extends NonRegisteringDriver implements java.sql.Driver {
        public Driver() throws SQLException {
        }

        static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
    }
     */
}
