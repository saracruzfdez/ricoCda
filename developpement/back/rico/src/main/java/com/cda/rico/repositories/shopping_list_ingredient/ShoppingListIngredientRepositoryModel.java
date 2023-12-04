package com.cda.rico.repositories.shopping_list_ingredient;

import com.cda.rico.enums.UnitEnum;
import com.cda.rico.repositories.menu.MenuRepositoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="shopping_List_ingredient")
@Data
public class ShoppingListIngredientRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int quantity;

    // @Enumerated(EnumType.STRING) // Specifies Enum representated as a string
    @Column
    private String unit;

    // Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "menu_id")
    private MenuRepositoryModel menuRepositoryModel;
}
