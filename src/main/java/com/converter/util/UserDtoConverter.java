package com.converter.util;

import com.converter.dto.User;
import org.springframework.util.MultiValueMap;

public class UserDtoConverter {

    private UserDtoConverter() {

    }

    public static User convert(MultiValueMap<String, String> userMap) {
        User user = new User();
        user.setFirstName(userMap.getFirst("firstName"));
        user.setLastName(userMap.getFirst("lastName"));
        user.setId(Integer.parseInt(userMap.getFirst("id")));
        user.setAge(Integer.parseInt(userMap.getFirst("age")));
        user.setBookList(ArrayStringConverter.convert(userMap.getFirst("bookList")));

        return user;
    }
}
