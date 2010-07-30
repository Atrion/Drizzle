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
    static String jarpath;
    static File thisJarsFile;

    //settings
    static final boolean updateenabled = true;
    static int requestedheapsize;

    
    public static void main(String[] args)
    {
        //shared.GuiUtils.getOKorCancelFromUserDos(m.trans("a"), "b");
        //find memory info:
        //int requiredheapsize = 800; //900;  //all Simplicity works with 400 on Win32.
        int requiredheapsize = 710; //was 720, but Diafero found that it was reported as lower by Linux.
        requiredmemory = requiredheapsize*1024*1024;
        requestedheapsize = (int)(requiredheapsize*1.1); //1.01 is approximately correct, but lets leave lots of space.
        try{
            maxmemory = Runtime.getRuntime().maxMemory();
        }catch(Exception e){}
        //m.msg("Heapsize="+Long.toString(maxmemory));

        //find launcher info:
        String islauncherstr = System.getProperty("Drizzle.IsLauncher","true");
        boolean isLauncher = Boolean.parseBoolean(islauncherstr);
        if(maxmemory>=requiredmemory)
        {
            isLauncher = false;
        }
        //m.msg("IsLauncher="+Boolean.toString(isLauncher));

        //find updater info:
        String isupdaterstr = System.getProperty("Drizzle.IsUpdater","false");
        boolean isUpdater = Boolean.parseBoolean(isupdaterstr);

        //find path to this jar:
        jarpath = shared.JarUtils.GetJarPath(Main.class);
        if(jarpath==null) m.err("Jarpath is null.");
        thisJarsFile = new File(jarpath);

        //check if a new version has been downloaded, and install it, if so.
        PerformUpdate(args,thisJarsFile.getParent(),false); //will halt the JVM if updated.




        if(isLauncher) //then relaunch with correct memsize, etc.
        {
            Main.LaunchDrizzle(jarpath, args, requestedheapsize);
        }
        else if(isUpdater)
        {
            int numretries = 10;
            int mstowait = 2000; //2 seconds
            boolean success = false;
            String genericJar = thisJarsFile.getParent()+"/Drizzle.jar";
            int numtries = 0;
            while(true)
            //for(int i=0;i<numretries;i++)
            {
                try{

                    //copy self to Drizzle.jar
                    shared.FileUtils.DeleteFile(genericJar, true);
                    if(shared.FileUtils.Exists(genericJar)) throw new shared.uncaughtexception("Drizzle.jar isn't deleted yet.");
                    shared.FileUtils.CopyFile(thisJarsFile.getAbsolutePath(), genericJar, true, false, true);
                    success = true;
                    break;

                }catch(Exception e){
                    m.err("Error while updating Drizzle. It seems Drizzle.jar did not close.");
                    e.printStackTrace();
                }
                numtries++;

                if(numtries>numretries)
                {
                    boolean ok = shared.GuiUtils.getOKorCancelFromUserDos(m.trans("Please make sure there are no other copies of Drizzle running, and hit OK to try again."), m.trans("Problem updating")); //This never gets displayed for some reason.
                    if(!ok) break;
                }

                //wait
                try{
                    Thread.sleep(mstowait);
                }catch(Exception e){}
            }

            if(success)
            {
                //launch Drizzle.jar
                Main.LaunchDrizzle(genericJar, args, requestedheapsize);
            }
        }
        else  //start normally
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

            //initialise plugins for all interfaces.
            Plugins.initialise();

            if(args.length>0)
            {
                //command-line mode.
                
                System.out.println("Using the commandline interface!");
                gui.CommandLine.HandleArguments(args);
            }
            else
            {
                //GUI mode.

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

    public static void PerformUpdate(String[] args, String installDir, boolean warnAboutRestart)
    {

        //detect if this is the first installation into this folder
        boolean firstInstall = !(new File(installDir+"/Drizzle.jar").exists());

        boolean thisIsGenericDrizzle = Main.thisJarsFile.getName().equals("Drizzle.jar");

        //find version info:
        String launchUpdater = FindUpdatedDrizzleJar(new File(installDir));

        boolean doupdate = updateenabled && (launchUpdater!=null) && (firstInstall || thisIsGenericDrizzle);

        if(doupdate)
        {
            try
            {
                if(firstInstall)
                {
                    //create Drizzle.jar
                    String from = launchUpdater;
                    String to = installDir+"/Drizzle.jar";
                    shared.FileUtils.CopyFile(from, to, false, false, true);

                    //copy over settings
                    from = thisJarsFile.getParent()+"/"+Gui.settingsfilename;
                    to = installDir+"/"+Gui.settingsfilename;
                    shared.FileUtils.CopyFile(from, to, false, false, false);

                    //if(warnAboutRestart)
                    //{
                    //    msg += m.trans("  Since this is the first time, we'll copy your settings over, and you should use this new copy installed");
                    //}
                }
                
                if(warnAboutRestart)
                {
                    //javax.swing.JOptionPane.sh
                    //shared.GuiUtils.getStringFromUser(msg, msg)
                    shared.GuiUtils.DisplayMessage(m.trans("Restarting to update Drizzle..."), m.trans("Drizzle is about to restart, in order to update itself.  You should use the Drizzle.exe (or less preferably, Drizzle.jar) file in the 'Drizzle' subfolder of Uru to start it.  Please don't get confused and use copies elsewhere, and think they are updated; you can always see which version you're using at the top of Drizzle.  And don't forget to have fun :D"));
                }


                //String jarpath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                //File ffile = new File(jarpath);
                //String file = ffile.getAbsolutePath();

                String[] command = new String[]{
                    "java",
                    "-Xmx"+Integer.toString(requestedheapsize)+"m",//"-Xmx1020m",//"-Xmx800m",
                    "-DDrizzle.IsUpdater=true",
                    "-jar",
                    launchUpdater,
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

                Process proc = Runtime.getRuntime().exec(fullcommand);

                //don't wait for this process, terminate now.
                System.exit(0);

                /*if(args.length>0)
                {
                    //only redirect the output/err streams if we have command-line arguments; i.e. command-line interface is being used.
                    shared.m.StreamRedirector.Redirect(proc);
                }*/

                /*if(args.length>0)
                {
                    //this is a command line invocation, so keep the parent open to redirect io.
                    proc.waitFor();
                }*/
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(launchUpdater!=null)
        {
            //so we're not going to update, *but* there is a newer version.
            m.msg("You have a newer version of Drizzle installed.  Perhaps you want to use that?  It's located at: ",launchUpdater);
        }
        
    }

    private static void LaunchDrizzle(String drizzlefilename, String[] args, int requestedheapsize)
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
            //String jarpath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            File ffile = new File(drizzlefilename);
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
    public static Integer getVersionFromFilename(String filename)
    {
        if(!filename.startsWith("Drizzle")) return null;
        if(!filename.endsWith(".jar")) return null;
        String verstr = filename.substring(7, filename.length()-4);
        if(verstr.equals("")) return null;
        try{
            int r = Integer.parseInt(verstr);
            return r;
        }catch(Exception e){}
        return null;
    }
    private static String FindUpdatedDrizzleJar(File installDir)
    {
        String launchUpdater = null;
        //String jarname = thisJarsFile.getName();
        //if(jarname.equals("Drizzle.jar"))
        {
            //using general Drizzle, check for updates.
            int thisver = Version.version;

            //find the newest version in this folder.
            int maxver = -1;
            File maxjar = null;
            //for(File f: thisJarsFile.getParentFile().listFiles())
            if(installDir.exists())
            {
                for(File f: installDir.listFiles())
                {
                    String curfilename = f.getName();
                    Integer ver = Main.getVersionFromFilename(curfilename);
                    if(ver!=null && ver>maxver)
                    {
                        maxver = ver;
                        maxjar = f;
                    }
                }
            }

            if(maxver>thisver) launchUpdater = maxjar.getAbsolutePath();
        }
        return launchUpdater;
    }
}
