package org.shiloh.jooq.test.record;

import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.exception.DetachedException;
import org.junit.Test;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.shiloh.jooq.entity.dto.SysUserDto;
import org.shiloh.jooq.test.base.JooqTests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * jOOQ Record 测试
 * <p>
 * {@link org.jooq.Record} 是 jOOQ 定义的用于储存数据库结果记录的一个接口，其主要是将一个表字段的列表和值的列表使用相同的顺序储存在一起，
 * 可以看做是一个用于储存列/值的映射的对象。通常有以下几种形式：
 *
 * <h3># 表记录</h3>
 * <p>
 * 与数据库表一一对应，如果包含主键，会继承UpdatableRecordImpl类，该类提供了使用 update, delete API进行数据操作。
 * 进行查询操作时，jOOQ 会将结果集包装为一个TableRecord 对象。 在使用代码生成器的时候，会生成更详细的表记录类，包含表的每个字段操作等，
 * 通常以表名为开头 XxxRecord
 * <p>
 * 此类 Record 对象一般都有对应字段的getter/setter方法，但其都只是去调用get/set方法。其储存的方式还是通过两个数组来储存对应列/值的数据的，
 * 所以Record对象是不能被JSON直接序列化和反序列化的
 *
 * <h3># UDT记录</h3>
 * <p>
 * 通常用于 Oracle 等支持用户自定义数据类型的数据库记录
 *
 * <h3># 明确的数据记录</h3>
 * <p>
 * 通用记录类型的一种，当表的字段不超过22个时，会根据字段个数反射成 Record1, Record2 … Record22 类的对象。
 * 这些对象需要的泛型个数和后面的数字一致，类型和字段类型一致。jOOQ 自动生成的Record对象里，如果字段个数不超过 22 个，
 * 会同时实现 Record[N] 接口
 *
 * @author shiloh
 * @date 2023/2/12 15:47
 */
public class JooqRecordTests extends JooqTests {
    /**
     * 测试直接创建 {@link org.jooq.Record} 对象
     *
     * @author shiloh
     * @date 2023/2/12 15:51
     */
    @Test(expected = DetachedException.class)
    public void testCreateRecordByNew() {
        // 直接 new 创建的 Record 对象，只是一种单纯的数据存储对象，
        // 由于不是通过 DSLContext 创建的，因此不具备连接信息，无法执行增删改操作
        // 但是可以通过 DSLContext 的 API 进行数据操作
        final SysUserRecord sysUserRecord = new SysUserRecord();
        sysUserRecord.setUsername("new-record");
        sysUserRecord.setPassword("record-pass");
        // 此时会报错：org.jooq.exception.DetachedException: Cannot execute query. No Connection configured
        sysUserRecord.insert();

        // 可通过 DSLContext 执行数据操作
        // final int affectedRow = this.dslContext.insertInto(SYS_USER)
        //         .set(sysUserRecord)
        //         .execute();
    }

