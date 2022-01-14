package com.xwz.jdbcTemplate.dao;

import com.xwz.jdbcTemplate.entity.UserInfo;

import java.util.List;

public interface UserDao {
  public void add(UserInfo userInfo);
  public void update(UserInfo userInfo);
  public void delete(int id);
  public int findCount();
  public UserInfo findInfo(int id);
  public List<UserInfo> findAll(int page, int pageSize);

  public void batchList(List<Object[]> batchArgs);
  public void batchUpdateUser(List<Object[]> batchArgs);
}
