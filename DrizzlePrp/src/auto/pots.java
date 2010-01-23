/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import java.io.File;
import shared.*;
import uru.moulprp.*;

public class pots
{
    public static AllGames.GameInfo getGameInfo()
    {
        AllGames.GameInfo r = new AllGames.GameInfo();
        r.GameName = "PathOfTheShell/CompleteChronicles";
        r.DetectionFile = "UruExplorer.exe";
        r.MusicFiles = new String[]{
            //AtrusIntro.bik has some music.
            "ahnyCathedralMusic.ogg",
            "dsntRestAreaMusic_Loop.ogg",
            "ercaHarvesterMusic.ogg",
            "grsnExteriorMusic_Loop.ogg",
            "grsnRandTCMusic01.ogg",
            "grsnRandTCMusic02.ogg",
            "grsnRandTCMusic03.ogg",
            "grsnTrainingWallMusic01.ogg",
            "islmCavernMusic_Loop.ogg",
            "islmLibraryInteriorMusic.ogg",
            "islmLibraryMusic_Loop.ogg",
            "islmPalaceMusic_Loop.ogg",
            "kdshMoonRoomMusic_Loop.ogg",
            "kdshX2VaultMusic.ogg",
            "KverRandMusic01.ogg","KverRandMusic02.ogg","KverRandMusic03.ogg","KverRandMusic04.ogg","KverRandMusic05.ogg",
            "KverYeeshaMusic.ogg",
            "mystLibraryMusic_loop.ogg",
            "psnlMusicPlayer.ogg",
            "tldnBaronCityMusic_Loop.ogg",
            "tldnUpperShroomMusic_Loop.ogg",
            "tmnaBahro_EndMusic.ogg",
            "tmnaYeesha_FinalMusic01.ogg",
            "tmnaYeesha_FinalMusic02.ogg",
            "clftBookRoomMusic_Loop.ogg",
            "clftOpenMusic.ogg",
            "clftYeesha_IntroMusic.ogg",
            "grsnInteriorMusic_Loop.ogg",
            "grsnTCBridgeMusic.ogg",
            "islmGalleryMusic_Loop.ogg",
            "tldnSlaveOfficeMusic_Loop.ogg",
            //don't have "music" in filename:
            "tldnSporeMe_Loop.ogg",
            "kdshPillarSolution_Loop.ogg",
            "ahnySphere03-Amb01_Loop.ogg",
            "ErcaMusSeg01-0.ogg",
            "ercaMusSeg02-1.ogg",
            "ErcaMusSeg03-2.ogg",
            "nb01PrivateRoom_Loop.ogg",
            "clftZandiRadio_Loop.ogg",
            //"tmnaCreditsMusic.ogg", //In ABM, but expansion packs delete it.  Full Peter Gabriel song.
        };
        return r;
    }

    /*public static void CopyMusic(String potsinfolder, String potsfolder)
    {
        m.status("Checking the folders you gave...");
        if(!detectinstallation.isFolderPots(potsinfolder)) return;
        if(!detectinstallation.isFolderPots(potsfolder)) return;

        for(String filename: auto.fileLists.potsMusic)
        {
            String infile = potsinfolder + "/sfx/" + filename;
            String outfile = potsfolder + "/MyMusic/" + filename;

            FileUtils.CopyFile(infile, outfile, true, true);
        }

        m.status("Done copying Pots music!");
    }*/

    public static void CreateAgeReport(String potsfolder, String agename)
    {
        m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        m.state.curstate.showNormalMessages = false;
        m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;

        if(!FileUtils.Exists(potsfolder+"/dat/"+agename+".age"))
        {
            m.status("Couldn't find "+agename+".age, you should probably have entered the filename of the Age. (e.g. 'EderRiltehInaltahv' for Eder Rilteh Inaltahv.)");
        }

        //for(File f: FileUtils.FindAllFiles(folder, ".prp", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/dat/", agename+"_District_", ".prp", false,false))
        {
            uru.moulprp.prpfile prp = uru.moulprp.prpfile.createFromFile(f, true);
            m.status("Prp file:",f.getName());
            m.status("  Pageid:",prp.header.pageid.toString2());
            
            for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plSoundBuffer))
            {
                uru.moulprp.x0029SoundBuffer sound = obj.castTo();
                m.status("  Ogg file:",sound.oggfile.toString()," flags:",Integer.toString(sound.flags)," chans:",Integer.toString(sound.channels));

                //check if ogg file is present:
                if(!FileUtils.Exists(potsfolder+"/sfx/"+sound.oggfile.toString()))
                {
                    m.status("    Warning: this file is not present in the sfx folder.");
                }
            }

            for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plPythonFileMod))
            {
                uru.moulprp.x00A2Pythonfilemod pfm = obj.castTo();
                m.status("  Python file:",pfm.pyfile.toString());
            }
            
            m.status("");
        }
        
        //for(File f: FileUtils.FindAllFiles(folder, ".pak", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/Python/", agename, ".pak", false,false))
        {
            m.status("Pak file:",f.getName());
            uru.moulprp.pakfile pak = new uru.moulprp.pakfile(f,3,false);
            for(uru.moulprp.pakfile.IndexEntry ind: pak.indices)
            {
                m.status("  Python file:",ind.objectname.toString());
            }
            m.status("");
        }
        
        //for(File f: FileUtils.FindAllFiles(folder, ".age", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/dat/", agename, ".age", false,false))
        {
            m.status("Age file:",f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        //for(File f: FileUtils.FindAllFiles(folder, ".fni", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/dat/", agename, ".fni", false,false))
        {
            m.status("Fni file:",f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        //for(File f: FileUtils.FindAllFiles(folder, ".sdl", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/SDL/", agename, ".sdl", false,false))
        {
            m.status("Sdl file:",f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        //for(File f: FileUtils.FindAllFiles(folder, ".sum", false))
        for(File f: FileUtils.FindAllFiles(potsfolder+"/dat/", agename, ".sum", false,false))
        {
            m.status("Sum file:",f.getName());
            //uru.moulprp.sumfile sum = new uru.moulprp.sumfile(FileUtils.ReadFile(f), true, 3);
            uru.moulprp.sumfile sum = uru.moulprp.sumfile.readFromFile(f, 3);
            for(uru.moulprp.sumfile.sumfileFileinfo info: sum.files)
            {
                m.status("  file:",info.filename.toString());
            }
            m.status("");
        }
        
        m.state.pop();
    }
}