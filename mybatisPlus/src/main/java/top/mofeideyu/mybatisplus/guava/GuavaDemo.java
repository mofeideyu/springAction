//package top.mofeideyu.mybatisplus.guava;
//
//import com.google.common.cache.*;
//
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author: Administrator
// * @date: 2023/5/19 10:10
// * @description:
// */
//public class GuavaDemo {
//
//    public static void main(String[] args) throws Exception {
//        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
//                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
//                .concurrencyLevel(8)
//                //设置缓存容器的初始容量为10
//                .initialCapacity(10)
//                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
//                .maximumSize(100)
//                //Cache中存储的对象,写入3s后过期
//                .expireAfterWrite(3, TimeUnit.SECONDS)
//                //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
//                //.expireAfterAccess(17, TimeUnit.SECONDS)
//                //只阻塞当前数据加载线程，其他线程返回旧值
//                //.refreshAfterWrite(13, TimeUnit.SECONDS)
//                //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
//                .recordStats()
//                //设置缓存的移除通知
//                .removalListener(new RemovalListener<Object, Object>() {
//                    @Override
//                    public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
//                        System.out.println(removalNotification.getKey() + " 被移除,原因:" + removalNotification.getCause());
//                    }
//                })
//                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
//                .build(new CacheLoaderClass());
//
////        String s = cache.get(6);
////        System.out.println(s);
//        String ifPresent = cache.getIfPresent(6);
//        System.out.println(ifPresent);
////        cache.invalidate(6);
//
////        Thread.sleep(5000);
////        for (Map.Entry<Integer, String> integerStringEntry : cache.asMap().entrySet()) {
////            System.out.println("key:" + integerStringEntry.getKey() + ";" + integerStringEntry.getValue());
////        }
//
//    }
//
//    /**
//     * 随机缓存加载,实际使用时应实现业务的缓存加载逻辑,例如从数据库获取数据
//     */
//    public static class CacheLoaderClass extends CacheLoader<Integer,String> {
//        @Override
//        public String load(Integer integer) throws Exception {
//            System.out.println(Thread.currentThread().getName() + " 加载数据开始");
//            TimeUnit.SECONDS.sleep(8);
//            Random random = new Random();
//            System.out.println(Thread.currentThread().getName() + " 加载数据结束");
//            return "value:" + random.nextInt(10000);
//        }
//    }
//
//}
