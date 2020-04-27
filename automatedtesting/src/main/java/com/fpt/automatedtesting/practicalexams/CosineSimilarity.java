package com.fpt.automatedtesting.practicalexams;

import java.util.List;

public class CosineSimilarity {

    public static Double computeSimilarity(List<Double> A, List<Double> B) {

        if (A.size() < B.size()) {
            for (int i = A.size(); i < B.size(); i++) {
                A.add(0.0);
            }
        } else {
            for (int i = B.size(); i < A.size(); i++) {
                B.add(0.0);
            }
        }
        double sumProduct = 0;
        double sumASq = 0;
        double sumBSq = 0;
        for (int i = 0; i < A.size(); i++) {
            if (B.get(i) == 0) {
            }
            sumProduct += A.get(i) * B.get(i);
            sumASq += A.get(i) * A.get(i);
            sumBSq += B.get(i) * B.get(i);
        }
        if (sumASq == 0 && sumBSq == 0) {
            return 2.0;
        }
        return sumProduct / (Math.sqrt(sumASq) * Math.sqrt(sumBSq));
    }
}
