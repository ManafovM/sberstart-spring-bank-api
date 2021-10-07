package bank.dto.converter;

import bank.dto.CustomerDto;
import bank.entity.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerConverter {
    private ModelMapper mapper;

    public CustomerDto toDto(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }

    public Customer toEntity(CustomerDto customerDto) {
        return mapper.map(customerDto, Customer.class);
    }
}
