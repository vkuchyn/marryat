package com.keypr.marryat.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration reservation controller test with mocked services.
 *
 * @author viktor email kuchin.victor@gmail.com
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReservationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test //TODO(vkuchyn) extract massive jsons to resource files.
    public void validatesWrongDates() throws Exception {
        mockMvc.perform(
                post("/reservations")
                        .content('{' +
                                "\"first_name\": \"Victor\"," +
                                "\"last_name\": \"Kuchyn\"," +
                                "\"room\": \"23A\"," +
                                "\"start_date\": \"20170814\"," +
                                "\"end_date\":  \"20170814\"" +
                                '}')
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[{" +
                        "\"errorKey\":\"end.date.after.start.date\"," +
                        "\"description\":\"End date should be after start date\"" +
                        "}]"));
    }
}