## List 命令
http://c.biancheng.net/redis2/llen.html

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

> LLEN key
> Redis LLEN命令返回列表 key 的长度。如果 key 不是列表类型，返回一个错误
> llen list -> 6

> LPOP key
> 移除并返回列表 key 的头元素
> 返回值：返回值是列表的头部的第一个元素。当 key 不存在时，则返回 null
> lpop list -> e

> LPUSH key value [value ...]
> Redis LPUSH 命令将一个或多个值插入到列表头部（从左侧开始操作），如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入。
> 如果 key 不存在，那么一个空列表会被自动创建并执行 LPUSH 操作；当 key 存在但不是列表类型时，则返回一个错误。
> 返回值：执行 LPUSH 命令后，返回列表中元数的的个数。即列表的长度
> lpush animals pig dog monkey -> 3
> lrange animals 0 -1 -> monkey dog pig

> LPUSHX key value
> Redis LPUSHX 命令，当且仅当 key 存在，并且类型为列表时，将值 value 插入到列表的头部。和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不执行
> 返回值：列表的长度
> exists list2 -> 0
> lpushx list2 a b c -> 0
> exists list2 -> 0

> LRANGE key start end
> Redis LRANGE 命令返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
> 下标(index)参数 start 和 stop 都是以 0 开始，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素

> LREM key count value
> Redis LREM 命令根据 count 的值，移除列表中与参数 value 相等的元素
> 返回值：被移除元素的数量。若 key 不存在时， 则 LREM 命令总是返回 0
> lrange list 0 -1 -> d c m b a
> lrem list 3 a -> 1
* count 取值有以下几种情况：
    * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
    * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
    * count = 0 : 移除表中所有与 value 相等的值

> LSET key index value
> Redis LSET 命令能够将列表中下标为 index 的元素的值设置为 value
> 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，将会返回一个错误
> 返回值：操作成功返回 ok ，否则返回错误信息
> lrange list 0 -1 -> d c m b a
> lset list 2 e -> OK
> lrange list 0 -1 -> d c e b a

> LTRIM key start stop
> Redis LTRIM 命令对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
> 操作成功返回 ok ，否则返回错误信息
> lrange list 0 -1 -> d c e b a
> ltrim list 1 -1 -> OK
> lrange list 0 -1 -> c e b a