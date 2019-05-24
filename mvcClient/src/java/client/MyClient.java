package client;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import util.FileIOutil;

import java.io.*;
import java.util.*;

//   存储response内容
class Content implements Serializable {
    String httpEntity;
    ArrayList<String> headers = new ArrayList<String>();

    public Content(String httpEntity, Header[] headers){
        this.httpEntity = httpEntity;
        for(Header header:headers){
            this.headers.add(header.toString());
        }

    }

    public Content(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while (!((s=br.readLine()).equals("entity"))){
                headers.add(s);
            }
            httpEntity = br.readLine();
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveContent(File file){
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String s:headers){
                bw.write(s+"\n");
            }
            bw.write("entity\n");
            bw.write(httpEntity);
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for(String header:headers){
            stringBuffer.append(header.toString()+" ");
        }
        stringBuffer.append("\n");
        stringBuffer.append(httpEntity);
        return stringBuffer.toString();
    }
}

public class MyClient {
    static String hostIni = "http://localhost:8080";
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    static HttpHost httpHost = HttpHost.create(hostIni);


    public static String requestResponse(String url){
        HttpGet httpGet = new HttpGet(url);

        String response = "err: exit via exception";

//        挡板
        File targetFile = new File(FileIOutil.getFileName(hostIni+url));
        if(targetFile.exists()){
            System.out.print("挡板触发: ");
            return new Content(targetFile).httpEntity;
        }else {
            FileIOutil.createNewFile(hostIni+url);
        }

        try {
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpHost,httpGet);
            response = EntityUtils.toString(closeableHttpResponse.getEntity());
//            储存header与content
            new Content(response,closeableHttpResponse.getAllHeaders()).saveContent(new File(FileIOutil.getFileName(hostIni+url)));
            closeableHttpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void selectAll(){
        String url = "/mvcApp/mysql/select/all";
        String response = requestResponse(url);
        System.out.println("selectAll: "+response);
    }

    public static void selectOne(int id){
        String url = "/mvcApp/mysql/select/one?id="+ id;
        String response = requestResponse(url);
        System.out.println("selectOne: "+response);

    }


    public static void main(String[] args) {
       selectAll();
       selectOne(1);
       selectOne(2);
    }
}
