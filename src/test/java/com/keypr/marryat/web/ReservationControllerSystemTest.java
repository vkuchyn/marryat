package com.keypr.marryat.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * System reservations controller test with whole spring context.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public final class ReservationControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void reservesRoom() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23A\"," +
                                "\"start_date\": \"20170814\"," +
                                "\"end_date\":  \"20170816\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

}
