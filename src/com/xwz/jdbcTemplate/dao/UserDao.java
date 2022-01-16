package com.xwz.jdbcTemplate.dao;

import com.xwz.jdbcTemplate.entity.UserInfo;

import java.util.List;

public interface UserDao {
  void add(UserInfo userInfo);
  void update(UserInfo userInfo);
  void delete(int id);
  int findCount();
  UserInfo findInfo(int id);
  List<UserInfo> findAll(int page, int pageSize);

  void batchList(List<Object[]> batchArgs);
  void batchUpdateUser(List<Object[]> batchArgs);

  void batchDelete(List<Object[]> batchArgs);
}
