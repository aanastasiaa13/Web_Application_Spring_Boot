package com.example.Web_Application_Spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private LocalDate birthday;
    private int age;
    private int salary;
    private List<Role> role;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
