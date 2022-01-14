package com.xwz.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//value 不写默认就是 userService
@Service(value = "userService") // 这里的注解等于 <bean id="userService" class="..." />
public class UserService {

  @Autowired // 这里出现波浪线的原因是，因为这个是根据类型注入，但是一个对象中可以有很多个相同的类型
  @Qualifier(value = "userDaoImpl")
  private UserDao userDao;

  public void add() {
    System.out.println("service add .....");
    userDao.add();
  }
}
