package com.xwz.transaction.service;

import com.xwz.transaction.dao.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountService {
  @Autowired
  public Account account;
}
