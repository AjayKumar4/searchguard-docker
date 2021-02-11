package docker.searchguard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Searchguard {
    private static Map<String, String> esSgVersionMap = new HashMap<>();
    private static Map<String, String> esSgUrlPathMap = new HashMap<>();
    private static final String url = "https://docs.search-guard.com/latest/search-guard-versions";

    Searchguard() throws IOException {
        String status;
        String esVersion;
        String sgVersion;
        String sgUrlPath;

        Document doc = Jsoup.connect(url).get();
        Elements tableElements = doc.select("table");

        for(Element tableElement:tableElements){
            Elements tableRowElements = tableElement.select("tbody");
            for(Element tableRowElement:tableRowElements){
                Elements rowItems = tableRowElement.select("tr");
                for(Element rowItem:rowItems){
                    status = rowItem.children().last().text();
                    esVersion = rowItem.children().first().text();
                    sgVersion = rowItem.children().first().nextElementSibling().text();
                    sgUrlPath = rowItem.children().first().nextElementSibling().children().attr("href").trim();

                    if(status.equalsIgnoreCase("active")){
                        esSgVersionMap.put(esVersion, sgVersion);
                        esSgUrlPathMap.put(esVersion, sgUrlPath);
                    }
                }
            }
        }
    }

    public static String getSearchGuardVersion(String esVersion) throws IOException {
        if(esSgVersionMap.isEmpty()) new Searchguard();
        return esSgVersionMap.get(esVersion);
    }

    public static String getSearchGuardUrlPath(String esVersion) throws IOException {
        if(esSgUrlPathMap.isEmpty()) new Searchguard();
        return esSgUrlPathMap.get(esVersion);
    }
}