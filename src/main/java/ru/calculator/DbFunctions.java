package ru.calculator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;

public class DbFunctions extends JFrame {
    private final ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().
            getImage(getClass().getResource("/ok.png")));
    private final ImageIcon icon1 = new ImageIcon(Toolkit.getDefaultToolkit().
            getImage(getClass().getResource("/icon1.png")));

    public Connection connectToDb(String dbname, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
            if (conn != null) {
                JOptionPane.showMessageDialog(
                        DbFunctions.this,
                        "Соединение с БД установлено", "Подключение",
                        JOptionPane.INFORMATION_MESSAGE, icon);
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(
                    DbFunctions.this, new String[] {
                            "Подключиться не удалось.",
                            "Проверьте логин и пароль." }, "Подключения нет",
                    JOptionPane.INFORMATION_MESSAGE, icon1);
        }
        return conn;
    }
}
