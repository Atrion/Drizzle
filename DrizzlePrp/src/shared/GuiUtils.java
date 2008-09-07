/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import javax.swing.text.JTextComponent;
import javax.swing.JFileChooser;

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

}
