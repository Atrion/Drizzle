/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import shared.m;
import java.io.File;
import java.util.HashMap;
import shared.FileUtils;
import uam.Uam.InstallStatus;
import uam.Uam;
import java.awt.Color;
import javax.swing.JButton;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import shared.b;

public class UamGui
{
    public static JList agelist;
    public static JList verlist;
    public static JList mirlist;
    public static gui.Gui gui;
    public static JButton downloadbutton;
    public static JButton deletebutton;
    
    final static boolean updateWhileAdjusting = true;

    
    public static void init()
    {
        agelist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(!updateWhileAdjusting && e.getValueIsAdjusting()) return;
                String age = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                GetVersionListGui(age);
            }
        });
        agelist.setCellRenderer(new javax.swing.ListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String agename = (String)value;
                InstallStatus status = Uam.ageInstallStatus.get(agename);

                javax.swing.JLabel label = new javax.swing.JLabel(agename);
                label.setOpaque(true); //allows us to set the background.
                switch(status)
                {
                    case notInstalled:
                        //label.setForeground(Color.red);
                        label.setForeground(new Color(0x550000));
                        break;
                    case latestVersionInCache:
                        //label.setForeground(Color.green);
                        label.setForeground(new Color(0x005500));
                        break;
                    case nonLatestVersionInCache:
                        //label.setForeground(Color.yellow);//new Color(0x00aa00));
                        //label.setForeground(Color.orange);
                        label.setForeground(new Color(0x555500));
                        break;
                    case notInCache:
                        label.setForeground(Color.black);
                        break;
                    default:
                        label.setForeground(Color.black);
                        break;
                }
                //label.setBackground(Color.white);
                if(isSelected) label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
                //else label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.pink));
                //if(cellHasFocus) label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.pink));
                return label;
            }
        });
        verlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!updateWhileAdjusting && e.getValueIsAdjusting()) return;
                String age2 = (String)agelist.getSelectedValue();
                String ver = (String)verlist.getSelectedValue();
                //String ver = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                GetMirrorListGui(age2,ver);
            }
        });
        mirlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!updateWhileAdjusting && e.getValueIsAdjusting()) return;
                String age2 = (String)agelist.getSelectedValue();
                String ver2 = (String)verlist.getSelectedValue();
                String mir = (String)mirlist.getSelectedValue();
                //String mir = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                OnMirrorSelected(age2,ver2,mir);
            }
        });
        
    }
    
    public static void GetLocalInfo(String potsfolder)
    {
        //This function gets info about installed Ages.
        //We care about these facts: which (if any) version of an Age is deployed?
        //In practise, we'll gather this: is any version of an Age installed, and if so we'll assume it's the most recent one in the cache(if any).
        //Because going through each 7zip file and checking hashes would mean pretty heavy waiting.
        //possibilities for each Age on the server list:
        //1)not installed
        //2)installed & has latest version in cache
        //3)installed & doesn't have latest version in cache and has some version
        //4)installed & doesn't have latest version in cache and has no version.
        
        /*Vector<String> installedAges = new Vector();
        for(File f: filesearcher.search.getallfiles(potsfolder+"/dat/", false))
        {
            String agefile = f.getName();
            if(agefile.endsWith(".age"))
            {
                String agename = f.getName().substring(0, agefile.length());
                installedAges.add(agename);
            }
        }*/
        
        String welcomeMessage = Uam.ageList.getWelcomeMessage();
        m.msg(welcomeMessage);
        
        Vector<String> availableAges = Uam.ageList.getAllAgeNames();
        
        HashMap<String,Vector<String>> ageversions = Uam.ageList.getConfigObject().ageversions;
        Uam.ageInstallStatus = new HashMap();
        
        
        for(String age: availableAges)
        {
            if(FileUtils.Exists(potsfolder+"/dat/"+age+".age"))
            {
                Vector<String> versions = ageversions.get(age);
                if(versions==null)
                {
                    //not in database; ignore
                    m.warn("Age has no available versions.");
                }
                else
                {
                    boolean isInCache = false;
                    for(int i=0;i<versions.size();i++)
                    {
                        String version = versions.get(i);
                        //check if we have this version cached...
                        if(FileUtils.Exists(potsfolder+Uam.ageArchivesFolder+age+uam.Uam.versionSep+version+".7z"))
                        {
                            if(i==0)
                            {
                                Uam.ageInstallStatus.put(age, InstallStatus.latestVersionInCache);
                            }
                            else
                            {
                                Uam.ageInstallStatus.put(age, InstallStatus.nonLatestVersionInCache);
                            }
                            isInCache = true;
                            break; //found the newest one, so break.
                        }
                    }
                    if(!isInCache)
                    {
                        Uam.ageInstallStatus.put(age, InstallStatus.notInCache);
                    }
                }
            }
            else
            {
                Uam.ageInstallStatus.put(age, InstallStatus.notInstalled);
            }
        }
        
    }
    public static void RefreshInfo(String potsfolder2)
    {
        //list Ages...
        final Vector<String> ages = uam.Uam.ageList.getAllAgeNames();
        GetLocalInfo(potsfolder2);
        Object selection = agelist.getSelectedValue();
        agelist.setModel(new javax.swing.ListModel() {
            public int getSize() {
                return ages.size();
            }
            public Object getElementAt(int index) {
                return ages.get(index);
            }
            public void addListDataListener(ListDataListener l) {}
            public void removeListDataListener(ListDataListener l) {}
        });
        verlist.setModel(new javax.swing.DefaultListModel());
        mirlist.setModel(new javax.swing.DefaultListModel());
        
        //try to re-select the item that was selected before.
        if(selection==null)
        {
            if(agelist.getModel().getSize()>0) agelist.setSelectedIndex(0);
        }
        else
        {
            agelist.setSelectedValue(selection, true); //doesn't die even if selection is no longer there.
        }
    }
    public static void GetAgeListGuiOffline(String potsfolder)
    {
        if(!automation.detectinstallation.isFolderPots(potsfolder))
        {
            return;
        }
        
        FileInputStream in;
        try
        {
            in = new FileInputStream(potsfolder+uam.Uam.ageArchivesFolder+uam.Uam.statusFilename);
        }
        catch(FileNotFoundException e)
        {
            m.err("Couldn't find cached list.");
            return;
        }
        uam.UamConfig ageList = new uam.UamConfig(in);
        uam.Uam.ageList = ageList;
        RefreshInfo(potsfolder);
    }
    public static void GetAgeListGui(String server, String potsfolder)
    {
        //m.msg("Updating Age list...");
        if(!automation.detectinstallation.isFolderPots(potsfolder))
        {
            return;
        }
        
        final String potsfolder2 = potsfolder;
        
        //final Vector<String> ages = uam.Uam.GetAgeList(server,gui,new shared.delegate() {
        //uam.Uam.GetAgeList(server,gui,new shared.delegate() {
        uam.ThreadDownloadAndProcess.downloadConfig(server, potsfolder, new shared.delegate(){
            public void callback(Object arg) {
                //parse config file.
                byte[] result = (byte[])arg;
                ByteArrayInputStream in = new ByteArrayInputStream(result);
                uam.UamConfig ageList = new uam.UamConfig(in);
                //list Ages...
                //return ageList.getAllAgeNames();
                //uam.UamConfig config = (uam.UamConfig)arg;
                //uam.Uam.ageList = config;
                uam.Uam.ageList = ageList;
                
                RefreshInfo(potsfolder2);
                
                /*final Vector<String> ages = uam.Uam.ageList.getAllAgeNames();
                GetLocalInfo(potsfolder2);
                
                agelist.setModel(new javax.swing.ListModel() {
                    public int getSize() {
                        return ages.size();
                    }
                    public Object getElementAt(int index) {
                        return ages.get(index);
                    }
                    public void addListDataListener(ListDataListener l) {}
                    public void removeListDataListener(ListDataListener l) {}
                });
                verlist.setModel(new javax.swing.DefaultListModel());
                mirlist.setModel(new javax.swing.DefaultListModel());*/
                
                /*agelist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        String age = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                        GetVersionListGui(age);
                    }
                });
                agelist.setCellRenderer(new javax.swing.ListCellRenderer() {
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        String agename = (String)value;
                        InstallStatus status = Uam.ageInstallStatus.get(agename);
                        
                        javax.swing.JLabel label = new javax.swing.JLabel(agename);
                        label.setOpaque(true); //allows us to set the background.
                        switch(status)
                        {
                            case notInstalled:
                                label.setForeground(Color.orange);
                                break;
                            case latestVersionInCache:
                                label.setForeground(Color.green);
                                break;
                            case nonLatestVersionInCache:
                                label.setForeground(new Color(0x00aa00));
                                break;
                            case notInCache:
                                label.setForeground(Color.gray);
                                break;
                            default:
                                //label.setForeground(Color.BLUE);
                                break;
                        }
                        label.setBackground(Color.gray);
                        if(isSelected) label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.black));
                        //else label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.pink));
                        //if(cellHasFocus) label.setBorder(javax.swing.BorderFactory.createLineBorder(Color.pink));
                        return label;
                    }
                });*/
                /*if(agelist.getModel().getSize()>0) agelist.setSelectedIndex(0);
                */
            }
            
        });
        //m.msg("getgu done.");
    }
    public static void GetVersionListGui(String age)
    {
        //m.msg("updating version list.");
        final String age2 = age;
        if(age!=null)
        {
            deletebutton.setEnabled(true);
        }
        else
        {
            deletebutton.setEnabled(false);
        }
        final Vector<String> vers = uam.Uam.ageList.getAllVersionsOfAge(age);
        verlist.setModel(new javax.swing.ListModel() {
            public int getSize() {
                return vers.size();
            }
            public Object getElementAt(int index) {
                return vers.get(index);
            }
            public void addListDataListener(ListDataListener l) {}
            public void removeListDataListener(ListDataListener l) {}
        });
        mirlist.setModel(new javax.swing.DefaultListModel());
        /*verlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String ver = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                GetMirrorListGui(age2,ver);
            }
        });*/
        if(verlist.getModel().getSize()>0) verlist.setSelectedIndex(0);
    }
    public static void GetMirrorListGui(String age, String ver)
    {
        //m.msg("updating mirror list.");
        final String age2 = age;
        final String ver2 = ver;
        final Vector<String> mirs = uam.Uam.ageList.getAllUrlsOfAgeVersion(age, ver);
        mirlist.setModel(new javax.swing.ListModel() {
            public int getSize() {
                return mirs.size();
            }
            public Object getElementAt(int index) {
                return mirs.get(index);
            }
            public void addListDataListener(ListDataListener l) {}
            public void removeListDataListener(ListDataListener l) {}
        });
        /*mirlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String mir = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                OnMirrorSelected(age2,ver2,mir);
            }
        });*/
        if(mirlist.getModel().getSize()>0) mirlist.setSelectedIndex(0);
    }
    public static void OnMirrorSelected(String age, String ver, String mir)
    {
        if(mir!=null)
        {
            downloadbutton.setEnabled(true);
        }
        else
        {
            downloadbutton.setEnabled(false);
        }
        //do nothing
    }
    public static void PerformDeletionAction()
    {
        String potsfolder = shared.State.AllStates.getStateAsString("uamRoot");
        if(PerformDeletion(potsfolder))
        {
            //refresh
            RefreshInfo(potsfolder);
        }
        
    }
    public static boolean PerformDeletion(String potsfolder)
    {
        //m.msg("Deletions aren't supported yet.");
        //if(true)return;
        
        //ensure pots folder.
        if(!automation.detectinstallation.isFolderPots(potsfolder))
        {
            return false;
        }
        
        String age = (String)agelist.getSelectedValue();
        if(age==null)
        {
            m.msg("You must select an Age.");
            return false;
        }
        
        String deletable = Uam.ageList.getDeletable(age);
        if(deletable.equals("true"))
        {
            //for each .7z file from that Age, delete its entries in Pots.
            Vector<File> files = filesearcher.search.getAllFilesWithExtension(potsfolder+uam.Uam.ageArchivesFolder, false, ".7z");
            for(File f: files)
            {
                if(f.getName().startsWith(age+uam.Uam.versionSep))
                {
                    shared.sevenzip.delete(f.getAbsolutePath(), potsfolder);
                }
            }
        }
        else
        {
            m.msg("This Age is listed as not being safely deletable; performing upgrade only.");
        }
        
        
        return true;
        
    }
    public static boolean PerformDownload()
    {
        String potsfolder = shared.State.AllStates.getStateAsString("uamRoot");
        
        //ensure pots folder.
        if(!automation.detectinstallation.isFolderPots(potsfolder))
        {
            return false;
        }

        String age = (String)agelist.getSelectedValue();
        String ver = (String)verlist.getSelectedValue();
        String mir = (String)mirlist.getSelectedValue();
        if(age==null || ver==null || mir==null)
        {
            m.msg("You must select an Age, Version, and Mirror.");
            return false;
        }
        
        String archiveType = uam.Uam.ageList.getArchiveType(age, ver);
        if(!archiveType.equals("7z"))
        {
            m.err("This version is in a archive type not currently supported: "+archiveType);
            return false;
        }
        

        m.msg("Cleaning up any previous version...");
        if(!PerformDeletion(potsfolder)) return false;
        
        m.status("Checking to see if we already have this version in the cache...");
        if(FileUtils.Exists(potsfolder+Uam.ageArchivesFolder+age+Uam.versionSep+ver+".7z"))
        {
            m.status("Using cached version of Age: "+age+", Version: "+ver+" ...");
            
            //String hash = Uam.ageList.getWhirlpool(age, ver);
            String hash = Uam.ageList.getSha1(age, ver);
            uam.ThreadDownloadAndProcess.extractAge(age, ver, potsfolder, hash);

            
            
            /*//prepare output file.
            String outputfolder = potsfolder+Uam.ageArchivesFolder;
            FileUtils.CreateFolder(outputfolder);
            String outputfile = outputfolder+age+uam.Uam.versionSep+ver+".7z";


            //check integrity.
            m.status("Checking integrity...");
            byte[] hash = shared.CryptHashes.GetWhirlpool(outputfile);
            String hashstr = b.BytesToHexString(hash);
            boolean isgood = whirlpool.equals(hashstr);
            if(!isgood)
            {
                m.err("Bad file integrity. The Age downloaded wasn't what was expected, perhaps because the version on the server is corrupted.");
                FileUtils.DeleteFile(outputfile);
                return false;
            }
            m.status("File integrity is good!");
            
            //extract.
            shared.sevenzip.extract(outputfile, potsfolder);
            
            //callback
            //callback.callback(null);
            m.status("Age installed!");

            RefreshInfo(potsfolder);*/
        }
        else
        {
            m.msg("Attempting to download Age: "+age+", Version: "+ver+", Mirror: "+mir);
            uam.Uam.DownloadAge7Zip(age,ver,mir,potsfolder);
        }

        //refresh
        //RefreshInfo(potsfolder);
        return true;
    }
}
