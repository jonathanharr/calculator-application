package se.calculatorprogram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.calculatorprogram.util.EquationUtil;
import se.calculatorprogram.util.InputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Equation Class, holds the entire equation, except the returning answer.
 */
public class Equation {

    @Getter @Setter
    private List<Term> termArray;
    @Getter @Setter
    private String operationType;
    @Getter @Setter
    private boolean soloOperation;

    /**
     * Equation Costructor, only takes the Builder.
     * @param equationBuilder
     */
    public Equation(EquationBuilder equationBuilder) {
        this.termArray = equationBuilder.getTermArray();
        this.operationType = equationBuilder.getOperationType();
        this.soloOperation = equationBuilder.flyingSoloOperation();
    }

    /**
     * Builder class, helps build the Equation.
     */
    public static class EquationBuilder {
        @Getter @Setter
        private List<Term> termArray = new ArrayList<>();
        @Getter @Setter
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
         * @return true if this is a Solo Operation.
         */
        public boolean flyingSoloOperation(){
            return (EquationUtil.flyingSoloOperation(operationType));
        }

        /**
         * Adds the First Term, initiates the Array.
         * @param inputDTO
         */
        public EquationBuilder addFirstTermAndOperator(InputDTO inputDTO) {
            termArray.add(new Term(inputDTO.getInputFromCalculator()));
            this.operationType = inputDTO.getInputOperator();
            return this;
        }

        /**
         * Adds the operation to perform on the given calculation.
         * @param operator
         */
        public EquationBuilder addOperator(String operator) {
            this.operationType = operator;
            return this;
        }

        /**
         * Adds the second term, and final term of the Equation.
         * @param inputDTO
         */
        public EquationBuilder addSecondTerm(InputDTO inputDTO) {
            termArray.add(new Term(inputDTO.getInputFromCalculator()));
            return this;
        }

        /**
         * Returns a simple String of the Equations first bit.
         * @return
         */
        public String getEquationInputMessage() {
            return termArray.get(0).getTermString() + " " + EquationUtil.simpleOperator(operationType) + " ";
        }
    }
}
