package com.application.splitwise.app;

import com.application.splitwise.input.InputReader;
import com.application.splitwise.output.OutputWriter;
import com.application.splitwise.service.OperationsManager;
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
    InputReader mockReader;
    @Mock
    OperationsManager mockOperationsManager;
    @Mock
    OutputWriter mockOutputWriter;

    @BeforeEach
    void setUp() {
        when(mockReader.readPersonsExpenditure()).thenReturn(Collections.emptyList());
        when(mockOperationsManager.findDebtors(any())).thenReturn(Collections.emptyList());
        when(mockOperationsManager.findBeneficiaries(any())).thenReturn(Collections.emptyList());
        when(mockOperationsManager.settleAmountBetweenDebtorsBeneficiaries(any(), any())).thenReturn(Collections.emptyList());
    }

    @Test
    void verifyImportantMethodCallsOnRunningApp() {
        SplitWiseApp splitWiseApp = new SplitWiseApp();
        splitWiseApp.reader = mockReader;
        splitWiseApp.operationsManager = mockOperationsManager;
        splitWiseApp.outputWriter = mockOutputWriter;
        splitWiseApp.run();

        verify(mockReader).readPersonsExpenditure();
        verify(mockOperationsManager).findBeneficiaries(any());
        verify(mockOperationsManager).findDebtors(any());
        verify(mockOutputWriter).writeSplitExpensesLog(any());
    }

}