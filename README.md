

# smpe-admin-learn

#### 介绍
学习开源框架：https://github.com/shiwei-Ren/smpe-admin

前端框架暂时不打算学习（https://github.com/shiwei-Ren/smpe-admin-web）



# 初始化

## 新建父工程

新建父工程：`smpe-admin-learn`

![image-20201203164836278](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-admin-learn/README.assets/image-20201203164836278.png)

![image-20201203164904482](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-admin-learn/README.assets/image-20201203164904482.png)![image-20201203165048747](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-admin-learn/README.assets/image-20201203165048747.png)

![image-20201203165223472](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-admin-learn/README.assets/image-20201203165223472.png)

最终如图所示

![image-20201203165248153](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-admin-learn/README.assets/image-20201203165248153.png)

## 新建子模块

新建子模块：`smpe-common`

![image-20201203170327655](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-common/README.assets/image-20201203170327655.png)

![image-20201203170501662](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-common/README.assets/image-20201203170501662.png)

![image-20201203170536781](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-common/README.assets/image-20201203170536781.png)

结果如图：

![image-20201203170620688](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-common/README.assets/image-20201203170620688.png)

![image-20201203170639879](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-common/README.assets/image-20201203170639879.png)

同上再创建子模块`smpe-system`

结果如图：

![image-20201203171247415](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-system/README.assets/image-20201203171247415.png)

![image-20201203171304998](https://gitee.com/sy_zrj/smpe-admin-learn/raw/create-smpe-system/README.assets/image-20201203171304998.png)

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

![image-20201203172242814](G:\all-workspace\all-project\小云通知\框架\smpe-admin-learn\README.assets\image-20201203172242814.png)

浏览器访问测试：访问到页面，服务启动成功。

![image-20201203172309837](G:\all-workspace\all-project\小云通知\框架\smpe-admin-learn\README.assets\image-20201203172309837.png)

![image-20201203172332247](G:\all-workspace\all-project\小云通知\框架\smpe-admin-learn\README.assets\image-20201203172332247.png)

