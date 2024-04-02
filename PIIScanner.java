import burp.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PIIScanner implements IScannerCheck {

    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private static final Pattern CPF_PATTERN = Pattern.compile("\\b\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}\\b");

    public PIIScanner(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
    }

    @Override
    public List<IScanIssue> doPassiveScan(IHttpRequestResponse baseRequestResponse) {
        byte[] responseBytes = baseRequestResponse.getResponse();
        IResponseInfo responseInfo = helpers.analyzeResponse(responseBytes);
        List<IScanIssue> issues = new ArrayList<>();

        Matcher matcher = CPF_PATTERN.matcher(helpers.bytesToString(responseBytes));

        while (matcher.find()) {
            issues.add(new PIIScannerIssue(
                    baseRequestResponse,
                    helpers,
                    matcher.start(),
                    matcher.end(),
                    matcher.group()
            ));
        }

        return issues;
    }

    @Override
    public List<IScanIssue> doActiveScan(IHttpRequestResponse baseRequestResponse, IScannerInsertionPoint insertionPoint) {
        return new ArrayList<>();
    }

    @Override
    public int consolidateDuplicateIssues(IScanIssue existingIssue, IScanIssue newIssue) {
        return 0;
    }
}
