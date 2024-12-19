package com.fourtwall.kino.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.util.Map;

@Slf4j
public class HttpClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }
    private final OkHttpClient client = new OkHttpClient();

    public <T> T call(final String url, final Map<String, String> headers, final Class<T> responseClass) {
        var request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .build();

        return Try.withResources(() -> client.newCall(request).execute())
                .of(response -> Option.of(response.body())
                        .map(body -> fromJson(body, responseClass))
                        .get()
                ).get();
    }

    private <T> T fromJson(final ResponseBody body, final Class<T> targetClass) {
        return Try
                .of(() -> OBJECT_MAPPER.readValue(body.string(), targetClass))
                .getOrElseThrow(() -> new IllegalStateException("error reading body"));
    }
}
