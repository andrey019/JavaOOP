package HomeWork.Lesson4.monitor;

import java.io.File;
import java.util.Date;

class Monitor {
    private String[] path;
	private IFileEvent event;
    private Boolean[] created;
	
	public Monitor(IFileEvent event, String...path) {
        this.path = new String[path.length];
        this.created = new Boolean[path.length];
        for (int i = 0; i < path.length; i++){
            this.path[i] = path[i];
            this.created[i] = new Boolean(false);
        }
		this.event = event;
	}

    public String[] getPath() {
        return path;
    }

    public IFileEvent getEvent() {
        return event;
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
                return;
            }
            System.out.println("Waiting...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
	}
}
