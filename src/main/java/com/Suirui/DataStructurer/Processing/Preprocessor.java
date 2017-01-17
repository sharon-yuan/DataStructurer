package com.Suirui.DataStructurer.Processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.Suirui.DataStructurer.WordSeg.Executor;
import com.Suirui.DataStructurer.WordSeg.WordFrequencyStatistics;

/*
 * 
 * 
 * @Author: Sharon-Y
 * 
 * */
public class Preprocessor {

	/*
	 * process the sample data(target and noisy) into datafile for knn
	 * 
	 * @Param targetDirPath dir of the target files
	 * 
	 * @Param noiseDirPath dir of the noise files
	 * 
	 */
	public static void contrastDataSet(String targetDirPath, String noiseDirPath) {
		// word2TF(targetDirPath);
		// word2TF(noiseDirPath);

		// chooseKeyWord
		choseKeyWords.executor(DirController.DirChanger(targetDirPath, "-TF"),
				DirController.DirChanger(noiseDirPath, "-TF"));

		// get data files=>"data"
		TF2Vector.WholeDirExector(DirController.DirChanger(targetDirPath, "-TF"), "targetResult", 1);
		TF2Vector.WholeDirExector(DirController.DirChanger(noiseDirPath, "-TF"), "noiseResult", 0);
		fileMerge("targetResult", "noiseResult");
	}

	private static void fileMerge(String filePath1, String filePath2) {
		File aFile = new File("data");
		String line;
		try {
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aFile), "utf-8"));

			BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath1)), "utf-8"));
			while ((line = input.readLine()) != null) {
				String aStringlist[] = line.split(" ");
				double sum = 0.0;
				for (int i = 0; i < aStringlist.length - 1; i++) {
					sum += Double.valueOf(aStringlist[i]);
				}
				if (sum != 0.0)
					output.write(line + '\n');
			}
			input.close();
			int temp1 = 0;
			BufferedReader input2 = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(filePath2)), "utf-8"));
			while ((line = input2.readLine()) != null) {
				String aStringlist[] = line.split(" ");
				double sum = 0.0;
				for (int i = 0; i < aStringlist.length - 1; i++) {
					sum += Double.valueOf(aStringlist[i]);
				}
				if (sum == 0.0 && temp1 == 0) {
					temp1 = 1;
					output.write(line + '\n');
				} else if (sum != 0.0)
					output.write(line + '\n');
			}
			input2.close();
			output.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void word2TF(String inputDir) {

		File input = new File(inputDir);
		if (!input.exists()) {
			System.out.println("target-dir-path error!   inputDir is: " + inputDir);
			return;
		}
		System.out.println("dir file seg");
		//Executor.dirsWordSeg(DirController.DirRoot(inputDir),DirController.DirChanger(inputDir, "-seg"));
		// file merged
		// get Mergerd.txt & Merged-Seg.txt
		 System.out.println("files merging dir:" + inputDir);
		fileMerge.MergeSegDir(DirController.DirChanger(inputDir, "-seg"));
		System.out.println("files merged! dir:" + inputDir);
		/*
		 * WordFrequencyStatistics.executor(DirController.DirChanger(inputDir,
		 * "-seg") + "Merged", DirController.DirChanger(inputDir, "-seg") +
		 * "Merged-seg");
		 */
		// get TF
		File dir = new File(DirController.DirChanger(inputDir, "-seg"));
		String outDir = DirController.DirChanger(inputDir, "-TF");
		File outDirFile = new File(outDir);
		if (!outDirFile.isDirectory())
			outDirFile.mkdirs();
		File[] files = dir.listFiles();
		System.out.println(dir.getAbsolutePath()+" "+files.length);
		for (File tempF : files) {
			//System.out.println(tempF.getAbsolutePath());
			File tempTFF=new File(outDirFile+tempF.getName());
			if (!tempTFF.exists())
				freqToTF.f2TF(tempF.getPath(), outDir + tempF.getName());
		}

	}

	public static void main(String[] args) {
		// String targetFilePath = "E:/data/china/ChinaForYes/", noiseFilePath =
		// "E:/data/china/ChinaForNo/";
		word2TF("E:/data/china/textsQ34/");
		// contrastDataSet(targetFilePath, noiseFilePath);
		/*
		 * String inputDir="E:/data/test/data/"; // get TF File dir = new
		 * File(DirController.DirChanger(inputDir, "-seg")); String outDir =
		 * DirController.DirChanger(inputDir, "-TF"); File outDirFile = new
		 * File(outDir); if (!outDirFile.isDirectory()) outDirFile.mkdirs();
		 * File[] files = dir.listFiles(); for (File tempF : files) {
		 * 
		 * freqToTF.f2TF(tempF.getPath(), outDir + tempF.getName()); }
		 */
	}

}
