package com.example.testplugin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.testplugin.model.TestUser;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This is a generated Service for demonstration purposes.
 */
public interface TestUserService {
  public String registerUser(String userName, String userPassword, String checkPassword);

  public TestUser doLogin(String userName, String userPassword, HttpServletRequest httpServletRequest);

  public List<TestUser> searchUser(@RequestParam String username);
}