package com.caribe.stone.anki;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class DownLoadService {
    public static void httpDownload(String httpUrl, String saveFile) {
        File destFile = new File(saveFile);
        if (destFile.exists()) {
            return;
        }
        long start = System.currentTimeMillis();
        // 下载网络文件
        int byteread = 0;

        if (httpUrl != null && httpUrl.trim() != "") {
            URL url = null;
            try {
                url = new URL(httpUrl);
            } catch (MalformedURLException e1) {
                System.out.println("error:" + saveFile);
                System.out.println(e1.getMessage());
                return;
            }
            FileOutputStream fs = null;
            String name = System.currentTimeMillis() + "tmp.file";
            File file = new File(name);
            try {
                URLConnection conn = url.openConnection();
                if (conn != null) {

                    InputStream inStream = conn.getInputStream();
                    fs = new FileOutputStream(file);

                    byte[] buffer = new byte[1204];
                    while ((byteread = inStream.read(buffer)) != -1) {
                        fs.write(buffer, 0, byteread);
                    }
                    long end = System.currentTimeMillis();
                    // System.out.print((int) (file.length() * 1d / (end -
                    // start)));
                    // System.out.println("K/s.");

                    FileUtils.copyFile(file, destFile);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fs != null) {
                        fs.flush();
                        fs.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (file != null) {
                    file.delete();
                }
            }

        }
    }
}
