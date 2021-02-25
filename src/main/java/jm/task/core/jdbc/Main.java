package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        //Создание таблицы User
        userService.dropUsersTable();
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        try(Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < 4; i++) {
                System.out.print("Input name: ");
                String name = scanner.next();

                System.out.print("Input lastname: ");
                String lastname = scanner.next();

                System.out.print("Input age: ");
                byte age = scanner.nextByte();


                userService.saveUser(name, lastname, age);

                System.out.println("User с именем – " + name + " добавлен в базу данных");

            }
        }

        //Получение всех User из базы и вывод в консоль
        System.out.println("-------------------------");
        System.out.println("Получение всех User из базы");
        System.out.println(userService.getAllUsers());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        System.out.println("-------------------------");
        System.out.println("таблица очищена");
        System.out.println(userService.getAllUsers());

        //Удаление таблицы
        userService.dropUsersTable();
        System.out.println("-------------------------");
        System.out.println("Таблица удалена");
    }
}
