package com.StockExchange.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StockPrice {
    public static final Pattern STOCK_PATTERN = Pattern.compile("\"priceChange\" content=\"([^\\\"]+)");

    @Autowired
    ObjectMapper om;

    public float getStockChange(String stockName) throws IOException {
        String stockSymbol = getStockSymbol(stockName);
        float change = getStockPrecantageChange(stockSymbol);

        return change;
    }
    public float getStockPrecantageChange(String stockSymbol) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.marketwatch.com/investing/stock/" + stockSymbol +"?mod=search_symbol")
                .method("GET", null)
                .addHeader("Cookie", "ab_uuid=b2fddec7-4d15-41cc-a3e3-c7746600a491; gdprApplies=false; mw_loc=%7B%22Region%22%3A%22%22%2C%22Country%22%3A%22IL%22%2C%22Continent%22%3A%22AS%22%2C%22ApplicablePrivacy%22%3A0%7D")
                .build();
        Response response = client.newCall(request).execute();

        Matcher matcher = STOCK_PATTERN.matcher(response.body().string());
        matcher.find();
        float ret = Float.parseFloat(matcher.group(1));
        return ret;
    }
    public String getStockSymbol(String stockName) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url("https://stockanalysis.com/api/search?q=" + stockName)
                .method("GET", null)
                .addHeader("authority", "stockanalysis.com")
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "en-US,en;q=0.9,he;q=0.8")
                .addHeader("cookie", "usprivacy=1---; _pbjs_userid_consent_data=3524755945110770; _lr_retry_request=true; _lr_env_src_ats=false; panoramaId_expiry=1683280819551; _cc_id=b7fd58c1c34b3e3514ec84e029ee6a5; panoramaId=3e25ba5a86ff1574a188941f6b2316d539386e26f2abb34c25f5e0f6d7740044; __gads=ID=5a884b26a655c8cd:T=1682676020:S=ALNI_MbbdRYIceUQODUH-hH4IoQPwurm7Q; __gpi=UID=00000c0c890b8ed5:T=1682676020:RT=1682676020:S=ALNI_MZZ19t2JfigCZRYu5shKMqTIC8Zng; _gid=GA1.2.2011001452.1682676022; __qca=P0-521586962-1682676022115; _ga=GA1.2.663029563.1682676018; cto_bundle=nx4dZF8zWk9CSWFaWnVYWUFQYjJCaXl2OUtORkd6bDhtNHc2eHlKN2h1YnNIM0hIWkYwUlBjJTJCWkRocXNRZGx1TUZ5d25PMjdtZHBkRFB4S0p0UEt3YVVYYklXZkVIRXF0QUV2QmRsVVRHZWc4bW5PSGhWTlRacmJ1Qmk2ek5qdk1vTlF3RnQ5WnpYWGJnb0VsJTJGN0xrb0JxcGZMWXNVQ2F6U2VUUmRkQ3ZRaU10NXBvJTNE; IC_ViewCounter_stockanalysis.com=8; _ga_C83MWM65QF=GS1.1.1682676018.1.1.1682676199.0.0.0")
                .addHeader("referer", "https://stockanalysis.com/stocks/aapl/")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"112\", \"Google Chrome\";v=\"112\", \"Not:A-Brand\";v=\"99\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();

        String res = response.body().string();
        JsonNode node = om.readTree(res);
        return node.get("data").get(0).get("s").asText();
    }
}
