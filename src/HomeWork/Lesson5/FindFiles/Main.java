package HomeWork.Lesson5.FindFiles;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

class Main {

	static class MyFileFilter implements FilenameFilter {
		private ArrayList<String> ext;

		public MyFileFilter(String...ext) {
            this.ext = new ArrayList<String>();
            for (int i = 0; i < ext.length; i++) {
                this.ext.add(ext[i]);
            }
		}

		public boolean accept(File dir, String name) {
            for (String s : ext) {
                if (name.endsWith(s)) {
                    return true;
                }
            }
			return false;
		}
	}

	private static void findFiles(String srcPath, ArrayList<String> list, String...ext) throws IOException {
		File dir = new File(srcPath);
        File[] files = dir.listFiles(new MyFileFilter(ext));
        for (int j = 0; j < files.length; j++) {
            list.add(srcPath + files[j].getName());
        }
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			findFiles("c:\\", list, ".txt", ".docx");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String s : list)
			System.out.println(s);
	}

}