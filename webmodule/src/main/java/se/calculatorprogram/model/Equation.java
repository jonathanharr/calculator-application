package se.calculatorprogram.model;

import lombok.Getter;
import lombok.Setter;
import se.calculatorprogram.util.EquationUtil;
import se.calculatorprogram.util.InputDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Equation Class, holds the entire equation, except the returning answer.
 */
public class Equation {

    @Getter
    @Setter
    private List<Term> termArray;
    @Getter
    @Setter
    private String operationType;
    @Getter
    @Setter
    private boolean soloOperation;

    /**
     * Equation Costructor, only takes the Builder.
     *
     * @param equationBuilder being the Builder to apply to Equation, handles all Constructive variables.
     */
    private Equation(EquationBuilder equationBuilder) {
        this.termArray = equationBuilder.getTermArray();
        this.operationType = equationBuilder.getOperationType();
        this.soloOperation = equationBuilder.flyingSoloOperation();
    }

    /**
     * Builder class, helps build the Equation.
     */
    public static class EquationBuilder {
        @Getter
        @Setter
        private List<Term> termArray = new ArrayList<>();
        @Getter
        @Setter
        private String operationType;

        public EquationBuilder(InputDTO inputDTO) {
            termArray.add(new Term(inputDTO.getInputFromCalculator()));
            this.operationType = inputDTO.getInputOperator();
        }

        public Equation buildEquation() {
            return new Equation(this);
        }

        /**
         * Checks if the Equation is a Solo Operation or not by asking {@link EquationUtil}
         *
         * @return true if this is a Solo Operation.
         */
        public boolean flyingSoloOperation() {
            return (EquationUtil.flyingSoloOperation(operationType));
        }

        /**
         * Adds the First Term, initiates the Array.
         *
         * @param inputDTO being input from User.
         */
        public EquationBuilder addFirstTermAndOperator(InputDTO inputDTO) {
            termArray.add(new Term(inputDTO.getInputFromCalculator()));
            this.operationType = inputDTO.getInputOperator();
            return this;
        }

        /**
         * Adds the operation to perform on the given calculation.
         *
         * @param operator operator in mathematical terms.
         */
        public EquationBuilder addOperator(String operator) {
            this.operationType = operator;
            return this;
        }

        /**
         * Adds the second term, and final term of the Equation.
         *
         * @param inputDTO being input from User.
         */
        public void addSecondTerm(InputDTO inputDTO) {
            termArray.add(new Term(inputDTO.getInputFromCalculator()));
        }

        /**
         * Returns a simple String of the Equations first bit.
         *
         * @return EquationInputString of format "number operator".
         */
        public String getEquationInputMessage() {
            return termArray.get(0).getTermString() + " " + EquationUtil.simpleOperator(operationType) + " ";
        }
    }
}
