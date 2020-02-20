package stuba.fei.gono.javablocking.data;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
@WritingConverter
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {

    public Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }
}