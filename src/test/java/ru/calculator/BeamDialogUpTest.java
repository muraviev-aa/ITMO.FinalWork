package ru.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeamDialogUpTest {
    @Test
    void when27PThen3Point13() {
        double b = 9.5;
        double tw = 0.6;
        double tf = 1.05;
        int h = 27;
        double expected = 3.13;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcBendCenter(b, tw, h, tf);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen40Point6() {
        double ax = 3.13;
        int h = 27;
        double tf = 1.05;
        double expected = 40.62;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcSectorialCoordinateW1(ax, h, tf);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen78Point76() {
        double b = 9.5;
        double tw = 0.6;
        double ax = 3.13;
        int h = 27;
        double tf = 1.05;
        double expected = 78.76;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcSectorialCoordinateW2(b, tw, ax, h, tf);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen38534Point48() {
        double tw = 0.6;
        int h = 27;
        double tf = 1.05;
        double b = 9.5;
        double w1 = 40.62;
        double w2 = 78.76;
        double expected = 38534.48;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcSectorialMomentInertia(tw, h, tf, w1, b, w2);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen10Point22() {
        double b = 9.5;
        double tf = 1.05;
        int h = 27;
        double tw = 0.6;
        double expected = 10.22;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcMomentInertiaTorsion(b, tf, h, tw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen0Point01008511() {
        double it = 10.22;
        double iw = 38534.48;
        double expected = 0.01008511;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcBendingTorsionalCharacteristic(it, iw);
        Assertions.assertEquals(expected, out, 0.00000001);
    }

    @Test
    void when27PThen828996Point72() {
        double p = 2100;
        double ex = 8;
        double k = 0.01008511;
        double l = 600;
        double expected = 828996.72;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcBimomentMax1(p, ex, k, l);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen817914Point55() {
        double p = 2100;
        double ex = 8;
        double k = 0.01008511;
        double l = 600;
        double expected = 817914.55;
        double a = 200;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcBimomentMax1Displaced(p, ex, k, l, a);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen315000Point00() {
        double p = 2100;
        double l = 600;
        double expected = 315000.00;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcMomentMax1(p, l);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThen280000Point00() {
        double p = 2100;
        double l = 600;
        double a = 200;
        double expected = 280000.00;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.calcMomentMax1Displaced(p, l, a);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when27PThenMinus873Point0() {
        double bmax = 828996.8;
        double w1 = 40.62;
        double iw = 38534.48;
        double expected = -873.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint1(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus1695Point0() {
        double bmax = 828996.8;
        double w2 = 78.76;
        double iw = 38534.48;
        double expected = 1695.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint2(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus873Point0() {
        double bmax = 828996.8;
        double w1 = 40.62;
        double iw = 38534.48;
        double expected = 873.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenMinus1695Point0() {
        double bmax = 828996.8;
        double w2 = 78.76;
        double iw = 38534.48;
        double expected = -1695.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint4(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenMinus1017Point0() {
        double mmax = 315000;
        double wx = 310;
        double expected = -1017.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressMomentPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus1017Point0() {
        double mmax = 315000;
        double wx = 310;
        double expected = 1017.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressMomentPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenMinus862Point0() {
        double bmax = 817914.6;
        double w1 = 40.62;
        double iw = 38534.48;
        double expected = -862.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint1(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus1672Point0() {
        double bmax = 817914.6;
        double w2 = 78.76;
        double iw = 38534.48;
        double expected = 1672.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint2(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus862Point0() {
        double bmax = 817914.6;
        double w1 = 40.62;
        double iw = 38534.48;
        double expected = 862.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenMinus1672Point0() {
        double bmax = 817914.6;
        double w2 = 78.76;
        double iw = 38534.48;
        double expected = -1672.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressBimomentPoint4(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenMinus904Point0() {
        double mmax = 280000;
        double wx = 310;
        double expected = -904.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressMomentPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }

    @Test
    void when27PThenPlus904Point0() {
        double mmax = 280000;
        double wx = 310;
        double expected = 904.0;
        BeamDialogUp beamDialogUp = new BeamDialogUp();
        double out = beamDialogUp.normalStressMomentPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.1);
    }
}