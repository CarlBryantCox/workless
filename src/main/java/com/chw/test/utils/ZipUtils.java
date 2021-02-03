package com.chw.test.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private static final int  BUFFER_SIZE = 2 * 1024;

    /**
     * 图片地址变压缩包
     */
    public static void imgUrlToZip(List<Map<String,String>> imgMapList, OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (Map<String, String> imgMap : imgMapList) {
                addImgMapToZos(imgMap,zos);
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 图片地址变压缩包
     */
    public static void imgUrlToZip(Map<String,String> imgMap, OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            addImgMapToZos(imgMap,zos);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addImgMapToZos(Map<String,String> imgMap,ZipOutputStream zos) throws IOException {
        for(Map.Entry<String, String> it : imgMap.entrySet()){
            zos.putNextEntry(new ZipEntry(it.getKey()));
            writeInputStreamToZipOutputStream(getImageStream(it.getValue()),zos);
            zos.closeEntry();
        }
    }

    /**
     * 获取网络图片流
     */
    public static InputStream getImageStream(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        }
        throw new RuntimeException("Invalid URL");
    }

    /**
     * 功能描述:获取图片流
     */
    public static BufferedImage getImageBuff(String imgUrl) throws Exception{
        BufferedImage img;
        if (imgUrl.startsWith("http:") || imgUrl.startsWith("https:")) {
            img = ImageIO.read(new URL(imgUrl));
        } else {
            img = ImageIO.read(new File(imgUrl));
        }
        return img;
    }

    /**
     * 压缩成ZIP 方法1
     * @param srcDir 压缩文件夹路径
     * @param out    压缩文件输出流
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩成ZIP 方法2
     * @param srcFiles 需要压缩的文件列表
     * @param out           压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                writeFileToZipOutputStream(srcFile,zos);
                zos.closeEntry();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws Exception{
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            writeFileToZipOutputStream(sourceFile,zos);
            zos.closeEntry();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }

    /**
     * copy文件到zip输出流中
     */
    private static void writeFileToZipOutputStream(File sourceFile, ZipOutputStream zos) throws IOException {
        writeInputStreamToZipOutputStream(new FileInputStream(sourceFile),zos);
    }

    /**
     * copy文件到zip输出流中
     */
    private static void writeInputStreamToZipOutputStream(InputStream in, ZipOutputStream zos) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int len;
        while ((len = in.read(buf)) != -1){
            zos.write(buf, 0, len);
        }
        in.close();
    }

    public static void main(String[] args) throws Exception{
//        FileOutputStream fos1 = new FileOutputStream(new File("D:\\upload\\mytest01.zip"));
//        ZipUtils.toZip("D:\\data", fos1,true);
//
//        List<File> fileList = new ArrayList<>();
//        fileList.add(new File("C:\\Users\\Administrator\\Desktop\\前端ip.txt"));
//        fileList.add(new File("C:\\Users\\Administrator\\Desktop\\sql优化.txt"));
//        FileOutputStream fos2 = new FileOutputStream(new File("D:\\upload\\mytest02.zip"));
//        ZipUtils.toZip(fileList, fos2);

        FileOutputStream fos3 = new FileOutputStream(new File("D:\\upload\\mytest03.zip"));
        Map<String,String> map = new HashMap<>();
        map.put("one.jpg","https://jty-yue-juan.oss-cn-beijing.aliyuncs.com/scanimages/2a12220c34464b0b83ac09f85bb50b1a/22.jpg?Expires=1927349971&OSSAccessKeyId=LTAINPdmg9GsW51l&Signature=ebvJtPWE%2B1zj11lUtxS55xhn%2B%2Bg%3D");
        ZipUtils.imgUrlToZip(map,fos3);
    }


}
