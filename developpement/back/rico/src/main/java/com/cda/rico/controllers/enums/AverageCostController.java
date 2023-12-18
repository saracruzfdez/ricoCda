package com.cda.rico.controllers.enums;

import com.cda.rico.enums.AverageCostEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/costs")
public class AverageCostController {

    @GetMapping
    public AverageCostEnum[] getCosts() {
        return AverageCostEnum.values();
    }
}
