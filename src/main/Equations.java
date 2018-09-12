package main;

class Equations {

    static void switchEq(Fraction[][] matrix, int indA, int indB) {
        if (indA == indB) return;
        Fraction[] tempEq = matrix[indA];
        matrix[indA] = matrix[indB];
        matrix[indB] = tempEq;
    }

    /**
     * A => A + kB
     *
     * @param matrix
     * @param indA
     * @param indB
     * @param k
     */
    static void addEq(Fraction[][] matrix, int indA, int indB, Fraction k) {
        if (k.equals(0)) return;
        for (int i = 0; i < matrix[indA].length; i++) {
            matrix[indA][i].add(Fraction.multiply(k, matrix[indB][i]));
        }
    }

    /**
     * A => kA
     *
     * @param matrix
     * @param indA
     * @param k
     */
    static void constantMulti(Fraction[][] matrix, int indA, Fraction k) {
        if (k.equals(1)) return;
        for (int i = 0; i < matrix[indA].length; i++) {
            matrix[indA][i].multiply(k);
        }
    }
}
