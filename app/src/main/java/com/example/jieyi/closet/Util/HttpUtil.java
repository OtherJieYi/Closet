package com.example.jieyi.closet.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.jieyi.closet.LoginPage.LoginPage;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    private static final int TIMEOUT_IN_MILLIONS = 5000;

    //自定义接口
    public interface CallBack{
        void onRequestComplete(String result);
    }

    public static void doGetAsyn(final String url,final CallBack callBack){//,final CallBack
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String result = doGet(url);
                    Log.d("网络读取了：",result);

                    Gson gson = new Gson();
                    MessageInfo info = gson.fromJson(result,MessageInfo.class);
                    String code = info.getCode();
                    if(code.equals("login_sucess")){
                        //登陆成功 缓存账号密码，下次登陆/访问网络就使用该账号 sharedPreferences
                        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                        editor.putString("account",info.getAccount());
                        editor.putString("password",info.getPassword());
                        editor.apply();
                    }

                    //使用回调函数将返回码存储起来，通知客户端
                    //使用handle 将message发送出去，让主线程来修改UI，跳转activity
                    if(callBack != null){
                        callBack.onRequestComplete(code);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void doPostAsyn(final String url,final  String params,final CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String result = doPost(url,params);
                    if(callBack != null){
                        callBack.onRequestComplete(result);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
    向指定 URL 发送get方法
    @params url 访问地址
    @return 代表响应结果
    @throws Exception
     */
    public static String doGet(String urlStr){
        //整体初始化
        URL url = null; //访问地址
        HttpURLConnection conn = null; //安卓官方推荐使用的 http连接 API
        InputStream is = null; //获取服务器返回的输入流
        ByteArrayOutputStream baos = null; //字节数组输出流
        BufferedReader reader=null;
        try{
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            // 设置是否使用缓存  默认是true
            conn.setUseCaches(true);
            conn.setRequestMethod("GET");
            //设置请求中的媒体类型信息。
            conn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            conn.addRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("charset", "utf-8");//gb2312  utf-8
            conn.connect();
            //setRequestProperty 按照缺省值传递
            //状态为200 的时候服务器正常处理
            String s = conn.getResponseMessage();
            Log.d("状态码",conn.getResponseCode()+ "");
            if(conn.getResponseCode() == 200){

                is = conn.getInputStream();
                //解决乱码问题
                //reader = new BufferedReader(new InputStreamReader(is,"GBK"));//utf-8
                baos = new ByteArrayOutputStream();
                int len  = 0;//0
                byte[]  buf = new byte[1024];
                while((len = is.read(buf))!= -1) {
                    baos.write(buf, 0, len);
                }

                baos.flush();
                byte[] byteArray = baos.toByteArray();
                System.out.println(new String(byteArray));
                //return baos.toString("utf-8");
                return new String(byteArray);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is != null) {
                    is.close();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }

            /*try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }

        return null;
    }

    /*
    函数名：doPost
    @Params urlStr 访问地址
    @Params params JSON 流
    @return 返回结果
     */
    public static String doPost(String urlStr,String params){
        //整体初始化
        URL url = null; //访问地址
        HttpURLConnection conn = null; //安卓官方推荐使用的 http连接 API

        //post
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try{
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setRequestMethod("POST");

            //setRequestProperty 告诉服务器的你可客户端的配置
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoInput(true); //
            conn.setDoOutput(true); //
            if(params != null && !params.trim().equals("")){
                //获取 URL Connection 对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                //发送参数请求
                //out.print(params);
                //单元测试
                out.print("account=1");
                out.print("password=1");
                //flush 输出流的缓冲
                out.flush();
            }


            //定义bufferedRead 输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                result += line;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  result;
    }

    class MessageInfo{

        private String code;
        private String account;
        private String password;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }
}
