# PRS_Model
A predictive ML model for polygenic disorders using synthetic genomic and clinical data. Calculates Polygenic Risk Scores (PRS) and uses Gradient Boosting to classify disease risk, enabling early detection and personalized treatment strategies.

## 🧠 What Are Polygenic Disorders?

Polygenic disorders are diseases influenced by the cumulative effect of many genes, each contributing a small risk. Unlike monogenic disorders (caused by a single gene), these involve complex gene-environment interactions.

**Examples include:**
- Schizophrenia  
- Autism Spectrum Disorder (ASD)  
- Type 2 Diabetes  
- Cardiovascular diseases  
- Depression

---

## 🎯 Objectives

- Generate **synthetic patient data** including genomic and lifestyle features.
- Compute a **Polygenic Risk Score (PRS)** based on known genetic variants.
- Train a **Gradient Boosting Classifier** to predict the presence of a disorder.
- Provide actionable insights into patient-level risk using a CLI-based interface.

---

## 📁 Dataset Description

This project uses **synthetically generated data** for 100 patients. Each patient’s data includes:

### 🧬 Genetic Data
- SNPs (Single Nucleotide Polymorphisms)
- Risk alleles and effect sizes (simulated GWAS data)
- PRS (computed per patient)

### 🏥 Clinical & Lifestyle Data
- Age, Sex
- Medical History Score
- Behavioral Score (e.g., smoking, alcohol)
- Physical Activity Level
- Stress Level
- Blood Pressure
- Cholesterol

### 🧾 Output Label
- Binary classification: **Has Disorder** (1) or **No Disorder** (0)

---

## ⚙️ Methodology

1. **Data Generation**:  
   Synthetic patients and variants were created with balanced classes and realistic distributions.

2. **Polygenic Risk Score Calculation**:  
   PRS is calculated as the weighted sum of risk alleles using simulated GWAS effect sizes.

3. **Feature Engineering**:  
   Merged clinical, lifestyle, and PRS data into a single dataset.

4. **Modeling**:  
   A **Gradient Boosting Classifier** was trained to predict disease presence.

5. **Evaluation**:  
   Accuracy, Precision, Recall, F1-Score, and Confusion Matrix used for model evaluation.

6. **Interface**:  
   Command-line interaction to query patient risk, view data summaries, and visualize results.

---

## 🚀 Features

- ✅ **Complete Synthetic Dataset**
- 🧮 **PRS Calculation Module**
- 🤖 **ML Model for Disease Prediction**
- 🗂️ **Integrated Feature Pipeline**
- 📈 **Model Evaluation & Visualization Ready**
- 💻 **CLI-based Interactive Application**
- ⚠️ **Built-in Error Handling for Edge Cases**

## 📊 Output and Insights

Binary prediction: Disease / No Disease
Numerical PRS for each patient
Optional data visualization (scatter plots, histograms)
Evaluation metrics (accuracy, confusion matrix, etc.)

## 🔒 Ethical Considerations

Synthetic data used to avoid real-world biases and privacy concerns.
No real patient data was used.
Model is a prototype only — not intended for clinical use.
PRS and ML predictions should always be interpreted alongside medical advice.

## 🌱 Future Scope

Add real-world GWAS datasets for external validation.
Introduce deep learning models for improved accuracy.
Deploy as a web app with interactive dashboard.
Expand dataset with multi-ethnic cohorts.
Add time-series patient monitoring.
