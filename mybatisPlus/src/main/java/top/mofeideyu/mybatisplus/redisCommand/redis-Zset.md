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

> ZINTERSTORE destination numkeys key [key ...] [WEIGHTS weight] [AGGREGATE SUM|MIN|MAX]
> 用于计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination
> 注意：默认情况下，结果集中某个成员的分数值是所有给定集合中该成员的分数值之和
* WEIGHTS
    * WEIGHTS 选项用来给每个有序集合分别指定一个加权因子，每个有序集合中所有成员的 score 值在传递给聚合函数(aggregation function)之前，
      都要先乘以该加权因子。如果没有指定 WEIGHTS 选项，加权因子默认设置为 1 
* AGGREGATE
    * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。默认使用的参数 SUM ，可以将所有集合中某个成员的 score 值之和作为结果集中该成员的 score 值；
      若使用 MIN 参数，则将所有集合中某个成员的最小 score 值作为结果集中该成员的 score 值；而参数 MAX 则是将所有集合中某个成员的最大 score 值作为结果集中该成员的 score 值
> 返回值：保存到 destination 的结果集的基数
> zrange juice 0 -1 withscores -> pear 89 apple 90 banana 96
> zrange package 1 apple 2 mac 3 win 5 pear
> zinterstore juicepackage 2 juice package -> 2
> zrange juicepackage 0 -1 withscores -> apple 91 pear 94

> ZRANGE key start end [WITHSCORES]
> Redis ZRANGE 命令返回有序集合 key 中，指定区间内的成员，其中成员的位置按 score 值递增(从小到大)来排序。如果你需要成员按 score 值递减(从大到小)来排列，
> 请使用 ZREVRANGE 命令。对于具有相同 score 值的成员按字典序来排列
> 通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回
> zrange juicepackage 0 -1 withscores -> apple 91 pear 94

> ZRANGEBYLEX key min max [LIMIT offset count]
> Redis ZRANGEBYLEX 命令返回有序集合 key 中指定区间内的成员。此命令适用于分数相同的有序集合
> 注意：若命令中包含 LEX 则要求有序集合成员的分数值 score 必须相同
> 返回值：指定成员范围的元素列表
> zadd letter 0 a 0 b 0 c 0 d 0 e
> zrangebylex letter - + -> a b c d e
> zrangebylex letter - + limit 0 2 -> a b
> zrangebylex letter - + limit 2 2 -> c d
> zrangebylex letter - + limit 4 2 -> e

参数说明：

