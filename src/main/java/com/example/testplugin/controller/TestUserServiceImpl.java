package com.example.testplugin.controller;

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

    public static final String USER_LOGIN_STATE = "userLoginState";

    public static final int DEFAULT_ROLE = 0;

    public static final int ADMIN_ROLE = 1;


    public TestUserServiceImpl() {
        super();
    }

    @Autowired
    public TestUserServiceImpl(TestUserRepository repository, TestUserModelAssembler assembler) {
        super(repository,assembler);
        this.repository = repository;
        this.assembler = assembler;
    }

    @Override
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

    @Override
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
        safetyUser.setUserstate(0);
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
}