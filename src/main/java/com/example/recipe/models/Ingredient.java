package com.example.recipe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ing_name;

    //establishing many-to-many relationship with recipe
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipe;

    public Ingredient(String name){
        this.ing_name = name;
    }
}
