package noraui.application.steps;

import org.openqa.selenium.WebDriver;

import noraui.application.page.IPage;

public interface IStep {

    /**
     * checkStep call checkPage
     *
     * @param page
     *            is the target page
     */
    public void checkStep(IPage page);

    /**
     * Quick getter to the global web driver
     * 
     * @return
     *         The global instance of web driver
     */
    WebDriver getDriver();

}
