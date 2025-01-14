package com.example.testplugin.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestUserRegisterRequest implements Serializable {
  private static final long serialVerisonUID = 3191241716373120793L;

  private String userName;

  private String userPassword;

  private String checkPassword;
}
