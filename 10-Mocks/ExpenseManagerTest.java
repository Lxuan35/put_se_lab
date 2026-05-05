package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    @Test
    public void testCalculateTotal(){
        IExpenseRepository mockRepository = mock(IExpenseRepository.class);

        List<Expense> fExpenses = new ArrayList<>();
        Expense e1 = new Expense();
        e1.setAmount(100);
        fExpenses.add(e1);

        Expense e2 = new Expense();
        e2.setAmount(200);
        fExpenses.add(e2);

        Expense e3 = new Expense();
        e3.setAmount(300);
        fExpenses.add(e3);

        when(mockRepository.getExpenses()).thenReturn(fExpenses);

        FancyService mockService = mock(FancyService.class);

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        long total = manager.calculateTotal();

        assertEquals(600, total);
    }

    @Test
    public void testCalculateTotalForCategory(){
        IExpenseRepository mockRepository = mock(IExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);

        //Data for "Home"
        List<Expense> homeExpenses = new ArrayList<>();
        Expense homeExpense1 = new Expense();
        homeExpense1.setAmount(400);
        homeExpense1.setCategory("Home");
        Expense homeExpense2 = new Expense();
        homeExpense2.setAmount(100);
        homeExpense2.setCategory("Home");
        homeExpenses.add(homeExpense1);
        homeExpenses.add(homeExpense2);

        //Data for "Car"
        List<Expense> carExpenses = new ArrayList<>();
        Expense carExpense1 = new Expense();
        carExpense1.setAmount(50);
        carExpense1.setCategory("Home");
        Expense carExpense2 = new Expense();
        carExpense2.setAmount(20);
        carExpense2.setCategory("Home");
        carExpenses.add(carExpense1);
        carExpenses.add(carExpense2);

        when(mockRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());

        when(mockRepository.getExpensesByCategory("Home")).thenReturn(homeExpenses);

        when(mockRepository.getExpensesByCategory("Car")).thenReturn(carExpenses);

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        assertEquals(500, manager.calculateTotalForCategory("Home"), "Total for Home should be 500.");
        assertEquals(70, manager.calculateTotalForCategory("Car"), "Total for Car should be 70.");

        assertEquals(0, manager.calculateTotalForCategory("Food"), "Total for Food should be 0.");
        assertEquals(0, manager.calculateTotalForCategory("Sport"), "Total for Sport should be 0.");

    }

    @Test
    public void testCalculateTotalInDollars() throws ConnectException {
        IExpenseRepository mockRepository = mock(IExpenseRepository.class);

        List<Expense> fExpenses = new ArrayList<>();

        Expense e1 = new Expense();
        e1.setAmount(60);
        fExpenses.add(e1);

        when(mockRepository.getExpenses()).thenReturn(fExpenses);

        FancyService mockService = mock(FancyService.class);;

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenReturn(15.0);

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        long totalInDollars = manager.calculateTotalInDollars();

        assertEquals(15, totalInDollars, "60 PLN should be converted to 15 USD");

    }

    @Test
    public void testCalculateTotalInDollarsException() throws ConnectException {
        IExpenseRepository mockRepository = mock(IExpenseRepository.class);
        List<Expense> fakeExpenses = new ArrayList<>();
        Expense e1 = new Expense();
        e1.setAmount(60);
        fakeExpenses.add(e1);
        when(mockRepository.getExpenses()).thenReturn(fakeExpenses);

        FancyService mockService = mock(FancyService.class);

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD")))
                .thenThrow(new ConnectException("Connection failure to NBP"));

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        long totalInDollars = manager.calculateTotalInDollars();

        assertEquals(-1, totalInDollars, "If the connection fails, the manager should return -1.");
    }

    @Test
    public void testCalculateTotalInDollarsDynamic() throws ConnectException {
        IExpenseRepository mockRepository = mock(IExpenseRepository.class);
        List<Expense> fExpenses = new ArrayList<>();

        Expense e1 = new Expense();
        e1.setAmount(80);
        fExpenses.add(e1);

        when(mockRepository.getExpenses()).thenReturn(fExpenses);

        FancyService mockService = mock(FancyService.class);

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(invocation -> {
            Double amountPLN = invocation.getArgument(0);

            return amountPLN / 4.0;
        });

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        long totalInDollars = manager.calculateTotalInDollars();

        assertEquals(20, totalInDollars, "The manager should return 20 USD for 80 PLN input.");
    }
}
