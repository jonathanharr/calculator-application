package se.calculatorprogram.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * InputDTO class, holds an input Term, and an inputOperator.
 */
@NoArgsConstructor
@AllArgsConstructor
public class InputDTO {

    @Getter @Setter
    private String inputFromCalculator;
    @Getter @Setter
    private String inputOperator;
    @Override
    public String toString() {
        return inputFromCalculator + inputOperator;
    }
}
