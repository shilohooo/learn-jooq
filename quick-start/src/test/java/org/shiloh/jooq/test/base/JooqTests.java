package org.shiloh.jooq.test.base;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.junit.Before;
import org.shiloh.jooq.codegen.tables.daos.SysUserDao;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import static org.jooq.SQLDialect.MYSQL;

/**
 * @author shiloh
 * @date 2023/2/11 15:51
 */
public abstract class JooqTests {
    protected DSLContext dslContext;
    protected SysUserDao sysUserDao;

    @Before
    public void setup() {
        try (
                final InputStream inputStream = JooqTests.class
                        .getClassLoader()
                        .getResourceAsStream("jdbc.properties")
        ) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            final String jdbcUrl = properties.getProperty("jdbc.url");
            final String jdbcUsername = properties.getProperty("jdbc.username");
            final String jdbcPassword = properties.getProperty("jdbc.password");
            this.dslContext = DSL.using(DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword), MYSQL);
            // 初始化 DAO 需要先有 Configuration 对象，如果已经实例化了 DSLContext 对象，
            // 可以使用 dslContext.configuration() 获取配置对象
            this.sysUserDao = new SysUserDao(this.dslContext.configuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
