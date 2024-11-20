# JRASP
一个Java运行时防御安全工具，方便研究学习RASP使用

# 安装&使用

+ 编译成jar包
```shell
mvn clean install
mvn package
```
+ 指定需要防护程序的JVM参数
```shell
-javaagent:/path/to/javaopenrasp.jar
```
