package ru.calculator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

import static javax.swing.SwingConstants.CENTER;

public class Calculator extends JFrame {
    private JPanel panel;
    private JTabbedPane jTabbedPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    /**
     * Изображение
     */
    private final Image anim =
            new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/anim.gif"))).getImage();

    public Calculator() {
        super("БиМКа 1.0");
        setContentPane(panel);
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/ant.gif")));
        final ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().
                getImage(getClass().getResource("/icon1.png")));
        /**
         * Операция при закрытии окна
         */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /**
         * Настройка размеров JButton
         */
        Dimension dimension = new Dimension(100, 20);
        button7.setPreferredSize(dimension);
        button6.setPreferredSize(dimension);
        /**
         * Всплывающие подсказки
         */
        button9.setToolTipText("Назначение");
        button8.setToolTipText("Об авторе");
        button6.setToolTipText("<html><h4>Кнопка авторизации<ul>"
                + "Для входа:<li>Введите логин"
                + "<li>Введите пароль"
                + "<li>Нажмите кнопку");
        button5.setToolTipText("Швеллер с параллельными гранями полок ГОСТ 8240-97");
        button4.setToolTipText("Швеллер с уклоном внутренних граней полок ГОСТ 8240-97");
        button3.setToolTipText("Двутавр с параллельными гранями полок ГОСТ Р 57837-2017");
        button2.setToolTipText("Двутавр сварной равнополочный ТУ 0908-135-02494680-2003");
        button1.setToolTipText("Двутавр сварной не равнополочный");
        /**
         * Настройка подсказок
         */
        ToolTipManager ttm = ToolTipManager.sharedInstance();
        ttm.setLightWeightPopupEnabled(false);
        ttm.setInitialDelay(1000);
        ttm.setDismissDelay(1500);
        ttm.setReshowDelay(1000);
        /**
         * Минимальные размеры
         */
        setMinimumSize(new Dimension(500, 400));
        /**
         * Расположение диалогового окна JFrame в центре
         */
        setLocationRelativeTo(null);
        jTabbedPane.setVisible(true);
        /**
         * Добавляем компонент с анимацией в слой PALETTE
         */
        Animation an = new Animation();
        an.setBounds(40, 20, anim.getWidth(this),
                anim.getHeight(this));
        getLayeredPane().add(an, JLayeredPane.PALETTE_LAYER);
        /**
         * Выводит окно на экран
         */
        setVisible(true);

        button8.addActionListener(e -> {
            JDialog dialog = createDialog("Автор"
            );

            JLabel label = new JLabel("Муравьев А.А. "
                    + "Группа 124/16 "
                    + "ИТМО 2023");
            label.setFont(new Font("Courier New", Font.BOLD, 14));
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            /**
             * Расположение JLabel по центру
             */
            label.setHorizontalAlignment(CENTER);
            label.setBorder(softBevelLowered);
            dialog.add(label);
            dialog.setSize(360, 80);
            /**
             * Расположение JDialog по центру
             */
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        });

        button9.addActionListener(e -> {
            JDialog dialog = createDialog("Расчет стальных балок на кручение");
            JLabel image = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().
                    getImage(getClass().getResource("/bimoment.PNG"))), SwingConstants.CENTER);
            Border softBevelLowered = BorderFactory.
                    createSoftBevelBorder(BevelBorder.LOWERED);
            image.setBorder(softBevelLowered);
            dialog.getContentPane().add(image);
            dialog.setSize(450, 250);
            /**
             * Расположение JDialog по центру
             */
            dialog.setLocationRelativeTo(dialog);
            dialog.setVisible(true);
        });

        button5.addActionListener(e -> SwingUtilities.invokeLater(BeamDialogUp::new));
        button4.addActionListener(e -> SwingUtilities.invokeLater(BeamDialogUu::new));
        button3.addActionListener(e -> SwingUtilities.invokeLater(BeamDialogI::new));
        button2.addActionListener(e -> SwingUtilities.invokeLater(BeamDialogIws::new));
        button1.addActionListener(e -> SwingUtilities.invokeLater(BeamDialogIwa::new));

        button6.addActionListener(e -> {
            DbFunctions db = new DbFunctions();
            String user = textField1.getText();
            String password = passwordField1.getText();
            db.connectToDb("test", user, password);
        });

        button7.addActionListener(e -> JOptionPane.showMessageDialog(
                Calculator.this,
                "Соединение с БД разорвано", "Подключения нет",
                JOptionPane.INFORMATION_MESSAGE, icon));
        button7.addActionListener(e -> {
            textField1.setText("");
            passwordField1.setText("");
        });
    }

    /**
     * Компонент, рисующий анимированное изображение
     */
    class Animation extends JComponent {
        public Animation() {
            setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            /**
             * Полупрозрачность
             */
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.2f));
            /**
             * Рисуем изображение
             */
            g2.drawImage(anim, 0, 0, this);
        }

        /**
         * Мы никогда не получаем событий от мыши
         */
        @Override
        public boolean contains(int x, int y) {
            return false;
        }
    }

    /**
     * Создает диалоговое окно
     */
    private JDialog createDialog(String title) {
        JDialog dialog = new JDialog(this, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        return dialog;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
