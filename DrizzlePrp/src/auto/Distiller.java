/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import prpobjects.prpfile;
import java.util.HashMap;
import prpobjects.Uruobjectdesc;
import prpdistiller.distiller;
import java.io.File;
import java.util.Vector;

public class Distiller
{
    public static void DistillTextures(String destfile, String texturefile, String outfolder)
    {
        //distill
        prpfile destprp = prpfile.createFromFile(destfile, false); //don't read raw, because we want to reocmpile all objects I think.
        prpfile textureprp = prpfile.createFromFile(texturefile, true);
        HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns = new HashMap();
        distiller.distillTextures(destprp, textureprp, new String[]{}, refReassigns);

        //compile and save
        File destfile2 = new File(destfile);
        String outfile = outfolder+"/"+destfile2.getName();
        destprp.saveAsFile(outfile);

    }


}
