package com.ode.service;

import com.ode.dto.OdeRequest;
import com.ode.dto.OdeResponse;
import com.ode.dto.SolverType;
import com.ode.model.OdeSystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class OdeSolverService {

    public OdeResponse solve(OdeRequest request) {
        // Создаем систему ОДУ
        List<BiFunction<Double, List<Double>, Double>> equations = new ArrayList<>();
        for (String eq : request.getEquations()) {
            equations.add(parseEquation(eq));
        }
        OdeSystem system = new OdeSystem(equations);

        // Выбираем решатель
        OdeSolver solver = switch (request.getSolverType()) {
            case RUNGE_KUTTA_4 -> new RungeKuttaSolver();
            case ADAMS_BASHFORTH_4 -> new AdamsSolver();
        };

        // Решаем систему
        return solver.solve(system, request.getInitialValues(), 
                          request.getT0(), request.getTEnd(), request.getStep());
    }

    private BiFunction<Double, List<Double>, Double> parseEquation(String eq) {
        // Простейший парсер уравнений (для демонстрации)
        return (t, y) -> {
            if (eq.contains("y2 + sin(t)")) return y.get(1) + Math.sin(t);
            if (eq.contains("-y1 + cos(t)")) return -y.get(0) + Math.cos(t);
            throw new IllegalArgumentException("Unknown equation: " + eq);
        };
    }
}