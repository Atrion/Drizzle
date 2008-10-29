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

package shared;

import org.bouncycastle.crypto.digests.MD5Digest;

/**
 *
 * @author user
 */
public class CryptHashes {
    
    public static byte[] GetMd5(byte[] inputData)
    {
        MD5Digest d = new MD5Digest();
        byte[] result = new byte[d.getDigestSize()]; //allocate enough bytes for the hash.

        d.update(inputData,0,inputData.length); //calculate the hash.
        d.doFinal(result, 0); //get the result.
        return result;
    }

}
