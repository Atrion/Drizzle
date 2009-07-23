/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import uru.moulprp.prpfile;
import uru.moulprp.Pageid;
import uru.moulprp.Urustring;
import uru.moulprp.Uruobjectdesc;
import shared.m;

public class ChangeNameAndPrefix
{
    public static void ChangeNameAndPrefix(String inputfilename, String outputfolder, String newname, String newprefix)
    {
        m.msg("Not currently changing Python refs.");

        //read old one:
        prpfile prp = prpfile.createFromFile(inputfilename, false);

        //calculate new values:
        int newpre = Integer.parseInt(newprefix);
        String oldname = prp.header.agename.toString();
        String distname = prp.header.pagename.toString();
        Urustring newnam = Urustring.createFromString(newname);
        Pageid oldpid = prp.header.pageid.deepClone();

        //change header entries:
        prp.header.agename = newnam;
        prp.header.pageid.setSequencePrefix(newpre);

        //change uruobjectdesc entries:
        for(Uruobjectdesc desc: shared.FindAllDescendants.FindAllDescendantsByClass(Uruobjectdesc.class, prp))
        {
            if(desc.pageid.getSequencePrefix()==oldpid.getSequencePrefix())
            {
                desc.pageid.setSequencePrefix(newpre);
            }
        }

        //save it!
        String outputfilename = outputfolder + "/" + newnam+"_District_"+distname+".prp";
        prp.saveAsFile(outputfilename);

    }
}
