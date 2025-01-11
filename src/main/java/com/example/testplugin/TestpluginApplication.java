package com.example.testplugin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper") // 指定 Mapper 接口所在的包
public class TestpluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestpluginApplication.class, args);
	}

}
