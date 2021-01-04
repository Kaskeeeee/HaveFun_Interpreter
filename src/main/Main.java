package main;

import org.antlr.v4.runtime.*;
import java.io.InputStream;
import gen.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(args[0]);
        CharStream cs = null;

        try {
            cs = CharStreams.fromStream(is);
        } catch (Exception e) {
            System.err.println("File could not be found");
            return;
        }

        HaveFunLexer lexer = new HaveFunLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HaveFunParser parser = new HaveFunParser(tokenStream);

        /* Handling syntax errors */
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line,
                                    int charPositionInLine,
                                    String msg,
                                    RecognitionException e) {
                super.syntaxError(recognizer,offendingSymbol,line,charPositionInLine,msg,e);
                System.exit(1);
            }
        });

        ParseTree parseTree = parser.prog();

        IntHaveFun hf = new IntHaveFun();
        hf.visit(parseTree);
    }
}
