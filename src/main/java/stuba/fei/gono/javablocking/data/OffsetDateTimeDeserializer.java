package stuba.fei.gono.javablocking.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class OffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {
    protected OffsetDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    public OffsetDateTimeDeserializer()
    {
        this(null);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String tmp = jsonParser.getText();

        return OffsetDateTime.parse(tmp);
    }
}