package ru.calculator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;

public class BeamDialogUu extends JFrame implements Bimoment, SectionU {
    /**
     * Расчет прокатного швеллера с уклоном полок
     */
    /**
     * Толщина полки [см]
     */
    private double tf;
    /**
     * Ширина полки [см]
     */
    private double b;
    /**
     * Толщина стенки [см]
     */
    private double tw;
    /**
     * Высота сечения [см]
     */
    private double h;
    /**
     * Сосредоточенная сила [кг]
     */
    private double p;
    /**
     * Эксцентриситет [см]
     */
    private double ex;
    /**
     * Пролет балки [см]
     */
    private double l;
    /**
     * Смещение силы от левой опоры [см]
     */
    private double a;
    /**
     * Координата центра изгиба [см]
     */
    private double ax;
    /**
     * Секториальный момент инерции [см6]
     */
    private double iw;
    /**
     * Главная секториальная координата w1 [см2]
     */
    private double w1;
    /**
     * Главная секториальная координата w2 [см2]
     */
    private double w2;
    /**
     * Момент инерции при чистом кручении [см4]
     */
    private double it;
    /**
     * Момент инерции сечения относительно оси Х [см4]
     */
    double ix;
    /**
     * Упругая изгибно-крутильная характеристика [1/см]
     */
    private double k;
    /**
     * Максимальный бимомент [кг*см2]
     */
    private double bmax;
    /**
     * Максимальный момент [кг*см]
     */
    private double mmax;
    /**
     * Момент сопротивления сечения [см3]
     */
    private double wx;
    /**
     * Расчетное сопротивление стали [кг/см2]
     */
    private int ry;
    private JTabbedPane tabbedPane;
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox default27PCheckBox;
    private JButton closeButton;
    private JButton calculateButton;
    private JButton acceptButton;
    private JButton changeButton;
    private JButton resetButton;
    private JButton helpButton;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JCheckBox c245CheckBox;
    private JCheckBox c255CheckBox;
    private JCheckBox c345CheckBox;
    private JLabel label2450;
    private JLabel label2550;
    private JLabel label3450;
    private JCheckBox selectCheckBox;
    private JCheckBox selectCheckBox2;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JButton solutionButton;
    private JButton fixButton;
    private JButton resetButton1;
    private JButton closeButton1;
    private JButton bimomentButton;
    private JButton stressButton;
    private JTextField textField16;
    private JTextField textField17;
    private JLabel sigmaB1;
    private JLabel sigmaB2;
    private JLabel sigmaB3;
    private JLabel sigmaB4;
    private JLabel sigmaM1;
    private JLabel sigmaM2;
    private JLabel sigmaM3;
    private JLabel sigmaM4;
    private JLabel sigmaS1;
    private JLabel sigmaS2;
    private JLabel sigmaS3;
    private JLabel sigmaS4;
    private JTextField textField18;
    private JTextField textField19;
    private JLabel labelA;
    private JLabel finale;
    private JLabel stop;
    private JTextField textField20;
    private JButton helpButton1;
    private JButton selectButton;
    boolean flag;

