package com.converter.util;

import com.converter.dto.User;
import org.springframework.util.MultiValueMap;

public class UserDtoConverter {

    private UserDtoConverter() {

    }

    public static User convert(MultiValueMap<String, String> userMap) {

        return User.create(
                Integer.parseInt(userMap.getFirst("id")),
                userMap.getFirst("firstName"),
                userMap.getFirst("lastName"),
                Integer.parseInt(userMap.getFirst("age")),
                ArrayStringConverter.convert(userMap.getFirst("bookList")));
    }
}
