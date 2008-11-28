/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.FileUtils;
import shared.Bytes;
import uru.UruCrypt;
import uru.moulprp.textfile;
import java.util.HashMap;
import uru.Bytestream;
import uru.context;
import uru.moulprp.prpfile;
import shared.m;
import uru.moulprp.Typeid;
import uru.moulprp.prputils.Compiler.Decider;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Pageid;
import java.util.Vector;
import java.io.File;
import uru.moulprp.PrpRootObject;
import uru.moulprp.prputils;
import uru.moulprp.Uruobjectref;
import shared.Flt;
import uru.moulprp.Rgba;
import shared.State.AllStates;
import uru.moulprp.Urustring;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Transmatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;
import uru.moulprp.x00A2Pythonfilemod;
import uru.moulprp.*;
import shared.Pair;
import shared.generic;
import shared.cmap;

public class mystAutomation
{
    public static class moulDecider implements Decider
    {
        public boolean isObjectToBeIncluded(Uruobjectdesc desc)
        {
                Typeid type = desc.objecttype;
                int number = desc.objectnumber;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;

                String[] namestartswith = {};
                String[] nameequals = {};
                Typeid[] typeequals = {};
                
                boolean useObject = false;
                
                //blacklist
                if(type==type.plSceneNode) return false; //do not allow Scene node in here, it must be treated separately.
                //if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                //if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                if(pageid.prefix==0x22 && pageid.suffix==0x24 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.prefix==0x2A && pageid.suffix==0x25 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                             
                            
                             
                
                int useCase = 11;
                switch(useCase)
                {
                    case 0: //ederdelin main
                        //blacklist
                        if(name.equals("LampBall40")) return false; //ball in alcove lamp doesn't work. Uses LayerAnimation
                        
                        //whitelist
                        
                        //specific sceneobjects to include:
                        if(type==type.plSceneObject) return true; //test
                        //if(number==406 && type==type.plSceneObject) return true; //LinkInPointDefault.
                        if(name.equals("LinkInPointDefault")) return true;
                        //if(name.startsWith("LampRTOmniLight")) return true; //get all the omni lamps
                        //if(name.startsWith("RTFillLight")) return true; //get all the directional lamps
                        //if(name.startsWith("dlnMArbleLanternGlare")) return true;
                        //if(name.startsWith("dlnLanternGlare")) return true;
                        //if(name.startsWith("ChainPlane")) return true; //this crashes the game. It's two SceneObjects that enables two viewfacemodifiers. Those viewfacemodifier have different flags than the one's that work.  Is this a new option not available in pots? Or does it use a material that isn't loading right(i.e. one of the animated ones)?
                        namestartswith = new String[]{
                            "SnowParty","Garden02Snow", //required for snow.
                            "a",
                            "bench05","bench06","big","blend","bluedoor",
                            "bluespiraldoorglow",
                            ////"bluespiralglow", //side-effect of huge lampshade in alcove. Caused by my faked materials for plLayerAnimation?
                            "bluespiralrig",
                            "bluespiraltapestry",
                            "blulamp",
                            "bscamera", //not working.
                            "bsdoortimegage",
                            "bugpath01",
                            "cam-sitbench","canyonpillar","chainplane",
                            "clothglowgroup","clusterflowers",
                            "contemplationsteps",
                            "dln",
                            ////"dust", //crashes the game; contains plAGMasterMod
                            "fern0","fern1","fern2","fern3","fern4","fern5",
                            "followcamera", "followclosecamera",
                            ////"fountain", //has side-effect of huge lampshade.
                            "garden02snow",
                            ////"Garden2background", //has side-effect
                            ////"garden2top", //has side-effect
                            "gazebo1camera","gazebo2camera",
                            "lamp","lapm",
                            ////"leafgenerator", //crashes game, probably has LayerAnimation for leaves (no normal animation, though) The leaves don't seem visible in screenshots anyway.
                            ////"leafkiller" //not needed, since we have no leaves anyway. It doesn't appear to have anything to do with the snow either.
                            ////"lightcone", //layeranimation
                            "MaintainersMarkerCracks",
                            ////"MaintainersMarker", //layeranimation
                            "NbhdBkPodiumPost",
                            "Object",
                            "path",
                            "patiobench",
                            "pythonbox",
                            "restroom",
                            ////"rock" //stuff related to movable and falling rocks.  Is this even used?
                            "rope",
                            "rt",
                            "sfxbs",
                            ////"sfxgrass" //run on grass sound, need physics
                            "sfxsnd-amb","sfxsnd-birds","sfxsnd-winteramb", //can't hear most of these(i did hear a random bird sound); it may just be a setting and perhaps you can't hear them in moul either. But I think responderModifier needs to be implemented.
                            ////"sfxsndamb" //uses linefollowmod
                            "sfxsndfountain", //works great.
                            ////"sfxsndloon", //use animation
                            "sfxsndwinter",
                            ////"sfxstone" //run on stone sound, need physics.
                            "signpost",
                            "smallrock",
                            ////"soundcontrol", //needs respondermodifier
                            "stairway02",
                            "statuebase","statueblue","statuechrome","statueplinth",
                            "treasure",
                            "tree",
                            "yeeshapage15decal","yeeshapageleafdecal",
                            "yeeshapage15","yeeshapageleaf", //should allow use to hide/show the two pages.
                        };
                        nameequals = new String[]{
                            "BigTree06",
                            "BigTree06Decal",
                            //"fountainpool", //layeranimation
                            //"fountainpool01", //layeranimation
                            "fountainwalkwaydecal",
                            //"fountainwater", //layeranimation
                            "Garden2",
                        };
                        
                        //if(number==1 && type==type.plSpawnModifier) return true;
                        if(type==type.plSpawnModifier) return true;
                        //if(number==175 && type==type.plCoordinateInterface) return true;
                        if(type==type.plCoordinateInterface) return true;
                        if(type==type.plDrawInterface) return true;
                        //if(number==1 && type==type.plDrawableSpans) return true;
                        if(type==type.plDrawableSpans) return true;
                        if(type==type.hsGMaterial) return true;
                        if(type==type.plLayer) return true;
                        if(type==type.plOmniLightInfo) return true;
                        if(type==type.plPointShadowMaster) return true;
                        if(type==type.plCubicEnvironMap) return true;
                        if(type==type.plMipMap) return true;
                        if(type==type.plPythonFileMod) return true;
                        if(type==type.plDirectionalLightInfo) return true;
                        if(type==type.plSimulationInterface) return true;
                        if(type==type.plViewFaceModifier) return true;
                        if(type==type.plAudioInterface) return true;
                        if(type==type.plStereizer) return true;
                        if(type==type.plSoundBuffer) return true;
                        if(type==type.plRandomSoundMod) return true;
                        if(type==type.plWin32StreamingSound) return true;
                        if(type==type.plWin32StaticSound) return true;
                        if(type==type.plWinAudio) return true;
                        if(type==type.plParticleSystem) return true;
                        if(type==type.plParticleCollisionEffectDie) return true;
                        if(type==type.plParticleLocalWind) return true;
                        if(type==type.plBoundInterface) return true;
                        typeequals = new Typeid[]{
                            type.plExcludeRegionModifier
                            ,type.plCameraBrain1
                            ,type.plCameraBrain1_Avatar
                            ,type.plCameraBrain1_Circle
                            ,type.plCameraBrain1_Fixed
                            ,type.plCameraModifier1,
                            type.plAGModifier
                        };
                        
                        //unstable
                        //if(name.equals("Archway")) return true;
                        //if(name.equals("ArchwayLight")) return true;
                        //if(type==type.plLayerAnimation) return true;
                        break;
                    case 1: //include all: spyroom builtin, spyroom textures, ederdelin builtin, ederdelin textures
                        return true;
                        //break;
                    case 2: //spyroom main
                        if(name.equals("LinkInPointSpyroom")) return true;
                        if(name.equals("StartPoint01")) return true;
                        break;
                    case 3: //basic link in
                        if(name.toLowerCase().startsWith("linkinpoint")) return true;
                        if(name.toLowerCase().startsWith("startpoint")) return true;
                        break;
                    case 4: //basic drawables
                        typeequals = new Typeid[]{
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            //"atria",
                        };
                        break;
                    case 5: //guild pub
                        //if(name.toLowerCase().startsWith("imager")) return false;
                        //if(name.toLowerCase().startsWith("billboard")) return false;
                        //if(name.toLowerCase().startsWith("cpythgpub")) return false;
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            //"a",
                            //"b","c","d",
                            //"e","f","g",//"h","i","j",
                            //"hanging","j",
                            //"j","half","hanginglantern",
                            "hanginglightflare",
                            //"agesdlhook",
                            //"atria",
                            //"ayhoheekrm01",
                            //"ayhoheekrm02",
                            //"balcony",
                            //"ballister",
                            //"boothwood",
                            //"cam",
                            //"canvashang",
                            //"cavewalls",
                            //"circlepath",
                            //"convchair",
                            //"curtain",
                            //"doorxrgn",
                        };
                        break;
                    case 6: //livebahrocaves
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            "rt",
                            "starfield",
                        };
                        break;
                    case 7: //tetsonot
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 8: //minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 9: //jalak, payiferen
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 10: //negilahn, edertsogal, new ederdelin, new minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                            
                            type.plParticleFlockEffect,
                            type.plFadeOpacityMod,
                            type.plClusterGroup,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 11: //new minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            
                            type.plParticleFlockEffect,
                            type.plFadeOpacityMod,
                            type.plClusterGroup,
                            type.plVisRegion,
                            type.plSoftVolumeUnion,
                            type.plObjectInVolumeDetector,
                            type.plObjectInBoxConditionalObject,
                            type.plInterfaceInfoModifier,
                            type.plVolumeSensorConditionalObject,
                            type.plLogicModifier,
                            type.plActivatorConditionalObject,
                            type.plFacingConditionalObject,
                            type.plOneShotMod,
                            type.plAvLadderMod,
                            type.plPickingDetector,
                            type.plCameraRegionDetector,
                            
