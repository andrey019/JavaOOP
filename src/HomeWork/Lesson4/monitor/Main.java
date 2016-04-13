package HomeWork.Lesson4.monitor;

class Main {
	public static void main(String[] args) {
		Monitor m = new Monitor(new FileEvent(), "c:\\test\\1.txt", "c:\\test\\2.txt");
		//m.startFileMonitoring();

		Monitor m2 = new Monitor("txt", new FileEvent(), "c:\\test\\");
        m2.startCatalogueMonitoring();
	}
}