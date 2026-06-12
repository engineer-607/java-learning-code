# Java Backend Learning Code

这是一个用于整理 Java 后端学习代码的私人仓库，方便在 Windows 和 Linux 环境之间同步学习进度。

## 目录说明

| 目录 | 内容 |
| --- | --- |
| `Java基础` | Java 语法、面向对象、集合、IO、网络、反射、线程等基础练习 |
| `jdbc` | JDBC API、数据库连接和 SQL 注入相关练习 |
| `mybatis` | MyBatis 配置、Mapper 和持久层练习 |
| `spring` | Maven、Spring、Spring MVC、Spring Boot、MyBatis-Plus 等练习项目 |
| `数据结构与算法` | 常见数据结构、算法题和实现练习 |
| `数据库mysql` | MySQL 语法、管理和数据库相关学习代码 |

## 开发环境

- JDK
- Maven
- MySQL（部分项目需要）
- IntelliJ IDEA 或其他 Java IDE

各 Maven 项目相互独立，请进入包含 `pom.xml` 的项目目录后执行 Maven 命令。

## 安全说明

构建产物、IDE 配置、日志、临时文件、压缩包、视频和本地环境文件不会提交。

包含数据库地址、账号、密码或本机网络信息的原始文件已通过根目录 `.gitignore` 排除。跨设备使用时，请创建不含真实凭据的示例配置，并通过环境变量或仅保存在本机的配置文件提供实际连接信息。

`spring/spring_autowire` 已作为普通学习目录纳入本仓库，不使用 Git submodule。该项目的真实 `jdbc.properties` 保留在本机并被忽略，仓库中提供 `jdbc-example.properties` 作为配置模板。
