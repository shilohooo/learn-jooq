/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.shiloh.jooq.codegen.tables.TSysUser;
import org.shiloh.jooq.codegen.tables.interfaces.ISysUser;


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
public class SysUserRecord extends UpdatableRecordImpl<SysUserRecord> implements Record7<Long, String, String, Integer, String, String, Long>, ISysUser {

    private static final long serialVersionUID = 40197978;

    /**
     * Setter for <code>learn_jooq.sys_user.id</code>. ID，自增主键
     */
    @Override
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.id</code>. ID，自增主键
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.username</code>. 用户名
     */
    @Override
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.username</code>. 用户名
     */
    @Override
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.password</code>. 密码
     */
    @Override
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.password</code>. 密码
     */
    @Override
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.age</code>. 年龄
     */
    @Override
    public void setAge(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.age</code>. 年龄
     */
    @Override
    public Integer getAge() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.sex</code>. 性别
     */
    @Override
    public void setSex(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.sex</code>. 性别
     */
    @Override
    public String getSex() {
        return (String) get(4);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.email</code>. 电子邮箱
     */
    @Override
    public void setEmail(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.email</code>. 电子邮箱
     */
    @Override
    public String getEmail() {
        return (String) get(5);
    }

    /**
     * Setter for <code>learn_jooq.sys_user.dept_id</code>. 所在部门 ID
     */
    @Override
    public void setDeptId(Long value) {
        set(6, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_user.dept_id</code>. 所在部门 ID
     */
    @Override
    public Long getDeptId() {
        return (Long) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, Integer, String, String, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, String, String, Integer, String, String, Long> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TSysUser.SYS_USER.ID;
    }

    @Override
    public Field<String> field2() {
        return TSysUser.SYS_USER.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return TSysUser.SYS_USER.PASSWORD;
    }

    @Override
    public Field<Integer> field4() {
        return TSysUser.SYS_USER.AGE;
    }

    @Override
    public Field<String> field5() {
        return TSysUser.SYS_USER.SEX;
    }

    @Override
    public Field<String> field6() {
        return TSysUser.SYS_USER.EMAIL;
    }

    @Override
    public Field<Long> field7() {
        return TSysUser.SYS_USER.DEPT_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getPassword();
    }

    @Override
    public Integer component4() {
        return getAge();
    }

    @Override
    public String component5() {
        return getSex();
    }

    @Override
    public String component6() {
        return getEmail();
    }

    @Override
    public Long component7() {
        return getDeptId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getPassword();
    }

    @Override
    public Integer value4() {
        return getAge();
    }

    @Override
    public String value5() {
        return getSex();
    }

    @Override
    public String value6() {
        return getEmail();
    }

    @Override
    public Long value7() {
        return getDeptId();
    }

    @Override
    public SysUserRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SysUserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public SysUserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public SysUserRecord value4(Integer value) {
        setAge(value);
        return this;
    }

    @Override
    public SysUserRecord value5(String value) {
        setSex(value);
        return this;
    }

    @Override
    public SysUserRecord value6(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public SysUserRecord value7(Long value) {
        setDeptId(value);
        return this;
    }

    @Override
    public SysUserRecord values(Long value1, String value2, String value3, Integer value4, String value5, String value6, Long value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISysUser from) {
        setId(from.getId());
        setUsername(from.getUsername());
        setPassword(from.getPassword());
        setAge(from.getAge());
        setSex(from.getSex());
        setEmail(from.getEmail());
        setDeptId(from.getDeptId());
    }

    @Override
    public <E extends ISysUser> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysUserRecord
     */
    public SysUserRecord() {
        super(TSysUser.SYS_USER);
    }

    /**
     * Create a detached, initialised SysUserRecord
     */
    public SysUserRecord(Long id, String username, String password, Integer age, String sex, String email, Long deptId) {
        super(TSysUser.SYS_USER);

        set(0, id);
        set(1, username);
        set(2, password);
        set(3, age);
        set(4, sex);
        set(5, email);
        set(6, deptId);
    }
}