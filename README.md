# filesync
这是一个基于java的监听文件夹内文件变化，并同步到ftp的工具。
## 1.配置
在filesync.jar同级目录新建文件夹config,并在该文件夹下新增配置application.yml     
配置内容如下
```yml
filesync:
  rootDir: C:\olivesrcfile\upload\ #监听本地文件夹
  interval: 1 #轮训间隔时间
  ftp:
    server: 0.0.0.0 #
    port: 21
    userName: abc
    userPassword: 123456
    path: \wwwroot\upload # ftp目录
    canDelete: false #是否可删除
```
