package com.example.recipe.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "comment_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm--dd HH:mm", iso = DateTimeFormat.ISO.DATE)
    private LocalDate commentDate;

    @Column(name = "comment_recipe_id", nullable = false)
    private long commentRecipeId;

    @Column(name = "comment_recipe_name", nullable = false)
    private String commentRecipeName;

    @Column(name = "comment_user_name", nullable = false)
    private String commentUserName;


    public Comment(String text, LocalDate date, long id, String recipeName, String username){
        this.commentText = text;
        this.commentDate = date;
        this.commentRecipeId = id;
        this.commentRecipeName =  recipeName;
        this.commentUserName = username;
    }


}
