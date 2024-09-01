package com.reetammitra.rest.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    private static final int NUMBER_OF_RECORDS = 30;

    public static void main(String[] args) throws IOException {
        int id = 1;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/person-details.csv"));
        while (id <= NUMBER_OF_RECORDS) {
            bufferedWriter.write(id + "," + RandomStringUtils.randomAlphabetic(5) + "," + RandomStringUtils.randomAlphabetic(5));
            bufferedWriter.newLine();
            id++;
        }
        bufferedWriter.close();
    }
}
