package test.myapplication2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by yangsu on 2017-11-12.
 */

public class SearchApi {

   // private static String Client_id = "heHsncCIBzkzz827TgOc";
   // private static String Client_secret = "HDClOaPq1n";

    public static String searchBlog(String searchInput) {
       // String clientId = Client_id;//애플리케이션 클라이언트 아이디값";
       // String clientSecret = Client_secret;//애플리케이션 클라이언트 시크릿값";
        try {
             String search = URLEncoder.encode(searchInput, "UTF-8");
             String apiURL = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query=" + search;
             return apiURL;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
