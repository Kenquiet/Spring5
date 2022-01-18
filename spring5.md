# * spring框架概述

### 1、 Spring 是轻量级的开源 JavaSE 框架

### 2 、Spring 可以解决企业应用开发的复杂性

### 3 、Spring 有两个核心部分： IOC 和Aop

1. IOC：控制反转，把创建对象过程交给 Spring 进行管理
2. Aop：面向切面，不修改源代码进行功能增强

### 4、Spring 特点

1. 方便，解耦
2. Aop编程支持
3. 方便程序测试
4. 方便和其它框架进行整合
5. 降低Java Api开发的难度
6. 方便进行事务操作

### 5、 使用Spring 开发前的准备

1. 官网： <a src="https://spring.io">https://spring.io</a>
2. Projects --> Spring Framework --> LEARN 选取相关版本，版本号带有GA的
3. 点击左上角的 github 图标 <span style="color: red">（如果打不开，下载个UU加速器，查找学术资源，点击加速就行了，或者直接跳到 7 ）</span>
4. 找到Access to Binaries,进入下载页面
5. 找到  [https://repo.spring.io](https://repo.spring.io/). 下地址
7. 下载地址：<a src=" https://repo.spring.io/artifactory/release/org/springframework/spring" > https://repo.spring.io/artifactory/release/org/springframework/spring</a>  下拉下载最新的版本

### 6、 导入Spring jar 包

1. 必须要的 jar 包：Beans 、 Core、  Context、  Expression和一个日志包 下载地址<a src="https://commons.apache.org/proper/commons-logging/download_logging.cgi" >https://commons.apache.org/proper/commons-logging/download_logging.cgi</a>
  
2. 在工程上右键，选择 `Open Module Setting` => Dependencies 点 加号 => 选第一个 `JARs or ...` 然后选择对应 jar 包 直接点击Apply，然后点击 Ok

### 7、编写一个基本的Spring 代码
1. 在src 目录下新建一包：com.xwz.spring5
2. 新建一个类：User 在里边添加一个add 方法
   ```java
   package com.xwz.spring5;
   public class User {
      public void add() {
         System.out.println("add.......");
      }
   }
   ```
3. 在 src 下新建一个 SpringTestUser.xml 添加一行代码
   - 配置文件路径以及文件名称 id 是名称，class 是路径
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
      <!-- 配置文件路径以及文件名称 id 是名称，class 是路径-->
      <bean id="user" class="com.xwz.spring5.User"></bean>
      </beans>
   ``` 
4. xml注入集合属性：
   1. 注入数组(普通数组)类型属性 --------使用`<array>` 和 `<value>` 标签
       - <span style="color:red">注意： ArrayList 类型的不能使用list 虽然预编译提示报错，但是运行时会报错</span> 
   2. 注入 List 集合类型属性 -----------使用`<list>` 和 `<value>` 标签
   3. 注入 Map 集合类型属性 ------------使用`<map>` 和 `<entry key="key1" value="value">` 标签
   4. 注入 Set 集合类型属性 ------------使用`<set>` 和 `<value>` 标签
      - 这些都要创建类， 定义属猪、List、Map、Set 类型属性，生成对应的set 方法

   新建一个 SetTest.class文件
   ```java
   package com.xwz.spring;
   import java.util.*;

   public class SetTest {
      private String[] array;
      private List<String> list;
      private Map<String, String> maps;
      private Set<String> sets;

      public void setArray(String[] array) {
         this.array = array;
      }
      public void setList(List<String> list) {
         this.list = list;
      }
      public void setMaps(Map<String, String> maps) {
         this.maps = maps;
      }
      public void setSets(Set<String> sets) {
         this.sets = sets;
      }

      @Override
      public String toString() {
         return "SetTest{" +
            "array=" + Arrays.toString(array) +
            ", list=" + list +
            ", maps=" + maps +
            ", sets=" + sets +
            '}';
      }
   }
   ```

   在src下新建一个 SpringSet.xml
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
      <bean id="setTest" class="com.xwz.spring.SetTest">
         <property name="array">
               <array>
                  <value>数组的测试</value>
                  <value>没有撤退可言</value>
               </array>
         </property>
         <property name="list">
               <list>
                  <value>法外狂徒</value>
                  <value>张三</value>
               </list>
         </property>
         <property name="maps">
               <map>
                  <entry key="m1" value="测试map" />
                  <entry key="m2" value="测试2" />
               </map>
         </property>
         <property name="sets">
               <set>
                  <value>测试set</value>
                  <value>测试set1</value>
               </set>
         </property>
      </bean>
   </beans>
   ```
   
   在随意目录下建一个用于运行测试的普通Class
   ```java
   package test;

   import com.xwz.spring.SetTest;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;

   public class TestSet {
   public static void main(String[] args) {
      ApplicationContext context =
         new ClassPathXmlApplicationContext(
               "SpringSet.xml"
         );
         SetTest setTest = context.getBean("setTest", SetTest.class);
         System.out.println(setTest);
      }
   }
   ```
5. A类中引用注入数组，数组中放着 B 类型 使用 `<ref bean="B_id">`;

      A类java代码
   ```java
   package com.xwz.spring;
   import java.util.List;

   public class TemplateTest {
      private List<TemplateChild> courseList;
      @Override
      public String toString() {
         return "TemplateTest{" +
            "courseList=" + courseList +
            '}';
      }
      public void setCourseList(List<TemplateChild> courseList) {
         this.courseList = courseList;
      }
   }

   ```
      B类中jva代码
   ```java
   package com.xwz.spring;
   public class TemplateChild {
      private String courses;

      public String getCourses() {
         return courses;
      }

      public void setCourses(String courses) {
         this.courses = courses;
      }
   }

   ```
   xml 代码
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="
               http://www.springframework.org/schema/beans
               http://www.springframework.org/schema/beans/spring-beans.xsd
            "
      >
         <bean id="templateTest" class="com.xwz.spring.TemplateTest">
            <property name="courseList">
                  <list>
                     <ref bean="templateChild" />
                  </list>
            </property>
         </bean>
         <bean id="templateChild" class="com.xwz.spring.TemplateChild">
            <property name="courses" value="测试对象中注入对象" />
         </bean>
      </beans>
   ```
6. 把集合注入部分提取出来
   (1) 在 spring 配置文件中引入名米空间 `util`
      - 在beans 上添加一个类属性，在beans 里边的 xsi:schemaLocation="..." 继续添加一个http连接
      - <span style="color:red">注意：原来的 xsi:schemaLocation 里边的值不要去掉，只是在后边追加</span>
      ```
         xmlns:util="http://www.springframework.org/schema/util"
         xsi:schemaLocation="http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd"
      ```
   （2）对 第五中 xml 进行改写
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:util="http://www.springframework.org/schema/util"
            xsi:schemaLocation="
               http://www.springframework.org/schema/beans 
               http://www.springframework.org/schema/beans/spring-beans.xsd
               http://www.springframework.org/schema/util 
               http://www.springframework.org/schema/util/spring-util.xsd
            ">
         <!-- 这种只是简单将集合提取出来 -->
         <bean id="templateTest" class="com.xwz.spring.TemplateTest">
            <property name="courseList" ref="c_list" />
         </bean>
         <util:list id="c_list">
            <ref bean="templateChild" />
            <ref bean="templateChild2" />
         </util:list>
         <!--  多个对象  -->
         <bean id="templateChild" class="com.xwz.spring.TemplateChild">
            <property name="courses" value="测试集合提取，使用util标签" />
         </bean>
         <bean id="templateChild2" class="com.xwz.spring.TemplateChild">
            <property name="courses" value="使用util标签2222" />
         </bean>
      </beans>
   ```

##### 1、IOC 操作 Bean 管理（工厂bean：FactoryBean）
1. Spring 中有两种类型bean ，一种是普通的bean，另一种是工厂bean
2. 普通bean： 在配置文件中定义bean类型就是返回类型，也就是说你bean 什么类，就返回什么类
3. 工厂bean： 在配置文件定义bean类型，可以和返回类型不一致
   - 创建类： 该类作为工厂bean，这个类需要实现接口 FactoryBean
   - 实现接口里面的方法，在实现方法 getBean 中定义 Bean 需要返回的类
##### 2、IOC 操作 Bean 管理（bean的作用域）
1. 在Spring 中，设置创建bean 实例是单实例还是多实例
   - 单实例概念：每次返回的对象都是同一个地址
   - 多实例概念：每次返回的对象都是不同的地址
2. 在Srping 里边，没有设置的话，默认bean 是单实例对象
3. 如何设置多实例
   - 在Spring配置文件bean 标签属性：scope 用于设置是否是单实例还是多实例
   - scope属性：
      * 值1：singleton 表示单实例（不设置的默认值）
      * 值2：prototype 表示多实例
   ```xml
   <bean id="user" class="com.xwz.spring5.User" scope="prototype" />
   ```
4. singleton 和 prototype 区别：
   - 第一： singleton 是单实例，prototype 是多实例
   - 第二：创建时区别 
      - `scope="singleton"` 时, 加载spring 配置文件时就会创建单实例对象（也就是调用 ClassPathXmlApplicationContext 方法时）
      - `scope="prototype"` 时，在调用getBean 方法时候创建多实例对象
##### 3、IOC 操作 Bean 管理（完整生命周期，对象创建到销毁的过程）
```tip
   注意：这里是完整的生命周期，共7步，加上了bean 的后置处理器
         如果 去掉 bean 后置处理器，那么就去掉 3和5 步剩下5个 
```
1. 通过构造器创建 bean 实例（无参构造方法）
2. 为 bean 属性设置值和其它bean 引用（调用set 方法）
3. <span style="color: red">把 bean实例传递 bean 后置处理器的方法 <span>：postProcessBeforeInitialization
4. 调用bean 初始化方法（需要设置）
   - 在bean 上使用 init-method 属性绑定 initMethod 方法
5.  <span style="color: red">把bean 实例传给bean 后置处理器的另外一个方法 <span> ：postProcessAfterInitialization
6. bean 使用（对象已经创建出来，可以进行使用）
7. 容器关闭时，调用bean 的销毁方法（需要配置）
   - 在 bean 上使用 destroy-method 属性绑定 destroyMethod 方法

定义一个 SpringCycle 普通类对象
```java
   package com.xwz.spring5;
   public class SpringCycle {
      public SpringCycle() {
         System.out.println("第一步：创建 bean 实例....");
      }
      private String name;
      public void setName(String name) {
         this.name = name;
         System.out.println("第二步：为实例设置值");
      }
      public void initMethod() {
         System.out.println("第四步：调用初始化方法");
      }
      public void destroyMethod() {
         System.out.println("第七步：调用销毁方法");
      }
   }
```
定义一个后置处理器类，实现 BeanPostProcessor 接口
```java
package com.xwz.spring5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SpringBeanPost implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("这是第三步，初始化前执行");
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("这是第五步，初始化后执行");
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
```
定义一个 xml 文件
```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
         "
   >
      <bean
         id="springCycle"
         class="com.xwz.spring5.SpringCycle" 
         init-method="initMethod" 
         destroy-method="destroyMethod"
      >
         <property name="name" value="张三" />
      </bean>
      <!-- 这里创建一个后置处理器，不需要绑定，在该文件中的所有类都会执行这个后置处理器 -->
      <bean
         id="springBeanPost" 
         class="com.xwz.spring5.SpringBeanPost" 
      />
   </beans>
```
在调用对象的test中，手动调用close方法。 destoryMethod 才会执行
```java
package test;

import com.xwz.spring5.SpringCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CycleTest {
  @Test
  public void cycleTest() {
    ClassPathXmlApplicationContext context =
      new ClassPathXmlApplicationContext("SpringCycleXML.xml");
    SpringCycle springCycle =  context.getBean("springCycle", SpringCycle.class);
    // 下面是一系列的 对象的使用 ....
    System.out.println("第六步：对象的使用：" + springCycle);
    // 最后注销这个类, 
    /** 这里是手动调用close方法。 destoryMethod 才会执行 */
    context.close();
  }
}
```
##### 4、IOC 操作 Bean 管理（自动装配: autowire）
1. 使用 autowire 自动装配
   - byName：根据属性名称注入（注意注入的值id 和 类属性名称一致）这个常用
   - byType：根据属性类型注入，这个少用
案例：创建两个类 Dept 和 Emp
   ```java
   package com.xwz.autowire;

   public class Dept {
      private String deptName;
   }
   public class Emp {
      private Dept dept;

      public void setDept(Dept dept) {
         this.dept = dept;
      }
   }
   ```
   在xml中使用 autowire
   ```xml
   <bean id="emp" class="com.xwz.autowire.Emp" autowire="byName"/>
   <bean id="dept" class="com.xwz.autowire.Dept" />
   ```
##### 5、IOC 操作 Bean 管理（引入外部属性文件）
以java 连接 mysql数据库
1. 下载链接mysql数据库的jer包地址：https://dev.mysql.com/downloads/connector/j/
2. 下载德鲁伊jer包地址：https://repo1.maven.org/maven2/com/alibaba/druid
3. 配置链接数据库的 properties 文件(src下新建jdbc.properties文件)
   ```properties
   prop.Driver=com.mysql.cj.jdbc.Driver
   prop.Url=jdbc:mysql://localhost:3306/userDb
   prop.user=root
   prop.password=root
   ```
4. 在xml 中配置文件
   - 先增加 beans 里边的 context 地址
   - 引入外部文件
   - 配置连接池
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="
                  http://www.springframework.org/schema/beans
                  http://www.springframework.org/schema/beans/spring-beans.xsd
                  http://www.springframework.org/schema/context
                  http://www.springframework.org/schema/context/spring-context.xsd
      ">
         <!-- 引入外部文件 -->
         <context:property-placeholder location="classpath:jdbc.properties" />
         <!-- 配置连接池  -->
         <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
            <property name="driverClassName" value="${prop.Driver}" />
            <property name="url" value="${prop.Url}"/>
            <property name="username" value="${prop.user}" />
            <property name="password" value="${prop.password}" />
         </bean>
      </beans>
   ```
### 5、IOC 操作 Bean 管理（基于注解）
1. 使用注解的目的：简化xml配置
2. Spring针对 Bean 管理中创建对象提供注解
   - @Component
   - @Service
   - @Controller
   - @Repository
#### 1、基于注解实现对象创建
1. 引入依赖jer包：spring-aop-5.3.9.jar
2. xml中开启组件扫描(同样需要引入context 命名空间)
3. 在新建的类上使用上面4个中的任意一个注解
   ```java
      package com.xwz.aop;
      import org.springframework.stereotype.Component;
      //value 不写默认就是 userService
      @Component(value = "userService") // 这里的注解等于 <bean id="userService" class="..." />
      public class UserService {
         public void add() {
            System.out.println("service add .....");
         }
      }
   ```
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd
      ">
         <!-- 开启扫描： base-package 是包名的路径，
            如果需要扫描多个包，可以用逗号隔开
            如果需要扫描包下的所有包，那就直接写父包，这样就会扫描父包下所有子包
         -->
         <context:component-scan base-package="com.xwz.aop" />
      </beans>
   ```
4. 关于扫描的属性讲解
   - use-default-filter:表示不使用默认的扫描filter
   - context:include-filter: 表示需要扫苗那些内容
      - expression="org.springframework.stereotype.Component" 表示只扫描包下带 @Component 注解的类
   - context:exclude-filter: 表示不扫描那些内容
      - expression="org.springframework.stereotype.Component" 表示不扫描包下带 @Component 注解的类
   ```xml
      <!-- 实例1 -->
      <context:component-scan
         base-package="com.xwz.aop"
         use-default-filter="false"
      >
         <context:include-filter
            type="annotation"
            expression="org.springframework.stereotype.Component"
         >
      </context:component-scan>
      <!-- 实例2 -->
      <context:component-scan base-package="com.xwz.aop">
         <context:exclude-filter
            type="annotation"
            expression="org.springframework.stereotype.Component"
         >
      </context:component-scan>
   ```
#### 2、基于注解方式实现属性注入
1. @Autowired: 根据属性类型进行自动装配
   - 在注入的类和属性类都需要加上注解 @Service（其它三个也行）
   - 在属性类上注解 @Autowired 即可，xml 中的扫描不需要修改（参考1）
   - userDao 也不需要 提供setUserDao 方法
      ```java
         // 这里出现波浪线的原因是，因为这个是根据类型注入，但是一个对象中可以有很多个相同的类型
         // 单单根据类型注入的话，会导致无法准确查找到该对象
         @Autowired 
         private UserDao userDao;
      ```
2. @Qualifer：根据属性名进行注入
   - 这个需要和 @Autowired 一起使用
   - 这里value指定注入的名称，这里的名称是 UserDao 接口的实现类上面注解的名称
   ```java
      @Autowired
      @Qualifier(value = "userDaoImpl") // 这里value指定注入的名称
      private UserDao userDao;
   ```
   ```java
      @Service(value = "userDaoImpl") // 定义名称 userDaoImpl
      public class UserDaoImpl implements UserDao{
         @Override
         public void add() {
            System.out.println(" UserDao add .... ");
         }
      }
   ```
3. @Resource：可根据类型注入，可根据名称注入
   - 这个就是 @Autowired 和 @Qualifier 的结合版
   ```java
      // @Resource 可以直接这样，这种按类型注入
      @Resource(value = "userDaoImpl") // 这里按名称注入
      private UserDao userDao;
   ```
4. @Value：注入普通类属性
   - 这个就是直接给基础类型注入属性用的
   ```java
      @Value(value="abc") // 直接注入 name = "abc"
      private String name;
   ```
#### 3、完全注解开发（没有xml文件）
1. 完全注解开发，就是用class 文件来替代了xml配置文件
   新建一个 `SpringConfig` class 文件（名字随便起，后边引入一致OK）
   ```java
      import org.springframework.context.annotation.ComponentScan;
      import org.springframework.context.annotation.Configuration;

      @Configuration // 这个做为配置类，代替xml配置文件
      @ComponentScan(basePackages = {"com.xwz.aop"}) // 这个还是需要扫描的文件
      public class SpringConfig {
      }
   ```
   在使用的test 文件中需要修改获取方式
   使用 `AnnotationConfigApplicationContext` 方法
   ```java
      import com.xwz.aop.SpringConfig; // 这个类要引入
      // 这是没有xml配置文件的
      @Test
      public void testAop01() {
         ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfig.class);
         UserService userService = context.getBean("userService", UserService.class);
         userService.add();
      }
   ```
# * AOP
### 1.什么是AOP？
1. AOP 面向切面编程
2. 通俗解释：不通过修改主代码部分的方式，新建另一个模块，在主干功能里边添加新的功能
3. 例如：登录时需要校验用户名密码，验证通过了，就能登录，
但是在后来的需求中添加了权限管理模块，这时新建一个另一个模块，
直接关联到主干登录流程，不修改主干代码，
不想要时可以去掉，不影响主干功能，这就是AOP
### 2. 有接口情况，使用JDK动态代理（Proxy）
#### 1. 调用newProxyInstance 方法
   - java.lang.reflect.Proxy 包中
   - 三个参数：
      - 参数1：用哪个类加载器去加载代理对象
      - 参数2：动态代理类需要实现的接口，接受一个数组
      - 参数3：动态代理方法在执行时，会调用 new对象 里面的 invoke 方法去执行，这个方法需要实现 InvocationHandler 接口
1. 案例：

   创建一个接口 UserDao
   ```java
   public interface UserDao {
      public int add(int a, int b);
   }
   ```
   UserDaoImp 实现这个接口 
   ```java
   public class UserDaoImp implements UserDao{
      @Override
      public int add(int a, int b) {
         System.out.println("add 方法执行了 ");
         return a + b;
      }
   }
   ```
   创建一个类加载器 JDKProxy
      - 首先比较重要的是一个 UserDaoProxy，这个是去实现一个接口，我们主要的增强代码都是在里边执行
      - 其次是执行 Proxy.newProxyInstance() 方法时的三个参数
   ```java
      import java.lang.reflect.InvocationHandler;
      import java.lang.reflect.Method;
      import java.lang.reflect.Proxy;
      import java.util.Arrays;

      // 这个定义成一个类加载器
      public class JDKProxy {
         public static void main(String[] args) {
            Class[] interfaces = { UserDao.class }; //这里是包装成数组

            UserDaoImp userDaoImp = new UserDaoImp();
            /**
            *  参数1：用哪个类加载器去加载代理对象
            *  参数2：动态代理类需要实现的接口，接受一个数组
            *  参数3：动态代理方法在执行时，会调用 new对象 里面的 invoke 方法去执行，这个方法需要实现 InvocationHandler 接口
            * */
            UserDao userDao = (UserDao)Proxy.newProxyInstance(
               JDKProxy.class.getClassLoader(),
               interfaces,
               new UserDaoProxy(userDaoImp)
            );
            // 执行add 方法
            userDao.add(1, 2);
         }
      }

      class UserDaoProxy implements InvocationHandler{
         // 通过有参构造方法进行获取到需要增强的对象
         private Object object;
         public UserDaoProxy() {}
         public UserDaoProxy(Object object) {
            this.object = object;
         }
         // 这里写增强逻辑代码
         /**
            *  里边分成三部分：方法执行前，方法执行，方法执行后
            * */
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 第一方法执行前, 查看参数
            System.out.println(
               "方法执行之前：方法名" + 
               method.getName() + 
               "参数：" + 
               Arrays.toString(args)
            );

            // 执行方法
            Object res = method.invoke(object, args);

            // 方法执行后
            System.out.println("方法执行后" + object);
            // 返回执行结果，例如 add 的 a+b 的执行结果
            return res;
         }
      }
   ```
#### 2. AOP 术语
1. 连接点
   - 类里边哪些方法能够被增强，这些方法称为连接点
2. 切入点
   - 实际被真正增强的方法，称为切入点
3. 通知（增强）
   - 实际增强逻辑部分称为通知
   - 通知有多种类型
      - 前置通知，方法执行前（参考1：调用newProxyInstance 方法）
      - 后置通知
      - 环绕通知： 前后都通知
      - 异常通知： 方法出现异常时通知
      - 最终通知： finally执行，不管成功还是异常都进行通知
4. 切面
   - 是个动作：把通知应用到切入点的过程
#### 3. AOP 操作准备
1. Spring 框架一般都是基于 AspectJ 实现 AOP 操作
   - AspectJ 不是 Spring 组成部分，独立于 AOP 框架，一般把AspectJ 和 Spring 框架一起用，进行AOP操作
2. 基于AspectJ 实现 AOP 操作实现方式
   - 基于xml配置文件实现
   - 基于注解方式实现（使用）
3. 需要准备添加4个 jer 包，一个 spring 中的，3个其它 jer 包的
   -下载地址：https://github.com/mxg133/learnforSpring5
   - 
4. 切入点表达式
   - 作用：知道对哪个类里边的哪个方法进行增强
   - 语法：execution([权限修饰符][返回类型][类全路径][方法名称]([参数列表])) --- 权限可以用*表示全部，返回类型可以省略
   - 常用栗子：
      - 对com.xwz.dao.BookDao类里边的add方法进行增强： execution(* com.xwz.dao.BookDao.add(..)) // 方法中的两个点表示参数，不能多不能少
      - 对com.xwz.dao.BookDao类里边的所有方法进行增强：execution(* com.xwz.dao.BookDao.*(..))
      - 对com.xwz.dao包里边的所有包的所有方法进行增强： execution(* com.xwz.dao.*.*(..))
#### 4. Aspect 操作基于注解
##### 通知的类型注解：
1. @Before 这个是前置通知,被增强类的方法之前执行
2. @After 这个是后置通知，被增强类的方法之后执行
3. @AfterReturning 
4. @AfterThrowing  异常时执行
5. @Around 环绕通知，这个比较特殊
##### 实例
1. 新建一个被增强类 User
```java
package com.xwz.aspect;
import org.springframework.stereotype.Service;
@Service
public class UserAspect {
  public void add() {
    System.out.println("add .....");
  }
}
```
2. 新建一个增强类 UserProxy
3. 进行通知设置
   - 在spring 配置文件中，开启注解扫描,(注意在类上加 @Service 或者 @Component)
   - 使用注解创建 User 和 UserProxy 对象
   - 在增强类上添加注解 @Aspect，生成代理对象
   - 在 spring 配置文件中开启生成代理对象
```java
package com.xwz.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

// 增强类
@Service //扫描
@Aspect //生成代理对象
public class UserProxy {

   // 这个是前置通知,被增强类的add方法
   @Before(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
   public void before() {
      System.out.println("在add之前执行了...");
   }
   @After(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
   public void after() {
      System.out.println("在add之后执行了...");
   }
   @AfterReturning(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
   public void afterReturning() {
      System.out.println("afterReturning执行了...");
   }
   // 异常通知
   @AfterThrowing(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
   public void afterThrowing() {
      System.out.println("afterThrowing执行了...");
   }
   // 环绕通知
   @Around(value = "execution(* com.xwz.aspect.UserAspect.add(..))")
   public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
      System.out.println("Around在add之前执行了...");
      proceedingJoinPoint.proceed();
      System.out.println("Around在add之后执行了...");
   }
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
">
    <!--  开启注解扫描  -->
    <context:component-scan base-package="com.xwz.aspect" />
    <!--  开启AspectJ 生成代理对象  -->
    <aop:aspectj-autoproxy />
</beans>
```
##### 切入点的抽取和多个增强类的优先级
1. 切入点的抽取使用 @Pointcut 注解 
   ```java
      @Component
      @Aspect
      public class UserProxy {
         // 相同切入点的抽取
         @Pointcut(value = "execution(* com.xwz.aspect.User.add(..))")
         public void pointcut() {}

         //  @Before(value = "execution(* com.xwz.aspect.User.add(..))")
         @Before(value = "pointcut()") // 这里就是抽离出的切入点，如果需要修改则只需要修改一处地方就可以了
         public void before() {
            System.out.println("before .....");
         }

         //  @After(value = "execution(* com.xwz.aspect.User.add(..))")
         @After(value = "pointcut()")
         public void after() {
            System.out.println("after .....");
         }
      }
   ```
2. 多个增强类的优先级使用@Order(number)
   - number 为数字，值越小，优先级越高
   - 以下为例： 只是针对每一个通知的类型的优先级
      - UserParentProxy 的 Before 比 UserProxy 的 Before先执行
      - UserParentProxy 的 After 不会比 UserProxy 的 Before 先执行
   ```java
      @Component
      @Aspect
      @Order(1)
      public class UserParentProxy {
         ...
      }

      @Component
      @Aspect
      @Order(2)
      public class UserProxy {
         ...
      }
   ```
#### 5. Aspect 操作基于配置（不常用,了解就行）
1. 新建一个被增强类(沿用4中实例)
2. 新建一个增强类（沿用4中实例）
3. 配置文件 AopXmlAspect
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="
         http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/aop
         http://www.springframework.org/schema/aop/spring-aop.xsd
   ">
      <bean id="user" class="com.xwz.aspect.User" />
      <bean id="userProxy" class="com.xwz.aspect.UserProxy" />
      
      <!--配置aop 增强 -->
      <aop:config>
         <!--切入点-->
         <aop:pointcut id="p" expression="execution(* com.xwz.aspect.User.add())" />
         <!-- 配置切面  -->
         <aop:aspect ref="userProxy">
               <!--  增强作用在具体哪个方法上  -->
               <aop:after method="before" pointcut-ref="p" />
         </aop:aspect>
      </aop:config>
   </beans>
   ```
#### 6.完全注解开发
1. 使用类完全替代xml配置文件
   - @EnableAspectJAutoProxy(proxyTargetClass = true)
   新建一个类 ConfigAspect
   ```java
      @Configuration
      @ComponentScan(basePackages = {"com.xwz.aspect"})
      @EnableAspectJAutoProxy(proxyTargetClass = true)
      public class ConfigAspect() {}
   ```
   测试获取
   ```java
      import com.xwz.aspect.ConfigAspect; // 这个要引入
      ....

      public class AspectTest {
         @Test
         public void testClass() {
            // 全部注解，走 ConfigAspect
            ApplicationContext context =
               new AnnotationConfigApplicationContext(ConfigAspect.class);
            UserAspect userAspect = context.getBean("userAspect", UserAspect.class);
            userAspect.add();
         }
      }
   ```
### 3. 没有接口情况，使用CGLIB 动态代理
# * jdbcTemplate
### 1. 基本工作
1. 引入相关依赖 https://pan.baidu.com/s/1BPdI_vDWW2M-1A0okF3Pww 提取码: 2333 
   - mysql-connector-java-5.1.7-bin.jar 在其他jar包里边（mysql5版本） mysql-connector-java-8.0.27.jar（mysql 8版本，去官网下载）
   - druid-1.1.9.jar 在其他jar包里边
   - spring-jdbc-5.2.6.RELEASE.jar  spring5的jer包里边
   - spring-orm-5.2.6.RELEASE.jar   spring5的jer包里边
   - spring-tx-5.2.6.RELEASE.jar    spring5的jer包里边
2. 在 spring 配置文件配置数据库的连接池(之前有写过了)
   ```xml
     <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="
                  http://www.springframework.org/schema/beans
                  http://www.springframework.org/schema/beans/spring-beans.xsd
                  http://www.springframework.org/schema/context
                  http://www.springframework.org/schema/context/spring-context.xsd
      ">
         <!-- 引入外部文件 -->
         <context:property-placeholder location="classpath:jdbc.properties" />
         <!-- 配置连接池  -->
         <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
            <property name="driverClassName" value="${prop.Driver}" />
            <property name="url" value="${prop.Url}"/>
            <property name="username" value="${prop.user}" />
            <property name="password" value="${prop.password}" />
         </bean>
      </beans> 
   ```
3. 配置 JdbcTemplate 对象，注入DataSource
   ```xml
      <!-- 配置jdbcTemplate 对象 -->
      <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
         <!--  注入数据库链接信息  -->
         <property name="dataSource" ref="dataSource" />
      </bean>
   ```
4. 配置文件开起扫描
   ```xml
      <context:component-scan base-package="com.xwz.jdbcTemplate" />
   ```
5. 新建一个User类实现一个UserDao接口，注入JdbcTemplate，新建一个Service 注入 UserDao
   ```java
     public interface UserDao {} 
   ```
   ```java
      @Service
      public class User implements UserDao{
         @Autowired
         private JdbcTemplate jdbcTemplate;
      }
   ```
   ```java
      @Service
      public class UserService {
         @Autowired
         private UserDao userDao;
      } 
   ```
### 2. JdbcTemplate 对数据库操作
#### 1. 新增
1. 新建一个实体包 entity
   - 新建一个类 UserInfom 并实现 set 和 get 方法
   ```java
      public class UserInfo {
         private int id;
         private String userName;
         private int status;
         ...
      }
   ```
2. 在 SuerService 类中实现 add 方法, 并调用 userDao 中 add 方法
   ```java
      public void add(UserInfo userInfo) {
         userDao.add(userInfo);
      }
   ```
3. UserDao 接口里边添加add方法约束，User 类中实现 add 方法
   - 
   ```java
      public void add(UserInfo userInfo) {
         String sql = "insert into t_user(userName, status) values (?,?)";
         int update = jdbcTemplate.update(sql, userInfo.getUserName(), userInfo.getStatus());
         System.out.println(update);
      }
   ```
4. 测试
   ```java
      public class JdbcTemplateTest {
         @Test
         public void test() {
            // 路径不是固定的，我这里是放在 jdbcTemplate 包里边
            ApplicationContext context =
               new ClassPathXmlApplicationContext("com/xwz/jdbcTemplate/jdbc.xml"); 
            UserService userService = context.getBean("userService", UserService.class);
            // 这里的数据本来更应该是拿到接口传过来的信息，这里模拟一下
            UserInfo info = new UserInfo();
            info.setUserName("张三");
            info.setStatus(1);
            userService.add(info);
         }
      }
   ```
#### 2. 修改和删除
1. UserDao 接口里边添加 update 和 delete 方法约束，User 类中实现 update 和 delete 方法
   ```java
      public void update(UserInfo userInfo);
      public void delete(int id);
   ```
2. 在 SuerService 类中实现 update 和 delete 方法, 并调用 userDao 中 update 和 delete 方法
   ```java
      public void update(UserInfo userInfo){
         userDao.update(userInfo);
      }
      public void delete(int id) {
         userDao.delete(id);
      }
   ```
3. 在 User 类中实现 update 和 delete 方法
   ```java
      @Override
      public void update(UserInfo userInfo) {
         String sql = "update t_user set userName=?, status=? where id=?";
         Object[] args = { userInfo.getUserName(), userInfo.getStatus(), userInfo.getId() };
         int update = jdbcTemplate.update(sql, args);
         System.out.println(update);
      }

      @Override
      public void delete(int id) {
         String sql = "delete from t_user where id=?";
         int update = jdbcTemplate.update(sql, id);
         System.out.println(update);
      }
   ```
#### 3. 查询功能
##### 1. 查询返回某个值
1. UserDao 接口里边添加 findCount 方法约束
   ```java
      public int findCount();
   ```
2. 在 SuerService 类中实现  findCount 方法, 并调用 UserDao 的 findCount 方法
   ```java
      public int findCount() {
         return userDao.findCount();
      }
   ```
3. User 类中实现 findCount 方法
   ```java
      @Override
      public int findCount() {
         String sql = "select count(*) from t_user";
         // 返回String 就 String.class
         Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
         return count;
      }
   ```
##### 2. 查询返回对象
使用 queryForObject 方法：
   - 参数1：sql
   - 参数2：RowMapper 接口的实现类，返回不同类型数据
   - 参数3：sql 语句的值
1. UserDao 接口里边添加 findInfo 方法约束
   ```java
      public UserInfo findInfo(int id);
   ```
2. 在 SuerService 类中实现  findInfo 方法, 并调用 UserDao 的 findInfo 方法
   ```java
      public UserInfo findInfo(int id) {
         return userDao.findInfo(id);
      }
   ```
3. User 类中实现 findInfo 方法
   ```java
      @Override
      public UserInfo findInfo(int id) {
         String sql = "select * from t_user where id=?";
         UserInfo userInfo = jdbcTemplate.queryForObject(
            sql, 
            new BeanPropertyRowMapper<UserInfo>(UserInfo.class), 
            id
         );
         return userInfo;
      }
   ```
##### 3. 查询返回列表
使用 query 方法
   - 参数1：sql
   - 参数2：RowMapper 接口的实现类，返回不同类型数据
   - 参数3：sql 语句的值
1. UserDao 接口里边添加 findAll 方法约束
   ```java
      public List<UserInfo> findAll(int page, int pageSize);
   ```
2. 在 SuerService 类中实现  findAll 方法, 并调用 UserDao 的 findAll 方法
   ```java
      public List<UserInfo> findAll(int page, int pageSize) {
         return userDao.findAll(page, pageSize);
      }
   ```
3. User 类中实现 findAll 方法
   ```java
      @Override
      public List<UserInfo> findAll(int page, int pageSize) {
         String sql = "select * from t_user limit ?,?";
         List<UserInfo> list = jdbcTemplate.query(
            sql, 
            new BeanPropertyRowMapper<UserInfo>(UserInfo.class), 
            (page - 1), pageSize
         );
         for (UserInfo userInfo: list) {
            System.out.println(userInfo.toString());
         }
         return list;
      } 
   ```
#### 4. 批量操作
##### 1. 批量添加
使用 batchUpdate 方法
   - 参数1： sql
   - 参数2：List集合，添加多条数据的List
1. UserDao 接口里边添加 batchList 方法约束
   ```java
      public void batchList(List<Object[]> batchArgs);
   ```
2. 在 SuerService 类中实现  batchList 方法, 并调用 UserDao 的 batchList 方法
   ```java
      public void batchList(List<Object[]> batchArgs) {
         userDao.batchList(batchArgs);
      }
   ```
3. User 类中实现 batchList 方法
   ```java
      @Override
      public void batchList(List<Object[]> batchArgs) {
         String sql = "insert into t_user(userName, status) values(?,?)";
         int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
         System.out.println(Arrays.toString(ints));
      }
   ```
##### 2. 批量修改
1. UserDao 接口里边添加 batchUpdateUser 方法约束
   ```java
      public void batchUpdateUser(List<Object[]> batchArgs);
   ```
2. 在 SuerService 类中实现  batchUpdateUser 方法, 并调用 UserDao 的 batchUpdateUser 方法
   ```java
      public void batchUpdateUser(List<Object[]> batchArgs) {
         userDao.batchUpdateUser(batchArgs);
      }
   ```
3. User 类中实现 batchUpdateUser 方法
   ```java
      @Override
      public void batchUpdateUser(List<Object[]> batchArgs) {
         String sql = "update t_user set userName=?, status=? where id = ?";
         int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
         System.out.println(Arrays.toString(ints));
      }
   ```
##### 3. 批量删除
1. UserDao 接口里边添加 batchDeleteUser 方法约束
   ```java
      public void batchDeleteUser(List<Object[]> batchArgs);
   ```
2. 在 SuerService 类中实现  batchDeleteUser 方法, 并调用 UserDao 的 batchDeleteUser 方法
   ```java
      public void batchDeleteUser(List<Object[]> batchArgs) {
         userDao.batchDeleteUser(batchArgs);
      }
   ```
3. User 类中实现 batchDeleteUser 方法
   ```java
      @Override
      public void batchDeleteUser(List<Object[]> batchArgs) {
         String sql = "delete from t_user where id = ?";
         int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
         System.out.println(Arrays.toString(ints));
      }
   ```
# * 事务管理
### 准备工作
1. 完整代码三步走：后边倒叙讲解
   - 新建dao，dao新建接口Account（面向接口编程），新建 接口实现类 AccountImp
   ```java
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
   ```
   - 新建service，service 中新建 AccountService类
   ```java
      @Repository
      @Transactional
      public class AccountService {
         @Autowired
         public Account account;

         public void transactionMoney() {

            account.reduceMoney(); // 从账户转出

            account.addMoney(); // 转进账户中
         }
      }
   ```
   - 新建 xml 配置文件，在文件目录下新建 transaction.xml
   ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xmlns:tx="http://www.springframework.org/schema/tx"
            xsi:schemaLocation="
                  http://www.springframework.org/schema/beans
                  http://www.springframework.org/schema/beans/spring-beans.xsd
                  http://www.springframework.org/schema/context
                  http://www.springframework.org/schema/context/spring-context.xsd
                  http://www.springframework.org/schema/tx
                  http://www.springframework.org/schema/tx/spring-tx.xsd
      ">
         <context:component-scan base-package="com.xwz.transaction" />
         <!-- 引入外部文件 -->
         <context:property-placeholder location="classpath:com/xwz/transaction/jdbc.properties" />
         <!-- 配置连接池  -->
         <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
            <property name="driverClassName" value="${prop.Driver}" />
            <property name="url" value="${prop.Url}"/>
            <property name="username" value="${prop.user}" />
            <property name="password" value="${prop.password}" />
         </bean>

         <!-- 配置jdbcTemplate 对象 -->
         <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
            <!--  注入数据库链接信息  -->
            <property name="dataSource" ref="dataSource" />
         </bean>

         <!-- 创建事务管理器 -->
         <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <!--   注入数据源，也就是数据库链接     -->
            <property name="dataSource" ref="dataSource" />
         </bean>
         <!-- 开启事务注解 -->
         <tx:annotation-driven transaction-manager="transactionManager" />
      </beans>
   ```
   - 额外新建jdbc配置文件 同在文件目录下：jdbc.properties
   ```properties
      prop.Driver=com.mysql.cj.jdbc.Driver
      prop.Url=jdbc:mysql://localhost:3306/userdb
      prop.user=root
      prop.password=root
   ```
   - 在数据库中新建一个表，t_account，字段有 id  c_name，money 加入两条数据，
### 基础讲解
1. 事务添加到JavaEE三层结构里边的Servise层（业务逻辑层）
2. 在Spring进行事务有两种方式：编程式（spring中少用） 和 声明式（spring 中常用）
3. 声明式事务管理
   - 基于注解
   - 基于xml配置（少用）
### 基于注解 (参考 `com.xwz.transaction` 包的代码)
1. 创建事务管理器
   ```xml
       <!-- 创建事务管理器 -->
      <bean
         id="transactionManager"
         class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
      >
         <!--   注入数据源，也就是数据库链接     -->
         <property name="dataSource" ref="dataSource" />
      </bean>
   ```
2. 在spring 配置文件，开启事务注解
   - 就是添加一个 tx 命名空间
   ```xml
   <beans
      xmlns:tx="http://www.springframework.org/schema/tx"
      xsi:schemaLocation="
            http://www.springframework.org/schema/tx
            http://www.springframework.org/schema/tx/spring-tx.xsd
   "></beans>
   ```
   - 开启事务注解
   ```xml
      <!-- 开启事务注解 -->
      <tx:annotation-driven transaction-manager="transactionManager" />
   ```
3. 在service类上（或者service 方法上）添加事务注解 
   - @Transactional 这个注解可以加到类上，也可以加到方法上
   - 加到类上就是对整个类进行事务管理
   - 加到方法上就是对这个方法进行事务管理
   ```java
      @Repository
      @Transactional
      public class AccountService {
         @Autowired
         public Account account;

         public void transactionMoney() {

            account.reduceMoney(); // 从账户转出

            account.addMoney(); // 转进账户中
         }
      }
   ```
### 事务操作(声明式事务管理参数配置)
##### 在service 类上面添加注解@Transactional，在这个注解里边可以配置相关参数
```java
   @Transactional
   public void add() {
      // 调用update 方法
      update()
   }

   public void update() {}
```
1. propagation 事务传播行为
   1. 事务方法： 对数据库表数据进行操作的放法（dao中的实现类中的 addMoney 方法）
   2. spring 框架中对事务传播行为有7中，其中主要了解两种
      - REQUIRED：如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。
      - REQUIRED_NEW: 重新创建一个新的事务，如果当前存在事务，延缓当前的事务。
      - SUPPORTS： 如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行
      - NOT_SUPPORTED： 以非事务的方式运行，如果当前存在事务，暂停当前的事务
      - MANDATORY： 如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常
      - NEVER：以非事务的方式运行，如果当前存在事务，则抛出异常
      - NESTED：如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务
2. ioslation 事务隔离级别
   - 解决sql读 的 脏读，幻读
3. timeout 超时时间，默认是-1，可以设置秒
4. readOlay 是否只读 默认是 false
   -  设置为true，那么这个业务方法只能读，不能写
5. rollbackFor： 回滚
   - 出现指定异常时，进行事务回滚
6. noRollbackFor 不回滚
   - 出现指定异常时，不进行事务回滚
### 完全注解方式（用class 代替了xml）
新建一个TxConfig
```java
   /**
   * 完全注解的方式，就是对 transaction.xml 进行注解化
   * */
   @Configuration // 配置类
   @ComponentScan(basePackages = "com.xwz.transaction") // 开启扫描
   @EnableTransactionManagement // 开启事务
   public class TxConfig {
      // 获取配置文件
      String resourceFile = "com.xwz.transaction.jdbc";
      ResourceBundle bundle = ResourceBundle.getBundle(resourceFile);

      // 配置连接池
      @Bean
      public DruidDataSource getDruidDataSource() {
         DruidDataSource druidDataSource = new DruidDataSource();
         druidDataSource.setDriverClassName(bundle.getString("prop.Driver"));
         druidDataSource.setUrl(bundle.getString("prop.Url"));
         druidDataSource.setUsername(bundle.getString("prop.user"));
         druidDataSource.setPassword(bundle.getString("prop.password"));
         return  druidDataSource;
      }

      // 配置jdbcTemplate
      @Bean
      public JdbcTemplate getJdbcTemplate(DruidDataSource dataSource) {
         JdbcTemplate jdbcTemplate = new JdbcTemplate();
         jdbcTemplate.setDataSource(dataSource);
         return jdbcTemplate;
      }

      // 创建事务管理器
      @Bean
      public DataSourceTransactionManager getDataSourceTransactionManager(DruidDataSource dataSource) {
         DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
         transactionManager.setDataSource(dataSource);
         return  transactionManager;
      }
   }
```
测试 类中使用这个方法测试
```java
   @Test
   public void testAnnotation() {
      ApplicationContext context =
         new AnnotationConfigApplicationContext(TxConfig.class);
      AccountService accountService = context.getBean("accountService", AccountService.class);
      accountService.transactionMoney();
   }
```
 
# * spring5新特性
#### 整合 JUnit5 的特性(单元测试)
```java
import com.xwz.transaction.TxConfig;
import com.xwz.transaction.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // 创建测试框架
@ContextConfiguration(classes = TxConfig.class) // 加载配置文件，这里是需要配置全注解扫描
public class JTest5 {
  @Autowired
  private AccountService accountService;
  @Test
  public void test() {
    accountService.transactionMoney();
  }
}
```
