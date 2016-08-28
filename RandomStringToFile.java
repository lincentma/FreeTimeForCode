package com.multithread;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomStringToFile {

	// 判断目录路径是否合法
	public static boolean TestFilePath(String dirpath) {
		boolean result = false;
		try {
			File dirname = new File(dirpath);
			if (dirname.isDirectory()) {
				System.out.println("DirPath Already Exists!");
				result = true;
			} else { // Ŀ¼������
				if (dirname.mkdirs()) {
					System.out.println("DirPath Create Successfully!");
					result = true;
				} else {
					System.out.println("DirPath Create Fail!");
					result = false;
				}
			}
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
		return result;
	}

	// 产生随机字符串
	public static String getRandomString(int length) {
		String string = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < length; i++) {
			sb.append(string.charAt((int) Math.round(Math.random() * (len - 1))));
		}
		return sb.toString();

	}

	// 写入文件
	public static void CreateFile(String dirpath, int filenum) {
		String[] num = new String[filenum];
		for (int i = 0; i < filenum; i++) {
			num[i] = getRandomString(3);
			String filepath = dirpath + "\\" + num[i] + ".txt";
			File file = new File(filepath);

			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);

				// 文件行数
				int rownum = (int) (1 + Math.random() * 1000);
				for (int j = 0; j < rownum; j++) {
					// 每一行随机字符串的长度
					int rownum2 = (int) (1 + Math.random() * 100);
					String filetext = getRandomString(rownum2);
					bw.write(filetext);
					bw.newLine();
				}
				bw.flush();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		String path = input.next();
		int filenum = input.nextInt();
		if (TestFilePath(path)) {
			CreateFile(path, filenum);
			System.out.println("Files Create Successfully!");
		}

	}

}
