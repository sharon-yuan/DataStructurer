package com.Suirui.DataStructurer.Processing;

public class PreProcessorForGradeAttri {
	public static void proc(){
		String[] filedNames = { "grade", "renew", "scope", 
				"location",
				//"aptitude" 
				};
		String Path = "E:/data/china/gradeTitle/";
		int[] filedScope = { 5, 2, 4, 3, 3 };
		for (int i = 0; i < filedNames.length; i++)
			for (int j = 1; j <= filedScope[i]; j++) {
		Preprocessor.word2TF(Path + filedNames[i]+"Title" + "-" + j + "/");}
	}

}
