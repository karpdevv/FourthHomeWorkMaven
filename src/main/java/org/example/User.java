package org.example;

public record User(int id, String firstName, String lastName, UserRole userRole) {
    public User {
        if (id<0){
            throw new IllegalArgumentException("id меньше нуля");
        }
        if (firstName==null || firstName.isBlank() || lastName==null || lastName.isBlank()){
            throw new IllegalArgumentException("Имя или фамилия не заполнены");
        }
        if (userRole==null){
            throw new IllegalArgumentException("Необходимо заполнить роль");
        }
    }

}

