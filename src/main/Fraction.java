package main;

final class Fraction extends Number{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if(denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if(denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public byte byteValue() {
        return (byte) this.doubleValue();
    }

    public double doubleValue() {
        return ((double) numerator)/((double) denominator);
    }

    public float floatValue() {
        return (float) this.doubleValue();
    }

    public int intValue() {
        return (int) this.doubleValue();
    }

    public long longValue() {
        return (long) this.doubleValue();
    }

    public short shortValue() {
        return (short) this.doubleValue();
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return "" + numerator;
        }
        return numerator + "/" + denominator;
    }

    public boolean equals(Fraction frac) {
        return this.compareTo(frac) == 0;
    }
    public boolean equals(int num) {
        return this.compareTo(new Fraction(num)) == 0;
    }

    public int compareTo(Fraction frac) {
        long t = this.getNumerator() * frac.getDenominator();
        long f = frac.getNumerator() * this.getDenominator();
        int result = 0;
        if(t>f) {
            result = 1;
        }
        else if(f>t) {
            result = -1;
        }
        return result;
    }

    public void add(Fraction frac) {
        this.numerator = numerator * frac.denominator + frac.numerator * denominator;
        this.denominator *= frac.denominator;
        simplify();
    }

    public void substract(Fraction frac) {
        this.numerator = numerator * frac.denominator - frac.numerator * denominator;
        this.denominator *= frac.denominator;
        simplify();
    }

    public void multiply(Fraction frac) {
        this.numerator *= frac.numerator;
        this.denominator *= frac.denominator;
        simplify();
    }

    public void divide(Fraction frac) {
        if (frac.numerator == 0) {
            throw new IllegalArgumentException("cannot divide by zero");
        }
        this.numerator *= frac.denominator;
        this.denominator *= frac.numerator;
        if (denominator < 0) {
            numerator *= -1;
            denominator*= -1;
        }
        simplify();
    }

    public void simplify() {
        int gcm = Math.abs(gcm(numerator, denominator));
        numerator /= gcm;
        denominator /= gcm;
    }

    private static int gcm(int a, int b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    static Fraction divide(Fraction frac1, Fraction frac2) {
        Fraction result = new Fraction(
                frac1.getNumerator() * frac2.getDenominator(),
                frac1.getDenominator() * frac2.getNumerator()
        );
        result.simplify();
        return result;
    }

    static Fraction multiply(Fraction frac1, Fraction frac2) {
        Fraction result = new Fraction(
                frac1.getNumerator() * frac2.getNumerator(),
                frac1.getDenominator() * frac2.getDenominator()
        );
        result.simplify();
        return result;
    }

}
