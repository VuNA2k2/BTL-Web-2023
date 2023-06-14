package com.example.webs2023.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JsonFromInputConverter {
    public static String getInputStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }
}
