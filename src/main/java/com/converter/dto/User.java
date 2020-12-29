package com.converter.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> bookList;

}


