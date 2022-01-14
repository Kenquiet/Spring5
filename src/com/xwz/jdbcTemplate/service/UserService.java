package com.xwz.jdbcTemplate.service;

import com.xwz.jdbcTemplate.dao.UserDao;
import com.xwz.jdbcTemplate.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserDao userDao;

  public void add(UserInfo userInfo) {
    userDao.add(userInfo);
  }
  public void update(UserInfo userInfo){
    userDao.update(userInfo);
  }

  public void delete(int id) {
    userDao.delete(id);
  }

  public int findCount() {
    return userDao.findCount();
  }

  public UserInfo findInfo(int id) {
    return userDao.findInfo(id);
  }
  public List<UserInfo> findAll(int page, int pageSize) {
    return userDao.findAll(page, pageSize);
  }

  public void batchList(List<Object[]> batchArgs) {
    userDao.batchList(batchArgs);
  }
  public void batchUpdateUser(List<Object[]> batchArgs) {
    userDao.batchUpdateUser(batchArgs);
  }

}
