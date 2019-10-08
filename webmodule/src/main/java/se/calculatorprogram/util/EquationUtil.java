package se.calculatorprogram.util;

/**
 * Utility Class for handling Equations, helps check if the operation is doable within the constraints
 * of this calculator application.
 */
public final class EquationUtil {

    public static final String OPERATION_ADD = "add";
    public static final String OPERATION_MINUS = "min";
    public static final String OPERATION_MULTIPLY = "mult";
    public static final String OPERATION_DIVIDE = "div";
    public static final String OPERATION_MOD = "mod";
    public static final String OPERATION_MAX = "max";
    public static final String OPERATION_MIN = "min";
    public static final String OPERATION_CEI = "cei";
    public static final String OPERATION_FLOOR = "floor";
    public static final String OPERATION_ROUND = "round";
    public static final String OPERATION_EQUALS_ITSELF = "=";

    public static final String[] operationUtilList = new String[]{OPERATION_ADD, OPERATION_MINUS, OPERATION_MULTIPLY, OPERATION_DIVIDE, OPERATION_MOD,
            OPERATION_MAX, OPERATION_MIN, OPERATION_CEI, OPERATION_FLOOR, OPERATION_ROUND, OPERATION_EQUALS_ITSELF};

    /**
     * Checks if the OperationType of a given calculation is a Solo Operation or not.
     *
     * @param operationType being the Operation to perform of the Equation.
     * @return
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
    public static boolean isThisAnOperationValue(String value) {
        for (int i = 0; i < operationUtilList.length; i++) {
            if (value.equalsIgnoreCase(operationUtilList[i])) {
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
     * @param operationType
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
