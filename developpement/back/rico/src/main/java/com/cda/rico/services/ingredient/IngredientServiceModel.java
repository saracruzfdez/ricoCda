package com.cda.rico.services.ingredient;

import com.cda.rico.enums.UnitEnum;
import lombok.*;

@Data
public class IngredientServiceModel {
    private int id;
    private String name;
    private int quantity;
    private UnitEnum unit;  // Changed to UnitEnum in the service
    private int recipe_id;



    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR
}
