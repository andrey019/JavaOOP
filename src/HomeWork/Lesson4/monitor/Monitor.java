package HomeWork.Lesson4.monitor;
import java.io.File;
import java.util.Date;

class Monitor {
    private String[] path;
	private IFileEvent event;
    private Boolean[] created;
    private ExtensionFilter extFilter;
	
	public Monitor(IFileEvent event, String...path) {
        this.path = new String[path.length];
        this.created = new Boolean[path.length];
        for (int i = 0; i < path.length; i++){
            this.path[i] = path[i];
            this.created[i] = new Boolean(false);
        }
		this.event = event;
        extFilter = new ExtensionFilter();
	}

    public Monitor(String filterWord, IFileEvent event, String...path) {
        this.path = new String[path.length];
        this.created = new Boolean[path.length];
        for (int i = 0; i < path.length; i++){
            this.path[i] = path[i];
            this.created[i] = new Boolean(false);
        }
        this.event = event;
        extFilter = new ExtensionFilter(filterWord);
    }
	
	public void startFileMonitoring() {
        int fileCounter = this.path.length;
        File[] file = new File[fileCounter];
        for (int i = 0; i < fileCounter; i++){
            file[i] = new File(path[i]);
        }

		while (true) {
			for (int i = 0; i < file.length; i++){
                if (file[i].exists() && file[i].isFile() && !created[i]) {
                    Date date = new Date(file[i].lastModified());
                    event.onFileAdded(path[i], date.toString());
                    created[i] = true;
                    fileCounter--;
                }
            }

			if (fileCounter == 0){
                System.exit(0);
            }

            System.out.println("Waiting...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
	}

    public void startCatalogueMonitoring() {
        File[] catalogue = new File[path.length];
        for (int i = 0; i < path.length; i++){
            catalogue[i] = new File(path[i]);
        }

        String[][] shownFile = new String[path.length][1];
        while (true){
            for (int i = 0; i < path.length; i++){
                int filesAmount = catalogue[i].list(extFilter).length;
                shownFile[i][0] = " ";
                //try {
                    if (filesAmount != 0) {
                        String[] fileList = catalogue[i].list(extFilter);
                        for (int j = 0; j < fileList.length; j++){

                            for (int k = 0; k < shownFile[i].length; k++) {
                                boolean alreadyShown = true;
                                if (shownFile[i][k].matches(fileList[j])) {
                                    alreadyShown = true;
                                } else {
                                    String[] tempArr = new String[shownFile[i].length + 1];
                                    System.arraycopy(shownFile[i], 0, tempArr, 0, tempArr.length - 1);
                                    tempArr[shownFile[i].length] = fileList[j];
                                    shownFile[i] = tempArr;
                                    alreadyShown = false;
                                }
                                // }


                                if (!alreadyShown) {
                                    Date date = new Date();
                                    event.onFileAdded("In " + path[i] + " '" + extFilter.toString()
                                            + "' file", date.toString());
                                }
                            }
                        }
                    }
               // } catch (Exception e){
               //     System.out.println("error");
               // }



            }
            System.out.println("Waiting...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
        }
    }
}
