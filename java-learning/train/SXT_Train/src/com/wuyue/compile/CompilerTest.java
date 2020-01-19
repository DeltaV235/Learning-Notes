package com.wuyue.compile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态编译java文件，并运行
 */
public class CompilerTest {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "src/com/wuyue/compile/Target.java");
        System.out.println(result == 0 ? "Succ" : "Fail");

        // 使用命令行运行指定的class 通过另一个JVM来运行.class
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("java -cp \"D:\\OneDrive - st.sdju.edu.cn\\Learning\\java-learning\\train\\SXT_Train\\src\" com.wuyue.compile.Target");
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = "";
            while ((info = br.readLine()) != null)
                System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用反射运行指定class的main方法
        try {
            URL url = new URL("file:/D:/OneDrive - st.sdju.edu.cn/Learning/java-learning/train/SXT_Train/src/");
            URLClassLoader loader = new URLClassLoader(new URL[]{url});
            Class c = loader.loadClass("com.wuyue.compile.Target");

            Method m = c.getMethod("main", String[].class);
            String[] temp = new String[]{"aa", "bb"};
            m.invoke(null, (Object) temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
