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

/**
 *
 * @author user
 */
public class Main extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    static Gui gui;
    
    public static void main(String[] args)
    {
        //select whether to match the native widgets or use the Swing appearance.
        try
        {
            //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
            //javax.swing.UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
            //javax.swing.UIManager.setLookAndFeel( sun.java.swing.plaf.gtk.GTKLooktAndFeel());
            //javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
            javax.swing.UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
            //javax.swing.JDialog j = new javax.swing.JDialog();
            //j.
        }
        catch(Exception e){}

        //Run the main form.
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                gui = new Gui();
                //java.net.URL url = this.getClass().getResource("Pterosaur2b4-16.png");
                //javax.swing.ImageIcon image = new javax.swing.ImageIcon(url,"");
                //java.awt.Image img = image.getImage();
                java.awt.Image img = shared.GetResource.getResourceAsImage("/gui/Pterosaur2b4-16.png");
                gui.setIconImage(img);
                gui.setVisible(true);
            }
        });
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
