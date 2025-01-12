package com.example.testplugin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testplugin.model.TestUser;
import com.example.testplugin.util.ValidationUtil;
import com.example.testplugin.controller.TestUserSpecs;

/**
 * This is a generated Service for demonstration purposes.
 */
@Service
public class TestUserServiceImpl extends TestUserBaseService implements TestUserService {
    /** This is an example repository. */
    private TestUserRepository repository;

    /** This is an example modelAssembler. */
    private TestUserModelAssembler assembler;

    public TestUserServiceImpl() {
        super();
    }

    @Autowired
    public TestUserServiceImpl(TestUserRepository repository, TestUserModelAssembler assembler) {
        super(repository,assembler);
        this.repository = repository;
        this.assembler = assembler;
    }

    public String registerUser(TestUser newTestUser) {
        // 验证用户名和密码是否包含特殊字符
        if (ValidationUtil.hasSpecialCharacters(newTestUser.getUsername())) {
            return "用户名不能包含特殊字符";
        }
        if (ValidationUtil.hasSpecialCharacters(newTestUser.getUserpassword())) {
            return "密码不能包含特殊字符";
        }

        // 检查用户名是否已经存在
        if (!repository.findAll(TestUserSpecs.equalByUsername(newTestUser.getUsername())).isEmpty()) {
            return "用户名已存在";
        }

        // 创建并保存用户
        TestUser testUser = new TestUser();
        testUser.setUsername(newTestUser.getUsername());
        testUser.setUserpassword(newTestUser.getUserpassword()); // 注意：建议实际应用中对密码进行加密存储
        
        repository.save(testUser);

        return "用户注册成功";
    }
}