    /**
     * 测试通过 {@link org.jooq.DSLContext#newRecord(Table)} 创建 {@link org.jooq.Record} 对象，
     * <p>
     * 通过此方法创建的对象包含了dslContext内的数据连接配置，可以直接进行 insert, update, delete 等操作。
     *
     * @author shiloh
     * @date 2023/2/12 15:59
     */
    @Test
    public void testCreateRecordByDslContext() {
        final SysUserRecord sysUserRecord = this.dslContext.newRecord(SYS_USER);
        sysUserRecord.setUsername("new-record");
        sysUserRecord.setPassword("new-record");
        sysUserRecord.setAge(28);
        final int affectedRow = sysUserRecord.insert();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * 测试通过 fetch api 查询创建 {@link org.jooq.Record} 对象
     * <p>
     * 通过fetch*方法读取到结果 Record 对象，同样带有数据库连接相关配置，并且带有查询结果的数据。可以直接进行数据操作
     *
     * @author shiloh
     * @date 2023/2/12 16:18
     */
    @Test
    public void testCreateRecordByFetchApi() {
        final SysUserRecord sysUserRecord = this.dslContext.selectFrom(SYS_USER)
                .where(SYS_USER.ID.eq(1L))
                .fetchOne();
        // 只有经过 set 的字段，才会被 insert、update 处理
        sysUserRecord.setUsername("shiloh595");
        sysUserRecord.setAge(26);
        // change 方法可以指定字段是否被更新或插入
        // sysUserRecord.changed(SYS_USER.AGE, false);
        // reset 方法用于重置字段更新/插入标识，效果和 change(field, false) 相同
        sysUserRecord.reset(SYS_USER.AGE);
        final int affectedRow = sysUserRecord.update();
        assertThat(affectedRow).isEqualTo(1);
    }

    /**
     * 测试 {@link org.jooq.Record#from(Object)} 方法
     * <p>
     * 此方法可以将任意的对象填充至 {@link org.jooq.Record} 中
     *
     * @author shiloh
     * @date 2023/2/12 16:25
     */
    @Test
    public void testRecordFromMethod() {
        // from obj
        final SysUserPojo sysUserPojo = new SysUserPojo();
        sysUserPojo.setId(1L);
        sysUserPojo.setUsername("shiloh595");
        final SysUserRecord fromObj = this.dslContext.newRecord(SYS_USER);
        fromObj.from(sysUserPojo);
        assertThat(fromObj.getId()).isNotNull();
        assertThat(fromObj.getId()).isEqualTo(sysUserPojo.getId());
        assertThat(fromObj.getUsername()).isNotBlank();
        assertThat(fromObj.getUsername()).isEqualTo(sysUserPojo.getUsername());
        // from map
        final Map<String, Object> map = new HashMap<>(2);
        map.put("id", 1L);
        map.put("username", "shiloh");
        final SysUserRecord fromMap = this.dslContext.newRecord(SYS_USER);
        fromMap.fromMap(map);
        assertThat(fromMap.getId()).isNotNull();
        assertThat(fromMap.getId()).isEqualTo(map.get("id"));
        assertThat(fromMap.getUsername()).isNotBlank();
        assertThat(fromMap.getUsername()).isEqualTo(map.get("username"));
        // from array
        final Object[] objects = {1L, "shiloh"};
        final SysUserRecord fromArr = this.dslContext.newRecord(SYS_USER);
        // 指定的字段类型需要与数组值一一对应
        fromArr.fromArray(objects, SYS_USER.ID, SYS_USER.USERNAME);
        assertThat(fromArr.getId()).isNotNull();
        assertThat(fromArr.getId()).isEqualTo(objects[0]);
        assertThat(fromArr.getUsername()).isNotBlank();
        assertThat(fromArr.getUsername()).isEqualTo(objects[1]);
    }

    /**
     * 测试 {@link org.jooq.Record#into(Table)} 方法
     * <p>
     * 此系列方法是将 {@link org.jooq.Record} 转换为其他任意指定类型
     *
     * @author shiloh
     * @date 2023/2/12 16:36
     */
    @Test
    public void testRecordIntoMethod() throws SQLException {
        final SysUserRecord sysUserRecord = this.dslContext.selectFrom(SYS_USER)
                .where(SYS_USER.ID.eq(1L))
                .fetchOne();
        assertThat(sysUserRecord).isNotNull();
        // 转换为指定类型的对象，此方法主要是通过反射的方式，创建目标类实例，并将对应字段值设置到目标实例中，也是在业务代码中用的最多的一个重载。
        final SysUserDto sysUserDto = sysUserRecord.into(SysUserDto.class);
        assertThat(sysUserDto).isNotNull();
        // 转换为包含指定字段的 Record 对象
        final Record2<Long, String> record2 = sysUserRecord.into(SYS_USER.ID, SYS_USER.USERNAME);
        assertThat(record2).isNotNull();
        assertThat(record2.get(SYS_USER.ID)).isNotNull();
        assertThat(record2.get(SYS_USER.USERNAME)).isNotBlank();
        // 转换为数组
        final Object[] objects = sysUserRecord.intoArray();
        assertThat(objects).isNotEmpty();
        // 转换为 map
        final Map<String, Object> map = sysUserRecord.intoMap();
        assertThat(map).isNotEmpty();
        // 转换为 JDBC ResultSet
        final ResultSet resultSet = sysUserRecord.intoResultSet();
        assertThat(resultSet).isNotNull();
        assertThat(resultSet.next()).isTrue();
    }
}
