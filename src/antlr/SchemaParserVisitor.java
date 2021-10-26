// Generated from schema/SchemaParser.g4 by ANTLR 4.9.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SchemaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SchemaParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SchemaParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive(SchemaParser.PrimitiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(SchemaParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#referencedTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferencedTypeIdentifier(SchemaParser.ReferencedTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#lateTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLateTypeIdentifier(SchemaParser.LateTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#baseTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseTypeIdentifier(SchemaParser.BaseTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(SchemaParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(SchemaParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#attribArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribArgList(SchemaParser.AttribArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(SchemaParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#macroDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacroDef(SchemaParser.MacroDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#optionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalExpression(SchemaParser.OptionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#fieldDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDef(SchemaParser.FieldDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#methodArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodArgList(SchemaParser.MethodArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeStringOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeStringOption(SchemaParser.BytecodeStringOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeConstValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeConstValue(SchemaParser.BytecodeConstValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeString(SchemaParser.BytecodeStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeCall(SchemaParser.BytecodeCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeNewInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeNewInstance(SchemaParser.BytecodeNewInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeTypeReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeTypeReference(SchemaParser.BytecodeTypeReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeFieldReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeFieldReference(SchemaParser.BytecodeFieldReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeConst(SchemaParser.BytecodeConstContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#bytecodeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytecodeIdentifier(SchemaParser.BytecodeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(SchemaParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#methodDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDef(SchemaParser.MethodDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#extendsStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsStatement(SchemaParser.ExtendsStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#implementsStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplementsStatement(SchemaParser.ImplementsStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(SchemaParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SchemaParser#schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema(SchemaParser.SchemaContext ctx);
}