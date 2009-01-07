/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import shared.FileUtils;
import java.io.File;

public class Exec
{
    public static void LaunchProgram(String execFile, String name)
    {
        String folder = new File(execFile).getParent();
        //String potsfolder = getPotsFolder()+"/";
        //if(!automation.detectinstallation.isFolderPots(potsfolder)) return;
        if(!FileUtils.Exists(execFile))
        {
            m.err("Unable to launch program: "+execFile+" because it doesn't exist.");
            return;
        }
        String[] command = new String[]{
            //potsfolder+"UruSetup.exe",
            execFile
        };
        try
        {
            java.lang.Process p = Runtime.getRuntime().exec(command, null, new File(folder));
            //Process proc = Runtime.getRuntime().exec(command);
            m.status(name+" launched!");
        }
        catch(java.io.IOException e)
        {
            m.err("Unable to launch "+name+".");
        }

                
    }

}
