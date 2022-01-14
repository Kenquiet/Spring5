package test;

import com.xwz.aspect.ConfigAspect;
import com.xwz.aspect.UserAspect;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectTest {
  @Test
  public void test() {
    // 部分注解 走 Aop.xml
    ApplicationContext context =
        new ClassPathXmlApplicationContext("com/xwz/aspect/Aop.xml");
    UserAspect userAspect = context.getBean("userAspect", UserAspect.class);
    userAspect.add();
  }

  @Test
  public void testClass() {
    // 全部注解，走 ConfigAspect
    ApplicationContext context =
        new AnnotationConfigApplicationContext(ConfigAspect.class);
    UserAspect userAspect = context.getBean("userAspect", UserAspect.class);
    userAspect.add();
  }

  @Test
  public void testXml() {
    // 全部xml 走 AopXmlAspect
    ApplicationContext context =
        new ClassPathXmlApplicationContext("com/xwz/aspect/AopXmlAspect.xml");
    UserAspect userAspect = context.getBean("userAspect", UserAspect.class);
    userAspect.add();
  }
}
