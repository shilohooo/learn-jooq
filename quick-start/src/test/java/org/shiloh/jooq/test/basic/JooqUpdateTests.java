package org.shiloh.jooq.test.basic;

import org.junit.Test;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * JOOQ 数据更新操作测试
 *
 * @author shiloh
 * @date 2023/2/11 17:09
 */
public class JooqUpdateTests extends JooqTests {
    /**
     * 测试更新用户信息
     *
     * @author shiloh
     * @date 2023/2/11 17:10
     */
    @Test
    public void updateUser() {
        // 生成的 SQL：update `learn_jooq`.`sys_user` set `learn_jooq`.`sys_user`.`username` = ?,
        // `learn_jooq`.`sys_user`.`password` = ? where `learn_jooq`.`sys_user`.`id` = ?
        final int affectedRow = this.dslContext.update(SYS_USER)
                .set(SYS_USER.USERNAME, "shilohooo")
                .set(SYS_USER.PASSWORD, "lixiaolei")
                // 设置条件 where id = ?
                .where(SYS_USER.ID.eq(1L))
                .execute();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * @author shiloh
     * @date 2023/2/11 17:14
     */
    @Test
    public void testUpdateUserUsingRecordApi() {
        final SysUserRecord sysUserRecord = this.dslContext.newRecord(SYS_USER);
        sysUserRecord.setId(1L);
        sysUserRecord.setUsername("shiloh-update");
        sysUserRecord.setAge(18);
        // RecordApi 默认使用表的主键作为 update 语句的 where 条件
        // 该操作生成的 SQL 为：update `learn_jooq`.`sys_user` set `learn_jooq`.`sys_user`.`id` = ?,
        // `learn_jooq`.`sys_user`.`username` = ?, `learn_jooq`.`sys_user`.`age` = ? where `learn_jooq`.`sys_user`.`id` = ?
        final int affectedRow = sysUserRecord.update();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * 测试批量更新用户信息
     *
     * @author shiloh
     * @date 2023/2/11 17:21
     */
    @Test
    public void testBatchUpdateUser() {
        final SysUserRecord sysUserRecord1 = this.dslContext.newRecord(SYS_USER);
        sysUserRecord1.setId(1L);
        sysUserRecord1.setUsername("shiloh-batch-update");
        sysUserRecord1.setPassword("pass-batch-update");

        final SysUserRecord sysUserRecord2 = this.dslContext.newRecord(SYS_USER);
        sysUserRecord2.setId(3L);
        sysUserRecord2.setUsername("bruce-batch-update");
        sysUserRecord2.setPassword("pass-batch-update");

        final List<SysUserRecord> records = new ArrayList<>(2);
        records.add(sysUserRecord1);
        records.add(sysUserRecord2);

        // 批量更新依然是默认使用主键作为 update 语句的 where 条件
        // 生成的 SQL：update `learn_jooq`.`sys_user` set `learn_jooq`.`sys_user`.`id` = ?,
        // `learn_jooq`.`sys_user`.`username` = ?, `learn_jooq`.`sys_user`.`password` = ? where `learn_jooq`.`sys_user`.`id` = ?
        final int[] affectedRows = this.dslContext.batchUpdate(records)
                .execute();
        assertThat(affectedRows).isNotEmpty();
        assertThat(affectedRows.length).isEqualTo(records.size());
    }
}
