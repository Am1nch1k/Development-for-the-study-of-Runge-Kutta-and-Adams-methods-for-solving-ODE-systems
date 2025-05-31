package com.ode.controller;

import com.ode.dto.OdeRequest;
import com.ode.dto.OdeResponse;
import com.ode.dto.SolverType;
import com.ode.service.OdeSolverService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ode")
@CrossOrigin(origins = "*")
public class OdeController {

    private final OdeSolverService solverService;

    public OdeController(OdeSolverService solverService) {
        this.solverService = solverService;
    }

    @PostMapping("/solve")
    public OdeResponse solve(@RequestBody OdeRequest request) {
        return solverService.solve(request);
    }
}