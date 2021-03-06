package com.xwz.transaction.service;

import com.xwz.transaction.dao.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional // 开启事务
public class AccountService {
  @Autowired
  public Account account;

  public void transactionMoney() {

    account.reduceMoney(); // 从账户转出

    account.addMoney(); // 转进账户中
  }
}
