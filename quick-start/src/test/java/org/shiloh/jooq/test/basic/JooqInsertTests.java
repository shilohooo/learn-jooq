package org.shiloh.jooq.test.basic;

import org.jooq.UDT;
import org.junit.Test;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * Jooq Insert 测试
 * <p>
 * JOOQ的数据操作通常有两种方式， 第一种是使用 DSLContext API 以类SQL的语法进行调用，第二种是利用 Record API 进行调用
 *
 * @author shiloh
 * @date 2023/2/11 15:50
 */
public class JooqInsertTests extends JooqTests {
    /**
     * 测试插入用户信息，支持批量插入
     *
     * @author shiloh
     * @date 2023/2/11 15:52
     */
    @Test
    public void testInsertUserUsingDslContext() {
        // 类 SQL 语法，通过 dslContext 的 insertInto() 方法完成
        // 该方法的第一个参数通常是表常量，后续是要设置值的字段常量
        final int affectedRows = this.dslContext.insertInto(SYS_USER, SYS_USER.USERNAME, SYS_USER.PASSWORD, SYS_USER.AGE, SYS_USER.SEX, SYS_USER.EMAIL)
                .values("jack", "123456", 20, "男", "jack@qq.com")
                .values("admin", "admin", 20, "男", "admin@gmail.com")
                // 执行插入
                .execute();
        assertThat(affectedRows).isEqualTo(2);
    }

    /**
     * 测试插入用户信息，支持批量插入
     * <p>
     * 使用 {@link org.jooq.DSLContext#newRecord(UDT)}方法来添加多条记录
     *
     * @author shiloh
     * @date 2023/2/11 15:59
     */
    @Test
    public void testInsertUserUsingDslContextWithNewRecord() {
        final int affectedRows = this.dslContext.insertInto(SYS_USER)
                .set(SYS_USER.USERNAME, "ray")
                .set(SYS_USER.PASSWORD, "123456")
                .newRecord()
                .set(SYS_USER.USERNAME, "reiner")
                .set(SYS_USER.PASSWORD, "111111")
                // execute() 方法 - 在执行 insert 操作时，会返回插入记录的数量
                .execute();
        assertThat(affectedRows).isEqualTo(2);
    }

    /**
     * 测试使用 RecordApi 插入用户信息
     *
     * @author shiloh
     * @date 2023/2/11 16:05
     */
    @Test
    public void testInsertUserUsingRecordApi() {
        final SysUserRecord sysUserRecord = this.dslContext.newRecord(SYS_USER);
        sysUserRecord.setUsername("record");
        sysUserRecord.setPassword("secrets");
        sysUserRecord.setAge(20);
        // 执行插入
        final int affectedRows = sysUserRecord.insert();
        assertThat(affectedRows).isEqualTo(1);
    }

    /**
     * 测试批量插入用户信息
     *
     * @author shiloh
     * @date 2023/2/11 16:25
     */
    @Test
    public void testBatchInsertUsers() {
        final List<SysUserRecord> sysUserRecords = IntStream.range(0, 3)
                .mapToObj(i -> {
                    final SysUserRecord sysUserRecord = new SysUserRecord();
                    final int val = i + 1;
                    sysUserRecord.setUsername("batch-insert-" + val);
                    sysUserRecord.setPassword("pass-" + val);
                    sysUserRecord.setAge(val);
                    return sysUserRecord;
                })
                .collect(Collectors.toList());
        final int[] affectedRows = this.dslContext.batchInsert(sysUserRecords)
                .execute();
        assertThat(affectedRows).isNotEmpty();
        assertThat(affectedRows.length).isEqualTo(sysUserRecords.size());
    }

    /**
     * 测试插入用户信息，操作完成后获取自增主键
     *
     * @author shiloh
     * @date 2023/2/11 16:32
     */
    @Test
    public void testInsertUserThenGetPrimaryKeyWhenCompleted() {
        final Long id = this.dslContext.insertInto(
                        // table
                        SYS_USER,
                        // fields
                        SYS_USER.USERNAME,
                        SYS_USER.PASSWORD,
                        SYS_USER.AGE,
                        SYS_USER.SEX
                )
                .values("thomas", "123456", 25, "男")
                .returning(SYS_USER.ID)
                .fetchOne()
                .getId();
        assertThat(id).isNotNull();
        assertThat(id).isGreaterThan(0L);
    }

    /**
     * 测试插入用户信息，操作完成后获取自增主键
     * <p>
     * 通过 {@link org.jooq.DSLContext#newRecord(UDT)} 的方式
     *
     * @author shiloh
     * @date 2023/2/11 16:38
     */
    @Test
    public void testInsertUserThenGetPrimaryKeyWhenCompletedUsingNewRecord() {
        final SysUserRecord sysUserRecord = this.dslContext.newRecord(SYS_USER);
        sysUserRecord.setUsername("ForrestGump");
        sysUserRecord.setPassword("123456");
        sysUserRecord.setAge(30);
        // 执行 record.insert() 方法，插入成功后，自增主键会设置到对应的字段中
        sysUserRecord.insert();
        assertThat(sysUserRecord.getId()).isNotNull();
        assertThat(sysUserRecord.getId()).isGreaterThan(0);
    }

    /**
     * 测试插入用户信息，如果主键已存在则取消插入
     *
     * @author shiloh
     * @date 2023/2/11 16:54
     */
    @Test
    public void testInsertUserIfKeyDuplicateThenCancel() {
        final int affectedRow = this.dslContext.insertInto(
                        SYS_USER,
                        SYS_USER.ID,
                        SYS_USER.USERNAME
                )
                .values(1L, "shiloh-failure")
                .onDuplicateKeyIgnore()
                .execute();
        assertThat(affectedRow).isEqualTo(0);
    }

    /**
     * 测试插入用户信息，如果主键已存在，则执行 update 操作
     *
     * @author shiloh
     * @date 2023/2/11 16:58
     */
    @Test
    public void testInsertUserIfKeyDuplicateThenUpdate() {
        // 生成的SQL：insert into `learn_jooq`.`sys_user` (`id`, `username`, `password`) values (?, ?, ?)
        // on duplicate key update `learn_jooq`.`sys_user`.`username` = ?, `learn_jooq`.`sys_user`.`password` = ?
        final int affectedRow = this.dslContext.insertInto(SYS_USER)
                .set(SYS_USER.ID, 1L)
                .set(SYS_USER.USERNAME, "shiloh")
                .set(SYS_USER.PASSWORD, "123456")
                .onDuplicateKeyUpdate()
                .set(SYS_USER.USERNAME, "shiloh595")
                .set(SYS_USER.PASSWORD, "12345678")
                .execute();
        assertThat(affectedRow).isEqualTo(2);
    }
}
