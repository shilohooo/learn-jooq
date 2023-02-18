/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables.daos;


import org.jooq.Configuration;
import org.shiloh.extend.dao.impl.BaseDaoImpl;
import org.shiloh.jooq.codegen.tables.TSysUser;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.shiloh.jooq.codegen.tables.records.SysUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.util.List;


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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Repository
public class SysUserDao extends BaseDaoImpl<SysUserRecord, SysUserPojo, Long> {

    /**
     * Create a new SysUserDao without any configuration
     */
    public SysUserDao() {
        super(TSysUser.SYS_USER, SysUserPojo.class);
    }

    /**
     * Create a new SysUserDao with an attached configuration
     */
    @Autowired
    public SysUserDao(Configuration configuration) {
        super(TSysUser.SYS_USER, SysUserPojo.class, configuration);
    }

    @Override
    public Long getId(SysUserPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<SysUserPojo> fetchById(Long... values) {
        return fetch(TSysUser.SYS_USER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public SysUserPojo fetchOneById(Long value) {
        return fetchOne(TSysUser.SYS_USER.ID, value);
    }

    /**
     * Fetch records that have <code>username BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfUsername(String lowerInclusive, String upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.USERNAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<SysUserPojo> fetchByUsername(String... values) {
        return fetch(TSysUser.SYS_USER.USERNAME, values);
    }

    /**
     * Fetch records that have <code>password BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfPassword(String lowerInclusive, String upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.PASSWORD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<SysUserPojo> fetchByPassword(String... values) {
        return fetch(TSysUser.SYS_USER.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>age BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfAge(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.AGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>age IN (values)</code>
     */
    public List<SysUserPojo> fetchByAge(Integer... values) {
        return fetch(TSysUser.SYS_USER.AGE, values);
    }

    /**
     * Fetch records that have <code>sex BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfSex(String lowerInclusive, String upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.SEX, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>sex IN (values)</code>
     */
    public List<SysUserPojo> fetchBySex(String... values) {
        return fetch(TSysUser.SYS_USER.SEX, values);
    }

    /**
     * Fetch records that have <code>email BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfEmail(String lowerInclusive, String upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.EMAIL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<SysUserPojo> fetchByEmail(String... values) {
        return fetch(TSysUser.SYS_USER.EMAIL, values);
    }

    /**
     * Fetch records that have <code>dept_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SysUserPojo> fetchRangeOfDeptId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(TSysUser.SYS_USER.DEPT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>dept_id IN (values)</code>
     */
    public List<SysUserPojo> fetchByDeptId(Long... values) {
        return fetch(TSysUser.SYS_USER.DEPT_ID, values);
    }
}
