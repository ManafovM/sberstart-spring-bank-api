package bank.it;

import bank.dto.BalanceDto;
import bank.dto.CardDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CardControllerIT extends AbstractControllerIT {
    @Test
    public void testGetBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.amount").value(10));
    }

    @Test
    public void testDeposit() throws Exception {
        BalanceDto balanceDto = new BalanceDto(new BigDecimal(100));

        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/2/balance")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(balanceDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.amount").value(200));
    }

    @Test
    public void testCreateCard() throws Exception {
        CardDto cardDto = CardDto.builder()
                .number("1234567891234567")
                .expireDate("11/25")
                .accountId(3L)
                .build();

        mockMvc.perform(post("/cards")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.number").value(cardDto.getNumber()))
                .andExpect(jsonPath("$.expireDate").value(cardDto.getExpireDate()))
                .andExpect(jsonPath("$.accountId").value(cardDto.getAccountId()));
    }
}
