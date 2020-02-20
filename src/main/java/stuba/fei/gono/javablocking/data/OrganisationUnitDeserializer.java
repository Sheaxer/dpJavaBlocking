package stuba.fei.gono.javablocking.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stuba.fei.gono.javablocking.pojo.OrganisationUnit;

import java.io.IOException;

@Component
public class OrganisationUnitDeserializer extends StdDeserializer<OrganisationUnit> {

    @Autowired
    private OrganisationUnitRepository organisationUnitRepository;

    protected OrganisationUnitDeserializer(Class<?> vc) {
        super(vc);
    }

    public OrganisationUnitDeserializer()
    {
        this(null);
    }

    @Override
    public OrganisationUnit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String tmp=jsonParser.getText();
        return organisationUnitRepository.findById(tmp).get();
    }
}
