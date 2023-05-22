## 键（Key）命令
> DEL key [key ...]
> 返回值： 命令执行后的返回值表示被删除 key 的数量
> DEL key1 key2  -> 2

> DUMP key
> 指定的 key 做序列化处理,并返回被序列化的值
> 返回值：key 存在时返回列化之后的值，否则，返回null
> SET web_url www.biancheng.net
> DUMP web_url
> "\x00\x11www.biancheng.net\t\x00\x9f\xddk\xe4\xe3\xef\xd7|"

> EXISTS key [key ...]
> 检查指定的一个 key 或者多个 key 是否存在
> 返回值：命令的返回值代表 key 存在的数量，如果被检查的 key 都不存则返回 0
> set key1 value1
> exists key1 -> 1
> exists key1 key2 -> 1

> EXPIRE key seconds
> 用于设置 key 的过期时间,以秒为单位,当 key 过期后将不能再使用
> 返回值：设置成功时返回 1，当 key 不存时则返回 0
> set key2 value2
> expire key2 10 -> 1
> expire key3 10 -> 0

> EXPIREAT key timestamp
> 用于设置过期时间，与 EXPIRE 不同，它以 UNIX 时间戳格式来设置 key 的过期时间
> 返回值：设置成功时返回 1，当 key 不存时则返回 0
> expireat key2 1611831986 -> 1
> expireat key3 1611831986 -> 0

> KEYS pattern
> 用于查找所有符合指定模式（pattern）的 key。在线上上产环境，该命令被禁止使用，一次性查询大量的 key，会导致服务性能受到影响
> 返回值：返回所有符合要求的 key
> set key1 value1
> set key2 value2
> set key3 value3
> keys key* -> key1 key2 key3

> MOVE key db
> Redis MOVE 命令用于将当前数据库中的 key 移动到其他数据库中，Redis 共有 16 个数据库，默认当前数据库为 0 库。
> 如果当前数据中要移动的 key ，在目标数据库中存在相同的 key，那么将移动失败
> 返回值：移动成功返回 1，如果移动失败则返回 0
> select 0 -> OK
> set key1 value1
> move key1 1
> exists key1 -> 0
> select 1 -> OK
> exists key1 -> 1

> TTL key
> 查看 Key 剩余的过期时间，以秒为单位
> 返回值：返回 key 所剩余生存时间。若 key 不存在时，返回 -2；若 key 存在但没有设置剩余生存时间时，返回 -1
> exists key2 -> 1
> ttl key2 -> -1
> expire key2 60
> ttl key2 -> 57 (57s后过期)
> ttl key2 -> -2 (不存在)

> PERSIST key
> 用于移除指定 key 的过期时间，这样 key 将永不过期
> 返回值：过期时间移除成功时，则返回 1 。 如果 key 不存在或者 key 没有设置过期时间，则返回 0
> set key2 value2
> ttl key2 -> -1
> expire key2 60
> ttl key2 -> 47 (47s后过期)
> persist key2 -> 1
> ttl key2 -> -1

> PEXPIRE ke milliseconds
> 与 EXPIRE 命令作用类似，用于设置 key 的过期时间，以毫秒为单位
> 返回值：设置成功时返回 1，当 key 不存时则返回 0
> set key2 value2
> pexpire key2 10000 (设置10000ms后过期)
> ttl key2 -> 7 (7s后过期)

> RANDOMKEY
> 用于从当前数据库中随机返回一个 key
> 返回值：当数据库不为空时，返回一个 key；当数据库为空时返回 null

> RENAME key newKey
> 用于修改 key 的名称(若newKey名称已存在,会替换掉已存在的值)
> 修改成功是返回 OK，如果修改失败则返回一个错误，错误一般有两种情况，一是被修改的 key 不存在，二是 key 与 newkey 名字相同
> set key value
> set key2 value2
> rename key2 key -> OK
> get key -> value2

> SCAN cursor [MATCH pattern] [COUNT count]
>   cursor：游标，游标起始值一般为 0
    pattern： 指定匹配模式
    count：指定从数据库中返回多少个 key，默认为 10
> SCAN 命令是一个基于游标的迭代器，每次被调用之后，都会向用户返回一个新的游标，
> 用户在下次迭代时需要使用这个新游标作为 SCAN 命令的游标参数，从而让迭代过程延续下，当游标返回  0 时，迭代结束
> 返回值：SCAN 返回一个包含两个元素的数组， 第一个元素是用于进行下一次迭代的新游标， 而第二个元素则是一个数组， 这个数组中包含了所有被迭代的元素
> scan 0 match key* count 5 -> 6 key3

> TYPE key
> 用于返回 key 所储存的值的类型
> 返回值：返回 key 的数据类型，比如 string、list、set、hash、zset 等，若果返回 none，则表明不存在该 key
> type key -> string
