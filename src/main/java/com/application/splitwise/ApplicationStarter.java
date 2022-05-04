package com.application.splitwise;

import com.application.splitwise.app.App;
import com.application.splitwise.app.SplitWiseApp;

public class ApplicationStarter {

    public static void main(String args[]) {
        App application = new SplitWiseApp();
        application.run();
    }
}
