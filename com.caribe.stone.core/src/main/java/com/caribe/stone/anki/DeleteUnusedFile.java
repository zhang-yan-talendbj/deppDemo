package com.caribe.stone.anki;

import java.io.File;

/**
 * Created by thinkdeeply on 8/30/14.
 */
public class DeleteUnusedFile {

    public static void main(String[] args) {

      File  file =new File("/Users/thinkdeeply/Documents/Anki/User 1/collection.media")          ;
      File[] filelist=  file.listFiles();
        for (File f:filelist) {
            String name=f.getName();
            if(name.indexOf(" ")>0)     {


            System.out.println(f.getName());
                f.delete();
            }

        }

    }

}
