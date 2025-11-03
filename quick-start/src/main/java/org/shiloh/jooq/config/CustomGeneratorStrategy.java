package org.shiloh.jooq.config;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;

/**
 * jOOQ 自定义生成器策略
 *
 * @author shiloh
 * @date 2023/2/12 13:32
 */
public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
    /**
     * 重写 jOOQ 生成的类的命名策略
     *
     * @param definition 定义
     * @param mode       模式
     * @return Java 类名称
     * @author shiloh
     * @date 2023/2/12 14:33
     */
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        final String javaClassName = super.getJavaClassName(definition, mode);
        switch (mode) {
            case POJO:
                // 生成的所有 Pojo 类都以 "Pojo" 结尾
                return String.format("%sPojo", javaClassName);
            case DEFAULT:
                if (definition instanceof TableDefinition) {
                    // 生成的所有表对应的对象都以 "T" 开头
                    return String.format("T%s", javaClassName);
                }

                return javaClassName;
            default:
                // 其余的类按 jOOQ 原本的命名方式返回
                return javaClassName;

        }
    }
}
