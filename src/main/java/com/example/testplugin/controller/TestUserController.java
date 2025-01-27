package com.example.testplugin.controller;

import com.example.testplugin.model.TestUser;
import com.example.testplugin.model.request.TestUserLoginRequest;
import com.example.testplugin.model.request.TestUserRegisterRequest;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a generated interface for demonstration purposes.
 */
@RestController
@RequestMapping("/testuser")
public class TestUserController {
    /** This is an example service. */
    @Resource
    private TestUserServiceImpl service;

    public TestUserController(TestUserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<TestUser>> all() {
        return service.all();
    }

    @PostMapping("/create")
    public TestUser create(@RequestBody TestUser newTestUser) {
        return service.create(newTestUser);
    }

    @GetMapping("/one/{id}")
    public EntityModel<TestUser> one(@PathVariable Long id) {
        return service.one(id);
    }

    @PutMapping("/replaceTestUser/{id}")
    public ResponseEntity<?> replaceTestUser(@RequestBody TestUser newTestUser, @PathVariable Long id) {
        return service.replaceTestUser(newTestUser,id);
    }

    @DeleteMapping("/deleteTestUser/{id}")
    public ResponseEntity<?> deleteTestUser(@PathVariable Long id) {
        return service.deleteTestUser(id);
    }

    @PostMapping("/register")
    public String userRegister(@RequestBody TestUserRegisterRequest testUserRequest) {
        //TODO: process POST request
        if (testUserRequest == null)
            return null;

        String userName = testUserRequest.getUserName();
        String userPassword = testUserRequest.getUserPassword();
        String checkPassword = testUserRequest.getCheckPassword();

        if (StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(userPassword) || StringUtil.isNullOrEmpty(checkPassword))
            return null;
        
        return service.registerUser(userName, userPassword, checkPassword);
    }
    
    @PostMapping("/login")
    public TestUser userLogin(@RequestBody TestUserLoginRequest testUserLoginRequest, HttpServletRequest request) {
        //TODO: process POST request
        if (testUserLoginRequest == null)
            return null;

        String userName = testUserLoginRequest.getUserName();
        String userPassword = testUserLoginRequest.getUserPassword();

        if (StringUtil.isNullOrEmpty(userName) || StringUtil.isNullOrEmpty(userPassword))
            return null;

        return service.doLogin(userName, userPassword, request);
    }

    @GetMapping("/search")
    public List<TestUser> searchUser(@RequestParam String username, HttpServletRequest request) {

        Object userObj = request.getSession().getAttribute(TestUserServiceImpl.USER_LOGIN_STATE);
        TestUser user = (TestUser)userObj;

        if(user.getGender() != 1)
            return new ArrayList<>();

        if(!StringUtils.isNotBlank(username)) 
            return new ArrayList<>();

        return service.searchUser(username);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent(HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(service.getCurrent(httpServletRequest));
    }

}