package com.ode.service;

import com.ode.dto.OdeResponse;
import com.ode.model.OdeSystem;

import java.util.ArrayList;
import java.util.List;

public class AdamsSolver implements OdeSolver {
    
    @Override
    public OdeResponse solve(OdeSystem system, List<Double> initialValues, 
                           double t0, double tEnd, double step) {
        List<Double> tValues = new ArrayList<>();
        List<List<Double>> yValues = new ArrayList<>();
        List<List<Double>> fHistory = new ArrayList<>();
        
        // Используем Runge-Kutta для первых 4 точек
        RungeKuttaSolver rkSolver = new RungeKuttaSolver();
        OdeResponse rkResponse = rkSolver.solve(system, initialValues, t0, t0 + 3*step, step);
        
        tValues.addAll(rkResponse.getTValues());
        yValues.addAll(rkResponse.getYValues());
        
        // Сохраняем производные для первых 4 точек
        for (int i = 0; i < 4; i++) {
            fHistory.add(system.computeDerivatives(tValues.get(i), yValues.get(i)));
        }
        
        // Метод Адамса-Башфорта 4-го порядка
        for (int n = 4; n <= (int)((tEnd - t0)/step); n++) {
            double t = t0 + n*step;
            List<Double> yNew = new ArrayList<>();
            
            for (int i = 0; i < initialValues.size(); i++) {
                double yNext = yValues.get(n-1).get(i) + step/24 * (
                    55*fHistory.get(n-1).get(i) - 
                    59*fHistory.get(n-2).get(i) + 
                    37*fHistory.get(n-3).get(i) - 
                    9*fHistory.get(n-4).get(i)
                );
                yNew.add(yNext);
            }
            
            tValues.add(t);
            yValues.add(yNew);
            fHistory.add(system.computeDerivatives(t, yNew));
        }
        
        OdeResponse response = new OdeResponse();
        response.setTValues(tValues);
        response.setYValues(yValues);
        response.setMethodName("Adams-Bashforth 4th Order");
        return response;
    }
}