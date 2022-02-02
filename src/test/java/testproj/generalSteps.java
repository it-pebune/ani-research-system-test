package testproj;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class generalSteps {
    private WebDriver driver;
    String given_url_global;
    static String retrieve_properties_variable(String variable_name) {

        String propFileName = "/config/cucumber.properties";
        Properties prop2 = null;
        try (InputStream input2 = generalSteps.class.getResourceAsStream(propFileName)) {
            prop2 = new Properties();
            prop2.load(input2);
            return prop2.getProperty(variable_name);
/*        try (InputStream input = new FileInputStream("src/test/resources/config/cucumber.properties")) {

            prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop2.getProperty(variable_name);
    }


    @Before
    public void createDriver() {

        //create object of chrome options
        ChromeOptions options = new ChromeOptions();

        //add the headless argument
        //options.addArguments("headless");
        options.addArguments("enable-javascript=1");
        options = new ChromeOptions().setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @Given("I start browser")
    public void i_start_browser() {
        driver.get("about:blank");
    }


    @When("I open the {string} url")
    public void i_open_url(String given_url) {
        System.out.format("Variable name: %s\n", given_url);
        //given_url_global = retrieve_properties_variable(given_url);
        given_url_global = given_url;
        System.out.format("URL to open: %s\n", given_url);
        driver.get(given_url);
    }

    @Then("The page loads successfully and HTTP success code is returned")
    public void i_check_for_success_http_code() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        HttpURLConnection c=
                (HttpURLConnection)new
                        URL(given_url_global)
                        .openConnection();
        // set the HEAD request with setRequestMethod
        c.setRequestMethod("HEAD");
        // connection started and get response code
        c.connect();
        int r = c.getResponseCode();
        c.disconnect();
        assertEquals(r, 200);
        System.out.println("Http response code: " + r);
    }

    @Then("Title displayed is the {string}")
    public void check_title(String expected_title) {
        //assertEquals(retrieve_properties_variable(expected_title), driver.getTitle());
        assertEquals(expected_title, driver.getTitle());
    }

    @Then("{string} element is displayed find by {string}")
    public void check_login_button(String expected_element_id, String item_identifier) {
        switch (item_identifier) {
            case "ID" ->

                    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    //wait.until(ExpectedConditions.presenceOfElementLocated(By.id(retrieve_properties_variable(expected_element_id))));
                    assertTrue(driver.findElement(By.id(retrieve_properties_variable(expected_element_id))).isDisplayed());
            case "xpath" -> assertTrue(driver.findElement(By.xpath(retrieve_properties_variable(expected_element_id))).isDisplayed());

            case "class" -> assertTrue(driver.findElement(By.className(expected_element_id)).isDisplayed());
        }

    }

    @Then("I click on the {string} button found by {string}")
    public void i_click_the_id_button(String expected_button_identifier, String item_identifier) {
        System.out.format("Starting to identify element %s by identifier %s\n", expected_button_identifier, item_identifier);

        switch (item_identifier) {
            case "ID":
                if (driver.findElement(By.id(retrieve_properties_variable(expected_button_identifier))).isDisplayed()) {
                    System.out.println("Button found by id...");
                    driver.findElement(By.id(retrieve_properties_variable(expected_button_identifier))).click();
                    System.out.println("Button found by id...clicking...");
                }
                break;
            case "xpath":

                if (driver.findElement(By.xpath(retrieve_properties_variable(expected_button_identifier))).isDisplayed()) {
                    System.out.println("Button found by xpath...");
                    driver.findElement(By.xpath(retrieve_properties_variable(expected_button_identifier))).click();
                    System.out.println("Button found by xpath...clicking...");
                }
                break;
            case "class":

                if (driver.findElement(By.className(expected_button_identifier)).isDisplayed()) {
                    System.out.println("Button found by class...");
                    driver.findElement(By.className(expected_button_identifier)).click();
                    System.out.println("Button found by class...clicking...");
                }
                break;
            default:
                System.out.println("Identifier type not found...");
                break;
        }

    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
