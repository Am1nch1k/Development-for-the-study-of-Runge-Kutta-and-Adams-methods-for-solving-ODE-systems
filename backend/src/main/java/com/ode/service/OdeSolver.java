package com.ode.service;
import java.util.*;
import com.ode.dto.OdeResponse;
import com.ode.model.OdeSystem;

public interface OdeSolver {
    OdeResponse solve(OdeSystem system, List<Double> initialValues, 
                     double t0, double tEnd, double step);
}