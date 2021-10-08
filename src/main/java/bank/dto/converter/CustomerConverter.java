package bank.dto.converter;

import bank.dto.CustomerDto;
import bank.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    private final ModelMapper mapper;

    public CustomerConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerDto toDto(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }

    public Customer toEntity(CustomerDto customerDto) {
        return mapper.map(customerDto, Customer.class);
    }
}
