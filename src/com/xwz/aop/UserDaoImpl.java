package com.xwz.aop;

import org.springframework.stereotype.Service;

@Service(value = "userDaoImpl") // 定义名称 userDaoImpl
public class UserDaoImpl implements UserDao{

  @Override
  public void add() {
    System.out.println(" UserDao add .... ");
  }
}
