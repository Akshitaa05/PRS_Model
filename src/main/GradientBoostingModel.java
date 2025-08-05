package main;

import models.PatientData;
import models.GeneticVariant;
import main.PRSModel;
import synthetic.SyntheticDataGenerator;

import java.util.List;

public class GradientBoostingModel {

    public static void main(String[] args) {
        // Generate synthetic data for patients
        SyntheticDataGenerator dataGenerator = new SyntheticDataGenerator();
        List<PatientData> patientDataList = dataGenerator.getData();  // Get synthetic data of patients

        // Example loop to process each patient and calculate PRS, behavioral, clinical, and other features
        for (PatientData patient : patientDataList) {
            // Print patient data
            System.out.println("Patient ID: " + patient.getId());
            System.out.println("Patient Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Sex: " + patient.getSex());

            // Calculate the Polygenic Risk Score (PRS)
            double prsScore = PRSModel.calculateRiskScore(patient.getGeneticVariants());

            // Collect other features (e.g., sex, disease history, medication, lifestyle)
            double behavioralScore = processBehavioralData(patient.getLifestyle());
            double clinicalScore = processClinicalData(patient.getDiseaseHistory(), patient.getMedication());
            double physicalActivityScore = processPhysicalActivity(patient.getPhysicalActivityLevel());
            double bloodPressureScore = processBloodPressure(patient.getBloodPressure());
            double cholesterolScore = processCholesterolLevel(patient.getCholesterolLevel());
            double stressLevelScore = processStressLevel(patient.getStressLevel());

            String sex = patient.getSex();  // Could be encoded numerically for ML models

            // Print behavioral and clinical data with explanations
            System.out.println("Behavioral Score (Lifestyle): " + behavioralScore + " (" + getBehavioralExplanation(behavioralScore) + ")");
            System.out.println("Clinical Score (Disease History, Medication): " + clinicalScore + " (" + getClinicalExplanation(clinicalScore) + ")");
            System.out.println("Physical Activity Level: " + physicalActivityScore);
            System.out.println("Blood Pressure Score: " + bloodPressureScore);
            System.out.println("Cholesterol Level Score: " + cholesterolScore);
            System.out.println("Stress Level Score: " + stressLevelScore);

            // Combine all features into a feature vector (e.g., PRS score, clinical, behavioral, and sex)
            double[] features = {
                    prsScore, behavioralScore, clinicalScore, physicalActivityScore,
                    bloodPressureScore, cholesterolScore, stressLevelScore,
                    sex.equals("Male") ? 1.0 : 0.0
            };

            // Create an instance of DiseasePrediction to use its method
            DiseasePrediction diseasePrediction = new DiseasePrediction();

            // Simulate the disease prediction (Replace with actual machine learning model logic)
            boolean hasDisease = diseasePrediction.simulateDiseasePrediction(patient, features);

            // Output prediction for the patient
            System.out.println("PRS Score: " + prsScore + ", Behavioral Score: " + behavioralScore + ", Clinical Score: " + clinicalScore);
            System.out.println("Predicted disease status: " + (hasDisease ? "Disease predicted" : "No disease predicted"));
            System.out.println("-----------------------------------------------------");
        }
    }

    // Process and score behavioral data
    public static double processBehavioralData(String lifestyle) {
        if (lifestyle.equals("Smoker")) {
            return 1.0; // Unhealthy lifestyle
        } else if (lifestyle.equals("Non-Smoker")) {
            return 0.0; // Healthy lifestyle
        }
        return 0.5; // Neutral if undefined
    }

    // Process and score clinical data (e.g., disease history, medication)
    public static double processClinicalData(String diseaseHistory, String medication) {
        double score = 0.0;
        if (diseaseHistory.equals("Heart Disease")) {
            score += 1.0;
        }
        if (medication.equals("On Medication")) {
            score += 0.5;
        }
        return score;
    }

    // Process physical activity level and convert to a numerical score
    public static double processPhysicalActivity(String physicalActivityLevel) {
        if (physicalActivityLevel.equals("Active")) {
            return 0.0; // Healthy
        } else if (physicalActivityLevel.equals("Moderate")) {
            return 0.5; // Neutral
        }
        return 1.0; // Sedentary lifestyle
    }

    // Process blood pressure and score based on thresholds
    public static double processBloodPressure(double bloodPressure) {
        if (bloodPressure > 140) {
            return 1.0; // High BP
        }
        return 0.0; // Normal BP
    }

    // Process cholesterol level and score based on thresholds
    public static double processCholesterolLevel(double cholesterolLevel) {
        if (cholesterolLevel > 200) {
            return 1.0; // High cholesterol
        }
        return 0.0; // Normal cholesterol
    }

    // Process stress level and score based on thresholds
    public static double processStressLevel(String stressLevel) {
        switch (stressLevel) {
            case "High":
                return 1.0;
            case "Moderate":
                return 0.5;
            default:
                return 0.0; // Low or None
        }
    }

    // Get explanation for behavioral data
    public static String getBehavioralExplanation(double score) {
        if (score == 1.0) {
            return "Unhealthy lifestyle (smoking)";
        } else if (score == 0.0) {
            return "Healthy lifestyle (non-smoker)";
        }
        return "Neutral lifestyle";
    }

