/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto.inplace;

import uru.moulprp.*;

public class Inplace_Misc
{

    public static void TranslateAhny(Inplace.InplaceModInfo info, prpfile prp)
    {
        String page = prp.header.pagename.toString();
        if(page.equals("Sphere01"))
        {
            auto.mod.AutoMod_TranslateAge.DustinModAhnySphere01(prp);
        }
        else if(page.equals("MaintRoom01"))
        {
            auto.mod.AutoMod_TranslateAge.DustinModAhnyMaint01(prp);
        }
        else if(page.equals("Sphere01OutBuildingInterior"))
        {
            auto.mod.AutoMod_TranslateAge.DustinModAhnyOutBuilding(prp);
        }

    }

    public static void ReltoFixPineTree(Inplace.InplaceModInfo info, prpfile prp)
    {
        auto.mod.AutoMod_Relto.ModRelto_FixPineTrees(prp);
    }

    public static void ReltoMakeDynamicCovers(Inplace.InplaceModInfo info, prpfile prp)
    {
        auto.mod.AutoMod_Relto.ModRelto_AddBookCovers(prp);
    }


}
