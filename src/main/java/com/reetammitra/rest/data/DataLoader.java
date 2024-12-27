package com.reetammitra.rest.data;

import com.reetammitra.rest.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Retryable(retryFor = RuntimeException.class)
    public Person getPerson(int id) {
        System.out.println("Getting person for id: " + id);
        Person person = personMap.get(id);
        if (Objects.isNull(person)) {
            throw new RuntimeException();
        }
        return person;
    }

    @Recover
    public Person recover(RuntimeException e) {
        System.out.println("Returning null");
        return null;
    }
}
