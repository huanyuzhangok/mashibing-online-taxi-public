package com.mashing.serviceDriverUser.generator;

/**
 * @className: MySqlGenerator
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2023/10/12
 **/

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 */
public class MySqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator
                .create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8","root","root")
                .globalConfig(builder -> {
                    builder.author("张寰宇").fileOverride().outputDir("C:\\Compiler\\IDEA\\IDEAworkspace\\zhy-online-taxi-public\\service-drive-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.mashing.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "C:\\Compiler\\IDEA\\IDEAworkspace\\zhy-online-taxi-public\\service-drive-user\\src\\main\\java\\com\\mashing\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_car_binding_relationship");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
