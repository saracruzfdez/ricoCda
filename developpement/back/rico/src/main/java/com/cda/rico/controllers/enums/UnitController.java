package com.cda.rico.controllers.enums;

import com.cda.rico.enums.UnitEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unities")
public class UnitController {

    @GetMapping
    public UnitEnum[] getUnities() {
        return UnitEnum.values();
    }
}
