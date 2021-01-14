package gen;

import main.Conf;
import org.antlr.v4.runtime.tree.TerminalNode;
import values.*;
import org.apache.commons.text.StringEscapeUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Interpreter for the HaveFun language
 * Matteo Cavaliere VR437065 (University of Verona)
 */
public class IntHaveFun extends HaveFunBaseVisitor<Value> {
    /*
       Since functions are all declared at the beginning of
       the program, and since variables and functions may have the same id,
       it has been decided to separate the memories that handle functions
       and variables
     */
    private final LinkedList<Conf<ExpValue<?>>> memList;  // Variables memories (List used to handle scoping)
    private final Conf<FunValue> funMem;                  // Function memory

    public IntHaveFun() {
        memList = new LinkedList<>();
        memList.add(new Conf<>());
        funMem = new Conf<>();
    }

    @Override
    public Value visitProg(HaveFunParser.ProgContext ctx) {
        for(HaveFunParser.FunContext f: ctx.fun()) {
            visit(f);
        }

        return visitCom(ctx.com());
    }

    private ComValue visitCom(HaveFunParser.ComContext ctx) {
        return (ComValue) visit(ctx);
    }

    private ExpValue<?> visitExp(HaveFunParser.ExpContext ctx) {
        return (ExpValue<?>) visit(ctx);
    }

    private int visitNaturalExp(HaveFunParser.ExpContext ctx) {
        try {
            return ((NaturalValue) visit(ctx)).rawValue();
        } catch (ClassCastException e) {
            HaveFunErrors.typeMismatchError(ctx, HaveFunErrors.NAT_ERR);
        }

        return 0;       //unreachable code
    }

    private boolean visitBooleanExp(HaveFunParser.ExpContext ctx) {
        try {
            return ((BooleanValue) visit(ctx)).rawValue();
        } catch (ClassCastException e) {
            HaveFunErrors.typeMismatchError(ctx, HaveFunErrors.BOOL_ERR);
        }

        return false;   //unreachable code
    }

    private String visitStringExp(HaveFunParser.ExpContext ctx) {
        try {
            return ((StringValue) visit(ctx)).rawValue();
        } catch (ClassCastException e) {
            HaveFunErrors.typeMismatchError(ctx, HaveFunErrors.STRING_ERR);
        }

        return null;    //unreachable code
    }

    @Override
    public NaturalValue visitNat(HaveFunParser.NatContext ctx) {
        int n = Integer.parseInt(ctx.NAT().getText());
        return new NaturalValue(n);
    }

    @Override
    public BooleanValue visitBool(HaveFunParser.BoolContext ctx) {
        boolean b = Boolean.parseBoolean(ctx.BOOL().getText());
        return new BooleanValue(b);
    }

    @Override
    public StringValue visitString(HaveFunParser.StringContext ctx) {
        String s = ctx.STRING().getText();
        return new StringValue(
                StringEscapeUtils
                .unescapeJava(s.substring(1, s.length() - 1))
        );
    }

    @Override
    public FunValue visitFun(HaveFunParser.FunContext ctx) {
        String id = ctx.ID().getText();

        /* Check if function is already defined */
        if (funMem.get(id) != null) {
            HaveFunErrors.funAlreadyDefined(ctx);
        }

        /* Check void return */
        if (ctx.exp() == null) {
            HaveFunErrors.returnsVoid(ctx);
        }

        /* Check if an id is used more than once for formal arguments */
        LinkedList<String> params = new LinkedList<>();
        for (TerminalNode currentId: ctx.args().ID()) {
            if (params.contains(currentId.getText())) {
                HaveFunErrors.argsClash(ctx, currentId.getText());
            }

            params.add(currentId.getText());
        }

        FunValue f = new FunValue(id, params, ctx.com(), ctx.exp());
        funMem.put(id, f);
        return f;
    }

    @Override
    public ExpValue<?> visitFunCall(HaveFunParser.FunCallContext ctx) {
        String id = ctx.ID().getText();
        FunValue f = funMem.get(id);

        if (f == null) {
            HaveFunErrors.notDefined(ctx, id, HaveFunErrors.FUNC);
        }

        List<String> params = f.getParams();

        /* Check if actual parameters are in the same number as formal parameters */
        if (ctx.argsExp().exp().size() != params.size()) {
            HaveFunErrors.argsMismatch(ctx);
        }

        /* Initialize memory used by the function called */
        Conf<ExpValue<?>> tempMem = new Conf<>();
        for (int arg = 0; arg < params.size(); arg++) {
            tempMem.put(params.get(arg),
                    visitExp(ctx.argsExp().exp(arg)));
        }

        /* Handling scoping of function */
        memList.add(tempMem);
        if (f.getCom() != null)
            visit(f.getCom());
        ExpValue<?> returnValue = visitExp(f.getReturnExp());
        memList.removeLast();

        return returnValue;
    }

    @Override
    public StringValue visitTostr(HaveFunParser.TostrContext ctx) {
        return new StringValue(visitExp(ctx.exp()).rawValue().toString());
    }

    @Override
    public BooleanValue visitNot(HaveFunParser.NotContext ctx) {
        return new BooleanValue(!visitBooleanExp(ctx.exp()));
    }

