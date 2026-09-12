package org.example;

public sealed interface UserRole permits Admin, StandardUser {
    public String getLabel();

}

record Admin() implements UserRole{
    @Override
    public String getLabel() {
        return "Администратор";
    }
};

record StandardUser() implements UserRole{
    @Override
    public String getLabel() {
        return "Пользователь";
    }
};


