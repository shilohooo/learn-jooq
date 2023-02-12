/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.shiloh.jooq.codegen.Indexes;
import org.shiloh.jooq.codegen.Keys;
import org.shiloh.jooq.codegen.LearnJooq;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;


/**
 * 系统用户信息表
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysUser extends TableImpl<SysUserRecord> {

    private static final long serialVersionUID = 1219771457;

    /**
     * The reference instance of <code>learn_jooq.sys_user</code>
     */
    public static final SysUser SYS_USER = new SysUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysUserRecord> getRecordType() {
        return SysUserRecord.class;
    }

    /**
     * The column <code>learn_jooq.sys_user.id</code>. ID，自增主键
     */
    public final TableField<SysUserRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "ID，自增主键");

    /**
     * The column <code>learn_jooq.sys_user.username</code>. 用户名
     */
    public final TableField<SysUserRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "用户名");

    /**
     * The column <code>learn_jooq.sys_user.password</code>. 密码
     */
    public final TableField<SysUserRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "密码");

    /**
     * The column <code>learn_jooq.sys_user.age</code>. 年龄
     */
    public final TableField<SysUserRecord, Integer> AGE = createField(DSL.name("age"), org.jooq.impl.SQLDataType.INTEGER, this, "年龄");

    /**
     * The column <code>learn_jooq.sys_user.sex</code>. 性别
     */
    public final TableField<SysUserRecord, String> SEX = createField(DSL.name("sex"), org.jooq.impl.SQLDataType.VARCHAR(10), this, "性别");

    /**
     * The column <code>learn_jooq.sys_user.email</code>. 电子邮箱
     */
    public final TableField<SysUserRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "电子邮箱");

    /**
     * The column <code>learn_jooq.sys_user.dept_id</code>. 所在部门 ID
     */
    public final TableField<SysUserRecord, Long> DEPT_ID = createField(DSL.name("dept_id"), org.jooq.impl.SQLDataType.BIGINT, this, "所在部门 ID");

    /**
     * Create a <code>learn_jooq.sys_user</code> table reference
     */
    public SysUser() {
        this(DSL.name("sys_user"), null);
    }

    /**
     * Create an aliased <code>learn_jooq.sys_user</code> table reference
     */
    public SysUser(String alias) {
        this(DSL.name(alias), SYS_USER);
    }

    /**
     * Create an aliased <code>learn_jooq.sys_user</code> table reference
     */
    public SysUser(Name alias) {
        this(alias, SYS_USER);
    }

    private SysUser(Name alias, Table<SysUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private SysUser(Name alias, Table<SysUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("系统用户信息表"));
    }

    public <O extends Record> SysUser(Table<O> child, ForeignKey<O, SysUserRecord> key) {
        super(child, key, SYS_USER);
    }

    @Override
    public Schema getSchema() {
        return LearnJooq.LEARN_JOOQ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SYS_USER_PRIMARY);
    }

    @Override
    public Identity<SysUserRecord, Long> getIdentity() {
        return Keys.IDENTITY_SYS_USER;
    }

    @Override
    public UniqueKey<SysUserRecord> getPrimaryKey() {
        return Keys.KEY_SYS_USER_PRIMARY;
    }

    @Override
    public List<UniqueKey<SysUserRecord>> getKeys() {
        return Arrays.<UniqueKey<SysUserRecord>>asList(Keys.KEY_SYS_USER_PRIMARY);
    }

    @Override
    public SysUser as(String alias) {
        return new SysUser(DSL.name(alias), this);
    }

    @Override
    public SysUser as(Name alias) {
        return new SysUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SysUser rename(String name) {
        return new SysUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SysUser rename(Name name) {
        return new SysUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, Integer, String, String, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
