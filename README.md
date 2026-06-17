# Java 后端学习代码

## 项目简介

这是我的 Java 后端学习代码仓库，记录了从 Java 基础、数据结构与算法、MySQL、JDBC、MyBatis、Redis，到 Spring、Spring MVC、Spring Boot 和 MyBatis-Plus 的学习过程。

仓库中的代码按学习阶段保留，既用于回顾自己的成长过程，也希望能给其他 Java 初学者提供一条可参考的学习路线。部分目录沿用了学习时的命名，例如 `OPP2.0`、`alorithm`，目前不做大规模重命名。

## 学习路线总览

```text
Java 基础
    ↓
数据结构与算法基础
    ↓
MySQL
    ↓
JDBC
    ↓
MyBatis
    ↓
Redis
    ↓
Spring / Spring Boot
```

在 MyBatis、Spring 学习阶段，我也并行进行了数据结构与算法进阶训练。

算法代码的对应关系：

- 算法基础：`数据结构与算法/DataStructure/src/com/nanxinda` 和 `数据结构与算法/arithmetic/src/com/nanxinda/alorithm`
- 算法进阶：`数据结构与算法/arithmetic/src/com/zuochengyun/alorithm`，对应左程云算法课程练习

## 目录结构与学习内容

### Java 基础

```text
Java基础/
├── 基础/
│   ├── 对象/
│   │   └── model：对象创建、属性与方法、Person/Cat 案例，以及对象在 JVM 中的引用关系
│   ├── 方法1.0/
│   │   └── 方法定义与调用、参数传递、递归和方法练习
│   ├── 方法2.0/
│   │   └── 构造器、方法重载、可变参数、作用域和对象初始化
│   ├── 数组/
│   │   └── 一维与二维数组、数组赋值、遍历、扩容和基础算法练习
│   ├── 进制/
│   │   └── 二进制、八进制、十六进制转换，以及位运算基础
│   ├── FunctionHomework/
│   │   └── 方法、数组、对象传参与综合基础题练习
│   │
│   ├── OPP2.0/
│   │   ├── com.nanxinda：包的使用和跨包调用入口
│   │   ├── com.nanxinda.test：类的访问权限、同包与跨包访问
│   │   └── com.nanxinda.print：跨包创建和使用对象
│   ├── OPP2.0-encapsulation/
│   │   ├── com.nanxinda.encap01：通过私有属性、构造器和 setter 完成数据校验
│   │   ├── com.nanxinda.encap02：账户对象的封装与合法性约束
│   │   └── com.nanxinda：封装练习的测试入口
│   ├── OPP2.0-extends/
│   │   ├── com.nanxinda.extend01：Student、Pupil、Graduate 之间的代码复用
│   │   ├── com.nanxinda.extendsDetail：继承访问权限、构造器调用、super 和 Object 根类
│   │   ├── com.nanxinda.extendsExercise：Computer、PC、NotePad 继承练习
│   │   ├── com.nanxinda.extendsTheory：继承对象在 JVM 中的属性查找和内存关系
│   │   └── com.nanxinda：继承示例的测试入口
│   ├── OPP2.0super01/
│   │   └── com.nanxinda.superDetail：使用 super 访问父类构造器、属性和方法
│   ├── OPP2.0_override/
│   │   ├── com.nanxinda.overrideDetail：方法重写规则、访问权限和协变返回类型
│   │   └── com.nanxinda.overrideExercise：父类与子类重写方法后的行为差异
│   ├── OPP2.0_polymorph/
│   │   ├── com.nanxinda.ploy01：父类引用指向子类对象的基础示例
│   │   ├── com.nanxinda.ploy02：主人给不同动物喂食，练习对象多态
│   │   ├── com.nanxinda.ployDetail：向上转型、向下转型和成员访问规则
│   │   ├── com.nanxinda.ployDeatil02：属性静态绑定与方法动态绑定
│   │   ├── com.nanxinda.dynamic：动态绑定机制和运行时方法选择
│   │   ├── com.nanxinda.ployarr：多态数组与不同子类的特有行为
│   │   └── com.nanxinda.polyparameter：多态参数、员工年薪和类型判断
│   ├── OPP3.0/
│   │   ├── com.nanxinda.abstract_：抽象类、抽象方法和模板方法模式
│   │   ├── com.nanxinda.interface_：接口定义、默认方法、接口多态及接口与继承的区别
│   │   ├── com.nanxinda.innerclass_：局部、匿名、成员和静态内部类
│   │   ├── com.nanxinda.codeblock：普通代码块、静态代码块和父子类初始化顺序
│   │   ├── com.nanxinda.final_：final 修饰类、方法、属性和局部变量
│   │   ├── com.nanxinda.staticvariables：静态变量共享、类加载和对象计数
│   │   └── com.nanxinda.Design：饿汉式与懒汉式单例模式
│   │
│   ├── Object/
│   │   ├── com.nanxinda.Object.equals：`==` 与 `equals` 的区别
│   │   ├── com.nanxinda.Object.equalsExercise：按业务字段重写 equals
│   │   ├── com.nanxinda.Object.hasCode：hashCode 的作用和对象散列值
│   │   ├── com.nanxinda.Object.toString：重写 toString 输出对象信息
│   │   ├── com.nanxinda.Object.Finalize：finalize 与垃圾回收的早期学习记录
│   │   └── com.nanxinda.Object.Debug：断点、单步执行和对象创建过程调试
│   │
│   ├── Collection/
│   │   ├── com.nanxinda.collectionbase：Collection 体系、常用增删方法、Iterator 和增强 for
│   │   ├── com.nanxinda.list：List 有序、可重复、可按索引访问的特点及常用方法
│   │   ├── com.nanxinda.arrayList_：ArrayList 的数组结构、默认容量、1.5 倍扩容和线程安全问题
│   │   ├── com.nanxinda.linkedlist：LinkedList 的双向链表结构、增删特点和源码过程
│   │   ├── com.nanxinda.vector_：Vector 的对象数组、同步方法和扩容机制
│   │   ├── com.nanxinda.set_：Set、HashSet、TreeSet，equals/hashCode 去重及红黑树转换条件
│   │   ├── com.nanxinda.linkedhashset：哈希表与双向链表结合、按插入顺序遍历
│   │   ├── com.nanxinda.map_：Map 遍历，HashMap、Hashtable、Properties、TreeMap 及底层结构
│   │   ├── com.nanxinda.collection_：Collections 的排序、打乱、查找、替换等工具方法
│   │   └── com.nanxinda.homework：集合综合题、排序去重和可变字段影响哈希定位等练习
│   │
│   ├── CommonClass/
│   │   ├── com.nanxinda.class_.String_：字符串常量池、不可变性、对象创建方式和常用 API
│   │   ├── com.nanxinda.class_.StringBuffer_：线程安全的可变字符串及增删改查
│   │   ├── com.nanxinda.class_.StringBuilder_：非同步可变字符串和性能使用场景
│   │   ├── com.nanxinda.class_.Arrays_：数组输出、排序和自定义 Comparator
│   │   ├── com.nanxinda.class_.pcakage_：包装类、装箱拆箱、Integer 缓存和比较
│   │   ├── com.nanxinda.class_.DateClass：Date、SimpleDateFormat、Calendar 和 LocalDateTime
│   │   ├── com.nanxinda.class_.BigInteger_and_BigDecimal：大整数和高精度小数运算
│   │   ├── com.nanxinda.class_.Math_：绝对值、幂、开方、随机数和取整等方法
│   │   ├── com.nanxinda.class_.System_：退出程序、时间、数组复制和垃圾回收入口
│   │   └── com.nanxinda.class_.homework_：字符串分割、反转、格式校验和对象比较练习
│   │
│   ├── generic/
│   │   └── com.nanxinda：泛型类、泛型方法、泛型接口、通配符上下界和 JUnit 入门
│   ├── enum/
│   │   └── com.nanxinda.enum_：自定义枚举、enum 语法、Enum 常用方法和枚举练习
│   ├── Exception/
│   │   └── com.nanxinda.exception_：异常体系、try-catch-finally、throws/throw 和自定义异常
│   ├── annotation/
│   │   ├── com.nanxinda.annotaion：Override、Deprecated、SuppressWarnings 等基础注解
│   │   └── com.nanxinda.OPPexercise：抽象类、内部类、静态成员和工厂等 OOP 综合练习
│   │
│   ├── IO/
│   │   ├── com.nanxinda.IO：File、目录、输入输出流分类和文件复制
│   │   ├── com.nanxinda.inputstream：FileInputStream 的单字节与批量读取
│   │   ├── com.nanxinda.outputstream：FileOutputStream 的覆盖与追加写入
│   │   ├── com.nanxinda.filereaderANDfilewriter：字符流读写文本
│   │   ├── com.nanxinda.bufferedwriterANDbufferedreader：缓冲字符流、处理流包装和文本复制
│   │   ├── com.nanxinda.ObjectInputStreamANDObjectOutputStream：对象序列化与反序列化
│   │   ├── com.nanxinda.printStream：PrintStream、PrintWriter 和标准输出重定向
│   │   ├── com.nanxinda.properties：读取 properties 配置文件
│   │   ├── com.nanxinda.transmation：字节流与字符流转换、字符编码
│   │   └── com.nanxinda.homework：目录创建、配置读取和对象序列化综合练习
│   │
│   ├── net/
│   │   ├── com.nanxinda.net：网络基础、IP、端口、协议、InetAddress 和 Socket 概念
│   │   ├── com.nanxinda.netstat：监听端口、连接状态和 netstat
│   │   ├── com.nanxinda.TCPsocket：TCP 文本通信、字节通信和文件传输的客户端/服务端练习
│   │   ├── com.nanxinda.UDP：DatagramSocket、DatagramPacket 的发送与接收
│   │   └── com.nanxinda.homework：TCP 问答、UDP 交互和文件下载综合练习
│   │
│   ├── reflection/
│   │   ├── com.nanxinda.class_：Class 对象、类加载过程、获取 Class 的方式和常用 API
│   │   ├── com.nanxinda.reflection：反射创建对象、访问字段和方法、构造器及性能优化
│   │   └── com.nanxinda.homework：反射修改私有字段、动态创建 File 等练习
│   ├── ProcessANDThread/
│   │   └── com.nanxinda.thread：Thread/Runnable、线程方法、状态、同步、锁、死锁和售票练习
│   ├── case/
│   │   ├── com.nanxinda.cases：对象、继承、多态、员工和账户等阶段性综合题
│   │   ├── com.nanxinda.houserenting：房屋出租系统的实体、菜单和分层结构练习
│   │   └── com.nanxinda.smallchanges：零钱通菜单、收益与消费明细练习
│   └── tank/
│       └── 单独的 Java Swing 坦克小游戏练习，不计入上面的 Java 基础知识单元
```

