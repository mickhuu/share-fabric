package cn.edu.gxmzu.ai.mickhu.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-17 19:44
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redisson() throws IOException {
        RedissonClient redisson = Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
//                Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-single.yml")));
        return redisson;
    }
}