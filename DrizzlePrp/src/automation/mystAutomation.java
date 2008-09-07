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
                if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                             
                            
                             
                
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
    };
    
    public static Typeid[] crowReadable = moulReadable;
    /*public static Typeid[] crowReadable={
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
    };*/
    
    public static void convertEoaToWhatdoyousee(String infile, String outfile)
    {
        Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
        Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
        Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
        FileUtils.WriteFile(outfile, wdysData);
    }
    public static String getAgenameFromFilename(String filename)
    {
        int pos = filename.lastIndexOf("/");
        if(pos != -1)
        {
            filename = filename.substring(pos+1);
        }
        
        pos = filename.lastIndexOf("\\");
        if(pos != -1)
        {
            filename = filename.substring(pos+1);
        }
        
        pos = filename.lastIndexOf(".");
        if(pos != -1)
        {
            filename = filename.substring(0, pos);
        }
        
        pos = filename.indexOf("_");
        if(pos != -1)
        {
            filename = filename.substring(0,pos);
        }
        
        return filename;
    }
    
    public static void convertMoulToPots(String infolder, String outfolder, Vector<String> files)
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
                if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).

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
                };
                String[] namestarts={
                };
                for(Typeid curtype: typeequals) if(curtype==type) return true;
                for(String start: namestarts) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                
                m.msg("Skipping type: "+type.toString());
                return false;
            }
        }

        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("Payiferen", 0x63);
        prefices.put("Kveer", 0x62);
        prefices.put("EderTsogal", 0x61);
        
        HashMap<String, String> agenames = new HashMap<String, String>();
        agenames.put("Kveer", "KveerMoul");
        
        Typeid[] readable = mystAutomation.moulReadable;
        
        
        //Handle .fni files...
        Vector<String> fnifiles = filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = UruCrypt.DecryptWhatdoyousee(encryptedData);// UruCrypt.DecryptEoa(encryptedData);
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .age files...
        Vector<String> agefiles = filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);
            
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
            
            //
            
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .prp files...
        Vector<String> prpfiles = filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);//.replace("_", "_District_");
            
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
            Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
            prpoutputbytes.saveAsFile(outfile);
        }
        
        
        //Handle .sum files...
        Vector<String> sumfiles = filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = getAgenameFromFilename(filename);
            Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", replaceAgenameIfApplicable(agename, agenames));
            FileUtils.WriteFile(outfolder+"/dat/"+replaceAgenameIfApplicable(filename, agenames), sum1);
        }
        
        
        //All done!
        m.msg("Done Moul work!");
    }
    
    public static void convertCrowthistleToPots(String crowthistlefolder, String potsfolder)
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
                
                m.msg("Skipping type: "+type.toString());
                return false;
            }
            
        }
        
        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("MarshScene", 96);
        prefices.put("MountainScene", 95);
        
        String[] fnifiles = {
            "MarshScene.fni",
            "MountainScene.fni",
        };
        String[] agefiles = {
            "MarshScene.age",
            "MountainScene.age",
        };
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

        //create folders...
        FileUtils.CreateFolder(potsfolder+"/dat/");

        //convert .fni files...
        for(String filename: fnifiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename;
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .age files...
        for(String filename: agefiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename;
            String agename = getAgenameFromFilename(filename);
            
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
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .prp files...
        for(String filename: prpfiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename.replace("_", "_District_");
            String agename = getAgenameFromFilename(filename);
            
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
            Bytes prpoutputbytes = prp.saveAsBytes(new crowDecider());
            prpoutputbytes.saveAsFile(outfile);
        }
        
        //create .sum files...
        Bytes sum1 = uru.moulprp.sumfile.createSumfile(potsfolder+"/dat/", "MarshScene");
        FileUtils.WriteFile(potsfolder+"/dat/MarshScene.sum", sum1);
        Bytes sum2 = uru.moulprp.sumfile.createSumfile(potsfolder+"/dat/", "MountainScene");
        FileUtils.WriteFile(potsfolder+"/dat/MountainScene.sum", sum2);
        
        
        m.msg("Done Crowthistle work!");
    }
    
    public static void convertMyst5ToPots(String infolder, String outfolder, Vector<String> files)
    {
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                //return false;
                Typeid type = desc.objecttype;
                String name = desc.objectname.toString();
                Typeid[] typeequals = new Typeid[]{
                    Typeid.plSceneNode, Typeid.plSceneObject, Typeid.plCoordinateInterface, Typeid.plSpawnModifier,
                    Typeid.plMipMap, Typeid.plCubicEnvironMap,
                    Typeid.plLayer, Typeid.hsGMaterial, Typeid.plDrawableSpans,
                    Typeid.plViewFaceModifier,
                    Typeid.plLayerAnimation,
                    Typeid.plLayerBink, //must have the .bnk files copied over or Uru will crash.
                    //Typeid.plBoundInterface,
                    
                    Typeid.plHKPhysical, Typeid.plSimulationInterface,
                    Typeid.plDirectionalLightInfo, Typeid.plOmniLightInfo, Typeid.plSpotLightInfo,
                    Typeid.plAGMasterMod, Typeid.plAGModifier, Typeid.plATCAnim,
                    Typeid.plParticleSystem, Typeid.plParticleLocalWind, Typeid.plParticleCollisionEffectDie,
                    Typeid.plAudioInterface, Typeid.plRandomSoundMod, Typeid.plSoundBuffer, Typeid.plWinAudio, Typeid.plWin32StreamingSound, Typeid.plWin32StaticSound, Typeid.plStereizer,
                    Typeid.plDrawInterface,
                    //Typeid.plSoftVolumeSimple,
                    //Typeid.plOccluder, Typeid.plShadowCaster, Typeid.plSoftVolumeInvert, Typeid.plSoftVolumeUnion,
                    //Typeid.plObjectInBoxConditionalObject, Typeid.plObjectInVolumeDetector,
                    //Typeid.plActivatorConditionalObject, Typeid.plFacingConditionalObject, Typeid.plVolumeSensorConditionalObject,
                    //Typeid.plInterfaceInfoModifier, Typeid.plLogicModifier,
                    //Typeid.plPointShadowMaster,
                    //Typeid.plDynamicEnvMap,
                    //Typeid.plWaveSet7,
                    //Typeid.plPickingDetector, Typeid.plMsgForwarder, Typeid.plLineFollowMod, Typeid.plExcludeRegionModifier,
                    //Typeid.plPythonFileMod,
                    //Typeid.plResponderModifier,
                };
                String[] namestarts={
                    /*//"boulder01",
                    "bridge poles05", //works when plLayerBink is present.
                    "bubble01haloa01", //works!*/
                };
                for(Typeid curtype: typeequals) if(curtype==type) return true;
                for(String start: namestarts) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                
                m.msg("Skipping type: "+type.toString());
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
        
        Typeid[] readable = mystAutomation.moulReadable;
        
        
        //Handle .fni files...
        Vector<String> fnifiles = filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .age files...
        Vector<String> agefiles = filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);
            
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
            
            //
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .prp files...
        Vector<String> prpfiles = filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames).replace("_", "_District_");
            
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
            Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
            prpoutputbytes.saveAsFile(outfile);
        }
        
        
        //Handle .sum files...
        Vector<String> sumfiles = filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = getAgenameFromFilename(filename);
            Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", replaceAgenameIfApplicable(agename, agenames));
            FileUtils.WriteFile(outfolder+"/dat/"+replaceAgenameIfApplicable(filename, agenames), sum1);
        }
        
        
        //All done!
        m.msg("Done MystV work!");
    }
    
    public static String replaceAgenameIfApplicable(String filename, HashMap<String, String>agenames)
    {
        String agename = getAgenameFromFilename(filename);
        String newagename = agenames.get(agename);
        if(newagename!=null)
        {
            return newagename+filename.substring(agename.length());
        }
        else
        {
            return filename;
        }
    }
    
    public static Vector<String> filterFilenamesByExtension(Vector<String> files, String extension)
    {
        Vector<String> result = new Vector<String>();
        for(String file: files) if(file.endsWith(extension)) result.add(file);
        return result;
    }
    public static void readAllPotsPrps(String prpdirname)
    {
        File prpfolder = new File(prpdirname);
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
                uru.moulprp.prpprocess.ProcessAllObjects(c);
                //if(version==3) prputils.ProcessPotsPrp(filedata);
                //if(version==6) prputils.ProcessAll(filedata);
            }
        }
        
        m.msg("Finished Processing all files.");
        
    }
}
