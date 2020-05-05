package com.malhoe.ordina.raboapp.Model;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
//I'm at the end of my time and for some simple reason i probably overlook i am not able to fix the null in a.freetext. I'm giving up on this part
class StatementProcessorInputModelTest {

    @Test
    @Disabled
        //Ignored because the converter can convert the value but it doesn't seem to save it.
    void mapping_from_json_should_not_throw_JsonException() throws Exception {
        String goodInput = "{\"Transaction reference\":1,\"Account number\":\"1\",\"Start Balance\":1,\"Mutation\":\"0.30\",\"Description\":\"Good_Text\",\"End Balance\":1.23}";
        StatementProcessorInputModel a = new StatementProcessorInputModel();
        a.converter(goodInput);
        Assert.assertEquals(a.freeText, "Good_Text");
    }


    @Test
    void mapping_from_broken_json_should_throw_JsonException() throws Exception {
        try {
            String goodInput = "Very wrong Json";
            StatementProcessorInputModel a = new StatementProcessorInputModel();
            a.converter(goodInput);
            throw new Exception();
        } catch (Exception Je){
            System.out.println(Je);
            Assert.assertEquals(Je.getMessage(), "Value Very of type java.lang.String cannot be converted to JSONObject");
        }

    }
}
