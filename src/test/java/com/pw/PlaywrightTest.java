package com.pw;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaywrightTest {

    @Test
    public void firstTest() {
        try(Playwright pw = Playwright.create()) {
           BrowserType browserType = pw.chromium();
            Browser browser = browserType.launch();
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());
        }
    }

    @Test
    public void secondScript() {
        try(Playwright pw = Playwright.create()) {
            //System.out.println(pw.chromium().launch().newPage().navigate("https://playwright.dev"));
            Page page = pw.chromium().launch().newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());
        }
    }

    @Test
    public void thirdScript(){

        try(Playwright pw = Playwright.create()) {

            List<BrowserType> browserTypes = Stream.of(pw.chromium(), pw.firefox(), pw.webkit()).collect(Collectors.toList());

            for(BrowserType type : browserTypes) {
                Page page = type.launch().newPage();
                page.navigate("https://www.whatsmybrowser.org/");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(type.name() + ".png")));
            }
        }



    }
}
