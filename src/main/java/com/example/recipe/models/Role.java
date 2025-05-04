package com.example.recipe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Types type;

    @ManyToMany(mappedBy = "roles", fetch =FetchType.LAZY)
    private Set<User> users;

    public Role(Types type){
        this.type =type;
    }

    public enum Types{
    ROLE_USER,
    ROLE_ADMIN
    }

    @Override
    public String getAuthority(){
        return type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && type == role.type;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,type);
    }



}
