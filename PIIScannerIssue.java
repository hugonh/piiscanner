import burp.*;

import java.net.URL;

public class PIIScannerIssue implements IScanIssue {

    private IHttpRequestResponse httpMessage;
    private IExtensionHelpers helpers;
    private int start;
    private int end;
    private String pattern;

    public PIIScannerIssue(IHttpRequestResponse httpMessage, IExtensionHelpers helpers, int start, int end, String pattern) {
        this.httpMessage = httpMessage;
        this.helpers = helpers;
        this.start = start;
        this.end = end;
        this.pattern = pattern;
    }

    @Override
    public URL getUrl() {
        return helpers.analyzeRequest(httpMessage).getUrl();
    }

    @Override
    public String getIssueName() {
        return "PII detectado";
    }

    @Override
    public int getIssueType() {
        return 0;
    }

    @Override
    public String getSeverity() {
        return "Alto";
    }

    @Override
    public String getConfidence() {
        return "Provável";
    }

    @Override
    public String getIssueBackground() {
        return "PII detectado na resposta.";
    }

    @Override
    public String getRemediationBackground() {
        return "Verificar resposta se não há PII exposto.";
    }

    @Override
    public String getIssueDetail() {
        return "PII exposta: " + pattern;
    }

    @Override
    public String getRemediationDetail() {
        return "Remover PII.";
    }

    @Override
    public IHttpRequestResponse[] getHttpMessages() {
        return new IHttpRequestResponse[]{httpMessage};
    }

    @Override
    public IHttpService getHttpService() {
        return httpMessage.getHttpService();
    }

    @Override
    public String getProtocol() {
        return httpMessage.getHttpService().getProtocol();
    }

    @Override
    public int getPort() {
        return httpMessage.getHttpService().getPort();
    }

    @Override
    public String getHost() {
        return httpMessage.getHttpService().getHost();
    }
}
