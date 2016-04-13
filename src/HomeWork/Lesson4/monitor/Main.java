package HomeWork.Lesson4.monitor;

class Main {
	public static void main(String[] args) {
		Monitor m = new Monitor(new FileEvent(), "c:\\test\\1.txt", "c:\\test\\2.txt", "c:\\test\\3.txt");
		m.startFileMonitoring();

		MonitorCatalogue m2 = new MonitorCatalogue(new FileEvent(), "txt", "c:\\test1\\", "c:\\test2\\", "c:\\test3\\");
        m2.startCatalogueMonitoring();
	}
}