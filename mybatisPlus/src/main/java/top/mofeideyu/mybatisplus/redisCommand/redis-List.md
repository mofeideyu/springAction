## List 命令

> LPUSH key value [value ...]
> Redis LPUSH 命令将一个或多个值插入到列表头部（从左侧开始操作），如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入
> 如果 key 不存在，那么一个空列表会被自动创建并执行 LPUSH 操作；当 key 存在但不是列表类型时，则返回一个错误
> lpush letter a b c d e -> 5

> BLPOP key [key ...] timeout
> 列表弹出元素的阻塞模式，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止
> 当给定多个 key 时，按 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素
> 返回值：如果列表为空，返回一个 null 。否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key，第二个元素是被弹出元素的值
> lpush letter a b c
> blpop letter -> letter c

> BRPOP key [key ...] timeout
> 用法与 BLPOP类似

> RPOPLPUSH source destination
> Redis  RPOPLPUSH 命令执行以下两步操作：首先将列表(source)中的最后一个元素(尾元素)弹出，并返回给客户端。然后将弹出的元素插入到另外一个指定的列表(destination)中，并作为该列表的的头元素
> 如果 source 不存在，那么将 返回 null，并且不再执行其他操作。 如果 source 和 destination 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况看做翻转列表操作
> 返回值：被弹出的元素
> lpush list1 a b
> lpush list2 c d e
> rpoplpush list1 list2 -> b
> rpoplpush list1 list2 -> a
> rpoplpush list1 list2 -> null

> LINDEX key index
> 返回列表中下标为 index 的元素
> 列表中下标为 index 的元素。如果 index 参数值超出列表的区间范围(out of range)，则返回 null
> lpush list a b c -> 3
> lindex 0 -> c
> lindex 1 -> b
> lindex 2 -> c

> LINSERT key BEFORE|AFRER pivot value
> Redis LINSERT 命令将值 value 插入到列表 key 当中，位于值 pivot 之前或之后
> 当 pivot (参照值)不存在于列表 key 时，不执行任何操作。当 key 不存在时， key 被视为空列表，不执行任何操作。如果 key 不是列表类型，返回一个错误
> lpush list a b c d e -> 5
> linsert list after c m -> 6
> lrange list 0 -1 -> e d c m b a

> LLEN
> http://c.biancheng.net/redis2/llen.html