### 数据结构与算法

```text
数据结构与算法/
├── DataStructure/
│   └── src/com/nanxinda/
│       ├── graph：图的顶点与边、邻接矩阵、DFS、BFS、连通性和无权图路径思路
│       ├── hash：数组加链表实现哈希表，完成员工数据的添加、遍历和查找
│       ├── queue：数组队列的局限，以及使用取模和预留空位实现环形队列
│       ├── sparsearray：二维数组与稀疏数组互转，并将稀疏数据写入文件
│       ├── recursion：迷宫回溯、八皇后二维解法和一维数组优化解法
│       ├── linkedlist/
│       │   ├── singlelinkedlist：单链表按序添加、修改、删除和遍历
│       │   ├── doublelinkedlist：双向链表前驱/后继指针及增删改查
│       │   ├── circularlinkedlist：环形链表和约瑟夫问题
│       │   └── interviewquestion：倒数节点、反转、逆序输出和有序链表合并
│       ├── stack/
│       │   ├── 基础代码：数组栈、链式栈和使用双栈完成四则运算
│       │   └── fixexpression：中缀转后缀、逆波兰表达式和后缀表达式求值
│       └── tree/
│           ├── binarytree.threadbinarytree：顺序二叉树、遍历、查找、删除及前序/中序线索化
│           ├── binarysorttree：二叉排序树的插入、查找和不同节点类型的删除
│           ├── binarybalancedtree.primary：AVL 树平衡因子、LL/RR/LR/RL 旋转的初步实现
│           ├── binarybalancedtree.advance：在递归插入过程中完成 AVL 树的局部旋转
│           ├── huffmantree：权值、WPL 和哈夫曼树构建
│           └── huffmantree.huffman_coding：哈夫曼编码、字符串/字节压缩及文件压缩解压
│
└── arithmetic/
    └── src/com/
        ├── nanxinda/alorithm/
        │   ├── divide_and_rule：使用分治和递归解决汉诺塔
        │   ├── dynamicplanning：使用二维状态表解决 0/1 背包问题
        │   ├── seek.dichotomy：递归与非递归二分查找，并查找全部重复值
        │   ├── seek.fibonacci：斐波那契查找和扩展查找数组
        │   ├── seek.interpolation：插值查找
        │   ├── sort.bubblesort：基础冒泡排序和提前结束优化
        │   ├── sort.selectsort：选择排序
        │   ├── sort.insertsort：插入排序
        │   ├── sort.shellsort：交换式与移位式希尔排序
        │   ├── sort.quicksort：双指针快速排序
        │   ├── sort.mergesort：递归拆分与归并排序
        │   ├── sort.radixsort：桶结构实现基数排序
        │   ├── sort.pilesort：大顶堆构建、下沉调整和堆排序
        │   └── sort.Complexity：时间复杂度、空间复杂度和常见增长量级
        │
        └── zuochengyun/alorithm/
            ├── arithmetic_skill：位运算、异或题、位图/BitSet、二进制四则运算和常见位技巧
            ├── datadesign：随机集合、快照数组、LRU 缓存等数据结构设计题
            ├── heapcommonproblems：堆排序、合并 K 个有序链表、数组和减半、生存人数最多年份
            ├── linkedlist：链表相交、K 组翻转、随机指针复制、回文、入环点和归并排序
            └── merge：使用归并过程统计交易逆序对和重要翻转对
```

