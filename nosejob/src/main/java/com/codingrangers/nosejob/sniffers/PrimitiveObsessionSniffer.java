package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveObsessionSniffer implements Sniffer {
	private ProjectData currentProjectToAnalyse;
	private static final String NAME = "Primitive Obsession";
	private List<Smell> smells;

	private class MethodDiagnosis implements Smell {
		private MethodData currentMethodToAnalyze;

		private MethodDiagnosis() {
		}

		public float countSeverityInMethodsParametersTypes() {
			return currentMethodToAnalyze.getPrimitiveParameterCount();
		}

		public int countSeverityInMethodsLocalTypes() {
			return currentMethodToAnalyze.getPrimitiveLocalCount();
		}

		public float countSeverityInMethodsReturnTypes() {
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
			return (getSmellSeverity() > 0) ? true : false;
		}

		@Override
		public float getSmellSeverity() {
			return (countSeverityInMethodsParametersTypes() + countSeverityInMethodsLocalTypes()
					+ countSeverityInMethodsReturnTypes())
					/ (currentMethodToAnalyze.getLocalVariables().size() + currentMethodToAnalyze.getParameters().size()
							+ 1);
		}
	}

	private class FieldsDiagnosis implements Smell {
		private ClassData currentClassToAnalyze;

		private FieldsDiagnosis() {
		}

		public float measureSeverityInClassFieldsTypes() {
			if (currentClassToAnalyze.getFieldsNames().size() == 0)
				return 0f;

			ArrayList<VariableData> fields = new ArrayList<>();

			for (String fieldName : currentClassToAnalyze.getFieldsNames()) {
				fields.add(currentClassToAnalyze.getField(fieldName));
			}

			return (float) DataStructureHelpers.countPrimitives(fields) / currentClassToAnalyze.getFieldsNames().size();
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
			return getSmellSeverity() > 0 ? true : false;
		}

		@Override
		public float getSmellSeverity() {
			return measureSeverityInClassFieldsTypes();
		}
	}

	public PrimitiveObsessionSniffer() {
		smells = new ArrayList<>();
	}

	public void retrieveSmellsFromMethods(ClassData currentClassAnalysed) {
		if (currentClassAnalysed.equals(null))
			throw new NullPointerException("Cannot analyse fields of a null.");

		for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
			Smell methodDiagnosis = new MethodDiagnosis();
			methodDiagnosis.setCodeData(currentClassAnalysed.getMethod(methodSignature));
			smells.add(methodDiagnosis);
		}
	}

	public void retrieveSmellFromFields(ClassData currentClassAnalysed) {
		if (currentClassAnalysed.equals(null)) {
			throw new NullPointerException("Cannot analyse methods of a null.");
		}

		if (currentClassAnalysed.getFieldsNames().size() > 0) {
			Smell fieldsDiagnosis = new FieldsDiagnosis();
			fieldsDiagnosis.setCodeData(currentClassAnalysed);
			smells.add(fieldsDiagnosis);
		}
	}

	public void retrieveSmellsFromClasses() {
		for (String className : currentProjectToAnalyse.getClassNames()) {
			retrieveSmellsFromMethods(currentProjectToAnalyse.getClassData(className));
			retrieveSmellFromFields(currentProjectToAnalyse.getClassData(className));
		}
	}

	@Override
	public void setProjectToAnalyse(ProjectData projectData) {
		this.currentProjectToAnalyse = projectData;
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
