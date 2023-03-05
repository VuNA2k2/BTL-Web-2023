package com.example.webs2023.utils;

import java.io.BufferedReader;
import java.util.stream.Collectors;

public class JsonFromInputConverter {
    public static String getInputStream(BufferedReader reader) {
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
