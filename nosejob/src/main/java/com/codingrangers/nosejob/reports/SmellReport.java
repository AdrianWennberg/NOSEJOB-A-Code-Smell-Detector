package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;

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
	// TODO
	public float getTotalSmellSeverity() {
		if (smells.size() == 0) {
			return 0f;
		}

		// int averageSeverity = smells.stream().reduce(0, (v.getSmellSeverity(), _v) ->
		// v.getSmellSeverity() + _v.getSmellSeverity()).collect();
		float averageSeverity = 0;
		for (Smell smell : smells) {
			averageSeverity += smell.getSmellSeverity();
		}
		averageSeverity /= smells.size();

		return averageSeverity;
	}
}
