/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.m;

public class DownloadErrorException extends RuntimeException
{
    public DownloadErrorException(String msg)
    {
        super(msg);
        m.err(msg);
    }
}
