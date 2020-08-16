package com.wuyue.oop;

public class YanMi {
    public static void main(String[] args) {
        Monkey monkey = new Monkey("杨幂");
        monkey.movie();
        monkey.TV();
        monkey.sing();
    }
}

interface makeMovie {
    void movie();
}

interface makeTV {
    void TV();
}

interface makeSong {
    void sing();
}

class Monkey implements makeMovie,makeSong,makeTV {
    private String name;

    public Monkey(String name) {
        this.name = name;
        System.out.println("大家好，我是" + name);
    }

    @Override
    public void movie() {
        System.out.println("我能演电影");
    }

    @Override
    public void TV() {
        System.out.println("我能演电视剧");
    }

    @Override
    public void sing() {
        System.out.println("我能唱歌");
    }
}
