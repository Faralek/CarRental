package com.kodilla.rentalcars.webServices;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class GasPrice {
    public String getGasPrice() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gas-price.p.rapidapi.com/europeanCountries")
                .get()
                .addHeader("x-rapidapi-key", "42995f5c38msh34d44863f18a2ffp1d27b8jsn3bae39b0ab2d")
                .addHeader("x-rapidapi-host", "gas-price.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }
}
