package ru.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeamDialogITest {
    @Test
    void when30SH1ThenMinus441Point0() {
        double mmax = 315000;
        double wx = 715;
        double expected = -441.0;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.normalStressMomentSimPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1Then441Point0() {
        double mmax = 315000;
        double wx = 715;
        double expected = 441.0;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.normalStressMomentSimPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1ThenMinus653Point0() {
        double bmax = 1290623.81;
        double w1 = 145.5;
        double iw = 287466.67;
        double expected = -653.0;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.normalStressBimomentSimPoint1And4(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1Then653Point0() {
        double bmax = 1290623.81;
        double w1 = 145.5;
        double iw = 287466.67;
        double expected = 653.0;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.normalStressBimomentSimPoint2And3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1Then145Point5() {
        double b = 20;
        double h = 29.1;
        double expected = 145.5;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.calcSectorialCoordinateW1(b, h);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1Then287466Point67() {
        double tf = 1.1;
        double b = 20;
        double h = 29.1;
        double expected = 287466.67;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.calcSectorialMomentInertia(tf, b, h);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when30SH1Then28Point82() {
        double tf = 1.1;
        double b = 20;
        double h = 29.1;
        double tw = 0.8;
        double expected = 28.82;
        BeamDialogI beamDialogI = new BeamDialogI();
        double out = beamDialogI.calcMomentInertiaTorsion(b, tf, h, tw);
        Assertions.assertEquals(expected, out, 0.01);
    }
}