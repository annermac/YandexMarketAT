package ru.yandex.market;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * Класс BaseTests настраивает chromeDriver для тестирования
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
public class BaseTests {
    protected WebDriver driver;

    /**
     * Метод устанавливает первоначальные настройки
     */
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * В конце выполнения тестов chromeDriver закрывается
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
