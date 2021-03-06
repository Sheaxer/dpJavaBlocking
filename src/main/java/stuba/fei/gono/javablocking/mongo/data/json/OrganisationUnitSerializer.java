package stuba.fei.gono.javablocking.mongo.data.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import stuba.fei.gono.javablocking.pojo.OrganisationUnit;

import java.io.IOException;

public class OrganisationUnitSerializer extends StdSerializer<OrganisationUnit> {
    protected OrganisationUnitSerializer(Class<OrganisationUnit> t) {
        super(t);
    }

    public OrganisationUnitSerializer()
    {
        this(null);
    }

    @Override
    public void serialize(OrganisationUnit organisationUnit, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(organisationUnit.getId());
    }
}
