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

public class GuiUtils
{
    public static boolean onlyUseASingleJFileChooser = true;
    private static JFileChooser _fc;
    
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
    
    public static void getUserSelectedFolder(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
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
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            field.setText(file);
        }
    }
    public static void getUserSelectedFileOrFolder(JTextComponent field)
    {
        JFileChooser fc = getJFileChooser();
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
