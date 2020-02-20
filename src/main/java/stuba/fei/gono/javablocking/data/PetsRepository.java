package stuba.fei.gono.javablocking.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import stuba.fei.gono.javablocking.pojo.Pets;

public interface PetsRepository extends MongoRepository<Pets, String> {

    Pets findBy_id(ObjectId _id);
}
