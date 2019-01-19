import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;

//класс просмотра имеющихся книг и двд
public class ViewItems extends JFrame {
    private Object[][] array;
    private Object[] columnsHeader;

    public ViewItems( UniLibraryManager uniLibraryManager) {
        JFrame frame = new JFrame("Items list");
        array = new String[uniLibraryManager.listItem.size()][9];
        columnsHeader = new String[]{"Type item", "ISBN", "Title", "Sector", "Publication date", "Borrow Time", "Current Reader", "Next Reader", "Reservation Date"};
        for (int i = 0; i < uniLibraryManager.listItem.size(); i++) {
            array[i][0] = uniLibraryManager.listItem.get(i).getClass().getName();
            array[i][1] = uniLibraryManager.listItem.get(i).getISBN();
            array[i][2] = uniLibraryManager.listItem.get(i).getTitle();
            array[i][3] = uniLibraryManager.listItem.get(i).getSector();
            if (uniLibraryManager.listItem.get(i).getPublicationDate() != null) {
                array[i][4] = uniLibraryManager.listItem.get(i).getPublicationDate().toString();
            } else
                array[i][4] = "";
            if (uniLibraryManager.listItem.get(i).getBorrowTime() != null) {
                array[i][5] = uniLibraryManager.listItem.get(i).getBorrowTime().toString();
            } else
                array[i][5] = "";
            if (uniLibraryManager.listItem.get(i).getCurrentReader() != null) {
                array[i][6] = uniLibraryManager.listItem.get(i).getCurrentReader().getName();
            } else
                array[i][6] = "";
            if (uniLibraryManager.listItem.get(i).getNextReader() != null) {
                array[i][7] = uniLibraryManager.listItem.get(i).getNextReader().getName();
            } else
                array[i][7] = "";
            if (uniLibraryManager.listItem.get(i).getReservationTime() != null) {
                array[i][8] = uniLibraryManager.listItem.get(i).getReservationTime().toString();
            } else
                array[i][8] = "";
        }

        TableModel model = new DefaultTableModel(array, columnsHeader){
            public Class getColumnClass(int row) {
                Class returnValue;
                if ((row >= 0) && (row < getRowCount())) {
                    returnValue = getValueAt(row, 0).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }

        };

        JTable table = new JTable(model);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);

        JScrollPane pane = new JScrollPane(table);
        table = setFlag(table); // задаем флаг для ячеек в колонке borrowTime

        table.setRowSorter(sorter);
        frame.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Search by Title: ");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField("");
        panel.add(filterText, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        JButton button = new JButton("Search");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        //получаем размер экрана - это для позиционирования GUI в центре экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 1000;
        int height = 400;

        frame.add(button, BorderLayout.SOUTH);
        frame.setBounds((screenSize.width / 2 - width / 2), (screenSize.height / 2 - height / 2), width, height);
        frame.setVisible(true);
    }

    private JTable setFlag(JTable table){
        //здесь рендерим 6 столбец и определяем в какой цвет закрашивать ячейку
        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm"); //задаем формат даты
                Date parsingDate;
                double razn = 0.0; //разница между тек. временем и borrowTime
                try {
                    //парсим все значения в 5 столбце, если ячейка не пустая
                    if(value.toString() != "") {
                        parsingDate = ft.parse(value.toString());
                        //ищем разницу и переводим значение из милисекунд в дни
                        razn = (double)(Calendar.getInstance().getTimeInMillis() - parsingDate.getTime()) / 1000 / 3600 / 24;
                    }
                } catch (Exception e) {
                    System.out.println("Parse failed!");
                }

                //если разница > 0 - флаг красный (книга занята)
                // иначе усли разница = 0 - флаг зелениый, т.е когда книгу не взяли почитать
                if (razn > 0) {
                    renderer.setBackground(Color.RED);
                } else if (razn == 0.0)
                    renderer.setBackground(Color.GREEN);
                return renderer;
            }
        });
        return table;
    }

    public static void main(String[] args, UniLibraryManager uniLibraryManager) {
        new ViewItems(uniLibraryManager);
    }
}
