package com.malhoe.ordina.raboapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class StatementProcessorITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void when_decently_called_return_successful() throws Exception{
        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"1.30}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("SUCCESSFUL"));
    }

    @Test
    void when_called_with_a_duplicated_reference_return_duplicate_reference() throws Exception{
        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"1.30}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("SUCCESSFUL"));

        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"1.30}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("DUPLICATE_REFERENCE"));
    }

    @Test
    void when_called_with_a_incorrect_endbalance_return_incorrect_endbalance() throws Exception{
        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"9.23}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("INCORRECT_END_BALANCE"));
        System.out.println("fd");
    }

    @Test
    void when_called_with_a_duplicated_reference_and_incorrect_balance_return_duplicate_reference_and_incorrect_balance() throws Exception{
        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"1.30}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("SUCCESSFUL"));

        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"Transaction reference\":\"1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"text\",\"End Balance\":\"1.23}"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE"));
    }

    @Test
    void when_called_with_bad_json_return_400() throws Exception{
        mockMvc.perform(post("/processStatement")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Very wrong Json"))
                .andExpect(mvcResult -> mvcResult.getResponse().getContentAsString().contains("BAD_REQUEST"));
    }

}

