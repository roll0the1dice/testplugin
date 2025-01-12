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

import org.springframework.http.ResponseEntity;

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

        //topLevelClass.addStaticImport("org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn");
        //topLevelClass.addStaticImport("org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo");

        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.DeleteMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PostMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PutMapping"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(modelClassName));
        topLevelClass.addImportedType(new FullyQualifiedJavaType(packageName + "." + _modelName + "NotFoundException"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.hateoas.EntityModel"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.hateoas.CollectionModel"));
        //topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        //topLevelClass.addImportedType(new FullyQualifiedJavaType("java.util.stream.Collectors"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.http.ResponseEntity"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));

        topLevelClass.addAnnotation("@RestController");
        topLevelClass.addAnnotation("@RequestMapping(\"/" + _modelName.toLowerCase() + "\")");

        // Add a private field
        var field = new Field ("service", new FullyQualifiedJavaType(_modelName + "ServiceImpl"));
        field.setVisibility(JavaVisibility.PRIVATE);
        field.addJavaDocLine("/** This is an example service. */");
        //field.addAnnotation("@Autowired");
        topLevelClass.addField(field);

        // Add a private field
        // var field2 = new Field ("assembler", new FullyQualifiedJavaType(_modelName + "ModelAssembler"));
        // field2.setVisibility(JavaVisibility.PRIVATE);
        // field2.addJavaDocLine("/** This is an example modelAssembler. */");
        // //field2.addAnnotation("@Autowired");
        // topLevelClass.addField(field2);

        Method _constructor = new Method(_modelName + "Controller");
        _constructor.setConstructor(true);
        _constructor.setVisibility(JavaVisibility.PUBLIC);
        _constructor.addAnnotation("@Autowired");
        _constructor.addParameter(new Parameter(new FullyQualifiedJavaType(_modelName + "ServiceImpl"), "service"));
        _constructor.addBodyLine("this.service = service;");
        topLevelClass.addMethod(_constructor);

        Method _getAll = new Method("all");
        _getAll.addAnnotation(String.format("@GetMapping(\"/%s\")", _getAll.getName()));
        _getAll.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType _retTypeForOne = new FullyQualifiedJavaType("org.springframework.hateoas.CollectionModel");
        FullyQualifiedJavaType _entityType = new FullyQualifiedJavaType("org.springframework.hateoas.EntityModel");
        _entityType.addTypeArgument(new FullyQualifiedJavaType(modelClassName));
        _retTypeForOne.addTypeArgument(_entityType);
        _getAll.setReturnType(_retTypeForOne);
        _getAll.addBodyLine(String.format("return service.%s();", _getAll.getName()));
        topLevelClass.addMethod(_getAll);
        
        Method _saveNew = new Method("create");
        _saveNew.addAnnotation(String.format("@PostMapping(\"/%s\")", _saveNew.getName()));
        _saveNew.setVisibility(JavaVisibility.PUBLIC);
        //String newModelName = "new" + _modelName;
        Parameter parameter  = new Parameter(new FullyQualifiedJavaType(modelClassName), "new" + _modelName);
        parameter.addAnnotation("@RequestBody");
        _saveNew.addParameter(parameter);
        _saveNew.setReturnType(new FullyQualifiedJavaType(modelClassName));
        _saveNew.addBodyLine(String.format("return service.%s(%s);", _saveNew.getName(), _saveNew.getParameters().get(0).getName()));
        topLevelClass.addMethod(_saveNew);

        Method _getOne = new Method("one");
        _getOne.addAnnotation(String.format("@GetMapping(\"/%s/{id}\")", _getOne.getName()));
        _getOne.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _getOne.addParameter(parameter);
        _retTypeForOne = new FullyQualifiedJavaType("org.springframework.hateoas.EntityModel");
        _retTypeForOne.addTypeArgument(new FullyQualifiedJavaType(modelClassName));
        _getOne.setReturnType(_retTypeForOne);
        _getOne.addBodyLine(String.format("return service.%s(%s);", _getOne.getName(), _getOne.getParameters().get(0).getName()));
        topLevelClass.addMethod(_getOne);        

        Method _replace = new Method("replace" + _modelName);
        _replace.addAnnotation(String.format("@PutMapping(\"/%s/{id}\")", _replace.getName()));
        _replace.setVisibility(JavaVisibility.PUBLIC);
        parameter  = new Parameter(new FullyQualifiedJavaType(modelClassName), "new" + _modelName);
        parameter.addAnnotation("@RequestBody");
        _replace.addParameter(parameter);
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _replace.addParameter(parameter);
        _replace.setReturnType(new FullyQualifiedJavaType("ResponseEntity<?> "));
        _replace.addBodyLine(String.format("return service.%s(%s,%s);", _replace.getName(), _replace.getParameters().get(0).getName(), _replace.getParameters().get(1).getName()));
        topLevelClass.addMethod(_replace);              

        Method _delete = new Method("delete" + _modelName);
        _delete.addAnnotation(String.format("@DeleteMapping(\"/%s/{id}\")", _delete.getName()));
        _delete.setVisibility(JavaVisibility.PUBLIC);
        //String newModelName = "new" + _modelName;
        parameter  = new Parameter(new FullyQualifiedJavaType("java.lang.Long"), "id");
        parameter.addAnnotation("@PathVariable");
        _delete.addParameter(parameter);
        _delete.addBodyLine(String.format("return service.%s(%s);", _delete.getName(), _delete.getParameters().get(0).getName()));
        _delete.setReturnType(new FullyQualifiedJavaType("ResponseEntity<?>"));
        topLevelClass.addMethod(_delete);

        // Use DefaultJavaFormatter to format the generated Java file
        DefaultJavaFormatter javaFormatter = new DefaultJavaFormatter();
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(topLevelClass, "src/main/java", javaFormatter);

        // Add the generated file to the list
        additionalFiles.add(generatedJavaFile);

        return additionalFiles;
    }
}
