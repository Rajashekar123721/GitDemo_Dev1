package git_demo;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
public class Sample1 {
	
	
	private static int getMonthNumber(String month){
		return java.time.Month.valueOf(month.toUpperCase()).getValue(); 
	}
	
public static void main(String[] args) {
	ChromeOptions options=new ChromeOptions();
	options.addArguments("--incognito");
	
	WebDriver driver=new ChromeDriver(options);
	driver.get("https://katalon-demo-cura.herokuapp.com/");
	driver.findElement(By.id("btn-make-appointment")).click();
	driver.findElement(By.id("txt-username")).sendKeys("John Doe");
	driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
	driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
	
	driver.manage().window().maximize();
	
	Select ele=new Select(driver.findElement(By.id("combo_facility")));
	ele.selectByValue("Hongkong CURA Healthcare Center");
	
	driver.findElement(By.xpath("//label[@class='radio-inline']//*[@id='radio_program_medicaid']")).click();
	
	//open calender
	driver.findElement(By.id("txt_visit_date")).click();
	

	//driver.findElement(By.id("txt_visit_date")).click();
	
	String targetMonth="December";
	String targetYear="2015";
	String targetDay="15";
	
	while(true) {
	// Get current month-year from date picker
	String monthYear=driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText(); 
	
	
	
	String[] parts=monthYear.split(" ");
	String currentMonth=parts[0];
	String currentYear=parts[1];
	

	//Stop if target reached
	if(currentMonth.equalsIgnoreCase(targetMonth) && currentYear.equals(targetYear)) {
		break;
	}
	
	int curYear=Integer.parseInt(currentYear);
	int tarYear=Integer.parseInt(targetYear);
	//To decide direction prev or next
	if(curYear<tarYear||(curYear==tarYear && getMonthNumber(currentMonth)<getMonthNumber(targetMonth))) {
		driver.findElement(By.xpath("//th[@class='next']")).click();
	}else {
		driver.findElement(By.xpath("//th[@class='prev']")).click();
	}
	}
	 
	driver.findElement(By.xpath("//td[text()='"+ targetDay +"']")).click();
	
	driver.findElement(By.id("btn-book-appointment")).click();
	
	driver.findElement(By.xpath("//a[@class=\"btn btn-default\"]")).click();
	
	System.out.println("Appointment Booked");
	//driver.quit();
}


}
