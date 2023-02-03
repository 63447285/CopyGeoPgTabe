# Atlas GHPC 服务型算子示例程序

## 说明

1. 插件注册相关信息位于`doc/DemoRestPlugin1.0.0`文件夹
2. 程序运行需要一个xml文件路径参数，xml文件为hpc传递算子参数文件示例如`doc/e9d4ac7a-6651-4bc1-b407-aef116f4d972.xml`
3. xml文件中必须包含`serverPort`与`zkUri`参数
4. 服务启动后，在zk路径/atlas/plugins下注册服务名称，如：DemoRestService-v1.1.0:8811，"DemoRestService-v1.1.0"前面用于标识服务名称，用于多队列调度时辨识是哪个服务(需要与服务插件注册时的entrypoint保持一致，如entrypoint值为zookeeper:/atlas/plugins/DemoRestService-v1.1.0,那么该服务型插件的http请求都发送至zk注册服务名称":"前面为"DemoRestService-v1.1.0"的服务)，"8811"是startup算子的serverPort参数，表示服务占用的端口(所有服务端口都应不一致，避免出现zk注册服务地址名称相同的情况)
