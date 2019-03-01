package com.ljtq.res.ut;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileLock;
import java.security.MessageDigest;

public class DownloadManager {
    private static final char[] HEX_CHARS;
    private Context mContext;
    private static volatile DownloadManager instance;
    private boolean isDownload;
    private FileOutputStream fos;
    private InputStream is;
    private SharedPreferencesInterface spi;
    private FileLock fileLock;

    static {
        DownloadManager.HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    private DownloadManager(Context ctx) {
        super();
        this.mContext = ctx;
    }

    public final void setSharedPreferencesInterface(SharedPreferencesInterface spi) {
        this.spi = spi;
    }

    public final void downloadAndDropPayload(String version_code, String URL, String md5, String csj_temp, String csj_ttf, String csj_jar) {
        new StringBuilder("download   isDownload = ").append(this.isDownload);
        if(!this.isDownload) {
            this.isDownload = true;
            new Thread(URL, csj_temp, csj_ttf, md5, version_code, csj_jar) {
                public final void run() {
                    try {
                        URLConnection urlConnection = new URL(this.URL).openConnection();
                        ((HttpURLConnection)urlConnection).setConnectTimeout(8000);
                        ((HttpURLConnection)urlConnection).setRequestMethod("GET");
                        ((HttpURLConnection)urlConnection).setRequestProperty("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-silverlight, */*");
                        ((HttpURLConnection)urlConnection).setRequestProperty("Accept-Language", "zh-CN");
                        ((HttpURLConnection)urlConnection).setRequestProperty("Charset", "UTF-8");
                        DownloadManager.setInputStream(this.instance, ((HttpURLConnection)urlConnection).getInputStream());
                        DownloadManager.setFileOutputStream(this.instance, new FileOutputStream(new File(this.csj_temp)));
                        DownloadManager.setFileLock(this.instance, DownloadManager.getFileOutputStream(this.instance).getChannel().lock());
                        byte[] buff = new byte[0x400];
                        while(true) {
                            int readBytes = DownloadManager.getInputStream(this.instance).read(buff);
                            if(readBytes == -1) {
                                break;
                            }

                            DownloadManager.getFileOutputStream(this.instance).write(buff, 0, readBytes);
                            DownloadManager.getFileOutputStream(this.instance).flush();
                        }

                        if(DownloadManager.moveFileStub(this.csj_temp, this.csj_ttf)) {
                            String MD5 = DownloadManager.calculateMD5String(this.csj_ttf);
                            if(MD5 != null && (MD5.equals(this.md5))) {
                                new StringBuilder("md5 校验成功   versionCode = ").append(this.version_code);
                                DownloadManager.getContext(this.instance);
                                FileUtilityManager.decryptAndDropFileStub(this.csj_jar);
                                DownloadManager.getSharedPreferencesInterface(this.instance).saveCoreVersion(this.version_code);
                                goto label_63;
                            }

                            if(DownloadManager.getSharedPreferencesInterface(this.instance) == null) {
                                goto label_63;
                            }

                            DownloadManager.getSharedPreferencesInterface(this.instance).getCoreVersion();
                        }
                        else {
                            if(DownloadManager.getSharedPreferencesInterface(this.instance) == null) {
                                goto label_63;
                            }

                            DownloadManager.getSharedPreferencesInterface(this.instance).getCoreVersion();
                        }
                    }
                    catch(Throwable th) {
                        goto label_131;
                    }
                    catch(Exception ex) {
                        goto label_93;
                    }

                label_63:
                    if(DownloadManager.getFileLock(this.instance) != null) {
                        try {
                            DownloadManager.getFileLock(this.instance).release();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getInputStream(this.instance) != null) {
                        try {
                            DownloadManager.getInputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getFileOutputStream(this.instance) != null) {
                        try {
                            DownloadManager.getFileOutputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    DownloadManager.getIsDownload(this.instance);
                    return;
                    try {
                    label_93:
                        ex.printStackTrace();
                        if(DownloadManager.getSharedPreferencesInterface(this.instance) != null) {
                            DownloadManager.getSharedPreferencesInterface(this.instance).getCoreVersion();
                        }
                    }
                    catch(Throwable th) {
                        goto label_131;
                    }

                    if(DownloadManager.getFileLock(this.instance) != null) {
                        try {
                            DownloadManager.getFileLock(this.instance).release();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getInputStream(this.instance) != null) {
                        try {
                            DownloadManager.getInputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getFileOutputStream(this.instance) != null) {
                        try {
                            DownloadManager.getFileOutputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    DownloadManager.getIsDownload(this.instance);
                    return;
                label_131:
                    if(DownloadManager.getFileLock(this.instance) != null) {
                        try {
                            DownloadManager.getFileLock(this.instance).release();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getInputStream(this.instance) != null) {
                        try {
                            DownloadManager.getInputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    if(DownloadManager.getFileOutputStream(this.instance) != null) {
                        try {
                            DownloadManager.getFileOutputStream(this.instance).close();
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    DownloadManager.getIsDownload(this.instance);
                    throw th;
                }
            }.start();
        }
    }

    static FileLock getFileLock(DownloadManager instance) {
        return instance.fileLock;
    }

    static void setFileOutputStream(DownloadManager instance, FileOutputStream fos) {
        instance.fos = fos;
    }

    static void setInputStream(DownloadManager instance, InputStream is) {
        instance.is = is;
    }

    static void setFileLock(DownloadManager instance, FileLock fileLock) {
        instance.fileLock = fileLock;
    }

    private static void copyFile(File source, File destination) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        BufferedInputStream bis = new BufferedInputStream(((InputStream)fis));
        FileOutputStream fos = new FileOutputStream(destination);
        BufferedOutputStream bos = new BufferedOutputStream(((OutputStream)fos));
        byte[] buff = new byte[0x1400];
        while(true) {
            int readBytes = bis.read(buff);
            if(readBytes == -1) {
                break;
            }

            bos.write(buff, 0, readBytes);
        }

        bos.flush();
        bis.close();
        bos.close();
        fos.close();
        fis.close();
    }

    private static boolean moveFile(String filename1, String filename2) {
        boolean res = false;
        File file1 = new File(filename1);
        File file2 = new File(filename2);
        if(file1.exists()) {
            if(!file2.exists()) {
                try {
                    file2.createNewFile();
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                    return res;
                }
            }
            else if(!file2.delete()) {
                return res;
            }
        }

        try {
            DownloadManager.copyFile(file1, file2);
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return res;
        }

        if(file1.exists()) {
            file1.delete();
        }

        return true;
    }

    public static DownloadManager getInstance(Context ctx) {
        if(DownloadManager.instance == null) {
            Class class = DownloadManager.class;
            __monitor_enter(class);
            try {
                if(DownloadManager.instance == null) {
                    DownloadManager.instance = new DownloadManager(ctx);
                }

                __monitor_exit(class);
            }
            catch(Throwable th) {
                try {
                label_13:
                    __monitor_exit(class);
                }
                catch(Throwable th) {
                    goto label_13;
                }

                throw th;
            }
        }

        return DownloadManager.instance;
    }

    static InputStream getInputStream(DownloadManager instance) {
        return instance.is;
    }

    static boolean moveFileStub(String filename1, String filename2) {
        return DownloadManager.moveFile(filename1, filename2);
    }

    static FileOutputStream getFileOutputStream(DownloadManager instance) {
        return instance.fos;
    }

    static void getIsDownload(DownloadManager instance) {
        instance.isDownload = false;
    }

    public static String calculateMD5String(String filename) {
        byte[] buff = new byte[0x400];
        try {
            FileInputStream fis = new FileInputStream(filename);
            MessageDigest md = MessageDigest.getInstance("MD5");
            while(true) {
                int readBytes = ((InputStream)fis).read(buff);
                if(readBytes <= 0) {
                    break;
                }

                md.update(buff, 0, readBytes);
            }

            ((InputStream)fis).close();
            String MD5_string = DownloadManager.toHexString(md.digest());
            return MD5_string;
        }
        catch(Exception ex) {
            System.out.println("error");
            return null;
        }
    }

    static Context getContext(DownloadManager instance) {
        return instance.mContext;
    }

    static SharedPreferencesInterface getSharedPreferencesInterface(DownloadManager instance) {
        return instance.spi;
    }

    private SharedPreferencesInterface getSharedPreferencesInterface1() {
        return this.spi;
    }

    private static String toHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder(byteArray.length << 1);
        int i;
        for(i = 0; i < byteArray.length; ++i) {
            sb.append(DownloadManager.HEX_CHARS[(byteArray[i] & 0xF0) >>> 4]);
            sb.append(DownloadManager.HEX_CHARS[byteArray[i] & 15]);
        }

        return sb.toString();
    }
}

