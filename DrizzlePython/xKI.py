#Drizzle
#  This file is a decompiled version of material copyright Cyan Worlds Inc.
#  It is included only for convienience, and you must not use code from this file elsewhere.
#  It could be replaced in principle by a depacker->decompiler->patcher->compiler->packer.
#  All hooks and changes appear within #Drizzle and #/Drizzle tags.
#/Drizzle
# emacs-mode: -*- python-*-
MaxVersionNumber = 61
MinorVersionNumber = 1
from Plasma import *
from PlasmaTypes import *
from PlasmaKITypes import *
from PlasmaVaultConstants import *
from PlasmaNetConstants import *
from HTMLParser import *
import xLocalization
import xKIExtChatCommands
import time
import string
import xCensor
import xLinkingBookDefs
import xBookGUIs
import whrandom
import glob
import os
import ascii
from xPsnlVaultSDL import *
#Drizzle
from ModuleDrizzle import *
#/Drizzle
KIBlackbar = ptAttribGUIDialog(1, 'The Blackbar dialog')
KIMini = ptAttribGUIDialog(2, 'The KIMini dialog')
KIYesNo = ptAttribGUIDialog(3, 'The KIYesNo dialog')
BigKI = ptAttribGUIDialog(5, 'The BIG KI (Mr. BigStuff)')
NewItemAlert = ptAttribGUIDialog(7, 'The new item alert dialog')
KIOnAnim = ptAttribAnimation(12, 'Turn on/off the KI on animation')
KIOnResp = ptAttribResponder(13, 'Turn On responder')
KIOffResp = ptAttribResponder(14, 'Turn Off responder')
KIMicroBlackbar = ptAttribGUIDialog(18, 'The micro Blackbar dialog')
KINanoBlackBar = ptAttribGUIDialog(20, 'The nano Blackbar dialog')
KIGUIInitialized = 0
IAmAdmin = 0
IKIDisabled = 0
IKIHardDisabled = 0
IminiKIWasUp = 0
WaitingForAnimation = 0
WeAreTakingAPicture = 0
ISawTheKIAtleastOnce = 0
IsPlayingLookingAtKIMode = 0
kX1 = 1
gExpansionPack = kX1
gShowGPSCheat = 0
kFadeTimer = 1
kBKITODCheck = 2
kAlertHideTimer = 3
kTakeSnapShot = 4
kMarkerGameTimer = 5
theKILevel = kNanoKI
gKIMarkerLevel = 0
gAlreadyCheckedCGZGame = 0
gKIHasJournal = 0
kChronicleFontSize = 'PlayerKIFontSize'
kChronicleFontSizeType = 2
kChronicleFadeTime = 'PlayerKIFadeTime'
kChronicleFadeTimeType = 2
kChronicleOnlyPMs = 'PlayerKIOnlyPMsBuddies'
kChronicleOnlyPMsType = 2
kChronicleBuddiesOnRequest = 'PlayerKIBuddiesOnRequest'
kChronicleBuddiesOnRequestType = 2
kChronCGZPlaying = 'CGZPlaying'
kChronicleHasJournal = 'PlayerKIHasJournal'
kChronicleHasJournalType = 2
kMiniMaximizeRGID = 34
kExitButtonID = 4
kPlayerBookCBID = 15
kJournalBookCBID = 35
kBBCCRButtonID = 200
kmicroChatButton = 100
kRolloverLeftID = 998
kRolloverRightID = 999
kChatCaretID = 12
kChatEditboxID = 5
kChatDisplayArea = 70
kFolderPlayerList = 30
kPlayerList = 31
kminiToggleBtnID = 1
kminiPutAwayID = 4
kminiTakePicture = 60
kminiMuteAll = 61
kminiPrivateToggle = 62
kminiCreateJournal = 63
kminiDragBar = 50
kminiChatScrollUp = 51
kminiChatScrollDown = 52
kminiPlayerListUp = 53
kminiPlayerListDown = 54
kBKIGPS1TextID = 162
kBKIGPS2TextID = 163
kBKIGPS3TextID = 164
kmini7Indicator1 = 71
kmini7Indicator2 = 72
kmini7Indicator3 = 73
kmini7Indicator4 = 74
kminiMarkerIndicator01 = 601
kminiMarkerIndicatorLast = 625
gMarkerColors = {'off': 0.0,
 'redlt': 1.5,
 'red': 3.5,
 'yellowlt': 6.0,
 'yellow': 8.5,
 'purplelt': 11.0,
 'purple': 13.5,
 'greenlt': 16.0,
 'green': 18.5}
kminiGZDrip = 700
kminiGZActive = 701
kminiGZMarkerGameActive = 702
kminiGZMarkerInRange = 703
kFadeNotActive = 0
kFadeFullDisp = 1
kFadeDoingFade = 2
kFadeStopping = 3
FadeMode = kFadeNotActive
MiniKIFirstTimeShow = 1
FadeEnableFlag = 1
CurrentFadeTick = 0
TicksOnFull = 30
kFadeTimeMax = 120
kFullTickTime = 1.0
TicksOnFade = 4
kFadeTickTime = 0.20000000000000001
OriginalForeAlpha = 1.0
OriginalSelectAlpha = 1.0
OriginalminiKICenter = None
LastminiKICenter = None
FontSizeList = [7,
 8,
 10,
 12,
 14]
PreviousTime = '20:20'
TimeBlinker = 1
gImageDirectory = 'KIimages'
gImageSearchPath1 = (('.\\' + gImageDirectory) + '\\*.jpg')
gImageSearchPath2 = (('.\\' + gImageDirectory) + '\\*.jpeg')
gImageFileNameTemplate = 'KIimage'
gImageFileSearch = (((('.\\' + gImageDirectory) + '\\') + gImageFileNameTemplate) + '*.jpg')
gCurrentImageFilename = None
gLastImageFileNumber = 0
gJournalBookFilePath = '.\\MyJournals\\'
gJournalBookFileExt = 'Journal.html'
kBKIImageStartX = 112
kBKIImageStartY = 212
BKInEditMode = 0
BKEditContent = None
BKEditField = -1
kBKToggleMiniID = 14
kBKIPutAwayID = 4
kBKICurAgeNameID = 60
kBKICurTimeID = 61
kBKPlayerListID = 65
kBKRadioModeID = 68
kBKPlayerName = 200
kBKPlayerID = 201
kBKPrevPictureButton = 300
kBKNextPictureButton = 310
kBKDeletePictureButton = 320
kBKIPICImage = 400
Clear = ptColor(0, 0, 0, 0)
AgenBlueDk = ptColor(0.65000000000000002, 0.63529999999999998, 0.745, 1.0)
AgenGreenLt = ptColor(0.87450000000000006, 1.0, 0.84999999999999998, 1.0)
AgenGreenDk = ptColor(0.65000000000000002, 0.745, 0.63529999999999998, 1.0)
DniYellow = ptColor(0.85099999999999998, 0.81200000000000006, 0.57599999999999996, 1.0)
DniCyan = ptColor(0.57599999999999996, 0.86699999999999999, 0.85099999999999998, 1.0)
DniBlue = ptColor(0.78000000000000003, 0.70599999999999996, 0.87, 1.0)
DniRed = ptColor(1.0, 0.216, 0.38, 1.0)
DniGreen = ptColor(0.69799999999999995, 0.878, 0.76100000000000001, 1.0)
DniGreenDk = ptColor(0.0, 0.59599999999999997, 0.21099999999999999, 1.0)
DniPurple = ptColor(0.878, 0.69799999999999995, 0.81899999999999995, 1.0)
DniWhite = ptColor().white()
DniShowRed = ptColor(1.0, 0.85099999999999998, 0.874, 1.0)
DniHideBlue = ptColor(0.78000000000000003, 0.70599999999999996, 0.87, 0.29999999999999999)
DniColorShowBtn = DniShowRed
DniColorGhostBtn = DniHideBlue
DniSelectableColor = DniGreen
DniSelectedColor = DniWhite
DniStaticColor = DniBlue
kYesNoTextID = 12
kYesButtonID = 10
kNoButtonID = 11
kYesButtonTextID = 60
kNoButtonTextID = 61
kYNQuit = 0
kYNDelete = 1
kYNOfferLink = 2
kYNOutside = 3
kYNKIFull = 4
kYNWanaPlay = 5
kYNNoReason = 6
YNWhatReason = kYNQuit
YNOutsideSender = None
YeeshaBook = None
IsYeeshaBookEnabled = 1
IsEntireYeeshaBookEnabled = 1
JournalBook = None
gCurBookIsYeesha = 1
AlertTimerActive = 0
kAlertTimeDefault = 10.0
kMaxBookAlertTime = 20.0
AlertTimeToUse = kAlertTimeDefault
kAlertKIAlert = 60
kAlertBookAlert = 61
kAlertJournalAlert = 62
kAlertMicroJournalAlert = 63
kMGNotActive = 0
kMGGameCreation = 1
kMGGameOn = 2
MarkerGameState = kMGNotActive
WorkingMarkerFolder = None
CurrentPlayingMarkerGame = None
kMFOverview = 1
kMFEditing = 2
kMFEditingMarker = 3
kMFPlaying = 4
MFdialogMode = kMFOverview
MarkerGameTimeID = 0
MarkerJoinRequests = []
gGZPlaying = 0
gGZMarkerInRange = 0
gGZMarkerInRangeRepy = None
gMarkerToGetColor = 'off'
gMarkerGottenColor = 'off'
gMarkerToGetNumber = 0
gMarkerGottenNumber = 0
class JournalHTMLParser(HTMLParser,):
    __module__ = __name__

    def __init__(self):
        HTMLParser.__init__(self)
        self.titleText = ''
        self.bodyText = ''
        self.inBody = 0
        self.inTitle = 0



    def IAddText(self, text):
        if self.inTitle:
            self.titleText += text
        if self.inBody:
            self.bodyText += text



    def handle_starttag(self, tag, attrs):
        if (tag == 'body'):
            self.inBody = 1
        elif (tag == 'title'):
            self.inTitle = 1
        elif (tag == 'br'):
            self.IAddText('\n')
        elif (tag == 'p'):
            pass
        else:
            PtDebugPrint(('JournalHTMLParser: Dropping unused open tag: ' + tag))



    def handle_startendtag(self, tag, attrs):
        if (tag == 'br'):
            self.IAddText('\n')
        else:
            PtDebugPrint(('JournalHTMLParser: Dropping unused open/close tag: ' + tag))



    def handle_endtag(self, tag):
        if (tag == 'body'):
            self.inBody = 0
        elif (tag == 'title'):
            self.inTitle = 0
        elif (tag == 'p'):
            self.IAddText('\n')
        else:
            PtDebugPrint(('JournalHTMLParser: Dropping unused close tag: ' + tag))



    def handle_data(self, data):
        strippedData = ''
        for character in data:
            if (not (character == '\n')):
                strippedData += character

        self.IAddText(strippedData)



    def handle_charref(self, name):
        value = int(name)
        character = ascii.unctrl(value)
        self.IAddText(str(character))



    def handle_entityref(self, name):
        if (name == 'gt'):
            self.IAddText('>')
        elif (name == 'lt'):
            self.IAddText('<')
        elif (name == 'quot'):
            self.IAddText('"')
        elif (name == 'amp'):
            self.IAddText('&')
        elif (name == 'nbsp'):
            self.IAddText(' ')
        else:
            PtDebugPrint(('JournalHTMLParser: Unknown entity ref: ' + name))




def MakeJournalFilename():
    PlayerName = PtGetClientName()
    filteredName = ''
    for letter in PlayerName:
        if (not ((letter == '/') or ((letter == '\\') or ((letter == ':') or ((letter == '*') or ((letter == '?') or ((letter == '"') or ((letter == '<') or ((letter == '>') or (letter == '|')))))))))):
            filteredName += letter

    return ((gJournalBookFilePath + filteredName) + gJournalBookFileExt)



def WriteHTMLFile(title, bodyText, fileName):
    makeDirectory = 0
    try:
        JournalFile = file(fileName, 'w')
    except:
        PtDebugPrint("Error making the journal file, maybe the path doesn't exist, attempting to make the directory")
        makeDirectory = 1
    if makeDirectory:
        try:
            os.mkdir(gJournalBookFilePath)
            JournalFile = file(fileName, 'w')
        except:
            PtDebugPrint("Couldn't make the directory, aborting save of journal")
            return 
    filteredText = ''
    for letter in bodyText:
        if (letter == '\n'):
            filteredText += '<br/>\n'
        elif (letter == '>'):
            filteredText += '&gt;'
        elif (letter == '<'):
            filteredText += '&lt;'
        elif (letter == '&'):
            filteredText += '&amp;'
        else:
            filteredText += letter

    JournalFile.write('<?xml version="1.0" encoding="UTF-8"?>\n')
    JournalFile.write('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n')
    JournalFile.write('<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">\n')
    JournalFile.write((('<head><title>' + title) + '</title></head>\n'))
    JournalFile.write((('<body>' + filteredText) + '</body></html>'))
    JournalFile.close()



def DirectoryCmp(x, y):
    return cmp(os.stat(y).st_ctime, os.stat(x).st_ctime)


