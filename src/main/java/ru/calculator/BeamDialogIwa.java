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

public class BeamDialogIwa extends JFrame implements Bimoment, SectionI {
    /**
     * Расчет сварного двутавра, сечение симметрично относительно одной оси
     */
    /**
     * Толщина верхней полки [см]
     */
    private double tf1;
    /**
     * Толщина нижней полки [см]
     */
    private double tf2;
    /**
     * Ширина верхней полки [см]
     */
    private double b;
    /**
     * Ширина нижней полки [см]
     */
    private double d;
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
    /**
     * Смещение силы от левой опоры [см]
     */
    private double a;
    private double l;
    /**
     * Координата центра изгиба [см]
     * по оси OY от середины верхней полки
     */
    private double ay;
    /**
     * Координата центра тяжести сечени [см]
     * по оси OY от середины верхней полки
     */
    private double aycg;
    /**
     * Главная секториальная координата w1 [см2]
     */
    private double w1;
    /**
     * Главная секториальная координата w2 [см2]
     */
    private double w2;
    /**
     * Секториальный момент инерции [см6]
     */
    private double iw;
    /**
     * Момент инерции при чистом кручении [см4]
     */
    private double it;
    /**
     * Момент сопротивления сечения [см3]
     */
    private double wx1;
    /**
     * Момент сопротивления сечения [см3]
     */
    private double wx2;
    /**
     * Момент инерции [см4]
     */
    private double ix;
    /**
     * Упругая изгибно-крутильная характеристика [1/см]
     */
    private double k;
    /**
     * Максимальный бимомент [кг/см2]
     */
    private double bmax;
    /**
     * Максимальный момент [кг*см]
     */
    private double mmax;
    /**
     * Расчетное сопротивление стали [кг/см2]
     */
    private int ry;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton closeButton;
    private JButton calculateButton;
    private JButton acceptButton;
    private JButton changeButton;
    private JButton resetButton;
    private JButton helpButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JCheckBox defaultShelf400x16Shelf250x10Wall666x8CheckBox;
    private JTextField textField15;
    private JCheckBox c245CheckBox;
    private JCheckBox c255CheckBox;
    private JCheckBox c345CheckBox;
    private JLabel label2450;
    private JLabel label2550;
    private JLabel label3450;
    private JCheckBox selectCheckBox;
    private JCheckBox selectCheckBox2;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JButton solutionButton;
    private JButton closeButton1;
    private JButton resetButton1;
    private JButton diagramButton;
    private JButton bimomentButton;
    private JButton stressButton;
    private JTextField textField20;
    private JTextField textField21;
    private JLabel labelA;
    private JButton helpButton1;
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
    private JTextField textField22;
    private JTextField textField23;
    private JLabel stop;
    private JLabel finale;
    private JButton selectButton;
    boolean flag;

