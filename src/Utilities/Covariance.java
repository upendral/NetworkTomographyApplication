/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

/**
 *
 * @author Perala Laxman
 */
public class Covariance {
    private double covariance;

    public double calCovariance(double[] entriesOne, double[] entriesTwo) {
        int size = entriesOne.length;
        double averageOne = findAverage(entriesOne);
        double averageTwo = findAverage(entriesTwo);
        double covarianceNumerator = 0;
        for(int i = 0; i < size; i++) {
            covarianceNumerator += (entriesOne[i] - averageOne) * (entriesTwo[i] - averageTwo);
        }
        if(size>1) {
            size--;
        }
        covariance = covarianceNumerator / size;
        return covariance;
    }

    private double findAverage(double[] input) {
        double total = 0;
        for(int i = 0; i < input.length; i++) {
            total += input[i];
        }
        return total / input.length;
    }
}
