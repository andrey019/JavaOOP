package HomeWork.Lesson11.Task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class Task {
    private static StringBuilder getHtml(String hostName) {
        try {
            URL url = new URL(hostName);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            System.out.println("Getting html from: " + hostName);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLCon.getInputStream()));
                StringBuilder html = new StringBuilder();
                char[] buf = new char[1024];
                int amountRead = 0;
                do {
                    if ((amountRead = bufferedReader.read(buf)) > 0) {
                        html.append(new String(buf, 0, amountRead));
                    }
                } while (amountRead > 0);
                return html;
            } catch (Exception e) {
                System.out.println(hostName + " error!");
                httpURLCon.disconnect();
                return null;
            } finally {
                httpURLCon.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ArrayList<String> getLinks(StringBuilder html) {
        if (html != null) {
            ArrayList<String> links = new ArrayList<>();
            String lookStart = "a href=";
            String lookEnd = "\"";
            int startPosition = 0;
            int endPosition = 0;
            String tempString;
            while(startPosition != -1) {
                startPosition = html.indexOf(lookStart, startPosition);
                if (startPosition != -1) {
                    startPosition++;
                    endPosition = html.indexOf(lookEnd, startPosition + 10);
                    tempString = html.substring(startPosition + lookStart.length(), endPosition);
                    if (tempString.contains("http:") && !links.contains(tempString)) {
                        links.add(tempString);
                    }
                }
            }
            return links;
        }
        return null;
    }

    private static void linksOnScreen(ArrayList<String> links) {
        if (links != null) {
            if (links.isEmpty()) {
                System.out.println("No links found!");
            } else {
                for (String string : links) {
                    System.out.println(string);
                }
                System.out.println();
            }
        }
    }

    private static void htmlOnDisk(String hostName, String saveDir) {
        if (hostName.charAt(hostName.length() - 1) == '/') {
            hostName = hostName.substring(0, hostName.length() - 1);
        }
        File file;
        try {
            if (hostName.lastIndexOf("/") == 6) {
                File dir = new File(saveDir);
                dir.mkdirs();
                file = new File(saveDir + "\\" + hostName.substring(7, hostName.length()) + ".html");
                file.createNewFile();
            } else {
                int position = hostName.lastIndexOf("/");
                File dir = new File(saveDir + "\\" + hostName.substring(7, position));
                dir.mkdirs();
                file = new File(dir.getCanonicalPath() + "\\" +
                        hostName.substring(position + 1, hostName.length()) + ".html");
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(getHtml(hostName));
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void task1(String hostName) {
        StringBuilder html = getHtml(hostName);
        ArrayList<String> links = getLinks(html);
        linksOnScreen(links);
    }

    private static void task2(String hostName, String saveDir) {
        StringBuilder html = getHtml(hostName);
        ArrayList<String> links = getLinks(html);
        if (links != null) {
            for (String string : links) {
                htmlOnDisk(string, saveDir);
            }
        }
    }

    public static void main(String[] args) {
        String hostName = "http://di.fm";
        String saveDir = "c:\\test\\html\\di.fm";
        task1(hostName);
        task2(hostName, saveDir);
    }
}
