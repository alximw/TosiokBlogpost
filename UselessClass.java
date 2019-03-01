package com.ljtq.res.ut;

public final class UselessClass {
    private static boolean L;

    public UselessClass() {
        super();
    }

    private static void a(Object arg1) {
        if(UselessClass.L) {
            String.valueOf(arg1);
        }
    }

    private static void a(boolean arg0) {
        UselessClass.L = arg0;
    }

    private static void b(Object arg1) {
        if(UselessClass.L) {
            String.valueOf(arg1);
        }
    }

    private static void c(Object arg1) {
        if(UselessClass.L) {
            String.valueOf(arg1);
        }
    }

    private static void d(Object arg1) {
        if(UselessClass.L) {
            String.valueOf(arg1);
        }
    }

    private static void e(Object arg1) {
        if(UselessClass.L) {
            String.valueOf(arg1);
        }
    }
}

