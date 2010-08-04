/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import shared.*;
import java.io.File;
import java.util.ArrayList;

public class CommandLine
{
    public static void HandleCommand(String[] args)
    {
        if(args[0].equals("-version"))
        {
            m.msg("This version of Drizzle is: ",Integer.toString(gui.Version.version));
        }
        else if(args[0].equals("-help"))
        {
            m.msg("Welcome to Drizzle!");
            m.msg("  (Any of these commands can be compounded into a list, e.g. '-version -prpdiff c:/source.prp c:/dest.prp -help')");
            m.msg("  -version    ->Shows Drizzle's version.");
            m.msg("  -help    ->This help screen.");
            m.msg("  -mirrordataserver exampleserver.com c:/outfolder    ->Mirrors an Alcugs dataserver at the given address, saving to the given output folder.");
            m.msg("  -generatedataserver c:/infolder c:/outfolder    ->Generates an Alcugs dataserver using the given input Uru installation, saving to the given output folder.");
            //m.msg("  -prpdiff c:/source.prp c:/dest.prp c:/generated.diff.txt");
            m.msg("  -prpdiff c:/source.prp c:/dest.prp");
            //m.msg("  -changeagename c:/inputfile.prp c:/outputfolder NewAgeName");
            m.msg("  -changeagename c:/inputfile.prp c:/outputfolder NewAgeName       ->Does not change python/ogg files.");
            m.msg("  -changeprefix c:/inputfile.prp c:/outputfolder NewSequencePrefix");
            m.msg("  -changepage c:/inputfile.prp c:/outputfolder NewPageName");
            m.msg("  -deepview c:/inputfile.prp    ->Starts DrizzleDeepview.  The inputfile is optional.");
            m.msg("  -changeagenameandprefix c:/inputfile.prp c:/outputfolder NewAgeName NewSequencePrefix    ->Changes python/ogg files.");
            m.msg("  -inplacemod c:/potsfolder dat/inputfile.prp ModName");
            m.msg("  -listinplacemods    ->Displays all the available InplaceMods.");
            m.msg("  -unpackpak c:/pakfile.pak c:/outputfolder gamename    ->Extracts all the .pyc files from a Python22 .pak file.");
            m.msg("  -decompilepyc c:/pycfile.pyc c:/outputfolder    ->Decompiles a .pyc file using DrizzleDecompile.");
            m.msg("  -decompilepak c:/pakfile.pak c:/outputfolder gamename    ->Decompiles all .pyc files within a Python22 .pak file, using DrizzleDecompile.");
            m.msg("  -convert3dsmaxtopots c:/3dsmaxexportfolder c:/potsfolder agename1,agename2,etc    ->Converts the files exported by the 3dsmax plugin to Pots.");
            m.msg("  -listgamenames    ->Lists the possible options for 'gamename' arguments in other commands.");
            //m.msg("(gamename is one of: "+auto.Game.getAllGamenames()+" )");
            m.msg("  -removepythonoverrides c:/pakfolder c:/outputfolder overriddenpakname.pak gamename    ->Removes the entries from a .pak file, which have overrides in other files.");
            m.msg("  -downloadmoulagainfiles c:/outputfolder");
            m.msg("  -downloadsecuremoulagainfiles username password c:/outputfolder");
            m.msg("  -startproxyserver c:/ProxyUruFolder     ->(Please note that there is no cleanup after this runs, so you should restart Drizzle.)");
            m.msg("  -patchmoulbinary c:/path/to/binary.exe server.com");
            m.msg("  -startmoulserver MainPassword server.com c:/DataFolder     ->(Please note that there is no cleanup after this runs, so you should restart Drizzle.)");
            m.msg("  -translateagedown c:/inputfile.prp c:/outputfolder 200.0   ->Translates everying in the prp down so many units.");
        }
        else if(args[0].equals("-translateagedown"))
        {
            File f = new File(args[1]);
            prpobjects.prpfile prp = prpobjects.prpfile.createFromFile(args[1], true);
            float z = Float.parseFloat(args[3]);
            auto.mod.AutoMod_TranslateAge.translateAllObjects(prp, 0.0f, 0.0f, -z);
            prp.saveAsFile(args[2]+"/"+f.getName());
        }
        else if(args[0].equals("-startmoulserver"))
        {
            moulserver.Manager.StartServers(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-patachmoulbinary"))
        {
            moulserver.Patcher.PatchFile(args[1], args[2]);
        }
        else if(args[0].equals("-startproxyserver"))
        {
            moulserver.Proxy.start(args[1]);
        }
        else if(args[0].equals("-downloadsecuremoulagainfiles"))
        {
            moulserver.Client_SaveSecureDownloadFiles.SaveSecureDownloadQueue(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-downloadmoulagainfiles"))
        {
            moulserver.Client_SaveFileServerFiles.SaveSecureDownloadQueue(args[1]);
        }
        else if(args[0].equals("-removepythonoverrides"))
        {
            auto.Python.RemovePythonOverrides(args[1], args[2], args[3], args[4]);
        }
        else if(args[0].equals("-listgamenames"))
        {
            m.msg(auto.Game.getAllGamenames());
        }
        else if(args[0].equals("-convert3dsmaxtopots"))
        {
            auto.Max.convert3dsmaxToPots(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-decompilepak"))
        {
            auto.Python.DecompilePak(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-decompilepyc"))
        {
            auto.Python.DecompilePyc(args[1], args[2]);
        }
        else if(args[0].equals("-unpackpak"))
        {
            auto.Python.UnpackPak(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-mirrordataserver"))
        {
            uru.server.Dataserver.MirrorServer(args[1], args[2], true, true, true, true, true, "");
        }
        else if(args[0].equals("-generatedataserver"))
        {
            uru.server.Dataserver.CreateFiles(args[1], args[2], true);
        }
        else if(args[0].equals("-input"))
        {
            java.util.Scanner s = new java.util.Scanner(System.in);
            String a = s.nextLine();
            System.out.print(a);
        }
        else if(args[0].equals("-prpdiff"))
        {
            auto.PrpDiff.FindDiff(args[1], args[2]);
        }
        else if(args[0].equals("-changeagename"))
        {
            auto.ChangeNameAndPrefix.ChangeName(args[1], args[2], args[3], false);
        }
        else if(args[0].equals("-changeprefix"))
        {
            auto.ChangeNameAndPrefix.ChangePrefix(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-changeagenameandprefix"))
        {
            auto.ChangeNameAndPrefix.ChangeNameAndPrefix(args[1],args[2],args[3],args[4],true);
        }
        else if(args[0].equals("-changepage"))
        {
            auto.ChangeNameAndPrefix.ChangePagename(args[1],args[2],args[3]);
        }
        else if(args[0].equals("-deepview"))
        {
            String filename = (args.length>=2)?args[1]:null;
            deepview2.dvGUI.open(filename);
        }
        else if(args[0].equals("-inplacemod"))
        {
            auto.inplace.Inplace.InplaceMod(args[1],args[2],args[3]);
        }
        else if(args[0].equals("-listinplacemods"))
        {
            auto.inplace.Inplace.printAllModNames();
        }
        else
        {
            m.err("Unknown command.  Use -help for some of the options.");
        }
    }
    public static void HandleArguments(String[] args)
    {
        ArrayList<ArrayList<String>> commands = CommandLine.SplitArgumentsIntoCommands(args);
        if(commands==null) return;

        for(ArrayList<String> command: commands)
        {
            String[] cargs = command.toArray(new String[]{});
            HandleCommand(cargs);
        }

    }

    public static ArrayList<ArrayList<String>> SplitArgumentsIntoCommands(String[] args)
    {
        //split into commands:
        ArrayList<ArrayList<String>> commands = new ArrayList();
        ArrayList<String> command = null;
        for(String arg: args)
        {
            if(arg.startsWith("--"))
            {
                //command within a command; treat as arg.
                if(command==null)
                {
                    m.err("Commands must start with a -, e.g. '-help'.");
                    return null;
                }
                command.add(arg);
            }
            else if(arg.startsWith("-"))
            {
                //new command.
                if(command!=null) commands.add(command); //save the current one
                command = new ArrayList(); //start a new one
                command.add(arg);
            }
            else
            {
                //regular arg.
                if(command==null)
                {
                    m.err("Commands must start with a -, e.g. '-help'.");
                    return null;
                }
                command.add(arg);
            }
        }
        if(command!=null) commands.add(command); //save the last one

        return commands;

    }
}
