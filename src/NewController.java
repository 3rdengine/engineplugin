import javax.swing.*;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Created by Tony on 9/24/2014.
 */
public class NewController extends DialogWrapper {
    private JComboBox comboBoxNamespace;
    private JComboBox comboBoxBundle;
    private JTextField textEntity;
    private JTextField textRoute;
    private JPanel newControllerPanel;

    /**
     * This holds a reference to the main project.
     */
    public Project project;

    public NewController(boolean canBeParent, Project project) {
        super(canBeParent);
        init();

        this.project = project;

        // Build the contents of the namespace dropdown
        String initialNamespace = this.populateNamespaceCombo();
        this.populateBundleCombo(initialNamespace);

        comboBoxNamespace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namespace = comboBoxNamespace.getSelectedItem().toString();
                populateBundleCombo(namespace);
            }
        });
    }

    protected void populateBundleCombo(String namespace)
    {
        String[] bundles = FileHelper.getSubdirectories(this.project.getBaseDir().findChild("src").findChild(namespace));
        ComponentHelper.populateComboBox(comboBoxBundle, bundles);
    }

    protected String populateNamespaceCombo()
    {
        String[] namespaces = FileHelper.getSubdirectories(this.project.getBaseDir().findChild("src"));
        ComponentHelper.populateComboBox(comboBoxNamespace, namespaces);

        // return the first element that will be the default selection
        return namespaces[0];
    }


    public String getNamespace()
    {
        return comboBoxNamespace.getSelectedItem().toString();
    }
    public String getBundle()
    {
        return comboBoxBundle.getSelectedItem().toString();
    }
    public String getEntity() { return textEntity.getText(); }
    public String getRoute() { return textRoute.getText(); }

    public JComponent createCenterPanel()
    {
        return (JComponent) newControllerPanel;
    }
}
