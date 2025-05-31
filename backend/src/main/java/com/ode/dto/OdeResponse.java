package com.ode.dto;

import lombok.Data;

import java.util.List;

@Data
public class OdeResponse {
    private List<Double> tValues;
    private List<List<Double>> yValues;
    private String methodName;
}