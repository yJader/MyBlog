# MyBlog
基于[三更草堂的博客项目](https://www.bilibili.com/video/BV1hq4y1F7zk) 完成的博客
- 前端项目由三更草堂给出
- 后端前台部分 : 仿照原项目代码完成
- 后端后台部分 : 根据接口文档要求自行编写

## 一些奇怪的想法
1. ```java
   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   ```
   写起来实在是太麻烦了
   - 使用自定义注解, 组合这三个注解
     - 但是会降低代码可读性 
     - 笑死 注解全忘了
   - 写一个idea代码模板(现在用的)


## 不足
### 有能力, 但是咕咕
- 接口文档(Swagger)摆烂了, 懒得写了, 反正前端不是我_(:з」∠)_
- 分页功能考虑不周, 如输入
- 前台部分的数据验证没有做, 部署前完成
- 图床迁移到阿里云
### 数据库部分
1. id使用自增存储
    - 改进 : [9种分布式id生成方式](https://zhuanlan.zhihu.com/p/152179727) 雪花算法

## bug记录
1. JRebel在修改vo后无法进行热部署
   - 但是单纯的vo是可行的
2. 经常接受前端参数后出现NPE, 极大可能是忘记加`@RequestBody`了
3. 想要一起上传前端的项目, 但是gitignore有问题
   ```
   /SGBlog/*
   !/SGBlog/resources/front-end-project/*
   ```
   咋回事啊, 啥情况啊, 这咋不对啊
4. ```
   Error response from daemon: failed to create shim task: OCI runtime create failed: runc create failed: unable to start container process: error during container init: error mounting "/app/mysql/conf/my.cnf" to rootfs at "/etc/my.cnf": mount /app/mysql/conf/my.cnf:/etc/my.cnf (via /proc/self/fd/6), flags: 0x5000: not a directory: unknown: Are you trying to mount a directory onto a file (or vice-versa)? Check if the specified host path exists and is the expected type
   ```
   docker启动服务失败啊啊啊啊啊
   

## 来点乐子
1. 关于写Dto和Vo
   为了正确和前端对接, Dto和Vo必须按照给定的接口文档的属性来书写。
   但是怪的是, 接口文档属性的排序和实体类属性的排序差别很大, 想要一一对应的话十分费眼。
   因此采用了极为先进的ChatGpt来帮助我们检查需要删去实体类的哪个属性来匹配接口要求。
2. 有一说一 后台管理大部分都是crud, 唯一点含金量的就是自己写的建立菜单树的"算法"
   - 但是这么简单的玩意也配叫算法吗_(:з」∠)_
   