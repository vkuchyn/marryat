package com.keypr.marryat.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        final String json = '{' +
                "\"first_name\": \"Victor\"," +
                "\"last_name\": \"Kuchyn\"," +
                "\"room\": \"23A\"," +
                "\"start_date\": \"20170814\"," +
                "\"end_date\":  \"20170816\"" +
                '}';
        mockMvc.perform(
                post("/reservations")
                        .content(json)
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void updatesReservation() throws Exception {
        final String json = '{' +
                "\"first_name\": \"Victor\"," +
                "\"last_name\": \"Kuchyn\"," +
                "\"room\": \"23B\"," +
                "\"start_date\": \"20170814\"," +
                "\"end_date\":  \"20170816\"" +
                '}';
        final MvcResult mvcResult = mockMvc.perform(
                post("/reservations")
                        .content(json)
                        .contentType("application/json")
        )
                .andExpect(status().isOk())
                .andReturn();
        String id = extractIdFromResponse(mvcResult.getResponse().getContentAsString());
        final String updateJson = json.replace("20170816", "20170817");
        mockMvc.perform(
                put("/reservations/" + id)
                        .contentType("application/json")
                        .content(updateJson)
        )
                .andExpect(status().isOk());
    }

    private String extractIdFromResponse(final String response) throws IOException {
        final Map map = new ObjectMapper().readValue(response, Map.class);
        return map.get("id").toString();
    }
}
