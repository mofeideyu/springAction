package top.mofeideyu.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Administrator
 * @date: 2023/5/16 16:56
 * @description:
 */
@Configuration
@MapperScan("top.mofeideyu.mybatisplus.dao")
public class MybatisConfig {

}
