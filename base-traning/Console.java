import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class User {
    String login;
    String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

public class Console {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("heso", "js"));

        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Введите ваш логин: ");
            String userLogin = scan.nextLine().trim();

            System.out.print("Введите ваш пароль: ");
            String userPass = scan.nextLine().trim();

            Optional<User> userFound = validateUser(users, userLogin, userPass);

            if (userFound.isPresent()) {
                System.out.println("Привет, " + userFound.get().login + "!");
            } else {
                System.out.println("Пользователь не существует!");
            }
        }
    }

    private static Optional<User> validateUser(List<User> users, String login, String password) {
        return users.stream()
                .filter(user -> user.login.equalsIgnoreCase(login) && user.password.equals(password))
                .findFirst();
    }
}
