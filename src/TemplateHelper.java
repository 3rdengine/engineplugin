import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tony on 9/25/2014.
 */
public class TemplateHelper {
    public static String getRenderedTemplate(final String templateFile, final Map<String, String> variables)
    {
        try
        {
            InputStream is = TemplateHelper.class.getResourceAsStream("ControllerTemplate.php");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }

            br.close();
            isr.close();
            is.close();

            String contents = builder.toString();

            for (String key : variables.keySet())
            {
                String value = variables.get(key);
                String token = "\\[\\[" + key + "\\]\\]";

                contents = contents.replaceAll(token, value);
            }

            return contents;
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(null, "Error reading template file.", null, JOptionPane.ERROR_MESSAGE);
        }

        return "";
    }
}
