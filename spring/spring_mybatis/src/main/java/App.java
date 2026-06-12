import com.nanxinda.dao.AccountDao;
import com.nanxinda.dervice.AccountService;
import com.nanxinda.domain.Account;
import com.nanxinda.spring.SpringConfig;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService bean = applicationContext.getBean(AccountService.class);
        Account byId = bean.findById(1);
        System.out.println(byId);
    }
}
/**
 * Application Execution & Architecture Flow:
 * * 1. IoC Container Initialization & Component Scanning
 * - Based on SpringConfig, the container scans the "com.nanxinda" package.
 * - Registers custom local beans into the container (e.g., AccountServiceImpl via @Service).
 * * 2. Third-Party Configuration (Outer Beans)
 * - Via @Import, JdbcConfig and MybatisConfig are processed to register external dependencies:
 * - DataSource: Configures the Druid connection pool for database access.
 * - SqlSessionFactoryBean: Manages SqlSession lifecycle and integrates MyBatis with Spring.
 * - MapperScannerConfigurer: Dynamically scans "com.nanxinda.dao", generates proxy
 * implementations for interfaces like AccountDao, and registers them into the IoC container.
 * * 3. Dependency Injection & Service Execution
 * - applicationContext.getBean(AccountService.class) retrieves the fully assembled service bean.
 * - During assembly, Spring @Autowired the dynamic proxy of AccountDao into AccountServiceImpl.
 * - Calling bean.findById(1) executes the internal DAO proxy method.
 * * 4. Object Relational Mapping (ORM) & Output
 * - The proxy executes the @Select SQL, maps the database ResultSet directly into
 * the Account domain object, and returns it for standard output.
 */
///MapperScannerConfigurer使用代理模式，AccountDao只是一个接口，并没有真正的执行方法，所以spring和mybatis会使用
///代理对象实现这个接口