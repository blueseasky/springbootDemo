package com.blueseasky.springboot.domain.converter;


import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import javax.persistence.AttributeConverter;


/**
 * Created by renlei on 2017/6/13.
 */
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String>{

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return LocalDateTime.parse(dbData, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
