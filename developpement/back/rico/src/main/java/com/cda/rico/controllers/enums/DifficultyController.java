package com.cda.rico.controllers.enums;

import com.cda.rico.enums.DifficultyEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/difficulties")
public class DifficultyController {
    @GetMapping
    public DifficultyEnum[] getDifficulties() {
        return DifficultyEnum.values();
    }
}
