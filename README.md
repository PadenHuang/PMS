### 绩效管理系统-PMS
@[TOC]
#### 一、项目介绍
1. 项目背景
   该项目的开发是为了方便部门领导对员工的绩效进行直接登记，方便高效。

2. 技术框架
   前后端不分离，前端采用jsp+css+js+jquery+layui，后端采用四层架构，逐层依赖（servlet+service+model+dao）

3. 演示例图
**登录**
![image-20230227193022912](https://github.com/PadenHuang/PMS/blob/master/img/image-20230227194626652.png) 
**功能页面**
![image-20230227194626652](https://github.com/PadenHuang/PMS/blob/master/img/image-20230227194626652.png)     

#### 二、功能

#### 三、使用(系统环境参数，部署要素，操作说明等)
JDK8、Tomcat8

#### 四、目录结构说明
```
├─.idea
├─src     //源代码
│  └─main
│      ├─java  //后台
│      │  └─com
│      │      └─hwq
│      │          └─company
│      │              ├─department  //部门模块
│      │              ├─employee  //员工模块
│      │              ├─init  //系统初始化文件
│      │              ├─project //项目模块
│      │              ├─score  //绩效模块
│      │              └─util  //封装工具
│      ├─resources 
│      └─webapp  //前台
│          ├─web
│          │  ├─base  //样式文件
│          │  │  ├─css
│          │  │  ├─img
│          │  │  ├─js
│          │  │  └─layui
│          │  └─page  //页面
│          │      ├─department
│          │      ├─employee
│          │      ├─project
│          │      └─score
│          └─WEB-INF //配置文件
```

#### 五、版本更新&debug

**版本**

- [x] 版本V1.0
- [ ] 版本V2.0

**debug**
1.数据库连接失败 页面拿不到数据
2.响应失效 jsp无法提交数据给servlet
3.servlet访问失败——404`

































