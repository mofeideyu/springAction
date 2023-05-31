package top.mofeideyu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Test
    void contextLoads() {
        String thinkNacos = new BCryptPasswordEncoder().encode("thinknacos");
        System.out.println(thinkNacos);
    }

}