| 参数 | 说明 |
| --- | --- |
| min | 集合中排序位置较小的成员,必须以 ”[“ 开头,或者以 ”(“ 开头,可使用 ”-“ 代替 |
| max | 集合中排序位置较大的成员,必须以 ”[“ 开头,或者以 ”(“ 开头,可使用 ”+”代替 |
| LIMIT | 返回结果是否分页，指令中包含 LIMIT 后 offset、count 必须输入 |
| offset | 偏移量，返回结果的起始位置 |
| count | 返回结果数量 |

> ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
> Redis ZRANGEBYSCORE 命令返回有序集合 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)顺序排列。
> 可选参数 LIMIT 指定返回结果的数量及区间(就像SQL中的 SELECT LIMIT offset, count )，注意当 offset 很大时，定位 offset 的操作可能需要遍历整个有序集，此过程最坏复杂度为 O(N) 时间。
> 可选参数 WITHSCORES 决定结果集是单单返回有序集的成员，还是将有序集成员及其 score 值一起返回。该选项自 Redis 2.0 版本起可用
* 无限区间
min 和 max 也可以是 -inf 和 +inf ，这样一来，您就可以在不知道有序集的最低和最高 score 值的情况下，使用 ZRANGEBYSCORE 命令。
默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，通过给参数前增加(符号来使用可选的开区间，也就是小于或大于。示例如下：
    * ZRANGEBYSCORE zset (1 10    #表示 1<score<=10
    * ZRANGEBYSCORE zset (5 (20  #1<score<20
> 返回值：指定区间内，带有 score 值(可选)的有序集成员的列表
> ZADD salary 3000 jack 3500 helen 2880 john 4000 simith 6000 rose
> zrangebyscore salary 1000 2500 withscores -> (empty list or set)
> zrangebyscore salary 1000 4000 withscores -> john 2880 jack 3000 helen 3500 simith 4000
> zrangebyscore salary 1000 4000 withscores limit 1 4 -> jack 3000 helen 3500 simith 4000
> zrangebyscore salary 1000 (4000 withscores limit 1 4 -> jack 3000 helen 3500

> ZRANK key member
> Redis ZRANK 命令返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。排名以 0 为底，也就是说， score 值最小的成员排名为 0 
> 注意：使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名
> 返回值：如果 member 是有序集 key 的成员，返回 member 的排名；如果 member 不是有序集 key 的成员，返回 null
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zrank salary john -> 0 (排名为0)
> zrank salary jack -> 1 (排名为1)

> ZREM key member [member ...]
> Redis ZREM 命令移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。当 key 存在但不是有序集类型时，返回一个错误。
> 注意：在 Redis 2.4 版本以前， ZREM 每次只能删除一个元素
> 返回值：被成功移除的成员的数量，不包括被忽略的成员
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zrem salary john helen -> 2
> zrange salary 0 -1 withscores -> jack 3000 simith 4000 rose 6000

> ZREMRANGEBYRANK key start stop
> Redis ZREMRANGEBYRANK 命令移除有序集合 key 中，指定排名(rank)区间内的所有成员。其中区间分别以下标参数 start 和 stop 指出，包含 start 和 stop 在内。
> 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。您也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推
> 返回值：被移除成员的数量
> zrange salary 0 -1 withscores -> jack 3000 simith 4000 rose 6000
> zremrangebyrank salary 0 0 -> 1 (移除 0<= x <=0 排名范围内的数据)
> zrange salary 0 -1 withscores -> simith 4000 rose 6000

> ZREMRANGEBYSCORE key min max
> Redis ZREMRANGEBYSCORE 命令移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员
> 注意：自版本 2.1.6 开始，score 值等于 min 或 max 的成员也可以不包括在内，详情请参见 ZRANGEBYSCORE 命令
> 返回值：被移除成员的数量
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zremrangebyscore salary 4000 6000 (移除score 4000<=salary<=6000 内的数据) -> 2
> zrange salary 1000 4000 withscores -> john 2880 jack 3000 helen 3500

> ZREVRANGE key start end [WITHSCORES]
> Redis ZREVRANGE 命令返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递减(从大到小)来排列。具有相同 score 值的成员按字典序的逆序排列。
> 除了成员按 score 值降序排列外， ZREVRANGE 命令的其他方面和 ZRANGE 命令相同
> 返回值：指定区间内，带有 score 值(可选)的有序集成员的列表
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zrevrange salary 0 -1 withscores -> rose 6000 simith 4000 helen 3500 jack 3000 john 2880

> ZREVRANK key number
> Redis ZREVRANK 命令返回有序集合 key 中 member 的排名。其中有序集合的成员按 score 值递减(从大到小)排序，该命令排序顺序与 ZRANK 命令正好相反
> 注意：排名从 0 开始， 也就是说，score 值最大的成员排名为 0
> 返回值：如果 member 是有序集 key 的成员，返回 member 的排名；如果 member 不是有序集 key 的成员，返回 null
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zrevrank salary jack -> 3 (倒数第三)

> ZSCORE key number
> Redis ZSCORE 命令返回有序集 key 中，成员 member 的 score 值。如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 null
> 返回值：member 成员的 score 值，以字符串形式表示
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500 simith 4000 rose 6000
> zscore  salary jack -> 3000

> ZUNIONSTORE destination numberkeys key [key ...] [WEIGHT weight] [AGGREGATE SUM|MIN|MAX]
* WEIGHTS
    * WEIGHTS 选项用来给每个有序集合分别指定一个加权因子，每个有序集合中所有成员的 score 值在传递给聚合函数(aggregation function)之前，都要先乘以该加权因子。如果没有指定 WEIGHTS 选项，加权因子默认设置为 1 。
* AGGREGATE 
    * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。默认使用的参数 SUM ，可以将所有集合中某个成员的 score 值之和作为结果集中该成员的 score 值；使用 MIN 参数，可以将所有集合中某个成员的最小 score 值作为结果集中该成员的 score 值；而参数 MAX 则是将所有集合中某个成员的最大 score 值作为结果集中该成员的 score 值。
> 返回值：保存到 destination 的结果集的成员数量
> zrange salary 0 -1 withscores -> john 2880 jack 3000 helen 3500
> zrange salary1 0 -1 withscores -> baoan 1000 baojie 1500 chushi 2000
> zunionstore salary2 2 salary salary1 weights 1 2 -> baoan 2000 john 2880 baojie 3000 jack 3000 helen 3500 chushi 4000