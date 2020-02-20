package stuba.fei.gono.javablocking.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stuba.fei.gono.javablocking.pojo.Client;
@Component
public class ClientConverter implements Converter<String,Client> {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientConverter(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client convert(String s) {
        return clientRepository.findById(s).get();
    }
}
