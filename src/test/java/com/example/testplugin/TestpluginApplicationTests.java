package com.example.testplugin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.testplugin.controller.CustomPredicate;
import com.example.testplugin.controller.TestUserServiceImpl;
import com.example.testplugin.model.TestUser;

@SpringBootTest
class TestpluginApplicationTests {
	@Autowired
	private TestUserServiceImpl myService;	
	@Test
	void contextLoads() {
		TestUser newTestUser = new TestUser();
		newTestUser.setUsername("user123");
		newTestUser.setUserpassword("password");
		var resulut = myService.doLogin(newTestUser.getUsername(), newTestUser.getUserpassword(), null);
		//System.out.println(resulut);
		Assertions.assertThat(resulut).isEqualTo("用户注册成功");
	}
}
