/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import java.io.File;
import shared.m;

public class detectinstallation
{
    public static boolean isFolderPots(String potsfolder)
    {
        File potsfile = new File(potsfolder);
        if(!potsfile.exists())
        {
            m.err("The PathOfTheShell/CompleteChronicles folder you selected doesn't exist.");
            return false;
        }
        if(!potsfile.isDirectory())
        {
            m.err("The PathOfTheShell/CompleteChronicles folder you selected must be a folder, not a file.");
            return false;
        }
        File potsexe = new File(potsfile+"/UruExplorer.exe");
        if(!potsexe.exists())
        {
            m.err("The Pots folder you selected doesn't seem to contain Pots.  Please select the folder that contains UruExplorer.exe");
            return false;
        }
        
        return true;
    }
}
