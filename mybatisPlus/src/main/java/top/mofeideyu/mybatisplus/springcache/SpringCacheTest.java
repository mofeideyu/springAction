//package top.mofeideyu.mybatisplus.springcache;
//
//import org.springframework.stereotype.Component;
//import top.mofeideyu.mybatisplus.entity.MybatisPlus;
//import top.mofeideyu.mybatisplus.mapper.MybatisPlusMapper;
//
//import javax.annotation.Resource;
//
///**
// * @author: Administrator
// * @date: 2023/5/19 13:30
// * @description:
// */
//@Component
//public class SpringCacheTest {
//
//    @Resource
//    private MybatisPlusMapper mybatisPlusMapper;
//
//    public MybatisPlus mybatisPlus() {
//        sleepStatement();
//        return mybatisPlusMapper.selectById(1659017279968759809L);
//    }
//
//    public void sleepStatement() {
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
