grammar HaveFun;

prog : fun* com EOF ;

fun : FUNC ID LPAR args RPAR LBRACE (com SEMICOLON)? RET exp? RBRACE;

args : | ID (',' ID)*;

com : IF LPAR exp RPAR THEN LBRACE com RBRACE ELSE LBRACE com RBRACE    # if
    | ID ASSIGN exp                                                     # assign
    | ID LBRACK exp RBRACK ASSIGN exp                                   # arrayAssign
    | SKIPP                                                             # skip
    | com SEMICOLON com                                                 # seq
    | WHILE LPAR exp RPAR LBRACE com RBRACE                             # while
    | OUT LPAR exp RPAR                                                 # out
    ;

exp : NAT                                 # nat
    | BOOL                                # bool
    | STRING                              # string
    | LPAR exp RPAR                       # parExp
    | <assoc=right> exp POW exp           # pow
    | TOSTR LPAR exp RPAR                 # tostr
    | NOT exp                             # not
    | exp op=(DIV | MUL | MOD) exp        # divMulMod
    | exp op=(PLUS | MINUS) exp           # plusMinus
    | exp op=(LT | LEQ | GEQ | GT) exp    # cmpExp
    | exp op=(EQQ | NEQ) exp              # eqExp
    | exp op=(AND | OR | CONCAT) exp      # andOrConcatExp
    | ID                                  # id
    | ID LBRACK exp RBRACK                # access
    | ID LPAR argsExp RPAR                # funCall
    ;

argsExp: | exp (',' exp)*;

NAT    : '0' | POS ;
BOOL   : 'true' | 'false' ;
STRING : '"' STRCHR* '"' ;

fragment STRCHR : ~["\\\r\n] | ESC ;
fragment ESC    : '\\' [btnfr"'\\] ;
fragment POS    : [1-9][0-9]* ;

PLUS  : '+' ;
MINUS : '-';
MUL   : '*' ;
DIV   : '/' ;
MOD   : '%' ;
POW   : '^' ;

AND : '&' ;
OR  : '|' ;

EQQ : '==' ;
NEQ : '!=' ;
LEQ : '<=' ;
GEQ : '>=' ;
LT  : '<' ;
GT  : '>' ;
NOT : '!' ;

CONCAT : '.' ;
TOSTR  : 'tostr' ;

FUNC   : 'fun';
RET    : 'return';
IF     : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
WHILE  : 'while' ;
SKIPP  : 'skip' ;
ASSIGN : '=' ;
OUT    : 'out' ;

LPAR      : '(' ;
RPAR      : ')';
LBRACE    : '{' ;
RBRACE    : '}' ;
LBRACK    : '[' ;
RBRACK    : ']' ;
SEMICOLON : ';' ;

ID: ([a-zA-Z_]+[0-9]*)+;

COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
WS: [ \t\r\n]+ -> skip;
