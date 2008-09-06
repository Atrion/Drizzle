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

package shared;

//import gui.Main;
//import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class m
{
    private static JTextArea _outputTextArea; //you must set this from the GUI.
    
    public static void setJTextArea(JTextArea newJTextArea)
    {
        _outputTextArea = newJTextArea;
    }
    
    private static void message(String s)
    {
        if(_outputTextArea!=null)
        {
            _outputTextArea.append(s+"\n");
        }
        else
        {
            String errormsg = "Programming Error: shared.m messages are being generated before the output TextArea is set, or there is no output TextArea.\nThe error is: " + s;
            System.out.println(errormsg);
            //javax.swing.JFrame frame = new javax.swing.JFrame();
            javax.swing.JOptionPane.showMessageDialog(null,errormsg);
            
        }
        
        String[] trapmessages = {"compile not implemented"};
        for(int i=0;i<trapmessages.length;i++)
        {
            if(s.toLowerCase().startsWith(trapmessages[i].toLowerCase()))
            {
                int trapbreak = 0;
            }
        }
    }
    
    public static void msg(String s)
    {
        //Main.message(s);
        message(s);
    }

    public static void err(String s)
    {
        //Main.message(s);
        message("Error: "+s);
        //throw new Exception(s);
    }
    
    public static void warn(String s)
    {
        message("Warning: "+s);
    }
    
}
