package org.shiloh.jooq.test.basic;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Statement;
import org.jooq.Table;
import org.junit.Test;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * JOOQ 查询测试
 *
 * @author shiloh
 * @date 2023/2/11 15:24
 */
public class JooqSimpleTests extends JooqTests {

    @Test
    public void parserTest() {
        final String sql = "create table sys_user" +
                "(" +
                "    id       bigint auto_increment comment 'ID，自增主键'" +
                "        primary key," +
                "    username varchar(50)  null comment '用户名'," +
                "    password varchar(255) null comment '密码'," +
                "    age      int          null comment '年龄'," +
                "    sex      varchar(10)  null comment '性别'," +
                "    email    varchar(255) null comment '电子邮箱'," +
                "    dept_id  bigint       null comment '所在部门 ID'" +
                ")" +
                "    comment '系统用户信息表';";
        final Statement statement = this.dslContext.parser().parseStatement(sql);
        System.out.println(statement);
    }

    /**
     * JOOQ 查询测试
     *
     * @author shiloh
     * @date 2023/2/11 15:30
     */
    @Test
    public void testJooqQuery() {
        // 获取 JOOQ 执行器上下文
        // fetch 方法返回一个结果集对象 Result
        // Result 对象实现了 List 接口，可以直接当作集合使用
        final Result<Record> result = this.dslContext.select().from(SYS_USER)
                .fetch();
        assertThat(result).isNotEmpty();
        // result.forEach(record -> {
        //     // 获取用户信息
        //     final long id = record.getValue(SYS_USER.ID);
        //     final String username = record.getValue(SYS_USER.USERNAME);
        //     System.out.printf("id = %d, username = %s", id, username);
        //     System.out.println(record);
        // });

        // 将查询出来的结果集转换为表对应的 record 对象
        // 表对应的 record 对象是 Record 对象的子类
        final Result<SysUserRecord> sysUserRecords = result.into(SYS_USER);
        assertThat(sysUserRecords).isNotEmpty();
        // sysUserRecords.forEach(sysUserRecord -> {
        //     // 转换后的对象可以直接获取到表字段对应的数据
        //     final long id = sysUserRecord.getId();
        //     final String username = sysUserRecord.getUsername();
        //     System.out.printf("id = %d, username = %s", id, username);
        //     System.out.println(sysUserRecord);
        // });

        // fetchInto 方法可以传入任意 record 对象的 class 类型或者表常量
        // 这会返回任意 class 类型的List集合或指定表 record 的结果集对象
        final List<SysUserRecord> sysUserRecordList = this.dslContext.select().from(SYS_USER)
                .fetchInto(SysUserRecord.class);
        assertThat(sysUserRecordList).isNotEmpty();

        final Result<SysUserRecord> sysUserRecordResult = this.dslContext.select().from(SYS_USER)
                .fetchInto(SYS_USER);
        assertThat(sysUserRecordResult).isNotEmpty();
    }
}
