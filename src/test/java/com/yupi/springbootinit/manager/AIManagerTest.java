package com.yupi.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AIManagerTest {
    @Resource
    private AIManager aiManager;

    @Test
    void doChat() {
        String answer = aiManager.doChat("你是一个数据分析师，请帮我分析网站用户的增长趋势 原始数据如下: 日期,用户数 1号,10 2号,20 3号,30");
        System.out.println(answer);
    }
}