package com.tgcity.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA
 * User yugai
 * Time 17/2/24.
 */
public class FileUtil {

    public static String traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    if (file2.getName().endsWith("mvp")) {
                        return file2.getAbsolutePath();
                    }
                    list.add(file2);
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        if (file2.getName().endsWith("mvp")) {
                            return file2.getAbsolutePath();
                        }
                        list.add(file2);

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("没有发现文件");
        return "";
    }

    /**
     * 将日志缓存到文件中
     */
    public static void saveLog(String content) {
        String filepath = "C:\\tgcity\\demo\\TestLoadImage\\app\\src\\main\\java\\com\\tgcity\\testloadimage";
        String filename = "Log.txt";
        try {
            File floder = new File(filepath);
            // if file doesnt exists, then create it
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(filepath + "\\" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            //设置日期格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = df.format(new Date());


            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(currentTime + "\t\t" + content + "\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