`nanxinda` 相关目录主要是算法基础训练，`zuochengyun` 相关目录主要是左程云算法进阶训练。

### MySQL

MySQL 学习内容主要保存在 `数据库mysql/untitled/src/com/nanxinda/sql/`。这里的 Java 类以注释和 SQL 示例记录知识点：

```text
数据库mysql/
└── untitled/src/com/nanxinda/sql/
    ├── SQL.java：关系型数据库概念、SQL 通用语法和 DDL/DML/DQL/DCL 分类
    ├── DDLGrammar.java：数据库与表的创建、查询、修改、删除，以及字段数据类型
    ├── DML.java：单条/批量 INSERT、UPDATE 和 DELETE
    ├── DQL.java：基础查询、条件、聚合、分组、排序、分页和 SQL 执行顺序
    ├── DCL.java：MySQL 用户创建、密码修改、删除、授权和撤销权限
    ├── Constraint.java：非空、唯一、主键、默认、检查、外键及级联行为
    ├── Function.java：字符串、数值、日期和流程控制函数
    ├── Muti_table_queries.java：表关系、内外连接、自连接、UNION 和各类子查询
    ├── Affair.java：事务提交/回滚、ACID、并发问题和四种隔离级别
    ├── StorageEngine.java：创建表时指定存储引擎和查看 MySQL 支持的引擎
    ├── Index.java：索引优缺点、B+Tree/Hash、聚集索引、二级索引和回表
    ├── IndexGrammar.java：主键、唯一、普通索引的查看、创建和删除语法
    ├── IndexUse.java：最左前缀、范围查询、函数运算、模糊查询、覆盖索引和索引提示
    ├── PerformanceAnalysis.java：访问频率、慢查询、profile、EXPLAIN、排序/分组/分页/count 优化
    ├── SQLOptimize.java：批量插入、LOAD DATA、顺序主键、页分裂/合并和主键设计
    └── View.java：视图的创建、修改、删除、检查选项、可更新条件和使用价值
```

