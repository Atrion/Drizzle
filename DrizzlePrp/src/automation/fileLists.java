/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import java.util.Vector;

public class fileLists
{
    //These are the oggs from MystV, that aren't already present in Pots or MoulOffline(they might be in the rest of Moul).
    //As it turns out, this is the same as the ones that simply aren't present in Pots.
    public static String[] mystvOggsNotInPotsNorMouloffline = {
        "drboCalmNight-Distant_Loop.ogg","drboEsher-DireboLaki_eng.ogg","drboEsher-DireboLaki_fre.ogg","drboEsher-DireboLaki_ger.ogg","drboEsher-DireboLaki_spa.ogg","drboEsher-DireboSrln_eng.ogg","drboEsher-DireboSrln_fre.ogg","drboEsher-DireboSrln_ger.ogg","drboEsher-DireboSrln_spa.ogg","drboEsher-DireboTdlm_eng.ogg","drboEsher-DireboTdlm_fre.ogg","drboEsher-DireboTdlm_ger.ogg","drboEsher-DireboTdlm_spa.ogg","drboEsher-DireboTghr_eng.ogg","drboEsher-DireboTghr_fre.ogg","drboEsher-DireboTghr_ger.ogg","drboEsher-DireboTghr_spa.ogg","drboGateButton.ogg","drboGate_Close.ogg","drboGate_LatchClose.ogg","drboGate_LeverClose.ogg","drboGate_LeverOpen.ogg","drboGate_Open.ogg","drboLakiBubbleAmb_Loop.ogg","drboRandomCricket01.ogg","drboRandomCricket02.ogg","drboRandomCricket03.ogg","drboRandomCritter01.ogg","drboRandomCritter02.ogg","drboRandomCritter03.ogg","drboRandomCritter04a.ogg","drboRandomCritter04b.ogg","drboRandomCritter04c.ogg","drboRandomCritter04d.ogg","drboRandomCritter04e.ogg","drboRandomCritter04f.ogg","drboRandomCritter04g.ogg","drboRandomCritter05.ogg","drboRandomCritter06a.ogg","drboRandomCritter06b.ogg","drboRandomCritter06c.ogg","drboRandomCritter06d.ogg","drboRandomCritter07a.ogg","drboRandomCritter07b.ogg","drboRandomCritter07c.ogg","drboRandomCritter07d.ogg","drboRandomCritter09a.ogg","drboRandomCritter09b.ogg","drboRandomCritter10.ogg","drboRandomCritter12a.ogg","drboRandomCritter12b.ogg","drboRockRandom01.ogg","drboRockRandom02.ogg","drboRockRandom03.ogg","drboRockRandom04.ogg","drboRockRandom05.ogg","drboRockRandom06.ogg","drboSiralehn-BubbleMix_Loop.ogg","drboTdlmBubbleAmb_Loop.ogg","drboThgrBubbleAmb_Loop.ogg","drboWaterLaps_Loop.ogg",
        "dsntAirShaftLadder_down.ogg","dsntAirShaftLadder_up.ogg","dsntBatFlock01_loop.ogg","dsntBats-Rnd01.ogg","dsntBats-Rnd02.ogg","dsntBats-Rnd03.ogg","dsntBats-Rnd04.ogg","dsntBats-Rnd05.ogg","dsntBats-Rnd06.ogg","dsntBats-Rnd07.ogg","dsntBats-Rnd08.ogg","dsntBats-Rnd09.ogg","dsntBats-Rnd10.ogg","dsntCave-PoolDrips_Loop.ogg","dsntCave-WaterDrips_Loop.ogg","dsntCave-WaterTrickle_Loop.ogg","dsntCave-WindAmb_Loop01.ogg","dsntDoor-Switch_Broken.ogg","dsntEarthquakeLoop.ogg","dsntElevator-Handle.ogg","dsntElevatorMusic.ogg","dsntElevLeverEngage.ogg","dsntElevLever_end.ogg","dsntEsher-BottomOfShaft_eng.ogg","dsntEsher-BottomOfShaft_fre.ogg","dsntEsher-BottomOfShaft_ger.ogg","dsntEsher-BottomOfShaft_spa.ogg","dsntEsher-FirstHub_eng.ogg","dsntEsher-FirstHub_fre.ogg","dsntEsher-FirstHub_ger.ogg","dsntEsher-FirstHub_spa.ogg","dsntEsher-Intro_eng.ogg","dsntEsher-Intro_fre.ogg","dsntEsher-Intro_ger.ogg","dsntEsher-Intro_Mx.ogg","dsntEsher-Intro_spa.ogg","dsntEsher-TopOfShaft_eng.ogg","dsntEsher-TopOfShaft_fre.ogg","dsntEsher-TopOfShaft_ger.ogg","dsntEsher-TopOfShaft_spa.ogg","dsntFan-NodeAmb_Loop.ogg","dsntFan-On_Loop.ogg","dsntFan-Power_Off.ogg","dsntFan-Power_On.ogg","dsntFan-Switch_Jiggle.ogg","dsntFloorColorButtonPress.ogg","dsntFloorElev-Countdown01.ogg","dsntFloorElev-Countdown02.ogg","dsntFloorElev-Countdown03.ogg","dsntFloorElev-Drive_Loop.ogg","dsntFloorElev-Gears_Loop.ogg","dsntFloorElev-Gears_Start.ogg","dsntFloorElev-Gears_Stop.ogg","dsntFloorElev-Pillars_End.ogg","dsntFloorElev-Pillars_Loop.ogg","dsntFloorElev-TimerSwitch.ogg","dsntFloorLowButton_down.ogg","dsntFloorLowButton_up.ogg","dsntFloorRaiseWarn.ogg","dsntFloorUpButton_down.ogg","dsntFloorUpButton_up.ogg","dsntGeneratorAmb_Loop.ogg","dsntImagerButtonClick.ogg","dsntImagerSquelch.ogg","dsntImager_Loop.ogg","dsntLavaDoor_Open.ogg","dsntMarbles_Loop.ogg","dsntNodeAmb_Loop.ogg","dsntNodeDoor_Open.ogg","dsntNodeLeverDragLoop.ogg","dsntRandomRocks01.ogg","dsntRandomRocks02.ogg","dsntRandomRocks03.ogg","dsntRndAbstractAmb01.ogg","dsntRndAbstractAmb02.ogg","dsntRndAbstractAmb03.ogg","dsntRndAbstractAmb04.ogg","dsntRndAbstractAmb05.ogg","dsntRndAbstractAmb06.ogg","dsntRndAbstractAmb07.ogg","dsntRndAbstractAmb08.ogg","dsntSafetyGate_close.ogg","dsntSafetyGate_open.ogg","dsntShaftAmb02_Loop.ogg","dsntSteamPipe01_Loop.ogg","dsntSteamPipe02_Loop.ogg","dsntSteamVents_Loop.ogg","dsntUpperElev-Away_Bottom.ogg","dsntUpperElev-Away_Top.ogg","dsntUpperElev-Dock_Bottom.ogg","dsntUpperElev-Dock_Top.ogg","dsntUpperElev-WeightsJiggle.ogg","dsntUpperElev-Weights_Loop.ogg","dsntUpperElevator_Ride.ogg","dsntUpperElevator_Start.ogg","dsntUpperElevator_Stop.ogg","dsntYeesha-Imager01Mx.ogg","dsntYeesha-Imager01_eng.ogg","dsntYeesha-Imager01_fre.ogg","dsntYeesha-Imager01_ger.ogg","dsntYeesha-Imager01_spa.ogg","dsntYeesha-Imager02Mx.ogg","dsntYeesha-Imager02_eng.ogg","dsntYeesha-Imager02_fre.ogg","dsntYeesha-Imager02_ger.ogg","dsntYeesha-Imager02_spa.ogg","dsntYeesha-Imager03Mx.ogg","dsntYeesha-Imager03_eng.ogg","dsntYeesha-Imager03_fre.ogg","dsntYeesha-Imager03_ger.ogg","dsntYeesha-Imager03_spa.ogg","dsntYeesha-Journal00_eng.ogg","dsntYeesha-Journal00_fre.ogg","dsntYeesha-Journal00_ger.ogg","dsntYeesha-Journal00_spa.ogg","dsntYeesha-Journal01_eng.ogg","dsntYeesha-Journal01_fre.ogg","dsntYeesha-Journal01_ger.ogg","dsntYeesha-Journal01_spa.ogg","dsntYeesha-Journal02_eng.ogg","dsntYeesha-Journal02_fre.ogg","dsntYeesha-Journal02_ger.ogg","dsntYeesha-Journal02_spa.ogg","dsntYeesha-Journal03_eng.ogg","dsntYeesha-Journal03_fre.ogg","dsntYeesha-Journal03_ger.ogg","dsntYeesha-Journal03_spa.ogg","dsntYeesha-Journal04_eng.ogg","dsntYeesha-Journal04_fre.ogg","dsntYeesha-Journal04_ger.ogg","dsntYeesha-Journal04_spa.ogg","dsntYeesha-Journal05_eng.ogg","dsntYeesha-Journal05_fre.ogg","dsntYeesha-Journal05_ger.ogg","dsntYeesha-Journal05_spa.ogg","dsntYeesha-Journal06_eng.ogg","dsntYeesha-Journal06_fre.ogg","dsntYeesha-Journal06_ger.ogg","dsntYeesha-Journal06_spa.ogg","dsntYeesha-Journal07_eng.ogg","dsntYeesha-Journal07_fre.ogg","dsntYeesha-Journal07_ger.ogg","dsntYeesha-Journal07_spa.ogg","dsntYeesha-Journal08_eng.ogg","dsntYeesha-Journal08_fre.ogg","dsntYeesha-Journal08_ger.ogg","dsntYeesha-Journal08_spa.ogg","dsntYeesha-Journal09_eng.ogg","dsntYeesha-Journal09_fre.ogg","dsntYeesha-Journal09_ger.ogg","dsntYeesha-Journal09_spa.ogg","dsntYeesha-Journal10_eng.ogg","dsntYeesha-Journal10_fre.ogg","dsntYeesha-Journal10_ger.ogg","dsntYeesha-Journal10_spa.ogg","dsntYeesha-Journal11_eng.ogg","dsntYeesha-Journal11_fre.ogg","dsntYeesha-Journal11_ger.ogg","dsntYeesha-Journal11_spa.ogg",
        "glblEsher-AfterSlate_eng.ogg","glblEsher-AfterSlate_fre.ogg","glblEsher-AfterSlate_ger.ogg","glblEsher-AfterSlate_spa.ogg","glblEsher-FirstAgeKeep_eng.ogg","glblEsher-FirstAgeKeep_fre.ogg","glblEsher-FirstAgeKeep_ger.ogg","glblEsher-FirstAgeKeep_spa.ogg","glblEsher-FirstAgeTake_eng.ogg","glblEsher-FirstAgeTake_fre.ogg","glblEsher-FirstAgeTake_ger.ogg","glblEsher-FirstAgeTake_spa.ogg","glblEsher-FourthAgeKeep_eng.ogg","glblEsher-FourthAgeKeep_fre.ogg","glblEsher-FourthAgeKeep_ger.ogg","glblEsher-FourthAgeKeep_spa.ogg","glblEsher-SecondAgeKeep_eng.ogg","glblEsher-SecondAgeKeep_fre.ogg","glblEsher-SecondAgeKeep_ger.ogg","glblEsher-SecondAgeKeep_spa.ogg","glblEsher-ThirdAgeKeep_eng.ogg","glblEsher-ThirdAgeKeep_fre.ogg","glblEsher-ThirdAgeKeep_ger.ogg","glblEsher-ThirdAgeKeep_spa.ogg",
        "kverAmb_Loop.ogg","kverAtrus_1_eng.ogg","kverAtrus_1_fre.ogg","kverAtrus_1_ger.ogg","kverAtrus_1_spa.ogg","kverBahroSneakUp.ogg","kverBahroTakeOff.ogg","kverBahroWingsCover.ogg","kverConc03MxPart01.ogg","kverConc03MxPart02.ogg","kverEsher_1_eng.ogg","kverEsher_1_fre.ogg","kverEsher_1_ger.ogg","kverEsher_1_spa.ogg","kverMystBookLocked.ogg","kverPrisonDoorHandle.ogg","kverPrisonDoor_Open.ogg","kverRandAmb01.ogg","kverReleeshanAmb.ogg","kverSwingingLamp_loop.ogg","kverTabletMaterialize.ogg","kverYeesha-Conc03_eng.ogg","kverYeesha-Conc03_fre.ogg","kverYeesha-Conc03_ger.ogg","kverYeesha-Conc03_spa.ogg","kverYeesha-IntroMx.ogg","kverYeesha-Intro_eng.ogg","kverYeesha-Intro_fre.ogg","kverYeesha-Intro_ger.ogg","kverYeesha-Intro_spa.ogg","kverYeeshaConc02Mx.ogg","kverYeesha_1_eng.ogg","kverYeesha_1_fre.ogg","kverYeesha_1_ger.ogg","kverYeesha_1_spa.ogg",
        "lakiArena-PedDownFull.ogg","lakiArena-PedDownHalf.ogg","lakiArena-PedUpFull.ogg","lakiArena-PedUpHalf.ogg","lakiArena-RevealMusic.ogg","LakiArena-ScaleLight_Off1.ogg","LakiArena-ScaleLight_On1.ogg","LakiArena-ScaleLight_On2.ogg","LakiArena-ScaleLight_On3.ogg","LakiArena-WeightBtnClose.ogg","LakiArena-WeightBtnOpen.ogg","lakiArena_PedEnd.ogg","lakiBird-Rnd01.ogg","lakiBird-Rnd02a.ogg","lakiBird-Rnd02b.ogg","lakiBird-Rnd02c.ogg","lakiBird-Rnd02d.ogg","lakiBird-Rnd02e.ogg","lakiBird-Rnd02f.ogg","lakiBird-Rnd02g.ogg","lakiBird-Rnd02h.ogg","lakiBird-Rnd03.ogg","lakiBird-Rnd04.ogg","lakiBird-Rnd06c.ogg","lakiBird-Rnd08.ogg","lakiBird-Rnd09.ogg","lakiBird-Rnd10a.ogg","lakiBird-Rnd10b.ogg","lakiBird-Rnd11.ogg","lakiBird-Rnd15.ogg","lakiBird-Rnd16.ogg","lakiBird-Rnd17.ogg","lakiBird-Rnd18a.ogg","lakiBird-Rnd18b.ogg","lakiBird-Rnd18c.ogg","lakiBird-Rnd18d.ogg","lakiBird-Rnd18e.ogg","lakiBird-Rnd18f.ogg","lakiBird-Rnd18g.ogg","lakiBird-Rnd20.ogg","lakiBird-Rnd21.ogg","lakiBird-Rnd23.ogg","lakiBird-Rnd24.ogg","lakiBird-Rnd25.ogg","lakiBoulderFallLarge.ogg","lakiBoulderFallMed.ogg","lakiBoulderFallSmall.ogg","lakiCageDoorBtn.ogg","lakiCageDoors_Close.ogg","lakiCageDoors_Open.ogg","lakiElevatorLever-Latch01.ogg","lakiElevatorLeverDrag_Loop.ogg","lakiEsher-Arena_eng.ogg","lakiEsher-Arena_fre.ogg","lakiEsher-Arena_ger.ogg","lakiEsher-Arena_Mx.ogg","lakiEsher-Arena_spa.ogg","lakiEsher-FighterBeach_eng.ogg","lakiEsher-FighterBeach_fre.ogg","lakiEsher-FighterBeach_ger.ogg","lakiEsher-FighterBeach_spa.ogg","lakiEsher-Keep_eng.ogg","lakiEsher-Keep_fre.ogg","lakiEsher-Keep_ger.ogg","lakiEsher-Keep_Mx.ogg","lakiEsher-Keep_spa.ogg","lakiEsher-Villa_eng.ogg","lakiEsher-Villa_fre.ogg","lakiEsher-Villa_ger.ogg","lakiEsher-Villa_Mx.ogg","lakiEsher-Villa_spa.ogg","lakiHutFulcrum-DoorLand.ogg","lakiHutFulcrum-MoveFull.ogg","lakiHutFulcrum-MoveHalf.ogg","lakiHutPulley_loop.ogg","lakiMaze-Elevator_down.ogg","lakiMaze-Elevator_up.ogg","lakiMazeButton01.ogg","LakiMazeDoor1Close.ogg","LakiMazeDoor1Open.ogg","LakiMazeExitDoorClose.ogg","LakiMazeExitDoorOpen.ogg","lakiOceanAirAmb_Loop.ogg","lakiPirBird_Vocalize01.ogg","LakiPuzzleDoorBolt.ogg","LakiPuzzleDoorButton01.ogg","LakiPuzzleDoorButton02.ogg","LakiPuzzleDoorButton03.ogg","LakiPuzzleDoorOpen.ogg","lakiRandMusic01.ogg","lakiRandMusic02.ogg","lakiRandMusic03.ogg","lakiRandMusic04.ogg","lakiRandMusic05.ogg","lakiRandMusic06.ogg","lakiRandMusic07.ogg","lakiRandMusic08.ogg","lakiRandMusic09.ogg","lakiRegisterBtn_press.ogg","lakiRegisterBtn_unpress.ogg","lakiTunnelAmb_loop.ogg","lakiVillaFrontDoorClose.ogg","lakiVillaFrontDoorOpen.ogg","lakiWaterLaps_Loop.ogg","lakiWhaleCall_Loop.ogg","LakiWind-Beach_loop.ogg","lakiWindAmb01_Loop.ogg","LakiWindBranches_loop.ogg","LakiWindLeaves_loop.ogg","LakiWindMetal_loop.ogg","lakiWindmill-CageLever_On01.ogg","lakiWindmill-Lever_loop.ogg","lakiWindmill-PuzzleMusic_Loop.ogg","lakiWindmill_Loop.ogg","LakiWindThruFence_loop.ogg","lakiWindWaterCombo_Loop.ogg","laki_BahroCommandWind.ogg","Laki_ScalePlateDown.ogg","Laki_ScalePlateUp.ogg",
        "mystAmbMusic.ogg","mystCliffs-WavesBreak_Loop.ogg","mystDockWaveAmb.ogg","mystDoorSlam_Loop.ogg","mystEsher-Conc01Mx.ogg","mystEsher-Conc01_eng.ogg","mystEsher-Conc01_fre.ogg","mystEsher-Conc01_ger.ogg","mystEsher-Conc01_spa.ogg","mystEsher-Conc02Mx.ogg","mystEsher-Conc02_eng.ogg","mystEsher-Conc02_fre.ogg","mystEsher-Conc02_ger.ogg","mystEsher-Conc02_spa.ogg","mystHandleBreak.ogg","mystMastCreak01.ogg","mystMastCreak02.ogg","mystMastCreak03.ogg","mystOceanAir_Loop.ogg","mystRainOnGrass_InLoop.ogg","mystRainOnMetal-Ext_Loop01.ogg","mystRainOnMetal-Ext_Loop02.ogg","mystRainOnMetal-Int_Loop.ogg","mystRainOnWater_InLoop.ogg","mystShipCreak_Loop.ogg","mystTabletTrap.ogg","mystThunder01.ogg","mystThunder02.ogg","mystThunder03.ogg","mystThunder04.ogg","mystThunder05.ogg","mystThunder06.ogg","mystThunder07.ogg","mystWind-Animating_Loop.ogg","mystWind-SpookyAmb_Loop.ogg","mystWind-ThruTrees_Loop.ogg","mystWind_InLoop.ogg",
        "srlnBird01.ogg","srlnBird02.ogg","srlnBird03.ogg","srlnBird04.ogg","srlnBird05.ogg","srlnBird06.ogg","srlnBird07.ogg","srlnBird08.ogg","srlnBird09.ogg","srlnBird10.ogg","srlnBird11.ogg","srlnBird12.ogg","srlnBird13.ogg","srlnCliffLadder-Rope_loop.ogg","srlnCliffLadder_Drop.ogg","srlnEsher-NolobenBeach_eng.ogg","srlnEsher-NolobenBeach_fre.ogg","srlnEsher-NolobenBeach_ger.ogg","srlnEsher-NolobenBeach_spa.ogg","srlnEsher-NolobenKeep_eng.ogg","srlnEsher-NolobenKeep_fre.ogg","srlnEsher-NolobenKeep_ger.ogg","srlnEsher-NolobenKeep_spa.ogg","srlnEsher-NolobenLab_eng.ogg","srlnEsher-NolobenLab_fre.ogg","srlnEsher-NolobenLab_ger.ogg","srlnEsher-NolobenLab_spa.ogg","srlnKeepDoor_appear.ogg","srlnKeepDoor_disappear.ogg","srlnMainMusic_Loop.ogg","srlnOuterWatersAmb_Loop.ogg","srlnPoolPuzzleWaterDrain.ogg","srlnPoolTrickle_Loop.ogg","srlnRainInTunnels_Loop.ogg","srlnRainOnGrass_Loop.ogg","srlnRainOnWater_Loop.ogg","srlnRandMusic01.ogg","srlnRandMusic02.ogg","srlnRandMusic03.ogg","srlnRandMusic04.ogg","srlnRandMusic05.ogg","srlnRandMusic06.ogg","srlnRandMusic07.ogg","srlnRandMusic08.ogg","srlnRandMusic09.ogg","srlnRandMusic10.ogg","srlnRandMusic11.ogg","srlnRandMusic12.ogg","srlnRandMusic13.ogg","srlnRandMusic14.ogg","srlnRandMusic15.ogg","srlnRockAmb_Loop.ogg","srlnRockPillar-Rotate_Loop.ogg","srlnRockWindow_Close.ogg","srlnRockWindow_Open.ogg","srlnShoreWavesLight_Lp.ogg","srlnTunnels-Aperture01_Loop.ogg","srlnTunnels-Aperture02_Loop.ogg","srlnTunnels-Aperture03_Loop.ogg","srlnTunnels-Aperture04b_Loop.ogg","srlnTunnels-Aperture04_Loop.ogg","srlnTunnels-Aperture05_Loop.ogg","srlnTunnelsAmb_Loop.ogg","srlnUndergroundRockRumble.ogg","srlnUpperBugs_Loop.ogg","srlnWindAmb01_Loop.ogg","srlnYeeshaSymbGlow.ogg","srln_BahroCommandRain.ogg",
        "tdlmAirLockdoor_Close.ogg","tdlmAirLockDoor_Open.ogg","tdlmAmb01_Loop.ogg","tdlmAmbMusic01_loop.ogg","tdlmAmbWind01_Loop.ogg","tdlmBigScopeCables_loop.ogg","tdlmCableBrake.ogg","tdlmCableSway-Fast_loop.ogg","tdlmCableSway_Loop.ogg","tdlmCableSway_Loop02.ogg","tdlmclockFast_loop.ogg","tdlmClockTurn01.ogg","tdlmClockTurn02.ogg","tdlmClockTurn03.ogg","tdlmDock-Handle_loop.ogg","tdlmDock-Pump02_loop.ogg","tdlmDock-PumpBeginning.ogg","tdlmEsher-TodelmerP1_eng.ogg","tdlmEsher-TodelmerP1_fre.ogg","tdlmEsher-TodelmerP1_ger.ogg","tdlmEsher-TodelmerP1_Mx.ogg","tdlmEsher-TodelmerP1_spa.ogg","tdlmEsher-TodelmerP3_eng.ogg","tdlmEsher-TodelmerP3_fre.ogg","tdlmEsher-TodelmerP3_ger.ogg","tdlmEsher-TodelmerP3_spa.ogg","tdlmEsher-TodelmerRing_eng.ogg","tdlmEsher-TodelmerRing_fre.ogg","tdlmEsher-TodelmerRing_ger.ogg","tdlmEsher-TodelmerRing_spa.ogg","tdlmInsideGUILevers01.ogg","tdlmInsideGUILevers02.ogg","tdlmInsideGUILevers03.ogg","tdlmJoySticksDragLoop.ogg","tdlmPodAmb_Loop.ogg","tdlmPodRand_01.ogg","tdlmPodRand_02.ogg","tdlmPodRand_03.ogg","tdlmPodRand_04.ogg","tdlmPodRand_05.ogg","tdlmPodRand_06.ogg","tdlmPower-Ring_Loop.ogg","tdlmPower-Ring_off.ogg","tdlmPower-Ring_Start.ogg","tdlmPowerOn_Loop.ogg","tdlmScope-ZoomButton.ogg","tdlmScopeGUI-Sliders.ogg","tdlmScreenStatic_loop.ogg","tdlmSpeedUpAmb.ogg","tdlmStoneStairs_Hide.ogg","tdlmStoneStairs_Unhide.ogg","tdlmTram-BigSpool_loop.ogg","tdlmTramCar-Lever_loop.ogg","tdlmTramCar-MidtoP1.ogg","tdlmTramCar-MidTurn.ogg","tdlmTramCar-P3toMid.ogg","tdlmTramCarDoors_close.ogg","tdlmTramCarDoors_open.ogg","tdlmTramDockCables.ogg","tdlm_BahroCommandTime.ogg",
        "thgrDistantAmb.ogg","thgrEsher-TahgiraGrave_eng.ogg","thgrEsher-TahgiraGrave_fre.ogg","thgrEsher-TahgiraGrave_ger.ogg","thgrEsher-TahgiraGrave_spa.ogg","thgrEsher-TahgiraIntro_eng.ogg","thgrEsher-TahgiraIntro_fre.ogg","thgrEsher-TahgiraIntro_ger.ogg","thgrEsher-TahgiraIntro_spa.ogg","thgrEsher-TahgiraTake_eng.ogg","thgrEsher-TahgiraTake_fre.ogg","thgrEsher-TahgiraTake_ger.ogg","thgrEsher-TahgiraTake_spa.ogg","thgrEsher-TahgiraVillage_eng.ogg","thgrEsher-TahgiraVillage_fre.ogg","thgrEsher-TahgiraVillage_ger.ogg","thgrEsher-TahgiraVillage_spa.ogg","thgrEsher-Thermals_eng.ogg","thgrEsher-Thermals_fre.ogg","thgrEsher-Thermals_ger.ogg","thgrEsher-Thermals_spa.ogg","thgrGeyser_Loop.ogg","thgrIceCaveAmb02_Loop.ogg","thgrIceCaveWind_Loop.ogg","thgrIceCave_Loop.ogg","thgrIceCrack01.ogg","thgrIceCrack02.ogg","thgrIceCrack03.ogg","thgrIceCrack04.ogg","thgrIceCrack05.ogg","thgrIceCrack06.ogg","thgrIceCrack07.ogg","thgrIceCrack08.ogg","thgrIceFieldBubbles_loop.ogg","thgrIceFildsMx_loop.ogg","thgrIceWaves01.ogg","thgrKeepBreakaway.ogg","thgrRandomIce01.ogg","thgrRandomIce02.ogg","thgrRandomIce03.ogg","thgrRandomIce04.ogg","thgrRandomIce05.ogg","thgrRandomIce06.ogg","thgrRandomIce07.ogg","thgrRandomIce08.ogg","thgrSteamPipe01_Loop.ogg","thgrSteamPipe02_Loop.ogg","thgrSteamVents_Loop.ogg","thgrSteamVents_Loop02.ogg","thgrThermalLeverDrag.ogg","thgrThrmlActivity_loop01.ogg","thgrWaterFieldHandle_Down.ogg","thgrWaterFieldHandle_Up.ogg","thgrWind_Loop01.ogg","thgr_BahroCommandHeat.ogg",
        "xAudioBubble_Enter.ogg","xAudioBubble_Exit.ogg","xBahro51.ogg","xBahro52.ogg","xBahro54.ogg","xBahro55.ogg","xBahro56.ogg","xBahro58.ogg","xBahro59.ogg","xBahro62.ogg","xBahro64.ogg","xBahro69.ogg","xBahro70.ogg","xBahroConfused.ogg","xBahroFriendship.ogg","xBahroLink.ogg","xBahroPickup01.ogg","xBahroPickup02.ogg","xBahroReturn01.ogg","xBahroReturn02.ogg","xBahroReturn03.ogg","xBahroSing.ogg","xBahroSlate-Draw_Loop.ogg","xBahrosnake.ogg","xBahroTimid19.ogg","xBahroTimid20.ogg","xBahroTorture.ogg","xBhroLinkIn_Clean.ogg","xBhroLinkIn_Scared.ogg","xbhroPlaceSlate01b.ogg","xBubbleAmb_loop.ogg","xBubbleMusic.ogg","xCameraPickUp.ogg","xdrboIntBubbleAmb.ogg","xFlySwarm.ogg","xKeepUnlock.ogg","xLakiBubKeepAmb_Loop.ogg","xOptionScreenSFX01.ogg","xOptionScreenSFX02.ogg","xOptionScreenSFX03.ogg","xScreenshot.ogg","xSlateVaporToSolid.ogg","xSpecialTransitionEffect03.ogg","xSrlnBubKeepAmb_Loop.ogg","xTakeSymbolGlow.ogg","xTdlmBubKeepAmb_Loop.ogg","xThgrBubKeepAmb_Loop.ogg",
    };
    
