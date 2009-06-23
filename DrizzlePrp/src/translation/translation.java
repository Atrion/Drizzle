/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package translation;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

import javax.swing.JFrame;
import java.util.Vector;
import java.awt.Component;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import shared.m;
import shared.b;
import shared.FileUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.io.File;
import java.util.LinkedHashSet;

/*
 * General idea is to do nothing unless changing to a non-default language, in which case:
 * -load and parse the new language file.
 * -store the english version of each gui component. (only the first time the language is changed)
 * -set the gui elements
 * -intercept m calls
*/

public class translation
{
    private static final String sep = "{->}";
    private static final String defaultlanguage = "en";
    private static String curlanguage = defaultlanguage;

    //missing translation recorder
    public static boolean doRecordMissingTranslations = false;
    private static LinkedHashSet<String> missingTranslations; //linked to maintain order
    private static HashSet<String> allstrings;

    //string->string  translations
    static Map<String,String> translator;// = new HashMap();

    //auto-registered components tied to their string.
    static Map<Object,String> defaults;
    
    //registered components tied to their resource-name:
    private static HashMap<JTextComponent, String> jTextComponents = new HashMap();

    public static void registerResourceString(String path, JTextComponent textbox)
    {
        jTextComponents.put(textbox, path);
    }

    public static String getCurLanguage()
    {
        return curlanguage;
    }
    public static String getDefaultLanguage()
    {
        return defaultlanguage;
    }
    public static void test()
    {
        //saveCurrentStrings();

        //setLanguage("de");
    }
    public static void testLanguage(String language)
    {
        loadLanguage(language);

        HashSet<String> strings = getAllStrings();

        for(String key: translator.keySet())
        {
            if(!strings.contains(key))
            {
                m.warn("Translation key is not in gui nor string literal: ",key);
            }
        }
    }
    public static void setLanguage(String language)
    {
        String prevlanguage = curlanguage;
        curlanguage = language;

        //things that are only done when going to the non-default language. (we could just do it, but it's less efficient for a lot of things.)
        if(!prevlanguage.equals(language))
        {
            //load string->string translations from file.
            loadLanguage(language);

            //auto-find gui elements, setting new values, and saving the defaults if we haven't already.
            boolean storeDefaults = (defaults==null);
            if(storeDefaults) defaults = java.util.Collections.synchronizedMap(new IdentityHashMap());
            findLabels(gui.Main.gui, storeDefaults, true);

            //enable translations.
            m.state.curstate.translate = true;
            shared.GetResource.enableTranslations = true;

            m.msg("Setting language to ",language);
        }

        //set manually registered components from their resource.
        for(JTextComponent textbox: jTextComponents.keySet())
        {
            textbox.setText(shared.GetResource.getResourceAsString(jTextComponents.get(textbox)));

            //hack to scroll the help window down.
            if(textbox instanceof JTextArea)
            {
                final JTextComponent textbox2 = textbox;
                javax.swing.SwingUtilities.invokeLater(new java.lang.Runnable() {

                    public void run() {
                        ((JTextArea)textbox2).scrollRectToVisible(new java.awt.Rectangle(0,0,1,1));
                    }
                });
            }
        }

    }

