package HomeWork.Lesson11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

class blank {
    public static void main(String[] args) {
        String hostname = "prog.kiev.ua";
        try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            System.out.println(inetAddress.getHostAddress() + ", " + inetAddress.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL("http://di.fm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000];
            StringBuilder sb = new StringBuilder();
            int r;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
            System.out.println(sb.toString());
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //http.disconnect();
        }
    }
}
