package com.application.splitwise.app;

import com.application.splitwise.input.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SplitWiseAppTest {
    @Mock
    InputReader mockReader;

    @BeforeEach
    void setUp() {
        when(mockReader.readPersonsExpenditure()).thenReturn(Collections.emptyList());
    }

    @Test
    void shouldBeAbleToCallRunMethod() {

        SplitWiseApp splitWiseApp = new SplitWiseApp();
        splitWiseApp.reader = mockReader;
        splitWiseApp.run();

        verify(mockReader).readPersonsExpenditure();
    }

}