####  编写项目中遇到的问题

#### 1：使用了无法使用@Autowire 
    
    只是因为该类还没有存放到本项目的spring容器中
    
    解决方案
    
    1：直接new一个该类的对象出来
    可以使用 ClassName className = new ClassName();
    
    2:把该类注册到Spring中
      @Bean
      public ClassName className() {
            return new ClassName();
      }
     