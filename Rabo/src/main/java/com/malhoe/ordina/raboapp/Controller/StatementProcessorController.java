package com.malhoe.ordina.raboapp.Controller;

import com.malhoe.ordina.raboapp.Model.StatementProcessorInputModel;
import com.malhoe.ordina.raboapp.Model.StatementProcessorResponseModel;
import com.malhoe.ordina.raboapp.Service.StatementProcessorService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementProcessorController {

    @Autowired
    StatementProcessorService statementProcessorService;

    @PostMapping(value = "/processStatement")
    StatementProcessorResponseModel processStatement (@RequestBody String rawInput){
        StatementProcessorResponseModel toReturn = new StatementProcessorResponseModel();
        /*
            * I'm not a fan of overusing comments but for this assignment it feels needed.
            * I decided to use the custom converter instead of the builder because i could not manage to apply the worker in a decent way within the time.
         */
        try{
            toReturn = statementProcessorService.validate(new StatementProcessorInputModel().converter(rawInput));
        }
        catch (JSONException je){
            toReturn.result = "BAD_REQUEST";
        }

        return toReturn;
    }
}
