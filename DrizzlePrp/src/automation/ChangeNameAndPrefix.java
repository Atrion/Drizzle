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
    public static void ChangeName(String inputfilename, String outputfolder, String newname)
    {
        m.msg("Not currently changing Python refs.");

        //read old one:
        prpfile prp = prpfile.createFromFile(inputfilename, true);

        //calculate new values:
        String oldname = prp.header.agename.toString();
        Urustring newnam = Urustring.createFromString(newname);

        //change header entries:
        prp.header.agename = newnam;

        //save it!
        String outputfilename = outputfolder + "/" + prp.header.agename.toString()+"_District_"+prp.header.pagename.toString()+".prp";
        prp.saveAsFile(outputfilename);

    }
    public static void ChangePrefix(String inputfilename, String outputfolder, String newprefix)
    {
        //read old one:
        prpfile prp = prpfile.createFromFile(inputfilename, false);

        //calculate new values:
        int newpre = Integer.parseInt(newprefix);
        Pageid oldpid = prp.header.pageid.deepClone();


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
        String outputfilename = outputfolder + "/" + prp.header.agename.toString()+"_District_"+prp.header.pagename.toString()+".prp";
        prp.saveAsFile(outputfilename);
    }
    public static void ChangePagename(String inputfilename, String outputfolder, String newname)
    {

        //read old one:
        prpfile prp = prpfile.createFromFile(inputfilename, true);

        prp.header.pagename = Urustring.createFromString(newname);

        //save it!
        String outputfilename = outputfolder + "/" + prp.header.agename.toString()+"_District_"+prp.header.pagename.toString()+".prp";
        prp.saveAsFile(outputfilename);

    }
}
