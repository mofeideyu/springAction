## String 命令

> SET key value [expiration EX seconds|PX milliseconds] [NX|XX]
* 参数说明：
    * EX second：设置键的过期时间为 second 秒。SET key value EX second 效果等同于 SETEX key second value
    * PX millisecond：设置键的过期时间为毫秒。SET key value PX millisecond 效果等同于 PSETEX key millisecond value
    * NX：只在键不存在时，才对键进行设置操作。SET key value NX 效果等同于 SETNX key value
    * XX：只在键已经存在时，才对键进行设置操作
> 用于设置 key 存储的值，当 key 已经存储了其他值时， SET 命令会将原值覆盖，重新设置新值
> 返回值：在 Redis 2.6.12 版本以前， SET 命令总是返回 OK 。从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 
> 如果设置了 NX 或者 XX 参数，但因为条件没达到而造成设置操作未执行，那么命令返回 NULL Bulk Reply
> set key1 value1 ex 60 nx

> SETNX key value
> Redis SETNX 命令，当且仅当给定的 key 不存在的时候，才创建 key，并为其设置 value 值。如果 key 已经存在，则命令执行失败
> 返回值：设置成功，返回 1；设置失败，返回 0 
> setnx name redis -> 1
> setnx name redis -> 0

> STRLEN key
> 返回 key 所储存的字符串值的长度。当 key 储存的不是字符串值时，返回一个错误
> set name redis
> strlen name -> 5

> SETEX key seconds value
> Redis SETEX 命令为 key 设置 value 值，并将 key 的过期时间设为 seconds (以秒为单位)。如果 key 已经存在，那么将覆盖 key 原来的值
> SETEX 命令与 PSETEX 命令类似，不过后者以毫秒(milliseconds)为单位
> 返回值：设置成功时返回 OK，若 second 参数不符合要求，则会返回一个错误，比如设置成了负数或者浮点数
> set name redis
> setex name 60 java -> OK
> ttl name -> 45(45s后过期)
> persist name -> OK
> psetex name 20000 redis -> OK
> ttl name -> 16(16s后过期)

> APPEND key value
> Redis APPEND 命令用于为指定的 key 追加值
> 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。当 key 不存在时，它就为这个 key 设置 value 值，等同于 SET key value 操作
> set name redis
> append name -java -> redis-java

> setbit key offset value
> Redis SETBIT 命令用于对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。位的设置或清除取决于 value，可以是 0 或者是 1
> 当 key 不存在时，自动生成一个新的字符串值。字符串会进行伸展以确保它可以将 value 保存在指定的偏移量上。当字符串值进行伸展时，空白位置以 0 填充。offset 参数必须大于或等于 0 ，小于 2^32 (bit 被限制在 512 MB 之内)
> set animal big
> getbit animal 0 -> 0
> getbit animal 1 -> 1
> getbit animal 7 -> 0
> setbit animal 7 1 -> 0
> get animal cig

|  -  |  b  |   i  |  g  |
|---- | ----| ---- |---- |
| 二进制数 | 01100010 | 01101001 | 01100111 |
|  索引位  |  0~7     | 8~15     |  16~23   |
|  字节    | 第0个字节 | 第1个字节 | 第2个字节 |

> BITCOUNT key [start end]
> 单位为字节
> 返回被设置为 1 的比特位数量
> bitcount animal 0 0 -> 3 (表示：第0个字节有3个1)
> bitcount animal 0 1 -> 7 (表示：第0,1个字节有7个1)
> bitcount animal 0 2 -> 12 (表示：第0~2个字节有12个1)

> DECR key
> Redis DECR 命令对 key 中存储的数值做减 1 操作。
> 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
> 返回值：执行 DECR 命令减 1 操作后 key 的值
> set num 10
> decr num -> 9

> DECRBY key increment
> Redis DECRBY 命令对 key 中存储的数值做递减运算
> 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
> set num 10
> decr num -> 9
> decrby num 5 -> 4

> GETRANGE key start end
> Redis GETRANGE 命令返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。负数偏移量表示从字符串末尾开始计数，-1 表示最后一个字符，-2 表示倒数第二个，以此类推
> GETRANGE 子字符串的长度大小(range)不能超过实际字符串的长度
> set name redis
> GETRANGE redis 0 2 -> red

> GETSET key value
> 将给定 key 的值设置为 value，并且返回 key 的旧值(old value)
> 返回值：返回给定 key 的旧值，若 key 不存在则返回 null；当 key 存在但不是字符类型是，返回一个错误
> getset name redis -> null
> getset name java -> redis

> INCR key
> 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
> 返回值：执行 INCR 命令加 1 操作后 key 的值
> get num -> 4
> incr num -> 5

> INCRBy key increment
> Redis INCRBY 命令对 key 中存储的数值做增量运算
> 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 操作。如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
> get num -> 5
> incrby num 5 -> 10

> INCRBYFLOAT key increment
> Redis INCRBYFLOAT 命令对 key 中存储的数值加上浮点数增量
> 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作。如果命令执行成功，那么 key 的值会被更新为新值，并且新值会以字符串的形式被返回
> 无论是 key 存储的值，还是 increment 增量值，它们都可以使用指数符号来表示，比如 2.0e7 、 3e5 、 90e-2
> 返回值：加上 increment 浮点数后， key 存储的数值
> get num -> 10
> incrbyfloat num 1.5 -> 11.5
> incrbyfloat num 1.5 -> 13

> MSET key value [key value ...]
> 用于同时设置一个或多个 key-value 键值对
> 返回OK
> MGET key [key ...]
> Redis MGET 命令返回一个或多个给定 key 的值
> 返回值：返回所有 key 存储的 value 值
> mset a valuea b valueb -> OK
> mget a b -> valuea valueb

> MSETNX key value [key value ...]
> Redis MSETNX 命令用于所有给定 key 都不存在时，同时设置一个或多个 key-value 键值对。该命令具有原子性，它执行结果只有两种：全部成功或者全部失败
> 返回值：当所有 key 都成功设置时，返回 1，如果其中至少一个 key 已经存在，那么将设置失败，此时会返回 0
> msetnx a valuea b valueb -> 1
> msetnx c valuec b valuebb -> 0

