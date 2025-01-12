package com.example.testplugin.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class SpecsPlugin extends PluginAdapter {
      private String targetPackage;

    public SpecsPlugin() {
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
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        // List to store additional generated files
        List<GeneratedJavaFile> additionalFiles = new ArrayList<>();

        // 设置接口类的包名
        String packageName = this.targetPackage;

        // 创建接口类（例如 UserMapper）
        // 创建接口类（例如 UserMapper）
        String modelClassName = introspectedTable.getBaseRecordType();

        String[] strList = modelClassName.split("\\.");
        String _modelName = Arrays.asList(strList).get(strList.length - 1);
        TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + _modelName + "Specs");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        // Add class documentation
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * This is a generated Specs for demonstration purposes.");
        topLevelClass.addJavaDocLine(" * Both keywords, namely Field and FieldKey in the method, should be manually modified.");
        topLevelClass.addJavaDocLine(" */");

        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));

        String[] opList = {
          "equal", "notEqual",
          "greaterThan", "greaterThanOrEqualTo",
          "lessThan", "lessThanOrEqualTo",
          "like"
          // "like", "ilike",
          // "and", "or",
          // "coalesce", 
        };

        for (var op : opList) {
          Method _specs = new Method(op+"ByField");
          _specs.setStatic(true);
          _specs.setVisibility(JavaVisibility.PUBLIC);
          _specs.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "fieldValue"));
          //_specs.addParameter(new Parameter(new FullyQualifiedJavaType("java.lang.String"), "value"));
          //System.out.println(.);
          String key = _specs.getParameters().get(0).getName();
          String[] strParameter3 = {"return (root, query, builder) -> {", 
                              String.format("return builder.%s(root.get(\"fieldKey\"), fieldValue);", op),
                              "};"
                              };
          // 将数组转换为 List<String>
          List<String> stringList3 = Arrays.asList(strParameter3);
          // 将 List<String> 赋值给 Collection<String>
          Collection<String> stringCollection3 = stringList3;
          _specs.addBodyLines(stringCollection3);
          FullyQualifiedJavaType _specsReturnType = new FullyQualifiedJavaType("org.springframework.data.jpa.domain.Specification");
          _specsReturnType.addTypeArgument(new FullyQualifiedJavaType(modelClassName));
          _specs.setReturnType(_specsReturnType);
          topLevelClass.addMethod(_specs);
        }


        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
