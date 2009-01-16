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

import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.b;
import shared.m;
import shared.Bytes;
import shared.e;

/**
 *
 * @author user
 */
//this is a class I made myself, to encapsulate changing page ids.

//In pots GlobalAvatars, the pages wrap around so that MaleFall,6 has the same Pageid as MalePelletBookLeft,262
public class Pageid extends uruobj
{
    public int prefix;
    public int suffix;
    
    //context ctx;
    //Integer xOverridePrefix;
    
    public Pageid(context c)
    {
        //int rawdata;
        if(c.readversion==6) // moul
        {
            //first 16 bits is sequence prefix, next is pageid?
            int rawdata = c.in.readInt();
            prefix = rawdata >>> 16;
            suffix = rawdata & 0x0000FFFF;
            if((prefix&0xFF00)==0xFF00)
            {
                prefix = -(prefix&0xFF);
                m.msg("Encountered negative sequence prefix.");
            }
            if((suffix&0xFF00)==0xFF00)
            {
                m.warn("Pageid: untested case.");
                suffix = -(suffix&0xFF);
            }
        }
        else if(c.readversion==3) // TPOTS 
        {
            if(c.curFile.toLowerCase().startsWith("gui"))
            {
                int dummy=0;
            }
            int fixme = c.in.readInt();
            //fixme = (fixme & 0x000000FF) | ((fixme & 0x0000FF00) << 8);
            //rawdata = fixme;
            suffix = fixme & 0x000000FF;
            if((fixme & 0xFFFF0000)==0xFFFF0000) prefix = -((fixme & 0x0000FF00)>>>8);
            else prefix = (fixme & 0xFFFFFF00)>>>8;
        }
        else if(c.readversion==4||c.readversion==7) // Crowthwistle
        {
            //int fixme = Bytes.Int16ToInt32(c.readShort());
            //fixme = (fixme & 0x000000FF) | ((fixme & 0x0000FF00) << 8);
            //rawdata = fixme;
            short rawdata = c.readShort();
            prefix = (rawdata & 0x0000FF00) >>> 8;
            suffix = (rawdata & 0x000000FF);
            
        }
        e.ensure(suffix>=0 && suffix<=512); //was suffix<=255 but Moul GlobalAnimations has ones above 255
        e.ensure(prefix>=-255 && prefix<=8388607);
        //else rawdata = 0;
        //prefix = rawdata >> 16;
        //suffix = rawdata << 16 >> 16;
         
        //ctx = c;
        //xOverridePrefix = c.sequencePrefix;
        //if(/*ctx.sequencePrefix*/xOverridePrefix!=null)
        if(c.sequencePrefix!=null)
        {
            //prefix = /*ctx.sequencePrefix*/xOverridePrefix;
            prefix = c.sequencePrefix;
            if(shared.State.AllStates.getStateAsBoolean("reportSuffixes")) m.msg("Suffix: Using forced sequence prefix 0x"+Integer.toHexString(prefix));
            // I have no clue why, but this seems to be necessary
            // BultIn and Textures have a prefix which is one HIGHER than the rest of the pages for that age
            // found for both Cyan and fan-created ages, it is what both PRPTool and PlasmaExplorer show
            // so I'll just believe it
            if (suffix <= 0x20) {
                ++prefix;
            }
        }
        
        //change suffix
        if(c.pagenumMap!=null)
        {
            //Integer newsuffix = c.sequenceSuffixMap.get(suffix);
            Integer newpagenum = c.pagenumMap.get(this.getPageNumber());
            if(newpagenum!=null)
            {
                if(shared.State.AllStates.getStateAsBoolean("reportSuffixes")) m.msg("Suffix: Replacing sequence suffix "+Integer.toString(suffix)+" with "+Integer.toString(newpagenum));
                //suffix = newsuffix;
                this.setPagenum(newpagenum);
            }
            else
            {
                int dummy=0;
            }
        }
    }
    private Pageid(){}
    public static Pageid createFromPrefixSuffix(int prefix, int suffix)
    {
        Pageid result = new Pageid();
        result.prefix = prefix;
        result.suffix = suffix;
        return result;
    }
    public static Pageid createFromPrefixPagenum(int prefix, int pagenum)
    {
        if(pagenum<-2) m.err("Unhandled pagenum: investigate now!"); //could be lower.
        return createFromPrefixSuffix(prefix,pagenum+33);
    }
    public void setPagenum(int pagenum)
    {
        if(prefix<0)
        {
            if(pagenum>511)
            {
                throw new shared.uncaughtexception("Unhandled pagenum in setPagenum; investigate now!");
            }
            if(pagenum>254)
            {
                prefix--; //observed in Pots GlobalAnimations.age.  MaleClap and FemalePelletBookRight only differ in prefix, because their page numbers differ by 256. 
            }
            suffix = pagenum+1;
        }
        else
        {
            if(pagenum>222 || pagenum<-2)
            {
                throw new shared.uncaughtexception("Unhandled pagenum: investigate now!"); //could be lower
            }
            suffix = pagenum+33;
        }
    }
    public int getPageNumber()
    {
        if(prefix<0)
        {
            return suffix - 1;
        }
        else
        {
            if(suffix<33)
            {
                m.err("Unhandled pageid suffix: investigate now!");
            }
            return suffix - 33;
        }
    }
    //public int getRawData()
    //{
    //    return prefix << 16 | suffix;
    //}
    public void compile(Bytedeque deque)
    {
        if(suffix>255 || suffix < 0)
        {
            //this can validly happen, see note at top of this page.
            m.warn("Suffix is out of range to be written.  May be a loop-around suffix.");
        }
        
        //int rawdata = getRawData();
        // convert it to TPOTS format
        if(shared.State.AllStates.getStateAsBoolean("reportSuffixes")) m.msg("Suffix: Writing "+toString());
        //int newdata = (rawdata & 0x000000FF) | ((rawdata & 0x00FF0000) >>> 8);
        int newprefix = prefix<0 ? (0xFFFF00|(0xFF & (-prefix))) : prefix;
        int newdata = (suffix & 0x000000FF) | ((newprefix & 0x00FFFFFF)<<8);
        deque.writeInt(newdata);
    }
    
    public String toString()
    {
        //return "Prefix=0x"+Integer.toHexString(prefix)+", Suffix=0x"+Integer.toHexString(suffix);
        return Integer.toString(prefix) + ":" + Integer.toString(suffix);
    }

    public boolean equals(Object o)
    {
        if(o==null) return false;
        if(!(o instanceof Pageid)) return false;
        Pageid o2 = (Pageid)o;
        if(this.prefix!=o2.prefix) return false;
        if(this.suffix!=o2.suffix) return false;
        return true;
    }
    public int hashCode()
    {
        return this.prefix + this.suffix;
    }
    public Pageid deepClone()
    {
        Pageid result = new Pageid();
        result.prefix = prefix;
        result.suffix = suffix;
        return result;
    }

}
