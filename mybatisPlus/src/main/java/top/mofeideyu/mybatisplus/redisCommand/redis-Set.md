## Set 命令

> SADD key member [member ...]
> 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。若 key 不存在，则自动创建一个包含 member 元素的集合。当 key 不是集合类型时，返回一个错误
> 返回值：被添加到集合中的新元素的数量，不包括被忽略的元素
> sadd fruit apple pear apple -> 2
> sadd fruit banana -> 1
> SMEMBERS key
> 返回集合 key 中的所有成员，不存在的 key 被视为空集合
> smembers fruit -> banana pear apple

> SCARD key
> 返回集合 key 的基数(集合中元素的数量)
> 返回值：集合的基数。若 key 不存在时，则返回 0
> scard fruit -> 3

> SDIFF key [key ...]
> Redis SDIFF 命令返回第一个集合与其他集合之间的差集，也可以认为是第一个集合中独有的元素。不存在的集合 key 将视为空集。对于不存在的 key 将被视为空集
> smembers fruit -> banana pear apple
> smembers fruit2 -> apple
> sdiff fruit fruit2 -> banana pear

> SDIFFSTORE destination key [key ...]
> Redis SDIFFSTORE 命令与 SDIFF 命令作用类似，但前者是将结果保存到 destination 集合，而不是简单地返回结果集。如果 destination 集合已经存在，则将其覆盖
> smembers fruit -> banana pear apple
> smembers fruit2 -> apple
> sdiffstore fruitdiff fruit fruit2
> smembers fruitdiff -> pear banana

> SINTER key [key ...]
> 返回一个集合的全部成员，该集合是所有给定集合的交集。对于不存在的 key 被视为空集。如果给定的集合中存在一个空集时，结果也为空集(根据集合运算定律)
> smembers fruit -> banana pear apple
> smembers fruit2 -> apple
> sinter fruit fruit2 -> apple

> SINTERSTORE destination key [key ...]
> Redis SINTERSTORE 命令与 SINTER 命令类似，但前者会将结果保存到 destination 集合，而不是简单地返回结果集。如果 destination 集合已经存在，则将其覆盖,destination 本身也是key
> 返回值：结果集中的成员数量
> smembers fruit -> banana pear apple
> smembers fruit2 -> apple
> sinterstore fruitinter fruit fruit2 -> 1
> smembers fruitinter -> apple
> sinterstore fruitinter fruit -> 3
> smembers fruitinter -> apple pear banana

> SMOVE source destination member
> Redis SMOVE 命令将 member 成员从一个集合(source)移动至另一个集合(destination)
> 如果 source 集合不存在，或者不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去
> 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。当 source 或 destination 不是集合类型时，返回一个错误信息
> smembers fruit -> banana pear apple
> smembers fruit2 -> apple
> smove fruit fruit2 pear -> 1
> smembers fruit -> banana apple
> smembers fruit2 -> pear apple

> SPOP key [count]
> 移除并返回集合中的一个随机元素
> 返回值：被移除的随机元素。当 key 不存在或 key 是空集时，返回 null
> smembers fruit2 -> pear apple
> spop fruit -> pear

> SRANDMEMBER key [count]

* Redis SRANDMEMBER 命令，该命令执行时，如果只提供了 key 参数，那么返回集合中的一个随机元素
* 从 Redis 2.6 版本开始， SRANDMEMBER 命令允许接受可选参数 count，该参数描述如下：
    * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同
    * 如果 count 大于等于集合基数，那么返回整个集合
    * 如果 count 为负数，那么返回数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值
该命令和 SPOP 相似，但 SPOP 是将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合做任何改动
> smembers fruit2 -> 3 2 1 4 5 apple
> srandmember fruit2 2 -> 5 apple

> SREM key member [member ...]
> Redis SREM 命令移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。当 key 不是集合类型，返回一个错误
> 注意：在 Redis 2.4 版本以前，SREM 只接受单个 member 值
> 返回值：被成功移除的元素的数量，不包括被忽略的元素
> smembers fruit2 -> 3 2 1 4 5 apple
> srem fruit2 2 6 -> 1

> SUNION key [key ...]
> 返回一个集合的全部成员，该集合是所有给定集合的并集
> 返回值：并集成员的列表
> smember number -> 5 4 3 2 1
> smember fruit -> banana
> sunion fruit number -> 3 2 1 banana 4 5

> SUNIONSTORE destination key [key ...]
> Redis SUNIONSTORE 命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。如果 destination 已经存在，则将其覆盖。
> 注意：destination 可以是 key 本身
> smember number -> 5 4 3 2 1
> smember fruit -> banana
> sunionstore fruitnumber fruit number -> 6
> smembers fruitnumber -> 3 2 1 banana 4 5