    public BeamDialogIwa() {
        super("Двутавр сварной с одной осью симметрии");
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/ant.gif")));
        /**
         * Настройка размеров JButton
         */
        Dimension dimension = new Dimension(120, 30);
        helpButton.setPreferredSize(dimension);
        resetButton.setPreferredSize(dimension);
        resetButton1.setPreferredSize(dimension);
        closeButton.setPreferredSize(dimension);
        acceptButton.setPreferredSize(dimension);
        calculateButton.setPreferredSize(dimension);
        changeButton.setPreferredSize(dimension);
        stressButton.setPreferredSize(dimension);
        bimomentButton.setPreferredSize(dimension);
        diagramButton.setPreferredSize(dimension);
        solutionButton.setPreferredSize(dimension);
        closeButton1.setPreferredSize(dimension);
        sigmaB1.setForeground(Color.MAGENTA);
        sigmaB4.setForeground(Color.MAGENTA);
        sigmaM1.setForeground(Color.MAGENTA);
        sigmaM2.setForeground(Color.MAGENTA);
        sigmaS1.setForeground(Color.MAGENTA);
        sigmaS4.setForeground(Color.MAGENTA);
        acceptButton.setVisible(false);
        changeButton.setVisible(false);
        diagramButton.setVisible(false);
        bimomentButton.setVisible(false);
        stressButton.setVisible(false);
        tabbedPane1.setVisible(true);
        setSize(830, 650);
        setLocationRelativeTo(null);
        setVisible(true);

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
            textField12.setText("");
            textField13.setText("");
            textField14.setText("");
            textField15.setText("");
        });

        defaultShelf400x16Shelf250x10Wall666x8CheckBox.addActionListener(e -> {
            textField1.setText("66.6");
            textField2.setText("40");
            textField3.setText("25");
            textField4.setText("1.6");
            textField5.setText("1.0");
            textField6.setText("0.8");
        });

        closeButton.addActionListener(e -> dispose());
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

        resetButton1.addActionListener(e -> {
            textField16.setText("");
            textField17.setText("");
            textField18.setText("");
            textField19.setText("");
            textField20.setText("");
            textField21.setText("");
            textField22.setText("");
            textField23.setText("");
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

        selectCheckBox2.addActionListener(e -> {
            flag = true;
            textField19.setVisible(false);
            labelA.setVisible(false);
        });

        selectCheckBox.addActionListener(e -> {
            flag = false;
            textField19.setVisible(true);
            labelA.setVisible(true);
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

        helpButton.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/dv_asim.png"))),
                    SwingConstants.CENTER);
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            image.setBorder(softBevelLowered);
            dialog.getContentPane().add(image);
            dialog.setSize(400, 200);
            /**
             * Расположение JDialog по центру
             */
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        });

        helpButton1.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/norm_dva.png"))),
                    SwingConstants.CENTER);
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            image.setBorder(softBevelLowered);
            dialog.getContentPane().add(image);
            dialog.setSize(400, 200);
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

    /**
     * Нормальные напряжения.
     */
    public void stressForceLocal() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        sigmaB1.setText(String.valueOf(format.format(normalStressBimomentAsimPoint1(bmax, w1, iw))));
        sigmaB2.setText("+" + format.format(normalStressBimomentAsimPoint2(bmax, w1, iw)));
        sigmaB3.setText("+" + format.format(normalStressBimomentAsimPoint3(bmax, w2, iw)));
        sigmaB4.setText(String.valueOf(format.format(normalStressBimomentAsimPoint4(bmax, w2, iw))));
        sigmaM1.setText(String.valueOf(format.format(normalStressMomentAsimPoint1And2(mmax, wx1))));
        sigmaM2.setText(String.valueOf(format.format(normalStressMomentAsimPoint1And2(mmax, wx1))));
        sigmaM3.setText("+" + format.format(normalStressMomentAsimPoint3And4(mmax, wx2)));
        sigmaM4.setText("+" + format.format(normalStressMomentAsimPoint3And4(mmax, wx2)));
        sigmaS1.setText(String.valueOf(format.format(normalStressBimomentAsimPoint1(bmax, w1, iw)
                + normalStressMomentAsimPoint1And2(mmax, wx1))));
        double s2 = normalStressBimomentAsimPoint2(bmax, w1, iw)
                + normalStressMomentAsimPoint1And2(mmax, wx1);
        if (s2 > 0) {
            sigmaS2.setForeground(Color.BLUE);
            sigmaS2.setText("+" + format.format(s2));
        } else {
            sigmaS2.setForeground(Color.MAGENTA);
            sigmaS2.setText(format.format(s2));
        }
        sigmaS3.setText("+" + format.format(normalStressBimomentAsimPoint3(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2)));
        sigmaS4.setText(String.valueOf(format.format(normalStressBimomentAsimPoint4(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2))));
    }

    /**
     * Расчет схемы "сосредоточенная сила
     * в середине пролета".
     */
    public void actionSolutionScheme() {
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1(p, ex, k, l);
        mmax = calcMomentMax1(p, l);
        textField20.setText(String.valueOf(calcBimomentMax1(p, ex, k, l)));
        textField21.setText(String.valueOf(calcMomentMax1(p, l)));
        double r = (normalStressBimomentAsimPoint3(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2)) / ry;
        textField22.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField22.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField22.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        stressForceLocal();
        textField23.setText(String.valueOf(roundOne((normalStressBimomentAsimPoint3(bmax, w2, iw) * 100)
                / (normalStressBimomentAsimPoint3(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2)))));
    }

    /**
     * Расчет схемы "сосредоточенная сила
     * смещена к левой опоре".
     */
    public void actionSolutionSchemeDisplaced() {
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1Displaced(p, ex, k, l, a);
        mmax = calcMomentMax1Displaced(p, l, a);
        textField20.setText(String.valueOf(calcBimomentMax1Displaced(p, ex, k, l, a)));
        textField21.setText(String.valueOf(calcMomentMax1Displaced(p, l, a)));
        double r = (normalStressBimomentAsimPoint3(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2)) / ry;
        textField22.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField22.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField22.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        stressForceLocal();
        textField23.setText(String.valueOf(roundOne((normalStressBimomentAsimPoint3(bmax, w2, iw) * 100)
                / (normalStressBimomentAsimPoint3(bmax, w2, iw)
                + normalStressMomentAsimPoint3And4(mmax, wx2)))));
    }

    public void actionCalcScheme() {
        p = Double.parseDouble(textField16.getText());
        ex = Double.parseDouble(textField17.getText());
        l = Double.parseDouble(textField18.getText());
    }

    public void actionCalcSchemeDisplaced() {
        p = Double.parseDouble(textField16.getText());
        ex = Double.parseDouble(textField17.getText());
        l = Double.parseDouble(textField18.getText());
        a = Double.parseDouble(textField19.getText());
    }

    /**
     * Присвоение значений из textField переменным класса
     */
    public void actionCalc() {
        h = Double.parseDouble(textField1.getText());
        b = Double.parseDouble(textField2.getText());
        d = Double.parseDouble(textField3.getText());
        tf1 = Double.parseDouble(textField4.getText());
        tf2 = Double.parseDouble(textField5.getText());
        tw = Double.parseDouble(textField6.getText());
    }

    /**
     * Присвоение вычисленных значений в textField
     */
    public void actionSolution() {
        h = Double.parseDouble(textField1.getText());
        b = Double.parseDouble(textField2.getText());
        d = Double.parseDouble(textField3.getText());
        tf1 = Double.parseDouble(textField4.getText());
        tf2 = Double.parseDouble(textField5.getText());
        tw = Double.parseDouble(textField6.getText());
        textField7.setText(String.valueOf(calcBendCenter(b, h, tf1, tf2, d)));
        textField8.setText(String.valueOf(calcSectorialCoordinateW1(b, ay)));
        textField9.setText(String.valueOf(calcSectorialCoordinateW2(d, ay, h, tf1, tf2)));
        textField10.setText(String.valueOf(calcSectorialMomentInertia(tf1, tf2, b, d, h)));
        textField11.setText(String.valueOf(calcMomentInertiaTorsion(b, tf1, d, tf2, h, tw)));
        textField12.setText(String.valueOf(calcBendingTorsionalCharacteristic(it, iw)));
        textField13.setText(String.valueOf(calcCenterOfGravity(tf1, tf2, tw, b, d, h)));
        textField14.setText(String.valueOf(calcMomentOfResistance1(tf1, tf2, tw, h, b, d, aycg)));
        textField15.setText(String.valueOf(calcMomentOfResistance2(tf1, tf2, tw, h, b, d, aycg)));
    }

    /**
     * Вычисление координаты центра изгиба ay [см]
     * по оси OY от середины верхней полки
     */
    double calcBendCenter(double b, double h, double tf1, double tf2, double d) {
        ay = roundTwo((tf2 * Math.pow(d, 3) * (h - 0.5 * (tf1 + tf2)))
                / (tf1 * Math.pow(b, 3) + tf2 * Math.pow(d, 3)));
        return ay;
    }

    /**
     * Вычисление секториальной координаты W1 [см2]
     */
    @Override
    public double calcSectorialCoordinateW1(double b, double ay) {
        w1 = roundTwo(0.5 * b * ay);
        return w1;
    }

    /**
     * Вычисление секториальной координаты W2 [см2]
     */
    public double calcSectorialCoordinateW2(double d, double ay, double h, double tf1, double tf2) {
        w2 = roundTwo(0.5 * (((h - 0.5 * (tf1 + tf2)) - ay) * d));
        return w2;
    }

    /**
     * Вычисление секториального момента инерции Iw [см6]
     */
    double calcSectorialMomentInertia(double tf1, double tf2, double b, double d, double h) {
        iw = roundTwo((tf1 * Math.pow(b, 3) * tf2 * Math.pow(d, 3)
                * Math.pow((h - 0.5 * (tf1 + tf2)), 2))
                / (12 * (tf1 * Math.pow(b, 3) + tf2 * Math.pow(d, 3))));
        return iw;
    }

    /**
     * Вычисление момента инерции при кручении It [см4]
     */
    double calcMomentInertiaTorsion(double b, double tf1, double d, double tf2, double h, double tw) {
        it = roundTwo((1.25 / 3) * (b * Math.pow(tf1, 3) + d * Math.pow(tf2, 3)
                + ((h - tf1 - tf2) * Math.pow(tw, 3))));
        return it;
    }

    double calcCenterOfGravity(double tf1, double tf2, double tw, double b, double d, double h) {
        double h2 = h - 0.5 * (tf1 + tf2);
        double h3 = 0.5 * (h - tf1 - tf2) + 0.5 * tf1;
        double s1 = tf1 * b;
        double s2 = tf2 * d;
        double s3 = tw * (h - tf1 - tf2);
        aycg = roundTwo((h2 * s2 + h3 * s3) / (s1 + s2 + s3));
        return aycg;
    }

    double calcMomentOfResistance1(double tf1, double tf2, double tw, double h, double b, double d, double aycg) {
        double s1 = tf1 * b;
        double s2 = tf2 * d;
        double s3 = tw * (h - tf1 - tf2);
        double l1 = 0.5 * (h - tf1 - tf2) - aycg + 0.5 * tf1;
        double ix1 = ((b * Math.pow(tf1, 3)) / 12)
                + s1 * Math.pow(aycg, 2);
        double ix2 = ((d * Math.pow(tf2, 3)) / 12)
                + s2 * Math.pow(h - 0.5 * (tf1 + tf2) - aycg, 2);
        double ix3 = ((tw * Math.pow(h - tf1 - tf2, 3)) / 12)
                + s3 * Math.pow(l1, 2);
        ix = ix1 + ix2 + ix3;
        wx1 = roundTwo(ix / (aycg + 0.5 * tf1));
        return wx1;
    }

    double calcMomentOfResistance2(double tf1, double tf2, double tw, double h, double b, double d, double aycg) {
        double s1 = tf1 * b;
        double s2 = tf2 * d;
        double s3 = tw * (h - tf1 - tf2);
        double l2 = 0.5 * (h - tf1 - tf2) - aycg + 0.5 * tf1;
        double ix1 = ((b * Math.pow(tf1, 3)) / 12)
                + s1 * Math.pow(aycg, 2);
        double ix2 = ((d * Math.pow(tf2, 3)) / 12)
                + s2 * Math.pow(h - 0.5 * (tf1 + tf2) - aycg, 2);
        double ix3 = ((tw * Math.pow(h - tf1 - tf2, 3)) / 12)
                + s3 * Math.pow(l2, 2);
        ix = ix1 + ix2 + ix3;
        wx2 = roundTwo(ix / (h - (aycg + 0.5 * tf1)));
        return wx2;
    }

    public void seeTable() throws Exception {
        String dsn = "jdbc:postgresql://localhost:5432/test";
        String uid = "postgres";
        String pwd = "admin";
        Connection conn = DriverManager.getConnection(dsn, uid, pwd);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profiles.dvwa"
                + "\n" + "ORDER BY number_dvwa ASC");
        final DatabaseTableModel dbm =
                new DatabaseTableModel(true);
        dbm.setDataSource(rs);
        SwingUtilities.invokeLater(
                () -> {
                    JTable table = new JTable(dbm);
                    JFrame frame = new JFrame("Сварной двутавр с одной осью симметрии");
                    frame.setIconImage(Toolkit.getDefaultToolkit().
                            getImage(getClass().getResource("/ant.gif")));
                    frame.setSize(600, 300);
                    frame.add(new JScrollPane(table));
                    table.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            int i = table.getSelectedRow();
                            TableModel model = table.getModel();
                            textField1.setText(model.getValueAt(i, 1).toString());
                            textField2.setText(model.getValueAt(i, 2).toString());
                            textField3.setText(model.getValueAt(i, 3).toString());
                            textField4.setText(model.getValueAt(i, 4).toString());
                            textField5.setText(model.getValueAt(i, 5).toString());
                            textField6.setText(model.getValueAt(i, 6).toString());
                        }
                    });
                    frame.setVisible(true);
                });
    }
}
