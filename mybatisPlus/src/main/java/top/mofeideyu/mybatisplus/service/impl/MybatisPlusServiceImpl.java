package top.mofeideyu.mybatisplus.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.mofeideyu.mybatisplus.entity.MybatisPlus;
import top.mofeideyu.mybatisplus.mapper.MybatisPlusMapper;
import top.mofeideyu.mybatisplus.service.MybatisPlusService;

/**
 * @author: Administrator
 * @date: 2023/5/19 14:24
 * @description:
 */
@Service
@Slf4j
public class MybatisPlusServiceImpl implements MybatisPlusService {
    @Autowired
    private MybatisPlusMapper mybatisPlusMapper;

    @Override
    @Cacheable(value = "mybatisPlusCache",key = "#id",condition = "#result != null")
    public MybatisPlus selectMybatisPlusById(Long id) {
        log.info("通过数据库获取数据");
        return mybatisPlusMapper.selectById(id);
    }

}
