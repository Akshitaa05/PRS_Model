package synthetic;

import models.GeneticVariant;
import models.PatientData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SyntheticDataGenerator {
    private static final String FILE_NAME = "syntheticData.ser";

    public void generateSyntheticData() {
        List<PatientData> patients = new ArrayList<>();
        Random random = new Random();

        // Generate data for 1000 patients
        for (int i = 0; i < 1000; i++) {
            String id = "P" + (i + 1);
            String name = "Patient" + (i + 1);
            int age = random.nextInt(60) + 18;  // Age between 18 and 77
            String sex = random.nextBoolean() ? "Male" : "Female";

            // Original attributes
            String diseaseHistory = random.nextBoolean() ? "Heart Disease" : "None";
            String medication = random.nextBoolean() ? "On Medication" : "None"; // Changed from Aspirin to On Medication/None
            String lifestyle = random.nextBoolean() ? "Smoker" : "Non-Smoker";

            // Additional constraints
            double bloodPressure = random.nextDouble() * (180 - 90) + 90; // Random BP between 90 and 180
            double sugarLevel = random.nextDouble() * (250 - 70) + 70;    // Random sugar levels between 70 and 250
            double cholesterolLevel = random.nextDouble() * (300 - 120) + 120; // Random cholesterol between 120 and 300
            boolean hasHighBP = bloodPressure > 140;  // High BP threshold
            boolean hasHighSugar = sugarLevel > 150;  // High sugar threshold
            boolean hasHighCholesterol = cholesterolLevel > 200; // High cholesterol threshold
            boolean hasDiabetes = hasHighSugar || random.nextBoolean(); // Diabetes condition

            // Family medical history
            String familyMedicalHistory = random.nextBoolean() ? "Cancer" :
                    (random.nextBoolean() ? "Heart Disease" : "None");

            // Physical activity level
            String physicalActivityLevel = random.nextInt(3) == 0 ? "Active" :
                    (random.nextInt(2) == 0 ? "Moderate" : "Sedentary");

            // Stress level
            String stressLevel = random.nextInt(4) == 0 ? "High" :
                    (random.nextInt(3) == 0 ? "Moderate" :
                            (random.nextBoolean() ? "Low" : "None"));

            // Chronic illness
            String chronicIllness = random.nextInt(3) == 0 ? "Asthma" :
                    (random.nextBoolean() ? "Arthritis" : "None");

            // Combine additional health conditions into a summary (optional)
            String healthSummary = (hasHighBP ? "High BP" : "Normal BP") + ", " +
                    (hasHighSugar ? "High Sugar" : "Normal Sugar") + ", " +
                    (hasHighCholesterol ? "High Cholesterol" : "Normal Cholesterol") + ", " +
                    "Stress: " + stressLevel + ", Chronic Illness: " + chronicIllness;

            // Generate genetic variants
            List<GeneticVariant> geneticVariants = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                String variantId = "VAR" + (j + 1);
                String gene = "Gene" + (random.nextInt(5) + 1);
                String chromosome = "Chromosome" + (random.nextInt(3) + 1);
                String position = "Position" + (random.nextInt(10000) + 1);
                String genotype = random.nextBoolean() ? "AA" : "AG";
                double riskscore = random.nextDouble();  // Random risk score for each genetic variant
                geneticVariants.add(new GeneticVariant(variantId, gene, chromosome, position, genotype, riskscore));
            }

            // Determine disease status
            boolean hasDisease = (age > 50 || hasHighBP || hasHighSugar || hasHighCholesterol ||
                    diseaseHistory.equals("Heart Disease") || familyMedicalHistory.contains("Heart") ||
                    stressLevel.equals("High") || !chronicIllness.equals("None"));

            // Create a new patient with extended attributes
            PatientData patient = new PatientData(
                    id, name, age, sex, geneticVariants, diseaseHistory, medication, lifestyle, hasDisease,
                    bloodPressure, sugarLevel, cholesterolLevel, physicalActivityLevel, familyMedicalHistory,
                    healthSummary, hasDiabetes, stressLevel, chronicIllness);
            patients.add(patient);

        }
        // Save the patient data to a file
        saveDataToFile(patients);
    }

    public void saveDataToFile(List<PatientData> patients) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PatientData> loadDataFromFile() {
        List<PatientData> data = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            data = (List<PatientData>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean doesDataFileExist() {
        File file = new File(FILE_NAME);
        return file.exists();
    }

    public List<PatientData> getData() {
        if (doesDataFileExist()) {
            System.out.println("Loading existing synthetic data...");
            return loadDataFromFile();
        } else {
            System.out.println("Generating new synthetic data...");
            generateSyntheticData();
            return loadDataFromFile();
        }
    }
}