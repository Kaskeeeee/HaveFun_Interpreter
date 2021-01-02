package values;

import gen.HaveFunParser;

/**
 * Class that represents functions
 */
public class FunValue extends ExpValue<HaveFunParser.FunContext> {
    public FunValue(HaveFunParser.FunContext ctx) {
        super(ctx);
    }
}
