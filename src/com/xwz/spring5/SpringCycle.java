package com.xwz.spring5;
public class SpringCycle {
  public SpringCycle() {
    System.out.println("第一步：创建 bean 实例....");
  }
  private String name;
  public void setName(String name) {
    this.name = name;
    System.out.println("第二步：为实例设置值");
  }
  public void initMethod() {
    System.out.println("第三步：调用初始化方法");
  }
  public void destroyMethod() {
    System.out.println("第五步：调用销毁方法");
  }
}


