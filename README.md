# Aquality Appium Mobile for Java

[![Build Status](https://dev.azure.com/aquality-automation/aquality-automation/_apis/build/status/aquality-automation.aquality-appium-mobile-java?branchName=master)](https://dev.azure.com/aquality-automation/aquality-automation/_build/latest?definitionId=6&branchName=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=aquality-automation_aquality-appium-mobile-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=aquality-automation_aquality-appium-mobile-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aquality-automation/aquality-appium-mobile/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aquality-automation/aquality-appium-mobile)


### Overview

This package is a library designed to simplify automation of Android and iOS mobile applications using Appium.

You've got to use this set of methods, related to most common actions performed with web elements.

Most of performed methods are logged using LOG4J, so you can easily see a history of performed actions in your log.

We use interfaces where is possible, so you can implement your own version of target interface with no need to rewrite other classes. 

### Quick start

1. To start work with this package, simply add the dependency to your pom.xml:
```
<dependency>
    <groupId>com.github.aquality-automation</groupId>
    <artifactId>aquality-appium-mobile</artifactId>
    <version>LATEST</version>
</dependency>
```

2. Configure path to your application at settings.json:
 - Copy [settings.json](./src/main/resources/settings.json) into the resources directory of your project. 
 - Open settings.json and find `applicationPath` option under the `driverSettings` section of desired platform. Replace the value with full or relative path to your app, e.g. `./src/test/resources/apps/ApiDemos-debug.apk`.

3. Ensure that [Appium server](https://appium.io) is set up at your machine where the code would be executed, and the address/port match to set in your `settings.json` in `remoteConnectionUrl` parameter.
If the parameter `isRemote` in your settings.json is set to `false`, this means that AppiumDriverLocalService would be used to setup Appium server using Node.js. This option requires specific version of node.js to be preinstalled on your machine (Please read more [here](http://appium.io/docs/en/contributing-to-appium/appium-from-source/#nodejs) )

4. (optional) Launch an application directly by calling `AqualityServices.getApplication();`. 
> Note: 
If you don't start an Application directly, it would be started with the first call of any Aquality service or class requiring interacting with the Application.

5. That's it! Now you are able work with Application via AqualityServices or via element services.
Please take a look at our example tests [here](./src/test/java/samples/)

6. To interact with Application's forms and elements, we recommend following the Page/Screen Objects pattern. This approach is fully integrated into our package.
To start with that, you will need to create a separate class for each window/form of your application, and inherit this class from the [AndroidScreen](./src/main/java/aquality/appium/mobile/screens/AndroidScreen.java) or [IOSScreen](./src/main/java/aquality/appium/mobile/screens/IOSScreen.java) respectively. 

> We recommend to use separate Screen class for each form of your application. You can take advantage of inheritance and composition pattern. We also suggest not to mix app different platforms in single class: take advantage of interfaces instead, adding the default implementation to them if is needed.


7. From the Screen Object perspective, each Screen consists of elements on it (e.g. Buttons, TextBox, Labels and so on). 
To interact with elements, on your form class create fields of type IButton, ITextBox, ILabel, and initialize them using the `getElementFactory()`. Created elements have a various methods to interact with them. We recommend combining actions into a business-level methods:

```java
package samples.android.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.AndroidScreen;
import org.openqa.selenium.By;

public class InvokeSearchScreen extends AndroidScreen {

    private final ITextBox txbSearch = getElementFactory().getTextBox(By.id("txt_query_prefill"), "Search");
    private final IButton btnStartSearch = getElementFactory().getButton(By.id("btn_start_search"), "Start search");
    private final ILabel lblSearchResult = getElementFactory().getLabel(By.id("android:id/search_src_text"), "Search results");

    public InvokeSearchScreen() {
        super(By.xpath("//android.widget.TextView[@text='App/Search/Invoke Search']"), "Invoke Search");
    }

    public void submitSearch(String query) {
        txbSearch.clearAndType(query);
        btnStartSearch.click();
    }

    public String getSearchResult() {
        return lblSearchResult.getText();
    }
}

```

8. We use DI Guice to inject dependencies, so you can simply implement your MobileModule extended from [MobileModule](./src/main/java/aquality/appium/mobile/application/MobileModule.java) and inject it to `AqualityServices.initInjector(yourModule)`.

### ScreenFactory

When you automate tests for both iOS and Android platforms it is good to have only one set of tests and different implementations of screens. `ScreenFactory` allows to do this. You can define interfaces for your screens and have different implementations for iOS and Android platforms. And then use `ScreenFactory` to resolve necessary screen depending on the chosen platform.

1. Set `screensLocation` property in `settings.json`. It is a name of package where you define screens.

2. Define interfaces for the screens:

```java
package aquality.appium.mobile.template.screens.interfaces;

public interface ILoginScreen extends IScreen {

    ILoginScreen setUsername(final String username);

    ILoginScreen setPassword(final String password);

    void tapLogin();
}
```

3. Implement interface (Android example):

```java
package aquality.appium.mobile.template.screens.android;

import aquality.appium.mobile.screens.AndroidScreen;
import aquality.appium.mobile.template.screens.interfaces.ILoginScreen;

import static io.appium.java_client.MobileBy.AccessibilityId;
import static org.openqa.selenium.By.xpath;

public class LoginScreen extends AndroidScreen implements ILoginScreen {

    public LoginScreen() {
        super(xpath("//android.widget.TextView[@text='Login']"), "Login");
    }

    @Override
    public ILoginScreen setUsername(final String username) {
        getElementFactory().getTextBox(AccessibilityId("username"), "Username").sendKeys(username);
        return this;
    }

    @Override
    public ILoginScreen setPassword(final String password) {
        getElementFactory().getTextBox(AccessibilityId("password"), "Password").typeSecret(password);
        return this;
    }

    @Override
    public void tapLogin() {
        getElementFactory().getButton(AccessibilityId("loginBtn"), "Login").click();
    }
}
```

4. Resolve screen in test:

```java
ILoginScreen loginScreen = AqualityServices.getScreenFactory().getScreen(ILoginScreen.class);
```

You can find an example in [aquality-appium-mobile-java-template](https://github.com/aquality-automation/aquality-appium-mobile-java-template) repository.

### License
Library's source code is made available under the [Apache 2.0 license](https://github.com/aquality-automation/aquality-winappdriver-dotnet/blob/master/LICENSE).
