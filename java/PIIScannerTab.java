import burp.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PIIScannerTab implements ITab {

    private IBurpExtenderCallbacks callbacks;
    private JLabel label;

    public PIIScannerTab(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        label = new JLabel("PII Scanner tab content");
    }

    @Override
    public String getTabCaption() {
        return "PII Scanner";
    }

    @Override
    public java.awt.Component getUiComponent() {
        return label;
    }
}
