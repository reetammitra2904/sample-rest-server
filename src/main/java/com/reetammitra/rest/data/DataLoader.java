package com.reetammitra.rest.data;

import com.reetammitra.rest.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataLoader {

    private final Map<Integer, Person> personMap = new HashMap<>();

    @PostConstruct
    private void loadData() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("person-details.csv");
        try {
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0]);
                String firstName = tokens[1];
                String lastName = tokens[2];
                personMap.put(id, new Person(id, firstName, lastName));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPerson(int id) {
        return personMap.get(id);
    }
}
