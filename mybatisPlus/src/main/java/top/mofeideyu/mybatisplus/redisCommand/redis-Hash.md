## Hash命令

> HSET key field value
> Redis HSET 命令用于将哈希表 key 中的字段 field 的值设为 value。如果 key 不存在，将自动创建一个新的哈希表并进行 HSET 操作。
> 如果 field 已经存，那么旧值将被覆盖。HSET 命令与 HMSET 命令相似，后者可以同时存储多个字段和字段值
> 返回值：如果 field 是哈希表中的一个新字段，并且值设置成功，则返回 1；如果哈希表中 field 已经存在，并且旧值已被新值覆盖，则返回 0
> hset key key1 value1 key2 value2 -> 2
> hset key key3 value3 -> 1
> hset key key3 value33 -> 0

> HDEL key field [field ...]
> Redis HDEL命令删除哈希表 key 中的一个或多个指定域，不存在的字段将被忽略
> 返回值：被成功移除的字段的数量，不包括被忽略的字段
> hdel key key3 -> 1

> HEXISTS key field
> 用于查看哈希表 key 中，给定域 field 是否存在
> 返回值：如果哈希表含有给字段，则返回 1；如果哈希表不含有给定字段，或者是 key 不存在，那么返回 0
> hexists key key1 -> 1
> hexists key key3 -> 0

> HGET key field
> 返回哈希表 key 中给定字段 field 的字段值。该命令与 HMGET 相似，后者可以返回哈希表 key 中，一个或多个给定的字段值
> 返回值：给定 field 的值。当给定 filed 不存在或给定 key 不存在时，则返回 null
> hget key key1 -> value1

> HGETALL key
> 用于返回哈希表 key 中，所有的字段和值
> 返回值：以列表形式返回哈希表的字段和字段值。若 key 不存在，则返回空列表
> hgetall key -> key1 value1 key2 value2

> HINCRBY key field increment
> Redis HINCRBY 命令为哈希表 key 中的 field 的值加上增量 increment 。增量也可以为负数，相当于对给定字段的值进行减法操作。
> 如果 key 不存在，将自动创建一个新的哈希表并执行 HINCRBY 命令；如果域 field 不存在，那么在执行命令前，字段的值被初始化为 0
> 若对于一个储存字符串值的 field 执行 HINCRBY 命令将造成一个错误。该命令操作的数值被限制在 64 位(bit)有符号数字表示之内
> 返回值：执行 HINCRBY 命令之后，哈希表 key 中 field 的值
> hset price apple 20 -> 1
> hincrby price apple 10 -> 30
> hincrby price apple -10 -> 20

> HKEYS key
> 返回哈希表 key 中的所有字段
> 返回值：一个包含哈希表中所有字段的列表。当 key 不存在时，返回一个空列表
> hkeys key -> key1 key2

> HLEN key
> 返回哈希表 key 中字段的数量
> 返回值：哈希表中字段的数量。若 key 不存在时，则返回 0
> hset key key1 value1 key2 value2
> hlen key -> 2

> HVALS key
> 返回哈希表 key 中所有字段的值
> 返回值：一个包含哈希表中所有的字段值。当 key 不存在时，返回一个空列表
> hvals key -> value1 value2




