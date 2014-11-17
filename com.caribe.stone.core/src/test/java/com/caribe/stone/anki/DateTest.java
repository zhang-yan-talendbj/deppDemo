package com.caribe.stone.anki;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by thinkdeeply on 11/16/14.
 */
public class DateTest {

    @Test
    public void test(){
        DateFormat df=new SimpleDateFormat("yyyy-MM-DD");
//        df.parse()
        Calendar c=new GregorianCalendar();
//        c.set(Calendar.HOUR_OF_DAY,23);
        c.add(Calendar.HOUR_OF_DAY,-12);
        System.out.println(c);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.add(Calendar.HOUR_OF_DAY,4);
        System.out.println(c.getTime());
    }
}
