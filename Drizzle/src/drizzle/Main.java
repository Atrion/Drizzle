/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drizzle;

import java.io.File;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
            File file = shared.GetResource.getResourceAsFile("/drizzle/DrizzlePrp.jar", true);
            String[] command = new String[]{
                "java",
                "-Xmx1020m",//"-Xmx800m",
                "-jar",
                file.getAbsolutePath(),
            };
            Process proc = Runtime.getRuntime().exec(command);
            proc.waitFor();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