                            type.plHKPhysical,
                            
                            type.plSoftVolumeIntersect,
                            type.plEAXListenerMod,
                            type.plPhysicalSndGroup,
                            type.plSeekPointMod,
                            type.plRailCameraMod,
                            type.plLayerAnimation,
                            type.plATCAnim,
                            type.plAGMasterMod,
                            type.plPanicLinkRegion,
                            type.plLineFollowMod,
                            type.plMsgForwarder,
                            type.plAnimEventModifier,
                            type.plMultiStageBehMod,

                            type.plDynaFootMgr,
                            type.plResponderModifier, //crashes POD district of LiveBahroCaves, and minkCameras district of Minkata.
                            type.plSittingModifier,
                            type.plImageLibMod,
                            type.plLimitedDirLightInfo,
                            type.plAgeGlobalAnim,
                            type.plDynaPuddleMgr,
                            type.plWaveSet7,
                            type.plDynamicEnvMap,
                            
                            //version2
                            type.plSoftVolumeInvert,
                            
                            //personal
                            type.plDynaRippleMgr,
                            type.plLayerSDLAnimation,
                            
                            type.plMaintainersMarkerModifier,
                            type.plDistOpacityMod,
                            type.plMorphSequence,
                            type.plMorphDataSet,
                            type.plClothingItem,
                            type.plSharedMesh,
                            type.plLayerLinkAnimation,
                            