    // Get explanation for clinical data
    public static String getClinicalExplanation(double score) {
        if (score > 0.5) {
            return "High clinical risk (disease history or medication)";
        }
        return "Low clinical risk";
    }

    // Helper method to check if a patient has high risk for certain factors
    private static boolean isHighRisk(double... factors) {
        for (double factor : factors) {
            if (factor == 1.0) {
                return true; // At least one factor is high risk
            }
        }
        return false; // No high risk factors
    }

    // DiseasePrediction class should be static for use in main method
    public static class DiseasePrediction {

        public boolean simulateDiseasePrediction(PatientData patient, double[] features) {
            // Extract features
            double behavioralScore = features[0]; // Unhealthy lifestyle (0 = Healthy, 1 = Unhealthy)
            double clinicalScore = features[1]; // Clinical risk (0 = Low, 1 = High)
            double prsScore = features[2]; // PRS (Polygenic Risk Score) (0 = Low, 1 = High)
            double physicalActivityScore = features[3]; // Physical activity level (0 = Low, 1 = High)
            double bloodPressureScore = features[4]; // Blood pressure (0 = Normal, 1 = High)
            double cholesterolScore = features[5]; // Cholesterol (0 = Normal, 1 = High)
            double stressLevelScore = features[6]; // Stress level (0 = Low, 1 = High)
            double age = patient.getAge(); // Age of the patient

            // Condition 1: Behavioral high + Clinical high + PRS high -> Disease present
            if (behavioralScore == 1.0 && clinicalScore == 1.0 && prsScore >0.5) {
                return true; // Disease is present for sure
            }

            // Condition 2: Behavioral high + Clinical high + PRS low -> Check age and risk factors
            if (behavioralScore == 1.0 && clinicalScore == 1.0 && prsScore < 0.5) {
                if (age > 40 && isHighRisk(bloodPressureScore, cholesterolScore, stressLevelScore, physicalActivityScore)) {
                    return true; // Disease predicted based on high risk factors
                } else if (age < 40) {
                    return false; // Disease not present if age < 40
                } else {
                    return false; // Disease depends on other factors
                }
            }

            // Condition 3: Behavioral low + Clinical low + PRS low -> Disease not present
            if (behavioralScore == 0.0 && clinicalScore == 0.0 && prsScore < 0.5) {
                return false; // Disease is not present
            }

            // Condition 4: Behavioral high/low + Clinical high/low + PRS high -> Check age > 40
            if ((behavioralScore == 1.0 || behavioralScore == 0.0) &&
                    (clinicalScore == 1.0 || clinicalScore == 0.0) && prsScore>0.5) {
                if (age > 40) {
                    return true; // Disease present if age > 40
                } else {
                    return false; // Disease not present if age <= 40
                }
            }

            // Condition 5: Behavioral high/low + Clinical high/low + PRS high -> Check other risk factors
            if (prsScore > 0.5) {
                if (isHighRisk(bloodPressureScore, cholesterolScore, stressLevelScore, physicalActivityScore)) {
                    return true; // Disease predicted based on high risk factors
                } else {
                    return false; // Disease depends on other factors
                }
            }
            // Default case: No disease predicted
            return false;
        }
        // Helper method to check if a patient has high risk for certain factors
        private static boolean isHighRisk(double... factors) {
            for (double factor : factors) {
                if (factor == 1.0) {
                    return true; // At least one factor is high risk
                }
            }
            return false; // No high risk factors
    }
}
}
/* Score Meaning:
Behavioral high (1.0) ⇒ Unhealthy lifestyle (e.g., smoking, poor diet)
Behavioral low (0.0) ⇒ Healthy lifestyle (e.g., non-smoker, good habits)
Clinical high (1.0) ⇒ High clinical risk (e.g., disease history, medication use)
Clinical low (0.0) ⇒ Low clinical risk (no disease history, no medication)
PRS high (1.0) ⇒ High genetic risk (Polygenic Risk Score indicates higher likelihood of disease)
PRS low (0.0) ⇒ Low genetic risk
BP high (1.0) ⇒ High blood pressure (above 140 mmHg)
BP low (0.0) ⇒ Normal blood pressure (below 140 mmHg)
Cholesterol high (1.0) ⇒ High cholesterol (above 200 mg/dL)
Cholesterol low (0.0) ⇒ Normal cholesterol (below 200 mg/dL)
Stress high (1.0) ⇒ High stress level
Stress low (0.0) ⇒ Low stress level

Behavioral high ⇒ Unhealthy lifestyle (e.g., smoking, poor habits)
Behavioral high + Clinical high + PRS high ⇒ Disease present (Unhealthy lifestyle, high clinical risk, high genetic risk)
Behavioral high + Clinical high + PRS low ⇒ Disease present if age > 40 and high risk factors (e.g., BP, cholesterol, stress)
Behavioral low + Clinical low + PRS low ⇒ No disease (Healthy lifestyle, low clinical risk, low genetic risk)
Behavioral high/low + Clinical high/low + PRS high + Age > 40 ⇒ Disease present (Age > 40 combined with other risk factors)
PRS high + High risk factors (BP, cholesterol, stress, physical activity) ⇒ Disease present (High genetic risk with high physical/clinical risk factors)
PRS high + Age <= 40 ⇒ No disease (Age below 40 reduces the likelihood despite high genetic risk)
*/
