/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import shared.m;

public class ThreadedTasks
{
    public static shared.GuiThread.GuiThreadInfo getInfo(){
        shared.GuiThread.GuiThreadInfo r = new shared.GuiThread.GuiThreadInfo();
        r.useGlassPane = true;
        r.setWorkingText = true;
        r.setWorkingProgressBar = true;
        r.rootpanes.add(gui.Main.guiform);
        return r;
    }
    public static void uamClearSumFiles()
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            uam.Uam.ClearSumFiles();
        }});
    }
    public static void uamFindAgeVersion()
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            gui.UamGui.ReportVersion();
        }});
    }
    public static void uamDeleteOldArchives()
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            uam.Uam.DeleteOldArchives();
            //gui.UamGui.RefreshInfo();
        }});
    }
    public static void uamDownload()
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            gui.UamGui.PerformDownload();
        }});
    }
    public static void uamDownloadAgeList(final String server, final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            gui.UamGui.GetAgeListGui(server,potsfolder);
        }});
    }
    public static void uamDelete()
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            gui.UamGui.PerformDeletionAction();
        }});
    }

    public static void saveMemories(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.vaultAutomation.saveImages(infolder,outfolder);
        }});
    }

    public static void inplaceConvertPots(final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getPots().ConvertGame(potsfolder, potsfolder);
        }});
    }

    public static void convertCrowthistle(final String infolder, final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getCrowthistle().ConvertGame(infolder, potsfolder);
        }});
    }

    public static void convertHexisle(final String infolder, final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getHexisle().ConvertGame(infolder, potsfolder);
        }});
    }

    public static void convertMystV(final String infolder, final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getMystV().ConvertGame(infolder, potsfolder);
        }});
    }

    public static void convertMoul(final String infolder, final String potsfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getMoul().ConvertGame(infolder, potsfolder);
        }});
    }

    public static void copyPotsMusic(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getPots().CopyMusic(infolder, outfolder);
        }});
    }

    public static void copyMoulMusic(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getMoul().CopyMusic(infolder, outfolder);
        }});
    }

    public static void copyMystVMusic(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getMystV().CopyMusic(infolder, outfolder);
        }});
    }

    public static void copyHexisleMusic(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getHexisle().CopyMusic(infolder, outfolder);
        }});
    }

    public static void copyCrowthistleMusic(final String infolder, final String outfolder)
    {
        shared.GuiThread.run(getInfo(),new java.lang.Runnable() { public void run() {
            auto.AllGames.getCrowthistle().CopyMusic(infolder, outfolder);
        }});
    }


}
