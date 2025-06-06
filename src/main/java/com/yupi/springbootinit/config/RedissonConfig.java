package com.yupi.springbootinit.config;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
    private Integer database;

    private String host;

    private Integer port;

    @Bean
    public RedissonClient getRedissonClient() {
        // 创建配置对象
        Config config = new Config();

        // 添加单机Redisson配置
        config.useSingleServer().setDatabase(database).setAddress("redis://" + host + ":" + port);

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