### JDBC

```text
jdbc/
└── jdbc_quickstart/
    └── JDBC 驱动注册与 DriverManager、Connection 事务、Statement、ResultSet、
        SQL 注入问题、PreparedStatement 预编译、Druid 连接池，以及查询结果封装为 Brand 对象
```

### MyBatis

```text
mybatis/
└── mybatis/
    └── MyBatis 核心配置、SqlSessionFactory/SqlSession 生命周期、Mapper 动态代理、
        XML 映射、resultMap、@Param/对象/Map 参数传递、动态 SQL 的 if/where/set/foreach、
        品牌数据 CRUD、批量删除、自增主键回填和事务提交
```

### Redis

```text
redis/
├── redie_knowledge/
│   └── src/Main.java：
│       Redis 键值数据库基础，String、Hash、List、Set、SortedSet 五种基本数据结构，
│       GEO、BitMap、HyperLogLog 特殊数据类型，常用命令、Key 命名结构及典型应用场景
│
├── redis_quickstart/
│   └── Jedis 客户端入门项目：
│       使用 Jedis 连接池访问 Redis，练习连接配置、认证、String 和 Hash 基础操作；
│       test 目录中保留了对应练习代码和学习笔记
│
└── redistemplate/
    └── Spring Data Redis 练习项目：
        使用 Spring Boot、RedisTemplate 和 StringRedisTemplate 操作 Redis，
        重点练习连接配置、序列化方式、对象缓存和 Hash 字段存取
```

### Spring、Spring MVC 与 Spring Boot

