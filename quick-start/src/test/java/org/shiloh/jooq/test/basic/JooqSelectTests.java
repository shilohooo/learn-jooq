package org.shiloh.jooq.test.basic;

import org.jooq.Record;
import org.jooq.Record8;
import org.jooq.Result;
import org.junit.Test;
import org.shiloh.jooq.codegen.tables.TSysUser;
import org.shiloh.jooq.entity.dto.SysUserDto;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysDept.SYS_DEPT;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * JOOQ 查询数据测试
 * <p>
 * 查询操作通常是通过类型 SQL 的方式完成
 *
 * @author shiloh
 * @date 2023/2/11 17:27
 */
public class JooqSelectTests extends JooqTests {
    /**
     * 单表查询测试
     * <p>
     * 基本查询方法，默认查询指定表的所有字段，返回一个结果集的包装，通过Result.into方法，可以将结果集转换为任意指定类型集合，
     * <p>
     * 也可以通过 Record.getValue 方法取得任意字段值，值类型依赖于字段类型
     *
     * @author shiloh
     * @date 2023/2/11 17:28
     */
    @Test
    public void testSingleTableQuery() {
        // 查询所有数据，默认查询全部字段
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`id`, `learn_jooq`.`sys_user`.`username`,
        // `learn_jooq`.`sys_user`.`password`, `learn_jooq`.`sys_user`.`age`, `learn_jooq`.`sys_user`.`sex`,
        // `learn_jooq`.`sys_user`.`email` from `learn_jooq`.`sys_user`
        final Result<Record> result = this.dslContext.select()
                .from(SYS_USER)
                .fetch();
        assertThat(result.isNotEmpty()).isTrue();
        final List<TSysUser> sysUsers = result.into(TSysUser.class);
        assertThat(sysUsers).isNotEmpty();

        // 条件查询
        final Result<Record> ageNotNullResult = this.dslContext.select()
                .from(SYS_USER)
                .where(SYS_USER.AGE.isNotNull())
                .fetch();
        assertThat(ageNotNullResult.isNotEmpty()).isTrue();
        final List<TSysUser> ageNotNullUsers = ageNotNullResult.into(TSysUser.class);
        assertThat(ageNotNullUsers).isNotEmpty();
    }

    /**
     * 测试关联查询
     *
     * @author shiloh
     * @date 2023/2/11 17:43
     */
    @Test
    public void testAssociatedQuery() {
        final Result<Record8<Long, String, String, Integer, String, String, Long, String>> result = this.dslContext.select(
                        SYS_USER.ID,
                        SYS_USER.USERNAME,
                        SYS_USER.PASSWORD,
                        SYS_USER.AGE,
                        SYS_USER.SEX,
                        SYS_USER.EMAIL,
                        SYS_DEPT.ID.as("deptId"),
                        SYS_DEPT.NAME.as("deptName")
                )
                .from(SYS_USER)
                .leftJoin(SYS_DEPT)
                .on(SYS_USER.DEPT_ID.eq(SYS_DEPT.ID))
                // 设置查询条件
                .where(SYS_USER.AGE.le(20))
                // 设置排序条件
                .orderBy(SYS_USER.ID.desc())
                // 设置分页条件
                .limit(3, 3)
                .fetch();
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`id`, `learn_jooq`.`sys_user`.`username`,
        // `learn_jooq`.`sys_user`.`password`, `learn_jooq`.`sys_user`.`age`, `learn_jooq`.`sys_user`.`sex`,
        // `learn_jooq`.`sys_user`.`email`, `learn_jooq`.`sys_dept`.`id` as `deptId`,
        // `learn_jooq`.`sys_dept`.`name` as `deptName` from `learn_jooq`.`sys_user`
        // left outer join `learn_jooq`.`sys_dept` on `learn_jooq`.`sys_user`.`dept_id` = `learn_jooq`.`sys_dept`.`id`
        // order by `learn_jooq`.`sys_user`.`id` desc limit ? offset ?
        assertThat(result.isNotEmpty()).isTrue();
        final List<SysUserDto> sysUserDtoList = result.into(SysUserDto.class);
        assertThat(sysUserDtoList).isNotEmpty();
    }
}
