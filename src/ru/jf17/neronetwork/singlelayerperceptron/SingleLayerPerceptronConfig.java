package ru.jf17.neronetwork.singlelayerperceptron;

import java.io.Serializable;

/**
 * @author Alexandrovich Alexey Borisovich
 */
public class SingleLayerPerceptronConfig implements Serializable {
    private int COUNT_ENTRIES; // кол-во входов
    private double[] weights; // веса
    private int epochs; // кол-во эпох прошедших при обучении сети

    public int getCOUNT_ENTRIES() {
        return COUNT_ENTRIES;
    }

    public void setCOUNT_ENTRIES(int COUNT_ENTRIES) {
        this.COUNT_ENTRIES = COUNT_ENTRIES;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }
}