class xKI(ptModifier,):
    __module__ = __name__

    def __init__(self):
        ptModifier.__init__(self)
        self.id = 199
        self.version = MaxVersionNumber
        PtDebugPrint(('__xKI: Max version %d - minor version %d' % (MaxVersionNumber,
         MinorVersionNumber)))



    def OnInit(self):
        try:
            os.mkdir(gImageDirectory)
            PtDebugPrint(('xKI: %s directory created' % gImageDirectory), level=kDebugDumpLevel)
        except OSError:
            PtDebugPrint(('xKI: %s already created' % gImageDirectory), level=kDebugDumpLevel)
        PtLoadDialog('KIBlackBar', self.key)
        PtLoadDialog('KINanoBlackBar', self.key)
        PtLoadDialog('KIMicroBlackBar', self.key)
        PtLoadDialog('KIMini', self.key)
        PtLoadDialog('KIMain', self.key)
        PtLoadDialog('KIYesNo', self.key)
        PtLoadDialog('KINewItemAlert', self.key)
        PtLoadDialog('OptionsMenuGUI')
        PtLoadDialog('IntroBahroBgGUI')
        PtLoadDialog('OptionsMenuGUI')
        PtLoadDialog('KeyMapDialog')
        PtLoadDialog('GameSettingsDialog')
        PtLoadDialog('CalibrationGUI')
        PtLoadDialog('TrailerPreviewGUI')
        PtLoadDialog('KeyMap2Dialog')
        PtLoadDialog('AdvancedGameSettingsDialog')
        PtLoadDialog('OptionsHelpGUI')
        PtLoadDialog('bkNotebook')
        PtLoadDialog('bkBahroRockBook')
        PtLoadDialog('bkEditableBook')
        PtLoadDialog('YeeshaPageGUI')
        #Drizzle
        drizzle.OnInit(self);
        #/Drizzle


    #Drizzle

    def OnControlKeyEvent(self, controlKey, activeFlag):
        drizzle.OnControlKeyEvent(self,controlKey,activeFlag);

    def OnServerInitComplete(self):
        drizzle.OnServerInitComplete(self);

    #def OnAvatarSpawn(self):
    #    drizzle.OnAvatarSpawn(self);

    #/Drizzle


    def OnFirstUpdate(self):
        self.dnicoords = ptDniCoordinates()
        xBookGUIs.LoadAllBookGUIs()



    def __del__(self):
        PtUnloadDialog('KINanoBlackBar')
        PtUnloadDialog('KIMicroBlackBar')
        PtUnloadDialog('KIMini')
        PtUnloadDialog('KIMain')
        PtUnloadDialog('KIYesNo')
        PtUnloadDialog('KINewItemAlert')
        PtUnloadDialog('OptionsMenuGUI')
        PtUnloadDialog('IntroBahroBgGUI')
        PtUnloadDialog('OptionsMenuGUI')
        PtUnloadDialog('KeyMapDialog')
        PtUnloadDialog('GameSettingsDialog')
        PtUnloadDialog('CalibrationGUI')
        PtUnloadDialog('TrailerPreviewGUI')
        PtUnloadDialog('KeyMap2Dialog')
        PtUnloadDialog('AdvancedGameSettingsDialog')
        PtUnloadDialog('OptionsHelpGUI')
        PtUnloadDialog('bkNotebook')
        PtUnloadDialog('bkBahroRockBook')
        PtUnloadDialog('YeeshaPageGUI')



    def ILocalizeQuitNoDialog(self):
        yesButton = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
        noButton = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
        yesButton.setString(xLocalization.xKI.xYesNoQuitbutton)
        noButton.setString(xLocalization.xKI.xYesNoNoButton)



    def ILocalizeYesNoDialog(self):
        yesButton = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
        noButton = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
        yesButton.setString(xLocalization.xKI.xYesNoYESbutton)
        noButton.setString(xLocalization.xKI.xYesNoNoButton)



    def OnNotify(self, state, id, events):
        global WaitingForAnimation
        PtDebugPrint(('xKI: Notify  state=%f, id=%d' % (state,
         id)), level=kDebugDumpLevel)
        for event in events:
            if (event[0] == PtEventType.kBook):
                PtDebugPrint(('xKI: BookNotify  event=%d, id=%d' % (event[1],
                 event[2])), level=kDebugDumpLevel)
                if (theKILevel == kMicroKI):
                    if gCurBookIsYeesha:
                        plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                    else:
                        plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                elif gCurBookIsYeesha:
                    plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                else:
                    plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                if (event[1] == PtBookEventTypes.kNotifyImageLink):
                    if (event[2] == xLinkingBookDefs.kYeeshaBookLinkID):
                        if IsYeeshaBookEnabled:
                            PtDebugPrint('xKI:Book: hit linking panel', level=kDebugDumpLevel)
                            YeeshaBook.hide()
                            plybkCB.setChecked(0)
                            curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                            if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                                linkmgr = ptNetLinkingMgr()
                                linkmgr.linkToMyPersonalAgeWithYeeshaBook()
                                PtToggleAvatarClickability(true)
                    elif (event[2] >= xLinkingBookDefs.kYeeshaPageStartID):
                        whatpage = (event[2] - xLinkingBookDefs.kYeeshaPageStartID)
                        sdlvar = xLinkingBookDefs.xYeeshaPages[whatpage][0]
                        self.IToggleYeeshaPageSDL(sdlvar, 1)
                elif (event[1] == PtBookEventTypes.kNotifyShow):
                    pass
                elif (event[1] == PtBookEventTypes.kNotifyHide):
                    PtDebugPrint('xKI:Book: NotifyHide', level=kDebugDumpLevel)
                    PtToggleAvatarClickability(true)
                    plybkCB.setChecked(0)
                    if (not gCurBookIsYeesha):
                        JBText = JournalBook.getEditableText()
                        WriteHTMLFile((xLocalization.xJournalBookDefs.xPlayerJournalTitle % PtGetClientName()), JBText, MakeJournalFilename())
                elif (event[1] == PtBookEventTypes.kNotifyNextPage):
                    pass
                elif (event[1] == PtBookEventTypes.kNotifyPreviousPage):
                    pass
                elif (event[1] == PtBookEventTypes.kNotifyCheckUnchecked):
                    if (event[2] >= xLinkingBookDefs.kYeeshaPageStartID):
                        whatpage = (event[2] - xLinkingBookDefs.kYeeshaPageStartID)
                        sdlvar = xLinkingBookDefs.xYeeshaPages[whatpage][0]
                        self.IToggleYeeshaPageSDL(sdlvar, 0)
                return 

        if state:
            if (id == KIOnResp.id):
                self.IBigKIShowMode()
                WaitingForAnimation = 0
                toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
                toggleCB.enable()
            elif (id == KIOffResp.id):
                BigKI.dialog.hide()
                WaitingForAnimation = 0
                toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
                toggleCB.enable()



    def OnPageLoad(self, what, room):
        global gGZMarkerInRange
        global gGZMarkerInRangeRepy
        global KIGUIInitialized
        if (not KIGUIInitialized):
            self.IDetermineKILevel()
            self.IDetermineKIFlags()
            self.IDetermineGZ()
            if (theKILevel == kNanoKI):
                PtDebugPrint('Its a nanoKI', level=kDebugDumpLevel)
                KINanoBlackBar.dialog.show()
            elif (theKILevel == kMicroKI):
                PtDebugPrint('Its a microKI', level=kDebugDumpLevel)
                KIMicroBlackbar.dialog.show()
                if (not gKIHasJournal):
                    ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID)).hide()
            elif (theKILevel == kNormalKI):
                PtDebugPrint('Its a normalKI', level=kDebugDumpLevel)
                KIBlackbar.dialog.show()
                self.IClearBBMini()
                if (not gKIHasJournal):
                    ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID)).hide()
            KIGUIInitialized = 1
        if ((what == kLoaded) and (theKILevel > kMicroKI)):
            self.ICheckIfCGZShouldBeRunning()
            workingMF = ptMarkerMgr().getWorkingMarkerFolder()
            if (type(workingMF) != type(None)):
                if (workingMF.getGameType() != PtMarkerMsgGameType.kGameTypeQuest):
                    ptMarkerMgr().endGame()
                    self.IResetWorkingMarkerFolder()
            self.IminiKIPSetStatics()
        if (what == kUnloaded):
            if gGZMarkerInRange:
                gGZMarkerInRange = 0
                gGZMarkerInRangeRepy = None
                self.IRefreshMiniKIMarkerDisplay()
                NewItemAlert.dialog.hide()
                kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
                kialert.hide()



    def ICheckIfCGZShouldBeRunning(self):
        global gAlreadyCheckedCGZGame
        if (not gAlreadyCheckedCGZGame):
            gAlreadyCheckedCGZGame = 1
            vault = ptVault()
            entry = vault.findChronicleEntry(kChronCGZPlaying)
            if (type(entry) != type(None)):
                gCurrentMarkerGame = entry.chronicleGetValue()
                if (gCurrentMarkerGame != ''):
                    Mmgr = ptMarkerMgr()
                    if (not Mmgr.isGameRunning()):
                        PtDebugPrint("xKI::IDetermineEndGame - but it wasn't running in MarkTag world! - Restarting", level=kDebugDumpLevel)
                        curGameFolder = self.IFindGameInFolder(self.IFindHiddenFolder(), gCurrentMarkerGame)
                        if curGameFolder:
                            Mmgr.setWorkingMarkerFolder(curGameFolder)
                            Mmgr.createGame(120, PtMarkerMsgGameType.kGameTypeQuest, [])
                            Mmgr.startGame()



    def IFindHiddenFolder(self):
        vault = ptVault()
        jfolder = None
        master_agefolder = vault.getAgeJournalsFolder()
        if (type(master_agefolder) != type(None)):
            agefolderRefs = master_agefolder.getChildNodeRefList()
            for agefolderRef in agefolderRefs:
                agefolder = agefolderRef.getChild()
                agefolder = agefolder.upcastToFolderNode()
                if (type(agefolder) != type(None)):
                    if self.IsFolderHidden(agefolder):
                        jfolder = agefolder
                        break

        return jfolder



    def IFindGameInFolder(self, folder, gameName):
        if folder:
            folderRefs = folder.getChildNodeRefList()
            for jref in folderRefs:
                jnode = jref.getChild()
                jnode = jnode.upcastToMarkerListNode()
                if (type(jnode) != type(None)):
                    if (jnode.folderGetName() == gameName):
                        print ('Found %s marker game in folder %s' % (folder.folderGetName(),
                         jnode.folderGetName()))
                        return jnode

        return None



    def OnGUINotify(self, id, control, event):
        global OriginalForeAlpha
        global OriginalSelectAlpha
        global YNOutsideSender
        global OriginalminiKICenter
        global MiniKIFirstTimeShow
        global YNWhatReason
        PtDebugPrint(('xKI::OnGUINotify id=%d, event=%d control=' % (id,
         event)), control, level=kDebugDumpLevel)
        if (id == KIBlackbar.id):
            if (event == kDialogLoaded):
                pass
            elif ((event == kAction) or (event == kValueChanged)):
                bbID = control.getTagID()
                if (bbID == kMiniMaximizeRGID):
                    if (control.getValue() == 0):
                        if PtIsDialogLoaded('KIMini'):
                            self.IminiKIShow()
                    elif (control.getValue() == -1):
                        if PtIsDialogLoaded('KIMini'):
                            self.IminiKIHide()
                elif (bbID == kExitButtonID):
                    yesText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
                    yesText.setString(xLocalization.xKI.xLeaveGameMessageNormal)
                    self.ILocalizeQuitNoDialog()
                    KIYesNo.dialog.show()
                elif (bbID == kPlayerBookCBID):
                    if control.isChecked():
                        curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                        if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                            if (not WaitingForAnimation):
                                self.IShowYeeshaBook()
                            else:
                                control.setChecked(0)
                        else:
                            control.setChecked(0)
                elif (bbID == kJournalBookCBID):
                    if control.isChecked():
                        curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                        if (gKIHasJournal and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                            if (not WaitingForAnimation):
                                self.IShowJournalBook()
                            else:
                                control.setChecked(0)
                        else:
                            control.setChecked(0)
                elif (bbID == kBBCCRButtonID):
                    PtShowDialog('OptionsMenuGUI')
                else:
                    PtDebugPrint(("xBlackbar: Don't know this control  bbID=%d" % bbID), level=kDebugDumpLevel)
            elif (event == kInterestingEvent):
                plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                jrnbkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                try:
                    curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                    if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                        PtDebugPrint('xBlackbar: interesting - show playerbook', level=kDebugDumpLevel)
                        plybkCB.show()
                    else:
                        PtDebugPrint('xBlackbar: interesting - on ladder - hide playerbook', level=kDebugDumpLevel)
                        plybkCB.hide()
                    if (gKIHasJournal and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                        PtDebugPrint('xBlackbar: interesting - show journal book', level=kDebugDumpLevel)
                        jrnbkCB.show()
                    else:
                        PtDebugPrint('xBlackbar: interesting - on ladder - hide journal book', level=kDebugDumpLevel)
                        jrnbkCB.hide()
                except NameError:
                    if IsEntireYeeshaBookEnabled:
                        PtDebugPrint('xBlackbar: interesting - show playerbook', level=kDebugDumpLevel)
                        plybkCB.show()
                    else:
                        PtDebugPrint('xBlackbar: interesting - on ladder - hide playerbook', level=kDebugDumpLevel)
                        plybkCB.hide()
                    if gKIHasJournal:
                        PtDebugPrint('xBlackbar: interesting - show journal book', level=kDebugDumpLevel)
                        jrnbkCB.show()
                    else:
                        PtDebugPrint('xBlackbar: interesting - on ladder - hide journal book', level=kDebugDumpLevel)
                        jrnbkCB.hide()
        elif (id == KINanoBlackBar.id):
            PtDebugPrint(('nanoBlackbar::OnGUINotify id=%d, event=%d control=' % (id,
             event)), control, level=kDebugDumpLevel)
            if (event == kDialogLoaded):
                pass
            elif ((event == kAction) or (event == kValueChanged)):
                bbID = control.getTagID()
                if (bbID == kExitButtonID):
                    yesText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
                    yesText.setString(xLocalization.xKI.xLeaveGameMessageNano)
                    self.ILocalizeQuitNoDialog()
                    KIYesNo.dialog.show()
                elif (bbID == kBBCCRButtonID):
                    PtShowDialog('OptionsMenuGUI')
                else:
                    PtDebugPrint(("xnanoBlackbar: Don't know this control  bbID=%d" % bbID), level=kDebugDumpLevel)
        elif (id == KIMicroBlackbar.id):
            PtDebugPrint(('microBlackbar::OnGUINotify id=%d, event=%d control=' % (id,
             event)), control, level=kDebugDumpLevel)
            if (event == kDialogLoaded):
                rollBtn = ptGUIControlButton(KIMicroBlackbar.dialog.getControlFromTag(kRolloverLeftID))
                rollBtn.setNotifyOnInteresting(1)
                rollBtn = ptGUIControlButton(KIMicroBlackbar.dialog.getControlFromTag(kRolloverRightID))
                rollBtn.setNotifyOnInteresting(1)
            elif ((event == kAction) or (event == kValueChanged)):
                bbID = control.getTagID()
                if (bbID == kExitButtonID):
                    yesText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
                    yesText.setString(xLocalization.xKI.xLeaveGameMessageMicro)
                    self.ILocalizeQuitNoDialog()
                    KIYesNo.dialog.show()
                elif (bbID == kPlayerBookCBID):
                    if control.isChecked():
                        curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                        if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                            if (not WaitingForAnimation):
                                self.IShowYeeshaBook()
                            else:
                                control.setChecked(0)
                        else:
                            control.setChecked(0)
                elif (bbID == kJournalBookCBID):
                    if control.isChecked():
                        curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                        if (gKIHasJournal and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                            if (not WaitingForAnimation):
                                self.IShowJournalBook()
                            else:
                                control.setChecked(0)
                        else:
                            control.setChecked(0)
                elif (bbID == kBBCCRButtonID):
                    PtShowDialog('OptionsMenuGUI')
                else:
                    PtDebugPrint(("xmicroBlackbar: Don't know this control  bbID=%d" % bbID), level=kDebugDumpLevel)
            elif (event == kInterestingEvent):
                plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                jrnbkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                try:
                    curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                    if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                        PtDebugPrint('xmicroBlackbar: interesting - show playerbook', level=kDebugDumpLevel)
                        plybkCB.show()
                    else:
                        PtDebugPrint('xmicroBlackbar: interesting - on ladder - hide playerbook', level=kDebugDumpLevel)
                        plybkCB.hide()
                    if (gKIHasJournal and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                        PtDebugPrint('xmicroBlackbar: interesting - show journal book', level=kDebugDumpLevel)
                        jrnbkCB.show()
                    else:
                        PtDebugPrint('xmicroBlackbar: interesting - on ladder - hide journal book', level=kDebugDumpLevel)
                        jrnbkCB.hide()
                except NameError:
                    if IsEntireYeeshaBookEnabled:
                        PtDebugPrint('xmicroBlackbar: interesting - show playerbook', level=kDebugDumpLevel)
                        plybkCB.show()
                    else:
                        PtDebugPrint('xmicroBlackbar: interesting - on ladder - hide playerbook', level=kDebugDumpLevel)
                        plybkCB.hide()
                    if gKIHasJournal:
                        PtDebugPrint('xmicroBlackbar: interesting - show journal book', level=kDebugDumpLevel)
                        jrnbkCB.show()
                    else:
                        PtDebugPrint('xmicroBlackbar: interesting - on ladder - hide journal book', level=kDebugDumpLevel)
                        jrnbkCB.hide()
        elif (id == KIMini.id):
            if (event == kDialogLoaded):
                dragbar = ptGUIControlDragBar(KIMini.dialog.getControlFromTag(kminiDragBar))
                OriginalminiKICenter = dragbar.getObjectCenter()
                fore = control.getForeColor()
                OriginalForeAlpha = fore.getAlpha()
                sel = control.getSelectColor()
                OriginalSelectAlpha = sel.getAlpha()
                btnmt = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZDrip))
                btnmt.hide()
                btnmt = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZActive))
                btnmt.hide()
                btnmt = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZMarkerGameActive))
                btnmt.hide()
                btnmt = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZMarkerInRange))
                btnmt.hide()
                for mcbid in range(kminiMarkerIndicator01, (kminiMarkerIndicatorLast + 1)):
                    mcb = ptGUIControlProgress(KIMini.dialog.getControlFromTag(mcbid))
                    mcb.setValue(gMarkerColors['off'])

            elif (event == kShowHide):
                if control.isEnabled():
                    if MiniKIFirstTimeShow:
                        self.IDetermineFadeTime()
                        MiniKIFirstTimeShow = 0
                    self.IRefreshMiniKIMarkerDisplay()
                    self.IminiKIPSetStatics()
                    self.IminiKIPSetChanging()
                else:
                    self.IClearBBMini()
            elif ((event == kAction) or (event == kValueChanged)):
                ctrlID = control.getTagID()
                PtDebugPrint(('xKImini:: tagID=%d, event=%d control=' % (ctrlID,
                 event)), control, level=kDebugDumpLevel)
                if (ctrlID == kminiPutAwayID):
                    self.IminiPutAwayKI()
                elif (ctrlID == kminiToggleBtnID):
                    self.IminiToggleKISize()
                elif (ctrlID == kminiTakePicture):
                    self.IminiTakePicture()
                elif (ctrlID == kminiMuteAll):
                    audio = ptAudioControl()
                    if control.isChecked():
                        audio.muteAll()
                    else:
                        audio.unmuteAll()
        elif (id == BigKI.id):
            PtDebugPrint(('BigKI::OnGUINotify id=%d, event=%d control=' % (id,
             event)), control, level=kDebugDumpLevel)
            if (event == kDialogLoaded):
                BKInEditMode = 0
                KIOnAnim.animation.skipToTime(1.5)
                self.IBigKIFindNextImage()
            elif (event == kShowHide):
                if control.isEnabled():
                    self.IBigKIDisplayCurrentContentImage()
                    self.IKillFadeTimer()
                    self.IBigKIShowBigKI()
                else:
                    self.IBigKIPurgeDisplay()
                    self.IStartFadeTimer()
            elif ((event == kAction) or (event == kValueChanged)):
                bkID = control.getTagID()
                PtDebugPrint(('BigKI::OnGUINotify event=%d controlID=%d' % (event,
                 bkID)), level=kDebugDumpLevel)
                if (bkID == kBKPrevPictureButton):
                    self.IBigKIFindPrevImage()
                    self.IBigKIDisplayCurrentContentImage()
                elif (bkID == kBKNextPictureButton):
                    self.IBigKIFindNextImage()
                    self.IBigKIDisplayCurrentContentImage()
                elif (bkID == kBKDeletePictureButton):
                    self.IBigKIDeleteCurrentContentImage()
        elif (id == KIYesNo.id):
            if ((event == kAction) or (event == kValueChanged)):
                ynID = control.getTagID()
                if (YNWhatReason == kYNQuit):
                    if (ynID == kYesButtonID):
                        PtConsole('app.quit')
                    elif (ynID == kNoButtonID):
                        KIYesNo.dialog.hide()
                elif (YNWhatReason == kYNDelete):
                    YNWhatReason = kYNQuit
                    KIYesNo.dialog.hide()
                    if (ynID == kYesButtonID):
                        try:
                            os.remove(gCurrentImageFilename)
                            self.IBigKIFindNextImage()
                            self.IBigKIDisplayCurrentContentImage()
                        except:
                            filename = gCurrentImageFilename
                            if (filename[0] == '.'):
                                filename = filename[(filename.rfind('\\') + 1):]
                            PtDebugPrint((('xBigKI: failed to delete ' + filename) + ' (is it in use?)'))
                            yesBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
                            yesBtnText.setString(xLocalization.xKI.xYesNoOKbutton)
                            noButton = ptGUIControlButton(KIYesNo.dialog.getControlFromTag(kNoButtonID))
                            noButton.hide()
                            noBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
                            noBtnText.hide()
                            mainText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
                            mainText.setString(xLocalization.xKI.xCannotDeleteImage)
                            YNWhatReason = kYNKIFull
                            KIYesNo.dialog.show()
                    elif (ynID == kNoButtonID):
                        pass
                elif (YNWhatReason == kYNOutside):
                    YNWhatReason = kYNQuit
                    KIYesNo.dialog.hide()
                    if (type(YNOutsideSender) != type(None)):
                        note = ptNotify(self.key)
                        note.clearReceivers()
                        note.addReceiver(YNOutsideSender)
                        note.netPropagate(0)
                        note.netForce(0)
                        if (ynID == kYesButtonID):
                            note.setActivate(1)
                            note.addVarNumber('YesNo', 1)
                        elif (ynID == kNoButtonID):
                            note.setActivate(0)
                            note.addVarNumber('YesNo', 0)
                        note.send()
                    YNOutsideSender = None
                elif (YNWhatReason == kYNKIFull):
                    KIYesNo.dialog.hide()
                    noButton = ptGUIControlButton(KIYesNo.dialog.getControlFromTag(kNoButtonID))
                    noButton.show()
                    noBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
                    noBtnText.show()
                    yesBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
                    yesBtnText.setString(xLocalization.xKI.xYesNoYESbutton)
                    YNWhatReason = kYNQuit
                else:
                    YNWhatReason = kYNQuit
                    KIYesNo.dialog.hide()
                    YNOutsideSender = None
            elif (event == kExitMode):
                YNWhatReason = kYNQuit
                KIYesNo.dialog.hide()
                YNOutsideSender = None
        elif (id == NewItemAlert.id):
            if (event == kDialogLoaded):
                kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
                kialert.disable()
                kialert.hide()
                bookalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertBookAlert))
                bookalert.disable()
                bookalert.hide()
                journalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertJournalAlert))
                journalalert.disable()
                journalalert.hide()
                microjournalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertMicroJournalAlert))
                microjournalalert.disable()
                microjournalalert.hide()
            elif (event == kShowHide):
                if control.isEnabled():
                    self.IAlertStartTimer()



    def OnKIMsg(self, command, value):
        global IminiKIWasUp
        global TicksOnFull
        global IKIDisabled
        global FadeEnableFlag
        global YNOutsideSender
        global IKIHardDisabled
        global gShowGPSCheat
        global theKILevel
        global gGZMarkerInRangeRepy
        global IsYeeshaBookEnabled
        global gGZMarkerInRange
        global IsEntireYeeshaBookEnabled
        global YNWhatReason
        PtDebugPrint(('xKI: KIMsg: command = %d value =' % command), value, level=kDebugDumpLevel)
        if (command == kEnterChatMode):
            PtDebugPrint('xKI:kEnterChatMode - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kSetChatFadeDelay):
            #Drizzle
            #we're intercepting this since it has a parameter we can use for keybinding purposes.
            if drizzle.OnKIMsg(self, command, value):
                return;  #don't execute this if we have intercepted it.
            #/Drizzle
            TicksOnFull = value
        elif (command == kSetTextChatAdminMode):
            PtDebugPrint('xKI:kSetTextChatAdminMode - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kDisableKIandBB):
            PtDebugPrint('xKI: Disable KI', level=kDebugDumpLevel)
            IKIDisabled = 1
            IKIHardDisabled = 1
            if (theKILevel == kNanoKI):
                KINanoBlackBar.dialog.hide()
            elif (theKILevel == kMicroKI):
                KIMicroBlackbar.dialog.hide()
            else:
                KIBlackbar.dialog.hide()
                if KIMini.dialog.isEnabled():
                    IminiKIWasUp = 1
                    self.IminiKIHide()
                else:
                    IminiKIWasUp = 0
                if (not WaitingForAnimation):
                    BigKI.dialog.hide()
                    KIOnAnim.animation.skipToTime(1.5)
            if (YNWhatReason == kYNOutside):
                if (type(YNOutsideSender) != type(None)):
                    note = ptNotify(self.key)
                    note.clearReceivers()
                    note.addReceiver(YNOutsideSender)
                    note.netPropagate(0)
                    note.netForce(0)
                    note.setActivate(0)
                    note.addVarNumber('YesNo', 0)
                    note.send()
                YNOutsideSender = None
            if YeeshaBook:
                YeeshaBook.hide()
            if JournalBook:
                JournalBook.hide()
            PtToggleAvatarClickability(true)
            plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
            plybkCB.setChecked(0)
            plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
            plybkCB.setChecked(0)
            YNWhatReason = kYNQuit
            KIYesNo.dialog.hide()
        elif (command == kEnableKIandBB):
            PtDebugPrint('xKI: Enable KI', level=kDebugDumpLevel)
            IKIDisabled = 0
            IKIHardDisabled = 0
            if (theKILevel == kNanoKI):
                KINanoBlackBar.dialog.show()
            elif (theKILevel == kMicroKI):
                KIMicroBlackbar.dialog.show()
            else:
                KIBlackbar.dialog.show()
                if IminiKIWasUp:
                    self.IClearBBMini(0)
                    self.IminiKIShow()
        elif (command == kTempDisableKIandBB):
            PtDebugPrint('xKI: TEMP Disable KI', level=kDebugDumpLevel)
            IKIDisabled = 1
            if (theKILevel == kNanoKI):
                KINanoBlackBar.dialog.hide()
            elif (theKILevel == kMicroKI):
                KIMicroBlackbar.dialog.hide()
            else:
                KIBlackbar.dialog.hide()
            if YeeshaBook:
                YeeshaBook.hide()
            if JournalBook:
                JournalBook.hide()
            PtToggleAvatarClickability(true)
            plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
            plybkCB.setChecked(0)
            plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
            plybkCB.setChecked(0)
        elif (command == kTempEnableKIandBB):
            PtDebugPrint('xKI: TEMP Enable KI', level=kDebugDumpLevel)
            if (not IKIHardDisabled):
                IKIDisabled = 0
                if (theKILevel == kNanoKI):
                    KINanoBlackBar.dialog.showNoReset()
                elif (theKILevel == kMicroKI):
                    KIMicroBlackbar.dialog.showNoReset()
                else:
                    KIBlackbar.dialog.showNoReset()
        elif (command == kYesNoDialog):
            YNWhatReason = kYNOutside
            YNOutsideSender = value[1]
            yesText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
            yesText.setString(value[0])
            self.ILocalizeYesNoDialog()
            KIYesNo.dialog.show()
        elif (command == kAddPlayerDevice):
            PtDebugPrint('xKI:kAddPlayerDevice - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kRemovePlayerDevice):
            PtDebugPrint('xKI:kRemovePlayerDevice - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kUpgradeKILevel):
            if ((value >= kLowestKILevel) and (value <= kHighestKILevel)):
                if (value > theKILevel):
                    PtDebugPrint(('xKI: Upgrading from KIlevel %d to new KI level of %d' % (theKILevel,
                     value)), level=kWarningLevel)
                    self.IRemoveKILevel(theKILevel, upgrading=1)
                    theKILevel = value
                    self.IUpdateKILevelChronicle()
                    self.IWearKILevel(theKILevel)
                else:
                    PtDebugPrint(('xKI: Ignoring - trying to upgrading from KIlevel %d to new KI level of %d' % (theKILevel,
                     value)), level=kWarningLevel)
                    self.IMakeSureWeWereKILevel()
            else:
                PtDebugPrint(('xKI: Invalid KI level %d' % value), level=kErrorLevel)
        elif (command == kDowngradeKILevel):
            if (value == theKILevel):
                PtDebugPrint(('xKI: Remove KI level of %d' % value), level=kWarningLevel)
                if (value == kMicroKI):
                    self.IRemoveKILevel(kMicroKI)
                    theKILevel = kNanoKI
                    self.IUpdateKILevelChronicle()
                    self.IWearKILevel(theKILevel)
                elif (value == kNormalKI):
                    self.IRemoveKILevel(kNormalKI)
                    theKILevel = kMicroKI
                    self.IUpdateKILevelChronicle()
                    self.IWearKILevel(theKILevel)
                else:
                    PtDebugPrint(("xKI: Ignoring - can't remove to any lower than %d" % value), level=kWarningLevel)
            else:
                PtDebugPrint(('xKI: Ignoring - trying to remove KILevel %d, but currently at %d' % (value,
                 theKILevel)), level=kWarningLevel)
        elif (command == kAddJournalBook):
            if (theKILevel >= kMicroKI):
                self.IAddJournalBook()
                self.IUpdateJournalBookChronicle()
        elif (command == kRemoveJournalBook):
            if (theKILevel >= kMicroKI):
                self.IRemoveJournalBook()
                self.IUpdateJournalBookChronicle()
        elif (command == kRateIt):
            PtDebugPrint('xKI:kRateIt - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kSetPrivateChatChannel):
            PtDebugPrint('xKI:kSetPrivateChatChannel - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kUnsetPrivateChatChannel):
            PtDebugPrint('xKI:kUnsetPrivateChatChannel - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kStartBookAlert):
            self.IAlertBookStart()
        elif (command == kStartJournalAlert):
            self.IAlertJournalStart()
        elif (command == kMiniBigKIToggle):
            self.IminiToggleKISize()
        elif (command == kKIPutAway):
            self.IminiPutAwayKI()
        elif (command == kChatAreaPageUp):
            PtDebugPrint('xKI:kChatAreaPageUp - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kChatAreaPageDown):
            PtDebugPrint('xKI:kChatAreaPageDown - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kChatAreaGoToBegin):
            PtDebugPrint('xKI:kChatAreaGoToBegin - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kChatAreaGoToEnd):
            PtDebugPrint('xKI:kChatAreaGoToEnd - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKITakePicture):
            self.IminiTakePicture()
        elif (command == kKICreateJournalNote):
            PtDebugPrint('xKI:kKICreateJournalNote - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKIToggleFade):
            if self.IsChatFaded():
                self.IKillFadeTimer()
                self.IStartFadeTimer()
            else:
                self.IFadeCompletely()
        elif (command == kKIToggleFadeEnable):
            self.IKillFadeTimer()
            if FadeEnableFlag:
                FadeEnableFlag = 0
            else:
                FadeEnableFlag = 1
            self.IStartFadeTimer()
        elif (command == kKIChatStatusMsg):
            PtDebugPrint('xKI:kKIChatStatusMsg - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKILocalChatStatusMsg):
            PtDebugPrint('xKI:kKILocalChatStatusMsg - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKILocalChatErrorMsg):
            PtDebugPrint('xKI:kKILocalChatErrorMsg - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKIUpSizeFont):
            PtDebugPrint('xKI:kKIUpSizeFont - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKIDownSizeFont):
            PtDebugPrint('xKI:kKIDownSizeFont - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKIOpenYeehsaBook):
            nm = ptNetLinkingMgr()
            if ((theKILevel >= kMicroKI) and ((not IKIDisabled) and ((not WaitingForAnimation) and nm.isEnabled()))):
                curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                if (IsEntireYeeshaBookEnabled and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                    self.IShowYeeshaBook()
                    if (theKILevel == kMicroKI):
                        plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                    else:
                        plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                    plybkCB.setChecked(1)
        elif (command == kKIOpenJournalBook):
            if ((theKILevel >= kMicroKI) and ((not IKIDisabled) and ((not WaitingForAnimation) and gKIHasJournal))):
                curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
                if (gKIHasJournal and ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit)))):
                    self.IShowJournalBook()
                    if (theKILevel == kMicroKI):
                        plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                    else:
                        plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                    plybkCB.setChecked(1)
        elif (command == kKIOpenKI):
            if (not WaitingForAnimation):
                if (not KIMini.dialog.isEnabled()):
                    self.IminiPutAwayKI()
                elif (not BigKI.dialog.isEnabled()):
                    if self.IsChatFaded():
                        self.IKillFadeTimer()
                        self.IStartFadeTimer()
                    else:
                        self.IminiToggleKISize()
                else:
                    self.IminiPutAwayKI()
        elif (command == kKIShowCCRHelp):
            if YeeshaBook:
                YeeshaBook.hide()
            if (theKILevel == kMicroKI):
                plybkCB = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                plybkCB.setChecked(0)
            elif (theKILevel > kMicroKI):
                plybkCB = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kPlayerBookCBID))
                plybkCB.setChecked(0)
            if ((not WaitingForAnimation) and (not IKIDisabled)):
                PtShowDialog('OptionsMenuGUI')
        elif (command == kKICreateMarker):
            PtDebugPrint('xKI:kKICreateMarker - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKICreateMarkerFolder):
            PtDebugPrint('xKI:kKICreateMarkerFolder - Unsupported KIMsg command in Expansion Pack 1', level=kErrorLevel)
        elif (command == kKIPhasedAllOn):
            gShowGPSCheat = 1
        elif (command == kKIPhasedAllOff):
            gShowGPSCheat = 0
        elif ((command == kKIOKDialog) or (command == kKIOKDialogNoQuit)):
            reasonField = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
            try:
                localized = xLocalization.xKI.xOKDialogDict[value]
            except KeyError:
                localized = ('UNTRANSLATED: ' + value)
            reasonField.setString(localized)
            noButton = ptGUIControlButton(KIYesNo.dialog.getControlFromTag(kNoButtonID))
            noButton.hide()
            noBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
            noBtnText.hide()
            yesBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
            yesBtnText.setString(xLocalization.xKI.xYesNoOKbutton)
            YNWhatReason = kYNQuit
            if (command == kKIOKDialogNoQuit):
                YNWhatReason = kYNNoReason
            KIYesNo.dialog.show()
        elif (command == kDisableYeeshaBook):
            IsYeeshaBookEnabled = 0
        elif (command == kEnableYeeshaBook):
            IsYeeshaBookEnabled = 1
        elif (command == kQuitDialog):
            yesText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
            yesText.setString(xLocalization.xKI.xLeaveGameMessageNormal)
            self.ILocalizeQuitNoDialog()
            KIYesNo.dialog.show()
        elif (command == kDisableEntireYeeshaBook):
            IsEntireYeeshaBookEnabled = 0
        elif (command == kEnableEntireYeeshaBook):
            IsEntireYeeshaBookEnabled = 1
        elif (command == kGZUpdated):
            if (value != 0):
                vault = ptVault()
                entry = vault.findChronicleEntry(kChronicleGZMarkersAquired)
                if (type(entry) != type(None)):
                    markers = entry.chronicleGetValue()
                    if (len(markers) < value):
                        markers += (kGZMarkerAvailable * (value - len(markers)))
                        entry.chronicleSetValue(markers)
                        entry.save()
                else:
                    markers = (kGZMarkerAvailable * value)
                    vault.addChronicleEntry(kChronicleGZMarkersAquired, kChronicleGZMarkersAquiredType, markers)
            self.IDetermineKILevel()
            self.IDetermineGZ()
            self.IRefreshMiniKIMarkerDisplay()
        elif (command == kGZFlashUpdate):
            self.IDetermineKILevel()
            self.IGZFlashUpdate(value)
            self.IRefreshMiniKIMarkerDisplay()
        elif (command == kGZInRange):
            gGZMarkerInRange = value[0]
            gGZMarkerInRangeRepy = value[1]
            self.IRefreshMiniKIMarkerDisplay()
            if (not KIMini.dialog.isEnabled()):
                NewItemAlert.dialog.show()
                kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
                kialert.show()
        elif (command == kGZOutRange):
            gGZMarkerInRange = 0
            gGZMarkerInRangeRepy = None
            self.IRefreshMiniKIMarkerDisplay()
            NewItemAlert.dialog.hide()
            kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
            kialert.hide()
        elif (command == kUpgradeKIMarkerLevel):
            self.IUpgradeKIMarkerLevel(value)
            self.IRefreshMiniKIMarkerDisplay()
        elif (command == kKIShowMiniKI):
            if (theKILevel >= kNormalKI):
                self.IClearBBMini(0)



    def IRemoveKILevel(self, level, upgrading = 0):
        if (level == kNanoKI):
            KINanoBlackBar.dialog.hide()
        elif (level == kMicroKI):
            if (not upgrading):
                avatar = PtGetLocalAvatar()
                gender = avatar.avatar.getAvatarClothingGroup()
                if (gender > kFemaleClothingGroup):
                    gender = kMaleClothingGroup
                avatar.netForce(1)
                if (gender == kFemaleClothingGroup):
                    avatar.avatar.removeClothingItem('FAccPlayerBook')
                else:
                    avatar.avatar.removeClothingItem('MAccPlayerBook')
                avatar.avatar.saveClothing()
                self.IRemoveJournalBook()
                self.IUpdateJournalBookChronicle()
            KIMicroBlackbar.dialog.hide()
        elif (level == kNormalKI):
            avatar = PtGetLocalAvatar()
            gender = avatar.avatar.getAvatarClothingGroup()
            if (gender > kFemaleClothingGroup):
                gender = kMaleClothingGroup
            avatar.netForce(1)
            if (gender == kFemaleClothingGroup):
                avatar.avatar.removeClothingItem('FAccKI')
            else:
                avatar.avatar.removeClothingItem('MAccKI')
            avatar.avatar.saveClothing()
            KIBlackbar.dialog.hide()
            self.IminiKIHide()
            BigKI.dialog.hide()
            KIOnAnim.animation.skipToTime(1.5)



    def IWearKILevel(self, level):
        if (level == kNanoKI):
            KINanoBlackBar.dialog.show()
        elif (level == kMicroKI):
            avatar = PtGetLocalAvatar()
            gender = avatar.avatar.getAvatarClothingGroup()
            if (gender > kFemaleClothingGroup):
                gender = kMaleClothingGroup
            avatar.netForce(1)
            if (gender == kFemaleClothingGroup):
                avatar.avatar.wearClothingItem('FAccPlayerBook')
            else:
                avatar.avatar.wearClothingItem('MAccPlayerBook')
            avatar.avatar.saveClothing()
            KIMicroBlackbar.dialog.show()
            self.IClearBBMini()
        elif (level == kNormalKI):
            avatar = PtGetLocalAvatar()
            gender = avatar.avatar.getAvatarClothingGroup()
            if (gender > kFemaleClothingGroup):
                gender = kMaleClothingGroup
            avatar.netForce(1)
            if (gender == kFemaleClothingGroup):
                avatar.avatar.wearClothingItem('FAccKI')
            else:
                avatar.avatar.wearClothingItem('MAccKI')
            avatar.avatar.saveClothing()
            KIBlackbar.dialog.show()
            self.IClearBBMini()
            if (not gKIHasJournal):
                PtDebugPrint("xKI: You don't have the journal, so removing it from the BB")
                chkbox = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
                chkbox.hide()
                chkbox.disable()
            KIOnAnim.animation.skipToTime(1.5)
            self.IAlertKIStart()



    def IMakeSureWeWereKILevel(self):
        if (theKILevel == kNanoKI):
            pass
        elif (theKILevel == kMicroKI):
            try:
                avatar = PtGetLocalAvatar()
                gender = avatar.avatar.getAvatarClothingGroup()
                if (gender > kFemaleClothingGroup):
                    gender = kMaleClothingGroup
                avatar.netForce(1)
                if (gender == kFemaleClothingGroup):
                    avatar.avatar.wearClothingItem('FAccPlayerBook')
                else:
                    avatar.avatar.wearClothingItem('MAccPlayerBook')
            except NameError:
                pass
        elif (theKILevel == kNormalKI):
            try:
                avatar = PtGetLocalAvatar()
                gender = avatar.avatar.getAvatarClothingGroup()
                if (gender > kFemaleClothingGroup):
                    gender = kMaleClothingGroup
                avatar.netForce(1)
                if (gender == kFemaleClothingGroup):
                    avatar.avatar.wearClothingItem('FAccPlayerBook')
                    avatar.avatar.wearClothingItem('FAccKI')
                else:
                    avatar.avatar.wearClothingItem('MAccPlayerBook')
                    avatar.avatar.wearClothingItem('MAccKI')
            except NameError:
                pass



    def IAddJournalBook(self):
        global gKIHasJournal
        if ((theKILevel >= kMicroKI) and (not gKIHasJournal)):
            gKIHasJournal = 1
            if (theKILevel == kMicroKI):
                chkbox = ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID))
            else:
                chkbox = ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID))
            chkbox.show()
            chkbox.enable()
            chkbox.setChecked(0)
            self.IAlertJournalStart()



    def IRemoveJournalBook(self):
        global gKIHasJournal
        gKIHasJournal = 0
        ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID)).hide()
        ptGUIControlCheckBox(KIMicroBlackbar.dialog.getControlFromTag(kJournalBookCBID)).disable()
        ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID)).hide()
        ptGUIControlCheckBox(KIBlackbar.dialog.getControlFromTag(kJournalBookCBID)).disable()



    def OnTimer(self, id):
        global CurrentFadeTick
        global FadeMode
        if (id == kFadeTimer):
            if (FadeMode == kFadeFullDisp):
                CurrentFadeTick -= 1
                if (CurrentFadeTick > 0):
                    PtAtTimeCallback(self.key, kFullTickTime, kFadeTimer)
                else:
                    FadeMode = kFadeDoingFade
                    CurrentFadeTick = TicksOnFade
                    PtAtTimeCallback(self.key, kFadeTickTime, kFadeTimer)
            elif (FadeMode == kFadeDoingFade):
                CurrentFadeTick -= 1
                if (CurrentFadeTick > 0):
                    mKIdialog = KIMini.dialog
                    mKIdialog.setForeColor(-1, -1, -1, ((OriginalForeAlpha * CurrentFadeTick) / TicksOnFade))
                    mKIdialog.setSelectColor(-1, -1, -1, ((OriginalSelectAlpha * CurrentFadeTick) / TicksOnFade))
                    mKIdialog.refreshAllControls()
                    PtAtTimeCallback(self.key, kFadeTickTime, kFadeTimer)
                else:
                    self.IFadeCompletely()
            elif (FadeMode == kFadeStopping):
                FadeMode = kFadeNotActive
        elif (id == kBKITODCheck):
            if KIMini.dialog.isEnabled():
                self.IminiKIPSetChanging()
        elif (id == kMarkerGameTimer):
            if (type(CurrentPlayingMarkerGame) != type(None)):
                CurrentPlayingMarkerGame.updateGameTime()
                PtAtTimeCallback(self.key, 1, kMarkerGameTimer)
        elif (id == kAlertHideTimer):
            self.IAlertStop()
        elif (id == kTakeSnapShot):
            PtForceCursorHidden()
            PtDebugPrint('xKI: *-*-*- Start screen capture -*-*-*-*', level=kDebugDumpLevel)
            PtStartScreenCapture(self.key)
        #Drizzle
        drizzle.OnTimer(self,id);
        #/Drizzle



    def OnScreenCaptureDone(self, image):
        global WeAreTakingAPicture
        global LastminiKICenter
        PtDebugPrint('xKI: #-#-#- caputre screen received -#-#-#-#', level=kDebugDumpLevel)
        self.IBigKICreateJournalImage(image)
        PtForceCursorShown()
        BigKI.dialog.show()
        if (type(LastminiKICenter) == type(None)):
            if (type(OriginalminiKICenter) != type(None)):
                dragbar = ptGUIControlDragBar(KIMini.dialog.getControlFromTag(kminiDragBar))
                LastminiKICenter = dragbar.getObjectCenter()
                dragbar.setObjectCenter(OriginalminiKICenter)
                dragbar.anchor()
        self.IminiKIShow()
        WeAreTakingAPicture = 0



    def OnMarkerMsg(self, msgType, gameMasterID, tupdata):
        global MarkerGameState
        global gMarkerGottenColor
        global gMarkerGottenNumber
        global CurrentPlayingMarkerGame
        global gMarkerToGetColor
        global gMarkerToGetNumber
        PtDebugPrint(('xKI: MarkerMsg type=%d, gamemaster=%d' % (msgType,
         gameMasterID)), tupdata, level=kDebugDumpLevel)
        if (msgType == PtMarkerMsgType.kGameCreate):
            if (theKILevel >= kNormalKI):
                if (gameMasterID != PtGetLocalPlayer().getPlayerID()):
                    PtDebugPrint("xKI:OnMarkerMsg - other people's markerTag games are unsupported in Expansion Pack 1", level=kErrorLevel)
                else:
                    MarkerGameState = kMGGameCreation
                    CurrentPlayingMarkerGame = MarkerGame(gameMasterID)
                    CurrentPlayingMarkerGame.setGame(tupdata[0], tupdata[1], tupdata[2])
        elif (msgType == PtMarkerMsgType.kGameJoin):
            pass
        elif (msgType == PtMarkerMsgType.kGameAddPlayer):
            if (type(CurrentPlayingMarkerGame) != type(None)):
                if (CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeQuest):
                    CurrentPlayingMarkerGame.addPlayerToTeam(tupdata[0], tupdata[1])
                else:
                    PtDebugPrint('xKI:OnMarkerMsg - adding other people to markerTag game is unsupported in Expansion Pack 1', level=kErrorLevel)
            else:
                PtDebugPrint('xKI:OnMarkerMsg - adding invited other people to markerTag games is unsupported in Expansion Pack 1', level=kErrorLevel)
        elif (msgType == PtMarkerMsgType.kGameStart):
            if (type(CurrentPlayingMarkerGame) != type(None)):
                MarkerGameState = kMGGameOn
                gMarkerToGetColor = 'yellowlt'
                gMarkerGottenColor = 'yellow'
                gMarkerToGetNumber = CurrentPlayingMarkerGame.numberMarkers
                gMarkerGottenNumber = (CurrentPlayingMarkerGame.numberMarkers - CurrentPlayingMarkerGame.markersRemaining)
                self.IRefreshMiniKIMarkerDisplay()
                if (CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeQuest):
                    workingMF = ptMarkerMgr().getWorkingMarkerFolder()
                    if (type(workingMF) != type(None)):
                        CurrentPlayingMarkerGame.resetScores()
                        markerRefs = workingMF.getChildNodeRefList()
                        for markerRef in markerRefs:
                            if markerRef.beenSeen():
                                mplayer = None
                                if (len(CurrentPlayingMarkerGame.greenTeamPlayers) > 0):
                                    mplayer = CurrentPlayingMarkerGame.greenTeamPlayers[0]
                                elif (len(CurrentPlayingMarkerGame.redTeamPlayers) > 0):
                                    mplayer = CurrentPlayingMarkerGame.redTeamPlayers[0]
                                if mplayer:
                                    mplayer.score += 1
                                    mplayer.updateScore()
                                CurrentPlayingMarkerGame.updateScores()
                                gMarkerToGetNumber = CurrentPlayingMarkerGame.numberMarkers
                                gMarkerGottenNumber = (CurrentPlayingMarkerGame.numberMarkers - CurrentPlayingMarkerGame.markersRemaining)

                    else:
                        PtDebugPrint('xKI:OnMarkerMsg - #### no working folder', level=kErrorLevel)
                else:
                    PtDebugPrint('xKI:OnMarkerMsg -  GameStart - other types of games are unsupported in Expansion Pack 1', level=kErrorLevel)
        elif (msgType == PtMarkerMsgType.kGameEnd):
            if (type(CurrentPlayingMarkerGame) != type(None)):
                if (MarkerGameState == kMGGameOn):
                    if (CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeQuest):
                        pass
                    else:
                        PtDebugPrint('xKI:OnMarkerMsg -  GameEnd - other types of games are unsupported in Expansion Pack 1', level=kErrorLevel)
                gMarkerToGetColor = 'off'
                gMarkerGottenColor = 'off'
                gMarkerToGetNumber = 0
                gMarkerGottenNumber = 0
                self.IRefreshMiniKIMarkerDisplay()
                MarkerGameState = kMGNotActive
                CurrentPlayingMarkerGame = None
                self.IResetWorkingMarkerFolder()
        elif (msgType == PtMarkerMsgType.kGamePoint):
            if (type(CurrentPlayingMarkerGame) != type(None)):
                scorer = '?unknown?'
                for mplayer in (CurrentPlayingMarkerGame.greenTeamPlayers + CurrentPlayingMarkerGame.redTeamPlayers):
                    if (mplayer.player.getPlayerID() == tupdata[0]):
                        if ((CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeCapture) or (CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeQuest)):
                            mplayer.score += 1
                            mplayer.updateScore()
                        scorer = mplayer.player.getPlayerName()

                CurrentPlayingMarkerGame.updateScores()
                gMarkerToGetNumber = CurrentPlayingMarkerGame.numberMarkers
                gMarkerGottenNumber = (CurrentPlayingMarkerGame.numberMarkers - CurrentPlayingMarkerGame.markersRemaining)
                self.IRefreshMiniKIMarkerDisplay()
                if (CurrentPlayingMarkerGame.gameType == PtMarkerMsgGameType.kGameTypeQuest):
                    pass
        else:
            PtDebugPrint('xKI:OnMarkerMsg -  unsupported MarkerGame message type for Expansion Pack 1', level=kErrorLevel)



    def IDetermineKILevel(self):
        global gKIHasJournal
        global theKILevel
        global gKIMarkerLevel
        theKILevel = kNanoKI
        vault = ptVault()
        entry = vault.findChronicleEntry(kChronicleKILevel)
        if (type(entry) == type(None)):
            vault.addChronicleEntry(kChronicleKILevel, kChronicleKILevelType, ('%d' % theKILevel))
        else:
            oldLevel = string.atoi(entry.chronicleGetValue())
            if ((oldLevel >= kLowestKILevel) and (oldLevel <= kHighestKILevel)):
                theKILevel = oldLevel
        PtDebugPrint(('xKI: the KI level is %d' % theKILevel), level=kWarningLevel)
        gKIMarkerLevel = 0
        entry = vault.findChronicleEntry(kChronicleKIMarkerLevel)
        if (type(entry) == type(None)):
            vault.addChronicleEntry(kChronicleKIMarkerLevel, kChronicleKIMarkerLevelType, ('%d' % gKIMarkerLevel))
        else:
            gKIMarkerLevel = string.atoi(entry.chronicleGetValue())
        PtDebugPrint(('xKI: the KIMarker level is %d' % gKIMarkerLevel), level=kWarningLevel)
        gKIHasJournal = 0
        entry = vault.findChronicleEntry(kChronicleHasJournal)
        if (type(entry) == type(None)):
            vault.addChronicleEntry(kChronicleHasJournal, kChronicleHasJournalType, ('%d' % gKIHasJournal))
        else:
            gKIHasJournal = string.atoi(entry.chronicleGetValue())
        PtDebugPrint(('xKI: the hasJournal value is %d' % gKIHasJournal), level=kWarningLevel)



    def IUpgradeKIMarkerLevel(self, newLevel):
        global gKIMarkerLevel
        PtDebugPrint(('xKI: KIMarker going from %d to %d' % (gKIMarkerLevel,
         newLevel)), level=kWarningLevel)
        if (theKILevel > kMicroKI):
            if (newLevel > gKIMarkerLevel):
                gKIMarkerLevel = newLevel
                vault = ptVault()
                entry = vault.findChronicleEntry(kChronicleKIMarkerLevel)
                if (type(entry) == type(None)):
                    PtDebugPrint(('xKI: KIMarker level not found - set to %d' % gKIMarkerLevel), level=kWarningLevel)
                    vault.addChronicleEntry(kChronicleKIMarkerLevel, kChronicleKIMarkerLevelType, ('%d' % gKIMarkerLevel))
                else:
                    PtDebugPrint(('xKI: KIMarker upgrading existing level to %d' % gKIMarkerLevel), level=kWarningLevel)
                    entry.chronicleSetValue(('%d' % gKIMarkerLevel))
                    entry.save()



    def IDetermineFadeTime(self):
        global TicksOnFull
        global FadeEnableFlag
        vault = ptVault()
        entry = vault.findChronicleEntry(kChronicleFadeTime)
        if (type(entry) == type(None)):
            vault.addChronicleEntry(kChronicleFadeTime, kChronicleFadeTimeType, ('%d' % TicksOnFull))
        else:
            TicksOnFull = string.atoi(entry.chronicleGetValue())
            if (TicksOnFull == kFadeTimeMax):
                FadeEnableFlag = 0
                self.IKillFadeTimer()
                PtDebugPrint('KIDeterineFadeTime: FadeTime disabled', level=kWarningLevel)
            else:
                FadeEnableFlag = 1
        PtDebugPrint(('xKI: the Saved Fade Time is %d' % TicksOnFull), level=kWarningLevel)



    def ISaveFadeTime(self):
        vault = ptVault()
        entry = vault.findChronicleEntry(kChronicleFadeTime)
        if (type(entry) != type(None)):
            entry.chronicleSetValue(('%d' % TicksOnFull))
            entry.save()
        else:
            vault.addChronicleEntry(kChronicleFadeTime, kChronicleFadeTimeType, ('%d' % TicksOnFull))
        PtDebugPrint(('xKI: Saving Fade Time of %d' % TicksOnFull), level=kWarningLevel)



    def IDetermineKIFlags(self):
        return 



    def ISaveKIFlags(self):
        return 



    def IDetermineGZ(self):
        global gMarkerToGetColor
        global gMarkerToGetNumber
        global gGZPlaying
        global gMarkerGottenColor
        global gMarkerGottenNumber
        (GZPlaying, MarkerGottenColor, MarkerToGetColor, MarkerGottenNumber, MarkerToGetNumber,) = PtDetermineGZ()
        if (GZPlaying == 0):
            if ((MarkerGameState == kMGNotActive) or (type(CurrentPlayingMarkerGame) == type(None))):
                pass
            else:
                return 
        gGZPlaying = GZPlaying
        gMarkerToGetColor = MarkerToGetColor
        gMarkerGottenColor = MarkerGottenColor
        gMarkerToGetNumber = MarkerToGetNumber
        gMarkerGottenNumber = MarkerGottenNumber



    def IGZFlashUpdate(self, gameString):
        global gMarkerToGetColor
        global gMarkerToGetNumber
        global gGZPlaying
        global gMarkerGottenColor
        global gMarkerGottenNumber
        if (gKIMarkerLevel > kKIMarkerNotUpgraded):
            if (gKIMarkerLevel < kKIMarkerNormalLevel):
                PtDebugPrint(('xKI:GZ FLASH - game string is %s' % gameString), level=kDebugDumpLevel)
                args = gameString.split()
                if (len(args) == 3):
                    try:
                        GZPlaying = string.atoi(args[0])
                        colors = args[1].split(':')
                        MarkerGottenColor = colors[0]
                        MarkerToGetColor = colors[1]
                        outof = args[2].split(':')
                        MarkerGottenNumber = string.atoi(outof[0])
                        MarkerToGetNumber = string.atoi(outof[1])
                        gGZPlaying = GZPlaying
                        gMarkerGottenColor = MarkerGottenColor
                        gMarkerToGetColor = MarkerToGetColor
                        gMarkerGottenNumber = MarkerGottenNumber
                        gMarkerToGetNumber = MarkerToGetNumber
                        return 
                    except ValueError:
                        PtDebugPrint('xKI:GZ FLASH - error trying to read GZGames Chronicle', level=kErrorLevel)
                else:
                    PtDebugPrint('xKI:GZ FLASH - error GZGames string formation error', level=kErrorLevel)



    def IUpdateKILevelChronicle(self):
        vault = ptVault()
        entry = vault.findChronicleEntry(kChronicleKILevel)
        if (type(entry) != type(None)):
            entry.chronicleSetValue(('%d' % theKILevel))
            entry.save()
        else:
            vault.addChronicleEntry(kChronicleKILevel, kChronicleKILevelType, ('%d' % theKILevel))



    def IUpdateJournalBookChronicle(self):
        vault = ptVault()
        entry = vault.findChronicleEntry(kChronicleHasJournal)
        if (type(entry) != type(None)):
            entry.chronicleSetValue(('%d' % gKIHasJournal))
            entry.save()
        else:
            vault.addChronicleEntry(kChronicleHasJournal, kChronicleHasJournalType, ('%d' % gKIHasJournal))



    def IGetAgeFileName(self, ageInfo = None):
        if (type(ageInfo) == type(None)):
            ageInfo = PtGetAgeInfo()
        if (type(ageInfo) != type(None)):
            return ageInfo.getAgeFilename()
        else:
            return '?UNKNOWN?'



    def IGetAgeInstanceName(self, ageInfo = None):
        if (type(ageInfo) == type(None)):
            ageInfo = PtGetAgeInfo()
        if (type(ageInfo) != type(None)):
            if (ageInfo.getAgeInstanceName() == "D'ni-Rudenna"):
                sdl = xPsnlVaultSDL()
                if ((sdl['TeledahnPoleState'][0] > 5) or ((sdl['KadishPoleState'][0] > 5) or ((sdl['GardenPoleState'][0] > 5) or (sdl['GarrisonPoleState'][0] > 5)))):
                    pass
                else:
                    return '???'
            if (ageInfo.getAgeInstanceName() == "Ae'gura"):
                return "D'ni-Ae'gura"
            return self.IFilterAgeName(xLocalization.xGlobal.LocalizeAgeName(ageInfo.getAgeInstanceName()))
        else:
            return '?UNKNOWN?'



    def IGetAgeDisplayName(self, ageInfo = None):
        if (type(ageInfo) == type(None)):
            ageInfo = PtGetAgeInfo()
        if (type(ageInfo) != type(None)):
            if (ageInfo.getAgeInstanceName() == "D'ni-Rudenna"):
                sdl = xPsnlVaultSDL()
                if ((sdl['TeledahnPoleState'][0] > 5) or ((sdl['KadishPoleState'][0] > 5) or ((sdl['GardenPoleState'][0] > 5) or (sdl['GarrisonPoleState'][0] > 5)))):
                    return "D'ni-Rudenna"
                else:
                    return '???'
            if (ageInfo.getAgeInstanceName() == "Ae'gura"):
                return "D'ni-Ae'gura"
            iname = ageInfo.getAgeInstanceName()
            if iname.startswith("D'ni"):
                return iname
            return self.IFilterAgeName(xLocalization.xGlobal.LocalizeAgeName(ageInfo.getDisplayName()))
        else:
            return '?UNKNOWN?'



    def IFilterAgeName(self, ageName):
        if (ageName.find('Garrison') != -1):
            ageName = ageName.replace('Garrison', 'Gahreesen')
        if (ageName.find('Personal') != -1):
            ageName = ageName.replace('Personal', 'Relto')
        if (ageName.find('Garden') != -1):
            ageName = ageName.replace('Garden', 'Eder Kemo')
        if (ageName == 'city'):
            ageName = "D'ni-Ae'gura"
        if ((ageName == 'GreatZero') or (ageName == 'Great Zero')):
            ageName = "D'ni-Rezeero"
        if (ageName == 'Gira'):
            ageName = 'Eder Gira'
        return ageName



    def IConvertAgeName(self, ageName):
        if (ageName == 'Cleft'):
            return "D'ni-Riltagamin"
        if (ageName == 'BahroCave'):
            sdl = xPsnlVaultSDL()
            if ((sdl['TeledahnPoleState'][0] > 5) or ((sdl['KadishPoleState'][0] > 5) or ((sdl['GardenPoleState'][0] > 5) or (sdl['GarrisonPoleState'][0] > 5)))):
                return "D'ni-Rudenna"
            else:
                return '???'
        if (ageName == 'BaronCityOffice'):
            return "D'ni-Ae'gura"
        if (ageName == 'city'):
            return "D'ni-Ae'gura"
        if (ageName == "Ae'gura"):
            return "D'ni-Ae'gura"
        if (ageName == 'Personal'):
            return 'Relto'
        if (ageName == 'Garden'):
            return 'Eder Kemo'
        if (ageName == 'Gira'):
            return 'Eder Gira'
        if (ageName == 'Garrison'):
            return 'Gahreesen'
        if (ageName == 'ErcanaCitySilo'):
            return "D'ni-Ashem'en"
        if (ageName == 'GreatZero'):
            return "D'ni-Rezeero"
        if (ageName == 'Shaft'):
            return "D'ni-Tiwah"
        return ageName



    def ICanTakePicture(self):
        return 1



    def IsFolderHidden(self, agefolder):
        if (agefolder.folderGetName() == 'Hidden'):
            return 1
        return 0



    def IResetWorkingMarkerFolder(self):
        global WorkingMarkerFolder
        MGmgr = ptMarkerMgr()
        MGmgr.hideMarkersLocal()
        WorkingMarkerFolder = None
        MGmgr.clearWorkingMarkerFolder()
        self.IRefreshMiniKIMarkerDisplay()



    def IShowYeeshaBook(self):
        global YeeshaBook
        global gCurBookIsYeesha
        if ((theKILevel >= kMicroKI) and ((not IKIDisabled) and (not WaitingForAnimation))):
            if (BigKI.dialog.isEnabled() or KIMini.dialog.isEnabled()):
                self.IminiPutAwayKI()
            startOpen = 0
            gCurBookIsYeesha = 1
            if IsYeeshaBookEnabled:
                YeeshaBDef = (xLinkingBookDefs.xYeeshaBookNoShare + self.IGetYeeshaPageDefs())
            else:
                YeeshaBDef = (xLinkingBookDefs.xYeeshaBookBroke + self.IGetYeeshaPageDefs())
            YeeshaBook = ptBook(YeeshaBDef, self.key)
            YeeshaBook.setSize(xLinkingBookDefs.YeeshaBookSizeWidth, xLinkingBookDefs.YeeshaBookSizeHeight)
            YeeshaBook.show(startOpen)



    def IGetYeeshaPageDefs(self):
        pagedef = ''
        vault = ptVault()
        if (type(vault) != type(None)):
            psnlSDL = vault.getPsnlAgeSDL()
            if psnlSDL:
                for (sdlvar, page,) in xLinkingBookDefs.xYeeshaPages:
                    FoundValue = psnlSDL.findVar(sdlvar)
                    PtDebugPrint(('xKI: The previous value of the SDL variable %s is %d' % (sdlvar,
                     FoundValue.getInt())), level=kDebugDumpLevel)
                    if (type(FoundValue) != type(None)):
                        state = (FoundValue.getInt() % 10)
                        if (state != 0):
                            active = 1
                            if ((state == 2) or (state == 3)):
                                active = 0
                            try:
                                pagedef += (page % active)
                            except LookupError:
                                pagedef += ('<pb><pb>Bogus page %s' % sdlvar)

            else:
                PtDebugPrint(('xKI: Error trying to access the Chronicle psnlSDL. psnlSDL = %s' % psnlSDL), level=kErrorLevel)
        else:
            PtDebugPrint("xKI: Error trying to access the Vault. Can't access YeeshaPageChanges chronicle.", level=kErrorLevel)
        return pagedef



    def IToggleYeeshaPageSDL(self, varname, on):
        vault = ptVault()
        if (type(vault) != type(None)):
            psnlSDL = vault.getPsnlAgeSDL()
            if psnlSDL:
                ypageSDL = psnlSDL.findVar(varname)
                if ypageSDL:
                    (size, state,) = divmod(ypageSDL.getInt(), 10)
                    value = None
                    if ((state == 1) and (not on)):
                        value = 3
                    elif ((state == 3) and on):
                        value = 1
                    elif ((state == 2) and on):
                        value = 4
                    elif ((state == 4) and (not on)):
                        value = 2
                    if (value != None):
                        PtDebugPrint(('KI:Book: setting %s to %d' % (varname,
                         value)), level=kDebugDumpLevel)
                        ypageSDL.setInt(((size * 10) + value))
                        vault.updatePsnlAgeSDL(psnlSDL)



    def IShowJournalBook(self):
        global JournalBook
        global gCurBookIsYeesha
        if ((theKILevel >= kMicroKI) and ((not IKIDisabled) and ((not WaitingForAnimation) and gKIHasJournal))):
            if (BigKI.dialog.isEnabled() or KIMini.dialog.isEnabled()):
                self.IminiPutAwayKI()
            startOpen = 0
            gCurBookIsYeesha = 0
            JournalBDef = xLocalization.xJournalBookDefs.xPlayerJournalSource
            JournalParser = JournalHTMLParser()
            try:
                JournalFile = file(MakeJournalFilename())
                JournalParser.feed(JournalFile.read())
                JournalBInitialText = JournalParser.bodyText
                JournalParser.close()
                JournalFile.close()
            except HTMLParseError, err:
                PtDebugPrint(('xKI: Malformed HTML file, ignoring anything past the error: ' + str(err)))
                JournalBInitialText = ((JournalParser.bodyText + '\n\nError in the HTML, anything past this point has been lost: ') + str(err))
                JournalFile.close()
            except:
                PtDebugPrint("xKI: Journal file doesn't exist, using default text")
                JournalBInitialText = (xLocalization.xJournalBookDefs.xPlayerJournalTitle % PtGetClientName())
            JournalBook = ptBook(JournalBDef, self.key)
            JournalBook.setGUI('bkEditableJournal')
            JournalBook.setSize(xLocalization.xJournalBookDefs.xPlayerJournalXScale, xLocalization.xJournalBookDefs.xPlayerJournalYScale)
            JournalBook.setEditableText(JournalBInitialText)
            JournalBook.show(startOpen)



    def IminiKIShow(self):
        KIMini.dialog.show()



    def IminiKIHide(self):
        KIMini.dialog.hide()



    def IStartFadeTimer(self):
        return 



    def IKillFadeTimer(self):
        return 



    def IFadeCompletely(self):
        return 



    def IsChatFaded(self):
        return 0



    def IClearBBMini(self, value = -1):
        if (theKILevel < kNormalKI):
            pass
        else:
            mmRG = ptGUIControlRadioGroup(KIBlackbar.dialog.getControlFromTag(kMiniMaximizeRGID))
            mmRG.setValue(value)



    def IRefreshMiniKIMarkerDisplay(self):
        print ('xKI:GZ: Refreshing MarkerDisplay  %d:%d' % (gMarkerGottenNumber,
         gMarkerToGetNumber))
        if (theKILevel > kMicroKI):
            for mcbid in range(kminiMarkerIndicator01, (kminiMarkerIndicatorLast + 1)):
                mcb = ptGUIControlProgress(KIMini.dialog.getControlFromTag(mcbid))
                markerNumber = ((mcbid - kminiMarkerIndicator01) + 1)
                try:
                    if ((not gKIMarkerLevel) or (markerNumber > gMarkerToGetNumber)):
                        mcb.setValue(gMarkerColors['off'])
                    elif ((markerNumber <= gMarkerToGetNumber) and (markerNumber > gMarkerGottenNumber)):
                        mcb.setValue(gMarkerColors[gMarkerToGetColor])
                    else:
                        mcb.setValue(gMarkerColors[gMarkerGottenColor])
                except LookupError:
                    PtDebugPrint("xKI:GZ - coundn't find color - defaulting to off", level=kErrorLevel)
                    mcb.setValue(gMarkerColors['off'])

            btnmtDrip = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZDrip))
            btnmtActive = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZActive))
            btnmtPlaying = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZMarkerGameActive))
            btnmtInRange = ptGUIControlButton(KIMini.dialog.getControlFromTag(kminiGZMarkerInRange))
            if gKIMarkerLevel:
                btnmtDrip.hide()
                if (gMarkerToGetNumber > gMarkerGottenNumber):
                    if gGZMarkerInRange:
                        btnmtInRange.show()
                        btnmtPlaying.hide()
                        btnmtActive.hide()
                    else:
                        btnmtInRange.hide()
                        btnmtPlaying.show()
                        btnmtActive.hide()
                else:
                    btnmtPlaying.hide()
                    btnmtInRange.hide()
                    btnmtActive.show()
            else:
                btnmtDrip.hide()
                btnmtActive.hide()
                btnmtPlaying.hide()
                btnmtInRange.hide()



    def IminiToggleKISize(self):
        global WaitingForAnimation
        global LastminiKICenter
        print 'toggle KI size'
        if ((theKILevel > kMicroKI) and (not IKIDisabled)):
            if (not WaitingForAnimation):
                toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
                if BigKI.dialog.isEnabled():
                    print 'toggle: bigKI up, hide it'
                    self.IBigKIHideBigKI()
                    KIBlackbar.dialog.show()
                    if (type(LastminiKICenter) != type(None)):
                        dragbar = ptGUIControlDragBar(KIMini.dialog.getControlFromTag(kminiDragBar))
                        dragbar.setObjectCenter(LastminiKICenter)
                        dragbar.unanchor()
                        LastminiKICenter = None
                    toggleCB.setChecked(0)
                elif (not KIMini.dialog.isEnabled()):
                    print 'nothing is up, bring up the miniKI'
                    self.IClearBBMini(0)
                else:
                    print 'miniKI up, bring up the bigKI'
                    WaitingForAnimation = 1
                    KIBlackbar.dialog.hide()
                    self.IminiKIHide()
                    BigKI.dialog.show()
                    if (type(OriginalminiKICenter) != type(None)):
                        dragbar = ptGUIControlDragBar(KIMini.dialog.getControlFromTag(kminiDragBar))
                        LastminiKICenter = dragbar.getObjectCenter()
                        PtDebugPrint(('xKI:distance to original = %f' % LastminiKICenter.distance(OriginalminiKICenter)), level=kDebugDumpLevel)
                        if (LastminiKICenter.distance(OriginalminiKICenter) < 0.027):
                            LastminiKICenter = OriginalminiKICenter
                        dragbar.setObjectCenter(OriginalminiKICenter)
                        dragbar.anchor()
                    self.IminiKIShow()
                    toggleCB.setChecked(1)
            else:
                print 'toggle: waiting for animation to finish'



    def IminiPutAwayKI(self):
        global ISawTheKIAtleastOnce
        global LastminiKICenter
        if ((theKILevel > kMicroKI) and (not IKIDisabled)):
            if KIMini.dialog.isEnabled():
                self.IminiKIHide()
                if (type(LastminiKICenter) != type(None)):
                    dragbar = ptGUIControlDragBar(KIMini.dialog.getControlFromTag(kminiDragBar))
                    dragbar.setObjectCenter(LastminiKICenter)
                    dragbar.unanchor()
                    LastminiKICenter = None
                if BigKI.dialog.isEnabled():
                    self.IBigKIHideBigKI()
                KIBlackbar.dialog.show()
                self.IClearBBMini(-1)
                toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
                toggleCB.setChecked(0)
                ISawTheKIAtleastOnce = 1
            else:
                self.IClearBBMini(0)



    def IminiTakePicture(self):
        global WeAreTakingAPicture
        global YNWhatReason
        if ((not WeAreTakingAPicture) and (not WaitingForAnimation)):
            if ((theKILevel > kMicroKI) and (not IKIDisabled)):
                if self.ICanTakePicture():
                    WeAreTakingAPicture = 1
                    KIBlackbar.dialog.hide()
                    self.IminiKIHide()
                    self.IBigKIHideMode()
                    BigKI.dialog.hide()
                    toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
                    toggleCB.setChecked(1)
                    PtAtTimeCallback(self.key, 0.25, kTakeSnapShot)
                else:
                    YNWhatReason = kYNKIFull
                    reasonField = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
                    reasonField.setString(xLocalization.xKI.xKIFullImagesError)
                    noButton = ptGUIControlButton(KIYesNo.dialog.getControlFromTag(kNoButtonID))
                    noButton.hide()
                    noBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
                    noBtnText.hide()
                    yesBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
                    yesBtnText.setString(xLocalization.xKI.xYesNoOKbutton)
                    KIYesNo.dialog.show()



    def IminiKIPSetStatics(self):
        return 



    def IminiKIPSetChanging(self):
        gps1 = ptGUIControlTextBox(KIMini.dialog.getControlFromTag(kBKIGPS1TextID))
        gps2 = ptGUIControlTextBox(KIMini.dialog.getControlFromTag(kBKIGPS2TextID))
        gps3 = ptGUIControlTextBox(KIMini.dialog.getControlFromTag(kBKIGPS3TextID))
        self.dnicoords.update()
        if (gShowGPSCheat != 1):
            if (gKIMarkerLevel == kKIMarkerNormalLevel):
                if (PtGetCGZGameState(kCGZToransGame) == kCGZMarkerUploaded):
                    gps1.setString(('%d' % self.dnicoords.getTorans()))
                else:
                    gps1.setString(('%d' % 0))
                if (PtGetCGZGameState(kCGZHSpansGame) == kCGZMarkerUploaded):
                    gps2.setString(('%d' % self.dnicoords.getHSpans()))
                else:
                    gps2.setString(('%d' % 0))
                if (PtGetCGZGameState(kCGZVSpansGame) == kCGZMarkerUploaded):
                    gps3.setString(('%d' % self.dnicoords.getVSpans()))
                else:
                    gps3.setString(('%d' % 0))
            else:
                gps1.setString(('%d' % 0))
                gps2.setString(('%d' % 0))
                gps3.setString(('%d' % 0))
        else:
            gps1.setString(('%d' % self.dnicoords.getTorans()))
            gps2.setString(('%d' % self.dnicoords.getHSpans()))
            gps3.setString(('%d' % self.dnicoords.getVSpans()))
        PtAtTimeCallback(self.key, 1, kBKITODCheck)



    def IBigKIShowBigKI(self):
        global WaitingForAnimation
        global IsPlayingLookingAtKIMode
        WaitingForAnimation = 1
        curBrainMode = PtGetLocalAvatar().avatar.getCurrentMode()
        toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
        toggleCB.disable()
        if (not PtFirstPerson()):
            if ((curBrainMode == PtBrainModes.kNonGeneric) or ((curBrainMode == PtBrainModes.kAFK) or (curBrainMode == PtBrainModes.kSit))):
                PtDebugPrint('xKI:ShowKI enter LookingAtKI mode', level=kDebugDumpLevel)
                PtAvatarEnterLookingAtKI()
                IsPlayingLookingAtKIMode = 1
        PtDisableMovementKeys()
        KIOnResp.run(self.key, netPropagate=0)



    def IBigKIHideBigKI(self):
        global WaitingForAnimation
        global IsPlayingLookingAtKIMode
        WaitingForAnimation = 1
        toggleCB = ptGUIControlCheckBox(KIMini.dialog.getControlFromTag(kminiToggleBtnID))
        toggleCB.disable()
        self.IBigKIHideMode()
        if IsPlayingLookingAtKIMode:
            PtDebugPrint('xKI:HideKI exit LookingAtKI mode', level=kDebugDumpLevel)
            PtAvatarExitLookingAtKI()
        IsPlayingLookingAtKIMode = 0
        PtEnableMovementKeys()
        KIOffResp.run(self.key, netPropagate=0)



    def IBigKIShowMode(self):
        return 



    def IBigKIHideMode(self):
        return 



    def IsContentMutable(self, noderef):
        return 1



    def IBigKIFindPrevImage(self):
        global gCurrentImageFilename
        files = glob.glob(gImageSearchPath1)
        files += glob.glob(gImageSearchPath2)
        files.sort(DirectoryCmp)
        if (len(files) == 0):
            gCurrentImageFilename = None
        elif (not gCurrentImageFilename):
            gCurrentImageFilename = files[-1]
        else:
            try:
                idx = files.index(gCurrentImageFilename)
                nextIdx = (idx - 1)
                if ((len(files) > nextIdx) and (nextIdx >= 0)):
                    gCurrentImageFilename = files[nextIdx]
                else:
                    PtDebugPrint(('xBigKI: trying to go prev before end of pictures (nextIdx=%d,pic=%s)' % (nextIdx,
                     gCurrentImageFilename)), level=kDebugDumpLevel)
            except ValueError:
                PtDebugPrint(('xBigKI: could not find %s in directory, going back to first file' % gCurrentImageFilename), level=kDebugDumpLevel)
                gCurrentImageFilename = files[0]



    def IBigKIFindNextImage(self):
        global gCurrentImageFilename
        files = glob.glob(gImageSearchPath1)
        files += glob.glob(gImageSearchPath2)
        files.sort(DirectoryCmp)
        if (len(files) == 0):
            gCurrentImageFilename = None
        elif (not gCurrentImageFilename):
            gCurrentImageFilename = files[0]
        else:
            try:
                idx = files.index(gCurrentImageFilename)
                nextIdx = (idx + 1)
                if (len(files) > nextIdx):
                    gCurrentImageFilename = files[nextIdx]
                else:
                    PtDebugPrint(('xBigKI: trying to go next past end of pictures, going to first file (pic=%s)' % gCurrentImageFilename), level=kDebugDumpLevel)
                    gCurrentImageFilename = files[0]
            except ValueError:
                PtDebugPrint(('xBigKI: could not find %s in directory, going back to first file' % gCurrentImageFilename), level=kDebugDumpLevel)
                gCurrentImageFilename = files[0]



    def ICanDeleteCurrentImage(self):
        if (not gCurrentImageFilename):
            PtDebugPrint('xBigKI: no filename, hiding delete button')
            return false
        filename = gCurrentImageFilename
        if (filename[0] == '.'):
            filename = filename[(filename.rfind('\\') + 1):]
        if (not (len(filename) == 15)):
            PtDebugPrint((((('xBigKI: filename ' + filename) + ' is not the right length, hiding delete button. (len = ') + str(len(filename))) + ')'))
        elif (filename.find('KIimage') == 0):
            ext = filename[11:]
            if (ext == '.jpg'):
                try:
                    number = int(filename[7:11])
                    return true
                except:
                    PtDebugPrint((((('xBigKI: filename ' + filename) + " doesn't end with four numbers. Hiding delete button. (number = ") + filename[7:11]) + ')'))
            else:
                PtDebugPrint((((('xBigKI: filename ' + filename) + ' has an incorrect extension. Hiding delete button. (ext = ') + ext) + ')'))
        else:
            PtDebugPrint((('xBigKI: filename ' + filename) + " doesn't start with KIimage. Hiding delete button."))
        return false



    def IBigKISetPrevNextButtons(self):
        global gCurrentImageFilename
        prevBtn = ptGUIControlButton(BigKI.dialog.getControlFromTag(kBKPrevPictureButton))
        nextBtn = ptGUIControlButton(BigKI.dialog.getControlFromTag(kBKNextPictureButton))
        deleteBtn = ptGUIControlButton(BigKI.dialog.getControlFromTag(kBKDeletePictureButton))
        files = glob.glob(gImageSearchPath1)
        files += glob.glob(gImageSearchPath2)
        files.sort(DirectoryCmp)
        if (len(files) == 0):
            gCurrentImageFilename = None
            prevBtn.hide()
            nextBtn.hide()
            deleteBtn.hide()
        else:
            try:
                if (not gCurrentImageFilename):
                    gCurrentImageFilename = files[0]
                idx = files.index(gCurrentImageFilename)
                if (idx > 0):
                    prevBtn.show()
                else:
                    prevBtn.hide()
                if (len(files) > (idx + 1)):
                    nextBtn.show()
                else:
                    nextBtn.hide()
                if self.ICanDeleteCurrentImage():
                    deleteBtn.show()
                else:
                    deleteBtn.hide()
            except ValueError:
                gCurrentImageFilename = files[0]
                idx = 0
                prevBtn.hide()
                if (len(files) > 1):
                    nextBtn.show()
                else:
                    nextBtn.hide()
                if self.ICanDeleteCurrentImage():
                    deleteBtn.show()
                else:
                    deleteBtn.hide()



    def IBigKIDisplayCurrentContentImage(self):
        self.IBigKISetPrevNextButtons()
        picImage = ptGUIControlDynamicText(BigKI.dialog.getControlFromTag(kBKIPICImage))
        picImage.hide()
        if (picImage.getNumMaps() > 0):
            dynmap = picImage.getMap(0)
            dynmap.clearToColor(ptColor(0.10000000000000001, 0.10000000000000001, 0.10000000000000001, 0.29999999999999999))
            if gCurrentImageFilename:
                theImage = PtLoadJPEGFromDisk(gCurrentImageFilename, 800, 600)
                if (type(theImage) != type(None)):
                    dynmap.drawImage(kBKIImageStartX, kBKIImageStartY, theImage, 0)
                else:
                    dynmap.fillRect(kBKIImageStartX, kBKIImageStartY, (kBKIImageStartX + 800), (kBKIImageStartY + 600), ptColor(0.80000000000000004, 0.20000000000000001, 0.20000000000000001, 0.10000000000000001))
                    PtDebugPrint(('xBigKI: could not find %s in directory, going back to first file' % gCurrentImageFilename), level=kErrorLevel)
            else:
                dynmap.fillRect(kBKIImageStartX, kBKIImageStartY, (kBKIImageStartX + 800), (kBKIImageStartY + 600), ptColor(0.20000000000000001, 0.20000000000000001, 0.20000000000000001, 0.10000000000000001))
            dynmap.flush()
            picImage.show()



    def IBigKIDeleteCurrentContentImage(self):
        global YNWhatReason
        if self.ICanDeleteCurrentImage():
            filename = gCurrentImageFilename
            if (filename[0] == '.'):
                filename = filename[(filename.rfind('\\') + 1):]
            yesBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesButtonTextID))
            yesBtnText.setString(xLocalization.xKI.xYesNoYESbutton)
            noBtnText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kNoButtonTextID))
            noBtnText.setString(xLocalization.xKI.xYesNoNoButton)
            mainText = ptGUIControlTextBox(KIYesNo.dialog.getControlFromTag(kYesNoTextID))
            mainText.setString((xLocalization.xKI.xDeletePictureAsk % filename))
            YNWhatReason = kYNDelete
            KIYesNo.dialog.show()



    def IBigKIPurgeDisplay(self):
        picImage = ptGUIControlDynamicText(BigKI.dialog.getControlFromTag(kBKIPICImage))
        if (picImage.getNumMaps() > 0):
            dynmap = picImage.getMap(0)
            dynmap.purgeImage()



    def IBigKICreateJournalImage(self, image):
        global gCurrentImageFilename
        global gLastImageFileNumber
        PtDebugPrint('xBigKI: create a picture element from ', image, level=kDebugDumpLevel)
        files = glob.glob(gImageFileSearch)
        print 'TakeShot dir = ',
        print files
        found = 0
        while (not found):
            gLastImageFileNumber += 1
            tryName = (((('.\\' + gImageDirectory) + '\\') + gImageFileNameTemplate) + ('%04d.jpg' % gLastImageFileNumber))
            print ('looking for %s' % tryName)
            if (tryName in files):
                pass
            else:
                found = 1

        image.saveAsJPEG(tryName, 100)
        gCurrentImageFilename = tryName
        self.IBigKIDisplayCurrentContentImage()



    def IBigKIEnterEditMode(self, whichfield):
        return 



    def IBigKISaveEdit(self):
        return 



    def IBigKICheckFocusChange(self):
        if BKInEditMode:
            if (BKEditField == kBKEditFieldPICTitle):
                editbox = ptGUIControlEditBox(KIPictureExpanded.dialog.getControlFromTag(BKEditFieldIDs[BKEditField][kBKEditIDeditbox]))
            else:
                editbox = None
            if (type(editbox) != type(None)):
                if editbox.isFocused():
                    return 
            self.IBigKISaveEdit()



    def IAlertKIStart(self):
        global AlertTimeToUse
        if (theKILevel >= kNormalKI):
            if (not AlertTimerActive):
                PtDebugPrint('xKI: show KI alert', level=kDebugDumpLevel)
                NewItemAlert.dialog.show()
            kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
            AlertTimeToUse = kAlertTimeDefault
            kialert.show()



    def IAlertBookStart(self, time = kAlertTimeDefault):
        global AlertTimeToUse
        if (not AlertTimerActive):
            PtDebugPrint('xKI: show Book alert', level=kDebugDumpLevel)
            NewItemAlert.dialog.show()
        bookalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertBookAlert))
        AlertTimeToUse = time
        bookalert.show()



    def IAlertJournalStart(self, time = kAlertTimeDefault):
        global AlertTimeToUse
        if (not AlertTimerActive):
            PtDebugPrint('xKI: show Journal alert', level=kDebugDumpLevel)
            NewItemAlert.dialog.show()
        if (theKILevel == kMicroKI):
            journalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertMicroJournalAlert))
        else:
            journalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertJournalAlert))
        AlertTimeToUse = time
        journalalert.show()



    def IAlertStop(self):
        global AlertTimerActive
        AlertTimerActive = 0
        NewItemAlert.dialog.hide()
        kialert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertKIAlert))
        kialert.hide()
        bookalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertBookAlert))
        bookalert.hide()
        journalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertJournalAlert))
        journalalert.hide()
        microjournalalert = ptGUIControlButton(NewItemAlert.dialog.getControlFromTag(kAlertMicroJournalAlert))
        microjournalalert.hide()



    def IAlertStartTimer(self):
        global AlertTimerActive
        if (not AlertTimerActive):
            AlertTimerActive = 1
            PtAtTimeCallback(self.key, AlertTimeToUse, kAlertHideTimer)



