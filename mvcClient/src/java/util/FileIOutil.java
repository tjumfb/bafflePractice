package util;

import java.io.*;

public class FileIOutil {
    public static void saveObjectByObjectOutput(Object o, File file) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getObjectByObjectInput(File file) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            Object o = inputStream.readObject();
            inputStream.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//    根据url创建目录
    public static void createNewFile(String url){
        url = url.replace("//","/");
        url = url.replace(":","");
        String[] sps = url.split("/");
        String fileName = sps[sps.length-1];
        String pathName = url.substring(0,url.length()-fileName.length());

        File dir = new File(pathName);
        if(dir.mkdirs()){
            System.out.println("创建目录成功");
        }else if(dir.exists()){
            System.out.println("目录存在");
        }else {
            System.out.println("创建目录失败");
        }

        try {
            new File(pathName+fileName).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String url){
        url = url.replace("//","/");
        url = url.replace(":","");
        String[] sps = url.split("/");
        String fileName = sps[sps.length-1];
        String pathName = url.substring(0,url.length()-fileName.length());
        return pathName+fileName;
    }

    public static void main(String[] args) {
        createNewFile("http://localhost:8080/mysql/select/all");
    }
}
