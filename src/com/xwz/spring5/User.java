package com.xwz.spring5;

public class User {
  public String name;
  public String no;
  public String school;
  public String classNo;
  public String address;
  public String books;

  public User() {}
  public User(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setBooks(String books) {
    this.books = books;
  }
}
