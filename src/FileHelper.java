import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tony on 9/21/2014.
 */
public class FileHelper {
    public static String[] getSubdirectories(VirtualFile directory)
    {
        return FileHelper.getSubdirectories(directory, true);
    }

    public static String[] getSubdirectories(VirtualFile directory, Boolean sortResults)
    {
        List<String> childList = new ArrayList<String>();

        // build a list object with all of the subdirectories, basically exclude any child that is not a directory
        Integer childCount = directory.getChildren().length;
        for (Integer index = 0; index < childCount; ++index)
        {
            VirtualFile child = directory.getChildren()[index];
            if (!child.isDirectory())
            {
                continue;
            }

            childList.add(child.getName());
        }

        String[] childArray = new String[childList.size()];
        childList.toArray(childArray);

        if (sortResults)
        {
            Arrays.sort(childArray);
        }

        return childArray;
    }

    public static void createSubdirectory(final VirtualFile parentDirectory, String name)
    {
        final String directoryName = name;

        ApplicationManager.getApplication().runWriteAction(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    parentDirectory.createChildDirectory(null, directoryName);
                }
                catch (IOException exception)
                {
                    JOptionPane.showMessageDialog(null, "Could not create new directory '" + directoryName + "'.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void createFile(final Project project, final VirtualFile directory, final String name, final String contents, final boolean openFile)
    {
        ApplicationManager.getApplication().runWriteAction(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    VirtualFile newFile = directory.createChildData(null, name);
                    Document doc = FileDocumentManager.getInstance().getDocument(newFile);
                    doc.setText(contents);

                    if (openFile)
                    {
                        FileEditorManager.getInstance(project).openFile(newFile, true);
                    }
                }
                catch (IOException exception)
                {
                    JOptionPane.showMessageDialog(null, "Could not create new file '" + name + "'.", null, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
