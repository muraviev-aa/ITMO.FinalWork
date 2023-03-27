package ru.calculator;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class DatabaseTableModel extends AbstractTableModel {
    /**
     * Здесь мы будем хранить названия столбцов
     */
    private final ArrayList<String> columnNames = new ArrayList<>();
    /**
     * Список типов столбцов
     */
    private final ArrayList<Class> columnTypes = new ArrayList<>();
    /**
     * Хранилище дл¤ полученных данных из базы данных
     */
    private final ArrayList<ArrayList<Object>> data
            = new ArrayList<>();
    /**
     * Признак редактировани¤ таблицы
     */
    private final boolean editable;

    /**
     * Конструктор позволяет задать возможность редактирования
     */
    public DatabaseTableModel(boolean editable) {
        this.editable = editable;
    }

    /**
     * Количество строк
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Количество столбцов
     */
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    /**
     * Тип данных столбца
     */
    @Override
    public Class getColumnClass(int column) {
        return columnTypes.get(column);
    }

    /**
     * Название столбца
     */
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    /**
     * Данные в ¤чейке
     */
    public Object getValueAt(int row, int column) {
        return (data.get(row)).get(column);
    }

    /**
     * Возможность редактирования
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }

    /**
     * Замена значени¤ ячейки
     */
    @Override
    public void setValueAt(
            Object value, int row, int column) {
        (data.get(row)).set(column, value);
    }

    /**
     * Получение данных из объекта ResultSet
     */
    public void setDataSource(ResultSet rs) throws Exception {
        /**
         * Удаляем прежние данные
         */
        data.clear(); columnNames.clear(); columnTypes.clear();
        /**
         * Получаем вспомогательную информацию о столбцах
         */
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            /**
             * Название столбца
             */
            columnNames.add(rsmd.getColumnName(i + 1));
            /**
             * Тип столбца
             */
            Class type =
                    Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }
        /**
         * Получаем данные
         */
        while (rs.next()) {
            /**
             * Здесь будем хранить ¤чейки одной строки
             */
            ArrayList<Object> row = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                row.add(rs.getObject(i + 1));
            }
            data.add(row);
        }
        /**
         * Сообщаем об изменениях в структуре данных
         */
        fireTableStructureChanged();
    }
}
