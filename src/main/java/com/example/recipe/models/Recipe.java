package com.example.recipe.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


import java.time.LocalDate;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@AllArgsConstructor
@NamedQuery(name = "Recipe.findByName", query = "select r from Recipe c where r.name like ?1")
@NamedQuery(name = "Recipe.findByCategory", query = "select r from Recipe r where r.category.name like ?1" )
@NamedQuery(name = "Recipe.findByCookingTime", query = "select r from Recipe r where r.cookingTime.h = ?1 AND r.cookingTime.min = ?2")
@NamedQuery(name = "Recipe.findByDate", query = "select r from Recipe r where r.date = ?1")
@NamedQuery(name = "Recipe.findByCost", query = "select r from Recipe r where r.cost = ?1")
@NamedQuery(name = "Recipe.findByIngredients", query = "select r from Recipe r join r.ingredients i where ?1 = i.ing_name")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    @Column(name = "main_recipe", nullable = false, length = 1000)
    @NotBlank
    private String main_recipe;


    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate date;

    @Column(name = "vege", nullable = false)
    private boolean vege;

    @Column(name = "cost", nullable = false)
    @NumberFormat(pattern = "0.00")
    @Min(1) @Max(1000)
    private double cost;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Valid
    private Category category;

    @Embedded
    @Valid
    private CookingTime cookingTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "recipes")
    private Set<Recipebook> recipeBook;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] fileContent;

    @NotBlank
    private String fileName;

    @Column(name = "commentsCount", nullable = false)
    private int commentsCount = 0;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    public Recipe(String name, String main_recipe, LocalDate date, boolean vege, double cost,
                  Category category, CookingTime cookingTime, String diff, Set<Ingredient> ingredients){
        this.name = name;
        this.main_recipe = main_recipe;
        this.date = date;
        this.vege = vege;
        this.cost = cost;
        this.category = category;
        this.cookingTime = cookingTime;
        this.difficulty = diff;
        this.ingredients = ingredients; //Initialised ingredients set
    }

    public Recipe(){
        this.ingredients = new HashSet<Ingredient>();
    }

    public String fileContentTo64(){
        return Base64.getEncoder().encodeToString(this.getFileContent());
    }

}
