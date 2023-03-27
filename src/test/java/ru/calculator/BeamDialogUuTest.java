package ru.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeamDialogUuTest {
    @Test
    void when27UThen3Point02() {
        double b = 9.5;
        double tw = 0.6;
        double tf = 1.05;
        double h = 27;
        double ix = 4160;
        double expected = 3.02;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcBendCenter(b, tw, h, tf, ix);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen38Point94() {
        double ax = 3.02;
        int h = 27;
        double tf = 1.05;
        double b = 9.5;
        double expected = 38.94;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcSectorialCoordinateW1(ax, h, tf, b);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen78Point7() {
        double b = 9.5;
        double tw = 0.6;
        double ax = 3.02;
        int h = 27;
        double tf = 1.05;
        double expected = 78.7;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcSectorialCoordinateW2(b, tw, ax, h, tf);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen33117Point17() {
        double tw = 0.6;
        int h = 27;
        double tf = 1.05;
        double b = 9.5;
        double w1 = 38.94;
        double w2 = 78.7;
        double expected = 33117.17;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcSectorialMomentInertia(tw, h, tf, w1, b, w2);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen10Point22() {
        double b = 9.5;
        double tf = 1.05;
        int h = 27;
        double tw = 0.6;
        double expected = 10.22;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcMomentInertiaTorsion(b, tf, h, tw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen0Point01087875() {
        double it = 10.22;
        double iw = 33117.17;
        double expected = 0.01087875;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcBendingTorsionalCharacteristic(it, iw);
        Assertions.assertEquals(expected, out, 0.00000001);
    }

    @Test
    void when27UThen769891Point5() {
        double p = 2100;
        double ex = 8;
        double k = 0.01087875;
        double l = 600;
        double expected = 769891.5;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcBimomentMax1(p, ex, k, l);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen762071Point55() {
        double p = 2100;
        double ex = 8;
        double k = 0.01087875;
        double l = 600;
        double a = 200;
        double expected = 762071.55;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcBimomentMax1Displaced(p, ex, k, l, a);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen315000Point0() {
        double p = 2100;
        double l = 600;
        double expected = 315000.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcMomentMax1(p, l);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThen280000Point0() {
        double p = 2100;
        double l = 600;
        double a = 200;
        double expected = 280000.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.calcMomentMax1Displaced(p, l, a);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27UThenMinus905Point0() {
        double bmax = 769891.5;
        double w1 = 38.94;
        double iw = 33117.17;
        double expected = -905.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint1(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus1830Point0() {
        double bmax = 769891.5;
        double w2 = 78.7;
        double iw = 33117.17;
        double expected = 1830.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint2(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus905Point0() {
        double bmax = 769891.5;
        double w1 = 38.94;
        double iw = 33117.17;
        double expected = 905.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenMinus1830Point0() {
        double bmax = 769891.5;
        double w2 = 78.7;
        double iw = 33117.17;
        double expected = -1830.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint4(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenMinus1023Point0() {
        double mmax = 315000;
        double wx = 308;
        double expected = -1023.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressMomentPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus1023Point0() {
        double mmax = 315000;
        double wx = 308;
        double expected = 1023.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressMomentPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenMinus896Point0() {
        double bmax = 762071.55;
        double w1 = 38.94;
        double iw = 33117.17;
        double expected = -896.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint1(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus1811Point0() {
        double bmax = 762071.55;
        double w2 = 78.7;
        double iw = 33117.17;
        double expected = 1811.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint2(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus896Point0() {
        double bmax = 762071.55;
        double w1 = 38.94;
        double iw = 33117.17;
        double expected = 896.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenMinus1811Point0() {
        double bmax = 762071.55;
        double w2 = 78.7;
        double iw = 33117.17;
        double expected = -1811.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressBimomentPoint4(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenMinus910Point0() {
        double mmax = 280000;
        double wx = 308;
        double expected = -910.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressMomentPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27UThenPlus910Point0() {
        double mmax = 280000;
        double wx = 308;
        double expected = 910.0;
        BeamDialogUu beamDialogUu = new BeamDialogUu();
        double out = beamDialogUu.normalStressMomentPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }
}