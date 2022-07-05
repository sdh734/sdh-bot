# SDH-Bot

#### 介绍

使用go-cqhttp+SpringBoot创建的QQ机器人，目前功能有随机setu，群抽奖（完善中）、天气查询（需要自行申请高德地图及彩云天气Api）、必应壁纸、WallHaven壁纸（需自行申请User_Key）。

#### 软件架构

软件架构说明
使用go-cqhttp+SpringBoot创建。数据库使用MySQL。

#### 安装教程

1. 下载最新版[go-cqhttp](https://docs.go-cqhttp.org/)，编辑配置文件，自行修改账号密码（如果没有配置文件，请先运行一遍程序，自动生成配置文件后再行修改），确保servers配置和下方一致。

```
    - http:
      address: 0.0.0.0:5700 # HTTP监听地址
      timeout: 30 # 反向 HTTP 超时时间, 单位秒，<5 时将被忽略
      long-polling: # 长轮询拓展
        enabled: false # 是否开启
        max-queue-size: 2000 # 消息队列大小，0 表示不限制队列大小，谨慎使用
      middlewares:
        <<: *default # 引用默认中间件
    # 正向WS设置
    - ws:
      address: 0.0.0.0:6700 # 正向WS服务器监听地址
      middlewares:
        <<: *default # 引用默认中间件
```

2. 导入数据库文件。
3. Clone项目，自行修改Config\ApiKeyConfig中的各项Api_Token。
4. 运行go-cqhttp，运行项目。
