package com.topjohnwu.magisk.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

import androidx.core.content.FileProvider;

public class APKInstall {
    public static void install(Context c, File apk) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent install = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri content = FileProvider.getUriForFile(c, c.getPackageName() + ".provider", apk);
            install.setData(content);
            c.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(install);
        }
    }
}