package org.mybatis.generator.codegen.mybatis3.model; /**
 * Copyright 2006-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Context;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.JavaBeansUtil.*;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * @author Jeff Butler
 */
public class BaseRecordGenerator extends AbstractJavaGenerator {

    public BaseRecordGenerator() {
        super();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.addAll(getEntryModel());
        answer.addAll(getEntryModelBuilder());
        return answer;
    }

    /**
     *
     */
    public List<CompilationUnit> getEntryModel() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
        CommentGenerator commentGenerator = context.getCommentGenerator();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);


        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Accessors(chain = true)");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.experimental.Accessors"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("tk.mybatis.mapper.annotation.KeySql"));

        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();

        String rootClass = getRootClass();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn)) {
                continue;
            }
            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            if (field.getAnnotations().contains("@Id")) {
                field.addAnnotation("@KeySql(useGeneratedKeys = true)");
            }

            topLevelClass.addField(field);
            topLevelClass.addImportedType(field.getType());
        }

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().modelBaseRecordClassGenerated(
                topLevelClass, introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }

    /**
     *
     */
    public List<CompilationUnit> getEntryModelBuilder() {
        //com.micro.domain.entry
        String entryTarget = this.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        String builderTarget = entryTarget.substring(0, entryTarget.lastIndexOf('.')) + ".builder";
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Plugin plugins = context.getPlugins();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(builderTarget + "." + table.getDomainObjectName() + "Builder");
        TopLevelClass topLevelClass = new TopLevelClass(type);
        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(entryTarget + "." + table.getDomainObjectName());
//        topLevelClass.setSuperClass(superClass);
        topLevelClass.addImportedType(superClass);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        commentGenerator.addJavaFileComment(topLevelClass);

        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();
        Field field = new Field("self", superClass);
        field.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(field);

        Method privateStructure = getJavaBeansBuilderStructurePrivate(type, superClass);
        topLevelClass.addMethod(privateStructure);
        Method structure = getJavaBeansBuilderStructure(type, superClass);
        topLevelClass.addMethod(structure);
        Method create = getJavaBeansCreate(introspectedColumns, context, introspectedTable, superClass);
        topLevelClass.addMethod(create);
        Method update = getJavaBeansUpdate(introspectedColumns, context, introspectedTable, superClass);
        topLevelClass.addMethod(update);

        String rootClass = getRootClass();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (RootClassInfo.getInstance(rootClass, warnings)
                    .containsProperty(introspectedColumn)) {
                continue;
            }
            Method method = getJavaBeansGetter(introspectedColumn, context, introspectedTable);
            if (plugins.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addMethod(method);
            }
        }
        List<CompilationUnit> answer = new ArrayList<>();
        topLevelClass.addImportedType("javax.persistence.*");
        answer.add(topLevelClass);
        return answer;
    }

    public static Method getJavaBeansBuilderStructurePrivate(FullyQualifiedJavaType type, FullyQualifiedJavaType supperType) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PRIVATE);
        method.setConstructor(true);
        method.setReturnType(type);
        method.setName(type.getShortName());
        method.addBodyLine("return;");
        return method;
    }

    public static Method getJavaBeansBuilderStructure(FullyQualifiedJavaType type, FullyQualifiedJavaType supperType) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.addParameter(new Parameter(supperType, "self"));
        method.setReturnType(type);
        method.addBodyLine("this.self = self;");
        method.setName(type.getShortName());
        return method;
    }

    /**
     * entry create method
     */
    public static Method getJavaBeansCreate(List<IntrospectedColumn> introspectedColumns, Context context, IntrospectedTable introspectedTable, FullyQualifiedJavaType type) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("create");
        method.setStatic(true);
        method.setReturnType(type);
        method.addBodyLine(type + " self = new " + type + "();");
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 建议不在本方法上进行修改,请复制再修改");
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (introspectedColumn.isAutoIncrement()) continue;
            FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
            String property = introspectedColumn.getJavaProperty();
            String defVal = introspectedColumn.getDefaultValue() == null ? "" : " def=" + introspectedColumn.getDefaultValue();
            method.addJavaDocLine(" * @param " + property + " " + introspectedColumn.getRemarks().replaceAll("\r\n", " ") + defVal);
            method.addParameter(new Parameter(fqjt, property));
            method.addBodyLine("self." + getSetterMethodName(property) + "(" + property + ");");
        }
        method.addJavaDocLine("*/");
        method.addBodyLine("return self;");
        return method;
    }

    /**
     * entry update method
     */
    public static Method getJavaBeansUpdate(List<IntrospectedColumn> introspectedColumns, Context context, IntrospectedTable introspectedTable, FullyQualifiedJavaType type) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("update");
        method.setReturnType(type);
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 建议不在本方法上进行修改,请复制再修改");
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (introspectedColumn.isAutoIncrement()) continue;
            FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
            String property = introspectedColumn.getJavaProperty();
            String defVal = introspectedColumn.getDefaultValue() == null ? "" : " def=" + introspectedColumn.getDefaultValue();
            method.addJavaDocLine(" * @param " + property + " " + introspectedColumn.getRemarks().replaceAll("\r\n", " ") + defVal);
            method.addParameter(new Parameter(fqjt, property));
            method.addBodyLine("self." + getSetterMethodName(property) + "(" + property + ");");
        }
        method.addBodyLine("return self;");
        method.addJavaDocLine("*/");
        return method;
    }

    private boolean includePrimaryKeyColumns() {
        return !introspectedTable.getRules().generatePrimaryKeyClass()
                && introspectedTable.hasPrimaryKeyColumns();
    }

    private boolean includeBLOBColumns() {
        return !introspectedTable.getRules().generateRecordWithBLOBsClass()
                && introspectedTable.hasBLOBColumns();
    }


    private List<IntrospectedColumn> getColumnsInThisClass() {
        List<IntrospectedColumn> introspectedColumns;
        if (includePrimaryKeyColumns()) {
            if (includeBLOBColumns()) {
                introspectedColumns = introspectedTable.getAllColumns();
            } else {
                introspectedColumns = introspectedTable.getNonBLOBColumns();
            }
        } else {
            if (includeBLOBColumns()) {
                introspectedColumns = introspectedTable
                        .getNonPrimaryKeyColumns();
            } else {
                introspectedColumns = introspectedTable.getBaseColumns();
            }
        }

        return introspectedColumns;
    }
}
