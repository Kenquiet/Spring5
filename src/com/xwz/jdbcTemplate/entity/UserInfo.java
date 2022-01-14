package com.xwz.jdbcTemplate.entity;

public class UserInfo {
  private int id;
  private String userName;
  private int status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String name) {
    this.userName = name;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "UserInfo{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", status=" + status +
        '}';
  }
}
