/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mystProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

public class CustomSiteIndex implements ICustomSite
{

    public void handle(packageInfo p, String domain, String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException
    {
        if(target.toLowerCase().equals("/startpages.html"))
        {
            StringBuilder s = new StringBuilder();
            s.append("<html><body>");
            for(packageInfo pack: proxySettings.packages.packages)
            {
                s.append(pack.name);
                for(String startpage: pack.startPages)
                {
                    s.append("<a href='"+startpage+"'>"+startpage+"</a>");
                    
                }
            }
            s.append("</body></html>");
            response.getWriter().print(s.toString());
            fileHandler.handleDone(request, response);
        }
        else
        {
            //do the normal handling.
            fileHandler.handle3(p, domain, target, request, response, dispatch);
            //fileHandler.handle(null, target, request, response, dispatch);
        }
    }

}
