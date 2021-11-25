package com.example.democlient.dao;

import com.example.democlient.model.Person;
import com.example.democlient.model.PersonList;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PersonDao {
    private final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
    private final String DEFAULT_URL = "http://localhost:8080/SpringBootCrudService";
    private final String URL_READ_PERSON = DEFAULT_URL + "/person/";
    private final String URL_ADD_PERSON = DEFAULT_URL + "/addPerson/";
    private final String URL_DELETE_PERSON = DEFAULT_URL + "/deletePerson/";
    private final String URL_READ_ALL = DEFAULT_URL + "/allPersons/";
    private final String URL_UPDATE_PERSON = DEFAULT_URL + "/updatePerson/";

    public ResponseEntity<Person> read(int id) {
        return exchangeTemplate(URL_READ_PERSON + id, HttpMethod.GET, new HttpEntity<>(createHeaders()));
    }

    public ResponseEntity<Person> create(Person person) {
        return exchangeTemplate(URL_ADD_PERSON, HttpMethod.POST, new HttpEntity<>(person, createHeaders()));
    }

    public ResponseEntity<Person> delete(int personId) {
        return exchangeTemplate(URL_DELETE_PERSON + personId, HttpMethod.DELETE, new HttpEntity<>(createHeaders()));
    }

    public ResponseEntity<PersonList> readAll() {
        return getTemplate().exchange(URL_READ_ALL, HttpMethod.GET, new HttpEntity<>(new PersonList(), createHeaders()), PersonList.class);
    }

    public ResponseEntity<Person> update(Person person) {
        return exchangeTemplate(URL_UPDATE_PERSON, HttpMethod.PUT, new HttpEntity<>(person, createHeaders()));
    }

    private ResponseEntity<Person> exchangeTemplate(String url, HttpMethod method, HttpEntity<Person> entity) {
        return getTemplate().exchange(url, method, entity, Person.class);
    }

    private RestTemplate getTemplate() {
        return new RestTemplate();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MEDIA_TYPE));
        headers.setContentType(MEDIA_TYPE);

        return headers;
    }
}
