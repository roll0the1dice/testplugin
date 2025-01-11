# 项目名称

**项目简介：** 简要描述项目的功能和用途。例如：这是一个基于 Spring Boot 3 开发的示例项目，集成了 JPA 和 MyBatis，演示如何使用 MyBatis Generator 结合 Lombok 简化开发。

## 使用技术栈

*   Spring Boot 3
*   Spring Data JPA
*   MyBatis
*   MyBatis Generator
*   Lombok
*   MySQL
*   JDK 17

## 环境准备

*   JDK 17 或更高版本
*   Maven 或 Gradle 构建工具
*   MySQL 数据库

## 数据库配置

在 `application.properties` 或 `application.yml` 文件中配置数据库连接信息：

**application.properties 示例：**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA 配置 (可选，如果使用JPA)
spring.jpa.hibernate.ddl-auto=update # 开发阶段使用 update，生产环境慎用
spring.jpa.show-sql=true # 打印 SQL 语句
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect # 根据 MySQL 版本选择方言
```

**application.yml 示例：**

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=Asia/Shanghai
    username: your_db_username
    password: your_db_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```

# **依赖配置**
## **pom.xml 示例 (Maven):**

```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>3.0.4</version>
		</dependency>

		<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.mybatis.generator</groupId>
			<artifactId>mybatis-generator-core</artifactId>
			<version>1.4.2</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter-test</artifactId>
			<version>3.0.4</version>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
				<dependencies>
						<dependency>
							<groupId>org.mybatis.generator</groupId>
							<artifactId>mybatis-generator-core</artifactId>
							<version>1.4.2</version>
						</dependency>
						<dependency>
							<groupId>com.mysql</groupId>
							<artifactId>mysql-connector-j</artifactId>
							<version>8.0.33</version>
						</dependency>
          </dependencies>
			</plugin>
		</plugins>
	</build>
```

# **代码生成**
## **MyBatis Generator 配置**
创建 `src/main/resources/generatorConfig.xml` 文件，配置 MyBatis Generator。 

**generatorConfig.xml 示例：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE generatorConfiguration
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!-- JDBC connection /home/linux/UserProject/demo/src/main/java/com/example/demo/generator/ServiceGeneratorPlugin.java -->
    <context id="MysqlContext" targetRuntime="MyBatis3">

        <plugin type="com.example.testplugin.plugin.CustomizedPlugin" />
        <plugin type="com.example.testplugin.plugin.RepositoryPlugin" >
          <property name="targetPackage" value="com.example.testplugin.controller"/>
        </plugin>
        <plugin type="com.example.testplugin.plugin.NotFoundExceptionPlugin">
          <property name="targetPackage" value="com.example.testplugin.controller"/>
        </plugin>
        <plugin type="com.example.testplugin.plugin.NotFoundAdvicePlugin">
          <property name="targetPackage" value="com.example.testplugin.controller"/>
        </plugin>
        <plugin type="com.example.testplugin.plugin.ControllerPlugin">
          <property name="targetPackage" value="com.example.testplugin.controller"/>
        </plugin>


        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/Test?useSSL=false&amp;serverTimezone=Asia/Shanghai"
                        userId="admin"
                        password="123456">
                        <!-- <property name="nullCatalogMeansCurrent" value="true" /> -->
        </jdbcConnection>

        <!-- Java Model -->
        <javaModelGenerator targetPackage="com.example.testplugin.model" targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
          <property name="trimStrings" value="true" />
        </javaModelGenerator>

        <!-- SQL Map -->
        <sqlMapGenerator targetPackage="com.example.testplugin.mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>

        <!-- Java Client (Mapper) -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.example.testplugin.mapper" targetProject="src/main/java">
          <property name="enableSubPackages" value="true" />
        </javaClientGenerator>

        <!-- Database Table -->
        <table schema="Test" tableName="test_user" domainObjectName="TestUser">
          <generatedKey column="id" sqlStatement="JDBC" identity="true"/>
        </table>

    </context>

</generatorConfiguration>
```

## **生成代码**

```bash
  运行 `src/main/java/com/example/testplugin/plugin`目录下的`CustomizedPlugin.java`即可
```

## **运行项目**
**安装依赖**
```
  mvn clean install -U
```

**打包项目**

```bash
mvn clean package
```

## **项目结构**

```text
项目根目录/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── mapper/
│   │   │       ├── ...
│   │   │       └── ...
│   │   ├── resources/
│   │   │   ├── application.properties/application.yml
│   │   │   ├── mapper/ (MyBatis Mapper XML 文件)
│   │   │   └── generatorConfig.xml (MyBatis Generator 配置文件)
│   │   └── ...
│   └── test/
│       └── ...
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```