import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class ApiRequestAdd {
    private static final String authorName = "Nguyen Ngoc Phuong Uyen";
    private static final String email = "phuongbinhthuan.dtn@gmail.com";
    private static final String phone = "0979635890";
    private static final String ward = "Đoàn phường Bình Thuận, Quận 7";

    @ParameterizedTest
    @MethodSource("ExcelUtils#excelDataProvider")
    public void sendAdd(String topic, String idea, String summary) {
        RestAssured.baseURI = "https://api.ytuongsangtaohcm.vn";

        // Gathering body  request because original body is way ugly, it was in one line as a string
        JSONObject company = new JSONObject();
        company.put("value", "5e69f64b364a867e50d702a2");
        company.put("label", ward);

        JSONObject subject = new JSONObject();
//        subject.put("value", "5e69fafb9db1e45d80f6fcd9"); //topic ID not needed
        subject.put("label", topic); //topic

        JSONObject requestBody = new JSONObject();
        requestBody.put("yt_id_user", "");
        requestBody.put("yt_image", "");
        requestBody.put("yt_ten", idea);
        requestBody.put("yt_tt", summary);
        requestBody.put("yt_tacgia", authorName);
        requestBody.put("yt_email", email);
        requestBody.put("yt_phone", phone);
        requestBody.put("yt_company", company);
        requestBody.put("yt_other_company", "");
        requestBody.put("yt_category", new JSONArray());
        requestBody.put("yt_subject", subject);
        requestBody.put("yt_program", new JSONArray());
        requestBody.put("yt_mssv", "");

        // send POST request
        Response response = RestAssured.given()
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "en-US,en;q=0.9")
                .header("cache-control", "no-cache")
                .header("content-type", "application/json;charset=UTF-8")
                .header("origin", "https://ytuongsangtaohcm.vn")
                .header("pragma", "no-cache")
                .header("priority", "u=1, i")
                .header("referer", "https://ytuongsangtaohcm.vn/")
                .header("sec-ch-ua", "\"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Brave\";v=\"128\"")
                .header("sec-ch-ua-mobile", "?1")
                .header("sec-ch-ua-platform", "\"Android\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("sec-gpc", "1")
                .header("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Mobile Safari/537.36")
                .body(requestBody.toString())
                .post("/api/idea/add");
        System.out.println(response.getStatusCode());
    }
}
