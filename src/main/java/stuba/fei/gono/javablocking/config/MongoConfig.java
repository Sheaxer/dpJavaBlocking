package stuba.fei.gono.javablocking.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import stuba.fei.gono.javablocking.data.OffsetDateTimeReadConverter;
import stuba.fei.gono.javablocking.data.OffsetDateTimeWriteConverter;
import stuba.fei.gono.javablocking.data.ZonedDateTimeReadConverter;
import stuba.fei.gono.javablocking.data.ZonedDateTimeWriteConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig  {

    @Bean
    public MongoCustomConversions customConversions()
    {
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        converters.add(new OffsetDateTimeReadConverter());
        converters.add(new OffsetDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }

}
