package org.shiloh.jooq.test.result;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.NoDataFoundException;
import org.junit.Test;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.entity.dto.SysUserDto;
import org.shiloh.jooq.test.base.JooqTests;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysDept.SYS_DEPT;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * jOOQ 查询结果处理测试
 *
 * @author shiloh
 * @date 2023/2/12 14:58
 */
public class JooqResultHandleTests extends JooqTests {
    /**
     * 测试查询所有用户信息，转换为 {@link org.shiloh.jooq.entity.dto.SysUserDto} 列表
     *
     * @author shiloh
     * @date 2023/2/12 14:59
     */
    @Test
    public void testGetAllUsersIntoDto() {
        final List<SysUserDto> sysUserDtoList = this.dslContext.select()
                .from(SYS_USER)
                // fetch() 方法可以接收一个 RecordMapper 类型参数，可以将查询出来的 Record 对象转换为指定的类型
                .fetch(record -> record.into(SysUserDto.class));
        assertThat(sysUserDtoList).isNotEmpty();
    }

    /**
     * 测试关联查询，将结果集转化为指定的 dto 列表，
     * <p>
     * 当字段名称相同时，相同字段名称的方法会以最后一个字段值为准。
     * <p>
     * 此时可以先将结果集转换为指定的 Record 对象，然后再转换为指定的 dto 类型
     * <p>
     * 最后再通过 Record 获取相同字段名称的值
     *
     * @author shiloh
     * @date 2023/2/12 15:05
     */
    @Test
    public void testAssociatedQueryIntoDto() {
        // final List<SysUserDto> sysUserDtoList = this.dslContext.select()
        //         .from(SYS_USER)
        //         .leftJoin(SYS_DEPT)
        //         .on(SYS_USER.DEPT_ID.eq(SYS_DEPT.ID))
        //         .where(SYS_USER.ID.eq(5L))
        //         .fetch(record -> record.into(SysUserDto.class));
        // assertThat(sysUserDtoList).isNotEmpty();
        // 此处本应该是查询 ID 为 5 的用户信息，但是应为 ID 字段和部门表相同，
        // 而部门表查出的 ID 字段的值为 6，所以 dto 的 ID 字段值被部门表覆盖了
        // System.out.println(sysUserDtoList);

        final List<SysUserDto> sysUserDtoList = this.dslContext.select()
                .from(SYS_USER)
                .leftJoin(SYS_DEPT)
                .on(SYS_USER.DEPT_ID.eq(SYS_DEPT.ID))
                .where(SYS_USER.ID.eq(5L))
                .fetch(record -> {
                    // 先转为主表对应的 Record 对象再转为指定的 dto 类型
                    final SysUserDto sysUserDto = record.into(SysUserRecord.class)
                            .into(SysUserDto.class);
                    sysUserDto.setDeptName(record.get(SYS_DEPT.NAME));
                    return sysUserDto;
                });
        assertThat(sysUserDtoList).isNotEmpty();
        System.out.println(sysUserDtoList);
    }

    /**
     * 测试获取查询结果集中的指定字段
     *
     * @author shiloh
     * @date 2023/2/12 15:17
     */
    @Test
    public void testGetSpecifiedFields() {
        final List<Long> sysUserIds = this.dslContext.select()
                .from(SYS_USER)
                // 把需要的字段传给 fetch 方法即可，会自动根据传入的字段推断结果集类型
                .fetch(SYS_USER.ID);
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`id`, `learn_jooq`.`sys_user`.`username`,
        // `learn_jooq`.`sys_user`.`password`, `learn_jooq`.`sys_user`.`age`, `learn_jooq`.`sys_user`.`sex`,
        // `learn_jooq`.`sys_user`.`email`, `learn_jooq`.`sys_user`.`dept_id` from `learn_jooq`.`sys_user`
        assertThat(sysUserIds).isNotEmpty();
    }

