package ru.yandex.market;

import helpers.*;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.YandexMarketCategory;
import pages.YandexMarketMain;

import java.util.List;
import java.util.Map;

/**
 * Класс Tests тестирует работоспособность страницы содержащую товары в определенной категории
 *
 * @author Ермаченкова Анна
 * @version 1.0
 */
public class Tests extends BaseTests {
    /**
     * Метод проверяет, что правильно работает фильтр и поиск в категории товаров
     *
     * @param categoryName          название каталога
     * @param subCategoryName       название подкаталог
     * @param expectedCountProducts ожидаемое количество товаров
     * @param filterCheckbox        содержит название фильтра и его значений для чекбокса
     * @param filterRange           содержит название фильтра и его значений для текстового поля
     * @param expectedWordsInTitle  ожидаемаемые заголовки товаров
     * @param expectedPrices        ожидаемые цены
     */
    @Feature("Проверка работоспособности страницы содержащую товары в определенной категории")
    @DisplayName("Проверка, что правильно работает фильтр и поиск в категории товаров")
    @MethodSource("helpers.DataProvider#providerCheckingCategory")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    public void testCategory(String categoryName, String subCategoryName, int expectedCountProducts,
                             Map<String, List<String>> filterCheckbox, Map<String, List<String>> filterRange,
                             List<String> expectedWordsInTitle, List<Integer> expectedPrices) {

        driver.get(Properties.testsProperties.yandexMarketURL());

        YandexMarketMain yandexMarketMain = new YandexMarketMain(driver);
        yandexMarketMain.openPageCategory(categoryName, subCategoryName);

        YandexMarketCategory yandexMarketCategory = new YandexMarketCategory(driver);

        yandexMarketCategory.checkCorrectPage(subCategoryName);
        yandexMarketCategory.checkCountProductsFirstPage(expectedCountProducts);
        yandexMarketCategory.insertValueInInputRange(filterRange);
        yandexMarketCategory.selectCheckboxFields(filterCheckbox);
        yandexMarketCategory.checkFiltersPriceAndTitle(expectedWordsInTitle, expectedPrices);
        yandexMarketCategory.paginationPageFirst();
        yandexMarketCategory.searchByTitleFirstProduct();
    }
}
