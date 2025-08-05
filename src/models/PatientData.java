package models;

import java.io.Serializable;
import java.util.List;

public class PatientData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int age;
    private String sex;
    private List<GeneticVariant> geneticVariants;
    private String diseaseHistory;  // Added clinical data
    private String medication;      // Added clinical data
    private String lifestyle;       // Behavioral data (e.g., exercise, smoking)
    private boolean hasDisease;     // Label: whether the patient has a disease
    private double bloodPressure;
    private double sugarLevel;
    private double cholesterolLevel;
    private String physicalActivityLevel;
    private String familyMedicalHistory;
    private String healthSummary;
    private boolean hasDiabetes;
    private String stressLevel;
    private String chronicIllness;

    // Constructor with additional fields
    public PatientData(String id, String name, int age, String sex, List<GeneticVariant> geneticVariants,
                       String diseaseHistory, String medication, String lifestyle, boolean hasDisease,
                       double bloodPressure, double sugarLevel, double cholesterolLevel,
                       String physicalActivityLevel, String familyMedicalHistory, String healthSummary,
                       boolean hasDiabetes, String stressLevel, String chronicIllness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.geneticVariants = geneticVariants;
        this.diseaseHistory = diseaseHistory;
        this.medication = medication;
        this.lifestyle = lifestyle;
        this.hasDisease = hasDisease;
        this.bloodPressure = bloodPressure;
        this.sugarLevel = sugarLevel;
        this.cholesterolLevel = cholesterolLevel;
        this.physicalActivityLevel = physicalActivityLevel;
        this.familyMedicalHistory = familyMedicalHistory;
        this.healthSummary = healthSummary;
        this.hasDiabetes = hasDiabetes;
        this.stressLevel = stressLevel;
        this.chronicIllness = chronicIllness;
    }

    // Getters and Setters for all fields
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public List<GeneticVariant> getGeneticVariants() {
        return geneticVariants;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public String getMedication() {
        return medication;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public boolean hasDisease() {
        return hasDisease;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public double getSugarLevel() {
        return sugarLevel;
    }

    public double getCholesterolLevel() {
        return cholesterolLevel;
    }

    public String getPhysicalActivityLevel() {
        return physicalActivityLevel;
    }

    public String getFamilyMedicalHistory() {
        return familyMedicalHistory;
    }

    public String getHealthSummary() {
        return healthSummary;
    }

    public boolean hasDiabetes() {
        return hasDiabetes;
    }

    public String getStressLevel() {
        return stressLevel;
    }

    public String getChronicIllness() {
        return chronicIllness;
    }

    @Override
    public String toString() {
        return "PatientData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", diseaseHistory='" + diseaseHistory + '\'' +
                ", medication='" + medication + '\'' +
                ", lifestyle='" + lifestyle + '\'' +
                ", hasDisease=" + hasDisease +
                ", bloodPressure=" + bloodPressure +
                ", sugarLevel=" + sugarLevel +
                ", cholesterolLevel=" + cholesterolLevel +
                ", physicalActivityLevel='" + physicalActivityLevel + '\'' +
                ", familyMedicalHistory='" + familyMedicalHistory + '\'' +
                ", healthSummary='" + healthSummary + '\'' +
                ", hasDiabetes=" + hasDiabetes +
                ", stressLevel='" + stressLevel + '\'' +
                ", chronicIllness='" + chronicIllness + '\'' +
                '}';
    }
}
