package ru.calculator;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.NumberFormat;

public class BeamDialogUp extends JFrame implements Bimoment, SectionU {
    /**
     * Расчет прокатного швеллера с параллельными полками
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
    private JTabbedPane tabbedPane;
    private JButton helpButton;
    private JButton resetButton;
    private JButton closeButton;
    private JButton changeButton;
    private JButton calculateButton;
    private JButton acceptButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JCheckBox checkBox1;
    private JCheckBox selectCheckBox;
    private JCheckBox selectCheckBox2;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JButton solutionButton;
    private JButton closeButton1;
    private JButton bimomentButton;
    private JButton stressButton;
    private JTextField textField15;
    private JTextField textField16;
    private JButton fixButton;
    private JCheckBox c245CheckBox;
    private JCheckBox c255CheckBox;
    private JCheckBox c345CheckBox;
    private JLabel label2450;
    private JLabel label2550;
    private JLabel label3450;
    private JLabel labelA;
    private JButton resetButton1;
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
    private JLabel finale;
    private JLabel stop;
    private JTextField textField19;
    private JButton helpButton1;
    private JLabel cb0;
    private JLabel cb2;
    private JButton selectButton;
    private JButton deleteButton;
    boolean flag;

    public BeamDialogUp() {
        super("Швеллер");
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
        closeButton1.setPreferredSize(dimension);
        solutionButton.setPreferredSize(dimension);
        fixButton.setPreferredSize(dimension);
        sigmaB1.setForeground(Color.MAGENTA);
        sigmaB4.setForeground(Color.MAGENTA);
        sigmaM1.setForeground(Color.MAGENTA);
        sigmaM2.setForeground(Color.MAGENTA);
        sigmaS1.setForeground(Color.MAGENTA);
        sigmaS4.setForeground(Color.MAGENTA);
        changeButton.setVisible(false);
        acceptButton.setVisible(false);
        stressButton.setVisible(false);
        bimomentButton.setVisible(false);
        fixButton.setVisible(false);
        tabbedPane.setVisible(true);
        cb0.setVisible(true);
        cb2.setVisible(true);
        setSize(830, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        stop.setVisible(false);

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
            textField17.setText("");
        });

        closeButton.addActionListener(e -> dispose());
        calculateButton.addActionListener(e -> actionSolution());
        checkBox1.addActionListener(e -> {
            textField1.setText("27");
            textField2.setText("9.5");
            textField3.setText("1.05");
            textField4.setText("0.6");
            textField17.setText("310");
        });

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

        selectCheckBox2.addActionListener(e -> {
            flag = true;
            textField14.setVisible(false);
            labelA.setVisible(false);
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

        selectCheckBox.addActionListener(e -> {
            flag = false;
            textField14.setVisible(true);
            labelA.setVisible(true);
        });

        resetButton1.addActionListener(e -> {
            textField11.setText("");
            textField12.setText("");
            textField13.setText("");
            textField14.setText("");
            textField15.setText("");
            textField16.setText("");
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

        helpButton.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/u_p.png"))),
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

        helpButton1.addActionListener(e -> {
            JDialog dialog = createDialog();
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/normal_up.png"))),
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

        closeButton1.addActionListener(e -> dispose());

        selectButton.addActionListener(e -> {
            try {
                seeTable();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                deleteRow();
            } catch (ClassNotFoundException exc) {
                throw new RuntimeException(exc);
            } catch (SQLException ex) {
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
        p = Double.parseDouble(textField11.getText());
        ex = Double.parseDouble(textField12.getText());
        l = Double.parseDouble(textField13.getText());
    }

    public void actionCalcSchemeDisplaced() {
        p = Double.parseDouble(textField11.getText());
        ex = Double.parseDouble(textField12.getText());
        l = Double.parseDouble(textField13.getText());
        a = Double.parseDouble(textField14.getText());
    }

    /**
     * Присвоение значений из textField переменным класса
     * и передача вычисленных значений в textField
     */
    public void actionSolution() {
        h = Double.parseDouble(textField1.getText());
        b = Double.parseDouble(textField2.getText());
        tf = Double.parseDouble(textField3.getText());
        tw = Double.parseDouble(textField4.getText());
        wx = Double.parseDouble(textField17.getText());
        textField8.setText(String.valueOf(calcBendCenter(b, tw, h, tf)));
        textField6.setText(String.valueOf(calcSectorialCoordinateW1(ax, h, tf)));
        textField5.setText(String.valueOf(calcSectorialCoordinateW2(b, tw, ax, h, tf)));
        textField9.setText(String.valueOf(calcSectorialMomentInertia(tw, h, tf, w1, b, w2)));
        textField7.setText(String.valueOf(calcMomentInertiaTorsion(b, tf, h, tw)));
        textField10.setText(String.valueOf(calcBendingTorsionalCharacteristic(it, iw)));
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
        textField15.setText(String.valueOf(calcBimomentMax1(p, ex, k, l)));
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
     * и присвоение вычисленных значений в JLabel
     */
    public void actionSolutionSchemeDisplaced() {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(0);
        k = calcBendingTorsionalCharacteristic(it, iw);
        bmax = calcBimomentMax1Displaced(p, ex, k, l, a);
        mmax = calcMomentMax1Displaced(p, l, a);
        textField15.setText(String.valueOf(calcBimomentMax1Displaced(p, ex, k, l, a)));
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
    double calcBendCenter(double b, double tw, double h, double tf) {
        ax = roundTwo(3 * Math.pow((b - 0.5 * tw), 2) / ((h - tf)
                + 6 * (b - 0.5 * tw)));
        return ax;
    }

    /**
     * Вычисление секториальной координаты W1 [см2]
     */
    double calcSectorialCoordinateW1(double ax, double h, double tf) {
        w1 = roundTwo(ax * 0.5 * (h - tf));
        return w1;
    }

    /**
     * Вычисление секториальной координаты W2 [см2]
     */
    @Override
    public double calcSectorialCoordinateW2(double b, double tw, double ax, double h, double tf) {
        w2 = roundTwo(((b - 0.5 * tw) - ax) * 0.5 * (h - tf));
        return w2;
    }

    /**
     * Вычисление секториального момента инерции Iw [см6]
     */
    @Override
    public double calcSectorialMomentInertia(double tw, double h, double tf, double w1, double b, double w2) {
        iw = roundTwo((tw * (h - tf) * Math.pow(w1, 2)) / 3
                + (2 * tf * (b - 0.5 * tw)) / 3 * (Math.pow(w1, 2)
                + Math.pow(w2, 2) - w1 * w2));
        return iw;
    }

    /**
     * Вычисление момента инерции при кручении It [см4]
     */
    @Override
    public double calcMomentInertiaTorsion(double b, double tf, double h, double tw) {
        it = roundTwo((1.12 / 3) * (2 * (b * Math.pow(tf, 3))
                + (h - 2 * tf) * Math.pow(tw, 3)));
        return it;
    }

    public void seeTable() throws Exception {
        String dsn = "jdbc:postgresql://localhost:5432/test";
        String uid = "postgres";
        String pwd = "admin";
        Connection conn = DriverManager.getConnection(dsn, uid, pwd);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM profiles.shvp");
        final DatabaseTableModel dbm =
                new DatabaseTableModel(true);
        dbm.setDataSource(rs);
        SwingUtilities.invokeLater(
                () -> {
                    JTable table = new JTable(dbm);
                    JFrame frame = new JFrame("Швеллер с параллельными гранями полок");
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
                            textField4.setText(model.getValueAt(i, 3).toString());
                            textField3.setText(model.getValueAt(i, 4).toString());
                            textField17.setText(model.getValueAt(i, 5).toString());
                        }
                    });
                    frame.setVisible(true);
                });
    }

    public void deleteRow() throws ClassNotFoundException, SQLException {
        String dsn = "jdbc:postgresql://localhost:5432/test";
        String uid = "postgres";
        String pwd = "admin";
        Double dbl = Double.valueOf(textField2.getText());
        Connection conn = null;
        Statement statement = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(dsn, uid, pwd);
        try {
            /**
             * удаление конкретной строки с указанием значения ячейки из textField
             */
            String query = "DELETE FROM profiles.shvp WHERE b='" + dbl + "'";
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
    }
}
