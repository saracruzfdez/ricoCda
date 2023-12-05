package com.cda.rico.services.step;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class StepServiceModel {
    private int id;
    private String name;
    private String description;
    private int recipe_id;

    // POST, PLUS BESOIN GRACE A MAPSTRUCT QUI GENERE LE CONSTRUCTEUR

}