package com.kvm.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthorService {

    public Author getAuthorById(Integer id) {
        return getAuthors().stream().filter(author -> author.getBookId().equals(id)).findFirst().orElse(null);
    }

    private List<Author> getAuthors() {
        return List.of(new Author(101,"John","Nellore"),
                new Author(102, "Riyan","Hyderabad"),
                new Author(103,"Parag","Chennai"),
                new Author(104, "Williams","Bangalore"),
                new Author(105, "Jerry","Delhi"));
    }
}
