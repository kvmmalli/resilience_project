package com.kvm.resilience4j;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    Integer bookId;
    String authorName;
    String authorCountry;

}
