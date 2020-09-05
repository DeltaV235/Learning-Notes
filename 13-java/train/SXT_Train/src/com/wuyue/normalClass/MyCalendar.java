package com.wuyue.normalClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static java.util.Calendar.*;

/**
 * 读取用户输入的日期，然后可视化的输出当月的日历，并在输入的改天后用*号标注
 *
 * @author DeltaV235
 */

public class MyCalendar {
    private Calendar gc;
    private int day;
    private int month;

    public MyCalendar(String input) throws ParseException {
        gc = new GregorianCalendar();
        gc.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(input));
        this.day = gc.get(DAY_OF_MONTH);
        this.month = gc.get(MONTH);
        System.out.println("输入的日期是： " + gc.get(YEAR) + "-" + (month + 1) + "-" + day);
    }

    public void show() {
        gc.set(DAY_OF_MONTH, 1);
        int startDayOfWeek = gc.get(DAY_OF_WEEK);
        gc.set(MONTH, month + 1);
        gc.add(DAY_OF_MONTH, -1);
        int endDayOfMonth = gc.get(DAY_OF_MONTH);
        System.out.println("日\t一\t二\t三\t四\t五\t六");

        int dayOfWeek = startDayOfWeek;
        boolean isFirstLine = true;
        for (int day = 1; day <= endDayOfMonth; day++) {
            if (isFirstLine)
                for (int temp = 1; temp < startDayOfWeek; temp++) {
                    System.out.print("\t");
                    isFirstLine = false;
                }
            if (dayOfWeek <= 7) {
                System.out.print(day);
                dayOfWeek++;
            } else {
                System.out.println();
                System.out.print(day);
                dayOfWeek = 2;
            }
            if (day == this.day)
                System.out.print("*");
            System.out.print("\t");
        }
    }

    public static void main(String[] args) throws ParseException {
        System.out.print("请输入日期 (格式为 2020-1-23):");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        MyCalendar myCalendar = new MyCalendar(input);
        myCalendar.show();
    }
}
