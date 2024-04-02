import burp.*;
import java.util.*;

public class ScannerInsertionPointProvider implements IScannerInsertionPointProvider {

    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;

    public ScannerInsertionPointProvider(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
    }

    @Override
    public List<IScannerInsertionPoint> getInsertionPoints(IHttpRequestResponse baseRequestResponse) {
        return new ArrayList<IScannerInsertionPoint>();
    }
}