class MarkerPlayer:
    __module__ = __name__

    def __init__(self, playerID, joined = 0):
        self.ID = playerID
        dpl = PtGetPlayerListDistanceSorted()
        self.player = None
        self.isUs = 0
        self.team = PtMarkerMsgTeam.kNoTeam
        for plyr in dpl:
            if (plyr.getPlayerID() == playerID):
                self.player = ptPlayer(plyr.getPlayerName(), plyr.getPlayerID())
                break

        if (type(self.player) == type(None)):
            us = PtGetLocalPlayer()
            if (us.getPlayerID() == playerID):
                self.player = us
                self.isUs = 1
        if (type(self.player) == type(None)):
            self.player = ptPlayer(('?UNKNOWN?ID:[%d]' % self.playerID), self.playerID)
        self.isJoined = joined
        self.score = 0
        self.scoreText = ''



    def updateScore(self):
        if (type(self.player) != type(None)):
            if (MarkerGameState == kMGGameOn):
                self.scoreText = ('(%d)' % self.score)
            else:
                self.scoreText = ''



class MarkerGame:
    __module__ = __name__

    def __init__(self, masterID, name = None):
        self.masterID = masterID
        self.gameName = name
        dpl = PtGetPlayerListDistanceSorted()
        self.master = None
        self.time = 0
        self.gameTimerOn = 0
        self.timeLeftDPL = None
        self.gameType = PtMarkerMsgGameType.kGameTypeCapture
        self.numberMarkers = 0
        self.markersRemaining = 0
        self.markersRemainingDPL = None
        self.invitedPlayers = []
        self.greenTeamPlayers = []
        self.greenTeamScore = 0
        self.greenTeamDPL = None
        self.redTeamPlayers = []
        self.redTeamScore = 0
        self.redTeamDPL = None
        self.master = MarkerPlayer(masterID)
        if (type(self.master.player) == type(None)):
            self.master.player = ptPlayer(('?UNKNOWN?ID:[%d]' % self.masterID), self.masterID)
        if (type(self.gameName) == type(None)):
            self.gameName = ("%s's MarkerGame" % self.master.player.getPlayerName())



    def setGame(self, time, type, markers):
        self.setTime(time)
        self.gameType = type
        self.numberMarkers = markers
        self.markersRemaining = markers



    def updateScores(self):
        self.greenTeamScore = 0
        for gplyr in self.greenTeamPlayers:
            self.greenTeamScore += gplyr.score

        self.redTeamScore = 0
        for rplyr in self.redTeamPlayers:
            self.redTeamScore += rplyr.score

        self.markersRemaining = (self.numberMarkers - (self.greenTeamScore + self.redTeamScore))



    def resetScores(self):
        self.greenTeamScore = 0
        for gplyr in self.greenTeamPlayers:
            gplyr.score = 0

        self.redTeamScore = 0
        for rplyr in self.redTeamPlayers:
            rplyr.score = 0

        self.markersRemaining = self.numberMarkers



    def setTime(self, time):
        self.time = time
        self.gameTimerOn = 0



    def startTimer(self):
        self.gameTimerOn = 1
        self.endTime = (PtGetTime() + self.time)



    def timeLeft(self):
        if self.gameTimerOn:
            if (PtGetTime() > self.endTime):
                return 0
            return int((self.endTime - PtGetTime()))
        else:
            return self.time



    def addPlayerToTeam(self, playerID, teamType):
        if (teamType == PtMarkerMsgTeam.kGreenTeam):
            teamplayers = self.greenTeamPlayers
        else:
            teamplayers = self.redTeamPlayers
        found = 0
        for mplayer in teamplayers:
            if (mplayer.player.getPlayerID() == playerID):
                mplayer.isJoined = 1
                mplayer.score = 0
                mplayer.team = teamType
                found = 1
                break

        if (not found):
            mgplayer = MarkerPlayer(playerID, joined=1)
            mgplayer.team = teamType
            teamplayers.append(mgplayer)



    def updateGameTime(self):
        if (type(self.timeLeftDPL) != type(None)):
            if (MarkerGameState == kMGGameOn):
                gametime = self.timeLeft()
                self.timeLeftDPL.updateText((xLocalization.xKI.xTimeRemainingText % (int((gametime / 60)),
                 (gametime % 60))))
            else:
                self.timeLeftDPL.updateText(xLocalization.xKI.xWaitingForStartText)



