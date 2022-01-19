# maven
### maven 核心概念
1. POM： 指的是 pom.xml 文件，是项目对象模型的意思
2. 约定的目录结构： maven 项目的目录和文件的位置都是规定的
3. 坐标： 是一个唯一的字符串，用来表示资源
4. 依赖管理： 项目的jar 的管理
5. 仓库管理（了解）：资源存放的位置
6. 生命周期（了解）：构建项目的过程
7. 插件和目标（了解）： 执行 maven 构建的时候用的工具是插件
8. 继承
9. 聚合
### maven 安装和配置
1. 下载安装文件：https://downloads.apache.org/maven/maven-3/3.3.9/binaries/（JDK1.8 一般用 3.3.9版本）
2. 解压文件
3. 配置环境变量
    - 在系统变量中，指定一个 M2_HOME 名称，指定到解压好的 maven 目录里边的 bin目录
    - 例如：`M2_HOME=D:\JAVA\maven-3.3.9`
    - 在系统变量中的Path 新建一个 `%M2_HOME%\bin` 做完映射保存
    - 如果不行，可以将全路径加入到path 中 `D:\JAVA\maven-3.3.9\bin`(我就遇到这种问题)
### maven 约定的目录结构
```md
Hello
  |---src
  |---|---main
  |---|---|---java      # 放程序
  |---|---|---resources # 放java程序中的使用的配置文件
  |---|---test
  |---|---|---java
  |---|---|---resources
  |---pom.xml   // 核心文件，必须有目录
```
1. maven 执行时，在项目的根目录生成 target 目录文件，是maven编译的java程序，最后的class文件都放在这里
2. 默认本地仓库位置：C:\Users\Admin\.m2\repository
3. 修改默认目录：
    - 修改maven 配置文件， maven 安装目录/conf/settings.xml(修改前先备份)
    - 修改 `<localRepository>/path/to/local/repo</localRepository>` 指定自己需要放的目录（不要用中文目录）
    - 例如：`<localRepository>D:/JAVA/maven_repository</localRepository>` 记得不要放在注解里面了
4. pom： 项目对象模型，是一个pom.xml文件
    1）坐标：唯一值，互联网中唯一的坐标
        ```xml
            <groupId>公司域名的到写</groupId>
            <artifactId>自定义项目名称</artifactId>
            <version>自定义版本（如：1.0.0）</version>
        ```
        - https://mvnrepository.com/ 搜索使用，中央仓库
    2）packaging： 打包后的压缩文件扩展名，默认是jar， web应用是 war，不写是jar
    3）依赖：
        - dependencies 和 dependency
        - 项目中使用的各种资源说明，比如需要到mysql8.0的驱动
            ```xml
                <!-- 依赖 -->
                <dependencies>
                    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.24</version>
                    </dependency>
                </dependencies>    
            ```
        - 
    4）properties： 配置属性
    5）build： maven 进行构建时，配置信息，比如打包时jdk的指定版本
### maven 的常用命令
1. mvn clean 清除（把原来编译好的 target 清除掉）
2. mvn compile 编译主程序
3. mvn test-compile 编译测试程序
4. mvn test 测试
5. mvn package 打包主程序
6. mvn install 安装主程序
7. mvn deploy 部署主程序，（把工程打包，按照工程坐标保存到本地仓库中，并保存到私服库中，还会自动把项目部署到web 容器中）
### build：配置
```xml
 <build>
    <!-- 配置插件 -->
    <plugins>
        <plugin>
            <groupId>org.ahache.maven.plugins</groupId>
            <!-- 插件的名称 -->
            <artifactId>maven-compiler-plugin</artifactId>
            <!-- 插件版本 -->
            <version>3.8.1</version>
            <!-- 配置插件的信息 -->
            <configuration>
                <!-- 告诉maven 代码是在jdk1.8上编译的 -->
                <source>1.8</source>
                <!-- 程序应该在jdk1.8 上运行-->
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins> 
 </build>
```