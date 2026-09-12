package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class UserService<T>{

    private final UserFormatter<T> formatter;

    public UserService(UserFormatter<T> formatter){
        this.formatter=formatter;
    }

    /**
     * Подготавливает список строк с пользователями и их ролями
     * @param users исходный список юзеров для обработки
     * @return список строк для вывода в консоль
     */
    public List<T> prepareUserList(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::lastName))
                .map(formatter::formatUser)
                .collect(Collectors.toList());
    }
}
