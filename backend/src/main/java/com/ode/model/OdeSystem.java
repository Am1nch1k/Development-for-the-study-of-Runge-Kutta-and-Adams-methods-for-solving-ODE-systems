package com.ode.model;

import java.util.List;
import java.util.function.BiFunction;

public class OdeSystem {
    private final List<BiFunction<Double, List<Double>, Double>> equations;

    public OdeSystem(List<BiFunction<Double, List<Double>, Double>> equations) {
        this.equations = equations;
    }

    public List<Double> computeDerivatives(double t, List<Double> y) {
        return equations.stream()
                .map(f -> f.apply(t, y))
                .toList();
    }
}