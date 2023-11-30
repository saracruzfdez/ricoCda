package com.cda.rico.services.ingredient;

import com.cda.rico.enums.UnitEnum;
import lombok.*;

@Data
public class IngredientServiceModel {
    private int id;
    private String name;
    private int quantity;
    private String unit;  // Changed to UnitEnum in the service
    private int recipe_id;

    public int getId(){return id;}
    public String getName(){return name;}
    public int getQuantity(){return quantity;}
    public String getUnit() { return unit; }  // Gtter returns UnitEnum
    public int getRecipe_id(){return recipe_id;}

    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR
}
