/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wikispider;

import shared.m;
import shared.b;
import shared.FileUtils;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;
//import java.util.HashSet;
import java.util.HashMap;

public class wikispider
{
    public static void start(String startingUrl, String outputFolder)
    {
        //HashSet<Link> alllinks = new HashSet();
        //alllinks.add(Link.create("Main_Page", LinkType.WikiPage));
        AllPages allpages = new AllPages();
        allpages.start(startingUrl, outputFolder);
        //String sourceurl = startingUrl + "/index.php?title=Main_Page&action=raw";
        
        //byte[] data = geturl(sourceurl);
        //String text = b.BytesToString(data);
        //Vector<String> links = getwikilinks(text);
        //Vector<Link> stripped = striplinks(links);
        m.msg("Done!");
        int dummy = 0;
    }
    
    /*public static Vector<Link> striplinks(Vector<String> links)
    {
        Vector<Link> result = new Vector();
        for(String link: links)
        {
            int ind = link.indexOf("|");
            if(ind!=-1) link = link.substring(0,ind);
            
            if(link.startsWith(" "))
            {
                int dummy=0;
            }
            
            int colind = link.indexOf(":");
            if(colind!=-1)
            {
                String domain = link.substring(0,colind);
                if(domain.equals("de"))
                {
                    Link a = Link.create(link, LinkType.WikiPage);
                    result.add(a);
                }
                else if(domain.equals("Image"))
                {
                    Link a = Link.create(link, LinkType.Image);
                    result.add(a);
                }
                else
                {
                    m.msg("Unhandled domain: "+link);
                }
            }
            else
            {
                Link a = Link.create(link, LinkType.WikiPage);
                result.add(a);
            }
            
        }
        return result;
    }*/
    
    public static Vector<String> getwikilinks(String source)
    {
        Vector<String> result = new Vector();
        int curpos = 0;
        while(true)
        {
            int ind = source.indexOf("[[",curpos);
            if(ind==-1)
            {
                break; //no more.
            }
            else
            {
                int endpos = source.indexOf("]]",ind);
                if(endpos==-1)
                {
                    m.msg("err, couldn't find ]].");
                    break;
                }
                else
                {
                    String link = source.substring(ind+2, endpos);
                    
                    int indbar = link.indexOf("|");
                    if(indbar!=-1) link = link.substring(0,indbar);

                    if(link.startsWith(" "))
                    {
                        int dummy=0;
                    }
                    
                    int indhash = link.indexOf("#");
                    if(indhash!=-1) link = link.substring(0,indhash);
                    
                    if(!link.equals(""))
                    {
                        result.add(link);
                    }
                    else
                    {
                        int dummy=0; //a #whatever link to within the same page probably.
                    }
                    curpos = endpos;
                }
            }
        }
        return result;
    }
    
    public static byte[] geturl(String address)
    {
        try {
            //m.msg("Checking...");
            URL url = new URL(address);
            Object content = url.getContent();
            InputStream in = new BufferedInputStream(url.openConnection().getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int read = in.read(buffer);
                if (read == -1) {
                    break;
                }
                out.write(buffer, 0, read);
            }
            in.close();
            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (Exception e) {
            //e.printStackTrace();
            m.warn("Unable to download: "+address);
        }
        return null;
    }
    
    public static enum LinkType
    {
        WikiPage,
        Image,
        Ignore,
        Unknown,
    }
    
    public static class AllPages
    {
        static final String rootname = "Main_Page";
        static final String outputExt = ".txt";
        
        //HashSet<Link> pages = new HashSet();
        HashMap<String, Link> pages = new HashMap();
        
        String startingUrl;
        String outputFolder;
        Link root;
    
        public void start(String startingUrl, String outputFolder)
        {
            this.startingUrl = startingUrl;
            this.outputFolder = outputFolder;
            root = this.add(rootname);
            root.downloadAndParse();
        }
        
        public Link add(String name)
        {
            Link l = pages.get(name);
            if(l!=null) return l;
            
            Link a = new Link(name);
            pages.put(name, a);
            /*for(Link l: pages)
            {
                if(l.equals(a)) return l;
            }
            pages.add(a);*/
            return a;
        }
        
        public class Link
        {
            String name;
            LinkType type;

            //only if it's a page type.
            boolean downloaded = false;
            boolean downloadedChildren = false;
            Vector<Link> links = new Vector();


            public Link(String name)
            {
                //Link result = new Link();
                this.name = name;
                
                //deal with wiki namespaces
                int colind = name.indexOf(":");
                String domain;
                if(colind!=-1)
                {
                    domain = name.substring(0,colind);
                }
                else
                {
                    domain = "";
                }
                type = GetLinkTypeFromDomain(domain);
            }
            public LinkType GetLinkTypeFromDomain(String domain)
            {
                //m.msg("domain="+domain);
                //String[] regulars = {"","de","fr","Es","Pl",};
                String[] ignores = {"Special"};
                String[] images = {"Image","File","Media"};
                /*for(String dom: regulars)
                {
                    if(domain.equals(dom))
                    {
                        return LinkType.WikiPage;
                    }
                }*/
                for(String dom: ignores)
                {
                    if(domain.equals(dom))
                    {
                        return LinkType.Ignore;
                    }
                }
                for(String dom: images)
                {
                    if(domain.equals(dom))
                    {
                        return LinkType.Image;
                    }
                }
                return LinkType.Unknown;
            }
            public void downloadAndParse()
            {
                if(downloaded) return;
                m.msg("Downloading: "+name);
                String safeinname = name.replace(" ", "_");
                String safeoutname = name.replace(" ", "_").replace(":", ";");
                String sourceurl = startingUrl + "/index.php?title="+safeinname+"&action=raw";
                String outputName = outputFolder+"/"+safeoutname;
                byte[] data;
                
                this.downloaded = true;
                
                switch(type)
                {
                    case Ignore:
                        break;
                    case Unknown:
                    case WikiPage:
                        data = wikispider.geturl(sourceurl);
                        if(data==null) return;
                        FileUtils.WriteFile(outputName+".txt", data, true);
                        String text = b.BytesToString(data);
                        Vector<String> strlinks = wikispider.getwikilinks(text);
                        for(String s: strlinks)
                        {
                            Link child = add(s);
                            links.add(child);
                        }
                        this.doAllChildren();
                        break;
                    case Image:
                        //if(true)break;
                        String pageurl=startingUrl + "/index.php?title="+safeinname;
                        data = wikispider.geturl(pageurl);
                        if(data==null) return;
                        xml x = new xml(data);
                        //String imgurl = x.getString("//div[@class='fullImageLink']/a/@href");
                        String imgurl = x.getString("//div[@id='file']/a/@href"); //also works.
                        imgurl = startingUrl+"/"+imgurl;
                        //The newer MediaWiki versions also let you use /Special:Filepath/Imagename.jpg  or /Special:Filepath?file=Imagename.jpg
                        byte[] data2 = wikispider.geturl(imgurl);
                        if(data==null) return;
                        FileUtils.WriteFile(outputName, data2, true);
                        break;
                }
                
            }
            public void doAllChildren()
            {
                for(Link l: links)
                {
                    l.downloadAndParse();
                }
                this.downloadedChildren = true;
            }
            public int hashCode()
            {
                return name.hashCode();
            }

            public boolean equals(Object o)
            {
                if(!(o instanceof Link)) return false;
                Link l2 = (Link)o;
                return this.name.equals(l2.name);
            }
        }
    }
}
