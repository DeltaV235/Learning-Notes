package com.wuyue.web.response.demo;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Random;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CheckCodeServlet
 * @description
 * @date 2020/2/16 0:16
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(Color.BLUE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        String code = "ABCDEFGHIJKMNOPQRSTUVWZYZqwertyuiopasdfghjklzxcvbnm1234567890";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(code.length());
            graphics.drawString(code.charAt(index) + "", width / 5 * i, height / 2);
        }

        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
