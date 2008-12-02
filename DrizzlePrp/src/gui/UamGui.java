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

public class UamGui
{
    public static JList agelist;
    public static JList verlist;
    public static JList mirlist;
    public static gui.Gui gui;
    
    public static void GetAgeListGui(String server)
    {
        //final Vector<String> ages = uam.Uam.GetAgeList(server,gui,new shared.delegate() {
        //uam.Uam.GetAgeList(server,gui,new shared.delegate() {
        uam.ThreadDownloadAndProcess.downloadConfig(server, new shared.delegate(){
            public void callback(Object arg) {
                //list Ages...
                //return ageList.getAllAgeNames();
                uam.UamConfig config = (uam.UamConfig)arg;
                uam.Uam.ageList = config;
                final Vector<String> ages = uam.Uam.ageList.getAllAgeNames();
                agelist.setModel(new javax.swing.ListModel() {
                    public int getSize() {
                        return ages.size();
                    }
                    public Object getElementAt(int index) {
                        return ages.get(index);
                    }
                    public void addListDataListener(ListDataListener l) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                    public void removeListDataListener(ListDataListener l) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                verlist.setModel(new javax.swing.DefaultListModel());
                mirlist.setModel(new javax.swing.DefaultListModel());
                agelist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        String age = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                        GetVersionListGui(age);
                    }
                });
                agelist.setCellRenderer(new javax.swing.ListCellRenderer() {
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        javax.swing.JLabel label = new javax.swing.JLabel("hi");
                        //label.getGraphics().setColor(java.awt.Color.RED);
                        //label.getGraphics().fillRect(0, 0, label, index)
                        label.setOpaque(true); //allows us to set the background.
                        label.setForeground(java.awt.Color.BLUE);
                        label.setBackground(java.awt.Color.GREEN);
                        label.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                        return label;
                    }
                });
                if(agelist.getModel().getSize()>0) agelist.setSelectedIndex(0);
            }
        });
    }
    public static void GetVersionListGui(String age)
    {
        final String age2 = age;
        final Vector<String> vers = uam.Uam.ageList.getAllVersionsOfAge(age);
        verlist.setModel(new javax.swing.ListModel() {
            public int getSize() {
                return vers.size();
            }
            public Object getElementAt(int index) {
                return vers.get(index);
            }
            public void addListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            public void removeListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        mirlist.setModel(new javax.swing.DefaultListModel());
        verlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String ver = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                GetMirrorListGui(age2,ver);
            }
        });
        if(verlist.getModel().getSize()>0) verlist.setSelectedIndex(0);
    }
    public static void GetMirrorListGui(String age, String ver)
    {
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
            public void addListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            public void removeListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        mirlist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String mir = (String)((javax.swing.JList)e.getSource()).getSelectedValue();
                OnMirrorSelected(age2,ver2,mir);
            }
        });
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
        
        m.msg("Cleaning up any previous version...");
        PerformDeletion();
        
        m.msg("Attempting to download Age: "+age+", Version: "+ver+", Mirror: "+mir);
        
        String potsfolder = shared.State.AllStates.getStateAsString("uamRoot");
        uam.Uam.DownloadAge7Zip(age,ver,mir,potsfolder);
    }
}
