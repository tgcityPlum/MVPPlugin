package com.tgcity.plugin;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author TGCity
 * @date 2020/1/21
 * @description
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();

        test.createClassMvp("test");
    }

    /**
     * 创建MVP架构
     */
    private void createClassMvp(String className) {
        boolean isFragment = className.endsWith("Fragment") || className.endsWith("fragment");
        if (className.endsWith("Fragment") || className.endsWith("fragment") || className.endsWith("Activity") || className.endsWith("activity")) {
            className = className.substring(0, className.length() - 8);
        }
        String path = "C:\\tgcity\\IdeaProjects\\TestMVP\\src\\com\\tgcity\\" + className.toLowerCase();
        String packageName = path.substring(path.indexOf("java") + 5, path.length()).replace("/", ".");
        String mvpPath = "C:/tgcity/demo/TestLoadImage/app/src/main/";
        mvpPath = mvpPath.substring(mvpPath.indexOf("java") + 5, mvpPath.length()).replace("/", ".").replace("\\", ".");

        className = className.substring(0, 1).toUpperCase() + className.substring(1);

        System.out.print(mvpPath + "---" + className + "----" + packageName);

        String contract = readFile("Contract.txt").replace("&package&", packageName).replace("&Contract&", className + "Contract");
        String presenter = readFile("Presenter.txt").replace("&package&", packageName).replace("&Contract&", className + "Contract").replace("&Presenter&", className + "Presenter");

        if (isFragment) {
            String fragment = readFile("Fragment.txt").replace("&package&", packageName).replace("&Fragment&", className + "Fragment").replace("&Contract&", className + "Contract").replace("&Presenter&", className + "Presenter");
            writetoFile(fragment, path, className + "Fragment.java");
        } else {
            String activity = readFile("Activity.txt").replace("&package&", packageName).replace("&Activity&", className + "Activity").replace("&Contract&", className + "Contract").replace("&Presenter&", className + "Presenter");
            writetoFile(activity, path, className + "Activity.java");
        }
        writetoFile(contract, path, className + "Contract.java");
        writetoFile(presenter, path, className + "Presenter.java");


    }

    private String readFile(String filename) {
        InputStream in = null;
        in = this.getClass().getResourceAsStream("code/" + filename);
        String content = "";
        try {
            content = new String(readStream(in));
        } catch (Exception e) {
        }
        return content;
    }

    private byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
                System.out.println(new String(buffer));
            }

        } catch (IOException e) {
        } finally {
            outSteam.close();
            inStream.close();
        }
        return outSteam.toByteArray();
    }

    private void writetoFile(String content, String filepath, String filename) {
        try {
            File floder = new File(filepath);
            // if file doesnt exists, then create it
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(filepath + "/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
