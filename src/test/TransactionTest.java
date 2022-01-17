package test;

import com.xwz.transaction.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {
  @Test
  public void test() {
    ApplicationContext context =
        new ClassPathXmlApplicationContext("com/xwz/transaction/transaction.xml");
    AccountService accountService = context.getBean("accountService", AccountService.class);
    accountService.transactionMoney();
  }
}