    //loads the string translator from file.
    private static void loadLanguage(String language)
    {
        //'language' is the 2-letter language code.
        boolean asresource = false;

        translator = java.util.Collections.synchronizedMap(new HashMap());
        //defaults = java.util.Collections.synchronizedMap(new HashMap());

        String langFile;
        if(asresource)
        {
            langFile = shared.GetResource.getResourceAsString("/lang/"+language+".txt");
        }
        else
        {
            langFile = FileUtils.ReadFileAsString(FileUtils.GetInitialWorkingDirectory()+"/"+language+".txt");
        }

        langFile = langFile.replace("\r", ""); //hack for windows eol
        String[] lines = langFile.split("\n");
        for(int i=0;i<lines.length;i++)
        {
            String line = lines[i];
            int index = line.indexOf(sep);
            String left = line.substring(0, index);
            String right = line.substring(index+sep.length());
            if(left.length()==0 || right.length()==0) throw new shared.uncaughtexception("Invalid language file at line "+Integer.toString(i+1));

            translator.put(left, right);
        }
    }
    public static void recordMissingTranslations()
    {
        doRecordMissingTranslations = true;
        missingTranslations = new LinkedHashSet();
        allstrings = getAllStrings();
    }
    public static void saveMissingTranslations()
    {
        StringBuilder result = new StringBuilder();
        for(String str: missingTranslations)
        {
            if(allstrings.contains(str))
            {
                result.append(str+sep+"\n");
            }
        }
        byte[] result2 = b.StringToBytes(result.toString());
        FileUtils.WriteFile(FileUtils.GetInitialWorkingDirectory()+"/DrizzleNeededTranslations.txt", result2);
    }
    public static String translate(String str)
    {
        String result = null;
        if(translator!=null) result = translator.get(str);
        if(result==null)
        {
            //if(shared.State.AllStates.getStateAsBoolean("recordtrans"))
            if(doRecordMissingTranslations)
            {
                //FileUtils.AppendText(FileUtils.GetInitialWorkingDirectory()+"DrizzleNeededTranslations.txt", str+sep+str+"\n");
                missingTranslations.add(str);
            }
            //if not in translator, leave it as is.
            result = str;
        }

        return result;
    }
    public static HashSet<String> getAllStrings()
    {
        //get the gui strings.
        if(defaults==null)
        {
            defaults = java.util.Collections.synchronizedMap(new IdentityHashMap());
            JFrame g = gui.Main.gui;
            //Vector<JLabel> labels = new Vector();
            findLabels(g,true,false);
        }

        HashSet<String> vals = new HashSet();
        for(String val: defaults.values())
        {
            vals.add(val);
        }

        //get the string literals
        String[] ignorePackages = {"/org/bouncycastle","/org/apache","/org/mortbay","/javax","/ie/wombat","/SevenZip","/automation/fileLists"};
        for(String res: shared.GetResource.listAllResources())
        {
            boolean skip = false;
            for(String ig: ignorePackages)
            {
                if(res.startsWith(ig))
                {
                    skip = true;
                }
            }
            if(skip) continue;
            if(res.endsWith(".class"))
            {
                shared.IBytestream c = shared.ByteArrayBytestream.createFromByteArray(shared.GetResource.getResourceAsByteArray(res));
                classfiles.classfile cf = new classfiles.classfile(c);
                for(String s: cf.getAllConstantStrings())
                {
                    //m.msg(s);
                    if(s.contains("\n") || s.contains("\r") ||s.contains(sep))
                    {
                        //m.warn("String literal contains newline or separator: ",s);
                        m.warn("Ignoring: ",s);
                    }
                    else
                    {
                        vals.add(s);
                    }
                }
            }
        }

        return vals;
    }
    public static void saveCurrentStrings()
    {
        //translator = java.util.Collections.synchronizedMap(new HashMap());
        HashSet<String> vals = getAllStrings();

        StringBuilder result = new StringBuilder();
        for(String val: vals)
        {
            //String val = defaults.get(key);
            if(val.equals("")) continue;
            result.append(/*"//"+*/val+sep+val+"\n");//+" : "+val+"\n");
        }
        String result2 = result.toString();
        FileUtils.WriteFile(FileUtils.GetInitialWorkingDirectory()+"/en.txt", b.StringToBytes(result2));
    }

    private static void findLabels(Container curcomp, boolean storeDefaults, boolean setNewValues)
    {
        for(Component c: curcomp.getComponents())
        {
            //recurse:
            if (c instanceof Container)
            {
                findLabels((Container)c,storeDefaults,setNewValues);
            }

            //Get text from component if applicable.
            String text = null;
            if (c instanceof JLabel)
            {
                JLabel label = (JLabel)c;
                if(storeDefaults) defaults.put(c, label.getText());
                if(setNewValues) label.setText(translate(defaults.get(c)));
                //translator.put(text, text);
                //m.msg("jlabel found! "+text);
            }
            if (c instanceof JButton)
            {
                JButton button = (JButton)c;
                if(storeDefaults) defaults.put(c, button.getText());
                if(setNewValues) button.setText(translate(defaults.get(c)));
                //translator.put(text, text);
                //m.msg("jbutton found! "+text);
            }
            if (c instanceof JPanel)
            {
                JPanel panel = (JPanel)c;
                Border border = panel.getBorder();
                if(border instanceof TitledBorder)
                {
                    TitledBorder tborder = (TitledBorder)border;
                    if(storeDefaults) defaults.put(c, tborder.getTitle());
                    if(setNewValues) tborder.setTitle(translate(defaults.get(c)));
                    //translator.put(text, text);
                    //m.msg("titled border found! "+text);
                }
            }
            if (c instanceof JCheckBox)
            {
                JCheckBox checkbox = (JCheckBox)c;
                if(storeDefaults) defaults.put(c, checkbox.getText());
                if(setNewValues) checkbox.setText(translate(defaults.get(c)));
                //translator.put(text, text);
                //m.msg("checkbox: "+text);
            }
            if (c instanceof JRadioButton)
            {
                JRadioButton radiobutton = (JRadioButton)c;
                if(storeDefaults) defaults.put(c, radiobutton.getText());
                if(setNewValues) radiobutton.setText(translate(defaults.get(c)));
                //translator.put(text, text);
                //m.msg("radiobutton: "+text);
            }

            //add text to translation strings.
            /*if(text!=null)
            {
                //translator.put(text, text);
                if(storeDefaults)
                {
                    defaults.put(c, text);
                    //translator.put(text,text);
                }
            }*/
        }
    }
}
