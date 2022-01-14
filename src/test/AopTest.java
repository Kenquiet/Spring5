package test;
import com.xwz.aop.SpringConfig;
import com.xwz.aop.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
  // 这是有xml配置的
    @Test
    public void testAop() {
      ClassPathXmlApplicationContext context =
          new ClassPathXmlApplicationContext("SpringAop.xml");
      UserService userService = context.getBean("userService", UserService.class);
      userService.add();
    }

  // 这是没有xml配置文件的
  @Test
  public void testAop01() {
    ApplicationContext context =
        new AnnotationConfigApplicationContext(SpringConfig.class);
    UserService userService = context.getBean("userService", UserService.class);
    userService.add();
  }
}
