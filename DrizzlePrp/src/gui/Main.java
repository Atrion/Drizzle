/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package gui;


import javax.swing.UIManager;
import shared.m;
import java.io.File;

public class Main extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    static public Gui guiform;
    static String javaversion = "";
    static double javaversion2 = 0.0;
    static String os = "";
    static String osversion = "";
    static double osversion2 = 0.0;
    static long maxmemory = 0;
    static long requiredmemory;

    static public Runnable debugcheck;
    
    public static void main(String[] args)
    {
        //int requiredheapsize = 800; //900;  //all Simplicity works with 400 on Win32.
        int requiredheapsize = 720;
        requiredmemory = requiredheapsize*1024*1024;
        int requestedheapsize = (int)(requiredheapsize*1.1); //1.01 is approximately correct, but lets leave lots of space.
        try{
            maxmemory = Runtime.getRuntime().maxMemory();
        }catch(Exception e){}
        //m.msg("Heapsize="+Long.toString(maxmemory));

        String islauncherstr = System.getProperty("Drizzle.IsLauncher","true");
        boolean isLauncher = Boolean.parseBoolean(islauncherstr);
        if(maxmemory>=requiredmemory)
        {
            isLauncher = false;
        }
        //m.msg("IsLauncher="+Boolean.toString(isLauncher));

        if(isLauncher) //then launch with correct memsize, etc.
        {
            try
            {
                /*if(args.length>0)
                {
                    System.out.println("commandline2!");
                    for(String arg: args)
                    {
                        System.out.println(arg);
                    }
                }*/

                //File file = shared.GetResource.getResourceAsFile("/drizzle/DrizzlePrp.jar", true);
                String jarpath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                File ffile = new File(jarpath);
                String file = ffile.getAbsolutePath();
                //file = file.substring(1);
                //String filepath = jarurl.getPath();
                //m.msg("jarfile="+file);

                String[] command = new String[]{
                    "java",
                    "-Xmx"+Integer.toString(requestedheapsize)+"m",//"-Xmx1020m",//"-Xmx800m",
                    "-DDrizzle.IsLauncher=false",
                    "-jar",
                    file,
                };

                String[] fullcommand = new String[command.length+args.length];
                for(int i=0;i<command.length;i++)
                {
                    fullcommand[i] = command[i];
                }
                for(int i=0;i<args.length;i++)
                {
                    fullcommand[command.length+i] = args[i];
                }
                /*System.out.println("commandline3!");
                for(String arg: fullcommand)
                {
                    System.out.println(arg);
                }*/
                Process proc = Runtime.getRuntime().exec(fullcommand);
                if(args.length>0)
                {
                    //only redirect the output/err streams if we have command-line arguments; i.e. command-line interface is being used.
                    shared.m.StreamRedirector.Redirect(proc);
                }

                if(args.length>0)
                {
                    //this is a command line invocation, so keep the parent open to redirect io.
                    proc.waitFor();
                }
                //m.msg("exitval="+Integer.toString(proc.exitValue()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try{
                //javaversion = System.getProperty("java.version");
                javaversion = System.getProperty("java.specification.version");
                System.out.println("Using JRE version: "+javaversion);
                javaversion2 = Double.parseDouble(javaversion);
                /*String[] verparts = version.split(".");
                String verstr = verparts[0]+"."+verparts[1];
                float jreversion = Float.parseFloat(verstr);
                float minver = 1.6f;
                if(jreversion<minver)
                {
                    System.out.println("Your JRE version is too old.");
                }*/
                //System.out.println("Written with JRE version 1.6.0");

                //get jar name:
                //String source = Gui.class.getProtectionDomain().getCodeSource().getLocation().toString();
                //shared.m.msg(source);
            }catch(Exception e){}
            try{
                os = System.getProperty("os.name");
            }catch(Exception e){}
            try{
                osversion = System.getProperty("os.version");
                osversion2 = Double.parseDouble(osversion);
            }catch(Exception e){}

            if(args.length>0)
            {
                //command-line mode.
                System.out.println("Using the commandline interface!");
                gui.CommandLine.HandleArguments(args);
            }
            else
            {
                //select whether to match the native widgets or use the Swing appearance.
                try
                {
                    //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
                    //javax.swing.UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
                    //javax.swing.UIManager.setLookAndFeel( sun.java.swing.plaf.gtk.GTKLooktAndFeel());
                    //javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
                    //javax.swing.JDialog j = new javax.swing.JDialog();
                    //j.
                    //javax.swing.UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
                    //javax.swing.UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceModerateLookAndFeel());
                    //javax.swing.UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessLookAndFeel());
                    //org.jvnet.substance.SubstanceLookAndFeel.setSkin(new org.jvnet.substance.skin.BusinessSkin());
                    //javax.swing.LookAndFeel laf = new com.jgoodies.looks.windows.WindowsLookAndFeel();
                    //javax.swing.LookAndFeel laf = new com.jgoodies.looks.plastic.PlasticXPLookAndFeel();
                    javax.swing.LookAndFeel laf;
                    //laf = new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel();
                    laf = new com.sun.java.swing.plaf.motif.MotifLookAndFeel();
                    javax.swing.UIManager.setLookAndFeel(laf);

                    shared.GuiUtils.setCrossPlatformFonts(true);
                    //shared.GuiUtils.setBackgroundColour(java.awt.Color.CYAN);

                }
                catch(Exception e){
                    int dummy=0;
                }

                //Run the main form.
                java.awt.EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {
                    //org.jvnet.substance.SubstanceLookAndFeel.setSkin(new org.jvnet.substance.skin.ModerateSkin());
                        guiform = new Gui();
                        if(debugcheck!=null) debugcheck.run();
                        //java.net.URL url = this.getClass().getResource("Pterosaur2b4-16.png");
                        //javax.swing.ImageIcon image = new javax.swing.ImageIcon(url,"");
                        //java.awt.Image img = image.getImage();
                        //java.awt.Image img = shared.GetResource.getResourceAsImage("/gui/Pterosaur2b4-16.png");
                        //gui.setIconImage(img);
                        guiform.setVisible(true);
                    }
                });
            }
        }
    }
    
    /*public static void message(String msg)
    {
        gui.message(msg);
    }
    public static void message(int msg)
    {
        message(Integer.toString(msg));
    }
    public static void warning(String msg)
    {
        message("Warning: "+msg);
    }
    
    public static void errror(String msg)
    {
        message("Error: "+msg);
    }*/

}
