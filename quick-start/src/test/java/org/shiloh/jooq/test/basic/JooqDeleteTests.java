package org.shiloh.jooq.test.basic;

import org.junit.Test;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * jOOQ 删除操作测试
 *
 * @author shiloh
 * @date 2023/2/12 12:59
 */
public class JooqDeleteTests extends JooqTests {
    /**
     * 测试根据 ID 删除用户信息
     *
     * @author shiloh
     * @date 2023/2/12 12:59
     */
    @Test
    public void testDeleteUserById() {
        // 删除操作返回值为：受影响的行数
        // 通过 DSL 的方式可以构建灵活的条件
        // 生成的 SQL：delete from `learn_jooq`.`sys_user` where `learn_jooq`.`sys_user`.`id` = ?
        final int affectedRow = this.dslContext.delete(SYS_USER)
                .where(SYS_USER.ID.eq(37L))
                .execute();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * 测试使用 Record Api 的方式删除用户信息
     *
     * @author shiloh
     * @date 2023/2/12 13:03
     */
    @Test
    public void testDeleteUserUsingRecordApi() {
        final SysUserRecord sysUserRecord = this.dslContext.newRecord(SYS_USER);
        sysUserRecord.setId(23L);
        // Record Api 的 delete 方法根据表对于的主键作为条件来进行删除操作
        // 生成的 SQL：delete from `learn_jooq`.`sys_user` where `learn_jooq`.`sys_user`.`id` = ?
        // 如果 Record 对象的 ID 为 null，那么生成的 SQL 为：
        // delete from `learn_jooq`.`sys_user` where `learn_jooq`.`sys_user`.`id` is null
        final int affectedRow = sysUserRecord.delete();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * 测试批量删除用户信息
     *
     * @author shiloh
     * @date 2023/2/12 13:09
     */
    @Test
    public void testBatchDeleteUser() {
        // 通过构建多个 record 对象，调用 Record Api 的 batchDelete() 方法可以进行批量删除操作
        final List<SysUserRecord> sysUserRecords = new ArrayList<>(2);
        final SysUserRecord sysUserRecord1 = this.dslContext.newRecord(SYS_USER);
        sysUserRecord1.setId(33L);
        sysUserRecords.add(sysUserRecord1);
        final SysUserRecord sysUserRecord2 = this.dslContext.newRecord(SYS_USER);
        sysUserRecord2.setId(35L);
        sysUserRecords.add(sysUserRecord2);
        final int[] affectedRows = this.dslContext.batchDelete(sysUserRecords)
                .execute();
        assertThat(affectedRows).isNotEmpty();
        assertThat(affectedRows.length).isEqualTo(sysUserRecords.size());
    }
}
