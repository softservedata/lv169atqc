package com.softserve.edu.atqc.tools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.softserve.edu.atqc.exceptions.ScreenCapturingCustomException;

class SearchExplicit extends ASearchContext {
    private static volatile SearchExplicit instance = null;
    private long explicitlyWaitTimeout = 30L; //4L; //30L;
    
    private SearchExplicit() {
    }

    public static SearchExplicit get() {
        if (instance == null) {
            synchronized (SearchExplicit.class) {
                if (instance == null) {
                    instance = new SearchExplicit();
                }
            }
        }
        BrowserUtils.get().getBrowser().getWebDriver()
            .manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
//        BrowserUtils.get().getBrowser().getWebDriver()
//            .manage().timeouts().pageLoadTimeout(0L, TimeUnit.SECONDS);
//        BrowserUtils.get().getBrowser().getWebDriver()
//            .manage().timeouts().setScriptTimeout(0L, TimeUnit.SECONDS);
        return instance;
    }

    void setExplicitlyWaitTimeout(long explicitlyWaitTimeout) {
        this.explicitlyWaitTimeout = explicitlyWaitTimeout;
    }

    long getExplicitlyWaitTimeout() {
        return this.explicitlyWaitTimeout;
    }
    
    WebElement getVisibleWebElement(ControlLocation controlLocation){
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .visibilityOfElementLocated(controlLocation.getBy()));
        } catch (Exception e) {
            // TODO Duplicate Code
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        if (webElement == null) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        return webElement;
    }
    
    List<WebElement> getVisibleWebElements(ControlLocation controlLocation){
        List<WebElement> webElements = null;
        try {
        webElements = new WebDriverWait(
                BrowserUtils.get().getBrowser().getWebDriver(),
                    getExplicitlyWaitTimeout())
            .until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(controlLocation.getBy()));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        if ((webElements == null) || (webElements.size() == 0)) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        return webElements;
    }
    
    WebElement getPresentWebElement(ControlLocation controlLocation){
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .presenceOfElementLocated(controlLocation.getBy()));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        if (webElement == null) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_NOT_FOUND,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_NOT_FOUND,
//                    controlLocation.getValue()));
        }
        return webElement;
    }
    
    boolean isClickableWebElement(ControlLocation controlLocation) {
        boolean clickableWebElement = false;
        try {
            clickableWebElement = (new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .elementToBeClickable(controlLocation.getBy()))) != null;
            System.out.println(" +++++++clickableWebElement = "+clickableWebElement);
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        if (!clickableWebElement) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        return clickableWebElement;
    }

    boolean isInvisibleWebElement(ControlLocation controlLocation){
        boolean invisibilityWebElement = false;
        try {
            invisibilityWebElement = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .invisibilityOfElementLocated(controlLocation.getBy()));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        if (!invisibilityWebElement) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        return invisibilityWebElement;
    }
    
    boolean isInvisibleWebElementWithText(ControlLocation controlLocation, String text){
        boolean invisibilityWebElement = false;
        try {
            invisibilityWebElement = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .invisibilityOfElementWithText(controlLocation.getBy(), text));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        if (!invisibilityWebElement) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlLocation.getValue()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlLocation.getValue()));
        }
        return invisibilityWebElement;
    }
    
    boolean isStatelessOfWebElement(ControlWrapper controlWrapper){
        boolean statelessOfWebElement = true;
        try {
            statelessOfWebElement = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .stalenessOf(controlWrapper.getWebElement()));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlWrapper.getWebElement().getTagName()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlWrapper.getWebElement().getTagName()));
        }
        if (!statelessOfWebElement) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
                    controlWrapper.getWebElement().getTagName()));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_STILL_VISIBLE,
//                    controlWrapper.getWebElement().getTagName()));
        }
        return statelessOfWebElement;
    }
    
    boolean isVisibleTitle(String partialTitle){
        boolean visibleTitle = false;
        try {
            visibleTitle = new WebDriverWait(
                    BrowserUtils.get().getBrowser().getWebDriver(),
                        getExplicitlyWaitTimeout())
                .until(ExpectedConditions
                    .titleContains(partialTitle));
        } catch (Exception e) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_TITLE_INVISIBLE,
                    partialTitle));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_TITLE_INVISIBLE,
//                    partialTitle));
        }
        if (visibleTitle) {
            throw new ScreenCapturingCustomException(String.format(ASearchContext.ERROR_TITLE_INVISIBLE,
                    partialTitle));
//            throw new RuntimeException(String.format(ASearchContext.ERROR_TITLE_INVISIBLE,
//                    partialTitle));
        }
        return visibleTitle;
    }

}
