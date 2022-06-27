package generated;// Generated from SMTLIB.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SMTLIBParser}.
 */
public interface SMTLIBListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(SMTLIBParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(SMTLIBParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#response}.
	 * @param ctx the parse tree
	 */
	void enterResponse(SMTLIBParser.ResponseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#response}.
	 * @param ctx the parse tree
	 */
	void exitResponse(SMTLIBParser.ResponseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#generalReservedWord}.
	 * @param ctx the parse tree
	 */
	void enterGeneralReservedWord(SMTLIBParser.GeneralReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#generalReservedWord}.
	 * @param ctx the parse tree
	 */
	void exitGeneralReservedWord(SMTLIBParser.GeneralReservedWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#simpleSymbol}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSymbol(SMTLIBParser.SimpleSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#simpleSymbol}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSymbol(SMTLIBParser.SimpleSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#quotedSymbol}.
	 * @param ctx the parse tree
	 */
	void enterQuotedSymbol(SMTLIBParser.QuotedSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#quotedSymbol}.
	 * @param ctx the parse tree
	 */
	void exitQuotedSymbol(SMTLIBParser.QuotedSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#predefSymbol}.
	 * @param ctx the parse tree
	 */
	void enterPredefSymbol(SMTLIBParser.PredefSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#predefSymbol}.
	 * @param ctx the parse tree
	 */
	void exitPredefSymbol(SMTLIBParser.PredefSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#predefKeyword}.
	 * @param ctx the parse tree
	 */
	void enterPredefKeyword(SMTLIBParser.PredefKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#predefKeyword}.
	 * @param ctx the parse tree
	 */
	void exitPredefKeyword(SMTLIBParser.PredefKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(SMTLIBParser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(SMTLIBParser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#numeral}.
	 * @param ctx the parse tree
	 */
	void enterNumeral(SMTLIBParser.NumeralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#numeral}.
	 * @param ctx the parse tree
	 */
	void exitNumeral(SMTLIBParser.NumeralContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(SMTLIBParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(SMTLIBParser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#hexadecimal}.
	 * @param ctx the parse tree
	 */
	void enterHexadecimal(SMTLIBParser.HexadecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#hexadecimal}.
	 * @param ctx the parse tree
	 */
	void exitHexadecimal(SMTLIBParser.HexadecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#binary}.
	 * @param ctx the parse tree
	 */
	void enterBinary(SMTLIBParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#binary}.
	 * @param ctx the parse tree
	 */
	void exitBinary(SMTLIBParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(SMTLIBParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(SMTLIBParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(SMTLIBParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(SMTLIBParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#spec_constant}.
	 * @param ctx the parse tree
	 */
	void enterSpec_constant(SMTLIBParser.Spec_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#spec_constant}.
	 * @param ctx the parse tree
	 */
	void exitSpec_constant(SMTLIBParser.Spec_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#s_expr}.
	 * @param ctx the parse tree
	 */
	void enterS_expr(SMTLIBParser.S_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#s_expr}.
	 * @param ctx the parse tree
	 */
	void exitS_expr(SMTLIBParser.S_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(SMTLIBParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(SMTLIBParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SMTLIBParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SMTLIBParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_value(SMTLIBParser.Attribute_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#attribute_value}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_value(SMTLIBParser.Attribute_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(SMTLIBParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(SMTLIBParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#sort}.
	 * @param ctx the parse tree
	 */
	void enterSort(SMTLIBParser.SortContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#sort}.
	 * @param ctx the parse tree
	 */
	void exitSort(SMTLIBParser.SortContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#qual_identifier}.
	 * @param ctx the parse tree
	 */
	void enterQual_identifier(SMTLIBParser.Qual_identifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#qual_identifier}.
	 * @param ctx the parse tree
	 */
	void exitQual_identifier(SMTLIBParser.Qual_identifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#var_binding}.
	 * @param ctx the parse tree
	 */
	void enterVar_binding(SMTLIBParser.Var_bindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#var_binding}.
	 * @param ctx the parse tree
	 */
	void exitVar_binding(SMTLIBParser.Var_bindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#sorted_var}.
	 * @param ctx the parse tree
	 */
	void enterSorted_var(SMTLIBParser.Sorted_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#sorted_var}.
	 * @param ctx the parse tree
	 */
	void exitSorted_var(SMTLIBParser.Sorted_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(SMTLIBParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(SMTLIBParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#match_case}.
	 * @param ctx the parse tree
	 */
	void enterMatch_case(SMTLIBParser.Match_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#match_case}.
	 * @param ctx the parse tree
	 */
	void exitMatch_case(SMTLIBParser.Match_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SMTLIBParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SMTLIBParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#sort_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void enterSort_symbol_decl(SMTLIBParser.Sort_symbol_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#sort_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void exitSort_symbol_decl(SMTLIBParser.Sort_symbol_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#meta_spec_constant}.
	 * @param ctx the parse tree
	 */
	void enterMeta_spec_constant(SMTLIBParser.Meta_spec_constantContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#meta_spec_constant}.
	 * @param ctx the parse tree
	 */
	void exitMeta_spec_constant(SMTLIBParser.Meta_spec_constantContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#fun_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void enterFun_symbol_decl(SMTLIBParser.Fun_symbol_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#fun_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void exitFun_symbol_decl(SMTLIBParser.Fun_symbol_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#par_fun_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void enterPar_fun_symbol_decl(SMTLIBParser.Par_fun_symbol_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#par_fun_symbol_decl}.
	 * @param ctx the parse tree
	 */
	void exitPar_fun_symbol_decl(SMTLIBParser.Par_fun_symbol_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#theory_attribute}.
	 * @param ctx the parse tree
	 */
	void enterTheory_attribute(SMTLIBParser.Theory_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#theory_attribute}.
	 * @param ctx the parse tree
	 */
	void exitTheory_attribute(SMTLIBParser.Theory_attributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#theory_decl}.
	 * @param ctx the parse tree
	 */
	void enterTheory_decl(SMTLIBParser.Theory_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#theory_decl}.
	 * @param ctx the parse tree
	 */
	void exitTheory_decl(SMTLIBParser.Theory_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#logic_attribue}.
	 * @param ctx the parse tree
	 */
	void enterLogic_attribue(SMTLIBParser.Logic_attribueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#logic_attribue}.
	 * @param ctx the parse tree
	 */
	void exitLogic_attribue(SMTLIBParser.Logic_attribueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#logic}.
	 * @param ctx the parse tree
	 */
	void enterLogic(SMTLIBParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#logic}.
	 * @param ctx the parse tree
	 */
	void exitLogic(SMTLIBParser.LogicContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#sort_dec}.
	 * @param ctx the parse tree
	 */
	void enterSort_dec(SMTLIBParser.Sort_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#sort_dec}.
	 * @param ctx the parse tree
	 */
	void exitSort_dec(SMTLIBParser.Sort_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#selector_dec}.
	 * @param ctx the parse tree
	 */
	void enterSelector_dec(SMTLIBParser.Selector_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#selector_dec}.
	 * @param ctx the parse tree
	 */
	void exitSelector_dec(SMTLIBParser.Selector_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#constructor_dec}.
	 * @param ctx the parse tree
	 */
	void enterConstructor_dec(SMTLIBParser.Constructor_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#constructor_dec}.
	 * @param ctx the parse tree
	 */
	void exitConstructor_dec(SMTLIBParser.Constructor_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#datatype_dec}.
	 * @param ctx the parse tree
	 */
	void enterDatatype_dec(SMTLIBParser.Datatype_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#datatype_dec}.
	 * @param ctx the parse tree
	 */
	void exitDatatype_dec(SMTLIBParser.Datatype_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#function_dec}.
	 * @param ctx the parse tree
	 */
	void enterFunction_dec(SMTLIBParser.Function_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#function_dec}.
	 * @param ctx the parse tree
	 */
	void exitFunction_dec(SMTLIBParser.Function_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#function_def}.
	 * @param ctx the parse tree
	 */
	void enterFunction_def(SMTLIBParser.Function_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#function_def}.
	 * @param ctx the parse tree
	 */
	void exitFunction_def(SMTLIBParser.Function_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#prop_literal}.
	 * @param ctx the parse tree
	 */
	void enterProp_literal(SMTLIBParser.Prop_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#prop_literal}.
	 * @param ctx the parse tree
	 */
	void exitProp_literal(SMTLIBParser.Prop_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(SMTLIBParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(SMTLIBParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_assert}.
	 * @param ctx the parse tree
	 */
	void enterCmd_assert(SMTLIBParser.Cmd_assertContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_assert}.
	 * @param ctx the parse tree
	 */
	void exitCmd_assert(SMTLIBParser.Cmd_assertContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_checkSat}.
	 * @param ctx the parse tree
	 */
	void enterCmd_checkSat(SMTLIBParser.Cmd_checkSatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_checkSat}.
	 * @param ctx the parse tree
	 */
	void exitCmd_checkSat(SMTLIBParser.Cmd_checkSatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_checkSatAssuming}.
	 * @param ctx the parse tree
	 */
	void enterCmd_checkSatAssuming(SMTLIBParser.Cmd_checkSatAssumingContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_checkSatAssuming}.
	 * @param ctx the parse tree
	 */
	void exitCmd_checkSatAssuming(SMTLIBParser.Cmd_checkSatAssumingContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_declareConst}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declareConst(SMTLIBParser.Cmd_declareConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_declareConst}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declareConst(SMTLIBParser.Cmd_declareConstContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_declareDatatype}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declareDatatype(SMTLIBParser.Cmd_declareDatatypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_declareDatatype}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declareDatatype(SMTLIBParser.Cmd_declareDatatypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_declareDatatypes}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declareDatatypes(SMTLIBParser.Cmd_declareDatatypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_declareDatatypes}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declareDatatypes(SMTLIBParser.Cmd_declareDatatypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_declareFun}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declareFun(SMTLIBParser.Cmd_declareFunContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_declareFun}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declareFun(SMTLIBParser.Cmd_declareFunContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_declareSort}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declareSort(SMTLIBParser.Cmd_declareSortContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_declareSort}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declareSort(SMTLIBParser.Cmd_declareSortContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_defineFun}.
	 * @param ctx the parse tree
	 */
	void enterCmd_defineFun(SMTLIBParser.Cmd_defineFunContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_defineFun}.
	 * @param ctx the parse tree
	 */
	void exitCmd_defineFun(SMTLIBParser.Cmd_defineFunContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_defineFunRec}.
	 * @param ctx the parse tree
	 */
	void enterCmd_defineFunRec(SMTLIBParser.Cmd_defineFunRecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_defineFunRec}.
	 * @param ctx the parse tree
	 */
	void exitCmd_defineFunRec(SMTLIBParser.Cmd_defineFunRecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_defineFunsRec}.
	 * @param ctx the parse tree
	 */
	void enterCmd_defineFunsRec(SMTLIBParser.Cmd_defineFunsRecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_defineFunsRec}.
	 * @param ctx the parse tree
	 */
	void exitCmd_defineFunsRec(SMTLIBParser.Cmd_defineFunsRecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_defineSort}.
	 * @param ctx the parse tree
	 */
	void enterCmd_defineSort(SMTLIBParser.Cmd_defineSortContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_defineSort}.
	 * @param ctx the parse tree
	 */
	void exitCmd_defineSort(SMTLIBParser.Cmd_defineSortContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_echo}.
	 * @param ctx the parse tree
	 */
	void enterCmd_echo(SMTLIBParser.Cmd_echoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_echo}.
	 * @param ctx the parse tree
	 */
	void exitCmd_echo(SMTLIBParser.Cmd_echoContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_exit}.
	 * @param ctx the parse tree
	 */
	void enterCmd_exit(SMTLIBParser.Cmd_exitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_exit}.
	 * @param ctx the parse tree
	 */
	void exitCmd_exit(SMTLIBParser.Cmd_exitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getAssertions}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getAssertions(SMTLIBParser.Cmd_getAssertionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getAssertions}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getAssertions(SMTLIBParser.Cmd_getAssertionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getAssignment}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getAssignment(SMTLIBParser.Cmd_getAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getAssignment}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getAssignment(SMTLIBParser.Cmd_getAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getInfo}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getInfo(SMTLIBParser.Cmd_getInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getInfo}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getInfo(SMTLIBParser.Cmd_getInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getModel}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getModel(SMTLIBParser.Cmd_getModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getModel}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getModel(SMTLIBParser.Cmd_getModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getOption}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getOption(SMTLIBParser.Cmd_getOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getOption}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getOption(SMTLIBParser.Cmd_getOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getProof}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getProof(SMTLIBParser.Cmd_getProofContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getProof}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getProof(SMTLIBParser.Cmd_getProofContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getProofGraph}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getProofGraph(SMTLIBParser.Cmd_getProofGraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getProofGraph}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getProofGraph(SMTLIBParser.Cmd_getProofGraphContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getUnsatAssumptions}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getUnsatAssumptions(SMTLIBParser.Cmd_getUnsatAssumptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getUnsatAssumptions}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getUnsatAssumptions(SMTLIBParser.Cmd_getUnsatAssumptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getUnsatCore}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getUnsatCore(SMTLIBParser.Cmd_getUnsatCoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getUnsatCore}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getUnsatCore(SMTLIBParser.Cmd_getUnsatCoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_getValue}.
	 * @param ctx the parse tree
	 */
	void enterCmd_getValue(SMTLIBParser.Cmd_getValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_getValue}.
	 * @param ctx the parse tree
	 */
	void exitCmd_getValue(SMTLIBParser.Cmd_getValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_pop}.
	 * @param ctx the parse tree
	 */
	void enterCmd_pop(SMTLIBParser.Cmd_popContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_pop}.
	 * @param ctx the parse tree
	 */
	void exitCmd_pop(SMTLIBParser.Cmd_popContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_push}.
	 * @param ctx the parse tree
	 */
	void enterCmd_push(SMTLIBParser.Cmd_pushContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_push}.
	 * @param ctx the parse tree
	 */
	void exitCmd_push(SMTLIBParser.Cmd_pushContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_reset}.
	 * @param ctx the parse tree
	 */
	void enterCmd_reset(SMTLIBParser.Cmd_resetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_reset}.
	 * @param ctx the parse tree
	 */
	void exitCmd_reset(SMTLIBParser.Cmd_resetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_resetAssertions}.
	 * @param ctx the parse tree
	 */
	void enterCmd_resetAssertions(SMTLIBParser.Cmd_resetAssertionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_resetAssertions}.
	 * @param ctx the parse tree
	 */
	void exitCmd_resetAssertions(SMTLIBParser.Cmd_resetAssertionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_setInfo}.
	 * @param ctx the parse tree
	 */
	void enterCmd_setInfo(SMTLIBParser.Cmd_setInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_setInfo}.
	 * @param ctx the parse tree
	 */
	void exitCmd_setInfo(SMTLIBParser.Cmd_setInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_setLogic}.
	 * @param ctx the parse tree
	 */
	void enterCmd_setLogic(SMTLIBParser.Cmd_setLogicContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_setLogic}.
	 * @param ctx the parse tree
	 */
	void exitCmd_setLogic(SMTLIBParser.Cmd_setLogicContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#cmd_setOption}.
	 * @param ctx the parse tree
	 */
	void enterCmd_setOption(SMTLIBParser.Cmd_setOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#cmd_setOption}.
	 * @param ctx the parse tree
	 */
	void exitCmd_setOption(SMTLIBParser.Cmd_setOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(SMTLIBParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(SMTLIBParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#b_value}.
	 * @param ctx the parse tree
	 */
	void enterB_value(SMTLIBParser.B_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#b_value}.
	 * @param ctx the parse tree
	 */
	void exitB_value(SMTLIBParser.B_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(SMTLIBParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(SMTLIBParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#info_flag}.
	 * @param ctx the parse tree
	 */
	void enterInfo_flag(SMTLIBParser.Info_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#info_flag}.
	 * @param ctx the parse tree
	 */
	void exitInfo_flag(SMTLIBParser.Info_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#error_behaviour}.
	 * @param ctx the parse tree
	 */
	void enterError_behaviour(SMTLIBParser.Error_behaviourContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#error_behaviour}.
	 * @param ctx the parse tree
	 */
	void exitError_behaviour(SMTLIBParser.Error_behaviourContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#reason_unknown}.
	 * @param ctx the parse tree
	 */
	void enterReason_unknown(SMTLIBParser.Reason_unknownContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#reason_unknown}.
	 * @param ctx the parse tree
	 */
	void exitReason_unknown(SMTLIBParser.Reason_unknownContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#model_response}.
	 * @param ctx the parse tree
	 */
	void enterModel_response(SMTLIBParser.Model_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#model_response}.
	 * @param ctx the parse tree
	 */
	void exitModel_response(SMTLIBParser.Model_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#info_response}.
	 * @param ctx the parse tree
	 */
	void enterInfo_response(SMTLIBParser.Info_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#info_response}.
	 * @param ctx the parse tree
	 */
	void exitInfo_response(SMTLIBParser.Info_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#valuation_pair}.
	 * @param ctx the parse tree
	 */
	void enterValuation_pair(SMTLIBParser.Valuation_pairContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#valuation_pair}.
	 * @param ctx the parse tree
	 */
	void exitValuation_pair(SMTLIBParser.Valuation_pairContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#t_valuation_pair}.
	 * @param ctx the parse tree
	 */
	void enterT_valuation_pair(SMTLIBParser.T_valuation_pairContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#t_valuation_pair}.
	 * @param ctx the parse tree
	 */
	void exitT_valuation_pair(SMTLIBParser.T_valuation_pairContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#check_sat_response}.
	 * @param ctx the parse tree
	 */
	void enterCheck_sat_response(SMTLIBParser.Check_sat_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#check_sat_response}.
	 * @param ctx the parse tree
	 */
	void exitCheck_sat_response(SMTLIBParser.Check_sat_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#echo_response}.
	 * @param ctx the parse tree
	 */
	void enterEcho_response(SMTLIBParser.Echo_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#echo_response}.
	 * @param ctx the parse tree
	 */
	void exitEcho_response(SMTLIBParser.Echo_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_assertions_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_assertions_response(SMTLIBParser.Get_assertions_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_assertions_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_assertions_response(SMTLIBParser.Get_assertions_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_assignment_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_assignment_response(SMTLIBParser.Get_assignment_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_assignment_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_assignment_response(SMTLIBParser.Get_assignment_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_info_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_info_response(SMTLIBParser.Get_info_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_info_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_info_response(SMTLIBParser.Get_info_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_model_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_model_response(SMTLIBParser.Get_model_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_model_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_model_response(SMTLIBParser.Get_model_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_option_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_option_response(SMTLIBParser.Get_option_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_option_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_option_response(SMTLIBParser.Get_option_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_proof_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_proof_response(SMTLIBParser.Get_proof_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_proof_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_proof_response(SMTLIBParser.Get_proof_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_unsat_assump_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_unsat_assump_response(SMTLIBParser.Get_unsat_assump_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_unsat_assump_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_unsat_assump_response(SMTLIBParser.Get_unsat_assump_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_unsat_core_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_unsat_core_response(SMTLIBParser.Get_unsat_core_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_unsat_core_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_unsat_core_response(SMTLIBParser.Get_unsat_core_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#get_value_response}.
	 * @param ctx the parse tree
	 */
	void enterGet_value_response(SMTLIBParser.Get_value_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#get_value_response}.
	 * @param ctx the parse tree
	 */
	void exitGet_value_response(SMTLIBParser.Get_value_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#specific_success_response}.
	 * @param ctx the parse tree
	 */
	void enterSpecific_success_response(SMTLIBParser.Specific_success_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#specific_success_response}.
	 * @param ctx the parse tree
	 */
	void exitSpecific_success_response(SMTLIBParser.Specific_success_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SMTLIBParser#general_response}.
	 * @param ctx the parse tree
	 */
	void enterGeneral_response(SMTLIBParser.General_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SMTLIBParser#general_response}.
	 * @param ctx the parse tree
	 */
	void exitGeneral_response(SMTLIBParser.General_responseContext ctx);
}