    /**
     * 测试查询指定的字段
     *
     * @author shiloh
     * @date 2023/2/12 15:17
     */
    @Test
    public void testSelectSpecifiedFields() {
        // 在 select 方法中传入想要查询的字段
        final List<String> usernames = this.dslContext.select(SYS_USER.USERNAME)
                .from(SYS_USER)
                .fetch(SYS_USER.USERNAME);
        // 生成的 SQL：select `learn_jooq`.`sys_user`.`username` from `learn_jooq`.`sys_user`
        assertThat(usernames).isNotEmpty();
    }

    /**
     * 测试查询一条记录
     * <p>
     * fetchAny, fetchOne, fetchSingle 方法返回单条数据，但是对于 数据为空、SQL结果为多条数据 的情况下，处理方式各不相同：
     *
     * <table>
     *     <thead>
     *         <tr>
     *             <th>方法名称</th>
     *             <th>无数据处理</th>
     *             <th>多条数据处理</th>
     *             <th>单条数据处理</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <td>fetchAny</td>
     *             <td>返回空</td>
     *             <td>返回第一条</td>
     *             <td>正常返回</td>
     *         </tr>
     *         <tr>
     *             <td>fetchOne</td>
     *             <td>返回空</td>
     *             <td>抛出异常：{@link org.jooq.exception.TooManyRowsException}</td>
     *             <td>正常返回</td>
     *         </tr>
     *         <tr>
     *             <td>fetchSingle</td>
     *             <td>抛出异常：{@link org.jooq.exception.NoDataFoundException}</td>
     *             <td>抛出异常：{@link org.jooq.exception.TooManyRowsException}</td>
     *             <td>正常返回</td>
     *         </tr>
     *     </tbody>
     * </table>
     *
     * @author shiloh
     * @date 2023/2/12 15:23
     */
    // @Test
    // @Test(expected = TooManyRowsException.class)
    @Test(expected = NoDataFoundException.class)
    public void testSelectOne() {
        // final SysUserDto sysUserDto = this.dslContext.select()
        //         .from(SYS_USER)
        //         .fetchAny(record -> record.into(SysUserDto.class));
        // assertThat(sysUserDto).isNotNull();

        // this.dslContext.select()
        //         .from(SYS_USER)
        //         .fetchOne(record -> record.into(SysUserDto.class));

        // this.dslContext.select()
        //         .from(SYS_USER)
        //         .fetchSingle(record -> record.into(SysUserDto.class));

        this.dslContext.select()
                .from(SYS_USER)
                .where(SYS_USER.AGE.gt(20))
                .fetchSingle(record -> record.into(SysUserDto.class));
    }

    /**
     * 测试查询 Map 结果集
     * <p>
     * 注意：key 在结果集中必须是唯一的，否则会报错
     *
     * @author shiloh
     * @date 2023/2/12 15:36
     */
    @Test
    public void testSelectMap() {
        // key = ID，value = dto
        final Map<Long, SysUserDto> sysUserDtoMap = this.dslContext.select()
                .from(SYS_USER)
                .fetchMap(SYS_USER.ID, SysUserDto.class);
        assertThat(sysUserDtoMap).isNotEmpty();
        // key = ID，value = 用户名
        final Map<Long, String> usernameMap = this.dslContext.select()
                .from(SYS_USER)
                .fetchMap(SYS_USER.ID, SYS_USER.USERNAME);
        assertThat(usernameMap).isNotEmpty();
    }

    /**
     * 测试查询以某个字段分组的结果集
     *
     * @author shiloh
     * @date 2023/2/12 15:39
     */
    @Test
    public void testSelectGroup() {
        // key = 性别，value = 用户列表
        final Map<String, Result<Record>> userGroupBySexMap = this.dslContext.select()
                .from(SYS_USER)
                .where(SYS_USER.SEX.isNotNull())
                .fetchGroups(SYS_USER.SEX);
        assertThat(userGroupBySexMap).isNotEmpty();
        assertThat(userGroupBySexMap.keySet()).contains("男");
        assertThat(userGroupBySexMap.keySet()).contains("女");
    }
}
