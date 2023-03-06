package com.stage.projet.controller;

import com.stage.projet.service.VirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.stage.projet.utils.Constants.ENDPOINT_VIREMENT;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_VIREMENT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VirementController {

    VirementService virementService;

    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }
}
