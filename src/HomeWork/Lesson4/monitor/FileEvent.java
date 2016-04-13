package HomeWork.Lesson4.monitor;

class FileEvent implements IFileEvent {
	@Override
	public void onFileAdded(String path, String date) {
		System.out.println("[" + date + "]" + " File added: " + path);
	}
}
