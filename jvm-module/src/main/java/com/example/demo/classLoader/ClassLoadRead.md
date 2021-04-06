#### 双亲委派机制
***

针对：应用程序类的加载过程   （同理对于 扩展类的加载过程 也是类似）
1. 首先，检查一下指定名称的类是否已经加载过，如果加载过了，就不需要再加载，直接 返回。 
2. 如果此类没有加载过，那么，再判断一下是否有父加载器；如果有父加载器，则由父加载器（扩展类加载器）加载（即调用parent.loadClass(name, false);）.
3：如果父加载器（扩展类加载器）也没有，则调用bootstrap类加载器来加载。
3. 如果父加载器及bootstrap类加载器都没有找到指定的类，那么调用当前类加载器的 findClass方法来完成类加载。
注意 只是 父加载器 不是父类  是子加载器的父加载器属性等于父加载器
***
ClassLoader.loadClass();
源码：查看 源码 Class<?> loadClass


为什么要设计双亲委派机制？
    沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心 API库被随意篡改。(避免后门)
    避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一 次，保证被加载类的唯一性
***
编写一个自己的类 
在引导类加载器 加载出了JDK里面的java.lang.String  但这个类没有main方法 直接报错
 package java.lang;  
  public class String { 
    public static void main(String[] args) {  
        System.out.println("**************My String Class**************"); 
     } 
  }

在类 java.lang.String 中找不到 main 方法, 
请将 main 方法定义为:  public static void main(String[] args)
***


为什么要从下往上加载：
类加载的时候 绝大部分是都是自己写的应用程序类
1：首次加载 某应用程序类 的时候多循环了一次
2：再次加载 就可以直接在应用程序类加载器中 查找到并返回了