package test;

import com.xwz.transaction.TxConfig;
import com.xwz.transaction.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // 创建测试框架
@ContextConfiguration(classes = TxConfig.class) // 加载配置文件
public class JTest5 {
  @Autowired
  private AccountService accountService;
  @Test
  public void test() {
    accountService.transactionMoney();
  }
}
