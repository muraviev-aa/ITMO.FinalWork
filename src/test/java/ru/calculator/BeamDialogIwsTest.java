package ru.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeamDialogIwsTest {
    @Test
    void when200x10And300x8Then150Point0() {
        double b = 20;
        double h = 30;
        double expected = 150.0;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.calcSectorialCoordinateW1(b, h);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8Then280333Point34() {
        double tf = 1;
        double b = 20;
        double h = 30;
        double expected = 280333.34;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.calcSectorialMomentInertia(tf, b, h);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8Then23Point37() {
        double tf = 1;
        double b = 20;
        double h = 30;
        double tw = 0.8;
        double expected = 23.37;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.calcMomentInertiaTorsion(b, tf, h, tw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8Then658Point24() {
        double tf = 1;
        double b = 20;
        double h = 30;
        double tw = 0.8;
        double expected = 658.24;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.calcMomentOfResistance(h, b, tw, tf);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8ThenMinus690Point0() {
        double bmax = 1290623.81;
        double w1 = 150.0;
        double iw = 280333.34;
        double expected = -690.0;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.normalStressBimomentSimPoint1And4(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8Then690Point0() {
        double bmax = 1290623.81;
        double w1 = 150.0;
        double iw = 280333.34;
        double expected = 690.0;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.normalStressBimomentSimPoint2And3(bmax, w1, iw);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8ThenMinus479Point0() {
        double mmax = 315000;
        double wx = 658.24;
        double expected = -479.0;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.normalStressMomentSimPoint1And2(mmax, wx);
        Assertions.assertEquals(expected, out, 0.01);
    }

    @Test
    void when200x10And300x8Then479Point0() {
        double mmax = 315000;
        double wx = 658.24;
        double expected = 479.0;
        BeamDialogIws beamDialogIws = new BeamDialogIws();
        double out = beamDialogIws.normalStressMomentSimPoint3And4(mmax, wx);
        Assertions.assertEquals(expected, out, 0.01);
    }
}