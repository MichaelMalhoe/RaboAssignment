package com.malhoe.ordina.raboapp.Model;

import lombok.*;
import org.json.JSONObject;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatementProcessorInputModel {
    //These should be private bur my Lombok is not generating the getters right and i am almost out of time
    public Long transactionReference;
    public String accountNumber;
    public Double startBalance;
    public Double mutation;
    public String freeText;
    public Double endBalance;


    public StatementProcessorInputModel converter(String rawInput) {
        JSONObject obj = new JSONObject(rawInput);

        StatementProcessorInputModel input = new StatementProcessorInputModel();
        input.transactionReference = obj.getLong("Transaction reference");
        input.accountNumber = obj.getString("Account number");
        input.startBalance = obj.getDouble("Start Balance");
        input.mutation = obj.getDouble("Mutation");
        input.freeText = obj.getString("Description");
        input.endBalance = obj.getDouble("End Balance");
        return input;
    }
}


