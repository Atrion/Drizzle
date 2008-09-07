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

package gui;
//import shared.State.CheckboxState;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import shared.FileUtils;
import javax.swing.JFileChooser;
import java.io.File;
import uru.UruCrypt;
import uru.UruFileTypes;
import uru.CryptHashes;
//import uru.Moul;
import uru.moulprp.prputils;
import uru.Bytestream;
import shared.m;
import relationvis.visualisation;
import uru.b;
import uru.moulprp._staticsettings;
import uru.context;
import uru.moulprp.Typeid;
import shared.Bytes;
import java.util.Vector;
import shared.GuiUtils;

/**
 *
 * @author  user
 */
public class Gui extends javax.swing.JFrame {

    JFileChooser fc = null;
    visualisation vis = null;
    
    String moul;
    String pots;
    String out;
    String settingsfile;
    Settings settings = new Settings();
    boolean dosavesettings = false;
    
    /** Creates new form Gui */
    public Gui()
    {
        initComponents();
        
        listState2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {
                "Descent.age","Descent.fni","Descent.sum","Descent_dsntBahro_Idle02.prp","Descent_dsntBahro_Idle03.prp","Descent_dsntBahro_Idle04.prp","Descent_dsntBahro_Idle05.prp","Descent_dsntBahro_Idle06.prp","Descent_dsntBahro_Idle07.prp","Descent_dsntBahro_Idle08.prp","Descent_dsntBahro_Idle09.prp","Descent_dsntBahro_Shot02.prp","Descent_dsntBahro_Shot03.prp","Descent_dsntBahro_Shot04.prp","Descent_dsntBahro_Shot05.prp","Descent_dsntBahro_Shot06.prp","Descent_dsntBahro_Shot07.prp","Descent_dsntBahro_Shot08.prp","Descent_dsntBahro_Shot09.prp","Descent_dsntBahro_Tunnel01.prp","Descent_dsntBahro_Tunnel01Idle.prp","Descent_dsntBats.prp","Descent_dsntEsherIdleTopOfShaft.prp","Descent_dsntEsher_BottomOfShaft.prp","Descent_dsntEsher_FirstHub.prp","Descent_dsntEsher_Intro.prp","Descent_dsntEsher_TopOfShaft.prp","Descent_dsntGreatShaftBalcony.prp","Descent_dsntGreatShaftLowerRm.prp","Descent_dsntLowerBats.prp","Descent_dsntMapGUI.prp","Descent_dsntPostBats.prp","Descent_dsntPostShaftNodeAndTunnels.prp","Descent_dsntShaftGeneratorRoom.prp","Descent_dsntShaftTunnelSystem.prp","Descent_dsntTianaCave.prp","Descent_dsntTianaCaveNode2.prp","Descent_dsntTianaCaveTunnel1.prp","Descent_dsntTianaCaveTunnel3.prp","Descent_dsntUpperBats.prp","Descent_dsntUpperShaft.prp","Descent_dsntVolcano.prp","Descent_Textures.prp",
                "Direbo.age","Direbo.fni","Direbo.sum","Direbo_DragonFly.prp","Direbo_drboEsherIdleDirebo.prp","Direbo_drboEsher_DireboLaki.prp","Direbo_drboEsher_DireboSrln.prp","Direbo_drboEsher_DireboTdlm.prp","Direbo_drboEsher_DireboThgr.prp","Direbo_drboUrwinShape.prp","Direbo_RestAge.prp","Direbo_Textures.prp","Direbo_UrwinIdle.prp","Direbo_UrwinWalk.prp",
                "Kveer.age","Kveer.fni","Kveer.sum","Kveer_bkMystBookLocked.prp","Kveer_GreatRm.prp","Kveer_KveerBats.prp","Kveer_kverAtrus.prp","Kveer_kverAtrus_1.prp","Kveer_kverAtrus_Idle.prp","Kveer_kverBahroWingsGUI.prp","Kveer_kverBahro_1.prp","Kveer_kverBahro_2.prp","Kveer_kverBahro_Ballroom01.prp","Kveer_kverBahro_Ballroom02.prp","Kveer_kverBahro_Ballroom03.prp","Kveer_kverBahro_Exit01.prp","Kveer_kverBahro_Exit02.prp","Kveer_kverBahro_Idle05.prp","Kveer_kverBahro_Idle06.prp","Kveer_kverBahro_Idle07.prp","Kveer_kverBahro_Idle08.prp","Kveer_kverBahro_Idle09.prp","Kveer_kverBahro_Shot03.prp","Kveer_kverBahro_Shot04.prp","Kveer_kverBahro_Shot05.prp","Kveer_kverBahro_Shot06.prp","Kveer_kverBahro_Shot07.prp","Kveer_kverBahro_Shot08.prp","Kveer_kverBahro_Shot09.prp","Kveer_kverConc3Music.prp","Kveer_kverEsher_1.prp","Kveer_kverReleeshan.prp","Kveer_kverYeesha_1.prp","Kveer_kverYeesha_Conc01.prp","Kveer_kverYeesha_Conc02.prp","Kveer_kverYeesha_Conc03.prp","Kveer_kverYeesha_ConcIntro.prp","Kveer_kverYeesha_ConcIntro2.prp","Kveer_kverYeesha_IdleForIntro.prp","Kveer_kverYeesha_Intro.prp","Kveer_Prison.prp","Kveer_Textures.prp",
                "Laki.age","Laki.fni","Laki.sum","Laki_Exterior.prp","Laki_LakiArenaVillaInt.prp","Laki_LakiCreatures.prp","Laki_lakiEsher-Arena.prp","Laki_lakiEsher-FighterBeach.prp","Laki_lakiEsher-Keep.prp","Laki_lakiEsher-Villa.prp","Laki_lakiEsherIdleKeep.prp","Laki_lakiEsherIdleVilla.prp","Laki_LakiMaze.prp","Laki_lakiMazeClue.prp","Laki_LakiTrees01.prp","Laki_PirBirdActor.prp","Laki_PirBirdChomp.prp","Laki_PirBirdIdle.prp","Laki_PirBirdSwallow.prp","Laki_PirBirdVocalize.prp","Laki_PirBirdWalk.prp","Laki_Textures.prp",
                "Myst.age","Myst.fni","Myst.sum","Myst_Island.prp","Myst_mystEsher-Conc01.prp","Myst_mystEsher-Conc02.prp","Myst_Textures.prp",
                "Siralehn.age","Siralehn.fni","Siralehn.sum","Siralehn_Birds.prp","Siralehn_Drawing01.prp","Siralehn_Drawing02.prp","Siralehn_Drawing03.prp","Siralehn_Drawing04.prp","Siralehn_Drawing05.prp","Siralehn_Drawing06.prp","Siralehn_Drawing07.prp","Siralehn_Drawing08.prp","Siralehn_Exterior.prp","Siralehn_rock.prp","Siralehn_srlnEsherIdleBeach.prp","Siralehn_srlnEsherIdleLab.prp","Siralehn_srlnEsher_NolobenBeach.prp","Siralehn_srlnEsher_NolobenKeep.prp","Siralehn_srlnEsher_NolobenLab.prp","Siralehn_srlnKeepInter.prp","Siralehn_Textures.prp","Siralehn_tunnels.prp",
                "Tahgira.age","Tahgira.fni","Tahgira.sum","Tahgira_Exterior.prp","Tahgira_IceCave.prp","Tahgira_Textures.prp","Tahgira_thgrEsherIdleIntro.prp","Tahgira_thgrEsherIdleTake.prp","Tahgira_thgrEsher_TahgiraGrave.prp","Tahgira_thgrEsher_TahgiraIntro.prp","Tahgira_thgrEsher_TahgiraTake.prp","Tahgira_thgrEsher_TahgiraThermals.prp","Tahgira_thgrEsher_TahgiraVillage.prp",
                "Todelmer.age","Todelmer.fni","Todelmer.sum","Todelmer_Exterior.prp","Todelmer_InteriorPillar1.prp","Todelmer_InteriorPillar3.prp","Todelmer_MiniScope.prp","Todelmer_Pod.prp","Todelmer_Sky.prp","Todelmer_tdlmEsherIdleP3.prp","Todelmer_tdlmEsherIdleRing.prp","Todelmer_tdlmEsher_TodelmerP1.prp","Todelmer_tdlmEsher_TodelmerP3.prp","Todelmer_tdlmEsher_TodelmerRing.prp","Todelmer_Textures.prp",
            };
            public int getSize() {
                return strings.length;
            }
            public Object getElementAt(int index) {
                return strings[index];
            }
        });
        listState3.setModel(new javax.swing.AbstractListModel() {
            String strings[] = {
                "Dereno.age","Dereno.fni","Dereno.sum","Dereno_District_DrnoExterior.prp","Dereno_District_DrnoPod.prp","Dereno_District_Textures.prp","Dereno_District_BuiltIn.prp",
                "EderDelin.age","EderDelin.fni","EderDelin.sum","EderDelin_District_garden.prp","EderDelin_District_BuiltIn.prp","EderDelin_District_Textures.prp",
                "EderTsogal.age","EderTsogal.fni","EderTsogal.sum","EderTsogal_District_tsoGarden.prp","EderTsogal_District_Textures.prp","EderTsogal_District_BuiltIn.prp",
                "GuildPub-Cartographers.age","GuildPub-Cartographers.fni","GuildPub-Cartographers.sum","GuildPub-Cartographers_District_Pub.prp","GuildPub-Cartographers_District_Textures.prp","GuildPub-Cartographers_District_BuiltIn.prp",
                "GuildPub-Greeters.age","GuildPub-Greeters.fni","GuildPub-Greeters.sum","GuildPub-Greeters_District_Pub.prp","GuildPub-Greeters_District_Textures.prp","GuildPub-Greeters_District_BuiltIn.prp",
                "GuildPub-Maintainers.age","GuildPub-Maintainers.fni","GuildPub-Maintainers.sum","GuildPub-Maintainers_District_Pub.prp","GuildPub-Maintainers_District_Textures.prp","GuildPub-Maintainers_District_BuiltIn.prp",
                "GuildPub-Messengers.age","GuildPub-Messengers.fni","GuildPub-Messengers.sum","GuildPub-Messengers_District_Pub.prp","GuildPub-Messengers_District_Textures.prp","GuildPub-Messengers_District_BuiltIn.prp",
                "GuildPub-Writers.age","GuildPub-Writers.fni","GuildPub-Writers.sum","GuildPub-Writers_District_Pub.prp","GuildPub-Writers_District_Textures.prp","GuildPub-Writers_District_BuiltIn.prp",
                "Jalak.age","Jalak.fni","Jalak.sum","Jalak_District_jlakArena.prp","Jalak_District_Textures.prp","Jalak_District_BuiltIn.prp",
                "LiveBahroCaves.age","LiveBahroCaves.fni","LiveBahroCaves.sum","LiveBahroCaves_District_MINKcave.prp","LiveBahroCaves_District_POTScave.prp","LiveBahroCaves_District_PODcave.prp","LiveBahroCaves_District_BlueSpiralCave.prp","LiveBahroCaves_District_TheSpecialPage.prp","LiveBahroCaves_District_Textures.prp","LiveBahroCaves_District_BuiltIn.prp",
                "Minkata.age","Minkata.fni","Minkata.sum","Minkata_District_minkExteriorDay.prp","Minkata_District_minkNightLinkSounds.prp","Minkata_District_minkExteriorNight.prp","Minkata_District_minkExcludeRegions.prp","Minkata_District_minkDistCraterPhysicals.prp","Minkata_District_minkDayLinkSounds.prp","Minkata_District_minkCameras.prp","Minkata_District_Textures.prp","Minkata_District_BuiltIn.prp",
                "Negilahn.age","Negilahn.fni","Negilahn.sum","Negilahn_District_Jungle.prp","Negilahn_District_MuseumPod.prp","Negilahn_District_Textures.prp","Negilahn_District_BuiltIn.prp",
                "Payiferen.age","Payiferen.fni","Payiferen.sum","Payiferen_District_Pod.prp","Payiferen_District_Textures.prp","Payiferen_District_BuiltIn.prp",
                "Tetsonot.age","Tetsonot.fni","Tetsonot.sum","Tetsonot_District_tetsoPod.prp","Tetsonot_District_Textures.prp","Tetsonot_District_BuiltIn.prp",
            };
            public int getSize() {
                return strings.length;
            }
            public Object getElementAt(int index) {
                return strings[index];
            }
        });
        
        //fc = new JFileChooser();
        m.setJTextArea(this.jTextArea1); //make sure the messages will come through.
        //vis = new visualisation(this.jPanel5.getGraphics());
        
        jComboBox1.setEditable(true);
        this.jComboBox1.removeAllItems();
        //pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        out = "C:/Documents and Settings/user/Desktop/output/";
        //pots = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "/shared/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        //out = "/shared/a/leftoff/output/";
        pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        //out = "D:/a/leftoff/output/";
        settingsfile = out+"drizzlesettings2.canbedeleted.dat";
        uru.moulprp._staticsettings.outputdir = out;

        this.jComboBox1.addItem("");
        this.jComboBox1.addItem("C:/Documents and Settings/user/Desktop/output/dat/MarshScene_District_Exterior.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_BuiltIn.prp");
        this.jComboBox1.addItem("Jalak_District_jlakArena.prp");
        this.jComboBox1.addItem("Jalak_District_Textures.prp");
        this.jComboBox1.addItem("Jalak_District_BuiltIn.prp");
        this.jComboBox1.addItem("Minkata_District_minkExteriorDay.prp");
        this.jComboBox1.addItem("Minkata_District_minkNightLinkSounds.prp");
        this.jComboBox1.addItem("Minkata_District_minkExteriorNight.prp");
        this.jComboBox1.addItem("Minkata_District_minkExcludeRegions.prp");
        this.jComboBox1.addItem("Minkata_District_minkDistCraterPhysicals.prp");
        this.jComboBox1.addItem("Minkata_District_minkDayLinkSounds.prp");
        this.jComboBox1.addItem("Minkata_District_minkCameras.prp");
        this.jComboBox1.addItem("Minkata_District_Textures.prp");
        this.jComboBox1.addItem("Minkata_District_BuiltIn.prp");
        this.jComboBox1.addItem("Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem("Tetsonot_District_Textures.prp");
        this.jComboBox1.addItem("Tetsonot_District_BuiltIn.prp");
        this.jComboBox1.addItem("Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem("Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem("Negilahn_District_Textures.prp");
        this.jComboBox1.addItem("Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem("Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem("Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem("Dereno_District_Textures.prp");
        this.jComboBox1.addItem("Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem("Payiferen_District_Pod.prp");
        this.jComboBox1.addItem("Payiferen_District_Textures.prp");
        this.jComboBox1.addItem("Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_MINKcave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_POTScave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_PODcave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem("EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem("EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderDelin_District_garden.prp");
        this.jComboBox1.addItem("EderDelin_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderDelin_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Garden_District_kemoGarden.prp");
        this.jComboBox1.addItem(pots+"Garden_District_kemoGarden.prp");
        this.jComboBox1.addItem(moul+"Personal_District_psnlMYSTII.prp");
        this.jComboBox1.addItem(pots+"Personal_District_psnlMYSTII.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_Pod.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_Textures.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem(out+"Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem(out+"Dereno_District_Textures.prp");
        this.jComboBox1.addItem(out+"Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_Pod.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_Textures.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_jlakArena.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExteriorDay.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkNightLinkSounds.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExteriorNight.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExcludeRegions.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkDistCraterPhysicals.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkDayLinkSounds.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkCameras.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_MINKcave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_PODcave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_POTScave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_garden.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_Textures.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_BuiltIn.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_Textures.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_Textures.prp");
        this.jComboBox1.addItem(pots);
        this.jComboBox1.addItem(moul);
        if(!uru.moulprp.Typeid.validateTriplets()) m.err("Triplets not valid!");
        
        this.loadsettings();
        dosavesettings = true;
    }
    public static class Settings implements java.io.Serializable
    {
        String curfile;
        int curfolder;
        
    }
    public void savesettings()
    {
        if(!dosavesettings) return;
        
        try
        {
            settings.curfile = (String)this.jComboBox1.getSelectedItem();
            //settings.curfolder = this.jRadioButton1.isSelected()
            if(this.jRadioButton1.isSelected()) settings.curfolder = 1;
            if(this.jRadioButton2.isSelected()) settings.curfolder = 2;
            if(this.jRadioButton3.isSelected()) settings.curfolder = 3;
            if(this.jRadioButton4.isSelected()) settings.curfolder = 4;
            
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(this.out+"settings.dat"));
            out.writeObject(settings);
            out.close();
        }
        catch(Exception e)
        {
        }
    }
    public void loadsettings()
    {
        try
        {
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(this.out+"settings.dat"));
            settings = (Settings)in.readObject();
            in.close();
            this.jComboBox1.setSelectedItem(settings.curfile);
            if(settings.curfolder==1) this.jRadioButton1.setSelected(true);
            if(settings.curfolder==2) this.jRadioButton2.setSelected(true);
            if(settings.curfolder==3) this.jRadioButton3.setSelected(true);
            if(settings.curfolder==4) this.jRadioButton4.setSelected(true);
        }
        catch(Exception e)
        {
        }
    }
    private String getSelectedFilename()
    {
        String name = (String)this.jComboBox1.getSelectedItem();
        //String command = this.filedirButtonGroup.getSelection().getActionCommand();
        String result = null;
        if(this.jRadioButton1.isSelected()) result = this.moul+name;
        if(this.jRadioButton2.isSelected()) result = this.pots+name;
        if(this.jRadioButton3.isSelected()) result = this.out+name;
        if(this.jRadioButton4.isSelected()) result = name;
        if(result==null) m.err("Uruutils: problem in getSelectedFilename.");
        return result;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filedirButtonGroup = new javax.swing.ButtonGroup();
        buttongroupState1 = new shared.State.ButtongroupState();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton20 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listState2 = new shared.State.ListState();
        textfieldState2 = new shared.State.TextfieldState();
        textfieldState3 = new shared.State.TextfieldState();
        jLabel10 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton53 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        textfieldState4 = new shared.State.TextfieldState();
        textfieldState5 = new shared.State.TextfieldState();
        jPanel16 = new javax.swing.JPanel();
        comboboxState1 = new shared.State.ComboboxState();
        comboboxState2 = new shared.State.ComboboxState();
        checkboxState2 = new shared.State.CheckboxState();
        formattedfieldState1 = new shared.State.FormattedfieldState();
        jScrollPane6 = new javax.swing.JScrollPane();
        textareaState1 = new shared.State.TextareaState();
        jScrollPane7 = new javax.swing.JScrollPane();
        listState1 = new shared.State.ListState();
        textfieldState1 = new shared.State.TextfieldState();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();
        jButton60 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        textfieldState7 = new shared.State.TextfieldState();
        jLabel11 = new javax.swing.JLabel();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        textfieldState6 = new shared.State.TextfieldState();
        textfieldState8 = new shared.State.TextfieldState();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton66 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listState3 = new shared.State.ListState();
        jButton67 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        checkboxState1 = new shared.State.CheckboxState();
        checkboxState3 = new shared.State.CheckboxState();
        checkboxState4 = new shared.State.CheckboxState();
        checkboxState5 = new shared.State.CheckboxState();
        checkboxState6 = new shared.State.CheckboxState();
        jPanel1 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton49 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton43 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton54 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();

        buttongroupState1.setName("awe");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uruutils");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1000, 680));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setText("Select...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 10, 90, 23);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(100, 440, 580, 130);

        jButton2.setText("Clear log.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(0, 440, 100, 23);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(110, 10, 610, 22);

        jButton20.setText("remove dupes.");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton20);
        jButton20.setBounds(0, 470, 100, 23);

        jLabel8.setText("MystV folder:");

        jLabel9.setText("Output folder:");

        jButton55.setText("select...");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jButton56.setText("select...");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton57.setText("Start...");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });

        jButton58.setText("Select All");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setText("Select None");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        listState2.setName("myst5list"); // NOI18N
        jScrollPane5.setViewportView(listState2);

        textfieldState2.setText("C:\\Program Files\\Ubisoft\\Cyan Worlds\\Myst V End Of Ages");
        textfieldState2.setName("myst5intext"); // NOI18N

        textfieldState3.setText("C:\\Documents and Settings\\user\\Desktop\\output");
        textfieldState3.setName("myst5outtext"); // NOI18N

        jLabel10.setText("Files to process:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldState3, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton58)
                                    .addComponent(jButton59))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton57)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(textfieldState2, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton55)
                    .addComponent(jButton56))
                .addGap(73, 73, 73))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel10)
                .addContainerGap(670, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton55)
                    .addComponent(textfieldState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton56)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jButton57)
                                .addGap(76, 76, 76))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton59)))
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textfieldState3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)))
                .addGap(76, 76, 76))
        );

        jTabbedPane2.addTab("MystV", jPanel15);

        jLabel6.setText("Crowthistle folder:");

        jLabel7.setText("Output folder:");

        jButton53.setText("start...");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        jButton51.setText("select...");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setText("select...");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        textfieldState4.setText("C:\\Program Files\\Crowthistle");
        textfieldState4.setName("crowintext"); // NOI18N

        textfieldState5.setText("C:\\Documents and Settings\\user\\Desktop\\output");
        textfieldState5.setName("crowouttext"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textfieldState5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldState4, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton51)
                            .addComponent(jButton52)))
                    .addComponent(jButton53))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton51)
                    .addComponent(textfieldState4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton52)
                    .addComponent(textfieldState5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton53)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Crowthistle", jPanel14);

        comboboxState1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxState1.setName("cb1"); // NOI18N
        comboboxState1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxState1ActionPerformed(evt);
            }
        });

        comboboxState2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxState2.setName("cb2"); // NOI18N

        checkboxState2.setText("checkboxState2");
        checkboxState2.setName("chebox1"); // NOI18N

        formattedfieldState1.setText("formattedfieldState1");
        formattedfieldState1.setName("format1"); // NOI18N

        textareaState1.setColumns(20);
        textareaState1.setRows(5);
        textareaState1.setName("area1"); // NOI18N
        jScrollPane6.setViewportView(textareaState1);

        listState1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listState1.setName("list1"); // NOI18N
        listState1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listState1ValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(listState1);

        textfieldState1.setText("textfieldState1");
        textfieldState1.setName("field1"); // NOI18N

        buttongroupState1.add(jRadioButton5);
        jRadioButton5.setText("jRadioButton5");

        buttongroupState1.add(jRadioButton6);
        jRadioButton6.setText("jRadioButton6");

        buttongroupState1.add(jRadioButton7);
        jRadioButton7.setText("jRadioButton7");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton60.setText("jButton60");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(comboboxState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jRadioButton5)
                                        .addComponent(jRadioButton6))
                                    .addComponent(jRadioButton7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(textfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(comboboxState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(checkboxState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(formattedfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formattedfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(comboboxState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton6))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(textfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton7))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton60))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab3", jPanel16);

        jPanel17.setLayout(null);

        textfieldState7.setText("C:\\Documents and Settings\\user\\Desktop\\output\\pots\\dat");
        textfieldState7.setName("potstext1"); // NOI18N
        jPanel17.add(textfieldState7);
        textfieldState7.setBounds(110, 40, 430, 20);

        jLabel11.setText("Pots folder:");
        jPanel17.add(jLabel11);
        jLabel11.setBounds(30, 40, 70, 14);

        jButton62.setText("Read all prp files...");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton62);
        jButton62.setBounds(100, 100, 160, 23);

        jButton63.setText("select...");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton63);
        jButton63.setBounds(550, 40, 81, 23);

        jTabbedPane2.addTab("Pots", jPanel17);

        jPanel19.setLayout(null);

        textfieldState6.setText("C:\\Documents and Settings\\user\\Desktop\\output\\mouloffline\\final3");
        textfieldState6.setName("moulintext"); // NOI18N
        jPanel19.add(textfieldState6);
        textfieldState6.setBounds(130, 10, 510, 20);

        textfieldState8.setText("C:\\Documents and Settings\\user\\Desktop\\output");
        textfieldState8.setName("moulouttext"); // NOI18N
        jPanel19.add(textfieldState8);
        textfieldState8.setBounds(130, 60, 510, 20);

        jButton64.setText("Select...");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton64);
        jButton64.setBounds(660, 20, 80, 23);

        jButton65.setText("Select....");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton65);
        jButton65.setBounds(660, 60, 80, 23);

        jLabel12.setText("Moul folder:");
        jPanel19.add(jLabel12);
        jLabel12.setBounds(40, 10, 70, 14);

        jLabel13.setText("Output folder:");
        jPanel19.add(jLabel13);
        jLabel13.setBounds(30, 50, 80, 14);

        jButton66.setText("Start...");
        jPanel19.add(jButton66);
        jButton66.setBounds(490, 200, 71, 23);

        listState3.setName("moulfiles"); // NOI18N
        jScrollPane8.setViewportView(listState3);

        jPanel19.add(jScrollPane8);
        jScrollPane8.setBounds(180, 142, 290, 150);

        jButton67.setText("Select all");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton67);
        jButton67.setBounds(37, 140, 130, 23);

        jButton68.setText("Select none");
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton68);
        jButton68.setBounds(40, 170, 130, 23);

        jLabel14.setText("Files to process:");
        jPanel19.add(jLabel14);
        jLabel14.setBounds(140, 110, 140, 14);

        jTabbedPane2.addTab("Moul", jPanel19);

        jPanel18.setLayout(null);

        checkboxState1.setText("Report the ogg files seen.");
        checkboxState1.setName("reportOggFiles"); // NOI18N
        jPanel18.add(checkboxState1);
        checkboxState1.setBounds(130, 50, 380, 23);

        checkboxState3.setText("Report the avi files seen.");
        checkboxState3.setName("reportAviFiles"); // NOI18N
        jPanel18.add(checkboxState3);
        checkboxState3.setBounds(130, 90, 430, 23);

        checkboxState4.setText("Report PlEAXSourceSettings info.");
        checkboxState4.setName("reportEaxSourceSettings"); // NOI18N
        jPanel18.add(checkboxState4);
        checkboxState4.setBounds(130, 130, 450, 23);

        checkboxState5.setText("Report Physics settings.");
        checkboxState5.setName("reportPhysics"); // NOI18N
        jPanel18.add(checkboxState5);
        checkboxState5.setBounds(130, 170, 410, 23);

        checkboxState6.setSelected(true);
        checkboxState6.setText("Report suffixes written(including forced changes) and changed Age names.");
        checkboxState6.setName("reportSuffixes"); // NOI18N
        jPanel18.add(checkboxState6);
        checkboxState6.setBounds(130, 210, 490, 23);

        jTabbedPane2.addTab("Reports", jPanel18);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Automation", jPanel13);

        jButton15.setText("Process All (moul)");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton19.setText("Process All (pots)");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton13.setText("Make prp report");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Dump some objects");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setText("Report cross-references");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setText("Deep Reflection Report");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setText("Recompile prp");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton3.setText("Detect Type");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("test!!!");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton21.setText("Find Objects of a certain type");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton31.setText("find drawinterfaces that use a LayerAnimation");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setText("convert from xml escaped text (TEXT:)");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setText("convert to escaped double quotes (TEXT:)");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel3.setText("Use these to convert text from .loc files to an escaped string that can be used by Python:");

        jButton34.setText("Make sound report");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton36.setText("Process all files as pots");
        jButton36.setEnabled(false);
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setText("Process all files");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setText("Python report");

        jButton40.setText("jButton40");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setText("jButton41");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton44.setText("Dump All Objects");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton46.setText("read .sum file");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton48.setText("create .sum file");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton15)
                                .addGap(49, 49, 49)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton46)
                                    .addComponent(jButton48))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton44))
                                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton21)
                                    .addComponent(jButton31)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton38)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton36)
                                    .addComponent(jButton37)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jButton6)))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton41)
                                    .addComponent(jButton40)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton33)
                            .addComponent(jButton32)
                            .addComponent(jLabel3))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(jButton13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19)
                            .addComponent(jButton14)
                            .addComponent(jButton44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton18)
                            .addComponent(jButton17)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton41))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton21)
                            .addComponent(jButton37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton31)
                            .addComponent(jButton36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton34)
                            .addComponent(jButton38)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton48)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton33)
                .addGap(36, 36, 36))
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jButton5.setText("Decrypt notthedroids");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Decrypt whatdoyousee");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton11.setText("Decrypt elf");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton8.setText("Decrypt eoa");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("Encrypt notthedroids");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("Encrypt whatdoyousee");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setText("Encrypt eoa");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setText("Encrypt elf");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton47.setText("Calculate md5");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton8)
                            .addComponent(jButton11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton47))
                .addGap(524, 524, 524))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton10))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(40, 40, 40)
                .addComponent(jButton47)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Encryption", jPanel3);

        jPanel4.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel5MouseReleased(evt);
            }
        });
        jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel5MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5);
        jPanel5.setBounds(0, 0, 660, 320);

        jButton23.setText("delete marked entities");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton23);
        jButton23.setBounds(691, 280, 170, 23);

        jButton22.setText("clear all marks");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton22);
        jButton22.setBounds(741, 40, 120, 23);

        jButton29.setText("mark links");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton29);
        jButton29.setBounds(751, 70, 110, 23);

        jButton24.setText("mark links from selected");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton24);
        jButton24.setBounds(715, 100, 150, 23);

        jButton25.setText("mark all that start with STRING");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton25);
        jButton25.setBounds(680, 160, 183, 23);

        jButton28.setText("load");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton28);
        jButton28.setBounds(785, 10, 80, 23);

        jButton27.setText("mark links to selected");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton27);
        jButton27.setBounds(689, 130, 170, 23);

        jButton26.setText("... end with STRING");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton26);
        jButton26.setBounds(700, 190, 160, 23);

        jButton30.setText("repaint");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton30);
        jButton30.setBounds(680, 10, 81, 23);

        jTabbedPane1.addTab("CrossRefTool", jPanel4);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Quaternion Compression Test"));

        jTextField3.setText("776421322");

        jTextField4.setText("883481695");

        jButton39.setText("jButton39");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jLabel5.setText("jLabel5");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText("-1258393726   1764680261\n\n\n");
        jScrollPane4.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4)
                    .addComponent(jLabel5)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton49.setText(".age test");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jButton49)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButton49)))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Pods"));

        jTextField5.setText("Feb 15, 1982 8:00 AM CST");

        jButton42.setText("Predict Pod events");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton42)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton42)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Minkata"));

        jButton35.setText("sum...");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jLabel4.setText("Enter a list of angles(20=full circle) and distances.  They will be summed as vectors.");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("angle1,dist1,angle2,dist2,...\ne.g.:\n8.5,360,\n1,556,\n2,442,\n15,420\n\n15,1386,\n11,274,\n11,164,\n17,269,\n11.25,237,\n10,807,\n16.5,91,\n15,1049,\n1,50,\n15,41,\n9,50,\n7,524,\n0,1154,\n2,629\n\n0.25,780,\n1,2052,\n7,825,\n10,866,\n2,105,\n10,2212,\n11.25,1245,\n15,748,\n13,429,\n16.25,315,\n10.5,264,\n15,461,\n15,652\n\n2.75,109,\n0,1259,\n14,210,\n9,196,\n15,415,\n17,160,\n15,360,\n13,251,\n17.25,424\n\n5,835,\n5,342,\n10,369,\n5,538,\n2.25,205,\n5,205,\n7.5,511");
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton35)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton43.setText("PtGetDniTime");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)
                        .addComponent(jButton43)))
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton43)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GameHelp", jPanel7);

        jButton45.setText("jButton45");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton45)
                .addContainerGap(730, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jButton45)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("realMyst", jPanel10);

        jButton50.setText("jButton50");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButton50)
                .addContainerGap(715, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jButton50)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Riven", jPanel12);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 70, 880, 360);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(190, 20, 30, 20);

        jLabel1.setText("STRING:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(31, 40, 70, 14);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(110, 40, 168, 20);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(410, 40, 310, 40);

        jLabel2.setText("TEXT:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(338, 40, 50, 14);

        filedirButtonGroup.add(jRadioButton1);
        jRadioButton1.setText("moul");
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(730, 10, 90, 10);

        filedirButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("pots");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(730, 20, 110, 10);

        filedirButtonGroup.add(jRadioButton3);
        jRadioButton3.setText("output");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(730, 30, 100, 10);

        filedirButtonGroup.add(jRadioButton4);
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("absolute");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(730, 40, 110, 10);

        jButton54.setText("Alphabetise.");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton54);
        jButton54.setBounds(1, 500, 100, 23);

        jButton61.setText("Delete settings and exit");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton61);
        jButton61.setBounds(730, 50, 190, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        m.msg("hi");
        if(fc==null) fc = new JFileChooser();
        if(this.jRadioButton1.isSelected()) fc.setCurrentDirectory(new File(this.moul));
        if(this.jRadioButton2.isSelected()) fc.setCurrentDirectory(new File(this.pots));
        if(this.jRadioButton3.isSelected()) fc.setCurrentDirectory(new File(this.out));
        int wasFileChosen = fc.showOpenDialog(null);
        //Main.message(returnVal);
        if(wasFileChosen==0)
        {
            //fc.getSelectedFiles(); //for multi-select.
            String file = fc.getSelectedFile().getAbsolutePath();
            if(this.jRadioButton1.isSelected()) file = fc.getSelectedFile().getName();
            if(this.jRadioButton2.isSelected()) file = fc.getSelectedFile().getName();
            if(this.jRadioButton3.isSelected()) file = fc.getSelectedFile().getName();
            jComboBox1.setSelectedItem(file);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jTextArea1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        UruFileTypes type = UruCrypt.DetectType(this.getSelectedFilename());
        m.msg("Filetype is:"+type.toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptWhatdoyousee(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptNotthedroids(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //tests
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        context c = context.createFromBytestream(new Bytestream(filecontents));
        //try{
        uru.moulprp.prpprocess.ProcessAllObjectsOfType(c, Typeid.plLayerAnimation);
        //}catch(Exception e){}
        
        
        //byte[] content = {'a','b','c'};
        //FileUtils.WriteFile("c:/hi.txt", content);
        
        //byte[] bytes = {(byte)0x00,(byte)0x01};
        //short s = UruUtils.BytesToInt16(bytes,0);
        //Main.message("short:"+Short.toString(s));
        
        //byte[] bytes = {(byte)0x06,(byte)0x00,(byte)0x29,(byte)0x10,(byte)0x01,(byte)0x11,(byte)0x0C,(byte)0x0A};
        //byte[] result = UruUtils.DecryptEoastring(bytes);
        //Main.message("eoastring:"+new String(result));
        
        //byte[] bytes = {(byte)'D',(byte)'i',(byte)'r',(byte)'e',(byte)'b',(byte)'o'};
        //byte[] result = UruUtils.EncryptEoastring(bytes);
        //Main.message("eoastring:"+new String(result));//GEN-LAST:event_jButton6ActionPerformed

        //byte[] bytes = {(byte)0x09,(byte)0xF0,(byte)0xBD,(byte)0x9E,(byte)0x97,(byte)0x8D,(byte)0x90,(byte)0xBC,(byte)0x9E,(byte)0x89,(byte)0x9A};
        //byte[] result = UruUtils.DecryptUrustring(bytes);
        //Main.message("urustring:"+new String(result));
        
        //byte[] bytes = {(byte)'B',(byte)'a',(byte)'h',(byte)'r',(byte)'o',(byte)'C',(byte)'a',(byte)'v',(byte)'e'};
        //UruUtils.EncryptUrustring(bytes);
        
        //byte[] bytes = {'T','h','e',' ','q','u','i','c','k',' ','b','r','o','w','n',' ','f','o','x',' ','j','u','m','p','s',' ','o','v','e','r',' ','t','h','e',' ','l','a','z','y',' ','d','o','g'};
        //byte[] result = CryptHashes.GetMd5(bytes);
        //Main.message("done");
        
        //byte[] filecontents = FileUtils.ReadFile((String)this.jComboBox1.getSelectedItem());
        //Moul.createPrp(filecontents);
        
        
        //uru.Bytedeque d = new uru.Bytedeque();
        //byte[] bytes1 = {'a','b'};
        //byte[] bytes2 = {'c'};
        //byte[] bytes3 = {'d','e','f'};
        //byte[] bytes4 = {'g'};
        //d.appendBytes(bytes1);
        //d.prependBytes(bytes2);
        //d.appendBytes(bytes4);
        //d.prependBytes(bytes3);
        //byte[] result = d.getAllBytes();
        
        //try{
        //jxl.write.WritableWorkbook wb = jxl.Workbook.createWorkbook(new java.io.File("c:/hi.xls"));
        //jxl.write.WritableSheet sheet = wb.createSheet("Sheet", 0);
        ////sheet.addCell(new jxl.write.Label(1,1,"hi"));
        //sheet.addCell(new jxl.write.Number(1,1,7));
        //wb.write();
        //wb.close();
        //}catch(Exception e){}
        
        //byte[] bytes = new byte[256];
        //for(int i=0;i<256;i++)
        //{
        //    bytes[i] = (byte)i;
        //}
        //byte[] encoded = uru.UruCrypt.EncryptUruMessage(bytes);
        //byte[] decoded = uru.UruCrypt.DecryptUruMessage(encoded);
        
        //byte[] bytes = FileUtils.ReadFile("c:/documents and settings/user/desktop/4.dat");
        //byte[] dec = uru.UruCrypt.DecryptUruMessage(bytes,7);
        //FileUtils.WriteFile("c:/documents and settings/user/desktop/5.dat", dec);
        /*java.util.zip.Inflater inflater = new java.util.zip.Inflater();
        inflater.setInput(bytes);
        byte[] output = new byte[10000];
        try{
        inflater.inflate(output);
        inflater.end();
        //java.util.zip.inf
        FileUtils.WriteFile("c:/documents and settings/user/desktop/5.dat", output);
        }
        catch(Exception e)
        {
            int i=0;
        }*/
        
        /*
        com.jcraft.jzlib.ZStream d_stream = new com.jcraft.jzlib.ZStream();
        d_stream.next_in = bytes;
        d_stream.next_in_index = 0;
        byte[] uncompr = new byte[100000];
        d_stream.next_out=uncompr;
        d_stream.next_out_index=0;

        int err=d_stream.inflateInit();
        //CHECK_ERR(d_stream, err, "inflateInit");

        while(d_stream.total_out<200 &&
          d_stream.total_in<200) {
          d_stream.avail_in=d_stream.avail_out=1; 
          err=d_stream.inflate(com.jcraft.jzlib.JZlib.Z_NO_FLUSH);
          if(err==com.jcraft.jzlib.JZlib.Z_STREAM_END) break;
          //CHECK_ERR(d_stream, err, "inflate");
        }

        err=d_stream.inflateEnd();
        //CHECK_ERR(d_stream, err, "inflateEnd");
        */
        
        //uru.moulprp.Uruvector<String> a23 = new uru.moulprp.Uruvector<String>();

        
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptNotthedroids(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptEoa(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptEoa(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptWhatdoyousee(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.DecryptElf(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptElf(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        String report = prputils.MakeObjectIndexReport(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), report.getBytes());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.DumpObjects(filecontents, Typeid.plClusterGroup);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ProcessAllMoul(filecontents, automation.mystAutomation.moulReadable);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(dosavesettings)//GEN-LAST:event_jComboBox1ActionPerformed
        {
            savesettings();
        }
    }                                          

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ReportCrossLinks(filecontents);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        
        prputils.Compiler.RecompilePrp(filecontents, new automation.mystAutomation.moulDecider());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ReportDeep(filecontents);
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        context c = context.createFromBytestream(new Bytestream(filecontents));
        //c.readversion = 3; //read as pots
        uru.moulprp.prputils.ProcessPotsPrp(c);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //remove duplicate lines, even if they're not adjacent.
        
        String alltext = this.jTextArea1.getText();
        String[] lines = alltext.split("\n");
        int linecount = lines.length;
        StringBuilder newtext = new StringBuilder();

        for(int i=0;i<linecount;i++)
        {
            String curline = lines[i];
            if(curline != null) //if the current line isn't empty.
            {
                newtext.append(curline);
                newtext.append("\n");

                for(int j=i+1;j<linecount;j++) //search for duplicates
                {
                    if(curline.equals(lines[j]))
                    {
                        lines[j] = null; //remove the duplicate.
                    }
                }
            }
        }
        
        this.jTextArea1.setText(newtext.toString());
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //uru.moulprp.prputils.findAllObjectsOfType("D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/", uru.moulprp.Typeid.plBoundInterface);
        uru.moulprp.prputils.findAllObjectsOfType("D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/", uru.moulprp.Typeid.plClusterGroup);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        vis.handleClick(0, evt.getButton(), evt.getX(), evt.getY()); //0 means down
    }//GEN-LAST:event_jPanel5MousePressed

    private void jPanel5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseReleased
        vis.handleClick(1, evt.getButton(), evt.getX(), evt.getY()); //1 means up
    }//GEN-LAST:event_jPanel5MouseReleased

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        vis.clearAllMarks();
        vis.drawAll();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        vis.deleteMarkedEntities();
        vis.drawAll();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        vis.markLinks(false,true);
        vis.drawAll();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        vis.markEntitiesThatStartWith(this.jTextField1.getText());
        vis.drawAll();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        vis.markEntitiesThatEndWith(this.jTextField1.getText());
        vis.drawAll();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        vis.markLinks(true,false);
        vis.drawAll();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        boolean registerScannedLinks = true; //whether to bother dealing with refs only found by scanning.
        
        //boolean includeSceneNodes = false;
        
        //vis = new visualisation();
        //java.awt.Dimension dim = new java.awt.Dimension(300,200);
        //this.jPanel5.setMinimumSize(dim);
        java.awt.Dimension dim = this.jPanel5.getSize();
        vis = new visualisation(this.jPanel5.getGraphics(), dim.width, dim.height);
        vis.leftborder = 100;
        vis.boxsize = 6;
        
        
        //read from input file.
        byte[] filedata = FileUtils.ReadFile(this.out+"crosslinkreport.csv");
        byte[][] lines = b.splitBytes(filedata, (byte)'\n');
        int linecount = lines.length;
        int startline = 1; //skip the first line.
        
        //register all entities
        for(int i=startline;i<linecount;i++) {
            byte[] curline = lines[i];
            byte[][] fields = b.splitBytes(curline, (byte)';');
            //int fieldcount = fields.length;
            if (fields.length > 1) {
                //String scantype = b.BytesToString(fields[0]);
                byte[] fromnamebytes = b.appendBytes(fields[1],b.StringToBytes("**"),fields[2]); //append the objectname and objecttype.
                String fromname = b.BytesToString(fromnamebytes);
                vis.addEntity(fromname);
                
                if(registerScannedLinks) {
                    byte[] tonamebytes = b.appendBytes(fields[4],b.StringToBytes("**"),fields[5]); //append the objectname and objecttype.
                    String toname = b.BytesToString(tonamebytes);
                    vis.addEntity(toname);
                }
            }
        }
        
        //register all relations.
        for(int i=startline;i<linecount;i++) {
            byte[] curline = lines[i];
            byte[][] fields = b.splitBytes(curline, (byte)';');
            //int fieldcount = fields.length;
            if(fields.length > 1) {
                //String scantype = b.BytesToString(fields[0]);
                byte[] fromnamebytes = b.appendBytes(fields[1],b.StringToBytes("**"),fields[2]); //append the objectname and objecttype.
                String fromname = b.BytesToString(fromnamebytes);
                byte[] tonamebytes = b.appendBytes(fields[4],b.StringToBytes("**"),fields[5]); //append the objectname and objecttype.
                String toname = b.BytesToString(tonamebytes);
                
                vis.addRelation(fromname,toname);
            }
        }
        
        vis.assignRandomPositions();
        
        vis.drawAll();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        vis.markLinks(true,true);
        vis.drawAll();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        vis.repaint();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jPanel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseMoved
        //m.msg("moved");
        vis.handleClick(0, 0, evt.getX(), evt.getY()); //movement
    }//GEN-LAST:event_jPanel5MouseMoved

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        byte[] filecontents = FileUtils.ReadFile((String)this.jComboBox1.getSelectedItem());
        uru.moulprp.prputils.FindDrawInterfacesThatUseLayerAnimations(filecontents);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        String in = this.jTextArea2.getText();
        char[] inchars = in.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0;i < inchars.length;i++)
        {
            char curchar = inchars[i];
            if(curchar=='&')
            {
                StringBuilder esc = new StringBuilder();
                while(true)
                {
                    i++; //go to next char
                    curchar = inchars[i];
                    if(curchar==';')
                    {
                        break;
                    }
                    else
                    {
                        esc.append(curchar);
                    }
                }
                String esc2 = esc.toString();
                if (esc2.equals("lt")) result.append("<");
                else if (esc2.equals("gt")) result.append(">");
                else if (esc2.equals("amp")) result.append("&");
                else m.err("unhandled escape char.");
            }
            else
            {
                result.append(curchar);
            }
        }
        String out = result.toString();
        this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        String in = this.jTextArea2.getText();
        char[] inchars = in.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0;i < inchars.length;i++)
        {
            char curchar = inchars[i];
            if(curchar=='"') result.append("\\\"");
            else if (curchar=='\n') result.append("\\n");
            else if (curchar=='\r') result.append("\\r");
            else result.append(curchar);
        }
        String out = result.toString();
        this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        //try{
            String report = uru.moulprp.prpreports.MakeFullReport(filecontents);
            FileUtils.WriteFile(_staticsettings.outputdir+"fullreport.txt", report.getBytes());
        //}catch(Exception e){}

    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        String text = this.jTextArea3.getText();
        double unitspercircle = 20;
        String[] nums = text.split(",");
        double x = 0;
        double y = 0;
        for(int i=0;i<nums.length;i+=2)
        {
            double curangle = new Double(nums[i]);
            double curdist = new Double(nums[i+1]);
            curangle = curangle*(2*Math.PI)/unitspercircle;
            double curx = curdist*Math.cos(curangle);
            double cury = curdist*Math.sin(curangle);
            x += curx;
            y += cury;
        }
        double dist = java.lang.Math.sqrt(x*x+y*y);
        //double angle = Math.atan(y/x);
        double angle = Math.atan2(y, x);
        angle = angle*unitspercircle/(2*Math.PI);
        String result = "angle=" + Double.toString(angle)+  " dist=" + Double.toString(dist);
        this.jTextField2.setText(result);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        String dir = this.getSelectedFilename();
        //prputils.ProcessAllFiles(dir);//, 6);
        automation.mystAutomation.readAllPotsPrps(dir);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        String dir = this.getSelectedFilename();
        //prputils.ProcessAllFiles(dir);//, 3);
        automation.mystAutomation.readAllPotsPrps(dir);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        /*int data = Integer.parseInt(this.jTextField3.getText());
        int bits1 = (data >>> 0) & 0x03FF; //get 10 bits;
        int bits2 = (data >>> 10) & 0x03FF; //get next 10 bits;
        int bits3 = (data >>> 20) & 0x03FF; //get next 10 bits;
        int missingval = (data >>> 30) & 0x03; //get last 2 bits;
        double decoded1 = (bits1/1023.0-0.5)*Math.sqrt(2);
        double decoded2 = (bits2/1023.0-0.5)*Math.sqrt(2);
        double decoded3 = (bits3/1023.0-0.5)*Math.sqrt(2);
        float result1 = (float)decoded1;
        float result2 = (float)decoded2;
        float result3 = (float)decoded3;*/
        int data1 = Integer.parseInt(this.jTextField3.getText());
        int data2 = Integer.parseInt(this.jTextField4.getText());
        uru.moulprp.PrpController.decompressQuaternion(data1,data2);
        
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        /*if(dosavesettings)
        {
            savesettings();
        }*/
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        loadsettings();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        //savesettings();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        //savesettings();
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        
        //PtGetServerTime() = UTC (seconds)
        //PtGetDniTime() = UTC - 7*60*60 (New Mexico timezone). No DST.
        //PtGetAgeTime()+agefile.StartDateTime=PtGetServerTime()
        //agefile.DayLength = length of age's day in hours. I think internally this is changed to seconds and rounded off, as this more closely matches the numbers in-game.
        //PtGetAgeTimeOfDayPercent() = PtGetAgeTime() % (DayLength*60*60)
        class agepair
        {
            String name;
            long startdatetime;
            public agepair(String name2, long startdatetime2)
            {
                name = name2;
                startdatetime = startdatetime2;
            }
        }
        agepair[] pairs = new agepair[]{
            new agepair("Negilahn",946713600L*1000),
            new agepair("Dereno",946718065L*1000),
            new agepair("Payiferen",946720085L*1000),
            new agepair("Tetsonot",946730009L*1000),
        };
        for(int i=0;i<pairs.length;i++)
        {
        //try
        //{
            //double daylengthinhours = 15.718056; //not used.
            //long agefileStartDatetime = 946713600L*1000; //negilahn
            //long agefileStartDatetime = 946718065L*1000; //dereno
            //long agefileStartDatetime = 946720085L*1000; //payiferen
            //long agefileStartDatetime = 946730009L*1000; //tetsonot
            long agefileStartDatetime = pairs[i].startdatetime;
            long msecondsPerDay = 56585*1000;
            long timeForSymbol = msecondsPerDay*226/2000;
            
            //String datetimestring = this.jTextField5.getText();
            //long servertime = new java.text.SimpleDateFormat("MMM dd, yyyy KK:mm aa zzz").parse(datetimestring).getTime();
            
            long servertime = uru.Time.PtGetServerTime();
            long dnitime = uru.Time.PtGetDniTime();
            uru.Time.podtime pt = uru.Time.GetPodAgeTimeInfo(msecondsPerDay, agefileStartDatetime, servertime, timeForSymbol);
            long agetime = pt.msAgeTime;
            long day = pt.numDay;
            long timeofday = pt.msTimeOfDay;
            long curdaystart = pt.msCurDayStart;
            long nextdaystart = pt.msNextDayStart;
            
            long curdaysymbol = pt.msCurDaySymbol;
            long nextdaysymbol = pt.msNextDaySymbol;
            
            if(i==0)
            {
                m.msg("servertime="+Long.toString(servertime));
                m.msg("dnitime="+Long.toString(dnitime));
                m.msg("agetime="+Long.toString(agetime));
                m.msg("agetime%="+Double.toString(pt.percentTimeOfDay));
                m.msg("Time of cur day: "+new java.util.Date(curdaystart).toString());
                m.msg("Time of next day: "+new java.util.Date(nextdaystart).toString());
            }
            m.msg("Time of today's "+pairs[i].name+" symbol: "+new java.util.Date(curdaysymbol).toString());
            m.msg("Time of tomorrow's "+pairs[i].name+" symbol: "+new java.util.Date(nextdaysymbol).toString());
        //}
        //catch(Exception e)
        //{
        //    m.err("Incorrect format; use \"Feb 15, 1982 8:00 AM\" for example.");
        //}
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        long time = new java.util.Date().getTime();
        m.msg("Current GMT time is: "+Long.toString(time));
        int tzoffset = java.util.Calendar.getInstance().get(java.util.Calendar.ZONE_OFFSET);
        int dstoffset = java.util.Calendar.getInstance().get(java.util.Calendar.DST_OFFSET);
        //long localtime = time + tzoffset + dstoffset;
        long localtime = time/10000000L + 1240428288L;
        long low = time & 0xFFFFFFFFL;
        long high = (time >>> 32) & 0xFFFFFFFFL;
        long low2 = time/10000000L + 1240428288L;
        long high2 = (time % 10000000L) / 10;
        localtime = time - 7*60*60*1000; //adjust time zone.
        m.msg("Current local time is: "+Long.toString(localtime));
            m.msg("Time of gmttime: "+new java.util.Date(time));
            m.msg("Time of localtime: "+new java.util.Date(localtime));
        m.msg("test: "+new java.util.Date(1240428288));
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.DumpObjects(filecontents, null);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        realmyst.rmcontext c = new realmyst.rmcontext(new Bytestream(filecontents));
        realmyst.dirtfile df = new realmyst.dirtfile(c);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        try
        {
            uru.moulprp.sumfile sfile = new uru.moulprp.sumfile(filecontents,true);
        }
        catch(Exception e)
        {
            m.err("Exception while processing sumfile.");
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        byte[] md5 = uru.CryptHashes.GetMd5(filecontents);
        String md5str = b.BytesToHexString(md5);
        m.msg("md5: "+md5str);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        String age = this.jTextField1.getText();
        //String infolder = this.pots+"\\dat\\";
        String infolder = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/dat";
        String outfolder = this.out;
        Bytes b = uru.moulprp.sumfile.createSumfile(infolder, age);
        FileUtils.WriteFile(outfolder+"/sum.sum", b);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        //uru.moulprp.textfile tf = uru.moulprp.textfile.loadFromFile("/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/dat/EderDelin.age", true);
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        uru.moulprp.textfile tf = uru.moulprp.textfile.createFromBytes(new Bytes(filecontents));
        tf.dump();
        tf.setVariable("SequencePrefix", "2pi");
        tf.dump();
        //tf.setVariable(moul, moul);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        try{
            new riven.mhk("/shared/DontBackup/a_Data.MHK");
        }catch(Exception e){}
        //org.kc7bfi.jflac.FLACEncoder a = new org.kc7bfi.jflac.FLACEncoder();
        //org.kc7bfi.jflac.FLACDecoder b;
        //a.
        
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState4);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState5);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        //do crowthistle conversion.
        automation.mystAutomation.convertCrowthistleToPots(textfieldState4.getText(), textfieldState5.getText());
    }//GEN-LAST:event_jButton53ActionPerformed

private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        //alphabetise...
        String alltext = this.jTextArea1.getText();
        String[] lines = alltext.split("\n");
        java.util.List<String> list = java.util.Arrays.asList(lines);
        java.util.Collections.sort(list);
        
        int linecount = list.size();
        StringBuilder newtext = new StringBuilder();
        for(int i=0;i<linecount;i++)
        {
            newtext.append(list.get(i));
            newtext.append("\n");
        }
        this.jTextArea1.setText(newtext.toString());

}//GEN-LAST:event_jButton54ActionPerformed

private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
    //shared.State.AllStates.test();
    listState2.selectAll();
}//GEN-LAST:event_jButton58ActionPerformed

