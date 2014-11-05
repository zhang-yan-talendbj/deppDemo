package com.caribe.stone.anki;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by thinkdeeply on 11/5/14.
 */
public class Report {
    public static void main(String[] args) throws IOException {
        String property = null;
//		property = "c:/Users/yan.zhang/Documents/Anki/User 1";
        property = "/Users/thinkdeeply/Documents/Anki/User 1";
        // property = "/Users/thinkdeeply/Documents/Anki/Fiona";
        AnkiSettings settings = new AnkiSettings(property);

        DBUtils db = new DBUtils(settings);
        Dao dao = new Dao(db);
        QueryBean bean = new QueryBean();

        List<IdObject> notes = dao.getTodayCard();

        DateFormat df=new SimpleDateFormat("yyyy-MM-DD");
//        df.parse()
        Calendar c=new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY,23);
        int today = c.get(Calendar.DAY_OF_YEAR);
        System.out.println(today);
        Date now = c.getTime();
        System.out.println(now);
        c.set(Calendar.DAY_OF_YEAR,today-1) ;
        Date yesterday = c.getTime();
        System.out.println(yesterday);

        StringBuffer sql = new StringBuffer("select sfld from notes where id in (select nid from cards where id in ("
                );
        for(IdObject id:notes){
            Date lastViewTime = new Date(id.getId());
            if(lastViewTime.after(yesterday)&& lastViewTime.before(now)){
                sql.append(id.getCid()).append(",");
            }
        }

        String sqll = sql.substring(0, sql.length() - 1);
        List<Note> execute = dao.execute(sqll + "))");

        for(Note n:execute){

            File us = settings.getUS(n);
            File uk = settings.getUK(n);
            File file = new File("mp")     ;
            FileUtils.copyFileToDirectory(us, file);
            FileUtils.copyFileToDirectory(uk, file);
            System.out.println(us);
        }

        System.out.println("There are "+notes.size()+" words.");
    }
}
