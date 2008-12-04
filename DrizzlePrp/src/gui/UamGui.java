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

public class UamGui
{
    public static JList agelist;
    public static JList verlist;
    public static JList mirlist;
    public static gui.Gui gui;
    
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
                        label.setForeground(Color.orange);
                        break;
                    case latestVersionInCache:
                        label.setForeground(Color.green);
                        break;
                    case nonLatestVersionInCache:
                        label.setForeground(Color.yellow);//new Color(0x00aa00));
                        break;
                    case notInCache:
                        label.setForeground(Color.black);
                        break;
                    default:
                        label.setForeground(Color.black);
                        break;
                }
                //label.setBackground(Color.lightGray);
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
        if(agelist.getModel().getSize()>0) agelist.setSelectedIndex(0);
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
        uam.ThreadDownloadAndProcess.downloadConfig(server, new shared.delegate(){
            public void callback(Object arg) {
                //list Ages...
                //return ageList.getAllAgeNames();
                uam.UamConfig config = (uam.UamConfig)arg;
                uam.Uam.ageList = config;
                
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
        
    }
    public static void GetVersionListGui(String age)
    {
        //m.msg("updating version list.");
        final String age2 = age;
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
        //do nothing
    }
    public static void PerformDeletion()
    {
        //m.msg("Deletions aren't supported yet.");
        //if(true)return;
        
        //ensure pots folder.
        String potsfolder = shared.State.AllStates.getStateAsString("uamRoot");
        if(!automation.detectinstallation.isFolderPots(potsfolder))
        {
            return;
        }
        String age = (String)agelist.getSelectedValue();
        if(age==null)
        {
            m.msg("You must select an Age.");
            return;
        }
        
        //for each .7z file from that Age, delete its entries in Pots.
        Vector<File> files = filesearcher.search.getAllFilesWithExtension(potsfolder+uam.Uam.ageArchivesFolder, false, ".7z");
        for(File f: files)
        {
            if(f.getName().startsWith(age+uam.Uam.versionSep))
            {
                shared.sevenzip.delete(f.getAbsolutePath(), potsfolder);
            }
        }
        
        //refresh
        RefreshInfo(potsfolder);
        
    }
    public static void PerformDownload()
    {
        String age = (String)agelist.getSelectedValue();
        String ver = (String)verlist.getSelectedValue();
        String mir = (String)mirlist.getSelectedValue();
        if(age==null || ver==null || mir==null)
        {
            m.msg("You must select an Age, Version, and Mirror.");
            return;
        }
        
        String archiveType = uam.Uam.ageList.getArchiveType(age, ver);
        if(!archiveType.equals("7z"))
        {
            m.err("This version is in a archive type not currently supported: "+archiveType);
            return;
        }
        
        m.msg("Cleaning up any previous version...");
        PerformDeletion();
        
        m.msg("Attempting to download Age: "+age+", Version: "+ver+", Mirror: "+mir);
        
        String potsfolder = shared.State.AllStates.getStateAsString("uamRoot");
        uam.Uam.DownloadAge7Zip(age,ver,mir,potsfolder);

        //refresh
        //RefreshInfo(potsfolder);
    }
}
