package org.shiloh.jooq.test.dao;

import org.junit.Test;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * jOOQ DAO 测试
 *
 * @author shiloh
 * @date 2023/2/12 17:01
 */
public class JooqDaoTests extends JooqTests {
    /**
     * 测试根据 ID 查询用户信息
     *
     * @author shiloh
     * @date 2023/2/12 17:02
     */
    @Test
    public void testFetchOneById() {
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`id`, `learn_jooq`.`sys_user`.`username`,
        // `learn_jooq`.`sys_user`.`password`, `learn_jooq`.`sys_user`.`age`, `learn_jooq`.`sys_user`.`sex`,
        // `learn_jooq`.`sys_user`.`email`, `learn_jooq`.`sys_user`.`dept_id` from `learn_jooq`.`sys_user`
        // where `learn_jooq`.`sys_user`.`id` = ?
        final SysUserPojo sysUserPojo = this.sysUserDao.fetchOneById(1L);
        assertThat(sysUserPojo).isNotNull();
        final SysUserPojo findById = this.sysUserDao.findById(1L);
        assertThat(findById).isNotNull();
    }

    /**
     * 测试查询所有用户信息
     *
     * @author shiloh
     * @date 2023/2/12 17:08
     */
    @Test
    public void testFetchAll() {
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`id`, `learn_jooq`.`sys_user`.`username`,
        // `learn_jooq`.`sys_user`.`password`, `learn_jooq`.`sys_user`.`age`, `learn_jooq`.`sys_user`.`sex`,
        // `learn_jooq`.`sys_user`.`email`, `learn_jooq`.`sys_user`.`dept_id` from `learn_jooq`.`sys_user`
        final List<SysUserPojo> sysUsers = this.sysUserDao.findAll();
        assertThat(sysUsers).isNotEmpty();
    }
}
