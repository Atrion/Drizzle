/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.m;
import java.io.File;
import java.util.Vector;

public class crowthistle
{
    public static void convertCrowthistle(String crowfolder, String outfolder)
    {
        m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        m.state.curstate.showNormalMessages = false;
        m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;

        
        
        shared.State.AllStates.push();
        //shared.State.AllStates.revertToDefaults();
        shared.State.AllStates.setState("removeDynamicCamMap", true);
        shared.State.AllStates.setState("makePlLayersWireframe", false);
        shared.State.AllStates.setState("changeVerySpecialPython", true);
        shared.State.AllStates.setState("translateSmartseeks", false);
        shared.State.AllStates.setState("removeLadders", true);
        shared.State.AllStates.setState("automateMystV", true);
        shared.State.AllStates.setState("includeAuthoredMaterial", shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial")); //this line doesn't really do anything, just there to remind you.
        
        //verify folders
        m.status("Checking the folders you gave...");
        if(!automation.detectinstallation.isFolderCrowthistle(crowfolder))
        {
            return;
        }
        if(!automation.detectinstallation.isFolderPots(outfolder))
        {
            return;
        }
        
        m.status("Starting conversion...");
        //Vector<String> files = fileLists.crowthistleSimplicityList();
        automation.mystAutomation.convertCrowthistleToPots(crowfolder, outfolder);
        
        
        
        shared.State.AllStates.pop();
        m.state.pop();
        //m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!");
        m.status("Dont forget to run SoundDecompress.exe; the button is at UAM->SoundDecompress. (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        m.status("Conversion completed!");
        
    }
}
