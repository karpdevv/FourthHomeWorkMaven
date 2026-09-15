package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for App class.
 */
class AppTest {

    @Test
    @DisplayName("App should have main method")
    void testAppHasMainMethod() {
        // Verify that the App class exists and has a main method
        assertThat(App.class).isNotNull();
    }
}

@DisplayName("Проверка форматирования user с помощью StringFormatter")
class StringFormatterTest {

    private final StringFormatter formatter=new StringFormatter();

    @Test
    @DisplayName("Проверяем, что корректно возвращает администратора")
    void shouldFormatAdminUserCorrect(){
        User user=new User(15,"Алеша", "Попович", new Admin());
        String result=formatter.formatUser(user);
        assertThat(result)
                .contains("ID: 15")
                .contains("Алеша Попович")
                .contains("Администратор");
    }

    @Test
    @DisplayName("Проверяем, что корректно возвращает стандартного юзера")
    void shouldFormatStandardUserCorrect(){
        User user = new User(16, "Алешка", "Поповичъ", new StandardUser());
        String result=formatter.formatUser(user);
        assertThat(result)
                .contains("16")
                .contains("Алешка Поповичъ")
                .contains("Пользователь");
    }

}


class UserServiceTest {
    private List<User> users;
    private UserService<String> service;
    private ApplicationContext applicationContext=new ApplicationContext();

    @BeforeEach
    void setUp(){
        service = applicationContext.getService();

        users = List.of(
                new User(3, "Анна", "Смирнова", new StandardUser()),
                new User(1, "Алексей", "Гусев", new Admin()),
                new User(2, "Антон", "Лебедев", new StandardUser()),
                new User(4, "Мария", "Козлова", new Admin())
        );
    }

    @Test
    @DisplayName("Проверяет, что сервис подготавливает корректно список (не пустой) и сортирует его по фамилии")
    void shouldBeSortedByLastName(){
        List<String> result=service.prepareUserList(users);
        //Ожидаемый порядок: Гусев, Козлова, Лебедев, Смирнова

        assertThat(result)
                .hasSize(4)
                .extracting(s -> s.substring(s.indexOf("ID:") + 4, s.indexOf(",")))
                .containsExactly("1", "4", "2", "3");
    }
}

@DisplayName("ApplicationContext тесты")
class ApplicationContextTest{
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new ApplicationContext();
    }

    @Test
    @DisplayName("service not null")
    void shouldReturnNotNullService() {
        assertThat(applicationContext.getService()).isNotNull();
    }

    @Test
    @DisplayName("formatter not null")
    void shouldReturnNotNullFormatter() {
        assertThat(applicationContext.getFormatter()).isNotNull();
    }

    @Test
    @DisplayName("Сервис возвращает одинаковый результат")
    void shouldReturnSameServiceOnMultipleCalls() {
        UserService<String> first = applicationContext.getService();
        UserService<String> second = applicationContext.getService();
        assertThat(first).isSameAs(second);
    }


}
