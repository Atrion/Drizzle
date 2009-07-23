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
            m.msg("  -changenameandprefix c:/inputfile.prp c:/outputfile.prp NewAgeName NewSequencePrefix");
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
            automation.PrpDiff.FindDiff(args[1], args[2], args[3]);
        }
        else if(args[0].equals("-changenameandprefix"))
        {
            automation.ChangeNameAndPrefix.ChangeNameAndPrefix(args[1], args[2], args[3], args[4]);
        }
        else
        {
            m.err("Unknown command.  Use -help for some of the options.");
        }
    }
}
