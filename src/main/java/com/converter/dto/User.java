package com.converter.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> bookList;

    @Builder(builderMethodName = "of")
    private User(int id, String firstName, String lastName, int age, List<String> bookList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.bookList = bookList;
    }

    public static User create(int id, String firstName, String lastName, int age, List<String> bookList) {
        return User.of()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .bookList(bookList)
                .build();
    }
}


