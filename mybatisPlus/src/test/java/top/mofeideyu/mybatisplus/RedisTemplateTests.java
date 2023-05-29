package top.mofeideyu.mybatisplus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class RedisTemplateTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void deleteKey() {
        Boolean delete = redisTemplate.delete("key1");
        log.info("delete: {}",delete);
    }

    @Test
    void saveCode() {
        redisTemplate.opsForValue().set("name","mofei");
        redisTemplate.expire("name",4, TimeUnit.MINUTES);
    }

    @Test
    void getCodeVal() {
        String name = null;
        try {
            name = redisTemplate.opsForValue().get("name1").toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println(name);
    }

    @Test
    void flushDb() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

}
