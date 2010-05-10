/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import auto.conversion;
import shared.m;
import java.util.Vector;
import auto.AllGames;

public class magiquest
{
    //todo
    //avi files
    //new avatar animations?
    //guis
    //python and sdl
    //avatar clothing?

    public static AllGames.GameInfo getGameInfo()
    {
        AllGames.GameInfo r = new AllGames.GameInfo();
        r.GameName = "MagiQuestOnline";
        //r.readversion = 8;
        r.PythonVersion = 23;
        r.game = auto.Game.mqo;
        r.prpMarkerForAgename = "_District_";
        r.DetectionFile = "MQExplorer.exe";
        //r.MusicFiles = new String[]{};
        r.renameinfo.prefices.put("PortalWell", 1048); //was 1
        r.renameinfo.prefices.put("Courtyard", 1049); //was 3
        r.renameinfo.prefices.put("Forest", 1050); //was 5
        r.renameinfo.agenames.put("Forest", "ForestMQ"); //because of a fan age called forest.
        //r.addSoundFiles(magiquestSoundFiles);
        r.addAgeFiles("Courtyard", new String[]{
            "Courtyard.age","Courtyard.fni","Courtyard.sum","Courtyard_District_Courtyard.prp","Courtyard_District_CRTRedoGUI.prp","Courtyard_District_MQQuestInterface.prp","Courtyard_District_QuestItems.prp","Courtyard_District_Textures.prp",
        });
        r.addAgeFiles("Forest", new String[]{
            "Forest.age","Forest.fni","Forest.sum","Forest_District_BattleGUI.prp","Forest_District_Forest.prp","Forest_District_FORRedoGUI.prp","Forest_District_HintGUI.prp","Forest_District_QuestItems.prp","Forest_District_Textures.prp","Forest_District_Windmill.prp",
        });
        r.addAgeFiles("PortalWell", new String[]{
            "PortalWell.age","PortalWell.fni","PortalWell.sum","PortalWell_District_PortalWell.prp","PortalWell_District_Textures.prp",
        });
        return r;
    }
    public static void convertMagiquest(String infolder, String outfolder)
    {
        AllGames.GameConversionSub _mqo = new AllGames.GameConversionSub(magiquest.getGameInfo());
        _mqo.ConvertGame(infolder, outfolder);
        /*m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        //m.state.curstate.showNormalMessages = false;
        //m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;



        shared.State.AllStates.push();
        //shared.State.AllStates.revertToDefaults();
        shared.State.AllStates.setState("removeDynamicCamMap", true);
        shared.State.AllStates.setState("makePlLayersWireframe", false);
        shared.State.AllStates.setState("changeVerySpecialPython", true);
        shared.State.AllStates.setState("translateSmartseeks", false);
        shared.State.AllStates.setState("removeLadders", true);
        shared.State.AllStates.setState("automateMystV", true);
        //shared.State.AllStates.setState("includeAuthoredMaterial", shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial")); //this line doesn't really do anything, just there to remind you.
        shared.State.AllStates.setState("includeAuthoredMaterial", false);

        //verify folders
        m.status("Checking the folders you gave...");
        m.status("fix the folder check.");
        //if(!auto.detectinstallation.isFolderX(infolder,"MagiQuest","MQExplorer.exe"))return;
        //if(!auto.detectinstallation.isFolderPots(outfolder)) return;

        m.status("Starting conversion...");
        //Vector<String> files = fileLists.crowthistleSimplicityList();
        convertMagiquestToPots(infolder, outfolder);



        shared.State.AllStates.pop();
        m.state.pop();
        m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!  (You can also click the SoundDecompress button on the form if you prefer.) (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        //m.status("Dont forget to run SoundDecompress.exe; the button is at UAM->SoundDecompress. (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        m.status("Conversion completed!");
        */
    }

    /*static String[] magiquestSimplicityFiles = new String[]{
        "Courtyard.age","Courtyard.fni","Courtyard.sum","Courtyard_District_Courtyard.prp","Courtyard_District_QuestItems.prp","Courtyard_District_Textures.prp",

        "Forest.age","Forest.fni","Forest.sum","Forest_District_BattleGUI.prp","Forest_District_Forest.prp","Forest_District_QuestItems.prp","Forest_District_Textures.prp","Forest_District_HintGUI.prp","Forest_District_FORRedoGUI.prp",

        "PortalWell.age","PortalWell.fni","PortalWell.sum","PortalWell_District_PortalWell.prp","PortalWell_District_Textures.prp",

    };*/
    static String[] magiquestSoundFiles = new String[]{
        //courtyard:
        "BirdFlaps01.ogg","BirdFlaps02.ogg","BirdRandom01.ogg","BirdRandom02.ogg","BirdRandom03.ogg","BirdRandom04.ogg","BirdRandom05.ogg","BirdRandom06.ogg","CourtyardAmbience02.ogg","crtCourtyardMusic_01.ogg","crtCourtyardMusic_02.ogg","crtCourtyardMusic_03.ogg","Random_Buzz01.ogg","Random_Buzz02.ogg","Random_Buzz03.ogg","Stream_Loop.ogg",
        //forest:
        //no sounds from Forest Age.
        //portalwell:
        "prtPortalWellAmb_Loop01.ogg","Tutorial01.ogg","Tutorial01_Nag.ogg","Tutorial02.ogg","Tutorial02_Nag.ogg","Tutorial03.ogg","Tutorial03_Nag.ogg","Tutorial04.ogg","Tutorial04_Nag.ogg","Tutorial05.ogg","Tutorial05_Nag.ogg","Tutorial06.ogg","Tutorial06_Nag.ogg","Tutorial07.ogg","Tutorial08.ogg",
    };

    /*public static void convertMagiquestToPots(String infolder, String outfolder)
    {
        Vector<String> files = shared.generic.convertArrayToVector(magiquestSimplicityFiles);
        auto.moul.convertMoulToPots(infolder, outfolder, files, true, magiquest.getMagiQuestRenameInfo());

    }*/

    /*public static conversion.RenameInfo getMagiQuestRenameInfo()
    {
        conversion.RenameInfo r = new conversion.RenameInfo();

        r.prefices.put("PortalWell", 1048); //was 1
        r.prefices.put("Courtyard", 1049); //was 3
        r.prefices.put("Forest", 1050); //was 5

        r.agenames.put("Forest", "ForestMQ"); //because of a fan age called forest.

        return r;
    }*/

}
