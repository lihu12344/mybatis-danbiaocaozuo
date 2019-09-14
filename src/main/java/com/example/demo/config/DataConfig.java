package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan("com.example.demo.dao")
public class DataConfig {
}
