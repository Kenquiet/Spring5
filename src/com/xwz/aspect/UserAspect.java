package com.xwz.aspect;

import org.springframework.stereotype.Service;

@Service(value = "userAspect")
public class UserAspect {
  public void add() {
    System.out.println("add .....");
  }
}
