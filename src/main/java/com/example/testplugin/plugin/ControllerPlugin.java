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
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class ControllerPlugin extends PluginAdapter {

    private String targetPackage;

    public ControllerPlugin() {
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
        // String interfaceName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + modelClassName;
        TopLevelClass topLevelClass = new TopLevelClass(packageName + "." + _modelName + "Controller");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        // Add class documentation
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * This is a generated interface for demonstration purposes.");
        topLevelClass.addJavaDocLine(" */");

        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PutMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + _modelName + "NotFoundException"));

        topLevelClass.addAnnotation("@RestController");

        // Add a private field
        var field = new Field ("repository", new FullyQualifiedJavaType(_modelName + "Repository"));
        field.setVisibility(JavaVisibility.PRIVATE);
        field.addJavaDocLine("/** This is an example repository. */");
        topLevelClass.addField(field);

        Method _constructor = new Method(_modelName + "Controller");
        _constructor.setConstructor(true);
        _constructor.setVisibility(JavaVisibility.PUBLIC);
        //String newModelName = "new" + _modelName;
        Parameter parameter  = new Parameter(new FullyQualifiedJavaType(_modelName + "Repository"), "repository");
        _constructor.addParameter(parameter);
        _constructor.addBodyLine("this.repository = repository;");
        topLevelClass.addMethod(_constructor);

        Method _getAll = new Method("all");
        _getAll.addAnnotation(String.format("@GetMapping(\"/all_%s\")", _modelName.toLowerCase()));
        _getAll.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType ListType = new FullyQualifiedJavaType("java.util.List");
        ListType.addTypeArgument(new FullyQualifiedJavaType(modelClassName));
        _getAll.setReturnType(ListType);
        _getAll.addBodyLine("return repository.findAll();");
        topLevelClass.addMethod(_getAll);
        
        Method _saveNew = new Method("create");
        _saveNew.addAnnotation(String.format("@PostMapping(\"/%s\")", _modelName.toLowerCase()));
        _saveNew.setVisibility(JavaVisibility.PUBLIC);
        //String newModelName = "new" + _modelName;
        parameter  = new Parameter(new FullyQualifiedJavaType(modelClassName), "new" + _modelName);
        parameter.addAnnotation("@RequestBody");
        _saveNew.addParameter(parameter);
        _saveNew.setReturnType(new FullyQualifiedJavaType(modelClassName));
        _saveNew.addBodyLine(String.format("return repository.save(%s);", "new" + _modelName));
        topLevelClass.addMethod(_saveNew);

        Method _getOne = new Method("one");
        _getOne.addAnnotation(String.format("@GetMapping(\"/%s/{id}\")", _modelName.toLowerCase()));
        _getOne.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _getOne.addParameter(parameter);
        _getOne.setReturnType(new FullyQualifiedJavaType(modelClassName));
        String[] strParameter2 = {"return repository.findById(id)", 
                                    String.format(".orElseThrow(() -> new %sNotFoundException(id));", _modelName)};
        // 将数组转换为 List<String>
        List<String> stringList = Arrays.asList(strParameter2);
        // 将 List<String> 赋值给 Collection<String>
        Collection<String> stringCollection = stringList;
        _getOne.addBodyLines(stringCollection);
        topLevelClass.addMethod(_getOne);        

        Method _replace = new Method("replace" + _modelName);
        _replace.addAnnotation(String.format("@PutMapping(\"/%s/{id}\")", _modelName.toLowerCase()));
        _replace.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(new FullyQualifiedJavaType(modelClassName), "new" + _modelName);
        parameter.addAnnotation("@RequestBody");
        _replace.addParameter(parameter);
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _replace.addParameter(parameter);
        _replace.setReturnType(new FullyQualifiedJavaType(modelClassName));
        String[] strParameter3 = {"return repository.findById(id)", 
                                    String.format(".map(%s -> {", "_new" + _modelName),
                                    String.format("return repository.save(%s);", "_new" + _modelName),
                                    "})",
                                    ".orElseGet(() -> {",
                                    String.format("return repository.save(%s);", "new" + _modelName),
                                    "});"};
        // 将数组转换为 List<String>
        List<String> stringList3 = Arrays.asList(strParameter3);
        // 将 List<String> 赋值给 Collection<String>
        Collection<String> stringCollection3 = stringList3;
        _replace.addBodyLines(stringCollection3);
        topLevelClass.addMethod(_replace);              

        Method _delete = new Method("delete" + _modelName);
        _delete.addAnnotation(String.format("@DeleteMapping(\"/%s/{id}\")", _modelName.toLowerCase()));
        _delete.setVisibility(JavaVisibility.PUBLIC);
        //String newModelName = "new" + _modelName;
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _delete.addParameter(parameter);
        _delete.addBodyLine("repository.deleteById(id);");
        topLevelClass.addMethod(_delete);

        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