    //This one is like mystvOggsNotInPotsNorMouloffline, but it removes all the speeches which end in _eng, _fre, _ger, and _spa.
    //Takes only half as much space.
    public static String[] mystvOggsNotInPotsNorMoulofflineMinusSpeeches = {
        "drboCalmNight-Distant_Loop.ogg","drboGateButton.ogg","drboGate_Close.ogg","drboGate_LatchClose.ogg","drboGate_LeverClose.ogg","drboGate_LeverOpen.ogg","drboGate_Open.ogg","drboLakiBubbleAmb_Loop.ogg","drboRandomCricket01.ogg","drboRandomCricket02.ogg","drboRandomCricket03.ogg","drboRandomCritter01.ogg","drboRandomCritter02.ogg","drboRandomCritter03.ogg","drboRandomCritter04a.ogg","drboRandomCritter04b.ogg","drboRandomCritter04c.ogg","drboRandomCritter04d.ogg","drboRandomCritter04e.ogg","drboRandomCritter04f.ogg","drboRandomCritter04g.ogg","drboRandomCritter05.ogg","drboRandomCritter06a.ogg","drboRandomCritter06b.ogg","drboRandomCritter06c.ogg","drboRandomCritter06d.ogg","drboRandomCritter07a.ogg","drboRandomCritter07b.ogg","drboRandomCritter07c.ogg","drboRandomCritter07d.ogg","drboRandomCritter09a.ogg","drboRandomCritter09b.ogg","drboRandomCritter10.ogg","drboRandomCritter12a.ogg","drboRandomCritter12b.ogg","drboRockRandom01.ogg","drboRockRandom02.ogg","drboRockRandom03.ogg","drboRockRandom04.ogg","drboRockRandom05.ogg","drboRockRandom06.ogg","drboSiralehn-BubbleMix_Loop.ogg","drboTdlmBubbleAmb_Loop.ogg","drboThgrBubbleAmb_Loop.ogg","drboWaterLaps_Loop.ogg",
        "dsntAirShaftLadder_down.ogg","dsntAirShaftLadder_up.ogg","dsntBatFlock01_loop.ogg","dsntBats-Rnd01.ogg","dsntBats-Rnd02.ogg","dsntBats-Rnd03.ogg","dsntBats-Rnd04.ogg","dsntBats-Rnd05.ogg","dsntBats-Rnd06.ogg","dsntBats-Rnd07.ogg","dsntBats-Rnd08.ogg","dsntBats-Rnd09.ogg","dsntBats-Rnd10.ogg","dsntCave-PoolDrips_Loop.ogg","dsntCave-WaterDrips_Loop.ogg","dsntCave-WaterTrickle_Loop.ogg","dsntCave-WindAmb_Loop01.ogg","dsntDoor-Switch_Broken.ogg","dsntEarthquakeLoop.ogg","dsntElevator-Handle.ogg","dsntElevatorMusic.ogg","dsntElevLeverEngage.ogg","dsntElevLever_end.ogg","dsntEsher-Intro_Mx.ogg","dsntFan-NodeAmb_Loop.ogg","dsntFan-On_Loop.ogg","dsntFan-Power_Off.ogg","dsntFan-Power_On.ogg","dsntFan-Switch_Jiggle.ogg","dsntFloorColorButtonPress.ogg","dsntFloorElev-Countdown01.ogg","dsntFloorElev-Countdown02.ogg","dsntFloorElev-Countdown03.ogg","dsntFloorElev-Drive_Loop.ogg","dsntFloorElev-Gears_Loop.ogg","dsntFloorElev-Gears_Start.ogg","dsntFloorElev-Gears_Stop.ogg","dsntFloorElev-Pillars_End.ogg","dsntFloorElev-Pillars_Loop.ogg","dsntFloorElev-TimerSwitch.ogg","dsntFloorLowButton_down.ogg","dsntFloorLowButton_up.ogg","dsntFloorRaiseWarn.ogg","dsntFloorUpButton_down.ogg","dsntFloorUpButton_up.ogg","dsntGeneratorAmb_Loop.ogg","dsntImagerButtonClick.ogg","dsntImagerSquelch.ogg","dsntImager_Loop.ogg","dsntLavaDoor_Open.ogg","dsntMarbles_Loop.ogg","dsntNodeAmb_Loop.ogg","dsntNodeDoor_Open.ogg","dsntNodeLeverDragLoop.ogg","dsntRandomRocks01.ogg","dsntRandomRocks02.ogg","dsntRandomRocks03.ogg","dsntRndAbstractAmb01.ogg","dsntRndAbstractAmb02.ogg","dsntRndAbstractAmb03.ogg","dsntRndAbstractAmb04.ogg","dsntRndAbstractAmb05.ogg","dsntRndAbstractAmb06.ogg","dsntRndAbstractAmb07.ogg","dsntRndAbstractAmb08.ogg","dsntSafetyGate_close.ogg","dsntSafetyGate_open.ogg","dsntShaftAmb02_Loop.ogg","dsntSteamPipe01_Loop.ogg","dsntSteamPipe02_Loop.ogg","dsntSteamVents_Loop.ogg","dsntUpperElev-Away_Bottom.ogg","dsntUpperElev-Away_Top.ogg","dsntUpperElev-Dock_Bottom.ogg","dsntUpperElev-Dock_Top.ogg","dsntUpperElev-WeightsJiggle.ogg","dsntUpperElev-Weights_Loop.ogg","dsntUpperElevator_Ride.ogg","dsntUpperElevator_Start.ogg","dsntUpperElevator_Stop.ogg","dsntYeesha-Imager01Mx.ogg","dsntYeesha-Imager02Mx.ogg","dsntYeesha-Imager03Mx.ogg",
        //
        "kverAmb_Loop.ogg","kverBahroSneakUp.ogg","kverBahroTakeOff.ogg","kverBahroWingsCover.ogg","kverConc03MxPart01.ogg","kverConc03MxPart02.ogg","kverMystBookLocked.ogg","kverPrisonDoorHandle.ogg","kverPrisonDoor_Open.ogg","kverRandAmb01.ogg","kverReleeshanAmb.ogg","kverSwingingLamp_loop.ogg","kverTabletMaterialize.ogg","kverYeesha-IntroMx.ogg","kverYeeshaConc02Mx.ogg",
        "lakiArena-PedDownFull.ogg","lakiArena-PedDownHalf.ogg","lakiArena-PedUpFull.ogg","lakiArena-PedUpHalf.ogg","lakiArena-RevealMusic.ogg","LakiArena-ScaleLight_Off1.ogg","LakiArena-ScaleLight_On1.ogg","LakiArena-ScaleLight_On2.ogg","LakiArena-ScaleLight_On3.ogg","LakiArena-WeightBtnClose.ogg","LakiArena-WeightBtnOpen.ogg","lakiArena_PedEnd.ogg","lakiBird-Rnd01.ogg","lakiBird-Rnd02a.ogg","lakiBird-Rnd02b.ogg","lakiBird-Rnd02c.ogg","lakiBird-Rnd02d.ogg","lakiBird-Rnd02e.ogg","lakiBird-Rnd02f.ogg","lakiBird-Rnd02g.ogg","lakiBird-Rnd02h.ogg","lakiBird-Rnd03.ogg","lakiBird-Rnd04.ogg","lakiBird-Rnd06c.ogg","lakiBird-Rnd08.ogg","lakiBird-Rnd09.ogg","lakiBird-Rnd10a.ogg","lakiBird-Rnd10b.ogg","lakiBird-Rnd11.ogg","lakiBird-Rnd15.ogg","lakiBird-Rnd16.ogg","lakiBird-Rnd17.ogg","lakiBird-Rnd18a.ogg","lakiBird-Rnd18b.ogg","lakiBird-Rnd18c.ogg","lakiBird-Rnd18d.ogg","lakiBird-Rnd18e.ogg","lakiBird-Rnd18f.ogg","lakiBird-Rnd18g.ogg","lakiBird-Rnd20.ogg","lakiBird-Rnd21.ogg","lakiBird-Rnd23.ogg","lakiBird-Rnd24.ogg","lakiBird-Rnd25.ogg","lakiBoulderFallLarge.ogg","lakiBoulderFallMed.ogg","lakiBoulderFallSmall.ogg","lakiCageDoorBtn.ogg","lakiCageDoors_Close.ogg","lakiCageDoors_Open.ogg","lakiElevatorLever-Latch01.ogg","lakiElevatorLeverDrag_Loop.ogg","lakiEsher-Arena_Mx.ogg","lakiEsher-Keep_Mx.ogg","lakiEsher-Villa_Mx.ogg","lakiHutFulcrum-DoorLand.ogg","lakiHutFulcrum-MoveFull.ogg","lakiHutFulcrum-MoveHalf.ogg","lakiHutPulley_loop.ogg","lakiMaze-Elevator_down.ogg","lakiMaze-Elevator_up.ogg","lakiMazeButton01.ogg","LakiMazeDoor1Close.ogg","LakiMazeDoor1Open.ogg","LakiMazeExitDoorClose.ogg","LakiMazeExitDoorOpen.ogg","lakiOceanAirAmb_Loop.ogg","lakiPirBird_Vocalize01.ogg","LakiPuzzleDoorBolt.ogg","LakiPuzzleDoorButton01.ogg","LakiPuzzleDoorButton02.ogg","LakiPuzzleDoorButton03.ogg","LakiPuzzleDoorOpen.ogg","lakiRandMusic01.ogg","lakiRandMusic02.ogg","lakiRandMusic03.ogg","lakiRandMusic04.ogg","lakiRandMusic05.ogg","lakiRandMusic06.ogg","lakiRandMusic07.ogg","lakiRandMusic08.ogg","lakiRandMusic09.ogg","lakiRegisterBtn_press.ogg","lakiRegisterBtn_unpress.ogg","lakiTunnelAmb_loop.ogg","lakiVillaFrontDoorClose.ogg","lakiVillaFrontDoorOpen.ogg","lakiWaterLaps_Loop.ogg","lakiWhaleCall_Loop.ogg","LakiWind-Beach_loop.ogg","lakiWindAmb01_Loop.ogg","LakiWindBranches_loop.ogg","LakiWindLeaves_loop.ogg","LakiWindMetal_loop.ogg","lakiWindmill-CageLever_On01.ogg","lakiWindmill-Lever_loop.ogg","lakiWindmill-PuzzleMusic_Loop.ogg","lakiWindmill_Loop.ogg","LakiWindThruFence_loop.ogg","lakiWindWaterCombo_Loop.ogg","laki_BahroCommandWind.ogg","Laki_ScalePlateDown.ogg","Laki_ScalePlateUp.ogg",
        "mystAmbMusic.ogg","mystCliffs-WavesBreak_Loop.ogg","mystDockWaveAmb.ogg","mystDoorSlam_Loop.ogg","mystEsher-Conc01Mx.ogg","mystEsher-Conc02Mx.ogg","mystHandleBreak.ogg","mystMastCreak01.ogg","mystMastCreak02.ogg","mystMastCreak03.ogg","mystOceanAir_Loop.ogg","mystRainOnGrass_InLoop.ogg","mystRainOnMetal-Ext_Loop01.ogg","mystRainOnMetal-Ext_Loop02.ogg","mystRainOnMetal-Int_Loop.ogg","mystRainOnWater_InLoop.ogg","mystShipCreak_Loop.ogg","mystTabletTrap.ogg","mystThunder01.ogg","mystThunder02.ogg","mystThunder03.ogg","mystThunder04.ogg","mystThunder05.ogg","mystThunder06.ogg","mystThunder07.ogg","mystWind-Animating_Loop.ogg","mystWind-SpookyAmb_Loop.ogg","mystWind-ThruTrees_Loop.ogg","mystWind_InLoop.ogg",
        "srlnBird01.ogg","srlnBird02.ogg","srlnBird03.ogg","srlnBird04.ogg","srlnBird05.ogg","srlnBird06.ogg","srlnBird07.ogg","srlnBird08.ogg","srlnBird09.ogg","srlnBird10.ogg","srlnBird11.ogg","srlnBird12.ogg","srlnBird13.ogg","srlnCliffLadder-Rope_loop.ogg","srlnCliffLadder_Drop.ogg","srlnKeepDoor_appear.ogg","srlnKeepDoor_disappear.ogg","srlnMainMusic_Loop.ogg","srlnOuterWatersAmb_Loop.ogg","srlnPoolPuzzleWaterDrain.ogg","srlnPoolTrickle_Loop.ogg","srlnRainInTunnels_Loop.ogg","srlnRainOnGrass_Loop.ogg","srlnRainOnWater_Loop.ogg","srlnRandMusic01.ogg","srlnRandMusic02.ogg","srlnRandMusic03.ogg","srlnRandMusic04.ogg","srlnRandMusic05.ogg","srlnRandMusic06.ogg","srlnRandMusic07.ogg","srlnRandMusic08.ogg","srlnRandMusic09.ogg","srlnRandMusic10.ogg","srlnRandMusic11.ogg","srlnRandMusic12.ogg","srlnRandMusic13.ogg","srlnRandMusic14.ogg","srlnRandMusic15.ogg","srlnRockAmb_Loop.ogg","srlnRockPillar-Rotate_Loop.ogg","srlnRockWindow_Close.ogg","srlnRockWindow_Open.ogg","srlnShoreWavesLight_Lp.ogg","srlnTunnels-Aperture01_Loop.ogg","srlnTunnels-Aperture02_Loop.ogg","srlnTunnels-Aperture03_Loop.ogg","srlnTunnels-Aperture04b_Loop.ogg","srlnTunnels-Aperture04_Loop.ogg","srlnTunnels-Aperture05_Loop.ogg","srlnTunnelsAmb_Loop.ogg","srlnUndergroundRockRumble.ogg","srlnUpperBugs_Loop.ogg","srlnWindAmb01_Loop.ogg","srlnYeeshaSymbGlow.ogg","srln_BahroCommandRain.ogg",
        "tdlmAirLockdoor_Close.ogg","tdlmAirLockDoor_Open.ogg","tdlmAmb01_Loop.ogg","tdlmAmbMusic01_loop.ogg","tdlmAmbWind01_Loop.ogg","tdlmBigScopeCables_loop.ogg","tdlmCableBrake.ogg","tdlmCableSway-Fast_loop.ogg","tdlmCableSway_Loop.ogg","tdlmCableSway_Loop02.ogg","tdlmclockFast_loop.ogg","tdlmClockTurn01.ogg","tdlmClockTurn02.ogg","tdlmClockTurn03.ogg","tdlmDock-Handle_loop.ogg","tdlmDock-Pump02_loop.ogg","tdlmDock-PumpBeginning.ogg","tdlmEsher-TodelmerP1_Mx.ogg","tdlmInsideGUILevers01.ogg","tdlmInsideGUILevers02.ogg","tdlmInsideGUILevers03.ogg","tdlmJoySticksDragLoop.ogg","tdlmPodAmb_Loop.ogg","tdlmPodRand_01.ogg","tdlmPodRand_02.ogg","tdlmPodRand_03.ogg","tdlmPodRand_04.ogg","tdlmPodRand_05.ogg","tdlmPodRand_06.ogg","tdlmPower-Ring_Loop.ogg","tdlmPower-Ring_off.ogg","tdlmPower-Ring_Start.ogg","tdlmPowerOn_Loop.ogg","tdlmScope-ZoomButton.ogg","tdlmScopeGUI-Sliders.ogg","tdlmScreenStatic_loop.ogg","tdlmSpeedUpAmb.ogg","tdlmStoneStairs_Hide.ogg","tdlmStoneStairs_Unhide.ogg","tdlmTram-BigSpool_loop.ogg","tdlmTramCar-Lever_loop.ogg","tdlmTramCar-MidtoP1.ogg","tdlmTramCar-MidTurn.ogg","tdlmTramCar-P3toMid.ogg","tdlmTramCarDoors_close.ogg","tdlmTramCarDoors_open.ogg","tdlmTramDockCables.ogg","tdlm_BahroCommandTime.ogg",
        "thgrDistantAmb.ogg","thgrGeyser_Loop.ogg","thgrIceCaveAmb02_Loop.ogg","thgrIceCaveWind_Loop.ogg","thgrIceCave_Loop.ogg","thgrIceCrack01.ogg","thgrIceCrack02.ogg","thgrIceCrack03.ogg","thgrIceCrack04.ogg","thgrIceCrack05.ogg","thgrIceCrack06.ogg","thgrIceCrack07.ogg","thgrIceCrack08.ogg","thgrIceFieldBubbles_loop.ogg","thgrIceFildsMx_loop.ogg","thgrIceWaves01.ogg","thgrKeepBreakaway.ogg","thgrRandomIce01.ogg","thgrRandomIce02.ogg","thgrRandomIce03.ogg","thgrRandomIce04.ogg","thgrRandomIce05.ogg","thgrRandomIce06.ogg","thgrRandomIce07.ogg","thgrRandomIce08.ogg","thgrSteamPipe01_Loop.ogg","thgrSteamPipe02_Loop.ogg","thgrSteamVents_Loop.ogg","thgrSteamVents_Loop02.ogg","thgrThermalLeverDrag.ogg","thgrThrmlActivity_loop01.ogg","thgrWaterFieldHandle_Down.ogg","thgrWaterFieldHandle_Up.ogg","thgrWind_Loop01.ogg","thgr_BahroCommandHeat.ogg",
        "xAudioBubble_Enter.ogg","xAudioBubble_Exit.ogg","xBahro51.ogg","xBahro52.ogg","xBahro54.ogg","xBahro55.ogg","xBahro56.ogg","xBahro58.ogg","xBahro59.ogg","xBahro62.ogg","xBahro64.ogg","xBahro69.ogg","xBahro70.ogg","xBahroConfused.ogg","xBahroFriendship.ogg","xBahroLink.ogg","xBahroPickup01.ogg","xBahroPickup02.ogg","xBahroReturn01.ogg","xBahroReturn02.ogg","xBahroReturn03.ogg","xBahroSing.ogg","xBahroSlate-Draw_Loop.ogg","xBahrosnake.ogg","xBahroTimid19.ogg","xBahroTimid20.ogg","xBahroTorture.ogg","xBhroLinkIn_Clean.ogg","xBhroLinkIn_Scared.ogg","xbhroPlaceSlate01b.ogg","xBubbleAmb_loop.ogg","xBubbleMusic.ogg","xCameraPickUp.ogg","xdrboIntBubbleAmb.ogg","xFlySwarm.ogg","xKeepUnlock.ogg","xLakiBubKeepAmb_Loop.ogg","xOptionScreenSFX01.ogg","xOptionScreenSFX02.ogg","xOptionScreenSFX03.ogg","xScreenshot.ogg","xSlateVaporToSolid.ogg","xSpecialTransitionEffect03.ogg","xSrlnBubKeepAmb_Loop.ogg","xTakeSymbolGlow.ogg","xTdlmBubKeepAmb_Loop.ogg","xThgrBubKeepAmb_Loop.ogg",
    };
    
