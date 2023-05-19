package top.mofeideyu.mybatisplus;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mofeideyu.mybatisplus.entity.MybatisPlus;
import top.mofeideyu.mybatisplus.entity.Product;
import top.mofeideyu.mybatisplus.mapper.MybatisPlusMapper;
import top.mofeideyu.mybatisplus.mapper.ProductMapper;
import top.mofeideyu.mybatisplus.service.MybatisPlusService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class MybatisPlusApplicationTests {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MybatisPlusMapper mybatisPlusMapper;
    @Autowired
    private MybatisPlusService mybatisPlusService;

    @Test
    void selectList() {
        List<Product> list = productMapper.selectList(null);
        if (CollectionUtil.isNotEmpty(list)) {
            System.out.println(list.get(0));
        }
    }

    @Test
    void insertProduct() {
        Product product = new Product();
        product.setMaterialNo("01.MJC-00000089");
        product.setProductName("mybatisPlusA");
        int insert = productMapper.insert(product);
        System.out.println(insert);
        System.out.println(product);
    }

    @Test
    void insertMybatisPlus() {
        MybatisPlus mybatisPlus = new MybatisPlus();
        mybatisPlus.setName("mybatisPlusE");
        int insert = mybatisPlusMapper.insert(mybatisPlus);
        System.out.println(insert);
        System.out.println(mybatisPlus);
    }

    @Test
    void insertMybatisFill() {
        MybatisPlus mybatisPlus = new MybatisPlus();
        mybatisPlus.setName("mybatisPlusJ");
        int insert = mybatisPlusMapper.insert(mybatisPlus);
        System.out.println(insert);
        System.out.println(mybatisPlus);
    }

    @Test
    void updateMybatisPlus() {
        MybatisPlus mybatisPlus = new MybatisPlus();
        mybatisPlus.setId(1659017279968759809L);
        mybatisPlus.setName("java+");
        int updateResult = mybatisPlusMapper.updateById(mybatisPlus);
        System.out.println(updateResult);
    }

    @Test
    void selectMybatisPlusById() {
        MybatisPlus mybatisPlus = mybatisPlusMapper.selectById(1659017279968759809L);
        System.out.println(mybatisPlus);
    }

    @Test
    void selectBatchids() {
        List<MybatisPlus> mybatisPluses = mybatisPlusMapper.selectBatchIds(Arrays.asList(1659017279968759809L,1659077997271212034L));
        mybatisPluses.forEach(System.out::println);
    }

    @Test
    void selectOne() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("name","mybatisPlusH");
        List<MybatisPlus> mybatisPlusList = mybatisPlusMapper.selectByMap(map);
        mybatisPlusList.forEach(System.out::println);
    }

    @Test
    void page() {
        Page<MybatisPlus> page = new Page<>(1, 4);
        mybatisPlusMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("记录数：" + page.getTotal());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    @Test
    void deleteById() {
        int i = mybatisPlusMapper.deleteById(1659091332930740225L);
        System.out.println(i);
    }

    @Test
    void deleteById2() {
        MybatisPlus mybatisPlus = new MybatisPlus();
        mybatisPlus.setId(1659086466162360321L);
        int i = mybatisPlusMapper.deleteById(mybatisPlus);
        System.out.println(i);
    }

    @Test
    void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","mybatisPlusC");
        int i = mybatisPlusMapper.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    void springCache() throws JsonProcessingException {
        MybatisPlus mybatisPlus1 = mybatisPlusService.selectMybatisPlusById(1659089249582215169L);
        log.info("mybatisPlus1: {}",new ObjectMapper().writeValueAsString(mybatisPlus1));
        MybatisPlus mybatisPlus2 = mybatisPlusService.selectMybatisPlusById(1659089249582215169L);
        log.info("mybatisPlus2: {}",new ObjectMapper().writeValueAsString(mybatisPlus2));

    }

}
