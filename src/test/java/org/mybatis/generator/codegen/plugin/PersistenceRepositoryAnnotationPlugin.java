package org.mybatis.generator.codegen.plugin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @author radioend
 * @version v1.0
 * @{#} PersistenceRepositoryAnnotationPlugin.java Create on 2016年2月10日 下午10:22:32
 * <p>
 *
 * </p>
 */
@Slf4j
public class PersistenceRepositoryAnnotationPlugin extends PluginAdapter {

    public PersistenceRepositoryAnnotationPlugin() {
        log.debug("initialized");
    }

    /**
     * 取消model的get方法
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;//return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 取消model的set方法
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;//return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }


    /**
     * 生成mapper
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        /*
         * Spring的@Repository注解
         */
        interfaze.addAnnotation("@MapperScan");
        interfaze.addAnnotation("@Repository");
        /*
         * Spring的@Repository注解的包路径
         */
        interfaze.addImportedType(new FullyQualifiedJavaType("org.mybatis.spring.annotation.MapperScan"));
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    /**
     * 取消验证
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}