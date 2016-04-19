package HomeWork.Lesson5.Task2;

import java.io.File;
import java.io.FilenameFilter;


class Filter implements FilenameFilter {


    @Override
    public boolean accept(File dir, String name) {
        System.out.println(name);
        if ( (name.length() > 9) && (name.charAt(1) == 'a') ) {
            return true;
        }
        return false;
    }
}