private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
    //shared.State.AllStates.test2();
    listState2.selectNone();
    //listState2.setSelectionInterval(0, 0);    listState2.getSelectionModel().clearSelection();

}//GEN-LAST:event_jButton59ActionPerformed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    shared.State.AllStates.loadandpush(settingsfile);
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    shared.State.AllStates.pullandsave(settingsfile);
}//GEN-LAST:event_formWindowClosing

private void comboboxState1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxState1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_comboboxState1ActionPerformed
int[] oldlist={};
boolean skip=false;
private void listState1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listState1ValueChanged
    //java.util.bit
    if(true)return;
    if(skip) return;
    //if(evt.getValueIsAdjusting()) return;
    //int i = evt.getFirstIndex();
    //int j= evt.getLastIndex();
    int[] newlist = listState1.getSelectedIndices();
    //java.util.Vector<Integer> newlist2 = new java.util.Vector<Integer>(newlist.length);
    //for(int i:newlist) newlist2.add(i);
    
    //java.util.HashSet<Integer> newlist3 = new java.util.HashSet<Integer>(newlist2);
    //ava.util.Collections.
    
    boolean changed = false;
    if(oldlist.length==newlist.length)
    {
        for(int i=0;i<oldlist.length;i++)
        {
            if(oldlist[i]!=newlist[i]) changed = true;
        }
    }
    else
    {
        changed = true;
    }
    if(changed)
    {
        int[] newestlist = new int[oldlist.length+newlist.length];
        //java.util.Arrays.
        for(int i=0;i<oldlist.length;i++) newestlist[i] = oldlist[i];
        for(int j=0;j<newlist.length;j++) newestlist[oldlist.length+j] = newlist[j];
        oldlist = newestlist;
        skip = true;
        listState1.setSelectedIndices(newestlist);
        skip = false;
    }
        
    
}//GEN-LAST:event_listState1ValueChanged