    @Override
    public NaturalValue visitDivMulMod(HaveFunParser.DivMulModContext ctx) {
        int l = visitNaturalExp(ctx.exp(0));
        int r = visitNaturalExp(ctx.exp(1));
        return switch (ctx.op.getType()) {
            case HaveFunParser.DIV -> new NaturalValue(l / r);
            case HaveFunParser.MUL -> new NaturalValue(l * r);
            default -> new NaturalValue(l % r);
        };
    }

    @Override
    public NaturalValue visitPlusMinus(HaveFunParser.PlusMinusContext ctx) {
        int l = visitNaturalExp(ctx.exp(0));
        int r = visitNaturalExp(ctx.exp(1));
        return ctx.op.getType() == HaveFunParser.PLUS ?
                new NaturalValue(l + r) : new NaturalValue(l - r);
    }

    @Override
    public BooleanValue visitCmpExp(HaveFunParser.CmpExpContext ctx) {
        int l = visitNaturalExp(ctx.exp(0));
        int r = visitNaturalExp(ctx.exp(1));
        return switch (ctx.op.getType()) {
            case HaveFunParser.LT -> new BooleanValue(l < r);
            case HaveFunParser.LEQ -> new BooleanValue(l <= r);
            case HaveFunParser.GT -> new BooleanValue(l > r);
            default -> new BooleanValue(l >= r);
        };
    }

    @Override
    public NaturalValue visitPow(HaveFunParser.PowContext ctx) {
        int base = visitNaturalExp(ctx.exp(0));
        int exp = visitNaturalExp(ctx.exp(1));

        return new NaturalValue((int) Math.pow(base, exp));
    }

    @Override
    public ExpValue<?> visitParExp(HaveFunParser.ParExpContext ctx) {
        return visitExp(ctx.exp());
    }

    @Override
    public BooleanValue visitEqExp(HaveFunParser.EqExpContext ctx) {
        ExpValue<?> l = visitExp(ctx.exp(0));
        ExpValue<?> r = visitExp(ctx.exp(1));

        return ctx.op.getType() == HaveFunParser.EQQ ?
                new BooleanValue(l.equals(r)) : new BooleanValue(!l.equals(r));
    }

    @Override
    public ExpValue<?> visitAndOrConcatExp(HaveFunParser.AndOrConcatExpContext ctx) {
        if (ctx.op.getType() == HaveFunParser.CONCAT)
            return new StringValue(visitStringExp(ctx.exp(0)) + visitStringExp(ctx.exp(1)));

        boolean l = visitBooleanExp(ctx.exp(0));
        boolean r = visitBooleanExp(ctx.exp(1));

        return ctx.op.getType() == HaveFunParser.AND ?
                new BooleanValue(l && r) : new BooleanValue(l || r);
    }

    @Override
    public ExpValue<?> visitId(HaveFunParser.IdContext ctx) {
        String id = ctx.ID().getText();
        ExpValue<?> expValue = memList.getLast().get(id);
        if (expValue == null) {
            HaveFunErrors.notDefined(ctx, id, HaveFunErrors.VAR);
        }
        return expValue;
    }

    @Override
    public ExpValue<?> visitAccess(HaveFunParser.AccessContext ctx) {
        String id = ctx.ID().getText();
        ExpValue<?> expValue = memList.getLast().get(id);

        if (expValue == null) {
            HaveFunErrors.notDefined(ctx, id, HaveFunErrors.VAR);
        }

        int index = visitNaturalExp(ctx.exp());

        ArrayValue expValueAsArray = expValue instanceof ArrayValue ? ((ArrayValue) expValue) : null;
        if (expValueAsArray == null) {
            HaveFunErrors.typeMismatchError(ctx, HaveFunErrors.ARRAY_ERR);
        }

        if (expValueAsArray.get(index) == null) {
            HaveFunErrors.nullArrayValue(id, index);
        }

        return expValueAsArray.get(index);
    }

    @Override
    public ComValue visitSkip(HaveFunParser.SkipContext ctx) {
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitIf(HaveFunParser.IfContext ctx) {
        return visitBooleanExp(ctx.exp()) ?
                visitCom(ctx.com(0)) : visitCom(ctx.com(1));
    }

    @Override
    public ComValue visitAssign(HaveFunParser.AssignContext ctx) {
        ExpValue<?> val = visitExp(ctx.exp());
        String id = ctx.ID().getText();
        memList.getLast().put(id, val);
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitSeq(HaveFunParser.SeqContext ctx) {
        visitCom(ctx.com(0));
        visitCom(ctx.com(1));
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitArrayAssign(HaveFunParser.ArrayAssignContext ctx) {
        String id = ctx.ID().getText();

        /* Check if array is null or is not an array */
        if (!(memList.getLast().get(id) instanceof ArrayValue))
            memList.getLast().put(id, new ArrayValue(new ArrayList<>()));

        ((ArrayValue) memList.getLast().get(id))
                .set(visitNaturalExp(ctx.exp(0)), visitExp(ctx.exp(1)));

        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitOut(HaveFunParser.OutContext ctx) {
        System.out.println(visitExp(ctx.exp()).rawValue());
        return ComValue.INSTANCE;
    }

    @Override
    public ComValue visitWhile(HaveFunParser.WhileContext ctx) {
        while(visitBooleanExp(ctx.exp())) {
            visitCom(ctx.com());
        }
        return ComValue.INSTANCE;
    }
}
