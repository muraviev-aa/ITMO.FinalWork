package ru.calculator;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;

public class BeamDialogIws extends JFrame implements Bimoment, SectionI {
    /**
     * Расчет сварного равнополочного двутавра
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
     * Смещение силы от левой опоры [см]
     */
    private double a;
    /**
     * Пролет балки [см]
     */
    private double l;
    /**
     * Главная секториальная координата w1 [см2]
     */
    private double w1;
    /**
     * Секториальный момент инерции [см6]
     */
    private double iw;
    /**
     * Момент инерции при чистом кручении [см4]
     */
    private double it;
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
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton closeButton;
    private JButton calculateButton;
    private JButton acceptButton;
    private JButton changeButton;
    private JButton resetButton;
    private JButton helpButton;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JCheckBox defaultShelf200x11Wall291x8CheckBox;
    private JLabel label2;
    private JLabel label3;
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
    private JButton closeButton1;
    private JButton resetButton1;
    private JButton diagramButton;
    private JButton bimomentButton;
    private JButton stressButton;
    private JTextField textField16;
    private JTextField textField17;
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
    private JTextField textField18;
    private JTextField textField19;
    private JLabel stop;
    private JLabel finale;
    private JButton selectButton;
    boolean flag;

    public BeamDialogIws() {
        super("Двутавр равнополочный симметричный");
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
        textField5.setVisible(false);
        textField6.setVisible(false);
        sigmaB1.setForeground(Color.MAGENTA);
        sigmaB4.setForeground(Color.MAGENTA);
        sigmaM1.setForeground(Color.MAGENTA);
        sigmaM2.setForeground(Color.MAGENTA);
        sigmaS1.setForeground(Color.MAGENTA);
        sigmaS4.setForeground(Color.MAGENTA);
        label2.setVisible(false);
        label3.setVisible(false);
        acceptButton.setVisible(false);
        changeButton.setVisible(false);
        helpButton.setVisible(false);
        diagramButton.setVisible(false);
        bimomentButton.setVisible(false);
        stressButton.setVisible(false);
        tabbedPane1.setVisible(true);
        setSize(830, 600);
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
        });

        defaultShelf200x11Wall291x8CheckBox.addActionListener(e -> {
            textField1.setText("29.1");
            textField2.setText("20");
            textField3.setText("1.1");
            textField4.setText("0.8");
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
            textField12.setText("");
            textField13.setText("");
            textField14.setText("");
            textField15.setText("");
            textField16.setText("");
            textField17.setText("");
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

        selectButton.addActionListener(e -> {
            try {
                seeTable();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        helpButton1.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon("norm_dvs.png"), SwingConstants.CENTER);
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            image.setBorder(softBevelLowered);
            dialog.getContentPane().add(image);
            dialog.setSize(300, 150);
            /**
             * Расположение JDialog по центру
             */
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        });
    }

    private JDialog createDialog() {
        JDialog dialog = new JDialog(this, "Справка", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        return dialog;
    }

    public void actionSolutionScheme() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1(p, ex, k, l);
        mmax = calcMomentMax1(p, l);
        textField16.setText(String.valueOf(calcBimomentMax1(p, ex, k, l)));
        textField17.setText(String.valueOf(calcMomentMax1(p, l)));
        double r = (normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx)) / ry;
        textField18.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField18.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField18.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        sigmaB1.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw))));
        sigmaB2.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)));
        sigmaB3.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)));
        sigmaB4.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw))));
        sigmaM1.setText(String.valueOf(format.format(normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaM2.setText(String.valueOf(format.format(normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaM3.setText("+" + format.format(normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaM4.setText("+" + format.format(normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaS1.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaS2.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx)));
        sigmaS3.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaS4.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx))));
        textField19.setText(String.valueOf(roundOne((normalStressBimomentSimPoint1And4(bmax, w1, iw) * 100)
                / (normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx)))));
    }

    public void actionSolutionSchemeDisplaced() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1Displaced(p, ex, k, l, a);
        mmax = calcMomentMax1Displaced(p, l, a);
        textField16.setText(String.valueOf(calcBimomentMax1Displaced(p, ex, k, l, a)));
        textField17.setText(String.valueOf(calcMomentMax1Displaced(p, l, a)));
        double r = (normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx)) / ry;
        textField18.setText(String.valueOf(roundTwo(r)));
        stop.setVisible(r > 1);
        if (r > 1) {
            textField18.setForeground(Color.RED);
            finale.setForeground(Color.RED);
        } else {
            textField18.setForeground(Color.BLUE);
            finale.setForeground(Color.BLUE);
        }
        sigmaB1.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw))));
        sigmaB2.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)));
        sigmaB3.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)));
        sigmaB4.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw))));
        sigmaM1.setText(String.valueOf(format.format(normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaM2.setText(String.valueOf(format.format(normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaM3.setText("+" + format.format(normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaM4.setText("+" + format.format(normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaS1.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx))));
        sigmaS2.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx)));
        sigmaS3.setText("+" + format.format(normalStressBimomentSimPoint2And3(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx)));
        sigmaS4.setText(String.valueOf(format.format(normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint3And4(mmax, wx))));
        textField19.setText(String.valueOf(roundOne((normalStressBimomentSimPoint1And4(bmax, w1, iw) * 100)
                / (normalStressBimomentSimPoint1And4(bmax, w1, iw)
                + normalStressMomentSimPoint1And2(mmax, wx)))));
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
     * Присвоение значений из textField переменным класса
     * и присвоение вычисленных значений в textField
     */
    public void actionSolution() {
        h = Double.parseDouble(textField1.getText());
        b = Double.parseDouble(textField2.getText());
        tf = Double.parseDouble(textField3.getText());
        tw = Double.parseDouble(textField4.getText());
        textField7.setText(String.valueOf(calcSectorialCoordinateW1(b, h)));
        textField8.setText(String.valueOf(calcMomentOfResistance(h, b, tw, tf)));
        textField9.setText(String.valueOf(calcSectorialMomentInertia(tf, b, h)));
        textField10.setText(String.valueOf(calcMomentInertiaTorsion(b, tf, h, tw)));
        textField11.setText(String.valueOf(calcBendingTorsionalCharacteristic(it, iw)));
    }

    /**
     * Вычисление секториальной координаты W1 [см2]
     */
    @Override
    public double calcSectorialCoordinateW1(double b, double h) {
        w1 = roundTwo(0.25 * b * h);
        return w1;
    }

    /**
     * Вычисление секториального момента инерции Iw [см6]
     */
    double calcSectorialMomentInertia(double tf, double b, double h) {
        iw = roundTwo((tf * Math.pow(b, 3) * Math.pow((h - tf), 2)) / 24);
        return iw;
    }

    /**
     * Вычисление момента инерции при кручении It [см4]
     */
    double calcMomentInertiaTorsion(double b, double tf, double h, double tw) {
        it = roundTwo((1.29 / 3) * (2 * b * Math.pow(tf, 3) + (h - 2 * tf) * Math.pow(tw, 3)));
        return it;
    }

    /**
     * Вычисление момента сопротивления сечения Wx [см3]
     */
    double calcMomentOfResistance(double h, double b, double tw, double tf) {
        double ix = (tw * Math.pow((h - 2 * tf), 3)) / 12 + 2 * b * tf * Math.pow((0.5 * (h - tf)), 2);
        wx = roundTwo(ix / (0.5 * h));
        return wx;
    }

    public void seeTable() throws Exception {
        String dsn = "jdbc:postgresql://localhost:5432/test";
        String uid = "postgres";
        String pwd = "admin";
        Connection conn = DriverManager.getConnection(dsn, uid, pwd);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profiles.dvws");
        final DatabaseTableModel dbm =
                new DatabaseTableModel(true);
        dbm.setDataSource(rs);
        SwingUtilities.invokeLater(
                () -> {
                    JTable table = new JTable(dbm);
                    JFrame frame = new JFrame("Сварной симметричный двутавр");
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
                            textField1.setText(model.getValueAt(i, 1).toString());
                            textField2.setText(model.getValueAt(i, 2).toString());
                            textField3.setText(model.getValueAt(i, 3).toString());
                            textField4.setText(model.getValueAt(i, 4).toString());
                        }
                    });
                    frame.setVisible(true);
                });
    }
}
