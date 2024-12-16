package com.kvm.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    private int attempt=1;

    String url = "http://localhost:9092/authors/{id}";

   /* @CircuitBreaker(
            name = "bookService",
            fallbackMethod = "getBookByDefaultAuthor"
    )*/
    @Retry(name = "bookService", fallbackMethod = "getBookByDefaultAuthor")
    public Book getBookById(Integer id) {
        System.out.println("retry method called "+attempt++ +" times "+" at "+new Date());
        Author authorDetails = restTemplate.getForObject(url, Author.class, id);
        return getBook().stream().filter(book -> book.getId() == id).findFirst().map(book -> {
            book.setAuthor(authorDetails);
            return book;
        }).orElse(null);

    }

    private List<Book> getBook() {
        return List.of(new Book(101, "Java", null, 100, 5),
                new Book(102, "Spring", null, 200, 4),
                new Book(103, "Hibernate", null, 300, 3),
                new Book(104, "Microservices", null, 400, 2),
                new Book(105, "Kubernetes", null, 500, 1));
    }

    public Book getBookByDefaultAuthor(Integer id, Exception e) {
        Book defaultAuthorBook = getBook().stream().filter(book -> book.getId() == id).findFirst().orElse(null);
        if (defaultAuthorBook != null) {
            defaultAuthorBook.setAuthor(new Author(1, "xxxx", "xxxx"));
        }
        return defaultAuthorBook;
    }
}
