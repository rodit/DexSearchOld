// Generated from schema/SchemaParser.g4 by ANTLR 4.9.2
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SchemaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, PARTIAL=2, EXACT=3, CERTAIN=4, LATE=5, ENUM=6, INTERFACE=7, DEFINE=8, 
		STATIC=9, EXTENDS=10, IMPLEMENTS=11, VERY_FUZZY=12, FUZZY=13, BRACE_OPEN=14, 
		BRACE_CLOSE=15, BRACKET_OPEN=16, BRACKET_CLOSE=17, INDEX_OPEN=18, INDEX_CLOSE=19, 
		REFERENCE_OPERATOR=20, LATE_OPERATOR=21, OPTIONAL_OPERATOR=22, ANNOTATION_AT=23, 
		PRIM_BOOL=24, PRIM_INT=25, PRIM_LONG=26, PRIM_FLOAT=27, PRIM_DOUBLE=28, 
		PRIM_VOID=29, MATCH_ANY=30, MATCH_IGNORE=31, NOT=32, BYTECODE_STRING=33, 
		BYTECODE_STRING_IGNORE_CASE=34, BYTECODE_STRING_CONTAINS=35, BYTECODE_CALL=36, 
		BYTECODE_CALL_SEPARATOR=37, BYTECODE_NEW_INSTANCE=38, BYTECODE_TYPE_REF=39, 
		BYTECODE_FIELD_REF=40, BYTECODE_CONST=41, VARARGS_ANY=42, WS=43, INT_LIT=44, 
		BOOL_LIT=45, CHAR_LIT=46, STRING_LIT=47, FLOAT_LIT=48, SEMICOLON=49, COMMA=50, 
		EXACT_MODIFIER=51, IDENTIFIER=52, JAVA_TYPE_IDENTIFIER=53;
	public static final int
		RULE_primitive = 0, RULE_identifier = 1, RULE_referencedTypeIdentifier = 2, 
		RULE_lateTypeIdentifier = 3, RULE_baseTypeIdentifier = 4, RULE_typeIdentifier = 5, 
		RULE_constant = 6, RULE_attribArgList = 7, RULE_annotation = 8, RULE_macroDef = 9, 
		RULE_optionalExpression = 10, RULE_fieldDef = 11, RULE_methodArgList = 12, 
		RULE_bytecodeStringOption = 13, RULE_bytecodeConstValue = 14, RULE_bytecodeString = 15, 
		RULE_bytecodeCall = 16, RULE_bytecodeNewInstance = 17, RULE_bytecodeTypeReference = 18, 
		RULE_bytecodeFieldReference = 19, RULE_bytecodeConst = 20, RULE_bytecodeIdentifier = 21, 
		RULE_methodBody = 22, RULE_methodDef = 23, RULE_extendsStatement = 24, 
		RULE_implementsStatement = 25, RULE_classDef = 26, RULE_schema = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"primitive", "identifier", "referencedTypeIdentifier", "lateTypeIdentifier", 
			"baseTypeIdentifier", "typeIdentifier", "constant", "attribArgList", 
			"annotation", "macroDef", "optionalExpression", "fieldDef", "methodArgList", 
			"bytecodeStringOption", "bytecodeConstValue", "bytecodeString", "bytecodeCall", 
			"bytecodeNewInstance", "bytecodeTypeReference", "bytecodeFieldReference", 
			"bytecodeConst", "bytecodeIdentifier", "methodBody", "methodDef", "extendsStatement", 
			"implementsStatement", "classDef", "schema"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'partial'", "'exact'", "'certain'", "'late'", "'enum'", 
			"'interface'", "'define'", "'static'", "'extends'", "'implements'", "'very fuzzy'", 
			"'fuzzy'", "'{'", "'}'", "'('", "')'", "'['", "']'", "'!'", "'#'", "'?'", 
			"'@'", "'boolean'", "'int'", "'long'", "'float'", "'double'", "'void'", 
			"'*'", "'_'", "'~'", "'.string'", "'caseless'", "'contains'", "'.call'", 
			"'->'", "'.new-instance'", "'.reference'", "'.field'", "'.const'", "'...'", 
			null, null, null, null, null, null, "';'", "','", "'$'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "PARTIAL", "EXACT", "CERTAIN", "LATE", "ENUM", "INTERFACE", 
			"DEFINE", "STATIC", "EXTENDS", "IMPLEMENTS", "VERY_FUZZY", "FUZZY", "BRACE_OPEN", 
			"BRACE_CLOSE", "BRACKET_OPEN", "BRACKET_CLOSE", "INDEX_OPEN", "INDEX_CLOSE", 
			"REFERENCE_OPERATOR", "LATE_OPERATOR", "OPTIONAL_OPERATOR", "ANNOTATION_AT", 
			"PRIM_BOOL", "PRIM_INT", "PRIM_LONG", "PRIM_FLOAT", "PRIM_DOUBLE", "PRIM_VOID", 
			"MATCH_ANY", "MATCH_IGNORE", "NOT", "BYTECODE_STRING", "BYTECODE_STRING_IGNORE_CASE", 
			"BYTECODE_STRING_CONTAINS", "BYTECODE_CALL", "BYTECODE_CALL_SEPARATOR", 
			"BYTECODE_NEW_INSTANCE", "BYTECODE_TYPE_REF", "BYTECODE_FIELD_REF", "BYTECODE_CONST", 
			"VARARGS_ANY", "WS", "INT_LIT", "BOOL_LIT", "CHAR_LIT", "STRING_LIT", 
			"FLOAT_LIT", "SEMICOLON", "COMMA", "EXACT_MODIFIER", "IDENTIFIER", "JAVA_TYPE_IDENTIFIER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SchemaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SchemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PrimitiveContext extends ParserRuleContext {
		public TerminalNode PRIM_BOOL() { return getToken(SchemaParser.PRIM_BOOL, 0); }
		public TerminalNode PRIM_INT() { return getToken(SchemaParser.PRIM_INT, 0); }
		public TerminalNode PRIM_LONG() { return getToken(SchemaParser.PRIM_LONG, 0); }
		public TerminalNode PRIM_FLOAT() { return getToken(SchemaParser.PRIM_FLOAT, 0); }
		public TerminalNode PRIM_DOUBLE() { return getToken(SchemaParser.PRIM_DOUBLE, 0); }
		public TerminalNode PRIM_VOID() { return getToken(SchemaParser.PRIM_VOID, 0); }
		public PrimitiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitPrimitive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveContext primitive() throws RecognitionException {
		PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_primitive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRIM_BOOL) | (1L << PRIM_INT) | (1L << PRIM_LONG) | (1L << PRIM_FLOAT) | (1L << PRIM_DOUBLE) | (1L << PRIM_VOID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SchemaParser.IDENTIFIER, 0); }
		public TerminalNode EXACT_MODIFIER() { return getToken(SchemaParser.EXACT_MODIFIER, 0); }
		public TerminalNode MATCH_IGNORE() { return getToken(SchemaParser.MATCH_IGNORE, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_identifier);
		int _la;
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXACT_MODIFIER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXACT_MODIFIER) {
					{
					setState(58);
					match(EXACT_MODIFIER);
					}
				}

				setState(61);
				match(IDENTIFIER);
				}
				break;
			case MATCH_IGNORE:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(MATCH_IGNORE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferencedTypeIdentifierContext extends ParserRuleContext {
		public TerminalNode REFERENCE_OPERATOR() { return getToken(SchemaParser.REFERENCE_OPERATOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SchemaParser.IDENTIFIER, 0); }
		public ReferencedTypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referencedTypeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitReferencedTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferencedTypeIdentifierContext referencedTypeIdentifier() throws RecognitionException {
		ReferencedTypeIdentifierContext _localctx = new ReferencedTypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_referencedTypeIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(REFERENCE_OPERATOR);
			setState(66);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LateTypeIdentifierContext extends ParserRuleContext {
		public TerminalNode LATE_OPERATOR() { return getToken(SchemaParser.LATE_OPERATOR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SchemaParser.IDENTIFIER, 0); }
		public LateTypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lateTypeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitLateTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LateTypeIdentifierContext lateTypeIdentifier() throws RecognitionException {
		LateTypeIdentifierContext _localctx = new LateTypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lateTypeIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(LATE_OPERATOR);
			setState(69);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeIdentifierContext extends ParserRuleContext {
		public TerminalNode MATCH_ANY() { return getToken(SchemaParser.MATCH_ANY, 0); }
		public TerminalNode JAVA_TYPE_IDENTIFIER() { return getToken(SchemaParser.JAVA_TYPE_IDENTIFIER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SchemaParser.IDENTIFIER, 0); }
		public PrimitiveContext primitive() {
			return getRuleContext(PrimitiveContext.class,0);
		}
		public ReferencedTypeIdentifierContext referencedTypeIdentifier() {
			return getRuleContext(ReferencedTypeIdentifierContext.class,0);
		}
		public LateTypeIdentifierContext lateTypeIdentifier() {
			return getRuleContext(LateTypeIdentifierContext.class,0);
		}
		public BaseTypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseTypeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBaseTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeIdentifierContext baseTypeIdentifier() throws RecognitionException {
		BaseTypeIdentifierContext _localctx = new BaseTypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_baseTypeIdentifier);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCH_ANY:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(MATCH_ANY);
				}
				break;
			case JAVA_TYPE_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(JAVA_TYPE_IDENTIFIER);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(IDENTIFIER);
				}
				break;
			case PRIM_BOOL:
			case PRIM_INT:
			case PRIM_LONG:
			case PRIM_FLOAT:
			case PRIM_DOUBLE:
			case PRIM_VOID:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				primitive();
				}
				break;
			case REFERENCE_OPERATOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(75);
				referencedTypeIdentifier();
				}
				break;
			case LATE_OPERATOR:
				enterOuterAlt(_localctx, 6);
				{
				setState(76);
				lateTypeIdentifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeIdentifierContext extends ParserRuleContext {
		public TerminalNode INDEX_OPEN() { return getToken(SchemaParser.INDEX_OPEN, 0); }
		public TerminalNode EXTENDS() { return getToken(SchemaParser.EXTENDS, 0); }
		public BaseTypeIdentifierContext baseTypeIdentifier() {
			return getRuleContext(BaseTypeIdentifierContext.class,0);
		}
		public TerminalNode INDEX_CLOSE() { return getToken(SchemaParser.INDEX_CLOSE, 0); }
		public TerminalNode VARARGS_ANY() { return getToken(SchemaParser.VARARGS_ANY, 0); }
		public TypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIdentifierContext typeIdentifier() throws RecognitionException {
		TypeIdentifierContext _localctx = new TypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_typeIdentifier);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(INDEX_OPEN);
				setState(80);
				match(EXTENDS);
				setState(81);
				baseTypeIdentifier();
				setState(82);
				match(INDEX_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				baseTypeIdentifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				baseTypeIdentifier();
				setState(86);
				match(INDEX_OPEN);
				setState(87);
				match(INDEX_CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(89);
				match(VARARGS_ANY);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRING_LIT() { return getToken(SchemaParser.STRING_LIT, 0); }
		public TerminalNode BOOL_LIT() { return getToken(SchemaParser.BOOL_LIT, 0); }
		public TerminalNode INT_LIT() { return getToken(SchemaParser.INT_LIT, 0); }
		public TerminalNode CHAR_LIT() { return getToken(SchemaParser.CHAR_LIT, 0); }
		public TerminalNode FLOAT_LIT() { return getToken(SchemaParser.FLOAT_LIT, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT_LIT) | (1L << BOOL_LIT) | (1L << CHAR_LIT) | (1L << STRING_LIT) | (1L << FLOAT_LIT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttribArgListContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SchemaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SchemaParser.COMMA, i);
		}
		public AttribArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribArgList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitAttribArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttribArgListContext attribArgList() throws RecognitionException {
		AttribArgListContext _localctx = new AttribArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribArgList);
		int _la;
		try {
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT_LIT) | (1L << BOOL_LIT) | (1L << CHAR_LIT) | (1L << STRING_LIT) | (1L << FLOAT_LIT))) != 0)) {
					{
					setState(94);
					constant();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				constant();
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(98);
					match(COMMA);
					setState(99);
					constant();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode ANNOTATION_AT() { return getToken(SchemaParser.ANNOTATION_AT, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(SchemaParser.BRACKET_OPEN, 0); }
		public AttribArgListContext attribArgList() {
			return getRuleContext(AttribArgListContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(SchemaParser.BRACKET_CLOSE, 0); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_annotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(ANNOTATION_AT);
			setState(108);
			typeIdentifier();
			setState(109);
			match(BRACKET_OPEN);
			setState(110);
			attribArgList();
			setState(111);
			match(BRACKET_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MacroDefContext extends ParserRuleContext {
		public TerminalNode DEFINE() { return getToken(SchemaParser.DEFINE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SchemaParser.IDENTIFIER, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public MacroDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macroDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitMacroDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroDefContext macroDef() throws RecognitionException {
		MacroDefContext _localctx = new MacroDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_macroDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(DEFINE);
			setState(114);
			match(IDENTIFIER);
			setState(115);
			constant();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalExpressionContext extends ParserRuleContext {
		public TerminalNode OPTIONAL_OPERATOR() { return getToken(SchemaParser.OPTIONAL_OPERATOR, 0); }
		public TerminalNode INT_LIT() { return getToken(SchemaParser.INT_LIT, 0); }
		public OptionalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitOptionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionalExpressionContext optionalExpression() throws RecognitionException {
		OptionalExpressionContext _localctx = new OptionalExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_optionalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(OPTIONAL_OPERATOR);
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INT_LIT) {
				{
				setState(118);
				match(INT_LIT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDefContext extends ParserRuleContext {
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SchemaParser.SEMICOLON, 0); }
		public TerminalNode NOT() { return getToken(SchemaParser.NOT, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode STATIC() { return getToken(SchemaParser.STATIC, 0); }
		public OptionalExpressionContext optionalExpression() {
			return getRuleContext(OptionalExpressionContext.class,0);
		}
		public FieldDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitFieldDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDefContext fieldDef() throws RecognitionException {
		FieldDefContext _localctx = new FieldDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fieldDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(121);
				match(NOT);
				}
			}

			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANNOTATION_AT) {
				{
				{
				setState(124);
				annotation();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(130);
				match(STATIC);
				}
			}

			setState(133);
			typeIdentifier();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPTIONAL_OPERATOR) {
				{
				setState(134);
				optionalExpression();
				}
			}

			setState(137);
			identifier();
			setState(138);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodArgListContext extends ParserRuleContext {
		public List<TypeIdentifierContext> typeIdentifier() {
			return getRuleContexts(TypeIdentifierContext.class);
		}
		public TypeIdentifierContext typeIdentifier(int i) {
			return getRuleContext(TypeIdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SchemaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SchemaParser.COMMA, i);
		}
		public MethodArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodArgList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitMethodArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodArgListContext methodArgList() throws RecognitionException {
		MethodArgListContext _localctx = new MethodArgListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_methodArgList);
		int _la;
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INDEX_OPEN) | (1L << REFERENCE_OPERATOR) | (1L << LATE_OPERATOR) | (1L << PRIM_BOOL) | (1L << PRIM_INT) | (1L << PRIM_LONG) | (1L << PRIM_FLOAT) | (1L << PRIM_DOUBLE) | (1L << PRIM_VOID) | (1L << MATCH_ANY) | (1L << VARARGS_ANY) | (1L << IDENTIFIER) | (1L << JAVA_TYPE_IDENTIFIER))) != 0)) {
					{
					setState(140);
					typeIdentifier();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				typeIdentifier();
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(144);
					match(COMMA);
					setState(145);
					typeIdentifier();
					}
					}
					setState(150);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeStringOptionContext extends ParserRuleContext {
		public TerminalNode BYTECODE_STRING_IGNORE_CASE() { return getToken(SchemaParser.BYTECODE_STRING_IGNORE_CASE, 0); }
		public TerminalNode BYTECODE_STRING_CONTAINS() { return getToken(SchemaParser.BYTECODE_STRING_CONTAINS, 0); }
		public BytecodeStringOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeStringOption; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeStringOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeStringOptionContext bytecodeStringOption() throws RecognitionException {
		BytecodeStringOptionContext _localctx = new BytecodeStringOptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_bytecodeStringOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_la = _input.LA(1);
			if ( !(_la==BYTECODE_STRING_IGNORE_CASE || _la==BYTECODE_STRING_CONTAINS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeConstValueContext extends ParserRuleContext {
		public TerminalNode INT_LIT() { return getToken(SchemaParser.INT_LIT, 0); }
		public BytecodeConstValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeConstValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeConstValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeConstValueContext bytecodeConstValue() throws RecognitionException {
		BytecodeConstValueContext _localctx = new BytecodeConstValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bytecodeConstValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(INT_LIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeStringContext extends ParserRuleContext {
		public TerminalNode BYTECODE_STRING() { return getToken(SchemaParser.BYTECODE_STRING, 0); }
		public TerminalNode STRING_LIT() { return getToken(SchemaParser.STRING_LIT, 0); }
		public List<BytecodeStringOptionContext> bytecodeStringOption() {
			return getRuleContexts(BytecodeStringOptionContext.class);
		}
		public BytecodeStringOptionContext bytecodeStringOption(int i) {
			return getRuleContext(BytecodeStringOptionContext.class,i);
		}
		public BytecodeStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeString; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeStringContext bytecodeString() throws RecognitionException {
		BytecodeStringContext _localctx = new BytecodeStringContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_bytecodeString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(BYTECODE_STRING);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BYTECODE_STRING_IGNORE_CASE || _la==BYTECODE_STRING_CONTAINS) {
				{
				{
				setState(158);
				bytecodeStringOption();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(164);
			match(STRING_LIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeCallContext extends ParserRuleContext {
		public TerminalNode BYTECODE_CALL() { return getToken(SchemaParser.BYTECODE_CALL, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public TerminalNode BYTECODE_CALL_SEPARATOR() { return getToken(SchemaParser.BYTECODE_CALL_SEPARATOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BytecodeCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeCallContext bytecodeCall() throws RecognitionException {
		BytecodeCallContext _localctx = new BytecodeCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bytecodeCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(BYTECODE_CALL);
			setState(167);
			typeIdentifier();
			setState(168);
			match(BYTECODE_CALL_SEPARATOR);
			setState(169);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeNewInstanceContext extends ParserRuleContext {
		public TerminalNode BYTECODE_NEW_INSTANCE() { return getToken(SchemaParser.BYTECODE_NEW_INSTANCE, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public BytecodeNewInstanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeNewInstance; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeNewInstance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeNewInstanceContext bytecodeNewInstance() throws RecognitionException {
		BytecodeNewInstanceContext _localctx = new BytecodeNewInstanceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_bytecodeNewInstance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(BYTECODE_NEW_INSTANCE);
			setState(172);
			typeIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeTypeReferenceContext extends ParserRuleContext {
		public TerminalNode BYTECODE_TYPE_REF() { return getToken(SchemaParser.BYTECODE_TYPE_REF, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public BytecodeTypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeTypeReference; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeTypeReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeTypeReferenceContext bytecodeTypeReference() throws RecognitionException {
		BytecodeTypeReferenceContext _localctx = new BytecodeTypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_bytecodeTypeReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(BYTECODE_TYPE_REF);
			setState(175);
			typeIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeFieldReferenceContext extends ParserRuleContext {
		public TerminalNode BYTECODE_FIELD_REF() { return getToken(SchemaParser.BYTECODE_FIELD_REF, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public TerminalNode BYTECODE_CALL_SEPARATOR() { return getToken(SchemaParser.BYTECODE_CALL_SEPARATOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BytecodeFieldReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeFieldReference; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeFieldReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeFieldReferenceContext bytecodeFieldReference() throws RecognitionException {
		BytecodeFieldReferenceContext _localctx = new BytecodeFieldReferenceContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_bytecodeFieldReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(BYTECODE_FIELD_REF);
			setState(178);
			typeIdentifier();
			setState(179);
			match(BYTECODE_CALL_SEPARATOR);
			setState(180);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeConstContext extends ParserRuleContext {
		public TerminalNode BYTECODE_CONST() { return getToken(SchemaParser.BYTECODE_CONST, 0); }
		public BytecodeConstValueContext bytecodeConstValue() {
			return getRuleContext(BytecodeConstValueContext.class,0);
		}
		public BytecodeConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeConst; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeConstContext bytecodeConst() throws RecognitionException {
		BytecodeConstContext _localctx = new BytecodeConstContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_bytecodeConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(BYTECODE_CONST);
			setState(183);
			bytecodeConstValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BytecodeIdentifierContext extends ParserRuleContext {
		public BytecodeStringContext bytecodeString() {
			return getRuleContext(BytecodeStringContext.class,0);
		}
		public BytecodeCallContext bytecodeCall() {
			return getRuleContext(BytecodeCallContext.class,0);
		}
		public BytecodeNewInstanceContext bytecodeNewInstance() {
			return getRuleContext(BytecodeNewInstanceContext.class,0);
		}
		public BytecodeTypeReferenceContext bytecodeTypeReference() {
			return getRuleContext(BytecodeTypeReferenceContext.class,0);
		}
		public BytecodeFieldReferenceContext bytecodeFieldReference() {
			return getRuleContext(BytecodeFieldReferenceContext.class,0);
		}
		public BytecodeConstContext bytecodeConst() {
			return getRuleContext(BytecodeConstContext.class,0);
		}
		public BytecodeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytecodeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitBytecodeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytecodeIdentifierContext bytecodeIdentifier() throws RecognitionException {
		BytecodeIdentifierContext _localctx = new BytecodeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_bytecodeIdentifier);
		try {
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTECODE_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				bytecodeString();
				}
				break;
			case BYTECODE_CALL:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				bytecodeCall();
				}
				break;
			case BYTECODE_NEW_INSTANCE:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				bytecodeNewInstance();
				}
				break;
			case BYTECODE_TYPE_REF:
				enterOuterAlt(_localctx, 4);
				{
				setState(188);
				bytecodeTypeReference();
				}
				break;
			case BYTECODE_FIELD_REF:
				enterOuterAlt(_localctx, 5);
				{
				setState(189);
				bytecodeFieldReference();
				}
				break;
			case BYTECODE_CONST:
				enterOuterAlt(_localctx, 6);
				{
				setState(190);
				bytecodeConst();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public TerminalNode BRACE_OPEN() { return getToken(SchemaParser.BRACE_OPEN, 0); }
		public TerminalNode BRACE_CLOSE() { return getToken(SchemaParser.BRACE_CLOSE, 0); }
		public List<BytecodeIdentifierContext> bytecodeIdentifier() {
			return getRuleContexts(BytecodeIdentifierContext.class);
		}
		public BytecodeIdentifierContext bytecodeIdentifier(int i) {
			return getRuleContext(BytecodeIdentifierContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(SchemaParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SchemaParser.SEMICOLON, i);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitMethodBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_methodBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(BRACE_OPEN);
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(194);
				bytecodeIdentifier();
				setState(195);
				match(SEMICOLON);
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTECODE_STRING) | (1L << BYTECODE_CALL) | (1L << BYTECODE_NEW_INSTANCE) | (1L << BYTECODE_TYPE_REF) | (1L << BYTECODE_FIELD_REF) | (1L << BYTECODE_CONST))) != 0) );
			setState(201);
			match(BRACE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDefContext extends ParserRuleContext {
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode BRACKET_OPEN() { return getToken(SchemaParser.BRACKET_OPEN, 0); }
		public MethodArgListContext methodArgList() {
			return getRuleContext(MethodArgListContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(SchemaParser.BRACKET_CLOSE, 0); }
		public TerminalNode NOT() { return getToken(SchemaParser.NOT, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode STATIC() { return getToken(SchemaParser.STATIC, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public MethodDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitMethodDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDefContext methodDef() throws RecognitionException {
		MethodDefContext _localctx = new MethodDefContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_methodDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(203);
				match(NOT);
				}
			}

			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANNOTATION_AT) {
				{
				{
				setState(206);
				annotation();
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(212);
				match(STATIC);
				}
			}

			setState(215);
			typeIdentifier();
			setState(216);
			identifier();
			setState(217);
			match(BRACKET_OPEN);
			setState(218);
			methodArgList();
			setState(219);
			match(BRACKET_CLOSE);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACE_OPEN) {
				{
				setState(220);
				methodBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExtendsStatementContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(SchemaParser.EXTENDS, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public ExtendsStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendsStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitExtendsStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendsStatementContext extendsStatement() throws RecognitionException {
		ExtendsStatementContext _localctx = new ExtendsStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_extendsStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(EXTENDS);
			setState(224);
			typeIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplementsStatementContext extends ParserRuleContext {
		public TerminalNode IMPLEMENTS() { return getToken(SchemaParser.IMPLEMENTS, 0); }
		public List<TypeIdentifierContext> typeIdentifier() {
			return getRuleContexts(TypeIdentifierContext.class);
		}
		public TypeIdentifierContext typeIdentifier(int i) {
			return getRuleContext(TypeIdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SchemaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SchemaParser.COMMA, i);
		}
		public ImplementsStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implementsStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitImplementsStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplementsStatementContext implementsStatement() throws RecognitionException {
		ImplementsStatementContext _localctx = new ImplementsStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_implementsStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(IMPLEMENTS);
			setState(227);
			typeIdentifier();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(228);
				match(COMMA);
				setState(229);
				typeIdentifier();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode BRACE_OPEN() { return getToken(SchemaParser.BRACE_OPEN, 0); }
		public TerminalNode BRACE_CLOSE() { return getToken(SchemaParser.BRACE_CLOSE, 0); }
		public TerminalNode CLASS() { return getToken(SchemaParser.CLASS, 0); }
		public TerminalNode INTERFACE() { return getToken(SchemaParser.INTERFACE, 0); }
		public TerminalNode ENUM() { return getToken(SchemaParser.ENUM, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode LATE() { return getToken(SchemaParser.LATE, 0); }
		public TerminalNode CERTAIN() { return getToken(SchemaParser.CERTAIN, 0); }
		public TerminalNode EXACT() { return getToken(SchemaParser.EXACT, 0); }
		public ExtendsStatementContext extendsStatement() {
			return getRuleContext(ExtendsStatementContext.class,0);
		}
		public ImplementsStatementContext implementsStatement() {
			return getRuleContext(ImplementsStatementContext.class,0);
		}
		public List<MacroDefContext> macroDef() {
			return getRuleContexts(MacroDefContext.class);
		}
		public MacroDefContext macroDef(int i) {
			return getRuleContext(MacroDefContext.class,i);
		}
		public List<FieldDefContext> fieldDef() {
			return getRuleContexts(FieldDefContext.class);
		}
		public FieldDefContext fieldDef(int i) {
			return getRuleContext(FieldDefContext.class,i);
		}
		public List<MethodDefContext> methodDef() {
			return getRuleContexts(MethodDefContext.class);
		}
		public MethodDefContext methodDef(int i) {
			return getRuleContext(MethodDefContext.class,i);
		}
		public TerminalNode VERY_FUZZY() { return getToken(SchemaParser.VERY_FUZZY, 0); }
		public TerminalNode FUZZY() { return getToken(SchemaParser.FUZZY, 0); }
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_classDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANNOTATION_AT) {
				{
				{
				setState(235);
				annotation();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VERY_FUZZY || _la==FUZZY) {
				{
				setState(241);
				_la = _input.LA(1);
				if ( !(_la==VERY_FUZZY || _la==FUZZY) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LATE) {
				{
				setState(244);
				match(LATE);
				}
			}

			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CERTAIN) {
				{
				setState(247);
				match(CERTAIN);
				}
			}

			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXACT) {
				{
				setState(250);
				match(EXACT);
				}
			}

			setState(253);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << ENUM) | (1L << INTERFACE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(254);
			identifier();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(255);
				extendsStatement();
				}
			}

			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(258);
				implementsStatement();
				}
			}

			setState(261);
			match(BRACE_OPEN);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINE) {
				{
				{
				setState(262);
				macroDef();
				}
				}
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(268);
					fieldDef();
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STATIC) | (1L << INDEX_OPEN) | (1L << REFERENCE_OPERATOR) | (1L << LATE_OPERATOR) | (1L << ANNOTATION_AT) | (1L << PRIM_BOOL) | (1L << PRIM_INT) | (1L << PRIM_LONG) | (1L << PRIM_FLOAT) | (1L << PRIM_DOUBLE) | (1L << PRIM_VOID) | (1L << MATCH_ANY) | (1L << NOT) | (1L << VARARGS_ANY) | (1L << IDENTIFIER) | (1L << JAVA_TYPE_IDENTIFIER))) != 0)) {
				{
				{
				setState(274);
				methodDef();
				}
				}
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(280);
			match(BRACE_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemaContext extends ParserRuleContext {
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaParserVisitor ) return ((SchemaParserVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CLASS) | (1L << EXACT) | (1L << CERTAIN) | (1L << LATE) | (1L << ENUM) | (1L << INTERFACE) | (1L << VERY_FUZZY) | (1L << FUZZY) | (1L << ANNOTATION_AT))) != 0)) {
				{
				{
				setState(282);
				classDef();
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u0123\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\5\3>\n\3\3\3\3\3"+
		"\5\3B\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6P\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7]\n\7\3\b\3\b\3\t\5\tb\n\t"+
		"\3\t\3\t\3\t\7\tg\n\t\f\t\16\tj\13\t\5\tl\n\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\5\fz\n\f\3\r\5\r}\n\r\3\r\7\r\u0080\n\r\f"+
		"\r\16\r\u0083\13\r\3\r\5\r\u0086\n\r\3\r\3\r\5\r\u008a\n\r\3\r\3\r\3\r"+
		"\3\16\5\16\u0090\n\16\3\16\3\16\3\16\7\16\u0095\n\16\f\16\16\16\u0098"+
		"\13\16\5\16\u009a\n\16\3\17\3\17\3\20\3\20\3\21\3\21\7\21\u00a2\n\21\f"+
		"\21\16\21\u00a5\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u00c2\n\27\3\30\3\30\3\30\3\30\6\30\u00c8\n\30\r"+
		"\30\16\30\u00c9\3\30\3\30\3\31\5\31\u00cf\n\31\3\31\7\31\u00d2\n\31\f"+
		"\31\16\31\u00d5\13\31\3\31\5\31\u00d8\n\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\5\31\u00e0\n\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00e9\n\33"+
		"\f\33\16\33\u00ec\13\33\3\34\7\34\u00ef\n\34\f\34\16\34\u00f2\13\34\3"+
		"\34\5\34\u00f5\n\34\3\34\5\34\u00f8\n\34\3\34\5\34\u00fb\n\34\3\34\5\34"+
		"\u00fe\n\34\3\34\3\34\3\34\5\34\u0103\n\34\3\34\5\34\u0106\n\34\3\34\3"+
		"\34\7\34\u010a\n\34\f\34\16\34\u010d\13\34\3\34\7\34\u0110\n\34\f\34\16"+
		"\34\u0113\13\34\3\34\7\34\u0116\n\34\f\34\16\34\u0119\13\34\3\34\3\34"+
		"\3\35\7\35\u011e\n\35\f\35\16\35\u0121\13\35\3\35\2\2\36\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\7\3\2\32\37\3\2.\62\3"+
		"\2$%\3\2\16\17\4\2\3\3\b\t\2\u0132\2:\3\2\2\2\4A\3\2\2\2\6C\3\2\2\2\b"+
		"F\3\2\2\2\nO\3\2\2\2\f\\\3\2\2\2\16^\3\2\2\2\20k\3\2\2\2\22m\3\2\2\2\24"+
		"s\3\2\2\2\26w\3\2\2\2\30|\3\2\2\2\32\u0099\3\2\2\2\34\u009b\3\2\2\2\36"+
		"\u009d\3\2\2\2 \u009f\3\2\2\2\"\u00a8\3\2\2\2$\u00ad\3\2\2\2&\u00b0\3"+
		"\2\2\2(\u00b3\3\2\2\2*\u00b8\3\2\2\2,\u00c1\3\2\2\2.\u00c3\3\2\2\2\60"+
		"\u00ce\3\2\2\2\62\u00e1\3\2\2\2\64\u00e4\3\2\2\2\66\u00f0\3\2\2\28\u011f"+
		"\3\2\2\2:;\t\2\2\2;\3\3\2\2\2<>\7\65\2\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2"+
		"?B\7\66\2\2@B\7!\2\2A=\3\2\2\2A@\3\2\2\2B\5\3\2\2\2CD\7\26\2\2DE\7\66"+
		"\2\2E\7\3\2\2\2FG\7\27\2\2GH\7\66\2\2H\t\3\2\2\2IP\7 \2\2JP\7\67\2\2K"+
		"P\7\66\2\2LP\5\2\2\2MP\5\6\4\2NP\5\b\5\2OI\3\2\2\2OJ\3\2\2\2OK\3\2\2\2"+
		"OL\3\2\2\2OM\3\2\2\2ON\3\2\2\2P\13\3\2\2\2QR\7\24\2\2RS\7\f\2\2ST\5\n"+
		"\6\2TU\7\25\2\2U]\3\2\2\2V]\5\n\6\2WX\5\n\6\2XY\7\24\2\2YZ\7\25\2\2Z]"+
		"\3\2\2\2[]\7,\2\2\\Q\3\2\2\2\\V\3\2\2\2\\W\3\2\2\2\\[\3\2\2\2]\r\3\2\2"+
		"\2^_\t\3\2\2_\17\3\2\2\2`b\5\16\b\2a`\3\2\2\2ab\3\2\2\2bl\3\2\2\2ch\5"+
		"\16\b\2de\7\64\2\2eg\5\16\b\2fd\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2"+
		"il\3\2\2\2jh\3\2\2\2ka\3\2\2\2kc\3\2\2\2l\21\3\2\2\2mn\7\31\2\2no\5\f"+
		"\7\2op\7\22\2\2pq\5\20\t\2qr\7\23\2\2r\23\3\2\2\2st\7\n\2\2tu\7\66\2\2"+
		"uv\5\16\b\2v\25\3\2\2\2wy\7\30\2\2xz\7.\2\2yx\3\2\2\2yz\3\2\2\2z\27\3"+
		"\2\2\2{}\7\"\2\2|{\3\2\2\2|}\3\2\2\2}\u0081\3\2\2\2~\u0080\5\22\n\2\177"+
		"~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086\7\13\2\2\u0085\u0084\3"+
		"\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\5\f\7\2\u0088"+
		"\u008a\5\26\f\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3"+
		"\2\2\2\u008b\u008c\5\4\3\2\u008c\u008d\7\63\2\2\u008d\31\3\2\2\2\u008e"+
		"\u0090\5\f\7\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u009a\3\2"+
		"\2\2\u0091\u0096\5\f\7\2\u0092\u0093\7\64\2\2\u0093\u0095\5\f\7\2\u0094"+
		"\u0092\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u008f\3\2\2\2\u0099"+
		"\u0091\3\2\2\2\u009a\33\3\2\2\2\u009b\u009c\t\4\2\2\u009c\35\3\2\2\2\u009d"+
		"\u009e\7.\2\2\u009e\37\3\2\2\2\u009f\u00a3\7#\2\2\u00a0\u00a2\5\34\17"+
		"\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7\61\2\2"+
		"\u00a7!\3\2\2\2\u00a8\u00a9\7&\2\2\u00a9\u00aa\5\f\7\2\u00aa\u00ab\7\'"+
		"\2\2\u00ab\u00ac\5\4\3\2\u00ac#\3\2\2\2\u00ad\u00ae\7(\2\2\u00ae\u00af"+
		"\5\f\7\2\u00af%\3\2\2\2\u00b0\u00b1\7)\2\2\u00b1\u00b2\5\f\7\2\u00b2\'"+
		"\3\2\2\2\u00b3\u00b4\7*\2\2\u00b4\u00b5\5\f\7\2\u00b5\u00b6\7\'\2\2\u00b6"+
		"\u00b7\5\4\3\2\u00b7)\3\2\2\2\u00b8\u00b9\7+\2\2\u00b9\u00ba\5\36\20\2"+
		"\u00ba+\3\2\2\2\u00bb\u00c2\5 \21\2\u00bc\u00c2\5\"\22\2\u00bd\u00c2\5"+
		"$\23\2\u00be\u00c2\5&\24\2\u00bf\u00c2\5(\25\2\u00c0\u00c2\5*\26\2\u00c1"+
		"\u00bb\3\2\2\2\u00c1\u00bc\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00be\3\2"+
		"\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2-\3\2\2\2\u00c3\u00c7"+
		"\7\20\2\2\u00c4\u00c5\5,\27\2\u00c5\u00c6\7\63\2\2\u00c6\u00c8\3\2\2\2"+
		"\u00c7\u00c4\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\21\2\2\u00cc/\3\2\2\2\u00cd"+
		"\u00cf\7\"\2\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d3\3\2"+
		"\2\2\u00d0\u00d2\5\22\n\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d6\u00d8\7\13\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\u00da\5\f\7\2\u00da\u00db\5\4\3\2\u00db\u00dc\7\22"+
		"\2\2\u00dc\u00dd\5\32\16\2\u00dd\u00df\7\23\2\2\u00de\u00e0\5.\30\2\u00df"+
		"\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\61\3\2\2\2\u00e1\u00e2\7\f\2"+
		"\2\u00e2\u00e3\5\f\7\2\u00e3\63\3\2\2\2\u00e4\u00e5\7\r\2\2\u00e5\u00ea"+
		"\5\f\7\2\u00e6\u00e7\7\64\2\2\u00e7\u00e9\5\f\7\2\u00e8\u00e6\3\2\2\2"+
		"\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\65"+
		"\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ef\5\22\n\2\u00ee\u00ed\3\2\2\2"+
		"\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f4"+
		"\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f5\t\5\2\2\u00f4\u00f3\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f8\7\7\2\2\u00f7\u00f6\3\2"+
		"\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00fb\7\6\2\2\u00fa"+
		"\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fe\7\5"+
		"\2\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0100\t\6\2\2\u0100\u0102\5\4\3\2\u0101\u0103\5\62\32\2\u0102\u0101\3"+
		"\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105\3\2\2\2\u0104\u0106\5\64\33\2\u0105"+
		"\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u010b\7\20"+
		"\2\2\u0108\u010a\5\24\13\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u0111\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010e\u0110\5\30\r\2\u010f\u010e\3\2\2\2\u0110\u0113\3\2\2\2\u0111"+
		"\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0117\3\2\2\2\u0113\u0111\3\2"+
		"\2\2\u0114\u0116\5\60\31\2\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u0117\3\2"+
		"\2\2\u011a\u011b\7\21\2\2\u011b\67\3\2\2\2\u011c\u011e\5\66\34\2\u011d"+
		"\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u01209\3\2\2\2\u0121\u011f\3\2\2\2$=AO\\ahky|\u0081\u0085\u0089\u008f"+
		"\u0096\u0099\u00a3\u00c1\u00c9\u00ce\u00d3\u00d7\u00df\u00ea\u00f0\u00f4"+
		"\u00f7\u00fa\u00fd\u0102\u0105\u010b\u0111\u0117\u011f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}