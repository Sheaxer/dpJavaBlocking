package stuba.fei.gono.javablocking.mongo.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import stuba.fei.gono.javablocking.pojo.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

   /* @Override
    Optional<Client> findById(String s);

    @Override
    void delete(Client client);

    @Override
    <S extends Client> S save(S s);*/

    
}
