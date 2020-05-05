package com.malhoe.ordina.raboapp.Service;

import com.malhoe.ordina.raboapp.Model.StatementProcessorInputModel;
import com.malhoe.ordina.raboapp.Model.StatementProcessorResponseModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StatementProcessorServiceTest {

    @Autowired
    StatementProcessorService statementProcessorService;

    private StatementProcessorInputModel setup(long reference, boolean endbalanceCorrect) {
        StatementProcessorInputModel statementProcessorInputModel = new StatementProcessorInputModel();
        statementProcessorInputModel.startBalance = 1.00;
        statementProcessorInputModel.transactionReference = reference;
        statementProcessorInputModel.accountNumber = "2";
        statementProcessorInputModel.startBalance = 1.00;
        statementProcessorInputModel.mutation = 1.00;
        statementProcessorInputModel.freeText = "Random text";
        statementProcessorInputModel.endBalance = endbalanceCorrect ? 2.00 : 1.00;
        return statementProcessorInputModel;
    }

    @Test
    void verify_with_all_good_settings()
    {
        StatementProcessorResponseModel sut;
        sut = statementProcessorService.validate(setup(1, true));
        Assert.assertEquals(sut.result, "SUCCESSFUL");
    }

    @Test
    void verify_with_good_settings_but_duplicated_reference()
    {
        StatementProcessorResponseModel sutFirst = statementProcessorService.validate(setup(2, true));
        Assert.assertEquals(sutFirst.result, "SUCCESSFUL");
        StatementProcessorResponseModel sutSecond;
        sutSecond = statementProcessorService.validate(setup(2, true));
        Assert.assertEquals(sutSecond.result, "DUPLICATE_REFERENCE");
    }

    @Test
    void verify_with_wrong_endBalance_and_good_reference()
    {
        StatementProcessorResponseModel sut;
        sut = statementProcessorService.validate(setup(3, false));
        Assert.assertEquals(sut.result, "INCORRECT_END_BALANCE");
    }

    @Test
    void verify_with_all_wrong_settings()
    {
        StatementProcessorResponseModel sutFirst = statementProcessorService.validate(setup(4, true));
        Assert.assertEquals(sutFirst.result, "SUCCESSFUL");
        StatementProcessorResponseModel sutSecond = statementProcessorService.validate(setup(4, false));
        Assert.assertEquals(sutSecond.result, "DUPLICATE_REFERENCE_INCORRECT_END_BALANCE");
    }
}