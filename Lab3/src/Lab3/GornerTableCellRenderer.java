package Lab3;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer
{
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
    public GornerTableCellRenderer()
    {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);

// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int col)
    {
// Создать компонент панели
        JPanel panel = new JPanel();
// Создать компонент надписи
        JLabel label = new JLabel(value.toString());
// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        if (col == 1 && needle!=null && needle.equals(formattedDouble))
        {
            panel.setBackground(Color.RED);
        }
        else
        {
            panel.setBackground(Color.WHITE);
        }

        if (col == 1)
        {
            double some = Double.valueOf(formattedDouble);
            some *= Math.pow(10, formatter.getMaximumFractionDigits());
            int sum = 0;
            for (int i = 1; i <= formatter.getMaximumFractionDigits(); i++)
            {
                sum += some % (Math.pow(10, i)) / (Math.pow(10, i - 1));
                System.out.println(sum);
            }
            if (sum % 10 == 0 && sum != 0)
                panel.setBackground(Color.BLUE);
        }
        return panel;

    }

    public void setNeedle(String needle)
    {
        this.needle = needle;
    }
}

