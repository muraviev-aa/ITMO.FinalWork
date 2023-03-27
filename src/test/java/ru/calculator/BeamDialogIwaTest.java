package ru.calculator;

import org.junit.jupiter.api.*;

class BeamDialogIwaTest {
    @Test
    void when400x16And250x10And666x8Then4133Point63() {
        double tf1 = 1.6;
        double tf2 = 1.0;
        double tw = 0.8;
        double h = 66.6;
        double b = 40;
        double d = 25;
        double aycg = 23.62;
        double expected = 4133.63;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcMomentOfResistance1(tf1, tf2, tw, h, b, d, aycg);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then2393Point16() {
        double tf1 = 1.6;
        double tf2 = 1.0;
        double tw = 0.8;
        double h = 66.6;
        double b = 40;
        double d = 25;
        double aycg = 23.62;
        double expected = 2393.16;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcMomentOfResistance2(tf1, tf2, tw, h, b, d, aycg);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8ThenMinus76Point0() {
        double mmax = 315000;
        double wx1 = 4133.63;
        double expected = -76.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressMomentAsimPoint1And2(mmax, wx1);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then132Point0() {
        double mmax = 315000;
        double wx2 = 2393.16;
        double expected = 132.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressMomentAsimPoint3And4(mmax, wx2);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8ThenMinus75Point0() {
        double bmax = 2080263.11;
        double w1 = 173.0;
        double iw = 4817160.21;
        double expected = -75.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressBimomentAsimPoint1(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then75Point0() {
        double bmax = 2080263.11;
        double w1 = 173.0;
        double iw = 4817160.21;
        double expected = 75.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressBimomentAsimPoint2(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then306Point0() {
        double bmax = 2080263.11;
        double w2 = 708.13;
        double iw = 4817160.21;
        double expected = 306.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressBimomentAsimPoint3(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8ThenMinus306Point0() {
        double bmax = 2080263.11;
        double w2 = 708.13;
        double iw = 4817160.21;
        double expected = -306.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.normalStressBimomentAsimPoint4(bmax, w2, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then8Point65() {
        double b = 40;
        double h = 66.6;
        double tf1 = 1.6;
        double tf2 = 1.0;
        double d = 25;
        double expected = 8.65;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcBendCenter(b, h, tf1, tf2, d);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then173Point0() {
        double b = 40;
        double ay = 8.65;
        double expected = 173.0;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcSectorialCoordinateW1(b, ay);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then708Point13() {
        double h = 66.6;
        double ay = 8.65;
        double tf1 = 1.6;
        double tf2 = 1.0;
        double d = 25;
        double expected = 708.13;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcSectorialCoordinateW2(d, ay, h, tf1, tf2);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then4817160Point21() {
        double b = 40;
        double h = 66.6;
        double tf1 = 1.6;
        double tf2 = 1.0;
        double d = 25;
        double expected = 4817160.21;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcSectorialMomentInertia(tf1, tf2, b, d, h);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then92Point34() {
        double b = 40;
        double h = 66.6;
        double tf1 = 1.6;
        double tf2 = 1.0;
        double d = 25;
        double tw = 0.8;
        double expected = 92.34;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcMomentInertiaTorsion(b, tf1, d, tf2, h, tw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when400x16And250x10And666x8Then23Point63() {
        double b = 40;
        double h = 66.6;
        double tf1 = 1.6;
        double tf2 = 1.0;
        double d = 25;
        double tw = 0.8;
        double expected = 23.63;
        BeamDialogIwa beamDialogIwa = new BeamDialogIwa();
        double out = beamDialogIwa.calcCenterOfGravity(tf1, tf2, tw, b, d, h);
        Assertions.assertEquals(expected, out, 0.01);
    }
}