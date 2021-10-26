parser grammar SchemaParser;

options {
  tokenVocab=SchemaLexer;
}

primitive: PRIM_BOOL
    | PRIM_INT
    | PRIM_LONG
    | PRIM_FLOAT
    | PRIM_DOUBLE
    | PRIM_VOID ;

identifier: EXACT_MODIFIER? IDENTIFIER
    | MATCH_IGNORE ;

referencedTypeIdentifier: REFERENCE_OPERATOR IDENTIFIER ;
lateTypeIdentifier : LATE_OPERATOR IDENTIFIER ;

baseTypeIdentifier: MATCH_ANY
    | JAVA_TYPE_IDENTIFIER
    | IDENTIFIER
    | primitive
    | referencedTypeIdentifier
    | lateTypeIdentifier ;

typeIdentifier: INDEX_OPEN EXTENDS baseTypeIdentifier INDEX_CLOSE
    | baseTypeIdentifier
    | baseTypeIdentifier INDEX_OPEN INDEX_CLOSE
    | VARARGS_ANY ;

constant: STRING_LIT
    | BOOL_LIT
    | INT_LIT
    | CHAR_LIT
    | FLOAT_LIT ;

attribArgList: constant?
    | constant (COMMA constant)* ;

annotation: ANNOTATION_AT typeIdentifier BRACKET_OPEN attribArgList BRACKET_CLOSE ;

macroDef: DEFINE IDENTIFIER constant ;

optionalExpression: OPTIONAL_OPERATOR INT_LIT? ;

fieldDef: NOT? annotation* STATIC? typeIdentifier optionalExpression? identifier SEMICOLON ;

methodArgList: typeIdentifier?
    | typeIdentifier (COMMA typeIdentifier)* ;

bytecodeStringOption: BYTECODE_STRING_IGNORE_CASE
    | BYTECODE_STRING_CONTAINS ;

bytecodeConstValue: INT_LIT ;

bytecodeString: BYTECODE_STRING bytecodeStringOption* STRING_LIT ;
bytecodeCall: BYTECODE_CALL typeIdentifier BYTECODE_CALL_SEPARATOR IDENTIFIER ;
bytecodeNewInstance: BYTECODE_NEW_INSTANCE typeIdentifier ;
bytecodeTypeReference: BYTECODE_TYPE_REF typeIdentifier ;
bytecodeFieldReference: BYTECODE_FIELD_REF typeIdentifier BYTECODE_CALL_SEPARATOR IDENTIFIER ;
bytecodeConst: BYTECODE_CONST bytecodeConstValue ;

bytecodeIdentifier: bytecodeString
    | bytecodeCall
    | bytecodeNewInstance
    | bytecodeTypeReference
    | bytecodeFieldReference
    | bytecodeConst ;

methodBody: BRACE_OPEN (bytecodeIdentifier SEMICOLON)+ BRACE_CLOSE ;

methodDef: NOT? annotation* STATIC? typeIdentifier identifier BRACKET_OPEN methodArgList BRACKET_CLOSE methodBody?;

extendsStatement: EXTENDS typeIdentifier ;
implementsStatement: IMPLEMENTS typeIdentifier (COMMA typeIdentifier)* ;

classDef: annotation* (VERY_FUZZY | FUZZY)? LATE? CERTAIN? EXACT? (CLASS | INTERFACE | ENUM) identifier extendsStatement? implementsStatement? BRACE_OPEN macroDef* fieldDef* methodDef* BRACE_CLOSE ;

schema: classDef* ;