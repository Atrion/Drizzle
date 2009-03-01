/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import java.io.File;
import shared.*;
import uru.moulprp.*;

public class pots
{
    public static void CreateAgeReport(String folder)
    {
        m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        m.state.curstate.showNormalMessages = false;
        m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;
        
        for(File f: FileUtils.FindAllFiles(folder, ".prp", false))
        {
            uru.moulprp.prpfile prp = uru.moulprp.prpfile.createFromFile(f, true);
            m.status("Prp file:"+f.getName());
            m.status("  Pageid:"+prp.header.pageid.toString2());
            
            for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plSoundBuffer))
            {
                uru.moulprp.x0029SoundBuffer sound = obj.castTo();
                m.status("  Ogg file:"+sound.oggfile.toString());
            }

            for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plPythonFileMod))
            {
                uru.moulprp.x00A2Pythonfilemod pfm = obj.castTo();
                m.status("  Python file:"+pfm.pyfile.toString());
            }
            
            m.status("");
        }
        
        for(File f: FileUtils.FindAllFiles(folder, ".pak", false))
        {
            m.status("Pak file:"+f.getName());
            uru.moulprp.pakfile pak = new uru.moulprp.pakfile(f,3,false);
            for(uru.moulprp.pakfile.IndexEntry ind: pak.indices)
            {
                m.status("  Python file:"+ind.objectname.toString());
            }
            m.status("");
        }
        
        for(File f: FileUtils.FindAllFiles(folder, ".age", false))
        {
            m.status("Age file:"+f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        for(File f: FileUtils.FindAllFiles(folder, ".fni", false))
        {
            m.status("Fni file:"+f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        for(File f: FileUtils.FindAllFiles(folder, ".sdl", false))
        {
            m.status("Sdl file:"+f.getName());
            byte[] data = uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f));
            String data2 = b.BytesToString(data);
            m.status(data2);
            m.status("");
        }
        
        for(File f: FileUtils.FindAllFiles(folder, ".sum", false))
        {
            m.status("Sum file:"+f.getName());
            //uru.moulprp.sumfile sum = new uru.moulprp.sumfile(FileUtils.ReadFile(f), true, 3);
            uru.moulprp.sumfile sum = uru.moulprp.sumfile.readFromFile(f, 3);
            for(uru.moulprp.sumfile.sumfileFileinfo info: sum.files)
            {
                m.status("  file:"+info.filename.toString());
            }
            m.status("");
        }
        
        m.state.pop();
    }
}