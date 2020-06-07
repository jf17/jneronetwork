package ru.jf17.neronetwork.singlelayerperceptron;

import java.io.IOException;

/**
 * @author Alexandrovich Alexey Borisovich
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SingleLayerPerceptron perceptron = new SingleLayerPerceptron();
        // First step
        perceptron.train();
        perceptron.serialize();

        // Second step
//        perceptron.deSerialize();
//        perceptron.prediction();

    }
}
