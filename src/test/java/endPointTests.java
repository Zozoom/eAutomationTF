import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class endPointTests {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/browsers/chromedriver.exe");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null)
            driver.quit();
    }

    /**
     * Endpoint test: Get all the planets.
     * */
    @Test
    public void getAllPlanets() {

        String response = null;
        try {
            response = callUrlWithGet("https://swapi.co/api/planets");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("String Result: \n" + response);

    }

    /**
     * Endpoint test: Get one planet.
     * */
    @Test
    public void getThePlanet() {

        String response = null;
        int planetNumber = 2;

        try {
            response = callUrlWithGet("https://swapi.co/api/planets"+"/"+planetNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("String Result: \n" + response);

    }

    /**
     * Need to be separated.endPointTests
     * */
    private String callUrlWithGet (String myUrl) throws IOException {
        URL url = new URL(myUrl);
        System.out.println("Called Url: "+myUrl);

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.addRequestProperty("User-Agent", "Chrome");
        con.setRequestMethod("GET");

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String readLine;
            StringBuffer response = new StringBuffer();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();

            return String.valueOf(response);
        }
        else{
            return String.valueOf(con.getResponseCode());
        }
    }
}
