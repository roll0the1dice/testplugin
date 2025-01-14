package com.example.testplugin.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.java.TypeParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.example.testplugin.controller.CustomSpecs;

import jakarta.persistence.criteria.CriteriaBuilder;

public class CustomSpecsPlugin extends PluginAdapter {

    private String targetPackage;

    public CustomSpecsPlugin() {
        super();
    }

    @Override
    public boolean validate(List<String> warnings) {
        // 在此验证插件的配置是否合法，返回true表示插件配置有效
        String myCustomParameter = properties.getProperty("targetPackage");
        if (myCustomParameter != null) 
            this.targetPackage = myCustomParameter;
        else 
           return false;
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

        
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        // List to store additional generated files
        List<GeneratedJavaFile> additionalFiles = new ArrayList<>();

        // 设置接口类的包名
        String packageName = this.targetPackage;

        // 创建接口类（例如 UserMapper）
        String modelClassName = introspectedTable.getBaseRecordType();

        String[] strList = modelClassName.split("\\.");
        String _modelName = Arrays.asList(strList).get(strList.length - 1);
        System.out.println(packageName);
        System.out.println(_modelName);
        // String interfaceName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + modelClassName;
        TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + "CustomSpecs");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        // Add class documentation
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * This is a generated BaseService for demonstration purposes.");
        topLevelClass.addJavaDocLine(" */");


        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("jakarta.persistence.criteria.CriteriaBuilder"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.lang.Nullable"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("jakarta.persistence.criteria.Root"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("jakarta.persistence.criteria.Predicate"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.LinkedList"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.Collection"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("jakarta.persistence.criteria.CriteriaQuery"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification"));

        FullyQualifiedJavaType specificationType = new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification");
        specificationType.addTypeArgument(new FullyQualifiedJavaType("T"));
        topLevelClass.addSuperInterface(specificationType);
        topLevelClass.addTypeParameter(new TypeParameter("T"));

        topLevelClass.addAnnotation("@Data");

        InnerEnum  innerEnum = new InnerEnum("Operator");
        innerEnum.addField(new Field("EQUAL, LIKE, GREATER_THAN, LESS_THAN, IN, AND, OR", new FullyQualifiedJavaType("")));
        topLevelClass.addInnerEnum(innerEnum);

        // Add a private field
        var field = new Field ("field", new FullyQualifiedJavaType("java.lang.String"));
        field.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(field);

        // Add a private field
        var field2 = new Field ("value", new FullyQualifiedJavaType("java.lang.Object"));
        field2.setVisibility(JavaVisibility.PRIVATE);
        field2.addJavaDocLine("/** This is an example. */");
        //field2.addAnnotation("@Autowired");
        topLevelClass.addField(field2);

        // Add a private field
        var field3 = new Field ("operator", new FullyQualifiedJavaType("Operator"));
        field3.setVisibility(JavaVisibility.PRIVATE);
        field3.addJavaDocLine("/** This is an example. */");
        topLevelClass.addField(field3);

        // // Add a private field
        // var field4 = new Field ("AND", new FullyQualifiedJavaType("java.lang.Integer"));
        // field4.setVisibility(JavaVisibility.PRIVATE);
        // field4.setFinal(true);
        // field4.addJavaDocLine("/** This is an example. */");
        // topLevelClass.addField(field4);

        // // Add a private field
        // var field5 = new Field ("OR", new FullyQualifiedJavaType("java.lang.Integer"));
        // field5.setVisibility(JavaVisibility.PRIVATE);
        // field5.setFinal(true);
        // field5.addJavaDocLine("/** This is an example. */");
        // topLevelClass.addField(field5);

        // Add a private field
        FullyQualifiedJavaType _listSpecification = new FullyQualifiedJavaType("java.lang.LinkedList");
        FullyQualifiedJavaType _specification =  new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification");
        _specification.addTypeArgument(new FullyQualifiedJavaType("T"));
        _listSpecification.addTypeArgument(_specification);
        var field6 = new Field ("specs", _listSpecification);
        field6.setVisibility(JavaVisibility.PRIVATE);
        field6.addJavaDocLine("/** This is an example. */");
        topLevelClass.addField(field6);

        // Add a private field
        FullyQualifiedJavaType _listInteger = new FullyQualifiedJavaType("java.lang.LinkedList");
        _listInteger.addTypeArgument(new FullyQualifiedJavaType("Operator"));
        var field7 = new Field ("boolOps", _listInteger);
        field7.setVisibility(JavaVisibility.PRIVATE);
        field7.addJavaDocLine("/** This is an example. */");
        topLevelClass.addField(field7);

        Method _defaultconstructor = new Method("CustomSpecs");
        _defaultconstructor.setConstructor(true);
        _defaultconstructor.setVisibility(JavaVisibility.PUBLIC);
        // _defaultconstructor.addBodyLine("AND = 0;");
        // _defaultconstructor.addBodyLine("OR = 1;");
        _defaultconstructor.addBodyLine("this.specs = new LinkedList<Specification<T>>();");
        _defaultconstructor.addBodyLine("this.boolOps = new LinkedList<Operator>();");
        topLevelClass.addMethod(_defaultconstructor);

        Method _toJpaPredicate = new Method("toPredicate");
        _toJpaPredicate.addAnnotation("@Nullable");
        _toJpaPredicate.addAnnotation("@Override");
        _toJpaPredicate.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType _rootType = new FullyQualifiedJavaType("jakarta.persistence.criteria.Root");
        _rootType.addTypeArgument(new FullyQualifiedJavaType("T"));
        Parameter param1  = new Parameter(_rootType, "root");
        _toJpaPredicate.addParameter(param1);
        FullyQualifiedJavaType _criteriaQuery = new FullyQualifiedJavaType("jakarta.persistence.criteria.CriteriaQuery");
        _criteriaQuery.addTypeArgument(new FullyQualifiedJavaType("?"));
        Parameter param2  = new Parameter(_criteriaQuery, "query");
        //param2.addAnnotation("@Nullable");
        _toJpaPredicate.addParameter(param2);
        FullyQualifiedJavaType _criteriaBuilder = new FullyQualifiedJavaType("jakarta.persistence.criteria.CriteriaBuilder");
        Parameter param3  = new Parameter(_criteriaBuilder, "cb");
        _toJpaPredicate.addParameter(param3);
        _toJpaPredicate.setReturnType(new FullyQualifiedJavaType("jakarta.persistence.criteria.Predicate"));
        String[] strParameter3 = {"switch (getOperator()) {",
            "case EQUAL:",
                "return cb.equal(root.get(getField()), getValue());",
            "case LIKE:",
                "return cb.like(root.get(getField()), \"%\" + getValue() + \"%\");",
            "case GREATER_THAN:",
                "return cb.greaterThan(root.get(getField()), (Comparable) getValue());",
            "case LESS_THAN:",
                "return cb.lessThan(root.get(getField()), (Comparable) getValue());",
            "case IN:",
                "CriteriaBuilder.In<Object> in = cb.in(root.get(getField()));",
                "if (getValue() instanceof Collection) {",
                    "for (Object value : (Collection<?>) getValue()) {",
                        "in.value(value);",
                    "}",
                "}",
                "return in;",
            "default:",
                "throw new UnsupportedOperationException(\"Unsupported operator: \" + getOperator());",
            "}"
        };
        List<String> stringList3 = Arrays.asList(strParameter3);
        Collection<String> stringCollection3 = stringList3;
        _toJpaPredicate.addBodyLines(stringCollection3);
        topLevelClass.addMethod(_toJpaPredicate);       
        
        
        Method _and = new Method("_and");
        _and.setVisibility(JavaVisibility.PUBLIC);
        String[] strParameter4 = {"if (boolOps.size() < specs.size())",
                                    "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList4 = Arrays.asList(strParameter4);
        Collection<String> stringCollection4 = stringList4;
        _and.addBodyLines(stringCollection4);
        FullyQualifiedJavaType _retEqual = new FullyQualifiedJavaType("CustomSpecs");
        _retEqual.addTypeArgument(new FullyQualifiedJavaType("T"));
        _and.setReturnType(_retEqual);
        topLevelClass.addMethod(_and);

        Method _or = new Method("_or");
        _or.setVisibility(JavaVisibility.PUBLIC);
        String[] strParameter5 = {"if (boolOps.size() < specs.size())",
                                    "boolOps.add(Operator.OR);",
                                    "return this;"};
        List<String> stringList5 = Arrays.asList(strParameter5);
        Collection<String> stringCollection5 = stringList5;
        _or.addBodyLines(stringCollection5);
        _or.setReturnType(_retEqual);
        topLevelClass.addMethod(_or);


        Method _equal = new Method("_equal");
        _equal.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType _filedKey = new FullyQualifiedJavaType("java.lang.String");
        //_filedKey.addTypeArgument(new FullyQualifiedJavaType("?"));
        Parameter param_fieldKey  = new Parameter(_filedKey, "fieldKey");
        FullyQualifiedJavaType _filedValue = new FullyQualifiedJavaType("java.lang.Object");
        //_filedKey.addTypeArgument(new FullyQualifiedJavaType("?"));
        Parameter param_filedValue  = new Parameter(_filedKey, "fieldValue");
        _equal.addParameter(param_fieldKey);
        _equal.addParameter(param_filedValue);
        String[] strParameter6 = {  "setField(fieldKey);",
                                    "setValue(fieldValue);",
                                    "setOperator(Operator.EQUAL);",
                                    "specs.add((root, query, builder) -> {",
                                        "return  toPredicate(root, query, builder);",
                                    "});",
                                    "if (boolOps.size() < specs.size())",
                                        "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList6 = Arrays.asList(strParameter6);
        Collection<String> stringCollection6 = stringList6;
        _equal.addBodyLines(stringCollection6);
        _equal.setReturnType(_retEqual);
        topLevelClass.addMethod(_equal);

        Method _like = new Method("_like");
        _like.setVisibility(JavaVisibility.PUBLIC);
        _like.addParameter(param_fieldKey);
        _like.addParameter(param_filedValue);
        String[] strParameter_like = {  "setField(fieldKey);",
                                    "setValue(fieldValue);",
                                    "setOperator(Operator.LIKE);",
                                    "specs.add((root, query, builder) -> {",
                                        "return  toPredicate(root, query, builder);",
                                    "});",
                                    "if (boolOps.size() < specs.size())",
                                        "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList_like = Arrays.asList(strParameter_like);
        Collection<String> stringCollection_like = stringList_like;
        _like.addBodyLines(stringCollection_like);
        _like.setReturnType(_retEqual);
        topLevelClass.addMethod(_like);

        Method _greaterThan = new Method("_greaterThan");
        _greaterThan.setVisibility(JavaVisibility.PUBLIC);
        _greaterThan.addParameter(param_fieldKey);
        _greaterThan.addParameter(param_filedValue);
        String[] strParameter_greaterThan = {  "setField(fieldKey);",
                                    "setValue(fieldValue);",
                                    "setOperator(Operator.GREATER_THAN);",
                                    "specs.add((root, query, builder) -> {",
                                        "return  toPredicate(root, query, builder);",
                                    "});",
                                    "if (boolOps.size() < specs.size())",
                                        "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList_greaterThan = Arrays.asList(strParameter_greaterThan);
        Collection<String> stringCollection_greaterThan = stringList_greaterThan;
        _greaterThan.addBodyLines(stringCollection_greaterThan);
        _greaterThan.setReturnType(_retEqual);
        topLevelClass.addMethod(_greaterThan);

        Method _lessThan = new Method("_lessThan");
        _lessThan.setVisibility(JavaVisibility.PUBLIC);
        _lessThan.addParameter(param_fieldKey);
        _lessThan.addParameter(param_filedValue);
        String[] strParameter_less = {  "setField(fieldKey);",
                                    "setValue(fieldValue);",
                                    "setOperator(Operator.LESS_THAN);",
                                    "specs.add((root, query, builder) -> {",
                                        "return  toPredicate(root, query, builder);",
                                    "});",
                                    "if (boolOps.size() < specs.size())",
                                        "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList_less = Arrays.asList(strParameter_less);
        Collection<String> stringCollection_less = stringList_less;
        _lessThan.addBodyLines(stringCollection_less);
        _lessThan.setReturnType(_retEqual);
        topLevelClass.addMethod(_lessThan);

        Method _in = new Method("_in");
        _in.setVisibility(JavaVisibility.PUBLIC);
        _in.addParameter(param_fieldKey);
        _in.addParameter(param_filedValue);
        String[] strParameter_in = {  "setField(fieldKey);",
                                    "setValue(fieldValue);",
                                    "setOperator(Operator.IN);",
                                    "specs.add((root, query, builder) -> {",
                                        "return  toPredicate(root, query, builder);",
                                    "});",
                                    "if (boolOps.size() < specs.size())",
                                        "boolOps.add(Operator.AND);",
                                    "return this;"};
        List<String> stringList_in = Arrays.asList(strParameter_in);
        Collection<String> stringCollection_in = stringList_in;
        _in.addBodyLines(stringCollection_in);
        _in.setReturnType(_retEqual);
        topLevelClass.addMethod(_in);

        Method _executeQuery= new Method("_generateSpecifications");
        _executeQuery.setVisibility(JavaVisibility.PUBLIC);
        String[] strParameter7 = { 
            "Specification<T> combinedSpec = Specification.where(null);",
            "assert(specs.size() == boolOps.size());",
            "for (int i = 0; i < specs.size() && i < boolOps.size(); i++) {",
              "var spec = specs.remove(0);",
              "var op = boolOps.remove(0);",
              "switch (op) {",
                "case OR:",
                  "combinedSpec = combinedSpec.or(spec);",
                  "break;",
                "case AND:",
                "default:",
                  "combinedSpec = combinedSpec.and(spec);",
                  "break;",
              "}",
            "}",
            "return combinedSpec;"};
        List<String> stringList7 = Arrays.asList(strParameter7);
        Collection<String> stringCollection7 = stringList7;
        _executeQuery.addBodyLines(stringCollection7);
        FullyQualifiedJavaType _retexecuteQuery = new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification");
        _retexecuteQuery.addTypeArgument(new FullyQualifiedJavaType("T"));
        _and.setReturnType(_retexecuteQuery);
        _executeQuery.setReturnType(_retexecuteQuery);
        topLevelClass.addMethod(_executeQuery);


        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
