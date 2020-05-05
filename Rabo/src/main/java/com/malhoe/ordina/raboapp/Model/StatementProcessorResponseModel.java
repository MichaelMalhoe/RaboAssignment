package com.malhoe.ordina.raboapp.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class StatementProcessorResponseModel {
    //These should be private with getters and setters but my lombok is having errors.
    //Initiating is wrong, i know. I ran out of time and this was the dirty fix so i could continue with the tests.
    public String result = "";
    public ArrayList<String> errorRecords = new ArrayList<>();
}
