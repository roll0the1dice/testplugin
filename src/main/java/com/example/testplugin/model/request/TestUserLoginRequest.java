package com.example.testplugin.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestUserLoginRequest implements Serializable {
  private static final long serialVerisonUID = 3191241716373120791L;

  private String userName;

  private String userPassword;

}
