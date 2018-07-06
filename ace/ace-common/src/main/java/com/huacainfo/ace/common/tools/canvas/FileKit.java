package com.huacainfo.ace.common.tools.canvas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther: Arvin
 * @Date: 2018/7/5 16:11
 * @Description:
 */
public class FileKit {
    public static final String SEPARATOR = "/";
    protected static Logger logger = LoggerFactory.getLogger(FileKit.class);

    public FileKit() {
    }

    public static String upFile(InputStream is, String fileName, String filePath) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(filePath + "/" + fileName);

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];

            int len;
            while ((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception var18) {
            logger.error("run error", var18);
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }

                if (null != fos) {
                    fos.close();
                    fos = null;
                }

                if (null != is) {
                    is.close();
                    is = null;
                }

                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception var17) {
                logger.error("run error", var17);
            }

        }

        return fileName;
    }

    public static boolean mkDir(String filePath) {
        boolean result = false;
        Path path = Paths.get(filePath, new String[0]);
        Path parendPath = path.getParent();
        File dir = new File(parendPath.toString());
        if (dir == null || !dir.exists()) {
            result = dir.mkdirs();
        }

        return result;
    }

    public static boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        return !file.exists() ? flag : (file.isFile() ? deleteFile(sPath) : deleteDirectory(sPath));
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }

        return flag;
    }

    public static boolean deleteFileWithDir(String sPath) {
        boolean flag = false;
        if (null == sPath || "".equals(sPath.trim())) {
            return flag;
        } else {
            File file = new File(sPath);
            if (file.isFile() && file.exists()) {
                file.delete();
                flag = true;

                try {
                    if (flag) {
                        String dirPath = file.getParent();
                        File dirs = new File(dirPath);
                        if (dirs.exists() && dirs.isDirectory() && dirs.listFiles().length == 0) {
                            dirs.delete();
                        }
                    }
                } catch (Exception var5) {
                    logger.error("FileUtil.deleteFileWithDir error:{}", var5);
                }
            }

            return flag;
        }
    }

    public static boolean deleteDirectory(String sPath) {
        boolean flag = false;
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }

        File dirFile = new File(sPath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            flag = true;
            File[] files = dirFile.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                } else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
            }

            return !flag ? false : dirFile.delete();
        } else {
            return false;
        }
    }

    public static byte[] urlToByte(String url) throws MalformedURLException {
        URL ur = new URL(url);
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;

        byte[] content;
        try {
            in = new BufferedInputStream(ur.openStream());
            out = new ByteArrayOutputStream(1024);
            content = new byte[1024];
            boolean var5 = false;

            int size;
            while ((size = in.read(content)) != -1) {
                out.write(content, 0, size);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        content = out.toByteArray();
        return content;
    }
}
