package padel_web_test;

import static org.junit.Assert.assertEquals;
import static padel_web_test.Login.timeToSleep;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class EncerrarInscricaoTest {
	 
    public static WebDriver driver;
    public static final String TESTLINK_KEY = "7ec1c9f07277075682b75bd510a67273";
    public static final String URL = "http://localhost:8080/";
    public static final String USUARIO = "Testador";
    public static final String SENHA = "teste";

    public EncerrarInscricaoTest() {}

    @Before
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    
    @Test
    public void encerrarInscricoes() throws Exception {
        String nomeMetodo = "Encerrar Inscricoes";
        try {
            //Logando no sistema
            Login.autenticar(driver, USUARIO, SENHA, URL);
            Thread.sleep(timeToSleep);
            acessarTorneio();
            //Clica Encerrar Incricoes
            driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/a[1]")).click();
            Thread.sleep(timeToSleep); 
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    private void acessarTorneio() throws InterruptedException {
    	//Entrando Gerenciar Circuitos
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/form[1]/button[1]")).click();
        Thread.sleep(timeToSleep);
        //Entrando CircuitoTest
        driver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/span[1]")).click();
        Thread.sleep(timeToSleep);
        //Entrando TorneioTest
        driver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/span[1]")).click();
        Thread.sleep(timeToSleep);               
    }
    
    @After
    //Saindo apos a aplicacao do teste
    public void sair() {
        try {
            Thread.sleep(timeToSleep + 1000);
            driver.quit();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
}
