package com.xwz.jdbcTemplate.dao;

import com.xwz.jdbcTemplate.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class User implements UserDao{
  @Autowired
  private JdbcTemplate jdbcTemplate;

  // 添加
  @Override
  public void add(UserInfo userInfo) {
    String sql = "insert into t_user(userName, status) values (?,?)";
    int add = jdbcTemplate.update(sql, userInfo.getUserName(), userInfo.getStatus());
    System.out.println(add);
  }

  // 更新
  @Override
  public void update(UserInfo userInfo) {
    String sql = "update t_user set userName=?, status=? where id=?";
    Object[] args = { userInfo.getUserName(), userInfo.getStatus(), userInfo.getId() };
    int update = jdbcTemplate.update(sql, args);
    System.out.println(update);
  }

  // 删除
  @Override
  public void delete(int id) {
    String sql = "delete from t_user where id=?";
    int update = jdbcTemplate.update(sql, id);
    System.out.println(update);
  }

  // 查询总值
  @Override
  public int findCount() {
    String sql = "select count(*) from t_user";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    System.out.println(count);
    return count;
  }

  // 查询详情
  @Override
  public UserInfo findInfo(int id) {
    String sql = "select * from t_user where id=?";
    UserInfo userInfo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), id);
    System.out.println(userInfo.toString());
    return userInfo;
  }

  // 查询列表
  @Override
  public List<UserInfo> findAll(int page, int pageSize) {
    String sql = "select * from t_user limit ?,?";
    List<UserInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), (page - 1), pageSize);
    for (UserInfo userInfo: list) {
      System.out.println(userInfo.toString());
    }
    return list;
  }

  // 批量添加
  @Override
  public void batchList(List<Object[]> batchArgs) {
    String sql = "insert into t_user(userName, status) values(?,?)";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
  }

  @Override
  public void batchUpdateUser(List<Object[]> batchArgs) {
    String sql = "update t_user set userName=?, status=? where id = ?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
  }

}
