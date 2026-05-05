package put.io.testing.mocks;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExpenseRepositoryTest {

    @Test
    public void testLoadExpenses() {
        IFancyDatabase mock = mock(IFancyDatabase.class);
        when(mock.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository repository = new ExpenseRepository(mock);
        repository.loadExpenses();

        InOrder inOrder = inOrder(mock);

        inOrder.verify(mock).connect();
        inOrder.verify(mock).queryAll();
        inOrder.verify(mock).close();

        assertTrue(repository.getExpenses().isEmpty(), "The list of expenses should be empty.");
    }

    @Test
    public void testSaveExpenses() {
        IFancyDatabase mock = mock(IFancyDatabase.class);
        when(mock.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository repository = new ExpenseRepository(mock);

        for(int i = 0; i < 5; i++) {
            repository.addExpense(new Expense());
        }

        repository.saveExpenses();

        InOrder inOrder = inOrder(mock);

        inOrder.verify(mock).connect();
        inOrder.verify(mock, times(5)).persist(any(Expense.class));
        inOrder.verify(mock).close();

    }
}