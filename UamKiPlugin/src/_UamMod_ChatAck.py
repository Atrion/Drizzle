#Decompiled with Drizzle30!  Enjoy :)

global SentMsgs
global RecdMsgs
global checkLoopBegun
import _UamEvents
import _UamUtils
import uam
import _UamTimer
kRTChatDustMsg = 128
SentMsgs = {}
RecdMsgs = {}
secsPerResend = 30
maxSends = 4
cycleTime = 5.0
import time
import Plasma
checkLoopBegun = False

def CheckMsgs():
    global checkLoopBegun
    if (not (checkLoopBegun)):
        checkLoopBegun = True
        CheckMsgs2()


def CheckMsgs2():
    global SentMsgs
    print 'DustAck checking messages...'
    for info in SentMsgs.values():
        id = info[0]
        sendtime = info[1]
        checksum = info[2]
        toPlayer = info[3]
        message = info[4]
        numsends = info[5]
        flags = info[6]
        curtime = time.time()
        if ((curtime - sendtime) > secsPerResend):
            if (numsends >= maxSends):
                print 'DustAck giving up...'
                uam.PrintKiMessage((((('The following message could not be delivered to ' + (`(toPlayer)`)) + ':') + message[:30]) + '...'))
                del SentMsgs[id]
            else:
                print 'DustAck resending...'
                info[5] = (numsends + 1)
                info[1] = curtime
                resendmsg = ((((((('/dust pleaseack ' + (`(id)`)) + ' ') + (`(checksum)`)) + ' ') + (`(numsends)`)) + ' :') + message)
                Plasma.PtSendRTChat(Plasma.PtGetLocalPlayer(), [toPlayer], resendmsg, flags)
    _UamTimer.Timer(CheckMsgs2, cycleTime, False, False)


def _OnRTChat(player, message, flags, old_method = uam._ki.OnRTChat):
    global RecdMsgs
    global SentMsgs
    print ((('DustAck OnRTChat message:' + (`(message)`)) + ' flags:') + (`(flags)`))
    msgparts = message.split(' ')
    if (msgparts[0] == '/dust'):
        type = msgparts[1]
        if (type == 'pleaseack'):
            id = int(msgparts[2])
            checksum = int(msgparts[3])
            numsends = int(msgparts[4])
            message = message[(message.index(':') + 1):]
            curtime = time.time()
            print 'DustAck OnRTChat, let\'s ack.'
            import binascii
            actualsum = binascii.crc32(message)
            import Plasma
            if (checksum != actualsum):
                print 'DustAck error! Checksums don\'t match'
                uam.PrintKiMessage((('The following chat message was corrupted. (id:' + (`(id)`)) + ')'))
            ackmsg = ('/dust ack ' + (`(id)`))
            Plasma.PtSendRTChat(Plasma.PtGetLocalPlayer(), [player], ackmsg, kRTChatDustMsg)
            RecdMsg = RecdMsgs.get(id)
            if RecdMsg:
                print ('DustAck warning: we\'ve already received this message with id:' + (`(id)`))
            else:
                RecdMsgs[id] = [id, checksum, curtime]
                if (numsends > 1):
                    print ('DustAck warning: this message was resent; this is send number: ' + (`(numsends)`))
                old_method(player, message, flags)
        elif (type == 'ack'):
            print 'DustAck received ack...'
            id = int(msgparts[2])
            m = SentMsgs.get(id)
            if m:
                print 'DustAck got ack, so removing'
                print ('SentMsgs(before):' + (`(SentMsgs)`))
                del SentMsgs[id]
                print ('SentMsgs(after):' + (`(SentMsgs)`))
            else:
                print 'DustAck warning! Unexpected ID. Could simply be already removed though.'
        else:
            print ('DustAck unknown type:' + type)
    else:
        old_method(player, message, flags)

uam._ki.OnRTChat = _OnRTChat

def _ISendRTChat(message, old_method = uam._ki.ISendRTChat):
    print ('DustAck ISendRTChat message:' + (`(message)`))
    old_method(message)

uam._ki.ISendRTChat = _ISendRTChat
import Plasma

def _PtSendRTChat(fromPlayer, toPlayerList, message, flags, old_method = Plasma.PtSendRTChat):
    global SentMsgs
    print ((('DustAck PtSendRTChat message: ' + (`(message)`)) + ' flags: ') + (`(flags)`))
    pleaseAck = True
    if pleaseAck:
        if (len(toPlayerList) > 0):
            import binascii
            import xRandom
            import time
            checksum = binascii.crc32(message)
            for toPlayer in toPlayerList:
                if (toPlayer == Plasma.PtGetLocalPlayer()):
                    old_method(fromPlayer, [toPlayer], message, flags)
                else:
                    print ('DustAck sending to player:' + (`(toPlayer)`))
                    id = xRandom.randint(0, 2000000000)
                    sendtime = time.time()
                    newmsg = ((((((('/dust pleaseack ' + (`(id)`)) + ' ') + (`(checksum)`)) + ' ') + '1') + ' :') + message)
                    flags ^= kRTChatDustMsg
                    SentMsgs[id] = [id, sendtime, checksum, toPlayer, message, 1, flags]
                    old_method(fromPlayer, [toPlayer], newmsg, flags)
                    CheckMsgs()
        else:
            old_method(fromPlayer, toPlayerList, message, flags)
    else:
        old_method(fromPlayer, toPlayerList, message, flags)

try:
    import BlackBarBodyKIHandler
    BlackBarBodyKIHandler.PtSendRTChat = _PtSendRTChat
except:
    print 'DustAck blackbar failed!'