    public BeamDialogUu() {
        super("Швеллер");
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/ant.gif")));
        /**
         * Настройка размеров JButton
         */
        Dimension dimension = new Dimension(120, 30);
        resetButton.setPreferredSize(dimension);
        calculateButton.setPreferredSize(dimension);
        closeButton.setPreferredSize(dimension);
        resetButton1.setPreferredSize(dimension);
        solutionButton.setPreferredSize(dimension);
        closeButton1.setPreferredSize(dimension);
        helpButton1.setPreferredSize(dimension);
        sigmaB1.setForeground(Color.MAGENTA);
        sigmaB4.setForeground(Color.MAGENTA);
        sigmaM1.setForeground(Color.MAGENTA);
        sigmaM2.setForeground(Color.MAGENTA);
        sigmaS1.setForeground(Color.MAGENTA);
        sigmaS4.setForeground(Color.MAGENTA);
        helpButton.setVisible(false);
        changeButton.setVisible(false);
        acceptButton.setVisible(false);
        stressButton.setVisible(false);
        bimomentButton.setVisible(false);
        fixButton.setVisible(false);
        tabbedPane.setVisible(true);
        setSize(830, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        closeButton.addActionListener(e -> dispose());

        resetButton.addActionListener(e -> {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField6.setText("");
            textField7.setText("");
            textField8.setText("");
            textField9.setText("");
            textField10.setText("");
            textField11.setText("");
            textField20.setText("");
        });

        default27PCheckBox.addActionListener(e -> {
            textField4.setText("27");
            textField3.setText("9.5");
            textField2.setText("1.05");
            textField1.setText("0.6");
            textField11.setText("4160");
            textField20.setText("308");
        });

        calculateButton.addActionListener(e -> actionSolution());

        c245CheckBox.addActionListener(e -> {
            label2450.setVisible(true);
            label2450.setText("2450 [кг/см2]");
            label2450.setForeground(Color.BLUE);
            label2550.setVisible(false);
            label3450.setVisible(false);
            ry = 2450;
        });

        c255CheckBox.addActionListener(e -> {
            label2550.setVisible(true);
            label2550.setText("2550 [кг/см2]");
            label2550.setForeground(Color.BLUE);
            label2450.setVisible(false);
            label3450.setVisible(false);
            ry = 2550;
        });

        c345CheckBox.addActionListener(e -> {
            label3450.setVisible(true);
            label3450.setText("3450 [кг/см2]");
            label3450.setForeground(Color.BLUE);
            label2450.setVisible(false);
            label2550.setVisible(false);
            ry = 3450;
        });

        selectCheckBox.addActionListener(e -> {
            flag = false;
            textField15.setVisible(true);
            labelA.setVisible(true);
        });

        selectCheckBox2.addActionListener(e -> {
            flag = true;
            textField15.setVisible(false);
            labelA.setVisible(false);
        });

        resetButton1.addActionListener(e -> {
            textField12.setText("");
            textField13.setText("");
            textField14.setText("");
            textField15.setText("");
            textField16.setText("");
            textField17.setText("");
            textField18.setText("");
            textField19.setText("");
            sigmaB1.setText("");
            sigmaB2.setText("");
            sigmaB3.setText("");
            sigmaB4.setText("");
            sigmaM1.setText("");
            sigmaM2.setText("");
            sigmaM3.setText("");
            sigmaM4.setText("");
            sigmaS1.setText("");
            sigmaS2.setText("");
            sigmaS3.setText("");
            sigmaS4.setText("");
        });

        solutionButton.addActionListener(e -> {
            if (flag) {
                actionCalcScheme();
                actionSolutionScheme();
            } else {
                actionCalcSchemeDisplaced();
                actionSolutionSchemeDisplaced();
            }
        });

        closeButton1.addActionListener(e -> dispose());
        helpButton1.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/normal_up.png"))),
                    SwingConstants.CENTER);
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            image.setBorder(softBevelLowered);
            dialog.getContentPane().add(image);
            dialog.setSize(500, 250);
            /**
             * Расположение JDialog по центру
             */
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        });

        selectButton.addActionListener(e -> {
            try {
                seeTable();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Создает диалоговое окно
     */
    private JDialog createDialog() {
        JDialog dialog = new JDialog(this, "Справка", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        return dialog;
    }

    public void actionCalcScheme() {
        p = Double.parseDouble(textField12.getText());
        ex = Double.parseDouble(textField13.getText());
        l = Double.parseDouble(textField14.getText());
    }

    public void actionCalcSchemeDisplaced() {
        p = Double.parseDouble(textField12.getText());
        ex = Double.parseDouble(textField13.getText());
        l = Double.parseDouble(textField14.getText());
        a = Double.parseDouble(textField15.getText());
    }

    /**
     * Считывание из textField и присвоение вычисленных значений в textField
     */
    public void actionSolution() {
        h = Double.parseDouble(textField4.getText());
        b = Double.parseDouble(textField3.getText());
        tf = Double.parseDouble(textField2.getText());
        tw = Double.parseDouble(textField1.getText());
        ix = Double.parseDouble(textField11.getText());
        wx = Double.parseDouble(textField20.getText());
        textField10.setText(String.valueOf(calcBendCenter(b, tw, h, tf, ix)));
        textField9.setText(String.valueOf(calcSectorialCoordinateW1(ax, h, tf, b)));
        textField5.setText(String.valueOf(calcSectorialCoordinateW2(b, tw, ax, h, tf)));
        textField8.setText(String.valueOf(calcSectorialMomentInertia(tw, h, tf, w1, b, w2)));
        textField7.setText(String.valueOf(calcMomentInertiaTorsion(b, tf, h, tw)));
        textField6.setText(String.valueOf(calcBendingTorsionalCharacteristic(it, iw)));
    }

    /**
     * Расчет для схемы с силой в середине пролета
     * и присвоение вычисленных значений в JLabel
     */
    public void actionSolutionScheme() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1(p, ex, k, l);
        mmax = calcMomentMax1(p, l);
        textField17.setText(String.valueOf(calcBimomentMax1(p, ex, k, l)));
        textField16.setText(String.valueOf(calcMomentMax1(p, l)));
        double r = (normalStressBimomentPoint3(bmax, w1, iw)
                + normalStressMomentPoint3And4(mmax, wx)) / ry;
        textField18.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField18.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField18.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        sigmaB1.setText(String.valueOf(format.format(normalStressBimomentPoint1(bmax, w1, iw))));
        sigmaB2.setText("+" + format.format(normalStressBimomentPoint2(bmax, w2, iw)));
        sigmaB3.setText("+" + format.format(normalStressBimomentPoint3(bmax, w1, iw)));
        sigmaB4.setText(String.valueOf(format.format(normalStressBimomentPoint4(bmax, w2, iw))));
        sigmaM1.setText(String.valueOf(format.format(normalStressMomentPoint1And2(mmax, wx))));
        sigmaM2.setText(String.valueOf(format.format(normalStressMomentPoint1And2(mmax, wx))));
        sigmaM3.setText("+" + format.format(normalStressMomentPoint3And4(mmax, wx)));
        sigmaM4.setText("+" + format.format(normalStressMomentPoint3And4(mmax, wx)));
        sigmaS1.setText(String.valueOf(format.format(normalStressBimomentPoint1(bmax, w1, iw)
                + normalStressMomentPoint1And2(mmax, wx))));
        sigmaS2.setText("+" + format.format(normalStressBimomentPoint2(bmax, w2, iw)
                + normalStressMomentPoint1And2(mmax, wx)));
        sigmaS3.setText("+" + format.format(normalStressBimomentPoint3(bmax, w1, iw)
                + normalStressMomentPoint3And4(mmax, wx)));
        sigmaS4.setText(String.valueOf(format.format(normalStressBimomentPoint4(bmax, w2, iw)
                + normalStressMomentPoint3And4(mmax, wx))));
        textField19.setText(String.valueOf(roundOne((normalStressBimomentPoint1(bmax, w1, iw) * 100)
                / (normalStressBimomentPoint1(bmax, w1, iw) + normalStressMomentPoint1And2(mmax, wx)))));
    }

    /**
     * Расчет для схемы сила смещена к левой опоре
     * и передача вычисленных значений в JLabel
     */
    public void actionSolutionSchemeDisplaced() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1Displaced(p, ex, k, l, a);
        mmax = calcMomentMax1Displaced(p, l, a);
        textField17.setText(String.valueOf(calcBimomentMax1Displaced(p, ex, k, l, a)));
        textField16.setText(String.valueOf(calcMomentMax1Displaced(p, l, a)));
        double r = (normalStressBimomentPoint3(bmax, w1, iw)
                + normalStressMomentPoint3And4(mmax, wx)) / ry;
        textField18.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField18.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField18.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        sigmaB1.setText(String.valueOf(format.format(normalStressBimomentPoint1(bmax, w1, iw))));
        sigmaB2.setText("+" + format.format(normalStressBimomentPoint2(bmax, w2, iw)));
        sigmaB3.setText("+" + format.format(normalStressBimomentPoint3(bmax, w1, iw)));
        sigmaB4.setText(String.valueOf(format.format(normalStressBimomentPoint4(bmax, w2, iw))));
        sigmaM1.setText(String.valueOf(format.format(normalStressMomentPoint1And2(mmax, wx))));
        sigmaM2.setText(String.valueOf(format.format(normalStressMomentPoint1And2(mmax, wx))));
        sigmaM3.setText("+" + format.format(normalStressMomentPoint3And4(mmax, wx)));
        sigmaM4.setText("+" + format.format(normalStressMomentPoint3And4(mmax, wx)));
        sigmaS1.setText(String.valueOf(format.format(normalStressBimomentPoint1(bmax, w1, iw)
                + normalStressMomentPoint1And2(mmax, wx))));
        sigmaS2.setText("+" + format.format(normalStressBimomentPoint2(bmax, w2, iw)
                + normalStressMomentPoint1And2(mmax, wx)));
        sigmaS3.setText("+" + format.format(normalStressBimomentPoint3(bmax, w1, iw)
                + normalStressMomentPoint3And4(mmax, wx)));
        sigmaS4.setText(String.valueOf(format.format(normalStressBimomentPoint4(bmax, w2, iw)
                + normalStressMomentPoint3And4(mmax, wx))));
        textField19.setText(String.valueOf(roundOne((normalStressBimomentPoint1(bmax, w1, iw) * 100)
                / (normalStressBimomentPoint1(bmax, w1, iw) + normalStressMomentPoint1And2(mmax, wx)))));
    }

    /**
     * Вычисление координаты центра изгиба ax [см]
     */
    double calcBendCenter(double b, double tw, double h, double tf, double ix) {
        double t = (Math.pow(b - 0.5 * tw, 2) * (h - (tf + 0.07 * 0.25 * b))) / (24 * ix);
        double c = 2 * tf - 0.05 * tw;
        double d = 2 * (h - (tf - 0.07 * 0.5 * b)) + (h - (tf + 0.07 * 0.25 * b));
        double f = 0.1 * (b - 0.5 * tw) * (h - (tf - 0.07 * 0.5 * b));
        ax = roundTwo(t * (c * d - f));
        return ax;
    }


    /**
     * Вычисление секториальной координаты W1 [см2]
     */
    double calcSectorialCoordinateW1(double ax, double h, double tf, double b) {
        w1 = roundTwo(0.5 * ax * (h - (tf + 0.07 * 0.25 * b)));
        return w1;
    }

    /**
     * Вычисление секториальной координаты W2 [см2]
     */
    @Override
    public double calcSectorialCoordinateW2(double b, double tw, double ax, double h, double tf) {
        w2 = roundTwo(0.5 * (h - (tf + 0.07 * 0.25 * b)) * ((b - 0.5 * tw) - ax)
                - 0.5 * 0.07 * (b - 0.5 * tw) * ax);
        return w2;
    }

    /**
     * Вычисление секториального момента инерции Iw [см6]
     */
    @Override
    public double calcSectorialMomentInertia(double tw, double h, double tf, double w1, double b, double w2) {
        double f = (tw * (h - (tf + 0.07 * 0.25 * b)) * Math.pow(w1, 2)) / 3;
        double c = ((2 * tf * (b - 0.5 * tw)) / 3) * (Math.pow(w1, 2) + Math.pow(w2, 2) - w1 * w2);
        double d = ((0.07 * Math.pow((b - 0.5 * tw), 2)) / 6) * (Math.pow(w2, 2) - Math.pow(w1, 2));
        iw = roundTwo(f + c - d);
        return iw;
    }

    /**
     * Вычисление момента инерции при кручении It [см4]
     */
    @Override
    public double calcMomentInertiaTorsion(double b, double tf, double h, double tw) {
        it = roundTwo((1.12 / 3) * (2 * b * Math.pow(tf, 3) + (h - 2 * tf) * Math.pow(tw, 3)));
        return it;
    }

    public void seeTable() throws Exception {
        String dsn = "jdbc:postgresql://localhost:5432/test";
        String uid = "postgres";
        String pwd = "admin";
        Connection conn = DriverManager.getConnection(dsn, uid, pwd);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profiles.shvu"
                + "\n" + "ORDER BY number_shvu ASC");
        final DatabaseTableModel dbm =
                new DatabaseTableModel(true);
        dbm.setDataSource(rs);
        SwingUtilities.invokeLater(
                () -> {
                    JTable table = new JTable(dbm);
                    JFrame frame = new JFrame("Швеллер с уклоном внутренних граней полок");
                    frame.setIconImage(Toolkit.getDefaultToolkit().
                            getImage(getClass().getResource("/ant.gif")));
                    frame.setSize(500, 300);
                    frame.add(new JScrollPane(table));
                    table.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            int i = table.getSelectedRow();
                            TableModel model = table.getModel();
                            textField4.setText(model.getValueAt(i, 1).toString());
                            textField3.setText(model.getValueAt(i, 2).toString());
                            textField1.setText(model.getValueAt(i, 3).toString());
                            textField2.setText(model.getValueAt(i, 4).toString());
                            textField20.setText(model.getValueAt(i, 5).toString());
                            textField11.setText(model.getValueAt(i, 6).toString());
                        }
                    });
                    frame.setVisible(true);
                });
    }
}
