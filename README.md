# SDH-Bot

#### 介绍
使用go-cqhttp+SpringBoot创建的QQ机器人，目前功能有随机setu，群抽奖（完善中）。
#### 软件架构
软件架构说明
使用go-cqhttp+SpringBoot创建。数据库使用MySQL。

#### 安装教程
1. 下载最新版[go-cqhttp](https://docs.go-cqhttp.org/)，编辑配置文件，确保HTTP和正向WS配置和下方一致。

```
- ws:
      # 正向WS服务器监听地址
      host: 127.0.0.1
      # 正向WS服务器监听端口
      port: 6700
      middlewares:
        <<: *default # 引用默认中间件
- http:
      # 服务端监听地址
      host: 127.0.0.1
      # 服务端监听端口
      port: 5700
      # 反向HTTP超时时间, 单位秒
      # 最小值为5，小于5将会忽略本项设置
      timeout: 5
      # 长轮询拓展
      long-polling:
        # 是否开启
        enabled: false
        # 消息队列大小，0 表示不限制队列大小，谨慎使用
        max-queue-size: 2000
      middlewares:
        <<: *default # 引用默认中间件
```
2. 导入数据库文件。
3. Clone项目，使用IDEA启动即可，也可打包运行。
