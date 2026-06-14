public class Main {
    public static void main(String[] args) {
        //NOTE Redis是一个key-value数据库，key是String类型，value有不同的数据结构
        //    redis-value五种基本数据结构：
        //    ┌────────────┬──────────┬──────────────────────────────┬──────────────────────┐
        //    │ 类型       │ 分类     │ 示例                         │ 说明                 │
        //    ├────────────┼──────────┼──────────────────────────────┼──────────────────────┤
        //    │ String     │ 基本类型 │ "hello world"                │ 字符串 / 数字 / JSON  │
        //    │ Hash       │ 基本类型 │ {name: "Jack", age: 21}      │ 类似 Java 的 Map      │
        //    │ List       │ 基本类型 │ [A -> B -> C -> C]           │ 有序，可重复          │
        //    │ Set        │ 基本类型 │ {A, B, C}                    │ 无序，不可重复        │
        //    │ SortedSet  │ 基本类型 │ {A: 1, B: 2, C: 3}           │ 有序，不可重复，带分数 │
        //    │ GEO        │ 特殊类型 │ {A: (120.3, 30.5)}           │ 地理位置坐标          │
        //    │ BitMap     │ 特殊类型 │ 0110110101110101011          │ 位图，适合签到/状态记录│
        //    │ HyperLogLog│ 特殊类型 │ 0110110101110101011          │ 基数统计，估算去重数量 │
        //    └────────────┴──────────┴──────────────────────────────┴──────────────────────┘


        // NOTE String类型
        //  String类型（字符串类型），是Redis中最简单的存储类型
        //  根据字符串的格式不同，可以分为三类：
        //  string：普通字符串
        //  int：整数类型，可以做自增，自减操作
        //  float：浮点类型，可以做自增，自减操作
        //  不管哪种格式，底层都是字节数组的形式存储，只是编码方式不同
        //  =====================================================
        //  String的常见命令有：
        //  SET：添加或者修改已经存在的一个String类型的键值对
        //  GET：根据key获取String类型的value
        //  MSET：批量添加多个String类型的键值对
        //  MGET：根据多个key获取多个String类型的val
        //  INCR：让一个整型的key自
        //  INCRBY：让一个整型的key自增并指定步长，例如：incrby num 2 让num值自
        //  INCRBYFLOAT：让一个浮点类型的数字自增并指定
        //  SETNX：添加一个String类型的键值对，前提是这个key不存在，否则不
        //  SETEX：添加一个String类型的键值对，并且指定有效期(SETEX 非常适合做验证码，因为它既能存 key-value，又能自动过期，
        //  而 Redis 基于内存，读写速度很快，很适合这种短时间、高频、临时性的数据。)

        //NOTE key的结构
        //     Redis的key允许有多个形成层级结构，多个单词之间用";"隔开
        //     格式：项目名:业务名:类型:id
        //     这个格式并非固定（可以根据自身需求删除或者添加词条）
        //     eg.user相关的key：heima:user:1
        //    ===================================
        //    如果 Value 是一个 Java 对象，例如 User 对象，
        //    则可以将对象序列化为 JSON 字符串后存储到 Redis 中（问题：没有办法单独修改对象
        //    的某个属性，解决方法：用Hash结构）
        //    +------------------+------------------------------------------------+
        //    |       KEY        |                     VALUE                      |
        //    +------------------+------------------------------------------------+
        //    | heima:user:1     | {"id":1, "name":"Jack", "age":21}             |
        //    | heima:product:1  | {"id":1, "name":"小米11", "price":4999}       |
        //    +------------------+------------------------------------------------+

        //NOTE Hash类型
        // Hash类型（散列），其value是一个无序字典，类似于Java中HashMap结构
        /*   Redis Hash 结构示例
         * Hash 结构可以将对象中的每个字段独立存储，
         * 因此可以针对单个字段进行 CRUD 操作。
         * ┌──────────────┬────────────┬────────────┐
         * │ KEY          │ field      │ value      │
         * ├──────────────┼────────────┼────────────┤
         * │ heima:user:1 │ name       │ Jack       │
         * │              │ age        │ 21         │
         * ├──────────────┼────────────┼────────────┤
         * │ heima:user:2 │ name       │ Rose       │
         * │              │ age        │ 18         │
         * └──────────────┴────────────┴────────────┘
         */

        //NOTE Hash常见命令：
        //  Hash的常见命令有：
        //  HSET key field value：添加或者修改hash类型key的field的值(现在也可以批量插入）
        //  HGET key field：获取一个hash类型key的field的值
        //  HMSET：批量添加多个hash类型key的field的值
        //  HMGET：批量获取多个hash类型key的field的值(deprecated，已经被淘汰，功能被HSET替代)
        //  HGETALL：获取一个hash类型的key中的所有的field和value
        //  HKEYS：获取一个hash类型的key中的所有的field
        //  HVALS：获取一个hash类型的key中的所有的value
        //  HINCRBY：让一个hash类型key的字段值自增并指定步长
        //  HSETNX：添加一个hash类型的key的field值，前提是这个field不存在，否则不执行

        //NOTE Hash常见应用场景：
        //   用户缓存
        //   购物车
        //   登录 token 信息
        //   文章统计信息
        //   表单草稿
        //   商品简略信息缓存
        //   房间/桌台临时状态

        //NOTE List类型
        // Redis中的List类型与Java中的LinkedList类似，可以看做是一个双向链表结构。
        // 支持正向和反向检索，特点于LinkedList类似：
        // 1.有序 2.元素可以重复 3.插入和删除快 4.查询速度一般
        // 常见应用场景：朋友圈点赞列表、评论区列表
        // ┌──────────────┬──────────────────────────────┬──────────────────────────────┐
        // │ 命令         │ 作用                         │ 示例                         │
        // ├──────────────┼──────────────────────────────┼──────────────────────────────┤
        // │ LPUSH        │ 向列表左侧插入一个或多个元素 │ LPUSH users jack rose         │
        // │ LPOP         │ 移除并返回列表左侧第一个元素 │ LPOP users                    │
        // │ RPUSH        │ 向列表右侧插入一个或多个元素 │ RPUSH users tom lucy          │
        // │ RPOP         │ 移除并返回列表右侧第一个元素 │ RPOP users                    │
        // │ LRANGE       │ 返回指定下标范围内的元素     │ LRANGE users 0 -1(获取整个列表)│
        // │ BLPOP        │ 阻塞式从左侧弹出元素         │ BLPOP users 100               │
        // │ BRPOP        │ 阻塞式从右侧弹出元素         │ BRPOP users 100               │
        // └──────────────┴──────────────────────────────┴──────────────────────────────┘
        // 入口和出口同一边可以模拟栈，入口和出口不在同边可以模拟队列
        // 入口和出口在不同边、出队时采取BLPOP或BRPOP


        //NOTE Set类型
        // Redis的Set类型与java中的HashSet类似，可以看做是一个value为null的HashMap
        // 因为为hash表，所以具备与hashset类似的特征：
        // 无序、元素不可重复、查找快、支持交集、并集、差集等功能
        // set常见命令
        //   单个集合操作
        //   SADD key member ...：向set中添加一个或多个元素
        //   SREM key member ...：移除set中的指定元素
        //   SCARD key：返回set中元素的个数
        //   SISMEMBER key member：判断一个元素是否存在于set中
        //   SMEMBERS：获取set中的所有元素
        // 多个集合操作
        //   SINTER key1 key2...：求key1和key2的交集
        //   SDIFF key1 key2 求key1与key2的差集（key1中有但key2中没有）
        //   SUNION key1 key2 求key1与key2的并集

        //NOTE SortedSet类型
        // Redis的SortedSet类型是一个可排序的Set集合，与Java中的TreeSet类似
        // 但底层数据结构却差别很大，SortedSet中的每一个元素都带有一个score属性
        // 可以基于score属性对元素进行排序，底层的实现是一个跳表(SkipList)加hash
        // 表。SortedSet具备下列属性：1.可排序2.元素不重复3.查询速度快
        // 常见应用场景：有序数据、范围查询、排行榜、按分数排序
        // 跳表：给有序列表加了多层快速通道的有序列表

        //NOTE SortedSet常见命令：
        // SortedSet的常见命令有：
        // ZADD key score member：添加一个或多个元素到sorted set，如果已经存在则更新其score值
        // ZREM key member：删除sorted set中的一个指定元素
        // ZSCORE key member：获取sorted set中的指定元素的score值
        // ZRANK key member：获取sorted set中的指定元素的排名
        // ZCARD key：获取sorted set中的元素个数
        // ZCOUNT key min max：统计score值在给定范围内的所有元素的个数
        // ZINCRBY key increment member：让sorted set中的指定元素自增，步长为指定的increment值
        // ZRANGE key start stop
        // 按照score排序后，获取指定排名范围内的元素
        // ZRANGEBYSCORE key min max：按照score排序后，获取指定score范围内的元素
        // ZDIFF、ZINTER、ZUNION：求差集、交集、并集
        // 注意：所有的排名默认都是升序，如果要降序则在命令的Z后面添加REV即可



    }
}
