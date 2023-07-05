import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }
    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    void shouldSignCorrect() {

        SelenideElement form = $("[form]");
        form.$("[data-test-id=town] input").setValue("Казань");
        form.$("[data-test-id=date] input").setValue("05.07.2023");
        form.$("[data-test-id=name] input").setValue("Петр Иванов");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("agreement").click();
        form.$("button_book").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Успешно! Встреча успешно забронирована на 05.07.2023"));

    }
}
