package test;

import com.xwz.spring5.SpringCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CycleTest {
  @Test
  public void cycleTest() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringCycleXML.xml");
    SpringCycle springCycle =  context.getBean("springCycle", SpringCycle.class);
    // 下面是一系列的 对象的使用 ....
    System.out.println(springCycle);
    // 最后注销这个类
    context.close();
  }
}
