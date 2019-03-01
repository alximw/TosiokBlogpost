package com.ljtq.res.ut;

import android.content.Context;
import com.ljtq.res.Res;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public final class FileUtilityManager {
    public FileUtilityManager() {
        super();
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

    private static void decryptAndDropFile(InputStream is, String filename) throws Exception {
        File file = new File(filename);
        if(is != null) {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            SecretKeySpec sks = new SecretKeySpec(new SecretKeySpec(RandomUtilityManager.base64DecodeStringToByteArray("GiEhjghmZIO7RTWyycQ9PQ=="), "AES").getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, ((Key)sks));
            CipherOutputStream cos = new CipherOutputStream(((OutputStream)fos), cipher);
            byte[] buff = new byte[0x400];
            while(true) {
                int readBytes = is.read(buff);
                if(readBytes == -1) {
                    break;
                }

                cos.write(buff, 0, readBytes);
                cos.flush();
            }

            cos.close();
            fos.close();
            is.close();
        }
    }

    private static boolean replaceFile(String filename1, String filename2) {
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
            FileUtilityManager.copyFile(file1, file2);
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

    private static void dropFile(InputStream is, String filename) throws Exception {
        File file = new File(filename);
        if(is != null) {
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buff = new byte[0x400];
            while(true) {
                int readBytes = is.read(buff);
                if(readBytes == -1) {
                    break;
                }

                fos.write(buff, 0, readBytes);
                fos.flush();
            }

            fos.close();
            is.close();
        }
    }

    private static String getCacheDirAbsolutePath(Context ctx) {
        return ctx.getDir("cache", 0).getAbsolutePath();
    }

    private static void d(Context ctx, String filename) throws Exception {
        FileUtilityManager.decryptAndDropFile(ctx.getAssets().open(FileNameManager.filename_ttf), filename);
    }

    private static int getRandomDigit() {
        return new Random().nextInt(10);
    }

    public static void decryptAndDropFileStub(String filename) throws Exception {
        FileUtilityManager.decryptAndDropFile(new FileInputStream(new File(Res.csj_absolute_path, FileNameManager.filename_ttf)), filename);
    }
}

