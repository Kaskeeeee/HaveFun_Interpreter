package values;

import gen.HaveFunParser;
import java.util.LinkedList;

/**
 * Class that represents functions
 */
public class FunValue extends Value {
    private final String id;
    private final LinkedList<String> params;
    private final HaveFunParser.ComContext com;
    private final HaveFunParser.ExpContext returnExp;

    public FunValue(String id, LinkedList<String> params, HaveFunParser.ComContext com, HaveFunParser.ExpContext returnExp) {
       this.id = id;
       this.params = params;
       this.com = com;
       this.returnExp = returnExp;
    }

    public LinkedList<String> getParams() {
        return params;
    }

    public HaveFunParser.ComContext getCom() {
        return com;
    }

    public HaveFunParser.ExpContext getReturnExp() {
        return returnExp;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FunValue && ((FunValue) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
