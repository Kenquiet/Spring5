package com.xwz.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountImp implements Account{
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void addMoney() {
    String sql = "update t_account set money=money+? where id = ?";
    jdbcTemplate.update(sql, 1000, 1);
  }

  @Override
  public void reduceMoney() {
    String sql = "update t_account set money=money-? where id = ?";
    jdbcTemplate.update(sql, 1000, 2);
  }
}