glue_cl = None
glue_inst = None
glue_params = None
glue_paramKeys = None
try:
    x = glue_verbose
except NameError:
    glue_verbose = 0

def glue_getClass():
    global glue_cl
    if (glue_cl == None):
        try:
            cl = eval(glue_name)
            if issubclass(cl, ptModifier):
                glue_cl = cl
            elif glue_verbose:
                print ('Class %s is not derived from modifier' % cl.__name__)
        except NameError:
            if glue_verbose:
                try:
                    print ('Could not find class %s' % glue_name)
                except NameError:
                    print 'Filename/classname not set!'
    return glue_cl



def glue_getInst():
    global glue_inst
    if (type(glue_inst) == type(None)):
        cl = glue_getClass()
        if (cl != None):
            glue_inst = cl()
    return glue_inst



def glue_delInst():
    global glue_inst
    global glue_cl
    global glue_paramKeys
    global glue_params
    if (type(glue_inst) != type(None)):
        del glue_inst
    glue_cl = None
    glue_params = None
    glue_paramKeys = None



def glue_getVersion():
    inst = glue_getInst()
    ver = inst.version
    glue_delInst()
    return ver



def glue_findAndAddAttribs(obj, glue_params):
    if isinstance(obj, ptAttribute):
        if glue_params.has_key(obj.id):
            if glue_verbose:
                print 'WARNING: Duplicate attribute ids!'
                print ('%s has id %d which is already defined in %s' % (obj.name,
                 obj.id,
                 glue_params[obj.id].name))
        else:
            glue_params[obj.id] = obj
    elif (type(obj) == type([])):
        for o in obj:
            glue_findAndAddAttribs(o, glue_params)

    elif (type(obj) == type({})):
        for o in obj.values():
            glue_findAndAddAttribs(o, glue_params)

    elif (type(obj) == type(())):
        for o in obj:
            glue_findAndAddAttribs(o, glue_params)




