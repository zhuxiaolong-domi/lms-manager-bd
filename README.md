# LMS-backend

## 软件项目管理后端

### 拉下项目配置maven即可启动，测试程序在Controller中的HelloController中。运行**LmsApplication**即可。

### application.properties需要添加的东西
```## MULTIPART (MultipartProperties)
# 开启 multipart 上传功能
spring.servlet.multipart.enabled=true
# 文件写入磁盘的阈值
spring.servlet.multipart.file-size-threshold=2KB
# 最大文件大小
spring.servlet.multipart.max-file-size=200MB
# 最大请求大小
spring.servlet.multipart.max-request-size=215MB

## 文件存储所需参数
# 所有通过 REST APIs 上传的文件都将存储在此目录下
file.upload-dir=F:\\report```
