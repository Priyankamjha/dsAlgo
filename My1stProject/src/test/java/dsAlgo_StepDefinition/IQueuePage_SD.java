package dsAlgo_StepDefinition;

import static dsAlgo_Constants.DsAlgoConstant.XL_DATA_FILE_PATH;
import static dsAlgo_Constants.DsAlgoConstant.queueUrl;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dsAlgo_Driverfactory.DriverFactory;
import dsAlgo_PageObject.BHomePage;
import dsAlgo_PageObject.IQueuePage;
import dsAlgo_Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IQueuePage_SD {

    private static final Logger logger = LogManager.getLogger(IQueuePage_SD.class);

	private BHomePage homePage = new BHomePage(DriverFactory.getDriver());
	private IQueuePage qPage = new IQueuePage(DriverFactory.getDriver());

	String expectedResult = null;
	
	
	@Given("The user is on the Queue page after logged in")
	public void the_user_is_on_the_queue_page_after_logged_in() {
		qPage.navigateToQueuePage();
	}

	@When("The user clicks {string} link")
	public void the_user_clicks_link(String string) {
		qPage.clickLink(string);
	}

	@Then("The user directed to {string} page")
	public void the_user_directed_to_page(String string) {
		String title = DriverFactory.getDriver().getTitle();
		System.out.println("Title = " + title);
		assertEquals(title, string);
	}

	@Given("The user is on homepage")                                 
	public void the_user_is_on_homepage() {
		homePage.homepage();
	}

	@When("The user clicks the queue item from the drop down menu")
	public void the_user_clicks_the_queue_item_from_the_drop_down_menu() {
		qPage.clickOnQueueItem();
	}

	@Then("The user be directed to Queue Data Structure Page")
	public void the_user_be_directed_to_queue_data_structure_page() {
		// modification on feature flile
		String actualURL = DriverFactory.getDriver().getCurrentUrl();
		System.out.println("actualURL  = " + actualURL);
		assertEquals(actualURL, queueUrl);
	}

	@Given("The user is in a {string} page for queue having an tryEditor with a Run button to test")
	public void the_user_is_in_a_page_for_queue_having_an_try_editor_with_a_run_button_to_test(String string) {
		qPage.navigateToQueuePage();                  //
		qPage.clickLink(string);
		qPage.clickOnTryHereLink();
	}
	
	@When("The user enter valid python code in tryEditor for queue from sheet {string} and {int}")
	public void the_user_enter_valid_python_code_in_try_editor_for_queue_from_sheet_and(String sheetName, Integer rowNumber) {
		ExcelReader excel = new ExcelReader();
		List<Map<String, String>> list = excel.getData(XL_DATA_FILE_PATH, sheetName);
		Map<String, String> dataMap = list.get(rowNumber);
		String pythonCode = dataMap.get("pythonCode");
		expectedResult = dataMap.get("Result");
		System.out.println("expected = " + expectedResult);
		qPage.sendPythonCode(pythonCode);
	}

	@When("The user clicks on run button for queue")
	public void the_user_clicks_on_run_button_for_queue() {
		qPage.clickOnRunButton();
		logger.info("Ended Test");
	}

	@Then("The user should be presented with Run result for queue")
	public void the_user_should_be_presented_with_run_result_for_queue() {
		String actualResult = qPage.getActualResult();
		System.out.println("actualResult = " + actualResult);
		assertEquals(actualResult, expectedResult);
	}

	@When("The user enter python code with invalid syntax in tryEditor  for queue from sheet {string} and {int}")
	public void the_user_enter_python_code_with_invalid_syntax_in_try_editor_for_queue_from_sheet_and(String sheetName, Integer rowNumber) {
		ExcelReader excel = new ExcelReader();
		List<Map<String, String>> list = excel.getData(XL_DATA_FILE_PATH, sheetName);
		Map<String, String> dataMap = list.get(rowNumber);
		String pythonCode = dataMap.get("pythonCode");
		expectedResult = dataMap.get("Result");
		qPage.sendPythonCode(pythonCode);
	}

	@Then("The user should be presented with error message for queue")
	public void the_user_should_be_presented_with_error_message_for_queue() {
		String errorMessage = qPage.getAlertMessage();
		System.out.println("errorMessage = " + errorMessage);
		System.out.println("driver.getTitle() = " + DriverFactory.getDriver().getTitle());
		assertEquals(errorMessage, expectedResult);
	}	
		
	@Given("The user is on Editor page and navigates to QueueOp page")
	public void the_user_is_in_editor_page_and_navigates_to_queue_op_page() {
		qPage.navigateToQueuePage();
		qPage.navigateToQueueOperationsLink();

	}

	@When("the user clicks on Practice Questions")
	public void the_user_clicks_on_practice_questions() {
		qPage.clickOnPracticeQuestionsLink();
	}

	@Then("The user is directed to Practice page")
	public void the_user_is_directed_to_practice_page() {
		qPage.navigateToPracticeQuestionsLink();
	}

}
