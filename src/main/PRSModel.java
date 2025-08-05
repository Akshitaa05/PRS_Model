package main;

import models.PatientData;
import models.GeneticVariant;
import synthetic.SyntheticDataGenerator;

import java.util.List;

public class PRSModel {

    public static void main(String[] args) {
        SyntheticDataGenerator dataGenerator = new SyntheticDataGenerator();
        List<PatientData> patientDataList = dataGenerator.getData();  // Get 100 patients
        for (PatientData patient : patientDataList) {
            // Calculate PRS for the patient
            double score = calculateRiskScore(patient.getGeneticVariants());
            System.out.println("Calculated Polygenic Risk Score for " + patient.getName() + ": " + score);
        }
    }
    public static double calculateRiskScore(List<GeneticVariant> variants) {
        double riskScore = 0;
        for (GeneticVariant variant : variants) {
            if (variant.getGenotype().equals("AA")) {
                riskScore += 0.5;
            } else if (variant.getGenotype().equals("AG")) {
                riskScore += 0.2;
            }
        }
        return riskScore;
    }
}
