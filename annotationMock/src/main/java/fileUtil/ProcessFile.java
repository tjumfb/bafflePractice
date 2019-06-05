package fileUtil;

import baffle.Baffle;
import baffle.Logging;

import java.io.*;
import java.util.logging.Logger;

public class ProcessFile {
    static Logger logger = Logger.getLogger("fileLog");
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
        }catch (EOFException e){
            logger.info("读入数据");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //    Object controller.HelloController.addRam(String,String,int)
    public static void createNewFile(String path, String raw, Object[] args){

        raw = raw.split(" ")[1];
        String pathName = raw.replace(".","/").replace("(","/").replace(")","/").replace(",","/");
        pathName = path + pathName;
        StringBuffer fileName = new StringBuffer();

        for(Object o:args){
            fileName.append(o.toString());
            fileName.append(".");
        }
        File dir = new File(pathName);
        if(dir.mkdirs()){
            logger.info("创建目录成功");
        }else if(dir.exists()){
            logger.info("目录存在");
        }else {
            logger.info("创建目录失败");
        }
        try {
            boolean msg = new File(pathName+fileName).createNewFile();
            if(msg) logger.info("新文件创建："+pathName+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getFileName(String raw, Object[] args){
        raw = raw.split(" ")[1];
        String pathName = raw.replace(".","/").replace("(","/").replace(")","/").replace(",","/");
        StringBuilder fileName = new StringBuilder();
        for(Object o:args){
            fileName.append(o.toString());
            fileName.append(".");
        }
        return pathName+fileName;
    }
}
