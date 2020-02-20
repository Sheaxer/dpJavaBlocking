package stuba.fei.gono.javablocking.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import stuba.fei.gono.javablocking.pojo.OrganisationUnit;

public interface OrganisationUnitRepository extends MongoRepository<OrganisationUnit,String> {
}
