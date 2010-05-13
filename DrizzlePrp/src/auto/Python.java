/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import shared.m;
import shared.b;
import java.io.File;

public class Python
{
    public static void UnpackPak(String infile, String outfolder, String gamename)
    {
            m.status("Unpacking pak file...");
            prpobjects.pakfile pak = prpobjects.pakfile.create(infile, auto.AllGames.get(gamename));
            pak.extractPakFile(true, outfolder);
            m.status("Done unpacking!");
    }
    public static void DecompilePakOrPyc(String infile, String outfolder, String gamename)
    {
        if(infile.endsWith(".pak"))
            DecompilePak(infile,outfolder,gamename);
        else if(infile.endsWith(".pyc"))
            DecompilePyc(infile,outfolder);
        else
            m.err("Filename must end with .pyc or .pak");
    }
    public static void DecompilePak(String infile, String outfolder, String gamename)
    {
        m.status("Decompiling pak file...");
        prpobjects.pakfile pak = prpobjects.pakfile.create(infile, auto.AllGames.get(gamename));
        java.util.List<pythondec.pycfile> pycs = pak.extractPakFile(true);
        for(pythondec.pycfile pyc: pycs)
        {
            m.msg("Decompiling: ",pyc.filename);
            pyc.decompile();
            String source = pyc.generateSourceCode();
            String outfile = outfolder+"/"+pyc.filename;
            shared.FileUtils.WriteFile(outfile, b.StringToBytes(source),true,true);
        }
        m.status("Done decompiling!");
    }
    public static void DecompilePyc(String infile, String outfolder)
    {
        m.status("Decompiling pyc file...");
        pythondec.pycfile pyc = pythondec.pycfile.createFromFilename(infile);
        pyc.decompile();
        String source = pyc.generateSourceCode();
        String outfile = outfolder+"/"+(new File(infile)).getName().replace(".pyc", ".py");
        shared.FileUtils.WriteFile(outfile, b.StringToBytes(source),true,true);
        m.status("Done decompiling!");
    }

}
