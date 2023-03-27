package ru.calculator;

public interface SectionI extends Bimoment {
    double calcSectorialCoordinateW1(double b, double h);

    /**
     * Нормальные напряжения для симметричного двутавра от бимомента
     */
    default double normalStressBimomentSimPoint1And4(double bmax, double w1, double iw) {
        return roundWhole((-1) * (bmax * w1) / iw);
    }

    default double normalStressBimomentSimPoint2And3(double bmax, double w1, double iw) {
        return roundWhole((-1) * normalStressBimomentSimPoint1And4(bmax, w1, iw));
    }

    /**
     * Нормальные напряжения для двутавра с одной осью симметрии  от бимомента
     */
    default double normalStressBimomentAsimPoint1(double bmax, double w1, double iw) {
        return roundWhole((-1) * normalStressBimomentAsimPoint2(bmax, w1, iw));
    }

    default double normalStressBimomentAsimPoint2(double bmax, double w1, double iw) {
        return roundWhole((bmax * w1) / iw);
    }

    default double normalStressBimomentAsimPoint3(double bmax, double w2, double iw) {
        return roundWhole((bmax * w2) / iw);
    }

    default double normalStressBimomentAsimPoint4(double bmax, double w2, double iw) {
        return roundWhole((-1) * normalStressBimomentAsimPoint3(bmax, w2, iw));
    }

    /**
     * Нормальные напряжения для симметричного двутавра от момента
     */
    default double normalStressMomentSimPoint1And2(double mmax, double wx) {
        return roundWhole((-1) * normalStressMomentSimPoint3And4(mmax, wx));
    }

    default double normalStressMomentSimPoint3And4(double mmax, double wx) {
        return roundWhole(mmax / wx);
    }

    /**
     * Нормальные напряжения для двутавра с одной осью симметрии  от момента
     */
    default double normalStressMomentAsimPoint1And2(double mmax, double wx1) {
        return roundWhole((-1) * mmax / wx1);
    }

    default double normalStressMomentAsimPoint3And4(double mmax, double wx2) {
        return roundWhole(mmax / wx2);
    }
}