def glue_getParamDict():
    global glue_paramKeys
    global glue_params
    if (type(glue_params) == type(None)):
        glue_params = {}
        gd = globals()
        for obj in gd.values():
            glue_findAndAddAttribs(obj, glue_params)

        glue_paramKeys = glue_params.keys()
        glue_paramKeys.sort()
        glue_paramKeys.reverse()
    return glue_params



def glue_getClassName():
    cl = glue_getClass()
    if (cl != None):
        return cl.__name__
    if glue_verbose:
        print ('Class not found in %s.py' % glue_name)
    return None



def glue_getBlockID():
    inst = glue_getInst()
    if (inst != None):
        return inst.id
    if glue_verbose:
        print ('Instance could not be created in %s.py' % glue_name)
    return None



def glue_getNumParams():
    pd = glue_getParamDict()
    if (pd != None):
        return len(pd)
    if glue_verbose:
        print ('No attributes found in %s.py' % glue_name)
    return 0



def glue_getParam(number):
    pd = glue_getParamDict()
    if (pd != None):
        if (type(glue_paramKeys) == type([])):
            if ((number >= 0) and (number < len(glue_paramKeys))):
                return pd[glue_paramKeys[number]].getdef()
            else:
                print ('glue_getParam: Error! %d out of range of attribute list' % number)
        else:
            pl = pd.values()
            if ((number >= 0) and (number < len(pl))):
                return pl[number].getdef()
            elif glue_verbose:
                print ('glue_getParam: Error! %d out of range of attribute list' % number)
    if glue_verbose:
        print 'GLUE: Attribute list error'
    return None



