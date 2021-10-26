lexer grammar SchemaLexer;

CLASS: 'class' ;
PARTIAL: 'partial' ;
EXACT: 'exact' ;
CERTAIN: 'certain' ;
LATE: 'late' ;
ENUM: 'enum' ;
INTERFACE: 'interface' ;
DEFINE: 'define' ;
STATIC: 'static' ;
EXTENDS: 'extends' ;
IMPLEMENTS: 'implements' ;
VERY_FUZZY: 'very fuzzy' ;
FUZZY: 'fuzzy' ;

BRACE_OPEN: '{' ;
BRACE_CLOSE: '}' ;
BRACKET_OPEN: '(' ;
BRACKET_CLOSE: ')' ;
INDEX_OPEN: '[' ;
INDEX_CLOSE: ']' ;
REFERENCE_OPERATOR: '!' ;
LATE_OPERATOR: '#' ;
OPTIONAL_OPERATOR: '?' ;

ANNOTATION_AT: '@' ;

PRIM_BOOL: 'boolean' ;
PRIM_INT: 'int' ;
PRIM_LONG: 'long' ;
PRIM_FLOAT: 'float' ;
PRIM_DOUBLE: 'double' ;
PRIM_VOID: 'void' ;
MATCH_ANY: '*' ;
MATCH_IGNORE: '_' ;
NOT: '~' ;

BYTECODE_STRING: '.string' ;
BYTECODE_STRING_IGNORE_CASE: 'caseless' ;
BYTECODE_STRING_CONTAINS: 'contains' ;
BYTECODE_CALL: '.call' ;
BYTECODE_CALL_SEPARATOR: '->' ;
BYTECODE_NEW_INSTANCE: '.new-instance' ;
BYTECODE_TYPE_REF: '.reference' ;
BYTECODE_FIELD_REF: '.field' ;
BYTECODE_CONST: '.const' ;

VARARGS_ANY: '...' ;

fragment DIGIT: '0'..'9' ;

WS: [ \t\n\r]+ -> skip ;

fragment SINGLE_QUOTE : '\'' ;
fragment DOUBLE_QUOTE : '"' ;

fragment ESCAPED_CHAR:
'\\0'
| '\\' 'b'
| '\\' 'n'
| '\\' 'f'
| '\\' 'r'
| '\\' DOUBLE_QUOTE
| '\\' SINGLE_QUOTE
| '\\' '\\' ;

fragment CHARACTER:
~('\''
| '"'
| '\\')
| ESCAPED_CHAR ;

INT_LIT: DIGIT+ ;
BOOL_LIT: 'true' | 'false' ;
CHAR_LIT: '\'' CHARACTER '\'' ;
STRING_LIT: '"' (CHARACTER)* '"' ;
FLOAT_LIT: DIGIT+ '.' DIGIT* ;

SEMICOLON: ';' ;
COMMA: ',' ;

EXACT_MODIFIER: '$' ;
IDENTIFIER: [_a-zA-Z<>] [_a-zA-Z0-9<>]* ;
JAVA_TYPE_IDENTIFIER: IDENTIFIER ('.' IDENTIFIER)* ;