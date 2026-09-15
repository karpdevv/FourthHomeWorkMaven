package org.example;

public class ApplicationContext {

    final private UserFormatter<String> formatter; ;
    final private UserService<String> service;

    public ApplicationContext() {
        this.formatter = new StringFormatter();
        this.service = new UserService<>(formatter);
    }


    public UserFormatter<String> getFormatter() {
        return formatter;
    }

    public UserService<String> getService() {
        return service;
    }


}