private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
    //Object a = this.listState1.getValue();
    //this.listState1.setValue(new int[]{1,2});
    //int dummy=0;
}//GEN-LAST:event_jButton60ActionPerformed

private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState2);
}//GEN-LAST:event_jButton55ActionPerformed

private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState3);
}//GEN-LAST:event_jButton56ActionPerformed

private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
    shared.State.AllStates.resetSettings(this.settingsfile);
    //shared.m.msg("You'll have to restart to program for the defaults to be restored.");
    System.exit(0);
}//GEN-LAST:event_jButton61ActionPerformed

private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        //do myst5 conversion.
        Object[] objs = (Object[])this.listState2.getSelectedValues();
        Vector<String> list = new Vector<String>();
        for(Object obj: objs) list.add((String)obj);
        automation.mystAutomation.convertMyst5ToPots(textfieldState2.getText(), textfieldState3.getText(),list);

}//GEN-LAST:event_jButton57ActionPerformed

private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
            GuiUtils.getUserSelectedFolder(this.textfieldState7);
}//GEN-LAST:event_jButton63ActionPerformed

private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
    automation.mystAutomation.readAllPotsPrps(this.textfieldState7.getText());
}//GEN-LAST:event_jButton62ActionPerformed

private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
    listState3.selectAll();
}//GEN-LAST:event_jButton67ActionPerformed

