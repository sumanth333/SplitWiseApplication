package com.application.splitwise.app;

import com.application.splitwise.input.ConsoleInputReader;
import com.application.splitwise.input.InputReader;

public class SplitWiseApp implements App{
    InputReader reader;

    public SplitWiseApp() {
        reader = new ConsoleInputReader();
    }

    @Override
    public void run() {
        reader.readPersonsExpenditure();
    }
}
