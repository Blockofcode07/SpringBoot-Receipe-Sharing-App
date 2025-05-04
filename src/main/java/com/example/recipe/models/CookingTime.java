package com.example.recipe.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CookingTime {

    @NotNull
    private int h;

    @NotNull
    private int min;

    public String toString() {
        return String.format("%02d:%02d", this.getH(),this.getMin());
    }
}
