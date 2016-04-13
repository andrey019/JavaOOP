package HomeWork.Lesson4.monitor;

import java.io.File;
import java.util.Date;

/**
 * Created by andrey on 13.04.2016.
 */
class MonitorCatalogue {
    private Monitor monitor;
    private ExtensionFilter extFilter;

    public MonitorCatalogue(IFileEvent event, String filterWord, String...path) {
        monitor = new Monitor(event, path);
        extFilter = new ExtensionFilter(filterWord);
    }

    public void startCatalogueMonitoring() {
        File[] catalogue = new File[monitor.getPath().length];
        for (int i = 0; i < monitor.getPath().length; i++){
            catalogue[i] = new File(monitor.getPath()[i]);
        }

        String[][] shownFile = new String[monitor.getPath().length][1];
        while (true){
            for (int i = 0; i < monitor.getPath().length; i++){
                if (catalogue[i].exists()){
                    int filesAmount = catalogue[i].list(extFilter).length;
                    shownFile[i][0] = " ";
                    if (filesAmount != 0) {
                        String[] fileList = catalogue[i].list(extFilter);
                        for (int j = 0; j < fileList.length; j++){
                            boolean alreadyShown = false;
                            int shownFileLenght = shownFile[i].length;
                            for (int k = 0; k < shownFileLenght; k++) {
                                if (shownFile[i][k].equalsIgnoreCase(fileList[j])) {
                                    alreadyShown = true;
                                }
                            }
                            if (!alreadyShown) {
                                String[] tempArr = new String[shownFile[i].length + 1];
                                System.arraycopy(shownFile[i], 0, tempArr, 0, tempArr.length - 1);
                                tempArr[shownFile[i].length] = fileList[j];
                                shownFile[i] = tempArr;
                                Date date = new Date();
                                monitor.getEvent().onFileAdded(monitor.getPath()[i] + fileList[j], date.toString());
                            }
                        }
                    }
                }
            }
            System.out.println("Waiting...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
        }
    }
}
