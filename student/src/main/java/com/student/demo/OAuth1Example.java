package com.student.demo;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OAuth1Example {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        // Include the oauth_consumer_key parameter in requestBodyData
        String requestBodyData = "oauth_consumer_key=LTE6Y2xpZW50MjcwNzoyNzA3&" +
            "roles=Instructor" + "&lis_person_name_given=76135359-037c-49e1-ad25-44c789a66772" +
            "&lis_person_name_family=0f405c29-988e-41b7-b8f8-c594fb9db03a" +
            "&lis_person_contact_email_primary=techsupport54@carnegie.com" +
            "&lti_version=LTI-1p0" +
            "&lti_message_type=basic-lti-launch-request" +
            "&custom_bookid=14341115" +
            "&user_id=79b28ea5-02f7-4142-b74f-ab230e40f2cb";

        RequestBody body = RequestBody.create(mediaType, requestBodyData);

        String consumerKey = "LTE6Y2xpZW50MjcwNzoyNzA3"; // oauth_consumer_key
        String consumerSecret = "1ee69c71bcbe7f0b460dd4bc764477c903d7a556";

        // Generate a unique nonce and timestamp
        Random rand = new Random();
        String nonce = Long.toString(rand.nextLong());
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);

        // Generate the OAuth signature
        String signature = generateOAuthSignature(consumerKey, consumerSecret, timestamp, nonce, requestBodyData);

        // Create the Authorization header
        String authorizationHeader = "OAuth " + "oauth_consumer_key=\"" + consumerKey + "\","
            + "oauth_signature_method=\"HMAC-SHA1\"," + "oauth_timestamp=\"" + timestamp + "\","
            + "oauth_nonce=\"" + nonce + "\","
            + "oauth_version=\"1.0\"," + "oauth_signature=\"" + signature + "\"";

        Request request = new Request.Builder()
            .url("https://prodlti.kitaboo.com/lti/launch?oauth_consumer_key=LTE6Y2xpZW50MjcwNzoyNzA3") // Remove the oauth_consumer_key from the URL
            .method("POST", body)
            .addHeader("User-Agent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36")
            .addHeader("Authorization", authorizationHeader)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Cookie", "JSESSIONID=3606254CE7A66E55775A3B666C507955")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Request failed: " + response);
            }
            String string = response.body().string();
            System.out.println(string);
        }
    }

    private static String generateOAuthSignature(String consumerKey, String consumerSecret, String timestamp,
        String nonce, String requestBodyData) {
        try {
            String baseString = "POST&" + "https%3A%2F%2Fprodlti.kitaboo.com%2Flti%2Flaunch&" + // URL-encoded request URL
                "oauth_consumer_key=" + consumerKey + "&" + "oauth_nonce=" + nonce + "&"
                + "oauth_signature_method=HMAC-SHA1&" + "oauth_timestamp=" + timestamp + "&" + "oauth_version=1.0&"
                + requestBodyData; // Include the request body

            String keyString = consumerSecret + "&"; // Append '&' to the consumer secret
            SecretKeySpec keySpec = new SecretKeySpec(keyString.getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);
            byte[] result = mac.doFinal(baseString.getBytes("UTF-8"));

            // Encode the result in Base64 and return it as the OAuth signature
            return new String(Base64.getEncoder().encode(result));
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return null;
        }
    }
}
           