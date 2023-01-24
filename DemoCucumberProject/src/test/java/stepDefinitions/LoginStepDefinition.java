package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginStepDefinition {
        WebDriver driver;
        WebDriverWait wait;

        @Before
        public void beforeScenario(){
                driver=new ChromeDriver();
                wait=new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://www.saucedemo.com/");
        }

        @Given("I am on Login Page")
        public void iAmOnLoginPage() {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        }

        @When("I enter Email and password and hit submit button")
        public void iEnterEmailAndPassword() {
                driver.findElement(By.id("user-name")).sendKeys("standard_user");
                driver.findElement(By.id("password")).sendKeys("secret_sauce");
                driver.findElement(By.id("login-button")).click();
        }

        @Then("the user should be logged in")
        public void theSubmitButtonWillBeActive() {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.inventory_item")));
                Assert.assertTrue(driver.findElements(By.cssSelector("div.inventory_item")).size()>=1);
        }


        @When("I enter Email and password and hit submit button using data table")
        public void iEnterEmailAndPasswordAndHitSubmitButtonUsingDataTable(DataTable dataTable) {
                List<List<String>> data=dataTable.asLists(String.class);
                for (List<String> column:data){
                        driver.findElement(By.id("user-name")).sendKeys(column.get(0));
                        driver.findElement(By.id("password")).sendKeys(column.get(1));
                        driver.findElement(By.id("login-button")).click();
                }
        }

        @Then("the user should not be logged in")
        public void theUserShouldNotBeLoggedIn() {
                Assert.assertTrue(driver.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed());
        }

        @After
        public void afterScenario(){
                driver.quit();
        }

        @When("I enter email(.+)$")
        public void iEnterEmailUser(String user) {

                driver.findElement(By.id("user-name")).sendKeys(user.trim());
        }

        @And("password and hit submit button multiple times (.+)$")
        public void passwordAndHitSubmitButtonMultipleTimesPassword(String password) {
                System.out.println(password);
                driver.findElement(By.id("password")).sendKeys(password.trim());
                driver.findElement(By.id("login-button")).click();
        }
}
