/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import java.io.File;
import java.util.Vector;
import uru.vault.*;
import shared.*;

public class vaultAutomation
{
    public static String xmlize(String s)
    {
        String result = s==null?"":s;
        result = result.replace("&", "&amp;");
        result = result.replace("<", "&lt;");
        result = result.replace(">", "&gt;");
        result = result.replace("\"", "&quot;");
        result = result.replace("'", "&apos;");
        return result;
    }
    public static void saveImages(String infolder, String outfolder)
    {
        Nodes nodes = readFolder(infolder);
        nodes.sortByCreationDate();

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        xml.append("<?xml-stylesheet type='text/xsl' href='memories.xsl'?>");
        xml.append("<memories>");
        
        for(Node n: nodes.nodes)
        {
            if(n.type==nodetype.ImageNode)
            {
                byte[] data = n.ImageNode_GetImageData();
                String sha1 = b.BytesToHexString(shared.CryptHashes.GetHash(data, shared.CryptHashes.Hashtype.sha1));
                String filename = sha1+".jpg";
                FileUtils.WriteFile(outfolder+"/"+filename, data);
                xml.append("<imagenode>");
                xml.append("<creationtime>"+xmlize(n.crt_time.toString())+"</creationtime>");
                xml.append("<owner>"+xmlize(nodes.getAvatarName(n.owner))+"</owner>");
                xml.append("<agename>"+xmlize(n.ImageNode_GetAgeName())+"</agename>");
                xml.append("<caption>"+xmlize(n.ImageNode_GetCaption())+"</caption>");
                xml.append("<imagesha1>"+sha1+"</imagesha1>");
                xml.append("</imagenode>");
            }
            else if(n.type==nodetype.TextNoteNode)
            {
                xml.append("<textnotenode>");
                xml.append("<creationtime>"+xmlize(n.crt_time.toString())+"</creationtime>");
                xml.append("<owner>"+xmlize(nodes.getAvatarName(n.owner))+"</owner>");
                xml.append("<agename>"+xmlize(n.ImageNode_GetAgeName())+"</agename>");
                xml.append("<title>"+xmlize(n.TextNoteNode_GetTitle())+"</title>");
                xml.append("<text>"+xmlize(n.TextNoteNode_GetText())+"</text>");
                xml.append("</textnotenode>");
                
            }
            else if(n.type==nodetype.MarkerListNode)
            {
                xml.append("<markerlistnode>");
                xml.append("<creationtime>"+xmlize(n.crt_time.toString())+"</creationtime>");
                xml.append("<owner>"+xmlize(nodes.getAvatarName(n.owner))+"</owner>");
                xml.append("<agename>"+xmlize(n.ImageNode_GetAgeName())+"</agename>");
                xml.append("<gamename>"+xmlize(n.xu20.toString())+"</gamename>");
                for(Node mn: nodes.getMarkers(n.owner, n.age_name.toString(), n.blob1))
                {
                    xml.append("<marker>");
                    Flt f16 = Flt.createFromData(mn.xu16);
                    Flt f17 = Flt.createFromData(mn.xu17);
                    Flt f18 = Flt.createFromData(mn.xu18);
                    xml.append("<text>"+mn.xu28.toString()+"</text>");
                    xml.append("<x>"+f16.toString()+"</x>");
                    xml.append("<y>"+f16.toString()+"</y>");
                    xml.append("<z>"+f16.toString()+"</z>");
                    xml.append("</marker>");
                }
                xml.append("</markerlistnode>");
            }
        }
        
        xml.append("</memories>");
        FileUtils.WriteFile(outfolder+"/memories.xml", b.StringToBytes(xml.toString()));
        FileUtils.WriteFile(outfolder+"/memories.xsl", shared.GetResource.getResourceAsBytes("/files/memories.xsl"));
        
        m.status("Finished creating Memories files!");
    }
    
