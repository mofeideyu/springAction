package top.mofeideyu.mybatisplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.mofeideyu.mybatisplus.entity.MybatisPlus;
import top.mofeideyu.mybatisplus.mapper.ProductMapper;
import top.mofeideyu.mybatisplus.service.MybatisPlusService;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @date: 2023/5/16 17:02
 * @description:
 */
@RestController
public class MybatisPlusController {

    @Resource
    private ProductMapper productMapper;
    @Autowired
    private MybatisPlusService mybatisPlusService;

    @GetMapping("/mybatis/{id}")
    public MybatisPlus test(@PathVariable("id") Long id) {
        return mybatisPlusService.selectMybatisPlusById(id);
    }

}
