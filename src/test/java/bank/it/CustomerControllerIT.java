package bank.it;

import bank.dto.CustomerDto;
import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerIT extends AbstractControllerIT {
    @Test
    public void testAddCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("Мариф")
                .lastName("Манафов")
                .role("физ. лицо")
                .build();

        mockMvc.perform(post("/customers")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value(customerDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customerDto.getLastName()))
                .andExpect(jsonPath("$.role").value(customerDto.getRole()));
    }
}
