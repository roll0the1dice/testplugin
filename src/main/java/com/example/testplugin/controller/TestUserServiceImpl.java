package com.example.testplugin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.testplugin.model.TestUser;
import com.example.testplugin.util.ValidationUtil;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

/**
 * This is a generated Service for demonstration purposes.
 */
@Service
public class TestUserServiceImpl extends TestUserBaseService implements TestUserService {
    /** This is an example repository. */
    private TestUserRepository repository;

    /** This is an example modelAssembler. */
    private TestUserModelAssembler assembler;

    public static String USER_LOGIN_STATE = "USER_LOGIN_STATE";

    public TestUserServiceImpl() {
        super();
    }

    @Autowired
    public TestUserServiceImpl(TestUserRepository repository, TestUserModelAssembler assembler) {
        super(repository,assembler);
        this.repository = repository;
        this.assembler = assembler;
    }

    //@Override
    public String registerUser(String userName, String userPassword, String checkPassword) {
        // 验证用户名和密码是否包含特殊字符
        if (ValidationUtil.hasSpecialCharacters(userName)) {
            return "用户名不能包含特殊字符";
        }
        if (ValidationUtil.hasSpecialCharacters(userPassword)) {
            return "密码不能包含特殊字符";
        }
        CustomSpecs<TestUser> customSpecs = new CustomSpecs<TestUser>();
        customSpecs._equal("username", userName);
        // 检查用户名是否已经存在
        if (!repository.findAll(customSpecs._generateSpecifications()).isEmpty()) {
            return "用户名已存在";
        }

        // 创建并保存用户
        TestUser testUser = new TestUser();
        testUser.setUsername(userName);
        testUser.setUserpassword(userPassword); // 注意：建议实际应用中对密码进行加密存储
        
        repository.save(testUser);

        return "用户注册成功";
    }

    //@Override
    public TestUser doLogin(String userName, String userPassword, HttpServletRequest httpServletRequest) {
        if(StringUtil.isNullOrEmpty(userName)) 
            return null;

        if(userName.length() < 4)
            return null;

        if(userPassword.length() < 5)
            return null;

        if (ValidationUtil.hasSpecialCharacters(userName)) 
            return null;
        
        if (ValidationUtil.hasSpecialCharacters(userPassword)) 
            return null;

        CustomSpecs<TestUser> customSpecs = new CustomSpecs<TestUser>();
        customSpecs._equal("username", userName)._equal("userpassword", userPassword);
        //customSpecs._like("userpassword", "123");

        Optional<TestUser> user = repository.findOne(customSpecs._generateSpecifications());
 
        if (user.isEmpty()) 
            return null;

        TestUser safetyUser = new TestUser();
        safetyUser.setId(user.get().getId());
        safetyUser.setUsername(user.get().getUsername());
        safetyUser.setUseraccount("");
        safetyUser.setAvatarurl(user.get().getAvatarurl());
        safetyUser.setGender(user.get().getGender());
        safetyUser.setPhone("");
        safetyUser.setEmail("");
        safetyUser.setAvatarurl("");
        safetyUser.setUserpassword("");
        safetyUser.setUserState(user.get().getUserState());
        safetyUser.setCreatetime(new Date());
        safetyUser.setUpdatetime(new Date());
        safetyUser.setIsdelete((byte)0);

        httpServletRequest.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    public List<TestUser> searchUser(@RequestParam String username) {

        if(!StringUtils.isNotBlank(username)) 
            return null;

        CustomSpecs<TestUser> customSpecs = new CustomSpecs<TestUser>();
        customSpecs._like("username", username);

        return repository.findAll(customSpecs._generateSpecifications());
    }

    public List<TestUser> searchUserState(@RequestParam Integer userState) {

        if(userState == null) 
            return new ArrayList<>();

        CustomSpecs<TestUser> customSpecs = new CustomSpecs<TestUser>();
        customSpecs._greaterThan("userState", userState);

        return repository.findAll(customSpecs._generateSpecifications());
    }

    public TestUser getCurrent(HttpServletRequest httpServletRequest) {

        Object obj = httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        
        // 检查类型并进行转换
        if (obj instanceof TestUser) {
            return (TestUser) obj; // 安全类型转换
        } else {
            return null; // 处理未找到或类型不匹配的情况
        }
    }
}