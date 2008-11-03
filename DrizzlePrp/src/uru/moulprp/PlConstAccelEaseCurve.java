/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.moulprp;

import shared.Flt;
import shared.m;
import uru.moulprp.uruobj;
import shared.readexception;
import uru.context;
import uru.Bytedeque;

public class PlConstAccelEaseCurve extends uruobj
{
    Flt f1;
    Flt f2;
    Flt f3;
    Flt f4;
    Flt f5;
    Double64 d6;

    public PlConstAccelEaseCurve(context c) throws readexception
    {
        f1 = new Flt(c);
        f2 = new Flt(c);
        f3 = new Flt(c);
        f4 = new Flt(c);
        f5 = new Flt(c);
        d6 = new Double64(c);
    }

    public void compile(Bytedeque c)
    {
        f1.compile(c);
        f2.compile(c);
        f3.compile(c);
        f4.compile(c);
        f5.compile(c);
        d6.compile(c);
    }
}
