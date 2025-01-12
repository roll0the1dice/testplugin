package com.example.testplugin.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class ServicePlugin extends PluginAdapter {
    private String targetPackage;

    public ServicePlugin() {
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
        // String interfaceName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + modelClassName;
        // System.out.println(packageName);
        // System.out.println(modelClassName);
        Interface anInterface = new Interface(packageName + "." + _modelName + "Service");
        anInterface.setVisibility(JavaVisibility.PUBLIC);

        // Add class documentation
        anInterface.addJavaDocLine("/**");
        anInterface.addJavaDocLine(" * This is a generated Service for demonstration purposes.");
        anInterface.addJavaDocLine(" */");




        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(anInterface, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