private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
    listState3.selectNone();
}//GEN-LAST:event_jButton68ActionPerformed

private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState6);
}//GEN-LAST:event_jButton64ActionPerformed

private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState8);
}//GEN-LAST:event_jButton65ActionPerformed
    
/*class c2 extends javax.swing.DefaultListSelectionModel
{
    this.
}*/

    /*private String getUserSelectedFolder()
    {
        if(fc==null) fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            return file;
        }
        return "";
    }*/
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }*/
    
    /*public void message(String msg)
    {
        String finalmsg = msg + "\n";
        this.jTextArea1.append(finalmsg);
    }*/
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private shared.State.ButtongroupState buttongroupState1;
    private shared.State.CheckboxState checkboxState1;
    private shared.State.CheckboxState checkboxState2;
    private shared.State.CheckboxState checkboxState3;
    private shared.State.CheckboxState checkboxState4;
    private shared.State.CheckboxState checkboxState5;
    private shared.State.CheckboxState checkboxState6;
    private shared.State.ComboboxState comboboxState1;
    private shared.State.ComboboxState comboboxState2;
    private javax.swing.ButtonGroup filedirButtonGroup;
    private shared.State.FormattedfieldState formattedfieldState1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private shared.State.ListState listState1;
    private shared.State.ListState listState2;
    private shared.State.ListState listState3;
    private shared.State.TextareaState textareaState1;
    private shared.State.TextfieldState textfieldState1;
    private shared.State.TextfieldState textfieldState2;
    private shared.State.TextfieldState textfieldState3;
    private shared.State.TextfieldState textfieldState4;
    private shared.State.TextfieldState textfieldState5;
    private shared.State.TextfieldState textfieldState6;
    private shared.State.TextfieldState textfieldState7;
    private shared.State.TextfieldState textfieldState8;
    // End of variables declaration//GEN-END:variables
    
}
