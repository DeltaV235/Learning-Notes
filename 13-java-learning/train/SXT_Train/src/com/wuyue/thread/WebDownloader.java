package com.wuyue.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WebDownloader extends Thread {
    private String url;
    private String savePath;

    public WebDownloader(String url, String savePath) {
        this.url = url;
        this.savePath = savePath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Start downloading " + savePath);
            FileUtils.copyURLToFile(new URL(url), new File(savePath));
            System.out.println("Download Finished " + savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebDownloader wd1 = new WebDownloader("https://i0.hdslb.com/bfs/bangumi/image/31dd70491b762543438f6075a5194f9f7ab7b903.png@338w_450h.webp", "threadTest/pic/BD.jpg");
        WebDownloader wd2 = new WebDownloader("https://www.apache.org/img/ASF20thAnniversary.jpg", "threadTest/pic/apache.jpg");
        WebDownloader wd3 = new WebDownloader("https://www.itbaizhan.cn/public/images/about/qiye.jpg", "threadTest/pic/qiye.jpg");

        wd1.start();
        wd2.start();
        wd3.start();
    }
}