```text
spring/
├── springFrameWork/
│   └── Spring 模块架构、Core Container、IoC 控制反转和 DI 依赖注入概念
├── spring_quickstart_01/
│   └── XML 配置 Bean、Bean 作用域与生命周期、静态/实例工厂、FactoryBean 和 setter 注入
├── spring_autowire/
│   └── XML 自动装配、BeanFactory/ApplicationContext、引用注入、集合注入和配置文件读取
├── spring_annotation/
│   └── @Component/@Service/@Repository、扫描、@Autowired、@Qualifier、@Value、
│       @PropertySource、@Bean 和纯注解配置
├── spring_aop_demo/
│   └── 切入点表达式，以及 Before、After、Around、AfterReturning、AfterThrowing 通知
├── spring_aop_data/
│   └── JoinPoint/ProceedingJoinPoint 参数、返回值、异常信息和通知获取业务数据
├── spring_case_handle_password/
│   └── 使用环绕通知统一清理 read 方法的字符串参数，再调用资源访问业务
├── spring_mybatis/
│   └── Spring 管理 DataSource、SqlSessionFactory、Mapper 扫描和 Account 注解式 CRUD
├── spring_transaction/
│   └── PlatformTransactionManager、@Transactional、转账回滚和 REQUIRES_NEW 日志事务
├── springssm/
│   └── 传统 Spring + Spring MVC + MyBatis 整合，图书 CRUD、统一响应、异常处理和静态资源
│
├── maven/
│   └── maven_03_pojo：Maven 工程结构、依赖配置和基础 POJO
├── mybatisplus/
│   ├── quickstart：BaseMapper CRUD、QueryWrapper/LambdaQueryWrapper、条件查询、字段投影、
│   │   分组、分页、乐观锁、逻辑删除和日志配置
│   └── generator：FastAutoGenerator 的包名、模板、表映射、乐观锁和逻辑删除生成配置
│
├── springmvc/
│   ├── springmvc_quickstart：DispatcherServlet 初始化、Spring/Spring MVC 容器拆分和首个控制器
│   ├── springmvc_request：普通参数、别名、POJO、嵌套对象、数组、集合、JSON 和日期参数绑定
│   ├── springmvc_response：页面跳转、文本响应、POJO/List 转 JSON 和 HttpMessageConverter
│   ├── springmvc_rest：RESTful 路径、HTTP 方法映射、@RequestBody 和 @PathVariable
│   ├── springmvc_filter：两个 HandlerInterceptor 的注册、执行顺序、放行规则和静态资源处理
│   └── rest_case：图书 REST 接口、JSON 请求和前端静态资源访问案例
│
└── springboot/
    ├── springboot_quickstart：Spring Boot 启动类和简单图书 REST 接口
    ├── config_file：classpath/file 下四级 application.yml 的加载位置与优先级
    ├── read_date：properties/yaml、@Value、@ConfigurationProperties 和多环境 profile
    ├── junit：@SpringBootTest、Service 注入和业务方法测试
    ├── mybatis：Spring Boot 整合 MyBatis，使用注解 Mapper 查询图书
    └── ssm：Spring Boot 整合表现层、业务层和 MyBatis，完成图书 CRUD、
        统一结果封装、业务/系统异常处理，并配有 Vue 页面
```

## 关于 JDBC

JDBC 是 Java 原生连接数据库的基础。理解 JDBC 有助于理解数据库驱动、连接建立、连接池、`Statement`、`PreparedStatement`、事务、结果集和 SQL 注入等底层概念。

对于普通 Java 后端学习路线，可以根据个人兴趣决定深入程度。实际项目中的大多数数据库操作已经由 MyBatis、MyBatis-Plus、Spring Data 等框架封装，日常开发通常不需要大量手写 JDBC。

因此，JDBC 更适合作为理解底层原理的补充内容，不一定需要花过多时间深挖，但也不建议完全跳过。

## 数据库示例配置

仓库中的数据库连接已替换为可公开展示的本地示例值：

```text
Host: 127.0.0.1
Port: 3306
Username: ubuntu
Password: 123456
```
本人是通过SSH隧道连接服务器并在服务器内部操作，所以该配置只对本人有效，请勿盲目照搬。

## 学习资料

Java 基础：`BV1fh411y7R8`

数据结构与算法基础：`BV1E4411H73v`

数据结构与算法进阶：左程云算法通关课

JDBC：`BV1s3411K7jH`

MyBatis：`BV1MT4y1k7wZ`

Redis：`BV1cr4y1671t`

Spring Boot：`BV1Fi4y1S7ix`

## 开源说明

- 本仓库主要用于个人学习记录。
- 代码带有阶段性、实验性和练习性质。
- 部分早期代码不一定符合当前最佳实践，但可以反映学习过程。
- 欢迎初学者参考学习路线，但不要直接照搬配置或任何敏感信息。
- 用于自己的项目时，请根据本地环境修改数据库名称、端口和连接配置。
