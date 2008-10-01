from Plasma import *
from PlasmaTypes import *


def dustlink(agename, spawnpoint):
    print "Attempting to link..."
    import PlasmaNetConstants
    als = ptAgeLinkStruct()
    ainfo = ptAgeInfoStruct()
    ainfo.setAgeFilename(agename)
    als.setAgeInfo(ainfo)
    als.setLinkingRules(PlasmaNetConstants.PtLinkingRules.kBasicLink)
    spTitle= (('linkname' + agename) + spawnpoint)
    spnpnt = ptSpawnPointInfo(spTitle, spawnpoint)
    als.setSpawnPoint(spnpnt)
    linkMgr = ptNetLinkingMgr()
    linkMgr.linkToAge(als)
    #PtPrintToScreen("Linking...")
    print "Linking..."
