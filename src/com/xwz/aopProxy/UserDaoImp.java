package com.xwz.aopProxy;

public class UserDaoImp implements UserDao{

  @Override
  public int add(int a, int b) {
    System.out.println("add 方法执行了 ");
    return a + b;
  }
  public String update(String id) {
    System.out.println("add 方法执行了 ");
    return id;
  }
}
