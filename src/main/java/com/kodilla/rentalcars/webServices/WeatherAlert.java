package com.kodilla.rentalcars.webServices;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class WeatherAlert {
    public String getWeatherAlert() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://aerisweather1.p.rapidapi.com/alerts/jordan,mt")
                .get()
                .addHeader("x-rapidapi-key", "c2f4f4d115mshe35677673111b30p16edcejsn0e910caeaef4")
                .addHeader("x-rapidapi-host", "aerisweather1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }
}
