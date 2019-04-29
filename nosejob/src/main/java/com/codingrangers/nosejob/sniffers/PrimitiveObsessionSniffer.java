/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.reports.SmellReport;

public class PrimitiveObsessionSniffer extends GeneralSniffer{
	private static final String NAME = "Primitive Obsession";

	private class MethodDiagnosis implements Smell {
		private MethodData currentMethodToAnalyze;

		private float countSeverityInMethodsParametersTypes() {
			return currentMethodToAnalyze.getPrimitiveParameterCount();
		}

		private int countSeverityInMethodsLocalTypes() {
			return currentMethodToAnalyze.getPrimitiveLocalCount();
		}

		private float countSeverityInMethodsReturnTypes() {
			return (currentMethodToAnalyze.hasPrimitiveReturnType()) ? 1 : 0;
		}

		@Override
		public void setCodeData(CodeData codeData) {
			currentMethodToAnalyze = (MethodData) codeData;
		}

		@Override
		public CodeData getLocation() {
			return currentMethodToAnalyze;
		}

		@Override
		public boolean isSmelly() {
            return getSmellSeverity() > 0;
		}

		@Override
		public float getSmellSeverity() {

			float primitives = countSeverityInMethodsParametersTypes() +
					countSeverityInMethodsLocalTypes() +
					countSeverityInMethodsReturnTypes();


			int total = currentMethodToAnalyze.getLocalVariables().size() +
					currentMethodToAnalyze.getParameters().size() +
					1;

			if (primitives / total < 0.3)
				return 0f;

			return primitives / total;
		}
	}

	private class FieldsDiagnosis implements Smell {
		private ClassData currentClassToAnalyze;

		public float measureSeverityInClassFieldsTypes() {
			if (currentClassToAnalyze.getFieldsNames().size() == 0)
				return 0f;

			return (float) currentClassToAnalyze.countPrimitiveFields() / currentClassToAnalyze.getFieldsNames().size();
		}

		@Override
		public void setCodeData(CodeData codeData) {
			currentClassToAnalyze = (ClassData) codeData;
		}

		@Override
		public CodeData getLocation() {
			return currentClassToAnalyze;
		}

		@Override
		public boolean isSmelly() {
			return getSmellSeverity() > 0;
		}

		@Override
		public float getSmellSeverity() {
			return measureSeverityInClassFieldsTypes();
		}
	}

	private void retrieveSmellsFromMethods(ClassData currentClassAnalysed) {
		if (currentClassAnalysed.equals(null))
			throw new NullPointerException("Cannot analyse methods of a null.");

		for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
			Smell methodDiagnosis = new MethodDiagnosis();
			methodDiagnosis.setCodeData(currentClassAnalysed.getMethod(methodSignature));
			smells.add(methodDiagnosis);
		}
	}

	private void retrieveSmellFromFields(ClassData currentClassToAnalyse) {
		if (currentClassToAnalyse.equals(null))
			throw new NullPointerException("Cannot analyse fields of a null.");

		if (currentClassToAnalyse.getFieldsNames().size() > 0) {
			Smell fieldsDiagnosis = new FieldsDiagnosis();
			fieldsDiagnosis.setCodeData(currentClassToAnalyse);
			smells.add(fieldsDiagnosis);
		}
	}

	private void retrieveSmellsFromClasses() {
		if (currentProjectToAnalyse.equals(null))
			throw new NullPointerException("Cannot analyse a null project.");

		for (String className : currentProjectToAnalyse.getClassNames()) {
			ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
			retrieveSmellsFromMethods(currentClassToAnalyse);
			retrieveSmellFromFields(currentClassToAnalyse);
		}
	}

	@Override
	public SmellReport getSmellReport() {
		retrieveSmellsFromClasses();

		SmellReport report = new SmellReport();
		report.setSmellName(NAME);
		report.addSmells(smells);
		return report;
	}
}