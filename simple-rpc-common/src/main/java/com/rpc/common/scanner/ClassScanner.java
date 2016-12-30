package com.rpc.common.scanner;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;

/**
 * Created by kaiwang on 2016/12/29.
 */
public class ClassScanner {
    public static List<Class> doScan(String basePackage) throws IOException, ClassNotFoundException {
        List<Class> classList = new ArrayList<>();
        String basePackageDir = basePackage.replace(".", "/");
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(basePackageDir);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            String urlProtocol = url.getProtocol();
            String foldName = URLDecoder.decode(url.getFile(), "UTF-8");
            if (urlProtocol.equals("file")) {
                classList = getClassesByFoldFileName(basePackage, foldName);
            } else if (urlProtocol.equals("jar")) {
                JarFile jar;
                jar = ((JarURLConnection) url.openConnection()).getJarFile();

            }
        }
        return classList;
    }

    private static List<Class> getClassesByFoldFileName(String pathName, String foldName) throws IOException, ClassNotFoundException {
        File dir = new File(foldName);
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return null;
        }
        String classPathName = pathName.replace("/", ".");
        File fold = new File(foldName);
        File[] files = fold.listFiles();
        List<Class> classes = new ArrayList<>();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                classes.addAll(getClassesByFoldFileName(pathName + "." + fileName, foldName + "/" + fileName));
            }
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (fileSuffix.equals("class")) {
                String className = fileName.substring(0, fileName.length() - 6);
                classes.add(Class.forName(classPathName + "." + className));
            }
        }
        return classes;
    }
}
