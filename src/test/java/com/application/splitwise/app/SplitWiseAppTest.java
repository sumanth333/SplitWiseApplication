package com.application.splitwise.app;

import com.application.splitwise.input.ExpenditureReader;
import com.application.splitwise.output.TransactionsWriter;
import com.application.splitwise.service.TransactionsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SplitWiseAppTest {
    @Mock
    ExpenditureReader mockReader;
    @Mock
    TransactionsGenerator mockTransactionsGenerator;
    @Mock
    TransactionsWriter mockWriter;

    @BeforeEach
    void setUp() {
        when(mockReader.readExpenditures()).thenReturn(Collections.emptyList());
        when(mockTransactionsGenerator.generateTransactions(any())).thenReturn(Collections.emptyList());
    }

    @Test
    void verifyImportantMethodCallsOnRunningApp() {
        SplitWiseApp splitWiseApp = new SplitWiseApp();
        splitWiseApp.setReader(mockReader);
        splitWiseApp.setTransactionsGenerator(mockTransactionsGenerator);
        splitWiseApp.setWriter(mockWriter);
        splitWiseApp.run();

        verify(mockReader).readExpenditures();
        verify(mockTransactionsGenerator).generateTransactions(any());
        verify(mockWriter).writeTransactions(any());
    }

}