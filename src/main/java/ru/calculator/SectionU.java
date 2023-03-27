package ru.calculator;

public interface SectionU extends Bimoment {
    /**
     * Вычисление главной секториальной координаты w2 [см2]
     */
    double calcSectorialCoordinateW2(double b, double tw, double ax, double h, double tf);

    /**
     * Вычисление секториального момента  инерции [см6]
     */
    double calcSectorialMomentInertia(double tw, double h, double tf, double w1, double b, double w2);

    /**
     * Вычисление момента инерции при кручении [см4]
     */
    double calcMomentInertiaTorsion(double b, double tf, double h, double tw);

    /**
     * Вычисление напряжения от бимомента в точке 1 [kg/см2]
     */
    default double normalStressBimomentPoint1(double bmax, double w1, double iw) {
        return roundWhole((-1) * (bmax * w1) / iw);
    }

    /**
     * Вычисление напряжения от бимомента в точке 2 [kg/см2]
     */
    default double normalStressBimomentPoint2(double bmax, double w2, double iw) {
        return roundWhole((bmax * w2) / iw);
    }

    /**
     * Вычисление напряжения от бимомента в точке 3 [kg/см2]
     */
    default double normalStressBimomentPoint3(double bmax, double w1, double iw) {
        return roundWhole((-1) * normalStressBimomentPoint1(bmax, w1, iw));
    }

    /**
     * Вычисление напряжения от бимомента в точке 4 [kg/см2]
     */
    default double normalStressBimomentPoint4(double bmax, double w2, double iw) {
        return roundWhole((-1) * normalStressBimomentPoint2(bmax, w2, iw));
    }

    /**
     * Вычисление напряжения от момента в точках 1 и 2 [kg/см2]
     */
    default double normalStressMomentPoint1And2(double mmax, double wx) {
        return roundWhole((-1) * normalStressMomentPoint3And4(mmax, wx));
    }

    /**
     * Вычисление напряжения от момента в точках 3 и 4 [kg/см2]
     */
    default double normalStressMomentPoint3And4(double mmax, double wx) {
        return roundWhole(mmax / wx);
    }
}
