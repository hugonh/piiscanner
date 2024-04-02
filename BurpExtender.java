import burp.*;
import java.util.List;
import java.util.ArrayList;

public class BurpExtender implements IBurpExtender, ITab, IScannerCheck {

    private IBurpExtenderCallbacks callbacks;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;

        // Set the name of the extension
        callbacks.setExtensionName("PII Scanner");

        // Register the scanner check
        callbacks.registerScannerCheck(new PIIScanner(callbacks));

        // Add the custom tab to Burp's UI
        callbacks.addSuiteTab(new PIIScannerTab(callbacks));
    }

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        return new ArrayList<IScanIssue>();
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse, IScannerInsertionPoint insertionPoint) {
        return new ArrayList<IScanIssue>();
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        return 0;
    }

    @Override
    public String getTabCaption() {
        return "PII Scanner";
    }

    @Override
    public java.awt.Component getUiComponent() {
        return null;
    }
}
