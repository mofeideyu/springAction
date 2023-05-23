## Zset 命令

> ZADD key [NX|XX] [CH] [INCR] score member [score member]
> Redis ZADD 命令将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
> 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上
> 注意：在 Redis 2.4 版本以前，ZADD 每次只能添加一个元素
> 返回值：被成功添加的新成员的数量，不包括那些被更新的，或者已经存在的成员
> zadd juice 90 apple 85 pear 96 banana -> 3
> zadd juice 86 pear -> 0

> ZCARD key
> 返回值：当 key 存在且是有序集类型时，返回有序集的基数。当 key 不存在时，返回 0
> zcard juice -> 3

> ZCOUNT key min max
> Redis ZCOUNT 命令返回有序集 key 中，score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量
> 返回值：score 值在 min 和 max 之间的成员的数量
> zcount juice 86 90 -> 2

> ZINCRBY key increment member
> Redis ZINCRBY 命令为有序集合 key 的成员 member 的 score 值加上增量 increment
> 当 key 不存在，或 member 不是 key 的成员时，ZINCRBY key increment member 等同于 ZADD key increment member；当 key 不是有序集类型时，返回一个错误
> 也可以通过传递一个负数值 increment ，让 score 减去相应的值
> 注意：score 值可以是整数值或双精度浮点数
> 返回值：member 成员的新 score 值，以字符串形式表示
> zincrby juice 2 pear -> 88

> 