笔记：
-------
mybatis-01
-------
1. 使用IDEA创建maven项目，删除自带的src目录

2. 开始依照mybatis官方文档进行环境搭建

3. 先创建数据库，使用命令创建，而不是可视化工具，复习sql命令

4. 创建好数据库和测试用表后开始写程序

5. 编写mybatis的核心配置文件，在resource文件夹下创建mybatis-config.xml

6. 当mysql为8的时候，需要配置时区：`MySQL 8.0.12 时区设置和修改`,解决方案https://blog.csdn.net/vkingnew/article/details/82149726

7. 配置文件配置之后，在java目录下建立三个文件夹，在utils包里写个获取SqlSession方法的工具类

8. 编写代码--》实体类--》Dao接口--》接口实现类,现在有了mybatis那么接口实现类就可以用实体类Mapper.xml文件替换掉了

9. 完成编码进行测试

10. 测试遇到的坑：

01--》`org.apache.ibatis.binding.BindingException: Type interface com.chen.dao.UserMapper is not known to the MapperRegistry.`
    在mapper注册中心未找到，解决方案，在mybatis-config.xml文件中配置<mappers><mapper>**配置这一块**</mapper></mappers>
    02--》``java.lang.ExceptionInInitializerError
        Caused by: org.apache.ibatis.exceptions.PersistenceException: ``
    
    初始化异常，原因是maven的filters，maven的约定大于配置，有可能导致我们写的配置文件无法被导出或者生效，解决方案：在build中配置resources，防止我们资源导出失败的问题，在pom文件中加入下面的代码。
        ```maven
             <build>
                 <resources>
                     <resource>
                         <directory>src/main/resources</directory>
                         <includes>
                             <include>**/*.properties</include>
                             <include>**/*.xml</include>
                         </includes>
                         <filtering>true</filtering>
                     </resource>
                     <resource>
                         <directory>src/main/java</directory>
                         <includes>
                             <include>**/*.properties</include>
                             <include>**/*.xml</include>
                         </includes>
                         <filtering>true</filtering>
                     </resource>
                 </resources>
             </build>
        ```

11. 由于版本原因，在设置mysql驱动的时候，应该修改com.mysql.jdbc.Driver为**com.mysql.cj.jdbc.Driver**，并且由于mysql版本原因，有时候需要设置时区，url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai,在此我设置时区为上海，这样数据库的时间是什么，后台取到的就是什么，如果想设置为国际时间可以将Asia/Shanghai设置为GMT

12. 可能遇到的问题：配置文件没有注册------绑定接口错误------方法名不对-------返回类型不对--------maven导出资源有问题

13. 三个核心对象：SqlSessionFactoryBuilder  SqlSessionFactory  SqlSession（用完记得关闭）

14. 使用sqlSession的话，可以使用万能map，在mapper.xml中执行插入操作#{value}这样来拼接的，如果参数是对象的话，那么value就应该是对象的字段名，如果是普通类型的话，就应该按照原有函数的参数名字一一对应填入，如果是map的话，就可以传入map的key，通过key取到value，然后拼接到xml中，具体查看实例UserMapper.xml

15. 模糊查找，为了避免sql注入产生的安全性问题，可以"%"#{value}"%"，而不要在value中写死。

-----

## mybatis-02

mybatis配置中需要记住的有：

属性

设置

别名

忘记了就看官方文档

mybatis中的设置几个比较重要的：

`logImpl     mapUnderscoreToCamelCase    cacheEnabled    	 lazyLoadingEnabled` 

主要记住上面这四个

plugins插件

mybatis-plus：继续简化mybatis，像魂斗罗中的1p 2p基友互补

mybatis-generator-core：mybatis自动代码生成工具

映射器：

MapperRegistry：注册绑定我们的Mapper.xml文件

方式一【推荐使用】：

```xml
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```

方式二（使用class文件绑定注册）

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```

注意点：

接口和它的Mapper配置文件必须重名！

接口和它的Mapper配置文件必须在同一个包下！

方式三（使用扫描包进行注入绑定）

```xml
<!-- 将包内的映射器接口实现全部注册为映射器 -->
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

注意点：

接口和它的Mapper配置文件必须重名！

接口和它的Mapper配置文件必须在同一个包下！

**最推荐使用方式一**

1. 将数据库配置文件外部引入

