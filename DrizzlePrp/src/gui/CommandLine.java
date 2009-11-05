/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import shared.m;

public class CommandLine
{
    public static void HandleArguments(String[] args)
    {
        if(args[0].equals("-version"))
        {
            m.msg("This version of Drizzle is: ",Integer.toString(gui.Version.version));
        }
        else if(args[0].equals("-help"))
        {
            m.msg("Welcome to Drizzle!");
            m.msg("  -version    ->Shows Drizzle's version.");
            m.msg("  -help    ->This help screen.");
            m.msg("  -mirrordataserver exampleserver.com c:/outfolder    ->Mirrors an Alcugs dataserver at the given address, saving to the given output folder.");
            m.msg("  -generatedataserver c:/infolder c:/outfolder    ->Generates an Alcugs dataserver using the given input Uru installation, saving to the given output folder.");
            m.msg("  -prpdiff c:/source.prp c:/dest.prp c:/generated.diff.txt");
            m.msg("  -changeagename c:/inputfile.prp c:/outputfolder NewAgeName");
            m.msg("  -changeprefix c:/inputfile.prp c:/outputfolder NewSequencePrefix");
            m.msg("  -changepage c:/inputfile.prp c:/outputfolder NewPageName");
            m.msg("  -deepview c:/inputfile.prp");
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
            auto.PrpDiff.FindDiff(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-changeagename"))
        {
            auto.ChangeNameAndPrefix.ChangeName(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-changeprefix"))
        {
            auto.ChangeNameAndPrefix.ChangePrefix(args[1], args[2], args[3]);
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
        else
        {
            m.err("Unknown command.  Use -help for some of the options.");
        }
    }
}
