package com.ode.service;

import com.ode.dto.OdeResponse;
import com.ode.model.OdeSystem;

import java.util.ArrayList;
import java.util.List;

public class RungeKuttaSolver implements OdeSolver {
    
    @Override
    public OdeResponse solve(OdeSystem system, List<Double> initialValues, 
                           double t0, double tEnd, double step) {
        List<Double> tValues = new ArrayList<>();
        List<List<Double>> yValues = new ArrayList<>();
        
        double t = t0;
        List<Double> y = new ArrayList<>(initialValues);
        
        tValues.add(t);
        yValues.add(new ArrayList<>(y));
        
        while (t < tEnd) {
            List<Double> k1 = system.computeDerivatives(t, y);
            List<Double> k2 = system.computeDerivatives(t + step/2, add(y, multiply(k1, step/2)));
            List<Double> k3 = system.computeDerivatives(t + step/2, add(y, multiply(k2, step/2)));
            List<Double> k4 = system.computeDerivatives(t + step, add(y, multiply(k3, step)));
            
            for (int i = 0; i < y.size(); i++) {
                y.set(i, y.get(i) + step/6 * (k1.get(i) + 2*k2.get(i) + 2*k3.get(i) + k4.get(i)));
            }
            
            t += step;
            tValues.add(t);
            yValues.add(new ArrayList<>(y));
        }
        
        OdeResponse response = new OdeResponse();
        response.setTValues(tValues);
        response.setYValues(yValues);
        response.setMethodName("Runge-Kutta 4th Order");
        return response;
    }
    
    private List<Double> add(List<Double> a, List<Double> b) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            result.add(a.get(i) + b.get(i));
        }
        return result;
    }
    
    private List<Double> multiply(List<Double> a, double factor) {
        List<Double> result = new ArrayList<>();
        for (Double value : a) {
            result.add(value * factor);
        }
        return result;
    }
}