2. 实体类别名

3. 保证userMapper接口和UserMapper.xml改为一致！并放在同一个包下！

-----------

生命周期和作用域

生命周期和作用域是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

**SqlSessionFactoryBuilder**

- 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了
- 局部变量

**SqlSessionFactory**

- 说白了就是可以想象成数据连接池
-  SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。 
-  因此 SqlSessionFactory 的最佳作用域是应用作用域。最简单的就是使用单例模式或者静态单例模式。 

**SqlSession**

- 连接池的一个请求
- SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。 
- 用完之后就应该马上关闭，否则资源会被占用

------------

## mybatis-03

数据库表的字段名和项目中java实体类的字段名不一致

```java
User{id=1, name='小王', password='null', time=2020-03-11 14:44:50.0}
```

查询出来password为null

解决方法：

- 起别名

  ```xml
  <select id="getUserById" parameterType="int" resultType="com.chen.pojo.User">
      select id,name,pwd as password,time from user where id = #{id}
  </select>
  ```

  程序运行结果

  ```java
  User{id=1, name='小王', password='112342', time=2020-03-11 14:44:50.0}
  ```

  

- resultMap

  ```xml
  <!--结果映射集-->
  <resultMap id="UserMap" type="com.chen.pojo.User">
      <result column="id" property="id"/>
      <result column="name" property="name"/>
      <result column="pwd" property="password"/>
      <result column="time" property="time"/>
  </resultMap>
  
  <select id="getUserById" parameterType="int" resultMap="UserMap">
      select * from user where id = #{id}
  </select>
  ```

  ```java
  User{id=1, name='小王', password='112342', time=2020-03-11 14:44:50.0}
  ```

  - resultMap 元素是 MyBatis 中最重要最强大的元素。
  -  ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。 

---

## mybatis-04

日志：如果我们的数据库操作出现异常，最好有个日志可以查看，快速定位问题

原来：sout  debug

现在：日志工厂（mybatis自带）

 logImpl 设置

 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。 

SLF4J 

LOG4J

LOG4J2

JDK_LOGGING

COMMONS_LOGGING 

STDOUT_LOGGING 

NO_LOGGING 

在mybatis中具体使用哪个日志，在设置中设定

**STDOUT_LOGGING**标准日志，无需添加依赖，直接配置就可以用

**log4j日志包的使用**

使用外部类步骤【LOG4J】：

1.导入jar包

```xml
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```



2.新建log4j.properties配置文件

```properties
# 将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file
# 控制台输出的相关设置
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target=System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
# 文件输出的相关设置
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/chen.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
# 日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```



3.进行自定义配置

4.创建log的java实体对象

```java
import org.apache.log4j.Logger;
static Logger logger = Logger.getLogger(UserMapperTest.class);
```

包不能导错了！

5.在想要输出日志的class类中这样调用：

```java
logger.info("这是logger info");
logger.error("这是logger error");
logger.warn("这是logger warn");
```

上面三个分别对应日志的三个级别：信息，错误，警告

在IDEA安装.log插件可以将log文件高亮，更易找出错误和警告

分页：

```sql
select * from table limit start,pages
```



----

## mybatis-05

**注解执行sql的原理：**

注解开发的**核心（本质）**是使用反射技术，反射可以在程序运行的时候得到某个类的所有方法，然后执行想要执行的方法。

底层使用的是**动态代理**。

使用注解开发：

第一步：注解在接口上实现

```java
@Select("select * from user")
List<User> getUsers();
```

第二步：在配置文件上绑定接口

```xml
<!--绑定接口-->
<mappers>
    <mapper class="com.chen.dao.UserMapper"/>
</mappers>
```

第三步：正常从sqlsession获取mapper.class，然后执行接口的方法

```java
@Test
public void getUserTest(){
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> users = mapper.getUsers();
    for (User user : users){
        System.out.println(user);
    }
    sqlSession.close();
}
```

mybatis注解开发自动提交事务：

```java
public static SqlSession getSqlSession(){
    return sqlSessionFactory.openSession(true);
}
```

## mybatis-06

**测试环境搭建：**

1.导入lombok

2.建立实体类Teacher,Student

3.建立Mapper接口建立Mapper.xml文件

4.在核心配置文件中绑定注册我们的Mapper接口或者文件

5.测试查询能否成功



**多对一处理：**

