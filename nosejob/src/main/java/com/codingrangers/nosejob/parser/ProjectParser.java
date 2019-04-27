package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.CodeParser;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedFile;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.codingrangers.nosejob.parser.visitors.FileVisitor;
import com.codingrangers.nosejob.parser.visitors.MethodVisitor;
import com.codingrangers.nosejob.parser.visitors.VariableVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * entry point into code parsing system Each instance can parse one project at a
 * time, thus is not thread safe
 *
 * @author peter
 *
 */

@Component
public class ProjectParser implements CodeParser {

    public static final int CODESTORE_FOLDER_OFFSET = 29;
	ParsedProject parsedProject;
	ClassVisitor classVisitor;

	public ProjectParser() {
        parsedProject = new ParsedProject();
		VariableVisitor variableVisitor = new VariableVisitor();
		MethodVisitor methodVisitor = new MethodVisitor(variableVisitor);
		classVisitor = new ClassVisitor(methodVisitor, variableVisitor);
	}

	/**
	 * constructor for injecting dependencies, needed for testing perposes
	 *
	 * @param injectedProjectData
	 * @param injectedClassVisitor
	 */
	public ProjectParser(ParsedProject injectedProjectData, ClassVisitor injectedClassVisitor) {
		parsedProject = injectedProjectData;
		classVisitor = injectedClassVisitor;
	}

	/**
	 * @param path is taken as root of the project, all files beneath it are parse
	 */
	public ProjectData parseProject(String path) throws FileNotFoundException {
		File root = new File(path);
		if (root.exists() == false) {
			throw new FileNotFoundException();
		}

		JavaParserTypeSolver javaParserSolver = null;
		ReflectionTypeSolver refelctionSolver = new ReflectionTypeSolver();
		CombinedTypeSolver typeSolver = null;
		
		if(root.isFile() == false) {
			javaParserSolver = new JavaParserTypeSolver(root);
			typeSolver = new CombinedTypeSolver(javaParserSolver,refelctionSolver);
		}
		else
			typeSolver = new CombinedTypeSolver(refelctionSolver);

		
		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
		
		directoryOrFile(root);

		ParsedProject returnValue = parsedProject;
		parsedProject = new ParsedProject();

		return returnValue;
	}

	private void directoryOrFile(File file) {
		if (file.isDirectory())
			parseDirectory(file);
		else if (file.isFile() && file.getName().endsWith(".java"))
			parseFile(file);
	}

	private void parseDirectory(File Directory) {
		for (File file : Directory.listFiles())
			directoryOrFile(file);
	}

	private void parseFile(File file) {
		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        String filePath = file.getPath().substring(CODESTORE_FOLDER_OFFSET);
        String packageName = getPackageName(compilationUnit);

        ParsedFile fileData = new ParsedFile(parsedProject, packageName, filePath);
        FileVisitor fileVisitor = new FileVisitor(classVisitor);

        fileVisitor.visit(compilationUnit, fileData);
    }

    private String getPackageName(CompilationUnit compilationUnit) {
        Optional<PackageDeclaration> packageDeclaration = compilationUnit.getPackageDeclaration();

        if (packageDeclaration.isPresent())
            return packageDeclaration.get().getName().asString();
        return "";
	}

}