@echo off
set BASE_DIR=schema
set LEXER_FILE=SchemaLexer.g4
set PARSER_FILE=SchemaParser.g4

set TARGET_DIR=src/antlr

rd /s /q %TARGET_DIR%

echo Compiling the lexer..
java -jar libs/antlr-4.9.2-complete.jar %BASE_DIR%/%LEXER_FILE% -o %TARGET_DIR% -package antlr -no-listener -visitor -Werror

echo Compiling the parser..
java -jar libs/antlr-4.9.2-complete.jar %BASE_DIR%/%PARSER_FILE% -o %TARGET_DIR% -package antlr -no-listener -visitor -Werror
