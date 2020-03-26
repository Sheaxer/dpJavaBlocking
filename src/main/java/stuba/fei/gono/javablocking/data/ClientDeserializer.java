package stuba.fei.gono.javablocking.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stuba.fei.gono.javablocking.errors.ReportedOverlimitTransactionException;
import stuba.fei.gono.javablocking.pojo.Client;

import java.io.IOException;
import java.util.Optional;

@Component
public class ClientDeserializer extends StdDeserializer<Client > {
    public ClientDeserializer(Class<?> vc) {
        super(vc);
    }
    public ClientDeserializer()
    {
        this(null);
    }
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Optional<Client> c = clientRepository.findById(jsonParser.getText());
        return c.orElse(null);
        //throw new ReportedOverlimitTransactionException("CLIENTID_NOT_VALID");
    }
}
