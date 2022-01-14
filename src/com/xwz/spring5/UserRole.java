package com.xwz.spring5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRole {
  private Role role;

  public void setRole(Role role) {
    this.role = role;
  }
  public void add() {
    System.out.println("UserRole update ....");
    Role.update();
  }
  public static void main(String[] args) throws Exception{
    // 加载对象
    ApplicationContext context =
        new ClassPathXmlApplicationContext("UserRefRole.xml");
    // 获取配置对象
    UserRole userRole = context.getBean("userRole", UserRole.class);
    userRole.add();
  }
}