**按照查询嵌套处理**

```xml
<!--思路:1、查询所有的学生信息 2、根据查询出来的学生的tid来寻找对应的老师-->
<select id="getStudent" resultMap="StudentTeacher">
    select * from student
</select>

<resultMap id="StudentTeacher" type="com.chen.pojo.Student">
    <result property="id" column="id"/>
    <result property="name" column="name"/>
    <!--复杂查询我们需要单独处理 对象：association 集合：collection-->
    <association property="teacher" column="tid" javaType="com.chen.pojo.Teacher" select="getTeacher"/>
</resultMap>

<select id="getTeacher" resultType="com.chen.pojo.Teacher">
    select * from teacher where id=#{id};
</select>
```

**按照结果嵌套处理**

```xml
<!--按照结果嵌套处理-->
<select id="getStudent2" resultMap="StudentTeacher2">
    select s.id sid,s.name sname,t.name tname from student s,teacher t where s.tid = t.id
</select>
<resultMap id="StudentTeacher2" type="com.chen.pojo.Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="com.chen.pojo.Teacher">
        <result property="name" column="tname"/>
    </association>
</resultMap>
```

回顾mysql多对一查询方式：

子查询

联表查询





**一对多处理：**

比如一个老师拥有多个学生，对于老师而言就是一对多

首先是实体类

```java
@Data
@ToString
public class Teacher {
    private int id;
    private String name;
    //一个老师拥有多个学生
    private List<Student> students;
}
```

```java
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
```

**根据结果嵌套处理（个人推荐）**

```xml
<!--按照结果嵌套查询-->
<select id="getTeachers" resultMap="TeacherStudent">
    select s.id sid,s.name sname,t.name tname,t.id tid from student s,teacher t where s.tid = t.id and t.id = #{tid};
</select>
<!--复杂查询我们需要单独处理 对象：association 集合：collection
        对于集合中的泛型  我们使用ofType来指定-->
<resultMap id="TeacherStudent" type="com.chen.pojo.Teacher">
    <result property="id" column="tid"/>
    <result property="name" column="tname"/>
    <collection property="students" ofType="com.chen.pojo.Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```

**根据查询嵌套处理**

```xml
<!--按照查询嵌套处理
        思路：1、先查老师，然后查学生的tid为老师的id的学生的结果
    -->
<select id="getTeachers2" resultMap="TeacherStudent2">
    select * from teacher where id=#{tid}
</select>
<resultMap id="TeacherStudent2" type="com.chen.pojo.Teacher">
    <collection property="students" javaType="ArrayList" ofType="com.chen.pojo.Student" select="getStudentByTeacherId" column="id"/>
</resultMap>

<select id="getStudentByTeacherId" resultType="com.chen.pojo.Student">
    select * from student where tid=#{tid};
</select>
```

**总结：**

1、保证sql的可读性

一对多和多对一需要注意：

3、JavaType【用来指定实体类中属性的类型】、ofType【用来映射到List或者集合中的pojo类型，泛型中的约束类型】以及collection集合【一对多】、association关联对象【多对一】



面试高频：

mysql引擎

索引

索引优化

InnoDB底层原理



**动态sql环境搭建：**

```sql
CREATE TABLE `blog`(
`id` VARCHAR(50) NOT NULL COMMENT '博客id',
`title` VARCHAR(100) NOT NULL COMMENT '博客标题',
`author` VARCHAR(30) NOT NULL COMMENT '博客作者',
`create_time` datetime NOT NULL COMMENT '创建时间',
`views` int(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

创建一个基础工程

1、导包

2、编写配置文件

3、编写实体类

```java
@Data
@ToString
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;    //属性名和字段名不一致
    private int views;
}
```



4、编写实体类对应Mapper接口和Mapper.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--核心配置文件-->
<mapper namespace="com.chen.dao.BlogMapper">
    <insert id="addBlog">
        insert into blog (id, title, author, create_time, views) values (#{id},#{title},#{author},#{createTime},#{views});
    </insert>

    <select id="queryBlogIF" parameterType="map" resultType="Blog">
        select * from blog where 1=1
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </select>
</mapper>
```

```java
package com.chen.dao;

import com.chen.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    //插入数据
    int addBlog(Blog blog);

    //根据if条件判断查询数据
    List<Blog> queryBlogIF(Map map);
}
```

