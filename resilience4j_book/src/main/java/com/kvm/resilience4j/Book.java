package com.kvm.resilience4j;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private Author author;
    private int price;
    private int rating;

}