def glue_setParam(id, value):
    pd = glue_getParamDict()
    if (pd != None):
        if pd.has_key(id):
            try:
                pd[id].__setvalue__(value)
            except AttributeError:
                if isinstance(pd[id], ptAttributeList):
                    try:
                        if (type(pd[id].value) != type([])):
                            pd[id].value = []
                    except AttributeError:
                        pd[id].value = []
                    pd[id].value.append(value)
                else:
                    pd[id].value = value
        elif glue_verbose:
            print "setParam: can't find id=",
            print id
    else:
        print 'setParma: Something terribly has gone wrong. Head for the cover.'



def glue_isNamedAttribute(id):
    pd = glue_getParamDict()
    if (pd != None):
        try:
            if isinstance(pd[id], ptAttribNamedActivator):
                return 1
            if isinstance(pd[id], ptAttribNamedResponder):
                return 2
        except KeyError:
            if glue_verbose:
                print ('Could not find id=%d attribute' % id)
    return 0



def glue_isMultiModifier():
    inst = glue_getInst()
    if isinstance(inst, ptMultiModifier):
        return 1
    return 0



def glue_getVisInfo(number):
    pd = glue_getParamDict()
    if (pd != None):
        if (type(glue_paramKeys) == type([])):
            if ((number >= 0) and (number < len(glue_paramKeys))):
                return pd[glue_paramKeys[number]].getVisInfo()
            else:
                print ('glue_getVisInfo: Error! %d out of range of attribute list' % number)
        else:
            pl = pd.values()
            if ((number >= 0) and (number < len(pl))):
                return pl[number].getVisInfo()
            elif glue_verbose:
                print ('glue_getVisInfo: Error! %d out of range of attribute list' % number)
    if glue_verbose:
        print 'GLUE: Attribute list error'
    return None



# local variables:
# tab-width: 4
