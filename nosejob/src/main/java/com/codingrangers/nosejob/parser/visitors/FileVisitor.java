package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedFile;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.UnsolvedSymbolException;

public class FileVisitor extends VoidVisitorAdapter<ParsedFile> {

    private VoidVisitorAdapter<ParsedClass> classVisitor;

    public FileVisitor(VoidVisitorAdapter<ParsedClass> classVisitor) {
        this.classVisitor = classVisitor;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedFile fileData) {
        if (classOrInterface.isInterface())
            return;


        String className = classOrInterface.getName().asString();
        int startLine = classOrInterface.getBegin().get().line;
        int endLine = classOrInterface.getEnd().get().line;


        ParsedClass classData = fileData.createClass(className, startLine, endLine);

        try {
            for (var ancestor : classOrInterface.resolve().getAllAncestors()) {
                classData.addSuperclass(ancestor.getQualifiedName());
            }
        } catch (UnsolvedSymbolException ignored) {

        }

        classVisitor.visit(classOrInterface, classData);
        super.visit(classOrInterface, fileData);
    }
}
