package ru.jf17.neronetwork.singlelayerperceptron;

import java.io.*;
import java.util.Arrays;

/**
 * @author Alexandrovich Alexey Borisovich
 * нейросеть из одного нейрона
 * может решать задачи только линейной классификации ( разделить можно только одной линией )
 */
public class SingleLayerPerceptron {

    private SingleLayerPerceptronConfig singleLayerPerceptronConfig;

    private double[] entersInNeuron; // входы в нейрон
    private double[] weights; // веса
    private double STEP_VALUE = 0.01; // шаг обучения весов нейросети
    private int COUNT_ENTRIES = 1; // кол-во входов

    private double[][] patterns = { // примеры на которых будет обучаться нейросеть (из одного нейрона)
            // таблица истиности для двух переменных
//            {0, 0, 0},
//            {0, 1, 1},
//            {1, 0, 1},
//            {1, 1, 1}

            // таблица истиности для трех переменных
//            {0, 0, 0, 0},
//            {0, 0, 1, 0},
//            {0, 1, 0, 0},
//            {0, 1, 1, 0},
//            {1, 0, 0, 0},
//            {1, 0, 1, 1},
//            {1, 1, 0, 1},
//            {1, 1, 1, 1}

            // таблица истиности для четыхер переменных
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 0, 1},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1}

    };
    private double[][] test = { // примеры на которых будет обучаться нейросеть (из одного нейрона)
            // таблица истиности для двух переменных
//            {0, 0, 0},
//            {0, 1, 1},
//            {1, 0, 1},
//            {1, 1, 1}

            // таблица истиности для трех переменных
//            {0, 0, 0, 0},
//            {0, 0, 1, 0},
//            {0, 1, 0, 0},
//            {0, 1, 1, 0},
//            {1, 0, 0, 0},
//            {1, 0, 1, 1},
//            {1, 1, 0, 1},
//            {1, 1, 1, 1}

            // таблица истиности для четыхер переменных
            {0, 0, 0, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0},
            {1, 1, 0, 0, 1},
            {0, 0, 1, 1, 0},
            {1, 0, 0, 1, 1},
            {0, 1, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1}

    };

    public SingleLayerPerceptron() {
        singleLayerPerceptronConfig = new SingleLayerPerceptronConfig();
        COUNT_ENTRIES = patterns[0].length - 1; // автоматически высчитываем кол-во входов на очнове обучающих данных .
        entersInNeuron = new double[COUNT_ENTRIES]; // создаём массив входных нейронов
        weights = new double[entersInNeuron.length]; // создаём массив весов

        for (int i = 0; i < entersInNeuron.length; i++) { // инициализируем веса рандомными значениями
            weights[i] = Math.random() * 0.2 + 0.1; // Значение веса должно быть ближе к нулю , но не должно равняться нулю .
        }
    }

    /**
     * метод вычисляющий значения выходного нейрона
     */
    private double counterOuter() {
        double result = 0; // сначало обнуляем значение
        for (int i = 0; i < entersInNeuron.length; i++) {
            result += entersInNeuron[i] * weights[i]; // результат это сумма произведений всех весов и входов
        }
        if (result > 0.5) {
            result = 1;
        } else {
            result = 0;
        } // функция активации нейрона (получение конечного результата)
        return result;
    }

    /**
     * функция обучения нейрона
     */
    public void train() {
        double gError = 0;
        int epoch = 0;
        do {
            epoch++;
            gError = 0; // обнуляем каждый шаг
            for (int p = 0; p < patterns.length; p++) {
                entersInNeuron = Arrays.copyOf(patterns[p], patterns[p].length - 1); // копируем значение из патерна обучения без последнего символа(без ответа) во входы нейросети
                double outerFromNeuron = counterOuter(); // вычисляем значение выходного нейрона
                double error = patterns[p][COUNT_ENTRIES] - outerFromNeuron; // вычисляем ошибку
                System.out.println("Епоха=" + epoch + " пример на вход№" + p + " локальная ошибка=" + error);
                gError += Math.abs(error); // добавляем значение локальной ошибки(по модулю) к глобальной
                for (int i = 0; i < entersInNeuron.length; i++) {
                    System.out.println("    Был вес =" + weights[i]);
                    double plusValue = STEP_VALUE * error * entersInNeuron[i]; // на сколько нужно увеличить вес ?
                    System.out.println("Вес нужно увеличить на " + plusValue);
                    weights[i] += plusValue;
                    System.out.println("    стал вес =" + weights[i]);
                    System.out.println();
                }
            }
            System.out.println("Глобальная ошибка =" + gError);
        } while (gError != 0);
        System.out.println("Сеть обучалась на " + epoch + " эпохах .");

        singleLayerPerceptronConfig.setWeights(weights);
        singleLayerPerceptronConfig.setCOUNT_ENTRIES(COUNT_ENTRIES);
        singleLayerPerceptronConfig.setEpochs(epoch);
    }

    private boolean checkValue(double realValue, double resultValue) {
        if (realValue == resultValue) {
            return true;
        } else {
            return false;
        }
    }

    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("singleLayerPerceptron.config");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(singleLayerPerceptronConfig);
        oos.flush();
        oos.close();
    }

    public void deSerialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("singleLayerPerceptron.config");
        ObjectInputStream oin = new ObjectInputStream(fis);
        singleLayerPerceptronConfig = (SingleLayerPerceptronConfig) oin.readObject();
        weights = singleLayerPerceptronConfig.getWeights();
        COUNT_ENTRIES = singleLayerPerceptronConfig.getCOUNT_ENTRIES();
    }


    public void prediction() {
        System.out.println("Сеть обучалась на " + singleLayerPerceptronConfig.getEpochs() + " эпохах .");
        for (int p = 0; p < test.length; p++) {
            entersInNeuron = Arrays.copyOf(test[p], test[p].length - 1);
            System.out.println(checkValue(test[p][COUNT_ENTRIES], counterOuter()));
        }
    }
}
