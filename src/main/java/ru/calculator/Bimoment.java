package ru.calculator;

public interface Bimoment {
    /**
     * Модуль сдвига [кг/см2]
     */
    double SHIFT = 805300.71;
    /**
     * Модуль упругости [кг/см2]
     */
    double ELASTIC = 2099898.06;

    /**
     * Округление промежуточных значений вычислений
     */
    default double roundWhole(double d) {
        double scale = Math.pow(10, 0);
        return Math.ceil(d * scale) / scale;
    }

    default double roundOne(double d) {
        double scale = Math.pow(10, 1);
        return Math.ceil(d * scale) / scale;
    }

    default double roundTwo(double d) {
        double scale = Math.pow(10, 2);
        return Math.ceil(d * scale) / scale;
    }

    default double roundEight(double d) {
        double scale = Math.pow(10, 8);
        return Math.ceil(d * scale) / scale;
    }

    /**
     * Вычисление изгибно-крутильной характеристики k [1/см]
     */
    default double calcBendingTorsionalCharacteristic(double it, double iw) {
        double k = roundEight(Math.sqrt((SHIFT * it) / (ELASTIC * iw)));
        return k;
    }

    /**
     * Вычисление максимального бимомента, сила в середине пролета [кг*см2]
     */
    default double calcBimomentMax1(double p, double ex, double k, double l) {
        return roundTwo(((p * ex) / (2 * k)) * (Math.sinh(0.5 * k * l) / Math.cosh(0.5 * k * l)));
    }

    /**
     * Вычисление максимального бимомента, сила смещена к левой опоре [кг*см2]
     */
    default double calcBimomentMax1Displaced(double p, double e, double k, double l, double a) {
        return roundTwo(((p * e) / k) * ((Math.sinh(k * a) * Math.sinh(k * (l - a))) / Math.sinh(k * l)));
    }

    /**
     * Вычисление максимального момента, сила в середине пролета [кг*см]
     */
    default double calcMomentMax1(double p, double l) {
        return roundTwo(0.25 * p * l);
    }

    /**
     * Вычисление максимального момента, сила смещена к левой опоре [кг*см]
     */
    default double calcMomentMax1Displaced(double p, double l, double a) {
        return roundTwo((p * a * (l - a)) / l);
    }
}
