package com.example.webs2023.ulti;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JsonFromInputConverter {
    public static String getInputStream(BufferedReader reader) {
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
