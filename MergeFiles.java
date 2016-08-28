package com.multithread;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MergeFiles {

	public static final int BUFSIZE = 1024 * 8;

	// public static void mergeFiles(String outFile, String[] files) {
	public static void mergeFiles(String outFile, String dirpath) {

		File dir = new File(dirpath);
		File[] dirfiles = dir.listFiles();
		List<String> list = new ArrayList<String>();
		if (dirfiles == null) {
			System.out.println("DirPath Error!");
		} else {
			for (int i = 0; i < dirfiles.length; i++) {
				String filename = dirfiles[i].getAbsolutePath();
				if (filename.endsWith(".txt")) {
					list.add(filename);
				}
			}
		}
		String[] files = list.toArray(new String[list.size()]);

		FileChannel outChannel = null;
		System.out.println("Merge " + Arrays.toString(files) + " into " + outFile);
		try {
			outChannel = new FileOutputStream(outFile).getChannel();
			for (String f : files) {
				FileChannel fc = new FileInputStream(f).getChannel();
				ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
				while (fc.read(bb) != -1) {
					bb.flip();
					outChannel.write(bb);
					bb.clear();
				}
				fc.close();
			}
			System.out.println("Merged Files Successfully! ");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (outChannel != null) {
					outChannel.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String Outputfile = input.next();
		String Dirpath = input.next();
		mergeFiles(Outputfile, Dirpath);

	}

}