    /*public static void saveImages2(String infolder, String outfolder)
    {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Memories</title>");
        html.append("<style type='text/css'>");
        html.append("div.textnotenode, div.imagenode, div.markerlistnode {");
        html.append("color: green;");
        html.append("border: thin solid blue;");
        html.append("margin: 16px 4px 16px 4px;");
        html.append("} </style>");
        html.append("</head><body>");
        
        Nodes nodes = readFolder(infolder);
        nodes.sortByCreationDate();

        for(Node n: nodes.nodes)
        {
            if(n.type==nodetype.ImageNode)
            {
                byte[] data = n.ImageNode_GetImageData();
                String sha1 = b.BytesToHexString(shared.CryptHashes.GetHash(data, shared.CryptHashes.Hashtype.sha1));
                //String filename = Integer.toString(i)+".jpg";
                String filename = sha1+".jpg";
                //html.append("Image:<a href='"+filename+"'>here</a><br/>");
                FileUtils.WriteFile(outfolder+"/"+filename, data);
                //String outfilename = outfolder+"/"+Sanitise.SanitiseFilename(image.ImageNode_GetAgeName()+"-("+Integer.toString(i)+")-"+image.ImageNode_GetCaption())+".jpg";
                //i++;
                //html.append("Agename:"+n.ImageNode_GetAgeName()+"<br/>");
                //html.append("Caption:"+n.ImageNode_GetCaption()+"<br/>");
                //html.append("Owner:"+nodes.getAvatarName(n.owner)+"<br/>");
                //html.append("Date:"+n.crt_time.toString()+"<br/>");
                //html.append("<img src='"+filename+"'/><br/>");
                html.append("<div class='imagenode'>");
                html.append("<b>"+htmlize(n.crt_time.toString())+"</b><br/>");
                html.append(htmlize(nodes.getAvatarName(n.owner))+", "+htmlize(n.ImageNode_GetAgeName())+"<br/>");
                html.append(htmlize(n.ImageNode_GetCaption())+"<br/>");
                html.append("<img src='"+filename+"'/><br/>");
                html.append("</div>\n");
            }
            else if(n.type==nodetype.TextNoteNode)
            {
                //html.append("Agename:"+n.TextNoteNode_GetAgename()+"<br/>");
                //html.append("Title:"+n.TextNoteNode_GetTitle()+"<br/>");
                //html.append("Text:"+n.TextNoteNode_GetText()+"<br/>");
                //html.append("Owner:"+nodes.getAvatarName(n.owner)+"<br/>");
                //html.append("Date:"+n.crt_time.toString()+"<br/>");
                html.append("<div class='textnotenode'>");
                html.append("<b>"+htmlize(n.crt_time.toString())+"</b><br/>");
                html.append(htmlize(nodes.getAvatarName(n.owner))+", "+htmlize(n.TextNoteNode_GetAgename())+"<br/>");
                html.append(htmlize(n.TextNoteNode_GetTitle())+"<br/>");
                html.append("<pre>"+htmlize(n.TextNoteNode_GetText())+"</pre><br/>");
                html.append("</div>\n");
                
            }
            else if(n.type==nodetype.MarkerListNode)
            {
                html.append("<div class='markerlistnode'>");
                html.append("<b>"+htmlize(n.crt_time.toString())+"</b><br/>");
                html.append(htmlize(nodes.getAvatarName(n.owner))+", "+htmlize(n.age_name.toString())+"<br/>");
                html.append(htmlize(n.xu20.toString())+"<br/>");
                html.append("<pre>");
                for(Node mn: nodes.getMarkers(n.owner, n.age_name.toString(), n.blob1))
                {
                    Flt f16 = Flt.createFromData(mn.xu16);
                    Flt f17 = Flt.createFromData(mn.xu17);
                    Flt f18 = Flt.createFromData(mn.xu18);
                    html.append(mn.xu28.toString()+" ("+f16.toString()+","+f17.toString()+","+f18.toString()+")"+"<br/>");
                }
                html.append("</pre>");
                html.append("</div>\n");
            }
        }
        
        html.append("</body></html>");
        //m.msg(html.toString());
        FileUtils.WriteFile(outfolder+"/index.htm", b.StringToBytes(html.toString()));
    }*/
    /*public static String htmlize(String text)
    {
        String result = text;
        if(result==null) result = "";
        //String result = "<pre>"+text.replace("<", "&lt;")+"</pre>";
        result = result.replace("<", "&lt;");
        return result;
    }*/
    public static Nodes readFolder(String infolder)
    {
        //Vector<vfile> vfiles = new Vector();
        Nodes result = new Nodes();
        
        File folder = new File(infolder);
        for(File child: folder.listFiles())
        {
            if(child.getName().endsWith(".v"))
            {
                vfile vf = vfile.createFromFilename(child.getAbsolutePath());
                //vfiles.add(vf);
                result.add(vf.node);
            }
        }
        //return vfiles;
        return result;
    }
    
    /*public static <T> Vector<T> findRecordsOfType(Vector<vfile> vfiles, Class<T> cls)
    {
        Vector<T> result = new Vector();
        
        for(vfile vf: vfiles)
        {
            T t = vf.node.castTo(cls);
            if(t!=null) result.add(t);
        }
        
        return result;
    }*/
}
