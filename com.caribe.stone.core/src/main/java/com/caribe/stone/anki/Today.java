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
public class Today {
    public static void main(String[] args) throws IOException {

        File today = new File("today");

        File[] files = today.listFiles();
        if(files!=null)
        for(File f:files)   {
            boolean delete = f.delete();
            System.out.println(delete);

        }
        files=new File("today again").listFiles();
        if(files!=null)
        for(File f:files)   {
            boolean delete = f.delete();
            System.out.println(delete);

        }

        String property = null;
//		property = "c:/Users/yan.zhang/Documents/Anki/User 1";
        property = "/Users/thinkdeeply/Documents/Anki/User 1";
        // property = "/Users/thinkdeeply/Documents/Anki/Fiona";
        AnkiSettings settings = new AnkiSettings(property);

        DBUtils db = new DBUtils(settings);
        Dao dao = new Dao(db);
        QueryBean bean = new QueryBean();

        today(settings, dao);
        todayAgain(settings,dao);


    }

    private static void todayAgain(AnkiSettings settings, Dao dao) throws IOException {
        List<IdObject> notes = dao.getTodayCard();

        DateFormat df=new SimpleDateFormat("yyyy-MM-DD");

        Calendar c = getToday();

        Date now = c.getTime();
        System.out.println(now.getTime());
        int today = c.get(Calendar.DAY_OF_YEAR);
        System.out.println(today);
        c.set(Calendar.DAY_OF_YEAR,today-1) ;
        Date yesterday = c.getTime();
        System.out.println(yesterday.getTime());

        StringBuffer sql = new StringBuffer("select sfld from notes where id in (select nid from cards where id in (select  cid from revlog where id between \n" +
                yesterday.getTime() +
                " and " +
                now.getTime() +
                " group by cid having count(*)>1 ) )"
        );


        System.out.println(sql);


        List<Note> execute = dao.execute(sql.toString());

        for(Note n:execute){

            File us = settings.getUS(n);
            File uk = settings.getUK(n);
            File file = new File("today again")     ;
            file.delete();
            file.mkdir();
            if(us.exists())
                FileUtils.copyFileToDirectory(us, file);
            if(uk.exists())
                FileUtils.copyFileToDirectory(uk, file);


            File phraseMp3 = settings.getPhraseMp3(n);
            if(phraseMp3.exists()){
                FileUtils.copyFileToDirectory(phraseMp3, file);
            }
            System.out.println(us);
        }
    }

    private static Calendar getToday() {
        Calendar c=new GregorianCalendar();
        c.add(Calendar.HOUR_OF_DAY,-12);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.add(Calendar.HOUR_OF_DAY, 4);
        return c;
    }

    private static List<IdObject> today(AnkiSettings settings, Dao dao) throws IOException {
        List<IdObject> notes = dao.getTodayCard();

        DateFormat df=new SimpleDateFormat("yyyy-MM-DD");
//        df.parse()
        Calendar c = getToday();

        Date now = c.getTime();
        System.out.println(now.getTime());
        int today = c.get(Calendar.DAY_OF_YEAR);
        System.out.println(today);
        c.set(Calendar.DAY_OF_YEAR,today-1) ;
        Date yesterday = c.getTime();
        System.out.println(yesterday.getTime());

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

            File file = new File("today")     ;
            file.delete();
            file.mkdir();
            if(us.exists())
            FileUtils.copyFileToDirectory(us, file);
            if(uk.exists())
            FileUtils.copyFileToDirectory(uk, file);

            File phraseMp3 = settings.getPhraseMp3(n);
            if(phraseMp3.exists()){
                FileUtils.copyFileToDirectory(phraseMp3, file);
            }
            System.out.println(us);
        }
        return notes;
    }
}
