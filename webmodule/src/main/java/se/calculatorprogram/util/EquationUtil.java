package se.calculatorprogram.util;

/**
 * Utility Class for handling Equations, helps check if the operation is doable within the constraints
 * of this calculator application.
 */
public final class EquationUtil {

    private static final String OPERATION_ADD = "add";
    private static final String OPERATION_MINUS = "min";
    private static final String OPERATION_MULTIPLY = "mult";
    private static final String OPERATION_DIVIDE = "div";
    private static final String OPERATION_MOD = "mod";
    private static final String OPERATION_MAX = "max";
    private static final String OPERATION_MIN = "min";
    private static final String OPERATION_CEI = "cei";
    private static final String OPERATION_FLOOR = "floor";
    private static final String OPERATION_ROUND = "round";
    private static final String OPERATION_EQUALS_ITSELF = "=";

    private static final String[] operationUtilList = new String[]{OPERATION_ADD, OPERATION_MINUS, OPERATION_MULTIPLY, OPERATION_DIVIDE, OPERATION_MOD,
            OPERATION_MAX, OPERATION_MIN, OPERATION_CEI, OPERATION_FLOOR, OPERATION_ROUND, OPERATION_EQUALS_ITSELF};

    /**
     * Checks if the OperationType of a given calculation is a Solo Operation or not.
     *
     * @param operationType being the Operation to perform of the Equation.
     * @return true if Term result is calculateable on its own.
     */
    public static boolean flyingSoloOperation(String operationType) {
        return ((operationType.equalsIgnoreCase(OPERATION_CEI)) || (operationType.equalsIgnoreCase(OPERATION_FLOOR)) || (operationType.equalsIgnoreCase(OPERATION_ROUND)));
    }

    /**
     * Checks if the given value matches one of the available operations.
     *
     * @param value being the operation to check.
     * @return true if any operation matches.
     */
    private static boolean isThisAnOperationValue(String value) {
        for (String s : operationUtilList) {
            if (value.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the input is of any use for the application, is it an operator or a term?
     * <p>
     * Left unused, but saved for the future.
     *
     * @param input most likely being from the URL method.
     * @return true if input is correct.
     */
    public static boolean isThisQuantifiable(String input) {
        return ((!input.isEmpty()) && ((isThisAnOperationValue(input)) || (input.matches("-?\\d+(\\.\\d+)?"))));
    }

    /**
     * Inputs one of the {@link se.calculatorprogram.model.OperationType} and converts it into a readable value
     *
     * @param operationType being the Operation to perform of the Equation.
     * @return + - * / and more..
     */
    public static String simpleOperator(String operationType) {
        switch (operationType) {
            case "add":
                return ("+");
            case "sub":
                return ("-");
            case "div":
                return ("/");
            case "mult":
                return ("*");
            case "max":
                return ("MAX");
            case "min":
                return ("MIN");
            case "floor":
                return ("FLOOR");
            case "mod":
                return ("%");
            default:
                return operationType;
        }
    }
}
