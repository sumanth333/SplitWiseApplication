package com.application.splitwise.app;

import com.application.splitwise.input.InputReader;
import com.application.splitwise.output.OutputWriter;
import com.application.splitwise.service.SplitWiseOperationsManager;
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
    SplitWiseOperationsManager mockSplitWiseOperationsManager;
    @Mock
    OutputWriter mockOutputWriter;

    @BeforeEach
    void setUp() {
        when(mockReader.readPersonsExpenditure()).thenReturn(Collections.emptyList());
        when(mockSplitWiseOperationsManager.findDebtors(any())).thenReturn(Collections.emptyList());
        when(mockSplitWiseOperationsManager.findBeneficiaries(any())).thenReturn(Collections.emptyList());
        when(mockSplitWiseOperationsManager.settleAmountBetweenDebtorsBeneficiaries(any(), any())).thenReturn(Collections.emptyList());
    }

    @Test
    void verifyImportantMethodCallsOnRunningApp() {
        SplitWiseApp splitWiseApp = new SplitWiseApp();
        splitWiseApp.setReader(mockReader);
        splitWiseApp.setSplitWiseOperationsManager(mockSplitWiseOperationsManager);
        splitWiseApp.setOutputWriter(mockOutputWriter);
        splitWiseApp.run();

        verify(mockReader).readPersonsExpenditure();
        verify(mockSplitWiseOperationsManager).findBeneficiaries(any());
        verify(mockSplitWiseOperationsManager).findDebtors(any());
        verify(mockOutputWriter).writeSplitExpensesLog(any());
    }

}