package stuba.fei.gono.javablocking.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import stuba.fei.gono.javablocking.pojo.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
