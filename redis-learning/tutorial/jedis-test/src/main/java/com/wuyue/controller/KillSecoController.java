package com.wuyue.controller;

import com.wuyue.service.KillSecoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author DeltaV235
 * @version 1.0
 * @className KillSecoController
 * @description
 * @date 2020/4/22 3:05
 */
@RestController
public class KillSecoController {
    private final KillSecoService service;

    public KillSecoController(KillSecoService service) {
        this.service = service;
    }

    @PostMapping("/doKill")
    public String doKillSeco(Integer pid) {
        Integer uid = new Random().nextInt(50000);
        boolean isSuccess = service.doKillSeco(uid, pid);
        if (isSuccess)
            return "{isSuccess: true}";
        return "{isSuccess: false}";
    }
}
