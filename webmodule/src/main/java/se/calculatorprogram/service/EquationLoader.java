package se.calculatorprogram.service;

import se.calculatorprogram.util.InputDTO;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Interface for EquationLoader.
 */
public interface EquationLoader {

    String getCalculatorMessage() throws UnsupportedEncodingException;

    void useCalculator(InputDTO inputDTO) throws UnsupportedEncodingException;

    List<String> getHistoryOfCalculations();
}
