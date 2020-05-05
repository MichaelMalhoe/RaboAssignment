package com.malhoe.ordina.raboapp.Service;

import com.malhoe.ordina.raboapp.Model.StatementProcessorInputModel;
import com.malhoe.ordina.raboapp.Model.StatementProcessorResponseModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StatementProcessorService {
    // Given the time and assignment I understand there is no database involved, yet it feels not okay to use a temp. list in an application.
    private static ArrayList<Long> usedReferences = new ArrayList<>();

    public StatementProcessorResponseModel validate(StatementProcessorInputModel inputModel){
        StatementProcessorResponseModel responseModel = new StatementProcessorResponseModel();
        if(!validateUniqueReference(inputModel.transactionReference))
        {
            responseModel.errorRecords.add("account_number_of_ inCorrectEndBalance _record");
            responseModel.result = "DUPLICATE_REFERENCE";
        }

        if(!validateMutation(inputModel))
        {
            responseModel.errorRecords.add("account_number_of_ inCorrectEndBalance _record");
            if (!responseModel.result.isBlank()){
                responseModel.result = responseModel.result + "_INCORRECT_END_BALANCE";
            }
            else{
                responseModel.result = "INCORRECT_END_BALANCE";
            }
        }

        if (responseModel.result.isBlank()){
            responseModel.result = "SUCCESSFUL";
        }
        return responseModel;
    }


    private static boolean validateMutation(StatementProcessorInputModel inputModel){
        if (inputModel.mutation >= 0){
            return inputModel.startBalance + inputModel.mutation == inputModel.endBalance;
        }
        else{
            return inputModel.startBalance - inputModel.mutation == inputModel.endBalance;
        }
    }

    private static boolean validateUniqueReference(Long id){
        if (!usedReferences.contains(id)){
            usedReferences.add(id);
            return true;
        }
        return false;
    }
}
