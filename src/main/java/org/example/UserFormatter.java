package org.example;

public interface UserFormatter<T> {

    T formatUser(User user);

}


class StringFormatter implements UserFormatter<String>  {

    public String formatUser(User user){
        String roleLabel = user.userRole().getLabel();
        return String.format("ID: %d, %s %s (%s)", user.id(),  user.firstName(), user.lastName(), roleLabel);
    }
}
