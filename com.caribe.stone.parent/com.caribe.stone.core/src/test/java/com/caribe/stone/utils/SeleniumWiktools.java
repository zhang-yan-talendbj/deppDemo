package com.caribe.stone.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.IOException;

public class SeleniumWiktools {

    private static String wikiPath = "f:\\depp\\wiki\\";
    private static final WebDriver DRIVER = new HtmlUnitDriver();

    public static void main(String[] args) throws IOException {
        File[] files = new File(wikiPath).listFiles();
        for (File f : files) {
            readWiki(f);
        }
    }

    private static void doMoin(String wikiName, String wikiContent) {
        DRIVER.get("http://localhost:8080/wiki/" + wikiName);
        boolean isCreate;
        // Find the text input element by its name
        if (isTextPresent("Create New Page")) {
            System.out.println("create");
            DRIVER.findElement(By.linkText("Create New Page")).click();
            isCreate = true;
        } else {
            DRIVER.findElement(By.name("texteditlink")).click();
            isCreate = false;
        }
        WebElement editor = DRIVER.findElement(By.id("editor-textarea"));
        if (isCreate) {
            editor.clear();
        }
//        String oldContent = editor.getValue();
//        if (!oldContent.equals(wikiContent)) {
//            editor.sendKeys("\r\n");
//            editor.sendKeys("  ");
//            editor.sendKeys(wikiContent);
//            editor.submit();
//        }
    }

    public static void readWiki(File file) throws IOException {
        if (file.isFile()) {
            String content = FileUtils.readFileToString(file, "utf-8");
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            doMoin(fileName, content);
        }
    }
    public static boolean isTextPresent(String text) {
        return StringUtils.contains(DRIVER.findElement(By.tagName("body")).getText(), text);
    }
}
