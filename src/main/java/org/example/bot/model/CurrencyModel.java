package org.example.bot.model;

import lombok.Data;

import java.util.Date;

@Data
public class CurrencyModel {
    Integer cur_ID;
    String description;
    Double cur_OfficialRate;
    String cur_Abbreviation;
    Date date;
}
