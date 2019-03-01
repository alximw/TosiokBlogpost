package com.ljtq.res.ut;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class SharedPreferencesManager {
    private static final String shared_preferences_name = "ljtq";

    public SharedPreferencesManager() {
        super();
    }

    public static void putString(Context ctx, String key, String val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, "ljtq");
        spe.putString(key, val);
        spe.commit();
    }

    private static SharedPreferences$Editor getEditor(Context ctx, String sp_name) {
        return ctx.getSharedPreferences(sp_name, 0).edit();
    }

    private static void putFloar(Context ctx, String key, float val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, "ljtq");
        spe.putFloat(key, val);
        spe.commit();
    }

    private static void putInt(Context ctx, String key, int val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, "ljtq");
        spe.putInt(key, val);
        spe.commit();
    }

    private static void putLong(Context ctx, String key, long val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, "ljtq");
        spe.putLong(key, val);
        spe.commit();
    }

    private static void putFloat(Context ctx, String sp_name, String key, float val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, sp_name);
        spe.putFloat(key, val);
        spe.commit();
    }

    private static void putInt2(Context ctx, String sp_name, String key, int val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, sp_name);
        spe.putInt(key, val);
        spe.commit();
    }

    private static void putLong2(Context ctx, String sp_name, String arg3, long arg4) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, sp_name);
        spe.putLong(arg3, arg4);
        spe.commit();
    }

    private static void putString2(Context ctx, String sp_name, String key, String val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, sp_name);
        spe.putString(key, val);
        spe.commit();
    }

    private static void putBoolean2(Context ctx, String sp_name, String key, boolean val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, sp_name);
        spe.putBoolean(key, val);
        spe.commit();
    }

    private static void putBoolean(Context ctx, String key, boolean val) {
        SharedPreferences$Editor spe = SharedPreferencesManager.getEditor(ctx, "ljtq");
        spe.putBoolean(key, val);
        spe.commit();
    }

    public static String getString(Context ctx, String key, String default) {
        return ctx.getSharedPreferences("ljtq", 0).getString(key, default);
    }

    private static float getFloat(Context ctx, String key, float default) {
        return ctx.getSharedPreferences("ljtq", 0).getFloat(key, default);
    }

    private static float getFloat2(Context ctx, String sp_name, String key, float default) {
        return ctx.getSharedPreferences(sp_name, 0).getFloat(key, default);
    }

    private static int getInt(Context ctx, String key, int default) {
        return ctx.getSharedPreferences("ljtq", 0).getInt(key, default);
    }

    private static int getInt2(Context ctx, String sp_name, String key, int default) {
        return ctx.getSharedPreferences(sp_name, 0).getInt(key, default);
    }

    private static long getLong2(Context ctx, String key, long default) {
        return ctx.getSharedPreferences("ljtq", 0).getLong(key, default);
    }

    private static long getLong(Context ctx, String sp_name, String key, long default) {
        return ctx.getSharedPreferences(sp_name, 0).getLong(key, default);
    }

    private static SharedPreferences getSharedPreferences(Context ctx, String sp_name) {
        return ctx.getSharedPreferences(sp_name, 0);
    }

    private static String getString3(Context ctx, String sp_name, String key, String default) {
        return ctx.getSharedPreferences(sp_name, 0).getString(key, default);
    }

    private static boolean getBoolean2(Context ctx, String sp_name, String key, boolean default) {
        return ctx.getSharedPreferences(sp_name, 0).getBoolean(key, default);
    }

    private static boolean getBoolean(Context ctx, String key, boolean default) {
        return ctx.getSharedPreferences("ljtq", 0).getBoolean(key, default);
    }

    private static String getString2(Context ctx, String key) {
        return SharedPreferencesManager.getString(ctx, key, "1");
    }
}

