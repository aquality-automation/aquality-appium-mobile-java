package aquality.appium.application;

import aquality.appium.configuration.IConfiguration;

public class ApplicationManager {
    private static final ThreadLocal<Application> applicationContainer = new ThreadLocal<>();
    private static final ThreadLocal<IApplicationFactory> factoryContainer = new ThreadLocal<>();

    private ApplicationManager() {
    }


    public static Application getApplication(){
        if(applicationContainer.get() == null || applicationContainer.get().getDriver().getSessionId() == null) {
            setDefaultApplication();
        }
        return applicationContainer.get();
    }

    public static void setDefaultFactory(){
        /*
        todo: to be implemented
        IConfiguration configuration = Configuration.getInstance();
        IApplicationFactory applicationFactory = Configuration.getInstance().getApplicationProfile().isRemote()
                ? new RemoteApplicationFactory(configuration) : new LocalApplicationFactory(configuration);

        setFactory(applicationFactory);*/
    }

    public static void setFactory(IApplicationFactory applicationFactory){
        remove(factoryContainer);
        ApplicationManager.factoryContainer.set(applicationFactory);
    }

    private static void setDefaultApplication(){
        if(factoryContainer.get() == null){
            setDefaultFactory();
        }
        setApplication(factoryContainer.get().getApplication());
    }

    public static void setApplication(Application application){
        remove(applicationContainer);
        ApplicationManager.applicationContainer.set(application);
    }

    private static void remove(ThreadLocal<?> container){
        if(container.get() != null){
            container.remove();
        }
    }
}
