package com.deltav.mat.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=./studentTrace.hprof
 * <p>
 * 关于P332案例分析的几个问题解答,首先老师讲错了一点，普通Java对象头的大小为12字节或16字节
 * 默认采用了指针压缩则为12字节，没有采用则为16字节（数组还需要加上数组长度）。详情参考我的博客。
 * <p>
 * 1.为什么有152字节和144字节：因为我们的URL和content存在两种情况
 * URL:"http://www.7.com"、content:"7"-----URL:"http://www.14.com"、content:"14"
 * 第一种URL长度为16，底层的char数组的占用空间为(【】方括号里面整个都属于对象头，分开写方便大家理解)
 * 【普通对象头(12) + 数组长度(4)】 + 16个字符(32) = 48字节，符合8字节对齐
 * <p>
 * 同理content 占用 【普通对象头(12) +数组长度(4)】+ 一个字符(2) = 18字节,八字节对齐=24字节
 * <p>
 * 第二种URL长度为17，底层的插入数组的占用空间为
 * 【普通对象头(12) + 数组长度(4)】 + 17个字符(34) = 50字节，不符合8字节对齐，对齐为56
 * <p>
 * 同理content 占用 【普通对象头(12) +数组长度(4)】+ 两个字符(4) = 20字节,八字节对齐=24字节
 * 所以第一种总字节为48 + 24 = 72,第二种总字节为56 + 24 = 80
 * 因此第二种比第一种多了8字节，所以是152和144。
 * (为什么总大小是152而不是72是因为我们只计算了String底层的char数组的区别没有计算各变量本身的浅堆,
 * 因为结构都想相同，所以差别就差在内容的占用上)
 * <p>
 * 2.为什么最终结果是1288
 * 首先ElementData数组本身的浅堆大小为
 * 【普通对象头(12) + 数组长度(4)】 + 数组内容【15个Obejct引用=16*4】 = 76,八字节对齐=80字节
 * 15个Object分为13个152字节+2个144字节，总大小为=2264字节
 * 7号和其他student重复的有0、21、42、63、84、35、70总计6个152和1一个144
 * 所以2264 - 6 * 152 - 144 = 1208字节
 * <p>
 * 所以ElementData本身的浅堆80 + 仅能通过它到达的浅堆1208 = 1288
 *
 * @author DeltaV235
 * @version 1.0
 */
public class StudentTrace {
    private static List<WebPage> webPages = new ArrayList<>();

    public static void main(String[] args) {
        createWebPage();
        Student st3 = new Student(3, "Tom");
        Student st5 = new Student(5, "Jerry");
        Student st7 = new Student(7, "Lily");
        for (int i = 0; i < webPages.size(); i++) {
            if (i % st3.getId() == 0) {
                st3.visit(webPages.get(i));
            }
            if (i % st5.getId() == 0) {
                st5.visit(webPages.get(i));
            }
            if (i % st7.getId() == 0) {
                st7.visit(webPages.get(i));
            }
        }

        webPages.clear();
        System.gc();
    }

    public static void createWebPage() {
        for (int i = 0; i < 100; i++) {
            WebPage webPage = new WebPage();
            webPage.setUrl("http://www." + i + ".com");
            webPage.setContent(Integer.toString(i));
            webPages.add(webPage);
        }
    }

}

