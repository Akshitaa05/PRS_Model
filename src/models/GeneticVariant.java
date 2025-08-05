package models;

import java.io.Serializable;

public class GeneticVariant implements Serializable {
    private static final long serialVersionUID = 1L;
    private String variantId;
    private String gene;
    private String chromosome;
    private String position;
    private String genotype;
    private double riskScore;
    public GeneticVariant(String variantId, String gene, String chromosome, String position, String genotype, double riskScore) {
        this.variantId = variantId;
        this.gene = gene;
        this.chromosome = chromosome;
        this.position = position;
        this.genotype = genotype;
        this.riskScore = riskScore;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    @Override
    public String toString() {
        return "GeneticVariant{" +
                "variantId='" + variantId + '\'' +
                ", gene='" + gene + '\'' +
                ", chromosome='" + chromosome + '\'' +
                ", position='" + position + '\'' +
                ", genotype='" + genotype + '\'' +
                ", riskScore=" + riskScore +
                '}';
    }
}
