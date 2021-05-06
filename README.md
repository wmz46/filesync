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
    server: 0.0.0.0 #ftp服务器地址
    port: 21 #ftp端口
    userName: abc #ftp用户名
    userPassword: 123456 #ftp用户密码
    path: \wwwroot\upload # ftp目录
    canDelete: false #本地文件删除是否同步删除ftp上文件
```
## 2.打包
```cmd
mvn package
```
## 3.启动
```cmd
java -jar filesync.jar
```
## 4.todo list
-  [ ] 1.首次如何同步已存在文件
-  [ ] 2.改成web项目，可以通过可视化管理文件，类似BCompare的文件夹比对
