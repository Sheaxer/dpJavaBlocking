package stuba.fei.gono.javablocking.mongo.data.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stuba.fei.gono.javablocking.mongo.data.repositories.EmployeeRepository;
import stuba.fei.gono.javablocking.pojo.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeConverter(EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee convert(String s) {
       return employeeRepository.findById(s).orElse(null);
    }
}
