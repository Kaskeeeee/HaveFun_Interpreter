package gen;

// Generated from D:/IdeaProjects/HaveFunLanguage/src\HaveFun.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HaveFunParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HaveFunVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HaveFunParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(HaveFunParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaveFunParser#fun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun(HaveFunParser.FunContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaveFunParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(HaveFunParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAssign}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssign(HaveFunParser.ArrayAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(HaveFunParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(HaveFunParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(HaveFunParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code seq}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeq(HaveFunParser.SeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(HaveFunParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code out}
	 * labeled alternative in {@link HaveFunParser#com}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOut(HaveFunParser.OutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nat}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNat(HaveFunParser.NatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tostr}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTostr(HaveFunParser.TostrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusMinus}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(HaveFunParser.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code access}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(HaveFunParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(HaveFunParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(HaveFunParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funCall}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(HaveFunParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divMulMod}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivMulMod(HaveFunParser.DivMulModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(HaveFunParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(HaveFunParser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmpExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpExp(HaveFunParser.CmpExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExp(HaveFunParser.ParExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrConcatExp}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrConcatExp(HaveFunParser.AndOrConcatExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pow}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(HaveFunParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link HaveFunParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HaveFunParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HaveFunParser#argsExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgsExp(HaveFunParser.ArgsExpContext ctx);
}