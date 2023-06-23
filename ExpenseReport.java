package com.vikasietum.interviews.expense;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    private final ExpenseType type;
    private final int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    //Getter for expense type - returns type of expense
    public ExpenseType getExpenseType() {
        return  type;
    }

    //Getter for expense amount - return amount of expense
    public int getAmount() {
        return amount;
    }

    /*
    * This function checks if the given expense is of meals or not
    */
    public boolean ifMealExpense() {
        return (type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST);
    }

    /*
    * This functions checks if Meal expense exceeds the limit and returns true or false - takes 4 arguments
    * arg1 - ExpenseType
    * arg2 - dinner limit amount
    * arg3 - breakfast limit amount
    * arg4 - Actual amount spent
    */
    public boolean ifMealOverExpenses(ExpenseType type, int dinnerLimit, int breakfastLimit,  int amount) {
        return (type == ExpenseType.DINNER && amount > dinnerLimit) || (type == ExpenseType.BREAKFAST && amount > breakfastLimit);
    }

}

public class ExpenseReport {

    /*
     * Calculates and returns total meal expenses - takes List of Expense as argument
     */
    public int getMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense : expenses) {
            if (expense.ifMealExpense()) {
                mealExpenses += expense.getAmount();
            }
        }
        return mealExpenses;
    }

    /*
     * Calculates and returns total expenses - takes List of Expense as argument
     */
    public int getTotalExpenses(List<Expense> expenses) {
        int totalExpenses = 0;
        for (Expense expense : expenses) {
            totalExpenses += expense.getAmount();
        }
        return totalExpenses;
    }

    /*
     * Returns the name of respective Expense Type - Takes ExpenseType as an argument
     */
    public String getExpenseName(ExpenseType type) {
        switch (type) {
            case DINNER:
                return "Dinner";
            case BREAKFAST:
                return "Breakfast";
            case CAR_RENTAL:
                return "Car Rental";
            default:
                return "Other";
        }
    }

    /*
     * Returns the marker String that tells if meal amount exceeded the limit - Takes ExpenseType and int(amount) as arguments
     */
    public String getMealOverExpensesMarker(Expense expense, ExpenseType type, int amount) {
        if (expense.ifMealOverExpenses(type, 5000, 1000, amount)) {
            return "X";
        }
        return " ";
    }

    /*
     * Print Expenses
     */
    public void printExpenses(List<Expense> expenses) {
        for (Expense expense : expenses) {
            String expenseName = getExpenseName(expense.getExpenseType());
            String mealOverExpensesMarker = getMealOverExpensesMarker(expense, expense.getExpenseType(), expense.getAmount());
            System.out.println(expenseName + "\t" + expense.getAmount() + "\t" + mealOverExpensesMarker);
        }
    }

    /*
     * Print final expense report
     */
    public void printReport(List<Expense> expenses) {
        int mealExpense = getMealExpenses(expenses);
        int totalExpense = getTotalExpenses(expenses);

        System.out.println("Expenses " + new Date());

        printExpenses(expenses);

        System.out.println("Meal Expenses " + mealExpense);
        System.out.println("Total Expenses " + totalExpense);
    }
}

/****************************************Given Code:*******************************************/
//    public void printReport(List<Expense> expenses) {
//        int total = 0;
//        int mealExpenses = 0;
//
//        System.out.println("Expenses " + new Date());
//
//        for (Expense expense : expenses) {
//            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
//                mealExpenses += expense.amount;
//            }
//
//            String expenseName = "";
//            switch (expense.type) {
//                case DINNER:
//                    expenseName = "Dinner";
//                    break;
//                case BREAKFAST:
//                    expenseName = "Breakfast";
//                    break;
//                case CAR_RENTAL:
//                    expenseName = "Car Rental";
//                    break;
//            }
//
//            String mealOverExpensesMarker = expense.type == ExpenseType.DINNER && expense.amount > 5000 ||
//                    expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";
//
//            System.out.println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker);
//
//            total += expense.amount;
//        }
//
//        System.out.println("Meal expenses: " + mealExpenses);
//        System.out.println("Total expenses: " + total);
//    }
//}