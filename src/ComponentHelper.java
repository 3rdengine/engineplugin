import javax.swing.*;

/**
 * Created by Tony on 9/21/2014.
 */
public class ComponentHelper {
    public static void populateComboBox(JComboBox combo, String[] labels)
    {
        combo.removeAllItems();
        for (Integer index = 0; index < labels.length; ++index)
        {
            combo.addItem(labels[index]);
        }
    }
}
