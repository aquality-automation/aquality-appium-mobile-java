package aquality.appium.elements.interfaces;

public interface IElement {
    /**
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     * @return provider to define element's state
     */
    IElementStateProvider state();
}
