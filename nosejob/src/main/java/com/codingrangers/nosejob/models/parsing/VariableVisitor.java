package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.VariableData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * parsing for Variable level data
 * enters project at VariableDeclarationExpr
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<VariableData> {

}
