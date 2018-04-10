sr-core //整个后端的根项目
    sr-admin //管理端
        knowledge
            controller
                ExpandKnowledgeController
            service
                ExpandKnowledgeServiceImpl //管理端的ExpandKnowledgeService实现
    sr-model //公共模块
        domain //实体包
            knowledge //
                ExpandKnowledge

        repository //数据操作包
            knowledge
                ExpandKnowledgeRepository //对拓展知识的增删改查
    sr-web  //学生端


//后端技术选型 1.springboot + jpa + springmvc
springboot 配置框架 自动配置 application.yml
jpa repository
springmvc controller


//数据库基本规范
1.新增一个拓展知识的时候 他的delete_at是空
2.删除一个拓展知识 将core_expand_knowledge表里的delete_at设为当前时间
3.delete_at空决定了这个拓展知识存在 非空表示不存在




//整个请求过程

//axios ->aspect //检测用户是否登录
axios -> aspect -> controller -> service -> repository
ajax -> controller -> service -> repository(操作数据库 进行增删改查)

// service  //业务逻辑层
1.以impl结尾
2.不以impl结尾的
exp:
ExpandKnowledgeServiceImpl //ExpandKnowledgeService的实现类
 ExpandKnowledgeService //接口

 //repository  //数据操作层(数据库的增删改查)
1.所有repository都继承与JpaRepository接口
JpaRepository<Table,id> -> JpaRepository<ExpandKnowledge,Long>
2.方法映射sql语句
    //select * from core_expand_knowledge where id = ? and delete_at is null
    01:ExpandKnowledge findByIdAndDeleteAtIsNull(Long id); //找到一个拓展知识根据id找

    // select * from core_expand_knowledge where delete_at is null limit 0,10
    02:Page<ExpandKnowledge> findAllByDeleteAtIsNullOrderByCreateAtAs(Pageable pageable) //找到所有拓展知识 分页获取
        分页需要两个关键数据 1.size //10 表示显示10条 2.page 0 第一页 2 第二页
        limit o1,o2 数据库里有20条拓展知识 limit 10,20 就表示过滤前十条 取从第10条开始的20条数据 o2:size o1: size * (page + 1)
        传参Pageable ->有两个属性 page,size
        返回 -> Page<ExpandKnowledge> {
                                            List<ExpandKnowledge> content //要获取的数据
                                            total //整个数据库的拓展知识数
                                      }



//数据库
所有表的表名前缀都是 core_
core_user //用户表 -> 用户名和密码 level 区分他是系统 教师 学生 1 2 3
//登录过程中 根据level判断用户身份  -> 系统 教师 学生

管理端:只能系统和教师登录的 sr-admin
学生端: 学生登录 sr-student



//父亲与儿子



知识列表 组件 1

专题 组件  2


1 没有父亲的时候 基础知识
2 -> 1
1进阶知识

四个项目
1. sr-admin 管理端的后端
2. sr-web 学生端的后端

sr-vue 学生端的前端
src-web 管理端的前端



前端 -> 后端
前端 发送 request
后端 接受 request ,并且处理 然后返回一个response
request里面包含 路径 + 请求参数
response里面包含 数据





























