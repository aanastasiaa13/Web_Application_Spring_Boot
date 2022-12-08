package com.example.Web_Application_Spring.repository;

import com.example.Web_Application_Spring.model.Role;
import com.example.Web_Application_Spring.model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@Repository
public class UserDaoInSQL implements UserDao {
    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager
                    .getConnection(
                            "jdbc:postgresql://localhost:5432/USERS",
                            "postgres",
                            "postGRE"
                    );

            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQuerySQL(String sqlCommand) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(sqlCommand);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(User user) {
        boolean added = false;

        if (!existsByLoginAndPassword(user.getLogin(), user.getPassword())) {
            String sqlCommandInsertUser = String.format(
                    "INSERT INTO \"Authorization\".\"Users\" (login, password, name, birthday, age, salary) " +
                            "VALUES ('%s', '%s', '%s', '%s', %d, %d);",
                    user.getLogin(), user.getPassword(),
                    user.getName(), user.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    user.getAge(), user.getSalary());

            executeQuerySQL(sqlCommandInsertUser);

            if (existsByLoginAndPassword(user.getLogin(), user.getPassword())) {
                added = true;
            }

            if (user.getRole() != null) {
                for (Role role : user.getRole()) {
                    executeQuerySQL(String.format(
                            "INSERT INTO \"Role\".\"Users_Roles\" " +
                                    "SELECT \"users\".id, \"roles\".id " +
                                    "FROM \"Authorization\".\"Users\" AS \"users\", \"Role\".\"Roles\" AS \"roles\" " +
                                    "WHERE \"users\".login = '%s' AND \"roles\".role_name = '%s'",
                            user.getLogin(), role.getName()
                    ));
                }
            }
        }

        return added;
    }

    @Override
    public boolean update(User user, String person) {
        boolean edited = false;

        if (existsByLoginAndPassword(user.getLogin(), user.getPassword()) && user.getLogin().equals(person)) {
            String sqlCommandUpdateUser = String.format(
                    "UPDATE \"Authorization\".\"Users\" " +
                            "SET login = '%s'," +
                            "password = '%s'," +
                            "name = '%s'," +
                            "birthday = '%s'," +
                            "age = '%d'," +
                            "salary = '%d' " +
                            "WHERE login = '%s'",
                    user.getLogin(), user.getPassword(),
                    user.getName(), user.getBirthday(),
                    user.getAge(), user.getSalary(),
                    person
            );

            executeQuerySQL(sqlCommandUpdateUser);

            if (existsByLoginAndPassword(user.getLogin(), user.getPassword())) {
                edited = true;
            }

            executeQuerySQL(String.format(
                            "DELETE FROM \"Role\".\"Users_Roles\" WHERE user_id = (" +
                                    "SELECT id FROM \"Authorization\".\"Users\" WHERE login = '%s')", user.getLogin()
                    )
            );

            if (user.getRole() != null) {
                for (Role role : user.getRole()) {
                    executeQuerySQL(String.format(
                                    "INSERT INTO \"Role\".\"Users_Roles\" " +
                                            "SELECT \"users\".id, \"roles\".id " +
                                            "FROM \"Authorization\".\"Users\" AS \"users\", \"Role\".\"Roles\" AS \"roles\" " +
                                            "WHERE \"users\".login = '%s' AND \"roles\".role_name = '%s'",
                                    user.getLogin(), role.getName()
                            )
                    );
                }
            }
        }

        return edited;
    }

    @Override
    public void delete(String login) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("DELETE FROM \"Authorization\".\"Users\" WHERE login = '%s'", login);

            statement.executeUpdate(sqlCommand);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* delete */
    @Override
    public boolean checkLogin(String login, String password) {
        boolean checked = false;

        if (!login.equals("") || !password.equals("")) {
            try (Connection connection = getConnection()) {
                Statement statement = connection.createStatement();
                String sqlCommand = String.format("SELECT EXISTS(SELECT 1 FROM \"Authorization\".\"Users\" WHERE login = '%s' AND password = '%s');", login, password);

                ResultSet resultSet = statement.executeQuery(sqlCommand);

                while (resultSet.next()) {
                    checked = resultSet.getBoolean(1);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return checked;
    }
    /* delete */

    @Override
    public boolean checkData(String login, String password, String name, String birthday, String salary, int age) {
        boolean checked = false;

        if (!login.equals("") || !(password.equals("")) || !(name.equals("")) ||
                !(birthday.equals("")) || !salary.equals("")) {
            checked = Pattern.matches("[a-zA-Zа-яА-Я0-9]+", login) &&
                    Pattern.matches(".{8,}", password) &&
                    Pattern.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}", birthday) &&
                    Pattern.matches("[0-9]+", salary) &&
                    age >= 18;
        }

        return checked;
    }

    public boolean existsByLoginAndPassword(String login, String password) {
        boolean exist = false;

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT 1 FROM \"Authorization\".\"Users\" WHERE login = '%s' AND password = '%s'", login, password
            ));

            while (resultSet.next()) {
                if (resultSet.getBoolean(1)) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return exist;
    }

    @Override
    public Set<User> findAll() {
        Set<User> usersList = new LinkedHashSet<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Authorization\".\"Users\" ORDER BY id ASC");

            while (resultSet.next()) {
                LocalDate birthday = null;
                if (resultSet.getDate("birthday") != null) {
                    birthday = LocalDate.parse(new SimpleDateFormat("dd.MM.yyyy").format(resultSet.getDate("birthday")), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }

                usersList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        birthday,
                        resultSet.getInt("age"),
                        resultSet.getInt("salary"),
                        findRole(resultSet.getString("login")))
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return usersList;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM \"Authorization\".\"Users\" WHERE login = '%s'", login));
            while (resultSet.next()) {
                LocalDate birthday = null;
                if (resultSet.getDate("birthday") != null) {
                    birthday = LocalDate.parse(new SimpleDateFormat("dd.MM.yyyy").format(resultSet.getDate("birthday")), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }

                user = new User(
                        (new Random()).nextLong(),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        birthday,
                        resultSet.getInt("age"),
                        resultSet.getInt("salary"),
                        findRole(resultSet.getString("login"))
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<Role> findRole(String login) {
        List<Role> rolesList = new ArrayList<Role>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("SELECT \"roles_names\".role_name " +
                            "FROM \"Authorization\".\"Users\" AS \"users\", \"Role\".\"Roles\" AS \"roles_names\", \"Role\".\"Users_Roles\" AS \"users_roles\" " +
                            "WHERE \"users\".login = '%s' AND \"users\".id = \"users_roles\".user_id AND \"roles_names\".id = \"users_roles\".role_id;",
                    login
            );

            ResultSet resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                rolesList.add(new Role((new Random()).nextLong(), resultSet.getString("role_name")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rolesList;
    }
}
