package ec.edu.ups.sd64.AppServicios.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.ejb.Stateless;

@Stateless
public class EmailService {
    private static final String EMAIL_SERVICE_URL = "http://34.170.36.187:5000/send-email";

    public void sendEmail(String to, String subject, String body){
		try {
			URL url = new URL(EMAIL_SERVICE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json; utf-8");
	        conn.setDoOutput(true);
	        String jsonInputString = String.format("{\"to\": \"%s\", \"subject\": \"%s\", \"body\": \"%s\"}", to, subject, body);
	        try (OutputStream os = conn.getOutputStream()) {
	            byte[] input = jsonInputString.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }
	        int code = conn.getResponseCode();
	        if (code == 200) {
	            System.out.println("Email sent successfully!" + "Enviado a " + to);
	        } else {
	            System.out.println("Failed to send email. Response code: " + code);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}