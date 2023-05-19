package top.mofeideyu.mybatisplus.service;

import top.mofeideyu.mybatisplus.entity.MybatisPlus;

/**
 * @author: Administrator
 * @date: 2023/5/19 14:23
 * @description:
 */
public interface MybatisPlusService {

    /**
     *
     * @param id
     * @return
     */
    MybatisPlus selectMybatisPlusById(Long id);

}
