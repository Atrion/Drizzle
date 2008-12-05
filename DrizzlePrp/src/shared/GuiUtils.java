/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import javax.swing.text.JTextComponent;
import javax.swing.JFileChooser;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.InputEvent;
import javax.swing.text.Keymap;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
//import javax.swing.text.Keymap;
import javax.swing.ActionMap;
import java.io.File;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import javax.swing.JOptionPane;

public class GuiUtils
{
    public static boolean onlyUseASingleJFileChooser = true;
    private static JFileChooser _fc;
    private static TrayIcon _trayicon;
    
    public static void DisplayTrayMessage(String caption, String message)
    {
        if(_trayicon==null)
        {
            m.err("You must have shown the tray icon first.");
        }
        else
        {
            _trayicon.displayMessage(caption, message, java.awt.TrayIcon.MessageType.NONE);
        }
    }
    
    public static void showTrayIcon(String imgpath)
    {
        try{
            if(_trayicon!=null)
            {
                //m.err("A tray icon has already been shown; doing nothing.");
                return;
            }
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            //java.awt.Image img = shared.GetResource.getResourceAsImage("/gui/Pterosaur2b4-16.png");
            java.awt.Image img = shared.GetResource.getResourceAsImage(imgpath);
            //java.awt.PopupMenu menu = new java.awt.PopupMenu();
            //menu.add(new java.awt.MenuItem("test"));
            //java.awt.TrayIcon ti = new java.awt.TrayIcon(img,"Drizzle",menu);
            java.awt.TrayIcon ti = new java.awt.TrayIcon(img);
            _trayicon = ti;
            ti.setImageAutoSize(true);
            tray.add(ti);
            //ti.displayMessage("caption", "message", java.awt.TrayIcon.MessageType.NONE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static JFileChooser getJFileChooser()
    {
        if(onlyUseASingleJFileChooser)
        {
            if(_fc==null) _fc = new JFileChooser();
            return _fc;
        }
        else
        {
            return new JFileChooser();
        }
    }
    
    public static void getStringFromUser(JTextComponent field, String message, String title)
    {
        String result = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        if(result!=null) field.setText(result);
    }
    public static void getUserSelectedFolder(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
        File cwd = new File(field.getText()).getParentFile();
        fc.setCurrentDirectory(cwd);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            field.setText(file);
        }
    }
    public static void getUserSelectedFile(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
        File cwd = new File(field.getText()).getParentFile();
        fc.setCurrentDirectory(cwd);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            field.setText(file);
        }
    }
    public static void getUserSelectedFileWithNoPath(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
        File cwd = new File(field.getText()).getParentFile();
        fc.setCurrentDirectory(cwd);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getName();
            field.setText(file);
        }
    }
    public static void getUserSelectedFileOrFolder(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
        File cwd = new File(field.getText()).getParentFile();
        fc.setCurrentDirectory(cwd);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            field.setText(file);
        }
    }
    
    public static void SetKeymaps()
    {
        SetKeymap(new JTextField());
        SetKeymap(new JTextArea());
        SetKeymap(new JFormattedTextField()); //todo: is this necessary?
    }
    
    public static void SetKeymap(JTextComponent componentExample)
    {
        Keymap keymap = componentExample.getKeymap();
        ActionMap actionmap = componentExample.getActionMap();
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),new DefaultEditorKit.CopyAction());
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK),new DefaultEditorKit.PasteAction());
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK),new DefaultEditorKit.CutAction());
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, InputEvent.SHIFT_DOWN_MASK),new DefaultEditorKit.PasteAction());
        keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK),actionmap.get(DefaultEditorKit.selectAllAction));
        
    }
}
