package org.example;

import java.util.List;

/**
 * Main application class.
 */
public class App {

    /**
     * Main entry point.
     * @param args command line arguments
     */
    public static void main(String[] args) {



        List<User> userList = List.of(
                new User(1, "Алексей", "Гусев", new Admin()),
                new User(2, "Антон", "Трунин", new StandardUser()),
                new User(3, "Максим", "Михайлов", new StandardUser()),
                new User(4, "Вадим", "Михалыч", new StandardUser()),
                new User(5, "Александр", "Филин", new Admin())
        );

        UserFormatter<String> formatter=new StringFormatter();
        UserService<String> service = new UserService<>(formatter);

        List<String> userString=service.prepareUserList(userList);

        System.out.println("Список всех пользователей:");
        userString.forEach(System.out::println);
        System.out.println("\n");


        System.out.println("Список администраторов:");
        for (String s : userString) {
            if(s.contains("Администратор")) {
                System.out.println(s);
            }
        }

    }
}
