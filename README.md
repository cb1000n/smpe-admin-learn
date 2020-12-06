

# smpe-admin-learn

#### 介绍
学习开源框架：https://github.com/shiwei-Ren/smpe-admin

前端框架暂时不打算学习（https://github.com/shiwei-Ren/smpe-admin-web）
# 配置 swagger && git排除规则文件 `.gitignore`
## git排除项
`.gitignore`
```gitignore
### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr
/logs/
/upload/
*/target/

# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

```
## 添加依赖
`spem-admin-learn/pom.xml`
```xml
<!--我的配置-->
<!--声明变量，用于统一控制版本-->
<properties>

    <!--文件编码格式 start-->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <!--文件编码格式 end-->

    <!--jdk 版本 start-->
    <java.version>1.8</java.version>
    <!--jdk 版本 end-->

    <!--以上变量是给其他依赖声明的，否则可能会出现乱码、报错等，可以更改为 GBK 测试（自定义日志乱码）-->

    <swagger.version>2.9.2</swagger.version>
    <lombok.version>1.18.8</lombok.version>
</properties>

<!--
该节点下的依赖关系只是为了统一版本号，
子项目添加依赖时，只能选择这个标签中的版本，否则会报错；
该节点中的依赖关系子项目不会自动继承，除非子项目主动引用；

如果一些依赖，只是某几个子项目需要，其他项目不需要，可以在这里进行版本锁定，
以防子项目引入不同版本的依赖，造成问题，同时在这里锁定后，子项目可以不用写版本号
-->
<dependencyManagement>
    <dependencies>
        <!--lombok start-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
        <!--lombok end-->

        <!--swagger start-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <!--swagger end-->
    </dependencies>
</dependencyManagement>

<!--引入依赖-->
<dependencies>

    <!--lombok start-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
    </dependency>
    <!--lombok end-->

    <!--swagger start-->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>${swagger.version}</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>${swagger.version}</version>
    </dependency>
    <!--swagger end-->

    <!-- springboot web 启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>



<!--框架配置，（未采用）-->

<!--规定版本-->
<properties>
    <swagger.version>2.9.2</swagger.version>
    <swagger.models.version>1.5.21</swagger.models.version>
    <swagger.annotations.version>1.5.21</swagger.annotations.version>
</properties>


<!--问题在下边的注释里边写的很清楚，而且eladmin官网也是这样引入配置的，
但是我测试了一下没试出来bug，暂时不这样搞了-->
<!-- swagger start -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>${swagger.version}</version>
    <!--Swagger异常：AbstractSerializableParameter : Illegal DefaultValue null for parameter type integer
        由于实体类使用@ApiModelProperty时，example属性没有赋值导致的，会进行非空判断
        解决： 排除后，上传更高版本

        https://blog.csdn.net/qq_17589751/article/details/105855854
        exclusions 这个标签的作用是排除关联依赖的引入，因为maven的pom依赖其中有一点是将关联的依赖全都引入进来 ，
        这个标签在这的作用就是 如果关联的依赖和引入的其他依赖可能存在冲突，
        就必须将关联的依赖排除掉，所以就用这个标签。
        另外这个＋s的，大家应该也明白 ，就是可以包含多个吗！！！！
    -->
    <exclusions>
        <exclusion>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
        </exclusion>
        <exclusion>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-annotations</artifactId>
    <version>${swagger.annotations.version}</version>
</dependency>
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-models</artifactId>
    <version>${swagger.models.version}</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>
<!-- swagger end -->
<!--lombok插件-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <!--
    optional 依赖传递的可选属性
    true 表示不传递依赖。
    但是，父子工程中，子工程也不会继承这个依赖吗？
    system 模块，xml 文件没有引入 lombok 插件，但有使用 lombok 的注解，
    optional 对父子工程不起作用
    http://www.360doc.com/content/17/0605/10/14808334_660126478.shtml
    -->
    <optional>true</optional>
</dependency>
```
## 添加 yml 配置
设置启动模式：`smpe-system/src/main/resources/config/application.yml`
```yaml
spring:
  profiles:
    active: dev
```
设置端口、swagger配置：`smpe-system/src/main/resources/config/application-dev.yml`
```yaml
server:
  port: 8080

swagger:
  enabled: true
  host: 127.0.0.1:${server.port}
  description: 一个简单且易上手的 Spring boot 后台管理框架
  title: SMPE-ADMIN 接口文档
  version: @project.version@
```
`smpe-system/src/main/resources/config/application-prod.yml`文件没有添加内容
## 添加配置类
获取yml文件swagger配置信息：`smpe-common/src/main/java/com/zhang/config/bean/SwaggerProperties.java`
```java
package com.zhang.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName; SwaggerProperties
 * @description: TODO Swagger配置，从yml读取
 * @Author: ZhangRenjie
 * @date: 2020/12/4 20:00
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /** 是否启用swagger */
    private Boolean enabled;

    /** 描述 */
    private String description;

    /** 标题 */
    private String title;

    /** 版本 */
    private String version;

    /** ip和host */
    private String swaggerHost;
}
```
配置swagger：`smpe-common/src/main/java/com/zhang/config/SwaggerConfig.java`
```java
package com.zhang.config;

import com.google.common.base.Predicates;
import com.zhang.config.bean.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName; SwaggerConfig
 * @description: TODO 类描述
 * @Author: ZhangRenjie
 * @date: 2020/12/3 20:03
 */
@RequiredArgsConstructor
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Bean
    @SuppressWarnings("all")
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnabled())
                .apiInfo(apiInfo())
                .host(swaggerProperties.getSwaggerHost())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhang"))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(),
                "http://www.marchsoft.cn/",
                new Contact("我想做阿信","https://blog.csdn.net/qq_42909053","1273206268@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()

        );
    }
}
```
## system工程依赖common工程
让`smpe-system`依赖`smpe-common`，获取swagger配置类的信息：`smpe-system/pom.xml`
```xml
<dependencies>
    <dependency>
        <groupId>com.zhang</groupId>
        <artifactId>smpe-common</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
## 运行效果
![](README.assets/678695a5.png)
# 准备数据库
数据库名 `smpe`

sql文件：
![](README.assets/ae1a5d82.png)

使用方法：
1. 打开 navicat 
2. 打开本地连接
3. 新建名为：`smpe`的库
4. 执行sql文件
5. 如图：

![](README.assets/f011123a.png)

![](README.assets/09b76d11.png)

![](README.assets/bb721c2e.png)

![](README.assets/214faf16.png)

![](README.assets/6507a221.png)

![](README.assets/8627d455.png)

![](README.assets/bc512d7d.png)

![](README.assets/ce351d24.png)

![](README.assets/db3c03fe.png)

![](README.assets/984bbf21.png)
# IDEA 安裝插件 
安装插件 `Paste lmagesinto into Markdown`
安装后，
1. 可直接通过 ctrl + c 把图片文件复插入到 md 文件（支持目录自定义），香！
2. 上传远程仓库（码云等）后，不需要手动更改，图片的插入地址，直接显示，香！
3. 目录设置成 `/{document_name}.assets` 之后，还可以适配 Typora ，香！

![](README.assets/29aac6f8.png)

![](README.assets/31ee6546.png)

效果如图：
![](README.assets/9119b803.png)
![](README.assets/e5b74f49.png)
# 初始化

## 新建父工程

新建父工程：`smpe-admin-learn`

![image-20201203164836278](README.assets/image-20201203164836278.png)

![image-20201203164904482](README.assets/image-20201203164904482.png)![image-20201203165048747](README.assets/image-20201203165048747.png)

![image-20201203165223472](README.assets/image-20201203165223472.png)

最终如图所示

![image-20201203165248153](README.assets/image-20201203165248153.png)

## 新建子模块

新建子模块：`smpe-common`

![image-20201203170327655](README.assets/image-20201203170327655.png)

![image-20201203170501662](README.assets/image-20201203170501662.png)

![image-20201203170536781](README.assets/image-20201203170536781.png)

结果如图：

![image-20201203170620688](README.assets/image-20201203170620688.png)

![image-20201203170639879](README.assets/image-20201203170639879.png)

同上再创建子模块`smpe-system`

结果如图：

![image-20201203171247415](README.assets/image-20201203171247415.png)

![image-20201203171304998](README.assets/image-20201203171304998.png)

## 添加 springboot 依赖

在父工程的 pom.xml 里边添加依赖

```xml
<!-- 所有的springboot的工程都以spring父工程为父工程 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.0.RELEASE</version>
</parent>

<dependencies>
    <!-- springboot web 启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

在子工程 `smpe-system` 中添加启动类

```java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName; AppRun
 * @description: TODO SpringBoot 启动类
 * @Author: ZhangRenjie
 * @date: 2020/12/3 17:18
 */
@SpringBootApplication
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
```

启动效果：

![image-20201203172242814](README.assets/image-20201203172242814.png)

浏览器访问测试：访问到页面，服务启动成功。

![image-20201203172309837](README.assets/image-20201203172309837.png)

![image-20201203172332247](README.assets/image-20201203172332247.png)

