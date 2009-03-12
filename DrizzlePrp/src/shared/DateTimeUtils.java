/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTimeUtils
{
    public static String GetSortableDate(Date d)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String result = format.format(d);
        return result;
    }

    public static String GetSortableCurrentDate()
    {
        return GetSortableDate(new Date());
    }
}
