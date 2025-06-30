package org.example.bot.service;

import org.example.bot.model.CurrencyModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@Service
public class CurrencyService {

    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException, ParseException {
        JSONObject object = getJsonObject(message);

        model.setCur_ID(object.getInt("r030"));
        model.setDescription(object.getString("txt"));
        model.setCur_OfficialRate(object.getDouble("rate"));
        model.setCur_Abbreviation(object.getString("cc"));
        model.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(object.getString("exchangedate")));

        return String.format(
            "Official rate %s (%s)\n" +
            "On date: %s\n" +
            "Is: %.4f UAH",
            model.getDescription(),
            model.getCur_Abbreviation(),
            getFormatDate(model),
            model.getCur_OfficialRate()
        );
    }

    private static JSONObject getJsonObject(String message) throws IOException {
        URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=" + message + "&json");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();

        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
        }
        scanner.close();

        JSONArray jsonArray = new JSONArray(result.toString());

        if (jsonArray.isEmpty()) {
            throw new IOException("Currency not found");
        }

        return jsonArray.getJSONObject(0);
    }

    private static String getFormatDate(CurrencyModel model) {
        return new SimpleDateFormat("dd MMM yyyy").format(model.getDate());
    }
}