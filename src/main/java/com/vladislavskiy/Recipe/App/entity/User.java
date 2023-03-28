package com.vladislavskiy.Recipe.App.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Comparable<User> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Size(min = 3, message = "name must be min 3 symb")
    private String name;
    @Size(min = 3, message = "surname must be min 3 symb")
    private String surname;
    @Size(min = 3, message = "email must be min 3 symb")
    private String email;
    private String hashPassword;
    @Size(min = 3, message = "password must be min 3 symb")
    @Transient
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recept> userRecepts;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id")
    )
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public int compareTo(User user) {
        return this.getName().compareTo(user.name);
    }

}
