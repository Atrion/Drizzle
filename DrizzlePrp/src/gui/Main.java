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

public class Main extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    static Gui gui;
    static String javaversion = "";
    static double javaversion2 = 0.0;
    static String os = "";
    static String osversion = "";
    static double osversion2 = 0.0;
    static long maxmemory = 0;
    
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

            if(true)
            {
                //todo: close stream.
                //java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, shared.GetResource.getResourceAsStream("/files/LiberationSans-Regular.ttf"));
                //java.awt.Font font2 = font.deriveFont(12.0f);
                //javax.swing.plaf.FontUIResource f = new javax.swing.plaf.FontUIResource(font2);

                javax.swing.plaf.FontUIResource plain = new javax.swing.plaf.FontUIResource("Lucida Sans",java.awt.Font.PLAIN,12);
                javax.swing.plaf.FontUIResource fixed = new javax.swing.plaf.FontUIResource("Lucida Sans Typewriter",java.awt.Font.PLAIN,12);

                //Object[] objs = new Object[]{"Lucida",java.awt.Font.PLAIN,12};
                //javax.swing.UIDefaults.ProxyLazyValue f2 = new javax.swing.UIDefaults.ProxyLazyValue("javax.swing.plaf.FontUIResource",null,objs);

                //java.util.Enumeration keys = javax.swing.UIManager.getDefaults().keys();
                //while (keys.hasMoreElements())
                //{
                //    Object key = keys.nextElement();
                //    Object value = javax.swing.UIManager.get(key);
                //    if (value instanceof javax.swing.plaf.FontUIResource)
                //    {
                //        javax.swing.UIManager.put (key, f);
                //    }
                //}

                UIManager.put("Button.font", plain);
                UIManager.put("ToggleButton.font", plain);
                UIManager.put("RadioButton.font", plain);
                UIManager.put("CheckBox.font", plain);
                UIManager.put("ColorChooser.font", plain);
                UIManager.put("ComboBox.font", plain);
                UIManager.put("Label.font", plain);
                UIManager.put("MenuBar.font", plain);
                UIManager.put("MenuItem.font", plain);
                UIManager.put("RadioButtonMenuItem.font", plain);
                UIManager.put("CheckBoxMenuItem.font", plain);
                UIManager.put("Menu.font", plain);
                UIManager.put("PopupMenu.font", plain);
                UIManager.put("OptionPane.font", plain);
                UIManager.put("Panel.font", plain);
                UIManager.put("ProgressBar.font", plain);
                UIManager.put("ScrollPane.font", plain);
                UIManager.put("Viewport.font", plain);
                UIManager.put("TabbedPane.font", plain);
                UIManager.put("Table.font", plain);
                UIManager.put("TableHeader.font", plain);
                UIManager.put("TitledBorder.font", plain);
                UIManager.put("ToolBar.font", plain);
                UIManager.put("ToolTip.font", plain);
                UIManager.put("Tree.font", plain);
        		UIManager.put("TextField.font", plain);
                UIManager.put("PasswordField.font", plain);
                UIManager.put("TextArea.font", fixed);
                UIManager.put("TextPane.font", plain);
                UIManager.put("EditorPane.font", plain);

            }
        }
        catch(Exception e){
            int dummy=0;
        }

        //Run the main form.
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
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
                    maxmemory = Runtime.getRuntime().maxMemory();
                }catch(Exception e){}
                try{
                    os = System.getProperty("os.name");
                }catch(Exception e){}
                try{
                    osversion = System.getProperty("os.version");
                    osversion2 = Double.parseDouble(osversion);
                }catch(Exception e){}
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
