package com.hdd.server.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author hedd
 * @create 2021/5/12 14:08
 * @Desc 日期格式转换类
 */
@Component
public class DataConver implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        try {
            if (s!=null && !"".equals(s)){
                return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
