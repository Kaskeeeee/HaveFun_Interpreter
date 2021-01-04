package gen;


import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Helper class used to handle runtime errors
 */
public class HaveFunErrors {
    private HaveFunErrors() {}

    public static final String NAT_ERR = "> Numerical expression expected";
    public static final String BOOL_ERR = "> Boolean expression expected";
    public static final String STRING_ERR = "> String expression expected";
    public static final String ARRAY_ERR = "> Array expression expected";
    public static final String FUNC = "Function";
    public static final String VAR = "Variable";

    public static void typeMismatchError(HaveFunParser.ExpContext ctx, String errMessage) {
        System.err.println("Type mismatch in the expression:");
        System.err.println(ctx.getText());
        System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine());
        System.err.println(errMessage);

        System.exit(1);
    }

    public static void funAlreadyDefined(HaveFunParser.FunContext ctx) {
        System.err.println("Function named " + ctx.ID().getText() + " is already defined:");
        System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " > " +
            ctx.FUNC().getText() + " " + ctx.ID().getText() + "(" + ctx.args().getText() + ")");
        System.err.println("> Please use different identifiers for functions' names");

        System.exit(1);
    }

    public static void notDefined(HaveFunParser.ExpContext ctx, String id, String type) {
        System.err.println(type + " named " + id + " is not defined:");

        System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() +
                " > " + ctx.getText());

        System.exit(1);
    }

    public static void argsMismatch(HaveFunParser.FunCallContext ctx) {
        System.err.println("Function called with the wrong number of arguments:");
        System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() +
                " > " + ctx.getText());

        System.exit(1);
    }

    public static void nullArrayValue(String id, int index) {
        System.err.println("Element '" + id + "[" + index + "]' used but never assigned");
        System.exit(1);
    }

    public static void argsClash(HaveFunParser.FunContext ctx, String id) {
        System.err.println("Parameter name " + id + " clashes with previous parameters");
        System.err.println("@" + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() +
                " > " + ctx.ID().getText() + "(" + ctx.args().getText() + ")");
        System.exit(1);
    }
}
