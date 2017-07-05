//package com.sinatest;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


class fileWorker implements Runnable {
    private File inputFile;
    private String outputFile;

    //构造函数
    public fileWorker(File inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public synchronized void run() {
        File output = new File(outputFile);
        if (!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fin = null;
        FileOutputStream fout = null;
        FileChannel fic = null;
        FileChannel foc = null;
        try {
            fin = new FileInputStream(inputFile);
            fout = new FileOutputStream(output, true);
            // 从FileInputStream创建用于输入的FileChannel
            fic = fin.getChannel();
            // 从FileOutputStream 创建用于输出的FileChannel
            foc = fout.getChannel();
            // 16KB缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024 << 4);
            // 根据 read返回实际读出的字节数 中止循环
            while (fic.read(bb) > 0) {
                // 缓冲区翻转用于输出到foc
                bb.flip();
                foc.write(bb);
                // 清空缓冲区用于下次读取
                bb.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 安全释放资源
            if (null != fic)
                try {
                    fic.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (null != foc)
                try {
                    foc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (null != fin)
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (null != fout)
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}


public class Main {
    //线程池线程数量
    public static final int THREAD_POOL_SIZE = 5;
    //遍历文件夹
    public static List<File> filePathsList = new ArrayList<File>();
    //缓存队列
    private static final BlockingQueue BLOCKING_QUEUE = new LinkedBlockingQueue();
    //1、遍历文件夹
    //2、线程池读取
    //3、线程写入

    public static void main(String[] args) {

        //读取文件类型
        String fileSuffix = ".txt";
        //读取文件目录
        String fileFolder = "D://Project//Java//MergeFile//datatest";
        //合并文件夹路径
        String outputFilePath = "D://Project//Java//MergeFile//output.txt";
        //遍历文件夹
        getFileList(fileFolder, fileSuffix);
        //创建线程池
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        //每一个线程读取一个文件
        for (File filePath : filePathsList) {
            es.execute(new fileWorker(filePath, outputFilePath));
        }
    }

    public static void getFileList(String fileFolder, String fileSuffix) {
        File f = new File(fileFolder);
        File[] filePaths = f.listFiles();
        for (File s : filePaths) {
            if (s.isDirectory()) {
                getFileList(s.toString(), fileSuffix);
            } else {
                if (-1 != s.getName().lastIndexOf(fileSuffix)) {
                    filePathsList.add(s);
                }
            }
        }
    }

}
