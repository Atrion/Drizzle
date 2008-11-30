/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.b;
import shared.m;

public class Uam
{
    public static void GetAgeList(String server)
    {
        //String statusfile = server+"/uam.status.txt";
        String statusfile = "http://www.the-ancient-city.de/uru-ages/Abysos.rar";
        byte[] data = uam.Downloader.DownloadFile(statusfile);
        String datastr = b.BytesToString(data);
        //m.msg(datastr);
    }
}