                            type.plEmoteAnim,
                            type.pfGUIDraggableMod,
                            type.pl2WayWinAudible,
                            type.plArmatureLODMod,
                            type.plClothingOutfit,
                            type.plClothingBase,
                            type.plArmatureEffectsMgr,
                            
                        };
                        namestartswith = new String[]{
                            /*"linkinpoint",
                            "startpoint",
                            "cratercentral",
                            "tower",
                            "outer",
                            "centertotem",
                            "ground", //ground
                            "criticalcave", //crater around caves
                            "soccer",
                            
                            "crespring",
                            "csfxresp",
                            "respdisablejc",
                            "respdrnowedge",
                            "respjconeshot",
                            "resplinkout",
                            "RespNglnWedge",
                            "RespPayiWedge",
                            "RespSolutionSymbols",
                            "RespTetsWedge",
                            "RespWedges", //pod problem.
                            
                            "cRespExcludeRgn", //minkata problem.
                            "cRespSfxLinkIn",*/
                        };
                        break;

                }
                
                for(int i=0;i<nameequals.length;i++)
                {
                    if(name.toLowerCase().equals(nameequals[i].toLowerCase())) return true;
                }
                for(int i=0;i<namestartswith.length;i++)
                {
                    if(name.toLowerCase().startsWith(namestartswith[i].toLowerCase())) return true;
                }
                for(int i=0;i<typeequals.length;i++)
                {
                    if(type==typeequals[i]) return true;
                }
                
                return useObject;
        }
    }
    
    public static Typeid[] moulReadable={
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
        
        Typeid.plEmoteAnim,
        Typeid.pfGUIDraggableMod,
        Typeid.pl2WayWinAudible,
        Typeid.plArmatureLODMod,
        Typeid.plClothingOutfit,
        Typeid.plClothingBase,
    };
    
    public static Typeid[] crowReadable = moulReadable;
    
    public static void convertEoaToWhatdoyousee(String infile, String outfile)
    {
        Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
        Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
        Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
        FileUtils.WriteFile(outfile, wdysData);
    }
    
    public static void convertMoulToPots(String infolder, String outfolder, Vector<String> files, boolean isSimplicity)
    {
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                Typeid type = desc.objecttype;
                int number = desc.objectnumber;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;

                String[] namestartswith = {};
                String[] nameequals = {};
                
                boolean useObject = false;
                
                //blacklist
                if(type==type.plSceneNode) return false; //do not allow Scene node in here, it must be treated separately.
                //if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                //if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                if(pageid.prefix==0x22 && pageid.suffix==0x24 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.prefix==0x2A && pageid.suffix==0x25 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).

                if(name.toLowerCase().equals("envmap02"))
                {
                    int dummy=0;
                }

                Typeid[] typeequals = new Typeid[]{
                        type.plSceneObject,

                        type.plCoordinateInterface,
                        type.plSpawnModifier,
                        type.plDrawInterface,
                        type.plDrawableSpans,
                        type.hsGMaterial,
                        type.plLayer,
                        type.plMipMap,
                        type.plCubicEnvironMap,

                        type.plOmniLightInfo,
                        type.plPointShadowMaster,
                        type.plPythonFileMod,
                        type.plDirectionalLightInfo,
                        type.plSimulationInterface,
                        type.plViewFaceModifier,
                        type.plAudioInterface,
                        type.plStereizer,
                        type.plSoundBuffer,
                        type.plRandomSoundMod,
                        type.plWin32StreamingSound,
                        type.plWin32StaticSound,
                        type.plWinAudio,
                        type.plParticleSystem,
                        type.plParticleCollisionEffectDie,
                        type.plParticleLocalWind,
                        type.plBoundInterface,
                        type.plExcludeRegionModifier,
                        type.plCameraBrain1,
                        type.plCameraBrain1_Avatar,
                        type.plCameraBrain1_Circle,
                        type.plCameraBrain1_Fixed,
                        type.plCameraModifier1,
                        type.plAGModifier,

                        type.plOccluder,
                        type.plDynamicTextMap,

                        type.plParticleCollisionEffectBounce,

                        type.plSpotLightInfo,

                        type.plShadowCaster,
                        type.plDirectShadowMaster,
                        type.plRelevanceRegion,
                        type.plSoftVolumeSimple,

                        type.plParticleFlockEffect,
                        type.plFadeOpacityMod,
                        type.plClusterGroup,
                        type.plVisRegion,
                        type.plSoftVolumeUnion,
                        type.plObjectInVolumeDetector,
                        type.plObjectInBoxConditionalObject,
                        type.plInterfaceInfoModifier,
                        type.plVolumeSensorConditionalObject,
                        type.plLogicModifier,
                        type.plActivatorConditionalObject,
                        type.plFacingConditionalObject,
                        type.plOneShotMod,
                        type.plAvLadderMod,
                        type.plPickingDetector,
                        type.plCameraRegionDetector,

                        type.plHKPhysical,

                        type.plSoftVolumeIntersect,
                        type.plEAXListenerMod,
                        type.plPhysicalSndGroup,
                        type.plSeekPointMod,
                        type.plRailCameraMod,
                        type.plLayerAnimation,
                        type.plATCAnim,
                        type.plAGMasterMod,
                        type.plPanicLinkRegion,
                        type.plLineFollowMod,
                        type.plMsgForwarder,
                        type.plAnimEventModifier,
                        type.plMultiStageBehMod,

                        type.plDynaFootMgr,
                        type.plResponderModifier, //crashes POD district of LiveBahroCaves, and minkCameras district of Minkata.
                        type.plSittingModifier,
                        type.plImageLibMod,
                        type.plLimitedDirLightInfo,
                        type.plAgeGlobalAnim,
                        type.plDynaPuddleMgr,
                        type.plWaveSet7,
                        type.plDynamicEnvMap,

                        //version2
                        type.plSoftVolumeInvert,
                        
                        //moul personal
                        type.plDynaRippleMgr,
                        type.plLayerSDLAnimation,
                        type.plParticleCollisionEffectBeat,
                        type.plParticleFadeVolumeEffect,

                        //some GUI stuff
                        type.pfGUIButtonMod,
                        type.pfGUIDialogMod,
                        type.plPostEffectMod,
                        type.pfGUIDragBarCtrl,
                        
                        type.plMaintainersMarkerModifier,
                        type.plDistOpacityMod,
                        type.plMorphSequence,
                        type.plMorphDataSet,
                        type.plClothingItem,
                        type.plSharedMesh,
                        
                        type.plEmoteAnim,
                        type.pfGUIDraggableMod,
                        type.pl2WayWinAudible,
                        type.plArmatureLODMod,
                        type.plClothingOutfit,
                        type.plClothingBase,
                        type.plArmatureEffectsMgr,
                        type.plLayerLinkAnimation,
                };
                String[] namestarts={
                };
                for(Typeid curtype: typeequals) if(curtype==type) return true;
                for(String start: namestarts) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                
                m.msg("Skipping type(1): "+type.toString());
                return false;
            }
        }

        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("Payiferen", 99);
        prefices.put("Kveer", 98);
        prefices.put("EderTsogal", 97);
        prefices.put("Neighborhood02",86);
        prefices.put("Personal",85);
        prefices.put("GreatTreePub",84);
        //prefices.put("GlobalAnimations", 789);
        
        HashMap<String, String> agenames = new HashMap<String, String>();
        agenames.put("Kveer", "KveerMOUL");
        agenames.put("Neighborhood02", "KirelMOUL");
        agenames.put("Personal", "PersonalMOUL");
        
        //these map from filename to oldpagenum to newpagenum
        //HashMap<String, HashMap<Integer,Integer>> suffices = new HashMap();
        //suffices.put("GUI_District_YeeshaPageGUI.prp", new Pair(50,86));
        //suffices.put("GUI_District_jalakControlPanel.prp", new Pair(68,90));
        cmap<String,cmap<Integer,Integer>> pagenums = new cmap();
        //suffices.put( "GUI", 50, 86); //YeeshaPageGUI
        //suffices.put( "GUI", 68, 90); //jalakControlPanel
        pagenums.put( "GUI", 49, 85); //YeeshaPageGUI
        pagenums.put( "GUI", 67, 89); //jalakControlPanel
        //add 1 to all these global ones.
        //There is an extra male animation: MaleTreadWater than has no female counterpart.  Just a mistake?
        pagenums.put( "GlobalAnimations", 291, 400); //FemaleAmazed
        pagenums.put( "GlobalAnimations", 292, 401); //
        pagenums.put( "GlobalAnimations", 326, 402); //FemaleAskQuestion
        pagenums.put( "GlobalAnimations", 325, 403); //
        pagenums.put( "GlobalAnimations",  77, 404); //FemaleBeckonBig
        pagenums.put( "GlobalAnimations", 144, 405); //
        pagenums.put( "GlobalAnimations", 145, 406); //FemaleBeckonSmall
        pagenums.put( "GlobalAnimations", 146, 407); //
        pagenums.put( "GlobalAnimations", 197, 408); //FemaleBookAccept
        pagenums.put( "GlobalAnimations", 202, 409); //
        pagenums.put( "GlobalAnimations", 198, 410); //FemaleBookAcceptIdle
        pagenums.put( "GlobalAnimations", 203, 411); //
        pagenums.put( "GlobalAnimations", 194, 412); //FemaleBookOffer
        pagenums.put( "GlobalAnimations", 199, 413); //
        pagenums.put( "GlobalAnimations", 196, 414); //FemaleBookOfferFinish
        pagenums.put( "GlobalAnimations", 201, 415); //
        pagenums.put( "GlobalAnimations", 195, 416); //FemaleBookOfferIdle
        pagenums.put( "GlobalAnimations", 200, 417); //
        pagenums.put( "GlobalAnimations", 293, 418); //FemaleBow
        pagenums.put( "GlobalAnimations", 294, 419); //
        pagenums.put( "GlobalAnimations", 258, 420); //FemaleCallMe
        pagenums.put( "GlobalAnimations", 259, 421); //
        pagenums.put( "GlobalAnimations", 260, 422); //FemaleCower
        pagenums.put( "GlobalAnimations", 261, 423); //
        pagenums.put( "GlobalAnimations", 295, 424); //FemaleCrazy
        pagenums.put( "GlobalAnimations", 309, 425); //
        pagenums.put( "GlobalAnimations", 280, 426); //FemaleCringe
        pagenums.put( "GlobalAnimations", 285, 427); //
        pagenums.put( "GlobalAnimations", 296, 428); //FemaleCrossArms
        pagenums.put( "GlobalAnimations", 310, 429); //
        pagenums.put( "GlobalAnimations", 297, 430); //FemaleDoh
        pagenums.put( "GlobalAnimations", 311, 431); //
        pagenums.put( "GlobalAnimations", 298, 432); //FemaleFlinch
        pagenums.put( "GlobalAnimations", 312, 433); //
        pagenums.put( "GlobalAnimations", 141, 434); //FemaleGlobalScopeGrab, like FemaleScopeGrab in pots
        pagenums.put( "GlobalAnimations",  70, 435); //
        pagenums.put( "GlobalAnimations", 142, 436); //FemaleGlobalScopeHold, like FemaleScopeHold in pots
        pagenums.put( "GlobalAnimations",  71, 437); //
        pagenums.put( "GlobalAnimations", 143, 438); //FemaleGlobalScopeRelease, like FemaleScopeRelease in pots
        pagenums.put( "GlobalAnimations",  72, 439); //
        pagenums.put( "GlobalAnimations", 262, 440); //FemaleGroan
        pagenums.put( "GlobalAnimations", 263, 441); //
        pagenums.put( "GlobalAnimations", 344, 442); //FemaleKITap
        pagenums.put( "GlobalAnimations", 343, 443); //
        pagenums.put( "GlobalAnimations", 282, 444); //FemaleKneel
        pagenums.put( "GlobalAnimations", 313, 445); //
        pagenums.put( "GlobalAnimations",  43, 446); //FemaleLeanLeft
        pagenums.put( "GlobalAnimations",  16, 447); //
        pagenums.put( "GlobalAnimations",  44, 448); //FemaleLeanRight
        pagenums.put( "GlobalAnimations",  17, 449); //
        pagenums.put( "GlobalAnimations", 299, 450); //FemaleLookAround
        pagenums.put( "GlobalAnimations", 314, 451); //
        pagenums.put( "GlobalAnimations", 264, 452); //FemaleOkay
        pagenums.put( "GlobalAnimations", 265, 453); //
        pagenums.put( "GlobalAnimations", 266, 454); //FemaleOverHere
        pagenums.put( "GlobalAnimations", 267, 455); //
        pagenums.put( "GlobalAnimations", 300, 456); //FemalePeer
        pagenums.put( "GlobalAnimations", 315, 457); //
        pagenums.put( "GlobalAnimations", 301, 458); //FemaleSalute
        pagenums.put( "GlobalAnimations", 316, 459); //
        pagenums.put( "GlobalAnimations", 302, 460); //FemaleScratchHead
        pagenums.put( "GlobalAnimations", 317, 461); //
        pagenums.put( "GlobalAnimations", 303, 462); //FemaleShakeFist
        pagenums.put( "GlobalAnimations", 318, 463); //
        pagenums.put( "GlobalAnimations", 304, 464); //FemaleShoo
        pagenums.put( "GlobalAnimations", 319, 465); //
        pagenums.put( "GlobalAnimations", 305, 466); //FemaleSlouchSad
        pagenums.put( "GlobalAnimations", 320, 467); //
        pagenums.put( "GlobalAnimations", 268, 468); //FemaleStop
        pagenums.put( "GlobalAnimations", 269, 469); //
        pagenums.put( "GlobalAnimations", 270, 470); //FemaleTalkHand
        pagenums.put( "GlobalAnimations", 321, 471); //
        pagenums.put( "GlobalAnimations", 272, 472); //FemaleTapFoot
        pagenums.put( "GlobalAnimations", 273, 473); //
        pagenums.put( "GlobalAnimations", 306, 474); //FemaleTaunt
        pagenums.put( "GlobalAnimations", 322, 475); //
        pagenums.put( "GlobalAnimations", 275, 476); //FemaleThumbsDown
        pagenums.put( "GlobalAnimations", 277, 477); //
        pagenums.put( "GlobalAnimations", 283, 478); //FemaleThumbsDown2
        pagenums.put( "GlobalAnimations", 286, 479); //
        pagenums.put( "GlobalAnimations", 274, 480); //FemaleThumbsUp
        pagenums.put( "GlobalAnimations", 276, 481); //
        pagenums.put( "GlobalAnimations", 284, 482); //FemaleThumbsUp2
        pagenums.put( "GlobalAnimations", 287, 483); //
        pagenums.put( "GlobalAnimations", 278, 484); //FemaleWallSlide
        pagenums.put( "GlobalAnimations", 279, 485); //
        pagenums.put( "GlobalAnimations", 307, 486); //FemaleWaveLow
        pagenums.put( "GlobalAnimations", 323, 487); //
        pagenums.put( "GlobalAnimations", 308, 488); //FemaleWinded
        pagenums.put( "GlobalAnimations", 324, 489); //
        pagenums.put( "GlobalAnimations",  32, 490); //FemaleDance
        pagenums.put( "GlobalAnimations",   5, 491); //MaleDance
        //include kg*

        cmap<String,cmap<String,String>> pagenames = new cmap();
        pagenames.put("GlobalAnimations", "FemaleDance", "FemaleDanceMOUL");
        pagenames.put("GlobalAnimations", "MaleDance", "MaleDanceMOUL");
        
        Typeid[] readable = mystAutomation.moulReadable;
        
        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");
        //FileUtils.CreateFolder(outfolder+"/SDL/");
        
        /*//Handle .sdl files...
        Vector<String> sdlfiles = common.filterFilenamesByExtension(files, ".sdl");
        for(String filename: sdlfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/SDL/" + filename;
            String outfile = outfolder + "/SDL/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = UruCrypt.DecryptWhatdoyousee(encryptedData);// UruCrypt.DecryptEoa(encryptedData);
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }*/
        
        //Handle .fni files...
        Vector<String> fnifiles = common.filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = UruCrypt.DecryptWhatdoyousee(encryptedData);// UruCrypt.DecryptEoa(encryptedData);
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //Handle .ogg files...
        Vector<String> oggfiles = common.filterFilenamesByExtension(files, ".ogg");
        for(String filename: oggfiles)
        {
            String infile = infolder + "/sfx/" + filename;
            String outfile = outfolder + "/sfx/" + filename;
            
            FileUtils.CopyFile(infile, outfile, true);
        }
        
        //Handle .age files...
        Vector<String> agefiles = common.filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            if(agename.toLowerCase().equals("personal")) m.warn("Relto may corrupt your savegame, be sure to back up your /sav folder!");
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = UruCrypt.DecryptWhatdoyousee(encryptedData); //UruCrypt.DecryptEoa(encryptedData);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToByteArray();
            }
            
            //modify Minkata's Age file.
            if(agename.toLowerCase().equals("minkata"))
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.appendLine("Page=minkDusttestDay,11");
                agefile.appendLine("Page=minkDusttestNight,12");
                agefile.appendLine("Page=minkDusttest,10");
                decryptedData = agefile.saveToByteArray();
            }
            
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .prp files...
        Vector<String> prpfiles = common.filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);//.replace("_", "_District_");
            
            shared.State.AllStates.push();
            if(isSimplicity)
            {
                if(agename.equals("Dereno"))
                {
                    shared.State.AllStates.setState("removeDynamicCamMap", false);
                }
                else if(agename.equals("EderDelin")||agename.equals("EderTsogal"))
                {
                    shared.State.AllStates.setState("translateSmartseeks", true);
                }
            }
            
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
            
            //modify sequence suffix if Age is in list.
            cmap<Integer,Integer> suffix = pagenums.get(agename);
            if(suffix!=null)
            {
                c.pagenumMap = suffix;
            }
            
            //modify agename if Age is in list.
            String newAgename = agenames.get(agename);
            if(newAgename!=null)
            {
                c.ageName = newAgename;
            }

            prpfile prp = prpfile.createFromContext(c, readable);
            
            processPrp(prp,agename,agenames,outfolder,infolder);
            
            //Change pagename, if applicable.
            String oldpagename = prp.header.pagename.toString();
            String newpagename = (String)pagenames.get2(agename,prp.header.pagename.toString());
            if(newpagename!=null)
            {
                prp.header.pagename = Urustring.createFromString(newpagename);
                outfile = outfile.replaceFirst("_District_"+oldpagename, "_District_"+newpagename);
            }
            
            Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
            prpoutputbytes.saveAsFile(outfile);
            
            shared.State.AllStates.pop();
            
        }
        
        
        //Handle .sum files...
        Vector<String> sumfiles = common.filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", common.replaceAgenameIfApplicable(agename, agenames));
            FileUtils.WriteFile(outfolder+"/dat/"+common.replaceAgenameIfApplicable(filename, agenames), sum1);
        }
        
        
        //All done!
        m.msg("Done Moul work!");
    }
    public static void processPrp(prpfile prp, String agename, HashMap<String, String> agenames, String outfolder, String infolder)
    {
        String newagename = agenames.get(agename);
        String finalname = newagename;
        if(finalname==null) finalname = agename;
        
        if(shared.State.AllStates.getStateAsBoolean("automateMystV"))
        {
            myst5Fixes.fixClickables(finalname, prp);
            myst5Fixes.fixBinks(finalname, prp, infolder);
            //fix direbo links.
            PrpRootObject[] objs = prputils.FindAllObjectsOfType(prp, Typeid.plPythonFileMod);
            for(PrpRootObject obj: objs)
            {
                x00A2Pythonfilemod pfm = obj.castTo();
                if(finalname.toLowerCase().equals("descentmystv")||finalname.toLowerCase().equals("direbo"))
                {
                    if(pfm.pyfile.toString().toLowerCase().equals("xlinkingbookguipopup"))
                    {
                        String oldlink = pfm.listings.get(2).xString.toString();
                        String age;
                        String spawnpoint;
                        if(oldlink.equals("DireboLaki"))
                        {
                            age = "Direbo";
                            spawnpoint = "LinkInPoint2";
                        }
                        else if(oldlink.equals("DireboSrln"))
                        {
                            age = "Direbo";
                            spawnpoint = "LinkInPoint1";
                        }
                        else if(oldlink.equals("DireboThgr"))
                        {
                            age = "Direbo";
                            spawnpoint = "LinkInPoint4";
                        }
                        else if(oldlink.equals("DireboTdlm"))
                        {
                            age = "Direbo";
                            spawnpoint = "LinkInPoint3";
                        }
                        else if(oldlink.equals("DescentRestAreaA"))
                        {
                            age = "DescentMystV";
                            spawnpoint = "LinkInFromThgrDirebo";
                        }
                        else if(oldlink.equals("DescentRestAreaB"))
                        {
                            age = "DescentMystV";
                            spawnpoint = "LinkInFromTdlmDirebo";
                        }
                        else if(oldlink.equals("DescentRestAreaC"))
                        {
                            age = "DescentMystV";
                            spawnpoint = "LinkInFromSrlnDirebo";
                        }
                        else if(oldlink.equals("DescentRestAreaD"))
                        {
                            age = "DescentMystV";
                            spawnpoint = "LinkInFromLakiDirebo";
                        }
                        else
                        {
                            m.err("Broken linking book in prpprocess.");
                            age="";
                            spawnpoint="";
                        }
                        pfm.pyfile = Urustring.createFromString("dusttest");
                        pfm.clearListings();
                        //pfm.listcount = 3;
                        //pfm.listings = new Pythonlisting[3];
                        pfm.addListing(Pythonlisting.createWithString(4, 1, Bstr.createFromString("linktoage")));
                        pfm.addListing(Pythonlisting.createWithString(4, 2, Bstr.createFromString(age)));
                        pfm.addListing(Pythonlisting.createWithString(4, 3, Bstr.createFromString(spawnpoint)));
                        /*pfm.listings[0] = new Pythonlisting();
                        pfm.listings[0].index = 1;
                        pfm.listings[0].type = 4; //string
                        pfm.listings[0].xString = Bstr.createFromString("linktoage");
                        pfm.listings[1] = new Pythonlisting();
                        pfm.listings[1].index = 2;
                        pfm.listings[1].type = 4; //string
                        pfm.listings[1].xString = Bstr.createFromString(age);
                        pfm.listings[2] = new Pythonlisting();
                        pfm.listings[2].index = 3;
                        pfm.listings[2].type = 4; //string
                        pfm.listings[2].xString = Bstr.createFromString(spawnpoint);*/
                        
                        //Vector<Pythonlisting> pls = new Vector<Pythonlisting>();
                        //for(Pythonlisting pl: pfm.listings)
                    }
                }
            }
            
            //pd1 = prp.findObjectWithRef(aco1.pickingdetectors[0]).castTo();
            //pd2 = prp.findObjectWithRef(aco2.pickingdetectors[0]).castTo();
            //lm2.parent.u2 = 0; //set disabled to false.
            int dummy=0;
            
            if(finalname.toLowerCase().equals("descentmystv") && prp.header.pagename.toString().toLowerCase().equals("dsntgreatshaftlowerrm"))
            {
                PlHKPhysical erf = prp.findObject("ElevRisingFloor", Typeid.plHKPhysical).castTo();
                erf.ode.convertee.coltype = 0x200;
                erf.ode.convertee.LOSDB = 0x44;
                erf.ode.convertee.group = new HsBitVector(4);
                erf.ode.convertee.flagsdetect = 0x0;
                erf.ode.convertee.mass = Flt.one();
            }
        }
        
        if(true) //attempt to rename animation in GlobalAnimations
        {
            if(finalname.toLowerCase().equals("globalanimations"))
            {
                if(prp.header.pagename.toString().equals("FemaleDance"))
                {
                    PlEmoteAnim ea = prp.findObject("FemaleDance", Typeid.plEmoteAnim).castTo();
                    ea.parent.parent.name = Urustring.createFromString("FemaleDanceMOUL");
                }
                if(prp.header.pagename.toString().equals("MaleDance"))
                {
                    PlEmoteAnim ea = prp.findObject("MaleDance", Typeid.plEmoteAnim).castTo();
                    ea.parent.parent.name = Urustring.createFromString("MaleDanceMOUL");
                }
            }
        }
        
        if(true) //attempts to fix the invisible minkata craters.
        {
            if(finalname.toLowerCase().equals("minkata") && prp.header.pagename.toString().toLowerCase().equals("minkexteriorday"))
            {
                fixCraters.fixCraters(prp, finalname, "minkDusttestDay", outfolder, Pageid.createFromPrefixPagenum(42, 11));
                
                //go ahead and make the dummy prp while we're at it.
                hackFactory.createMinkataClusterGroupPythonMod(outfolder);
                
                /*PrpRootObject[] clustergroups = prp.FindAllObjectsOfType(Typeid.plClusterGroup);
                for(PrpRootObject clustergroup: clustergroups)
                {
                    uru.moulprp.PlClusterGroup plcg = clustergroup.castTo();
                    plcg.count2 = 0;
                    plcg.fRegions = new Uruobjectref[0];
                }*/
                
            }
            
            if(finalname.toLowerCase().equals("minkata") && prp.header.pagename.toString().toLowerCase().equals("minkexteriornight"))
            {
                fixCraters.fixCraters(prp, finalname, "minkDusttestNight", outfolder, Pageid.createFromPrefixPagenum(42, 12));
            
            }
            
                //disable visregion.
                /*cgroup.count2 = 0; //disable visregion.
                cgroup.refs = new Uruobjectref[0]; //disable visregion.*/
                
                //replace material.
                /*PrpRootObject[] objs = prputils.FindAllObjectsWithName(prp, "RegMoundNew_3");
                uru.moulprp.x0007Material mat = objs[0].castTo();
                Uruobjectdesc desc = objs[0].header.desc;
                cgroup.ref = Uruobjectref.createFromUruobjectdesc(desc);*/
                
                //change material flags.
                /*PrpRootObject obj = cgroup.ref.xdesc.getObjectDescribed(prp);
                uru.moulprp.x0007Material mat = obj.castTo();
                for(Uruobjectref layerref: mat.layerrefs)
                {
                    PrpRootObject layer = layerref.xdesc.getObjectDescribed(prp);
                    uru.moulprp.x0006Layer layer2 = layer.castTo();
                    layer2.flags1 = 8;
                    int dummy=0;
                }*/
                int dummy=0;
            //}
        }

        if(AllStates.getStateAsBoolean("removeLadders"))
        {
            //find all sceneobjects that contain LogicModifiers that contain LadderModifiers, and remove them.
            PrpRootObject[] objs = prputils.FindAllObjectsOfType(prp, Typeid.plSceneObject);
            Vector<PrpRootObject> objsToDelete = new Vector<PrpRootObject>();
            for(PrpRootObject obj: objs)
            {
                boolean removeThisSceneobject = false;
                uru.moulprp.x0001Sceneobject so = obj.castTo();
                for(Uruobjectref ref: so.objectrefs2)
                {
                    if(ref.hasref() && ref.xdesc.objecttype==Typeid.plLogicModifier)
                    {
                        uru.moulprp.PlLogicModifier lmod = prputils.findObjectWithDesc(prp, ref.xdesc).castTo();
                        if(lmod.parent.message.type==Typeid.plNotifyMsg)
                        {
                            uru.moulprp.uruobj a = lmod.parent.message.prpobject.object;
                            if(a instanceof uru.moulprp.PrpMessage.PlNotifyMsg)
                            {
                                uru.moulprp.PrpMessage.PlNotifyMsg notifymsg = (uru.moulprp.PrpMessage.PlNotifyMsg)a;
                                for(Uruobjectref notmsgref: notifymsg.parent.refs)
                                {
                                    if(notmsgref.hasref()&&notmsgref.xdesc.objecttype==Typeid.plLadderModifier)
                                    {
                                        //remove this root sceneobject.
                                        removeThisSceneobject = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if(removeThisSceneobject)
                {
                    //actually remove it.
                    m.msg("Removing SceneObject that indirectly references PlLadderModifier: "+obj.header.desc.toString());
                    //prp.removeRootObject(obj);
                    objsToDelete.add(obj);
                }
            }
            for(PrpRootObject obj: objsToDelete)
            {
                prp.tagRootObjectAsDeleted(obj);
            }
            //if(xdesc!=null && xdesc.objecttype==Typeid.plLadderModifier && c.curRootObject!= null && c.curRootObject.objecttype==Typeid.plLogicModifier)
            //{
            //    {
            //        //throw new shared.readwarningexception("Removing plLogicModifier that references plLadderModifier:"+xdesc.objectname.toString());
            //    }
            //}
        }
        
        if(AllStates.getStateAsBoolean("translateSmartseeks"))
        {
            PrpRootObject[] objs = prputils.FindAllObjectsOfType(prp, Typeid.plSceneObject);
            for(PrpRootObject obj: objs)
            {
                uru.moulprp.x0001Sceneobject so = obj.castTo();
                for(Uruobjectref ref: so.objectrefs2)
                {
                    if(ref.hasref()&&ref.xdesc.objecttype==Typeid.plOneShotMod)
                    {
                        uru.moulprp.PlOneShotMod osm = prputils.findObjectWithDesc(prp, ref.xdesc).castTo();
                        if(osm.smartseek!=0)
                        {
                            //found it!
                            Uruobjectref coordsref = so.regioninfo;
                            if(coordsref.hasref())
                            {
                                m.msg("Translating smartseek for object... "+obj.header.desc.toString());
                                uru.moulprp.x0015CoordinateInterface coords = prputils.findObjectWithDesc(prp, coordsref.xdesc).castTo();
                                Transmatrix m = coords.localToParent;
                                RealMatrix m2 = m.convertToMatrix();
                                //org.apache.commons.math.linear.RealMatrixImpl b;
                                double[][] rawdata = m2.getData();
                                double scalar = 0.0; //set this to determine the number of feet back from the cloth to be.  A negative value will move you closer.
                                double height = 0.8; //the height to translate the avatar.  1.0 works.  0.6 is too small for the tsogal cloth on the back of the hood book structure.  0.8 seems just right.
                                rawdata[0][3] -= scalar*rawdata[1][0];
                                rawdata[1][3] += scalar*rawdata[0][0];
                                rawdata[2][3] += height; //set this to move the avatar up.  A negative value will move it down.
                                m2 = new RealMatrixImpl(rawdata);
                                coords.localToParent = Transmatrix.createFromMatrix(m2);
                                coords.localToWorld = coords.localToParent;
                                RealMatrix m3 = m2.inverse();
                                coords.parentToLocal = Transmatrix.createFromMatrix(m3);
                                coords.worldToLocal = coords.parentToLocal;
                            }
                        }
                    }
                }
            }
        }
        if(AllStates.getStateAsBoolean("changeVerySpecialPython"))
        {
            //String newagename = agenames.get(agename);
            if(newagename!=null)
            {
                if(prp.header.pagename.toString().toLowerCase().equals("builtin"))
                {
                    PrpRootObject[] objs = prputils.FindAllObjectsWithName(prp, "VeryVerySpecialPythonFileMod");
                    if(objs.length>0)
                    {
                        if(objs.length>1) m.warn("More than one VeryVerySpecialPythonFileMod found, just handling the first.");
                        uru.moulprp.x00A2Pythonfilemod pythfilemod =  objs[0].castTo();
                        Urustring oldpyfile = pythfilemod.pyfile;
                        pythfilemod.pyfile = Urustring.createFromString(pythfilemod.pyfile.toString().replace(agename, newagename));
                        if(shared.State.AllStates.getStateAsBoolean("reportSuffixes")) m.msg("Changing Agename in VeryVerySpecialPythonFileMod from "+agename+" to "+newagename);
                    }
                }
            }
        }
        if(AllStates.getStateAsBoolean("makePlLayersWireframe"))
        {
            PrpRootObject[] layers = prputils.FindAllObjectsOfType(prp, Typeid.plLayer);
            for(PrpRootObject layer2: layers)
            {
                uru.moulprp.x0006Layer layer = layer2.castTo();
                m.msg("Making wireframes!");
                layer.flags5 |= 0x1; //wireframe! //misc
            }
        }
        if(AllStates.getStateAsBoolean("removeDynamicCamMap"))
        {
            PrpRootObject[] layers = prputils.FindAllObjectsOfType(prp, Typeid.plLayer);
            for(PrpRootObject layer2: layers)
            {
                uru.moulprp.x0006Layer layer = layer2.castTo();
                if(layer.texture.hasref() && layer.texture.xdesc.objecttype==Typeid.plDynamicCamMap)
                {
                    //found it!
                    m.msg("Blackifying DynamicCamMap in PlLayer.");
                    //layer.flags1 |= 0x80; //kBlendNoColor //blend //makes it invisible?
                    //float r = (float)(15.0/255.0); //15
                    //float g = (float)(20.0/255.0); //20
                    //float b = (float)(26.0/255.0); //26
                    Flt r = Flt.createFromJavaFloat((float)(0.0/255.0)); //15
                    Flt g = Flt.createFromJavaFloat((float)(0.0/255.0)); //20
                    Flt b = Flt.createFromJavaFloat((float)(0.0/255.0)); //26
                    //float a = (float)1.0;
                    //layer.ambient = new Rgba(Flt.createFromJavaFloat(r),Flt.createFromJavaFloat(g),Flt.createFromJavaFloat(b),Flt.createFromJavaFloat(a));
                    //layer.diffuse = new Rgba(Flt.createFromJavaFloat(r),Flt.createFromJavaFloat(g),Flt.createFromJavaFloat(b),Flt.createFromJavaFloat(a));
                    //layer.emissive = new Rgba(Flt.createFromJavaFloat(r),Flt.createFromJavaFloat(g),Flt.createFromJavaFloat(b),Flt.createFromJavaFloat(a));
                    layer.ambient.r = r;
                    layer.ambient.g = g;
                    layer.ambient.b = b;
                    layer.diffuse.r = r;
                    layer.diffuse.g = g;
                    layer.diffuse.b = b;
                    layer.emissive.r = r;
                    layer.emissive.g = g;
                    layer.emissive.b = b;
                    layer.specular.r = r;
                    layer.specular.g = g;
                    layer.specular.b = b;
                }
            }
        }

    }
    /*public static void removeDynamicCamMapsFromMaterials(prpfile prp)
    {
        PrpRootObject[] layers = prputils.FindAllObjectsOfType(prp, Typeid.plLayer);
        for(PrpRootObject layer2: layers)
        {
            uru.moulprp.x0006Layer layer = layer2.castTo();
            if(layer.texture.hasref() && layer.texture.xdesc.objecttype==Typeid.plDynamicCamMap)
            {
                //found it!
                m.msg("Removing DynamicCamMap from layerRefs.");
                //mat.layerrefs.remove(layerref);
                //layer.flags5 = layer.flags5 | 0x1; //wireframe! //misc
                //layer.flags1 |= 0x80; //kBlendNoColor //blend //makes it invisible?
                //layer.opacity = Flt.zero();
                //layer.specular = new Rgba(0x3f800000,0x3f800000,0x3f800000,0x3f800000);
                //layer.flags3 |= 0x80; //kShadeSpecular //shade
            }
        }
        PrpRootObject[] mats = prputils.FindAllObjectsOfType(prp, Typeid.hsGMaterial);
        for(PrpRootObject mat2: mats)
        {
            uru.moulprp.x0007Material mat = mat2.castTo();
            for(Uruobjectref layerref: mat.layerrefs)
            {
                PrpRootObject layer2 = prputils.findObjectWithDesc(prp, layerref.xdesc);
                if(layer2.getObject() instanceof uru.moulprp.x0006Layer)
                {
                    uru.moulprp.x0006Layer layer = layer2.castTo();
                    if(layer.texture.hasref() && layer.texture.xdesc.objecttype==Typeid.plDynamicCamMap)
                    {
                        //found it!
                        m.msg("Removing DynamicCamMap from layerRefs.");
                        mat.layerrefs.remove(layerref);
                    }
                }
            }
        }
        mats = prputils.FindAllObjectsOfType(prp, Typeid.hsGMaterial);
        for(PrpRootObject mat2: mats)
        {
            uru.moulprp.x0007Material mat = mat2.castTo();
            for(Uruobjectref layerref: mat.maplayerrefs)
            {
                PrpRootObject layer2 = prputils.findObjectWithDesc(prp, layerref.xdesc);
                uru.moulprp.x0006Layer layer = layer2.castTo();
                if(layer.texture.hasref() && layer.texture.xdesc.objecttype==Typeid.plDynamicCamMap)
                {
                    //found it!
                    m.warn("Removing DynamicCamMap from mapLayerRefs.(untested.)");
                    mat.maplayerrefs.remove(layerref);
                }
            }
        }
    }*/
    
    public static void convertCrowthistleToPots(String crowthistlefolder, String outfolder)
    {
        class crowDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                Typeid type = desc.objecttype;
                String name = desc.objectname.toString();
                if(desc.objecttype==Typeid.plSceneNode) return true;
                if(desc.objecttype==Typeid.plCoordinateInterface) return true;
                if(desc.objecttype==Typeid.plSpawnModifier) return true;
                if(desc.objecttype==Typeid.plPythonFileMod) return true;
                if(desc.objecttype==Typeid.plSceneObject) return true;
                if(desc.objecttype==Typeid.plMipMap) return true;
                if(desc.objecttype==Typeid.plCubicEnvironMap) return true;
                //plLayer, plMaterial, plDrawInterface, plDrawableSpans
                if(type==Typeid.plLayer) return true;
                if(type==Typeid.hsGMaterial) return true;
                if(type==Typeid.plDrawableSpans) return true;
                Typeid[] typeequals = new Typeid[]{
                    Typeid.plViewFaceModifier,
                    Typeid.plLayerAnimation,
                    Typeid.plPythonFileMod,
                    Typeid.plBoundInterface,
                    
                    Typeid.plHKPhysical, Typeid.plSimulationInterface,
                    Typeid.plDirectionalLightInfo, Typeid.plOmniLightInfo,
                    Typeid.plAGMasterMod, Typeid.plAGModifier, Typeid.plATCAnim,
                    Typeid.plParticleSystem, Typeid.plParticleLocalWind, Typeid.plParticleCollisionEffectDie,
                    Typeid.plMsgForwarder,
                    Typeid.plAudioInterface, Typeid.plRandomSoundMod, Typeid.plSoundBuffer, Typeid.plWinAudio, Typeid.plWin32StreamingSound, Typeid.plWin32StaticSound,
                    Typeid.plDrawInterface,
                    Typeid.plSoftVolumeSimple,
                    Typeid.plResponderModifier,
                    Typeid.plOccluder, Typeid.plShadowCaster, Typeid.plSoftVolumeInvert, Typeid.plSoftVolumeUnion,
                    Typeid.plStereizer,
                };
                String[] namestartswith = new String[]{
                    "a",
                    "b",
                    "cpyth","d","e","f",
                    "basket",
                    "bigrock",
                    //"bigtree",
                    "blueflower",
                    //"boat",
                    "boulder",
                    "bowl",
                    "box",
                    "bridge",
                    "bucket",
                };
                String[] nameequals = new String[]{
                    "bigtree01",
                    "bigtree02",
                    "bigtree03",
                    "bigtree04",
                    "bigtree05",
                    "bigtree06",
                    "bigtree07",
                    "bigtree08",
                    "Fern08",
                };
                
                
                for(int i=0;i<nameequals.length;i++)
                {
                    if(name.toLowerCase().equals(nameequals[i].toLowerCase())) return true;
                }
                for(int i=0;i<namestartswith.length;i++)
                {
                    if(name.toLowerCase().startsWith(namestartswith[i].toLowerCase())) return true;
                }
                for(int i=0;i<typeequals.length;i++)
                {
                    if(type==typeequals[i]) return true;
                }
                
                m.msg("Skipping type(2): "+type.toString());
                return false;
            }
            
        }
        
        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("MarshScene", 96);
        prefices.put("MountainScene", 95);

        HashMap<String, String> agenames = new HashMap<String, String>();
        
        String[] fnifiles = {
            "MarshScene.fni",
            "MountainScene.fni",
        };
        String[] agefiles = {
            "MarshScene.age",
            "MountainScene.age",
        };
        //String[] sumfiles = {
        //    "MarshScene.sum",
        //    "MountainScene.sum",
        //};
        String[] prpfiles = {
            "MarshScene_Exterior.prp",
            "MarshScene_Extras.prp",
            "MarshScene_MWInterior.prp",
            "MarshScene_Textures.prp",
            "MarshScene_TourCamera.prp",
            "MarshScene_WaterHorses.prp",
            "MountainScene_Courtyard.prp",
            "MountainScene_EllenHallInterior.prp",
            "MountainScene_Exterior.prp",
            "MountainScene_Extras.prp",
            "MountainScene_Textures.prp",
            "MountainScene_TourCamera.prp",
            "MountainScene_tw_g1_g2.prp",
            "MountainScene_tw_g1_g3.prp",
            "MountainScene_tw_g1_hm.prp",
            "MountainScene_tw_g2_g1.prp",
            "MountainScene_tw_g2_g3.prp",
            "MountainScene_tw_g2_hm.prp",
            "MountainScene_tw_g3_g1.prp",
            "MountainScene_tw_g3_g2.prp",
            "MountainScene_tw_g3_hm.prp",
            "MountainScene_tw_hm_g1.prp",
            "MountainScene_tw_hm_g2.prp",
            "MountainScene_tw_hm_g3.prp",
            "MountainScene_tw_shape.prp",
            "MountainScene_tw_w1.prp",
            "MountainScene_tw_w2.prp",
            "MountainScene_tw_w3.prp",
            "MountainScene_Vista.prp",
        };
        
        Vector<String> files = new Vector();
        files.add("MarshScene.(others)");
        
        cmap<String,cmap<String,Integer>> authored = new cmap();
        authored.put("MarshScene","FootRgns",93);

        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");

        //convert .fni files...
        for(String filename: fnifiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename;
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .age files...
        for(String filename: agefiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename;
            String agename = common.getAgenameFromFilename(filename);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToBytes();
            }
            
            //add any pages that are authored.
            if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page="+pagename+","+Integer.toString(pagenum));
                    decryptedData = agefile.saveToBytes();
                }
            }
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .prp files...
        for(String filename: prpfiles)
        {
            //Runtime.getRuntime().gc();
            
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename.replaceFirst("_", "_District_");
            String agename = common.getAgenameFromFilename(filename);
            
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

            //c.typesToRead = typesToRead;
            
            prpfile prp = prpfile.createFromContext(c, automation.mystAutomation.crowReadable);

            processPrp(prp,agename,agenames,outfolder,crowthistlefolder);

            Bytes prpoutputbytes = prp.saveAsBytes(new crowDecider());
            prpoutputbytes.saveAsFile(outfile);
        }
        
        //Handle .(others) files...
        Vector<String> otherfiles = common.filterFilenamesByExtension(files, ".(others)");
        for(String filename: otherfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            
            if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    String outfilename = common.replaceAgenameIfApplicable(agename, agenames)+"_District_"+pagename+".prp";
                    String outfile = outfolder + "/dat/" + outfilename;

                    Bytes bytes = shared.GetResource.getResourceAsBytes("/files/authored/"+outfilename);
                    bytes.saveAsFile(outfile);
                }
            }
        }
        
        //create .sum files...
        Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", "MarshScene");
        FileUtils.WriteFile(outfolder+"/dat/MarshScene.sum", sum1);
        Bytes sum2 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", "MountainScene");
        FileUtils.WriteFile(outfolder+"/dat/MountainScene.sum", sum2);
        
        
        m.msg("Done Crowthistle work!");
    }
    
    public static void convertMyst5ToPots(String infolder, String outfolder, Vector<String> files, boolean makeMinimalReleeshan)
    {
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                //return false;
                Typeid type = desc.objecttype;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;
                
                //blacklist
                if(type==Typeid.plBoundInterface&&name.equals("PartColl08")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface&&name.equals("PartColl07")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface&&name.equals("PartColl06")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface && pageid.prefix==89) //Siralehn/Noloben
                {
                    if(name.equals("Cone01") || name.equals("doorParticleColliderMesh") || name.startsWith("RainDef0") || name.startsWith("RainDef1") || name.equals("RsinDefTop"))
                    {
                        return false;
                    }
                }
                if(type==Typeid.plBoundInterface && pageid.prefix==88) //tahgira
                {
                    if(name.equals("CaveSnowKiller01")) return false;
                    if(name.equals("FieldBubbleKiller")) return false; //this makes tahgira crash when you link there in 3rd person, I think.
                }
                
                if(type==Typeid.plBoundInterface)
                {
                    int dummy=0;
                }
                
                Typeid[] typeequals = new Typeid[]{
                    Typeid.plSceneNode, Typeid.plSceneObject, Typeid.plCoordinateInterface, Typeid.plSpawnModifier, Typeid.plFilterCoordInterface, //not having plFilterCoordInterface can crash Uru.

                    Typeid.plMipMap, Typeid.plCubicEnvironMap,
                    Typeid.plLayer, Typeid.hsGMaterial, Typeid.plDrawableSpans,
                    Typeid.plViewFaceModifier,
                    Typeid.plLayerAnimation,
                    Typeid.plLayerLinkAnimation,
                    Typeid.plLayerBink, //must have the .bnk files copied over or Uru will crash.
                    
                    Typeid.plHKPhysical, Typeid.plSimulationInterface,
                    Typeid.plDirectionalLightInfo, Typeid.plOmniLightInfo, Typeid.plSpotLightInfo,
                    Typeid.plAGMasterMod, Typeid.plAGModifier, Typeid.plATCAnim,
                    Typeid.plParticleSystem, Typeid.plParticleLocalWind, Typeid.plParticleCollisionEffectDie,
                    Typeid.plAudioInterface, Typeid.plRandomSoundMod, Typeid.plSoundBuffer, Typeid.plWinAudio, Typeid.plWin32StreamingSound, Typeid.plWin32StaticSound, Typeid.plStereizer,
                    Typeid.plDrawInterface,
                    Typeid.plSoftVolumeSimple,
                    Typeid.plOccluder, Typeid.plShadowCaster, Typeid.plSoftVolumeInvert, Typeid.plSoftVolumeUnion, Typeid.plSoftVolumeIntersect,
                    Typeid.plObjectInBoxConditionalObject, Typeid.plObjectInVolumeDetector,
                    Typeid.plActivatorConditionalObject, Typeid.plFacingConditionalObject, Typeid.plVolumeSensorConditionalObject,
                    Typeid.plInterfaceInfoModifier, Typeid.plLogicModifier,
                    Typeid.plPointShadowMaster,
                    Typeid.plDynamicEnvMap,
                    Typeid.plWaveSet7,
                    Typeid.plPickingDetector, Typeid.plMsgForwarder, Typeid.plLineFollowMod, Typeid.plExcludeRegionModifier,
                    Typeid.plPythonFileMod,
                    Typeid.plResponderModifier,
                    Typeid.plParticleCollisionEffectBounce, Typeid.plPostEffectMod,
                    
                    Typeid.pfGUIButtonMod,
                    Typeid.pfGUIDialogMod,
                    Typeid.plAGAnimBink,
                    Typeid.plAnimEventModifier,
                    Typeid.plAxisAnimModifier,
                    Typeid.plMobileOccluder,
                    Typeid.plDirectShadowMaster,
                    Typeid.plVisRegion,
                    Typeid.plParticleFlockEffect,
                    Typeid.plImageLibMod,
                    
                    Typeid.plMobileOccluder,
                    Typeid.plArmatureMod,
                    Typeid.plArmatureEffectsMgr,
                    
                    Typeid.plParticleCollisionEffectBeat,
                    Typeid.plParticleFadeVolumeEffect,
                    Typeid.plParticleFollowSystemEffect,
                    
                    Typeid.pfGUIKnobCtrl,
                    
                    Typeid.plBoundInterface,
                };
                String[] namestarts={
                    /*//"boulder01",
                    "bridge poles05", //works when plLayerBink is present.
                    "bubble01haloa01", //works!*/
                    //"partcoll06", //breaks it.
                    //"partcoll07", //breaks it.
                    //"partcoll08", //breaks it.
                    //"partcoll09",
                    //"partcollidtablet",
                    //"particlegroundcollide",
                    //"craterupper", //works
                    //"spawnwindmill",
                    //"startpoint01_1",
                    //"box",
                    //"cone",
                    //"door",
                    //"raindef0",
                    //"raindef1",
                    //"raindeflector",
                    //"rsin",
                    //"CaveSnowKiller01",
                    //"FieldBubbleKiller",
                };
                for(Typeid curtype: typeequals) if(curtype==type) return true;
                for(String start: namestarts) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                
                if(type==Typeid.plCoordinateInterface)
                {
                    for(String start: new String[]{
                        "arenagateleft",
                        //"arenalift",
                    }) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                }
                
                m.msg("Skipping type(3): "+type.toString());
                return false;
            }
        }

        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("Descent", 94);
        prefices.put("Direbo", 93);
        prefices.put("Kveer", 92);
        prefices.put("Laki", 91);
        prefices.put("Myst", 90);
        prefices.put("Siralehn", 89);
        prefices.put("Tahgira", 88);
        prefices.put("Todelmer", 87);
        
        HashMap<String, String> agenames = new HashMap<String, String>();
        agenames.put("Descent", "DescentMystV");
        agenames.put("Kveer", "KveerMystV");
        agenames.put("Myst", "MystMystV");
        
        cmap<String,cmap<String,Integer>> authored = new cmap();
        //authored.put("Myst","Additions",89);
        authored.put("Myst","FootRgns",89);
        authored.put("Direbo","Additions",98);
        authored.put("Descent","FootRgns",97);
        authored.put("Tahgira","FootRgns",97);
        authored.put("Todelmer","FootRgns",92);
        authored.put("Kveer","FootRgns",97); //Releeshan

        Typeid[] readable = mystAutomation.moulReadable;
        
        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");
        
        //Handle .bik files...
        Vector<String> bikfiles = common.filterFilenamesByExtension(files, ".bik");
        for(String filename: bikfiles)
        {
            String infile = infolder + "/avi/" + filename;
            String outfile = outfolder + "/avi/" + filename;
            
            FileUtils.CopyFile(infile, outfile, true);
        }
        
        //Handle .ogg files...
        Vector<String> oggfiles = common.filterFilenamesByExtension(files, ".ogg");
        for(String filename: oggfiles)
        {
            String infile = infolder + "/sfx/" + filename;
            String outfile = outfolder + "/sfx/" + filename;
            
            FileUtils.CopyFile(infile, outfile, true);
        }
        
        //Handle .fni files...
        Vector<String> fnifiles = common.filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            
            //laki needs these lines.
            if(agename.toLowerCase().equals("laki"))
            {
                textfile fnifile = textfile.createFromBytes(decryptedData);
                //fnifile.appendLine("Graphics.Renderer.SetClearColor 0 0 0");
                //fnifile.appendLine("Graphics.Renderer.Fog.SetDefColor 0 0 0");
                fnifile.appendLine("Graphics.Renderer.Fog.SetDefLinear 0 0 0");
                decryptedData = fnifile.saveToBytes();
            }

            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .age files...
        Vector<String> agefiles = common.filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToBytes();
            }
            
            //Remove all the KveerMystV pages except kverReleeshan and dusttest from the .age file.
            if(makeMinimalReleeshan)
            {
                if(agename.equals("Kveer"))
                {
                    textfile agefile = textfile.createFromBytes(decryptedData);
                    for(textfile.textline line: agefile.getLines())
                    {
                        String l = line.getString();
                        if(l.startsWith("Page="))
                        {
                            line.setString("#"+l); //comment it out.
                        }
                    }
                    agefile.appendLine("Page=kverReleeshan,22"); //remove the ,1 from the end so that it loads.
                    //agefile.appendLine("Page=dusttest,90"); //leave it alone.
                    decryptedData = agefile.saveToBytes();
                }
            }
            
            //add dusttest page, dynamically loaded.
            if(agename.equals("Descent") || agename.equals("Todelmer") || agename.equals("Tahgira") || agename.equals("Siralehn") || agename.equals("Laki") || agename.equals("Kveer"))
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.appendLine("Page=dusttest,90");
                decryptedData = agefile.saveToBytes();
            }
            
            //add any pages that are authored.
            if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                //for(Pair<String,Integer> curauthprp: authored.get(agename))
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page="+pagename+","+Integer.toString(pagenum));
                    decryptedData = agefile.saveToBytes();
                }
                
                /*if(agename.equals("Myst"))
                {
                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page=Additions,89");
                    decryptedData = agefile.saveToBytes();
                }
                if(agename.equals("Direbo"))
                {
                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page=Additions,98");
                    decryptedData = agefile.saveToBytes();
                }*/
            }
            
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .(others) files...
        Vector<String> otherfiles = common.filterFilenamesByExtension(files, ".(others)");
        for(String filename: otherfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            
            if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    String outfilename = common.replaceAgenameIfApplicable(agename, agenames)+"_District_"+pagename+".prp";
                    String outfile = outfolder + "/dat/" + outfilename;

                    Bytes bytes = shared.GetResource.getResourceAsBytes("/files/authored/"+outfilename);
                    bytes.saveAsFile(outfile);
                }
            }
            
        }
        
        
        //Handle .prp files...
        Vector<String> prpfiles = common.filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {
           
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfilename = common.replaceAgenameIfApplicable(filename, agenames).replaceFirst("_", "_District_");
            String outfile = outfolder + "/dat/" + outfilename;
            
            if(shared.GetResource.hasResource("/files/myst5/"+outfilename))
            {
                Bytes bytes = shared.GetResource.getResourceAsBytes("/files/myst5/"+outfilename);
                bytes.saveAsFile(outfile);
            }
            //else if(shared.GetResource.hasResource("files/authored/"+outfilename))
            //{
            //    if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial"))
            //    {
            //        Bytes bytes = shared.GetResource.getResourceAsBytes("/files/authored/"+outfilename);
            //        bytes.saveAsFile(outfile);
            //    }
            //}
            else
            {
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

                prpfile prp = prpfile.createFromContext(c, readable);
            
                processPrp(prp,agename,agenames,outfolder,infolder);

                Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
                prpoutputbytes.saveAsFile(outfile);
            }
        }
        
        
        //Handle .sum files...
        Vector<String> sumfiles = common.filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", common.replaceAgenameIfApplicable(agename, agenames));
            FileUtils.WriteFile(outfolder+"/dat/"+common.replaceAgenameIfApplicable(filename, agenames), sum1);
        }
        
        //Handle .sdl files...
        /*Vector<String> sdlfiles = common.filterFilenamesByExtension(files, ".sdl");
        for(String filename: sdlfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/sdl/" + filename;
            String outfile = outfolder + "/sdl/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            
            uru.moulprp.sdlfile sdl = new uru.moulprp.sdlfile(decryptedData);
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }*/
        
        
        //All done!
        m.msg("Done MystV work!");
    }
    
    
    public static void readPotsPrps(String infolder, Vector<String> files)
    {
        for(String file: files)
        {
            String filename = infolder + "/dat/" + file;
            //open prp file and process it.
            byte[] filedata = FileUtils.ReadFile(filename);

            //do work.
            context c = context.createFromBytestream(new Bytestream(filedata));
            //c.readversion = version;
            c.curFile = file;
            uru.moulprp.prpprocess.ProcessAllObjects(c,false);
            //if(version==3) prputils.ProcessPotsPrp(filedata);
        }
    }
    public static void readAllPotsPrps(String prpdirname)
    {
        File prpfolder = new File(prpdirname+"/dat/");
        if (!prpfolder.isDirectory() || !prpfolder.exists())
        {
            m.err("Prp directory not in proper format or not found.");
            return;
        }
        
        File[] files = prpfolder.listFiles();
        m.msg("Parsing files... count="+Integer.toString(files.length));
        for(int i=0;i<files.length;i++)
        {
            File curfile = files[i];
            if(curfile.getName().toLowerCase().endsWith(".prp"))
            {
                //open prp file and process it.
                byte[] filedata = FileUtils.ReadFile(curfile);
                
                //do work.
                context c = context.createFromBytestream(new Bytestream(filedata));
                //c.readversion = version;
                c.curFile = curfile.getName();
                uru.moulprp.prpprocess.ProcessAllObjects(c,false);
                //if(version==3) prputils.ProcessPotsPrp(filedata);
                //if(version==6) prputils.ProcessAll(filedata);
            }
        }
        
        m.msg("Finished Processing all files.");
        
    }
    
    public static void readAllPrpsFromAllGames(String potsdir, String crowdir, String myst5dir, String mouldir)
    {
        Typeid[] alltypes = {
            Typeid.hsGMaterial, //large
            Typeid.pfGUIButtonMod,
            Typeid.pfGUIDialogMod,
            Typeid.pfObjectFlocker,
            Typeid.plAGAnimBink,
            Typeid.plAGMasterMod,
            Typeid.plAGModifier, //has embedded
            Typeid.plATCAnim, //has embedded
            Typeid.plActivatorConditionalObject,
            Typeid.plActivatorMsg,
            Typeid.plAgeGlobalAnim, //has embedded
            Typeid.plAnimCmdMsg,
            Typeid.plAnimEventModifier,
            Typeid.plAnimPath,
            Typeid.plArmatureEffectStateMsg,
            Typeid.plAudioInterface,
            Typeid.plAvLadderMod,
            Typeid.plAxisAnimModifier,
            Typeid.plBoundInterface,
            Typeid.plBubbleShaderMod,
            Typeid.plCameraBrain1,
            Typeid.plCameraBrain1_Avatar,
            Typeid.plCameraBrain1_Circle,
            Typeid.plCameraBrain1_Fixed,
            Typeid.plCameraBrainUru,
            Typeid.plCameraBrainUru_Fixed,
            Typeid.plCameraModifier,
            Typeid.plCameraModifier1,
            Typeid.plCameraMsg,
            Typeid.plCameraRegionDetector,
            Typeid.plClusterGroup, //large
            Typeid.plCompoundController,
            Typeid.plCompoundPosController,
            Typeid.plCompoundRotController,
            Typeid.plConvexIsect,
            Typeid.plCoordinateInterface,
            Typeid.plCubicEnvironMap, //large
            Typeid.plDirectMusicSound,
            Typeid.plDirectShadowMaster,
            Typeid.plDirectionalLightInfo,
            Typeid.plDrawInterface,
            Typeid.plDrawableSpans, //large
            Typeid.plDynaFootMgr,
            Typeid.plDynaPuddleMgr,
            Typeid.plDynamicCamMap,
            Typeid.plDynamicEnvMap,
            Typeid.plDynamicTextMap,
            Typeid.plEAXListenerMod,
            Typeid.plEAXReverbEffect,
            Typeid.plEnableMsg,
            Typeid.plEventCallbackMsg,
            Typeid.plExcludeRegionModifier,
            Typeid.plExcludeRegionMsg,
            Typeid.plFacingConditionalObject,
            Typeid.plFadeOpacityMod,
            Typeid.plGenRefMsg,
            Typeid.plGrassShaderMod,
            
            Typeid.plHKPhysical, //large
            Typeid.plImageLibMod,
            Typeid.plInterfaceInfoModifier,
            Typeid.plLayer,
            Typeid.plLayerAnimation,
            Typeid.plLayerBink,
            Typeid.plLeafController,
            Typeid.plLightDiffuseApplicator,
            Typeid.plLightSpecularApplicator,
            Typeid.plLimitedDirLightInfo,
            Typeid.plLineFollowMod,
            Typeid.plLinkToAgeMsg,
            Typeid.plLogicModifier,
            
            Typeid.plMatrix44Controller,
            Typeid.plMatrixChannelApplicator,
            Typeid.plMatrixControllerChannel,
            Typeid.plMipMap, //large
            Typeid.plMsgForwarder,
            Typeid.plMultiStageBehMod,
            Typeid.plNodeRegionModifier,
            Typeid.plNotifyMsg,
            Typeid.plObjectInBoxConditionalObject,
            Typeid.plObjectInVolumeDetector,
            Typeid.plOccluder,
            Typeid.plOmniLightInfo,
            Typeid.plOmniSqApplicator,
            Typeid.plOneShotMod,
            Typeid.plOneShotMsg,
            Typeid.plOneTimeParticleGenerator,
            Typeid.plPanicLinkRegion,
            Typeid.plParticleCollisionEffectBounce,
            Typeid.plParticleCollisionEffectDie,
            Typeid.plParticleEmitter,
            Typeid.plParticleFlockEffect,
            Typeid.plParticleLocalWind,
            Typeid.plParticlePPSApplicator,
            Typeid.plParticleSystem,
            Typeid.plPhysicalSndGroup,
            Typeid.plPickingDetector,
            Typeid.plPointControllerChannel,
            Typeid.plPointShadowMaster,
            Typeid.plPostEffectMod,
            Typeid.plPythonFileMod,
            
            Typeid.plRailCameraMod,
            Typeid.plRandomSoundMod,
            Typeid.plRefMsg,
            Typeid.plRelativeMatrixChannelApplicator,
            Typeid.plRelevanceRegion,
            Typeid.plResponderEnableMsg,
            Typeid.plResponderModifier,
            Typeid.plRideAnimatedPhysMsg,
            Typeid.plRidingAnimatedPhysicalDetector,
            Typeid.plScalarController,
            Typeid.plScalarControllerChannel,
            Typeid.plSceneNode,
            Typeid.plSceneObject,
            Typeid.plSeekPointMod,
            Typeid.plShadowCaster,
            Typeid.plSimSuppressMsg,
            Typeid.plSimpleParticleGenerator,
            Typeid.plSimplePosController,
            Typeid.plSimpleRotController,
            Typeid.plSimpleScaleController,
            Typeid.plSimulationInterface,
            Typeid.plSittingModifier,
            Typeid.plSoftVolumeIntersect,
            Typeid.plSoftVolumeInvert,
            Typeid.plSoftVolumeSimple,
            Typeid.plSoftVolumeUnion,
            Typeid.plSoundBuffer,
            Typeid.plSoundMsg,
            Typeid.plSoundVolumeApplicator,
            Typeid.plSpaceTree,
            Typeid.plSpawnMod,
            Typeid.plSpawnModifier,
            Typeid.plSpotLightInfo,
            Typeid.plStereizer,
            Typeid.plSubWorldMsg,
            
            Typeid.plTMController,
            Typeid.plTimerCallbackMsg,
            Typeid.plViewFaceModifier,
            Typeid.plVisRegion,
            Typeid.plVolumeIsect,
            Typeid.plVolumeSensorConditionalObject,
            Typeid.plWaveSet7,
            Typeid.plWin32StaticSound,
            Typeid.plWin32StreamingSound,
            Typeid.plWinAudio,
            
        };
        Typeid[] potstypes = {};
        Typeid[] myst5types = {};
        Typeid[] moultypes = {};
        boolean dopots = shared.State.AllStates.getStateAsBoolean("readAllFromPots");
        boolean docrow = shared.State.AllStates.getStateAsBoolean("readAllFromCrowthistle");
        boolean domyst5 = shared.State.AllStates.getStateAsBoolean("readAllFromMystv");
        boolean domoul = shared.State.AllStates.getStateAsBoolean("readAllFromMoul");
        
        
        //Typeid[] crowtypes = {}; //this should just be the myst5types.
        String[] dirs = {potsdir,crowdir,myst5dir,mouldir};
        
        Typeid[] potstypes2 = new Typeid[alltypes.length+potstypes.length];
        int i=0;
        for(int j=0;j<alltypes.length;j++) { potstypes2[i] = alltypes[j]; i++; }
        for(int j=0;j<potstypes.length;j++) { potstypes2[i] = potstypes[j]; i++; }
        
        Typeid[] myst5types2 = new Typeid[alltypes.length+myst5types.length];
        i=0;
        for(int j=0;j<alltypes.length;j++) { myst5types2[i] = alltypes[j]; i++; }
        for(int j=0;j<myst5types.length;j++) { myst5types2[i] = myst5types[j]; i++; }

        Typeid[] moultypes2 = new Typeid[alltypes.length+moultypes.length];
        i=0;
        for(int j=0;j<alltypes.length;j++) { moultypes2[i] = alltypes[j]; i++; }
        for(int j=0;j<moultypes.length;j++) { moultypes2[i] = moultypes[j]; i++; }
        /*class fileinfo
        {
            File file;
        }*/
        
        //get a list of all the prp files.
        //Vector<File> allfiles = new Vector<File>();
        for(int dircount = 0;dircount<dirs.length;dircount++)
        {
            String dir = dirs[dircount];
            
            Typeid[] readable = null;
            if(dircount==0)
            {
                readable = potstypes2;
                if(!dopots) continue;
            }
            else if(dircount==1)
            {
                readable = myst5types2;
                if(!docrow) continue;
            }
            else if(dircount==2)
            {
                readable = myst5types2;
                if(!domyst5) continue;
            }
            else if(dircount==3)
            {
                readable = moultypes2;
                if(!domoul) continue;
            }
            else
            {
                m.err("Programming error.");
                return;
            }
            
            File prpfolder = new File(dir+"/dat/");
            if (!prpfolder.isDirectory() || !prpfolder.exists())
            {
                m.err("Prp directory not in proper format or not found: "+dir);
                return;
            }
            File[] files = prpfolder.listFiles();
            for(File curfile: files)
            {
                if(curfile.getName().toLowerCase().endsWith(".prp"))
                {
                    //process file...
                    //allfiles.add(file);
                    
                    //open prp file and process it.
                    byte[] filedata = FileUtils.ReadFile(curfile);

                    //do work.
                    context c = context.createFromBytestream(new Bytestream(filedata));
                    //c.readversion = version;
                    c.curFile = curfile.getPath();
                    //uru.moulprp.prpprocess.ProcessAllObjects(c);
                    prpfile prp = prpfile.createFromContext(c, readable);
                }
            }
        }
        //m.msg("Parsing files... count="+Integer.toString(allfiles.size()));
        
        
    }
}
