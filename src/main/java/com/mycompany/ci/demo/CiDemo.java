import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CiDemo {

    private static final String API_KEY = "YOUR_TMDB_API_KEY";  // 여기에 TMDB API Key를 넣으세요.
    private static final String API_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

    // 캐시 저장소 (메모리 캐싱)
    private static Map<String, String> cache = new HashMap<>();

    public static void main(String[] args) {
        // TMDB API 요청 성능 측정
        long directRequestTime = measureApiRequestPerformance(false);
        // 캐시된 요청 성능 측정
        long cachedRequestTime = measureApiRequestPerformance(true);

        // 성능 비교 출력
        System.out.println("직접 API 요청 시간: " + directRequestTime + " ns");
        System.out.println("캐시된 요청 시간: " + cachedRequestTime + " ns");

        if (directRequestTime > cachedRequestTime) {
            System.out.println("캐시된 요청이 더 빠릅니다.");
        } else if (directRequestTime < cachedRequestTime) {
            System.out.println("직접 API 요청이 더 빠릅니다.");
        } else {
            System.out.println("두 요청의 속도가 같습니다.");
        }
    }

    private static long measureApiRequestPerformance(boolean useCache) {
        long startTime = System.nanoTime();

        String response = null;
        if (useCache && cache.containsKey(API_URL)) {
            response = cache.get(API_URL);
            System.out.println("캐시된 데이터 사용");
        } else {
            response = sendApiRequest(API_URL);
            if (useCache) {
                cache.put(API_URL, response);
                System.out.println("API 응답 캐시 저장");
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static String sendApiRequest(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
