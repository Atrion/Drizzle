#Decompiled with Drizzle30!  Enjoy :)

global _AgeInfo
import _UamEvents
import Plasma
import uam
import xLinkMgr
import _UamUtils
import os
_AgeInfo = None

def _ReadAgeInfo():
    global _AgeInfo
    try:
        _AgeInfo = {}
        if os.path.exists('img/UamAgeInfo/'):
            files = os.listdir('img/UamAgeInfo/')
        else:
            files = []
        for filename in files:
            if (filename.startswith('UamAgeInfo--') and filename.endswith('.txt')):
                try:
                    agename = filename[len('UamAgeInfo--'):(len(filename) - len('.txt'))]
                    print ('agename: ' + agename)
                    f = file(('img/UamAgeInfo/' + filename), 'r')
                    contents = f.read()
                    f.close()
                    agedict = _UamUtils._StringToDict(contents)
                    if ('fullagename' not in agedict):
                        agedict['fullagename'] = agename
                    if ('info' not in agedict):
                        agedict['info'] = ''
                    hidden = agedict.get('hidden', 'false')
                    if (hidden == 'true'):
                        agedict['hidden'] = True
                    else:
                        agedict['hidden'] = False
                    if ('defaultspawnpoint' not in agedict):
                        agedict['defaultspawnpoint'] = 'LinkInPointDefault'
                    if ('linkingrule' not in agedict):
                        agedict['linkingrule'] = 'basic'
                    linkingrule = agedict['linkingrule']
                    if (linkingrule in ('basic', 'original')):
                        agedict['parentage'] = ''
                    elif linkingrule.startswith('subage:'):
                        agedict['linkingrule'] = 'subage'
                        agedict['parentage'] = linkingrule[7:]
                    else:
                        raise 'Unhandled linkingrule.'
                    spawnpoints_str = agedict.get('spawnpoints', '')
                    spawnpoints = {}
                    for spstr in spawnpoints_str.split(','):
                        spstr = spstr.strip()
                        if (spstr != ''):
                            spstrparts = spstr.split(':')
                            name = spstrparts[0].strip()
                            title = spstrparts[1].strip()
                            spawnpoints[name] = title
                    agedict['spawnpoints'] = spawnpoints
                    _AgeInfo[agename] = agedict
                except:
                    import traceback
                    traceback.print_exc()
    except:
        import traceback
        traceback.print_exc()


def _AddAgeInfo():
    global _AgeInfo
    import xLinkMgr
    for agename in _AgeInfo:
        agedict = _AgeInfo[agename]
        fullagename = agedict['fullagename']
        linkrule = agedict['linkingrule']
        if (linkrule == 'subage'):
            linkrule = (('subageof(' + agedict['parentage']) + ')')
        defspawnpoint = agedict['defaultspawnpoint']
        age = xLinkMgr._Age(fullagename, 'dataserver', linkrule, defspawnpoint)
        age.description = agedict['info']
        spawnpoints = agedict['spawnpoints']
        for spname in spawnpoints:
            age.spawnpoints[spname] = spawnpoints[spname]
        if agedict['hidden']:
            age.linktype = 'hidden'
        else:
            age.linktype = 'public'
        xLinkMgr._AvailableLinks[agename] = age


def _SortRestorationLinks():
    print '_UamMod_AgeInfo sorting...'
    import xLinkMgr

    def cmp2(a, b):
        return cmp(a[1].lower(), b[1].lower())

    for item in xLinkMgr._RestorationLinks:
        age = xLinkMgr._AvailableLinks[item[0]]
        if (not (hasattr(age, 'linktype'))):
            age.linktype = 'public'
    newrest = []
    for agename in xLinkMgr._AvailableLinks:
        age = xLinkMgr._AvailableLinks[agename]
        print ('checking %s %s' % (agename, age.displayName))
        if (hasattr(age, 'linktype') and (age.linktype == 'public')):
            newrest.append((agename, age.displayName))
            print 'adding.'
    newrest.sort(cmp2)
    xLinkMgr._RestorationLinks = newrest

import xUserKI
if (xUserKI.gUserKIVersion == '3.4'):
    print 'Detected ki 3.4'
    raise 'Error: _UamMod_AgeInfo, only supports offline-ki 3.5 or newer'
else:
    print 'assuming ki 3.5+'
    import xLinkMgr

    def _New_LoadAvailableLinks(old_method = xLinkMgr._LoadAvailableLinks):
        import xLinkMgr
        needsrefresh = (len(xLinkMgr._AvailableLinks) == 0)
        old_method()
        if needsrefresh:
            print 'UamMod_AgeInfo loading data'
            _ReadAgeInfo()
            _AddAgeInfo()
            _SortRestorationLinks()

    xLinkMgr._LoadAvailableLinks = _New_LoadAvailableLinks


