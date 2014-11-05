package com.caribe.stone.anki;

import java.text.*;
import java.util.Date;

/**
 * Created by thinkdeeply on 11/4/14.
 */
public class TestRevlog {
    public static void main(String[] args) throws java.text.ParseException {
        Date date = new Date(1413019245000L+66600L);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(sdf.format(date));
        System.out.println(date.getMonth());


        System.out.println(sdf.parse("2014-10-14").getTime());
    }
}
