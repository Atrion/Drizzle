/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.b;
import shared.m;
import java.io.ByteArrayOutputStream;

public class Uam
{
    public static void GetAgeList(String server)
    {
        //GuiModal modal = new GuiModal(null,true);
        //modal.setVisible(true);
        //modal.showButDontBlock();
        
        //SevenZip.J7zip 
        
        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        //String file = "http://www.the-ancient-city.de/uru-ages/Abysos.rar";
        String file = "http://www.the-ancient-city.de/uru-ages/Dustin2.rar";
        ThreadDownloader td = new ThreadDownloader(file, baout);
        td.start();
        //td.join();
        
        if(true)return;
        
        
        //String statusfile = server+"/uam.status.txt";
        String statusfile = "http://www.the-ancient-city.de/uru-ages/Abysos.rar";
        byte[] data = uam.Downloader.DownloadFile(statusfile);
        String datastr = b.BytesToString(data);
        //m.msg(datastr);
    }
}
