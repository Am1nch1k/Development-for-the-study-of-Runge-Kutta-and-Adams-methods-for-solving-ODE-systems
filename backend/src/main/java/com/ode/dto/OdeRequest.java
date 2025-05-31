package com.ode.dto;

import com.ode.dto.SolverType;
import lombok.Data;

import java.util.List;

@Data
public class OdeRequest {
    private List<String> equations;
    private List<Double> initialValues;
    private double t0;
    private double tEnd;
    private double step;
    private SolverType solverType;
}