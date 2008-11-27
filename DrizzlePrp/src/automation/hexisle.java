/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.*;
import uru.Bytestream;
import uru.context;
import uru.moulprp.prpfile;
import uru.moulprp.Typeid;
import java.util.HashMap;

public class hexisle
{
    public static String[] prpfiles = {
        "CatfishCanyon_Canyon.prp",
    };
    public static Typeid[] hexisleReadable={
        Typeid.plSceneNode,
        Typeid.plSceneObject,
        Typeid.plMipMap,
        Typeid.plCubicEnvironMap,
        Typeid.plLayer,
        Typeid.hsGMaterial,
        Typeid.plParticleSystem,
        Typeid.plBoundInterface,
        Typeid.plAudioInterface,
        Typeid.plWinAudio,
        Typeid.plCoordinateInterface,
        Typeid.plDrawInterface,
        Typeid.plSpawnModifier,
        Typeid.plDrawableSpans,
        Typeid.plDirectionalLightInfo,
        Typeid.plOmniLightInfo,
        Typeid.plPythonFileMod,
        Typeid.plPointShadowMaster,
        Typeid.plSimulationInterface,
        Typeid.plViewFaceModifier,
        Typeid.plSittingModifier,
        Typeid.plStereizer,
        Typeid.plSoundBuffer,
        Typeid.plRandomSoundMod,
        Typeid.plWin32StreamingSound,
        Typeid.plWin32StaticSound,
        Typeid.plParticleLocalWind,
        Typeid.plParticleCollisionEffectDie,
        Typeid.plExcludeRegionModifier,
        Typeid.plCameraBrain1,
        Typeid.plCameraBrain1_Avatar,
        Typeid.plCameraBrain1_Fixed,
        Typeid.plCameraBrain1_Circle,
        Typeid.plCameraModifier1,
        Typeid.plAGModifier,
        Typeid.plOccluder,
        Typeid.plDynamicTextMap,
        Typeid.plParticleCollisionEffectBounce,
        Typeid.plSpotLightInfo,
        Typeid.plShadowCaster,
        Typeid.plDirectShadowMaster,
        Typeid.plRelevanceRegion,
        Typeid.plSoftVolumeSimple,
        Typeid.plResponderModifier,
        Typeid.plParticleFlockEffect,
        Typeid.plFadeOpacityMod,
        Typeid.plClusterGroup,
        Typeid.plVisRegion,
        Typeid.plSoftVolumeUnion,
        Typeid.plObjectInVolumeDetector,
        Typeid.plObjectInBoxConditionalObject,
        Typeid.plInterfaceInfoModifier,
        Typeid.plVolumeSensorConditionalObject,
        Typeid.plLogicModifier,
        Typeid.plActivatorConditionalObject,
        Typeid.plFacingConditionalObject,
        Typeid.plOneShotMod,
        Typeid.plAvLadderMod,
        Typeid.plDynaFootMgr,
        Typeid.plPickingDetector,
        Typeid.plCameraRegionDetector,
        Typeid.plHKPhysical,
        Typeid.plSoftVolumeIntersect,
        Typeid.plEAXListenerMod,
        Typeid.plPhysicalSndGroup,
        Typeid.plSeekPointMod,
        Typeid.plRailCameraMod,
        Typeid.plLayerAnimation,
        Typeid.plATCAnim,
        Typeid.plAGMasterMod,
        Typeid.plPanicLinkRegion,
        Typeid.plLineFollowMod,
        Typeid.plMsgForwarder,
        Typeid.plAnimEventModifier,
        Typeid.plMultiStageBehMod,
        Typeid.plImageLibMod,
        Typeid.plLimitedDirLightInfo,
        Typeid.plAgeGlobalAnim,
        Typeid.plDynaPuddleMgr,
        Typeid.plWaveSet7,
        Typeid.plDynamicEnvMap,
        Typeid.plRidingAnimatedPhysicalDetector,
        Typeid.plGrassShaderMod,
        Typeid.plDynamicCamMap,
        Typeid.plSoftVolumeInvert,
        Typeid.plLayerBink,
        
        Typeid.plPostEffectMod,
        Typeid.plSoundVolumeApplicator,
        Typeid.plSimSuppressMsg,
        Typeid.plPostEffectMod,
        Typeid.plAxisAnimModifier,
        Typeid.pfGUIDialogMod,
        Typeid.pfGUIButtonMod,
        
        Typeid.plMobileOccluder,
        Typeid.plLayerLinkAnimation,
        Typeid.plArmatureMod,
        Typeid.plArmatureEffectsMgr,
        Typeid.plFilterCoordInterface,
        
        Typeid.plParticleFollowSystemEffect,
        Typeid.plParticleCollisionEffectBeat,
        Typeid.plParticleFadeVolumeEffect,
        
        Typeid.pfGUIKnobCtrl,
        
        Typeid.plDynaRippleMgr,
        Typeid.plLayerSDLAnimation,
        
        Typeid.pfGUIDragBarCtrl,
        
        Typeid.plMaintainersMarkerModifier,
        Typeid.plDistOpacityMod,
        Typeid.plMorphSequence,
        Typeid.plMorphDataSet,
        Typeid.plClothingItem,
        Typeid.plSharedMesh,
    };
    
    public static void convert(String infolder, String outfolder)
    {
        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("CatfishCanyon", 83);
        prefices.put("DessertDesert", 82);
        prefices.put("LouderSpace", 81);
        prefices.put("MoldyDungeon", 80);
        prefices.put("PlasmaMiasma", 79);
        prefices.put("PumpkinJungle", 78);
        
        HashMap<String, String> agenames = new HashMap<String, String>();

        
        //handle prps...
        for(String filename: prpfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfilename = filename.replaceFirst("_", "_District_");
            String outfile = outfolder + "/dat/" + outfilename;
            
            Bytes prpdata = Bytes.createFromFile(infile);
            Bytestream bytestream = Bytestream.createFromBytes(prpdata);
            context c = context.createFromBytestream(bytestream);
            c.curFile = filename; //helpful for debugging.
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                c.sequencePrefix = prefix;
            }
            
            //modify agename if Age is in list.
            String newAgename = agenames.get(agename);
            if(newAgename!=null)
            {
                c.ageName = newAgename;
            }

            prpfile prp = prpfile.createFromContext(c, hexisleReadable);

            //processPrp(prp,agename,agenames,outfolder);

            Bytes prpoutputbytes = prp.saveAsBytes();
            prpoutputbytes.saveAsFile(outfile);
        }
    }
    
    
}
