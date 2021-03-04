####    spring-security-jwt-module(模块讲解)

##### 项目用到的一些框架/服务：
    数据库： H2内存数据库，无需手动安装。
    缓存： Redis
    权限框架 ：Spring Security
    ORM框架 ：Mybatis plus 加 自定义 mapper
    
    接口文档 ： swagger:
    在线 API 文档地址：http://localhost:7001/api/swagger-ui/
    目前使用 knife4j 增强了 swagger 功能:
    地址： http://localhost:7001/api/doc.html#/home 
    
##### 该项目的学习要点：
    1. Spring Security +JWT 实现登入登出以及权限校验
    2. JPA 实现审计功能、多对多的映射关系如何通过关联表实现

##### 项目结构
![](./pictures/structure.png)    