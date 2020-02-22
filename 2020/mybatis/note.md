笔记：
-------
mybatis-started
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

## mybatis-conf

mybatis配置中需要记住的有：

![image-20200222224733443](C:\Users\1\AppData\Roaming\Typora\typora-user-images\image-20200222224733443.png)

忘记了就看官方文档

mybatis中的设置几个比较重要的：

![image-20200222224609722](C:\Users\1\AppData\Roaming\Typora\typora-user-images\image-20200222224609722.png)

![image-20200222224621548](C:\Users\1\AppData\Roaming\Typora\typora-user-images\image-20200222224621548.png)

![image-20200222224628392](C:\Users\1\AppData\Roaming\Typora\typora-user-images\image-20200222224628392.png)

主要记住上面这四个

plugins插件

mybatis-plus：继续简化mybatis，像魂斗罗中的1p 2p基友互补

mybatis-generator-core：mybatis自动代码生成工具

![image-20200222230638139](C:\Users\1\AppData\Roaming\Typora\typora-user-images\image-20200222230638139.png)



方式二注意点：

接口和它的Mapper配置文件必须重名！

接口和它的Mapper配置文件必须在同一个包下！

**最推荐使用方式一**

1. 将数据库配置文件外部引入

2. 实体类别名

3. 保证userMapper接口和UserMapper.xml改为一致！并放在同一个包下！