####  编写项目中遇到的问题

#### 1：无法使用@Autowire 
    
    只是因为该类还没有存放到本项目的spring容器中
    
    解决方案
    
    1：直接new一个该类的对象出来
    可以使用 ClassName className = new ClassName();
    
    2:把该类注册到Spring中
      @Bean
      public ClassName className() {
            return new ClassName();
      }
      
#### 2：Controller 层添加了 @RequestBody RequestBody
    无法使用 log.info("打印参数：{}",JSONObject.toJSONString(RequestBody));
    可以使用 log.info("打印参数：{}",RequestBody);

#### 3：@EqualsAndHashCode(callSuper = false)的作用
    callsuper  ture  代表着  加上父类的的属性来 来生成 hashcode
    callsuper  false 代表着  不用父类的的属性来 来生成 hashcode
    
    callsuper（默认为true）
    两个对象 equal的时候  判断是否相等 是通过 hashcode来判断的
    
#### 4：debug模式  无法看到继承父类的属性  但日志却可以
    如实体类继承了BaseModel  但在debug模式中无法 看到BaseModel的属性
         
#### 5：New出来的对象是  里面无法使用 Autowire的 
     可以利用反射   反射出来的类 使用构造方法创建的对象也无法使用  Autowire
     可以使用ApplicationContextHelper  通过上下文 直接获取该类的 spring对象
     详情见 User类
            
#### 6：JSONObject.toJSONString 会执行该实体里面所有的get方法
       @JSONField(serialize = false)
       在相应的get方法中加入该注解
       
#### 7：发现执行单元测试的时 Class not found: com.example.xxx.junit
    点击maven 的 lifecycle 再点击 test    