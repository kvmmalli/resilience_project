package com.kvm.resilience4j;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    int bookId;
    String authorName;
    String authorCountry;

}
