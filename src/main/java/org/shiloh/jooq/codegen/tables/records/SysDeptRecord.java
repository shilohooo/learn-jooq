/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.shiloh.jooq.codegen.tables.TSysDept;


/**
 * 系统部门信息表
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysDeptRecord extends UpdatableRecordImpl<SysDeptRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = -698838041;

    /**
     * Setter for <code>learn_jooq.sys_dept.id</code>. ID，自增主键
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_dept.id</code>. ID，自增主键
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>learn_jooq.sys_dept.name</code>. 部门名称
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_dept.name</code>. 部门名称
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>learn_jooq.sys_dept.tel</code>. 电话号码
     */
    public void setTel(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>learn_jooq.sys_dept.tel</code>. 电话号码
     */
    public String getTel() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TSysDept.SYS_DEPT.ID;
    }

    @Override
    public Field<String> field2() {
        return TSysDept.SYS_DEPT.NAME;
    }

    @Override
    public Field<String> field3() {
        return TSysDept.SYS_DEPT.TEL;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getTel();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getTel();
    }

    @Override
    public SysDeptRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SysDeptRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public SysDeptRecord value3(String value) {
        setTel(value);
        return this;
    }

    @Override
    public SysDeptRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysDeptRecord
     */
    public SysDeptRecord() {
        super(TSysDept.SYS_DEPT);
    }

    /**
     * Create a detached, initialised SysDeptRecord
     */
    public SysDeptRecord(Long id, String name, String tel) {
        super(TSysDept.SYS_DEPT);

        set(0, id);
        set(1, name);
        set(2, tel);
    }
}
