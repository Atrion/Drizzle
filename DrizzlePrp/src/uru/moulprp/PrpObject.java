/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package uru.moulprp;

import uru.context;
import uru.Bytedeque;
import shared.readexception;
import shared.m;

public class PrpObject extends uruobj
{
    uruobj object;
    
    public uruobj getObject(context c, Typeid type) throws readexception
    {
        switch(type)
        {
            case plSceneNode:
                return new x0000Scenenode(c);
            case plSceneObject:
                return new x0001Sceneobject(c);
            //case 0x0002:
            //    return new x0002Keyedobject(stream,true);
            case plMipMap:
                return new x0004MipMap(c);
            case plCubicEnvironMap:
                return new x0005Environmap(c);
            case plLayer:
                return new x0006Layer(c);
            case hsGMaterial:
                return new x0007Material(c);
            case plParticleSystem:
                return new PlParticleSystem(c);
            case plBoundInterface:
                return new x000CBoundInterface(c);
            case plAudioInterface:
                return new x0011AudioInterface(c);
            case plWinAudio:
                return new x0014WinAudio(c);
            case plCoordinateInterface:
                return new x0015CoordinateInterface(c);
            case plDrawInterface:
                return new x0016DrawInterface(c);
            case plSpawnModifier:
                return new x003DSpawnModifier(c);
            case plDrawableSpans:
                return new PlDrawableSpans(c);
            case plDirectionalLightInfo:
                return new x0055DirectionalLightInfo(c);
            case plOmniLightInfo:
                return new x0056OmniLightInfo(c);
            //case plLayerAnimation:
            //    return new x0043LayerAnimation(stream,true);
            //case 0x0027:
            //    return new x0027MultiModifier(stream,true);
            //case 0x0028:
            //    return new x0028Synchedobject(stream,true);
            case plPythonFileMod:
                return new x00A2Pythonfilemod(c);
            case plPointShadowMaster:
                return new x00D5PointShadowMaster(c);
            case plSimulationInterface:
                return new x001CSimulationInterface(c);
            case plHKPhysical:
                return new PlHKPhysical(c);
            case plViewFaceModifier:
                return new PlViewFaceModifier(c);
            case plSittingModifier: //haven't
                return new x00AESittingModifier(c);
            case plStereizer:
                return new x00FFStereizer(c);
            case plSoundBuffer:
                return new x0029SoundBuffer(c);
            case plRandomSoundMod:
                return new PlRandomSoundMod(c);
            case plWin32StreamingSound:
                return new x0084Win32StreamingSound(c);
            case plWin32StaticSound:
                return new x0096Win32StaticSound(c);
            case plParticleLocalWind:
                return new x00D0ParticleLocalWind(c);
            case plParticleCollisionEffectDie:
                return new x00C9ParticleCollisionEffectDie(c);
            case plExcludeRegionModifier:
                return new x00A4ExcludeRegionModifier(c);
            case plCameraBrain1:
                return new PlCameraBrain1(c);
            case plCameraBrain1_Avatar:
                return new PlCameraBrain1_Avatar(c);
            case plCameraBrain1_Fixed:
                return new PlCameraBrain1_Fixed(c);
            case plCameraBrain1_Circle:
                return new PlCameraBrain1_Circle(c);
            case plCameraModifier1:
                return new x009BCameraModifier1(c);
            case plRailCameraMod:
                return new PlRailCameraMod(c);
            case plAGMasterMod:
                return new PlAGMasterMod(c);
            case plAGModifier:
                return new PlAGModifier(c);
            case plOccluder:
                return new PlOccluder(c);
            //case plLimitedDirLightInfo:
            //    return new PlLimitedDirLightInfo(stream,true);
            case plDynamicTextMap:
                return new PlDynamicTextMap(c);
            case plParticleCollisionEffectBounce:
                return new PlParticleCollisionEffectBounce(c);
            case plSpotLightInfo:
                return new x0057SpotLightInfo(c);
            case plShadowCaster:
                return new PlShadowCaster(c);
            case plDirectShadowMaster:
                return new PlDirectShadowMaster(c);
            case plRelevanceRegion:
                return new PlRelevanceRegion(c);
            case plSoftVolumeSimple:
                return new PlSoftVolumeSimple(c);
            case plResponderModifier:
                return new PlResponderModifier(c);
            case plParticleFlockEffect:
                return new PlParticleFlockEffect(c);
            case plFadeOpacityMod:
                return new PlFadeOpacityMod(c);
            case plClusterGroup:
                return new PlClusterGroup(c);
            case plVisRegion:
                return new PlVisRegion(c);
            case plSoftVolumeUnion:
                return new PlSoftVolumeUnion(c);
            case plObjectInVolumeDetector:
                return new PlObjectInVolumeDetector(c);
            case plObjectInBoxConditionalObject:
                return new PlObjectInBoxConditionalObject(c);
            case plInterfaceInfoModifier:
                return new PlInterfaceInfoModifier(c);
            case plVolumeSensorConditionalObject:
                return new PlVolumeSensorConditionalObject(c);
            case plLogicModifier:
                return new PlLogicModifier(c);
            case plActivatorConditionalObject:
                return new PlActivatorConditionalObject(c);
            case plFacingConditionalObject:
                return new PlFacingConditionalObject(c);
            case plOneShotMod:
                return new PlOneShotMod(c);
            case plAvLadderMod:
                return new PlAvLadderMod(c);
            case plDynaFootMgr:
                return new PlDynaFootMgr(c);
            case plPickingDetector:
                return new PlPickingDetector(c);
            case plCameraRegionDetector:
                return new PlCameraRegionDetector(c);
                
                
                
            //case plSceneNode: //really used as a null here.
            //    break;
            case plExcludeRegionMsg:
                return new PrpMessage.PlExcludeRegionMsg(c);
            case plSoundMsg:
                return new PrpMessage.PlSoundMsg(c);
            case plEnableMsg:
                return new PrpMessage.PlEnableMsg(c);
            case plNotifyMsg:
                return new PrpMessage.PlNotifyMsg(c);
            case plOneShotMsg:
                return new PrpMessage.PlOneShotMsg(c);
            case plArmatureEffectStateMsg:
                return new PrpMessage.PlArmatureEffectStateMsg(c);
            case plLinkToAgeMsg:
                return new PrpMessage.PlLinkToAgeMsg(c);
            case plAnimCmdMsg:
                return new PrpMessage.PlAnimCmdMsg(c);
            case plTimerCallbackMsg:
                return new PrpMessage.PlTimerCallbackMsg(c);
            case plEventCallbackMsg:
                return new PrpMessage.PlEventCallbackMsg(c);
            case plCameraMsg:
                return new PrpMessage.PlCameraMsg(c);

            case plParticleEmitter:
                return new PlParticleSystem.plParticleEmitter(c);
            case plOneTimeParticleGenerator:
                return new PlParticleSystem.plOneTimeParticleGenerator(c);
            case plSimpleParticleGenerator:
                return new PlParticleSystem.plSimpleParticleGenerator(c);
            case plSoftVolumeIntersect:
                return new PlSoftVolumeIntersect(c);
            case plEAXListenerMod:
                return new PlEAXListenerMod(c);
            case plPhysicalSndGroup:
                return new PlPhysicalSndGroup(c);
            case plSeekPointMod:
                return new PlSeekPointMod(c);
            case plAnimPath:
                return new PlRailCameraMod.plAnimPath(c);
            case plCompoundController:
                return new PrpController.plCompoundController(c);
            case plLeafController:
                return new PrpController.plLeafController(c);
            case plLayerAnimation:
                return new PlLayerAnimation(c);
            case plScalarController:
                return new PrpController.plScalarController(c);
            case plMatrix44Controller:
                return new PrpController.plMatrix44Controller(c);
            case plSimplePosController:
                return new PrpController.plSimplePosController(c);
            case plCompoundPosController:
                return new PrpController.plCompoundPosController(c);
            case plSimpleRotController:
                return new PrpController.plSimpleRotController(c);
            case plCompoundRotController:
                return new PrpController.plCompoundRotController(c);
            case plSimpleScaleController:
                return new PrpController.plSimpleScaleController(c);
            case plATCAnim:
                return new PlATCAnim(c);
            case plMatrixChannelApplicator:
                return new PlAGAnim.plMatrixChannelApplicator(c);
            case plMatrixControllerChannel:
                return new PlAGAnim.plMatrixControllerChannel(c);
            case plParticlePPSApplicator:
                return new PlAGAnim.plParticlePPSApplicator(c);
            case plScalarControllerChannel:
                return new PlAGAnim.plScalarControllerChannel(c);
            case plTMController:
                return new PrpController.plTMController(c);
            case plPanicLinkRegion:
                return new PlPanicLinkRegion(c);
            case plLineFollowMod:
                return new PlLineFollowMod(c);
            case plMsgForwarder:
                return new PlMsgForwarder(c);
            case plAnimEventModifier:
                return new PlAnimEventModifier(c);
            case plMultiStageBehMod:
                return new PlMultiStageBehMod(c);
            case plLightDiffuseApplicator:
                return new EmbeddedClasses.PlLightDiffuseApplicator(c);
            case plPointControllerChannel:
                return new EmbeddedClasses.PlPointControllerChannel(c);
            case plImageLibMod:
                return new PlImageLibMod(c);
            case plLimitedDirLightInfo:
                return new PlLimitedDirLightInfo(c);
            case plAgeGlobalAnim:
                return new PlAgeGlobalAnim(c);
            case plLightSpecularApplicator:
                return new EmbeddedClasses.PlLightSpecularApplicator(c);
            case plDynaPuddleMgr:
                return new PlDynaPuddleMgr(c);
            case plWaveSet7:
                return new PlWaveSet7(c);
            case plDynamicEnvMap:
                return new PlDynamicEnvMap(c);
            case plOmniSqApplicator:
                return new EmbeddedClasses.PlOmniSqApplicator(c);
            case plRidingAnimatedPhysicalDetector:
                return new PlRidingAnimatedPhysicalDetector(c);
            case plGrassShaderMod:
                return new PlGrassShaderMod(c);
            case plDynamicCamMap:
                return new PlDynamicCamMap(c);
            case plRideAnimatedPhysMsg:
                return new PrpMessage.PlRideAnimatedPhysMsg(c);
            case plSoftVolumeInvert:
                return new PlSoftVolumeInvert(c);
            case plLayerBink:
                return new PlLayerBink(c);
            case plRelativeMatrixChannelApplicator:
                return new PlAGAnim.plRelativeMatrixChannelApplicator(c);
            case plSoundVolumeApplicator:
                return new EmbeddedClasses.PlSoundVolumeApplicator(c);
            case plPostEffectMod:
                return new PlPostEffectMod(c);
            case plSimSuppressMsg:
                return new PrpMessage.PlSimSuppressMsg(c);
            case plAxisAnimModifier:
                return new PlAxisAnimModifier(c);
            case pfGUIButtonMod:
                return new PfGUIButtonMod(c);
            case pfGUIDialogMod:
                return new PfGUIButtonMod.PfGUIDialogMod(c);
            case plConstAccelEaseCurve:
                return new PlConstAccelEaseCurve(c);
            case plMobileOccluder:
                return new PlMobileOccluder(c);
            case plLayerLinkAnimation:
                return new PlLayerLinkAnimation(c);
            case plArmatureMod:
                return new PlArmatureMod(c);
            case plAvBrainPirahna:
                return new PlAvBrainPirahna(c);
            case plAvBrainQuab:
                return new PlAvBrainQuab(c);
            case plArmatureEffectsMgr:
                return new PlArmatureEffectsMgr(c);
            case plFilterCoordInterface:
                return new PlFilterCoordInterface(c);
            case plParticleFollowSystemEffect:
                return new PlParticleFollowSystemEffect(c);
            case plParticleCollisionEffectBeat:
                return new PlParticleCollisionEffectBeat(c);
            case plParticleFadeVolumeEffect:
                return new PlParticleFadeVolumeEffect(c);
            case pfGUIKnobCtrl:
                return new PfGUIButtonMod.PfGUIKnobCtrl(c);
            default:
                //m.err("prprootobject: unhandled type.");
                throw new readexception("PrpObject: type constructor not in main list: "+type.toString());
        }
    }

    public PrpObject(context c, Typeid type) throws readexception
    {
        object = this.getObject(c, type);
    }
    
    public void compile(Bytedeque c)
    {
        object.compile(c);
    }
    
    public String toString()
    {
        return object.toString();
    }
    
        
}
