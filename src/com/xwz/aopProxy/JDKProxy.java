package com.xwz.aopProxy;

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
  /** 这里写增强逻辑代码
   *  里边分成三部分：方法执行前，方法执行，方法执行后
   * */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    // 第一方法执行前, 查看参数
    System.out.println("方法执行之前：方法名"+ method.getName() + "参数：" + Arrays.toString(args));

    // 执行方法
    Object res = method.invoke(object, args);

    // 方法执行后
    System.out.println("方法执行后" + object);
    // 返回执行结果，例如 add 的 a+b 的执行结果
    return res;
  }
}
