package test;

import com.xwz.jdbcTemplate.entity.UserInfo;
import com.xwz.jdbcTemplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class JdbcTemplateTest {
  @Test
  public void test() {
    ApplicationContext context =
        new ClassPathXmlApplicationContext("com/xwz/jdbcTemplate/jdbc.xml");
    UserService userService = context.getBean("userService", UserService.class);
    // 这里的数据本来更应该是拿到接口传过来的信息，这里模拟一下
//    UserInfo info = new UserInfo();
//    info.setUserName("李四");
//    info.setStatus(1);
//    userService.add(info);
//
//    info.setId(2);
//    info.setUserName("李44");
//    info.setStatus(1);
//    userService.update(info);
//
////    userService.delete(3);
//
//    userService.findCount();
//    userService.findInfo(6);
//    userService.findAll(1,20);
    List<Object[]> list = new ArrayList<>();
    Object[] o1 = { "测试1", 1 };
    Object[] o2 = { "测试2", 1 };
    list.add(o1);
    list.add(o2);
    userService.batchList(list);
  }
}
