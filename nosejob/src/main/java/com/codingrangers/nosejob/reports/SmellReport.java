/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SmellReport implements SmellReportBody {
	private String smellName;
	private List<Smell> smells;

	public SmellReport() {
		smells = new ArrayList<>();
	}

	@Override
	public void setSmellName(String smellName) {
		this.smellName = smellName;
	}

	@Override
	public String getSmellName() {
		return smellName;
	}

	@Override
	public void addSmell(Smell smell) {
		if (smell.equals(null))
			throw new NullPointerException("Cannot add a null as a smell.");

		smells.add(smell);
	}

	@Override
	public void addSmells(List<Smell> retrievedSmells) {
		if (retrievedSmells.equals(null))
			throw new NullPointerException("Cannot add a null list of smells.");

		smells.addAll(retrievedSmells);
	}

	@Override
	public List<Smell> getSmells() {
		List<Smell> smellySmells = new ArrayList<>();

		for (Smell smell : smells) {
			if (smell.isSmelly())
				smellySmells.add(smell);
		}

		return smellySmells;
	}

	@Override
	public float getTotalSmellSeverity() {
		float averageSeverity = 0;
		try {
			if (smells.size() == 0) {
				return 0f;
			}

			for (Smell smell : smells) {
				averageSeverity += smell.getSmellSeverity();
			}
			averageSeverity /= smells.size();
		} catch (Exception ignored) {
		}

		return averageSeverity;
	}

	@Override
	public String getDisplayTotalSmellSeverity() {
		return ((int) (this.getTotalSmellSeverity() * 100)) + "%";
	}

	@Override
	public ArrayList<String> getFilesAffectedDatums() {
		ArrayList<String> datums = new ArrayList<String>();

		for (Smell smell : this.getSmells()) {
			Path path = Paths.get(smell.getLocation().getFilePath());
			String filename = path.getFileName().toString();
			CodeData codeData = smell.getLocation();

			if (filename.length() > 0) {
				datums.add(filename + " (" + ((int) (smell.getSmellSeverity() * 100)) + "%"
						+ " severity) - start line: " + codeData.getStartLineNumber() + ", end line: "
						+ codeData.getEndLineNumber() + ", line count: " + codeData.getLineCount());
			}
		}
		return datums;
	}

	@Override
	public int getAffectedFilesCount() {
		ArrayList<String> filenames = new ArrayList<String>();

		for (Smell smell : this.getSmells()) {
			Path path = Paths.get(smell.getLocation().getFilePath());
			String filename = path.getFileName().toString();

			if (!filenames.contains(filename) && filename.length() > 0) {
				filenames.add(filename);
			}
		}

		return filenames.size();
	}
}
