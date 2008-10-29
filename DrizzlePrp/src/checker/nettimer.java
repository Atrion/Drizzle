/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checker;

import java.net.URL;
//import java.net.HttpURLConnection;
//import sun.net.www.protocol.http.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import shared.*;

import java.lang.Runnable;
import java.lang.Thread;

public class nettimer {

    public static void timer(String address, double interval, String search)
    {
        runner r = new runner(interval,address,search);
        r.start();
        //we lose the reference, but since it's a thread, it doesn't get garbage collected.
    }
    public static class runner extends Thread
    {
        double interval;
        String address;
        String search;
        //boolean stop = false;
        
        public runner(double interval, String address, String search)
        {
            this.interval = interval;
            this.address = address;
            this.search = search;
        }
        
        public void run()
        {
            while(true)
            {
                check(address,search);
                long msToSleep = (long)(interval*1000);
                try
                {
                    Thread.sleep(msToSleep);
                }
                catch(Exception e)
                {
                    //interrupted.
                }
            }
        }
    }
    static void check(String address, String search)
    {
        try {
            m.msg("Checking...");
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
            String result = b.BytesToString(bytes);
            if (result.indexOf(search) != -1) {
                GuiUtils.showTrayIcon("/gui/Pterosaur2b4-16.png");
                GuiUtils.DisplayTrayMessage("Found", "The query '"+search+"' has been found.");
            }
            int dummy = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
