package HomeWork.Lesson5.Task2;

import java.io.File;

/**
 * Created by andrey on 19.04.16.
 */
class Main {
    static void findAndShow(String srcCatalogue) {
        File catalogue = new File(srcCatalogue);
        File[] files = catalogue.listFiles();
        for (File f : files) {
            try {
                if (f.isFile()) {
                    if ( (f.getName().length() > 9) && (f.getName().charAt(1) == 'A') ) {
                        System.out.println(f.getCanonicalPath());
                    }
                } else {
                    findAndShow(f.getCanonicalPath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        findAndShow("c:\\test");
    }
}
