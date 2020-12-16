package test.interview;

/**
 * @author yangkai
 * @date 2020/5/7
 * @email yangkai@hujiang.com
 * @description
 */
public class B extends A {
   static {
       System.out.println("B");
       a = new B();
   }
}
