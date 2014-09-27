import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 9/24/2014.
 */
public class CreateServiceController extends AnAction {
    public Project project;
    public String namespace;
    public String bundle;
    public String entity;
    public String fullRoute;

    public VirtualFile bundleDirectory;
    public VirtualFile controllerDirectory;

    public void actionPerformed(AnActionEvent e) {
        this.project = e.getProject();
        NewController newControllerForm = new NewController(false, e.getProject());

        newControllerForm.getPeer().setTitle("New Service Controller");
        newControllerForm.show();

        if (newControllerForm.getExitCode() == 1)
        {
            // the action was canceled, so don't take any more actions
            return;
        }

        this.namespace = newControllerForm.getNamespace();
        this.bundle    = newControllerForm.getBundle();
        this.entity    = newControllerForm.getEntity();
        this.fullRoute = newControllerForm.getRoute();

        this.bundleDirectory = this.project.getBaseDir().findChild("src").findChild(this.namespace).findChild(this.bundle);
        this.controllerDirectory = bundleDirectory.findChild("Controller");

        if (this.controllerDirectory == null)
        {
            FileHelper.createSubdirectory(this.bundleDirectory, "Controller");
            this.controllerDirectory = bundleDirectory.findChild("Controller");
        }

        FileHelper.createFile(this.project, this.controllerDirectory, this.entity + "Controller.php", this.getControllerContents(), true);
    }

    public String getControllerContents()
    {
        HashMap<String, String> variables = new HashMap<String, String>();

        variables.put("namespace", this.namespace);
        variables.put("bundle", this.bundle);
        variables.put("entity", this.entity);
        variables.put("fullRoute", this.fullRoute);

        // get the short bundle
        variables.put("shortBundle", this.bundle.replace("Bundle", ""));

        // get the small route. this one can be done by remove the first clause from the full route
        Integer slashIndex = this.fullRoute.indexOf('/', 1);
        variables.put("shortRoute", this.fullRoute.substring(slashIndex));

        return TemplateHelper.getRenderedTemplate("ControllerTemplate.php", variables);
    }
}
