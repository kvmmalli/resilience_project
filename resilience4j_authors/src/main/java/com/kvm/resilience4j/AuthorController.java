package com.kvm.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/{id}")
    public Author getAuthor(@PathVariable("id") Integer id) {
        return authorService.getAuthorById(id);
    }


}