    public static String[] mystvFiles = {
        "Descent.(others)","Descent.age","Descent.fni","Descent.sum","Descent_dsntBahro_Idle02.prp","Descent_dsntBahro_Idle03.prp","Descent_dsntBahro_Idle04.prp","Descent_dsntBahro_Idle05.prp","Descent_dsntBahro_Idle06.prp","Descent_dsntBahro_Idle07.prp","Descent_dsntBahro_Idle08.prp","Descent_dsntBahro_Idle09.prp","Descent_dsntBahro_Shot02.prp","Descent_dsntBahro_Shot03.prp","Descent_dsntBahro_Shot04.prp","Descent_dsntBahro_Shot05.prp","Descent_dsntBahro_Shot06.prp","Descent_dsntBahro_Shot07.prp","Descent_dsntBahro_Shot08.prp","Descent_dsntBahro_Shot09.prp","Descent_dsntBahro_Tunnel01.prp","Descent_dsntBahro_Tunnel01Idle.prp","Descent_dsntBats.prp","Descent_dsntEsherIdleTopOfShaft.prp","Descent_dsntEsher_BottomOfShaft.prp","Descent_dsntEsher_FirstHub.prp","Descent_dsntEsher_Intro.prp","Descent_dsntEsher_TopOfShaft.prp","Descent_dsntGreatShaftBalcony.prp","Descent_dsntGreatShaftLowerRm.prp","Descent_dsntLowerBats.prp","Descent_dsntMapGUI.prp","Descent_dsntPostBats.prp","Descent_dsntPostShaftNodeAndTunnels.prp","Descent_dsntShaftGeneratorRoom.prp","Descent_dsntShaftTunnelSystem.prp","Descent_dsntTianaCave.prp","Descent_dsntTianaCaveNode2.prp","Descent_dsntTianaCaveTunnel1.prp","Descent_dsntTianaCaveTunnel3.prp","Descent_dsntUpperBats.prp","Descent_dsntUpperShaft.prp","Descent_dsntVolcano.prp","Descent_Textures.prp","Descent_dusttest.prp",
        "Direbo.(others)","Direbo.age","Direbo.fni","Direbo.sum","Direbo_DragonFly.prp","Direbo_drboEsherIdleDirebo.prp","Direbo_drboEsher_DireboLaki.prp","Direbo_drboEsher_DireboSrln.prp","Direbo_drboEsher_DireboTdlm.prp","Direbo_drboEsher_DireboThgr.prp","Direbo_drboUrwinShape.prp","Direbo_RestAge.prp","Direbo_Textures.prp","Direbo_UrwinIdle.prp","Direbo_UrwinWalk.prp",
        "Kveer.(others)","Kveer.age","Kveer.fni","Kveer.sum",/*"Kveer_bkMystBookLocked.prp","Kveer_GreatRm.prp","Kveer_KveerBats.prp","Kveer_kverAtrus.prp","Kveer_kverAtrus_1.prp","Kveer_kverAtrus_Idle.prp","Kveer_kverBahroWingsGUI.prp","Kveer_kverBahro_1.prp","Kveer_kverBahro_2.prp","Kveer_kverBahro_Ballroom01.prp","Kveer_kverBahro_Ballroom02.prp","Kveer_kverBahro_Ballroom03.prp","Kveer_kverBahro_Exit01.prp","Kveer_kverBahro_Exit02.prp","Kveer_kverBahro_Idle05.prp","Kveer_kverBahro_Idle06.prp","Kveer_kverBahro_Idle07.prp","Kveer_kverBahro_Idle08.prp","Kveer_kverBahro_Idle09.prp","Kveer_kverBahro_Shot03.prp","Kveer_kverBahro_Shot04.prp","Kveer_kverBahro_Shot05.prp","Kveer_kverBahro_Shot06.prp","Kveer_kverBahro_Shot07.prp","Kveer_kverBahro_Shot08.prp","Kveer_kverBahro_Shot09.prp","Kveer_kverConc3Music.prp","Kveer_kverEsher_1.prp",*/"Kveer_kverReleeshan.prp",/*"Kveer_kverYeesha_1.prp","Kveer_kverYeesha_Conc01.prp","Kveer_kverYeesha_Conc02.prp","Kveer_kverYeesha_Conc03.prp","Kveer_kverYeesha_ConcIntro.prp","Kveer_kverYeesha_ConcIntro2.prp","Kveer_kverYeesha_IdleForIntro.prp","Kveer_kverYeesha_Intro.prp","Kveer_Prison.prp",*/"Kveer_Textures.prp","Kveer_dusttest.prp",
        "Laki.(others)","Laki.age","Laki.fni","Laki.sum","Laki_Exterior.prp","Laki_LakiArenaVillaInt.prp","Laki_LakiCreatures.prp","Laki_lakiEsher-Arena.prp","Laki_lakiEsher-FighterBeach.prp","Laki_lakiEsher-Keep.prp","Laki_lakiEsher-Villa.prp","Laki_lakiEsherIdleKeep.prp","Laki_lakiEsherIdleVilla.prp","Laki_LakiMaze.prp","Laki_lakiMazeClue.prp","Laki_LakiTrees01.prp","Laki_PirBirdActor.prp","Laki_PirBirdChomp.prp","Laki_PirBirdIdle.prp","Laki_PirBirdSwallow.prp","Laki_PirBirdVocalize.prp","Laki_PirBirdWalk.prp","Laki_Textures.prp","Laki_dusttest.prp",
        "Myst.(others)","Myst.age","Myst.fni","Myst.sum","Myst_Island.prp","Myst_mystEsher-Conc01.prp","Myst_mystEsher-Conc02.prp","Myst_Textures.prp",
        //"MystMystV_District_Additions.prp","Direbo_District_Additions.prp", //original authored material.
        "Siralehn.(others)","Siralehn.age","Siralehn.fni","Siralehn.sum","Siralehn_Birds.prp","Siralehn_Drawing01.prp","Siralehn_Drawing02.prp","Siralehn_Drawing03.prp","Siralehn_Drawing04.prp","Siralehn_Drawing05.prp","Siralehn_Drawing06.prp","Siralehn_Drawing07.prp","Siralehn_Drawing08.prp","Siralehn_Exterior.prp","Siralehn_rock.prp","Siralehn_srlnEsherIdleBeach.prp","Siralehn_srlnEsherIdleLab.prp","Siralehn_srlnEsher_NolobenBeach.prp","Siralehn_srlnEsher_NolobenKeep.prp","Siralehn_srlnEsher_NolobenLab.prp","Siralehn_srlnKeepInter.prp","Siralehn_Textures.prp","Siralehn_tunnels.prp","Siralehn_dusttest.prp",
        "Tahgira.(others)","Tahgira.age","Tahgira.fni","Tahgira.sum","Tahgira_Exterior.prp","Tahgira_IceCave.prp","Tahgira_Textures.prp","Tahgira_thgrEsherIdleIntro.prp","Tahgira_thgrEsherIdleTake.prp","Tahgira_thgrEsher_TahgiraGrave.prp","Tahgira_thgrEsher_TahgiraIntro.prp","Tahgira_thgrEsher_TahgiraTake.prp","Tahgira_thgrEsher_TahgiraThermals.prp","Tahgira_thgrEsher_TahgiraVillage.prp","Tahgira_dusttest.prp",
        "Todelmer.(others)","Todelmer.age","Todelmer.fni","Todelmer.sum","Todelmer_Exterior.prp","Todelmer_InteriorPillar1.prp","Todelmer_InteriorPillar3.prp","Todelmer_MiniScope.prp","Todelmer_Pod.prp","Todelmer_Sky.prp","Todelmer_tdlmEsherIdleP3.prp","Todelmer_tdlmEsherIdleRing.prp","Todelmer_tdlmEsher_TodelmerP1.prp","Todelmer_tdlmEsher_TodelmerP3.prp","Todelmer_tdlmEsher_TodelmerRing.prp","Todelmer_Textures.prp","Todelmer_dusttest.prp",
        "direbo.bik","restStop1.bik","restStop2.bik","restStop3.bik","restStop4.bik","direboWithAlpha.bik",
    };
    
}
