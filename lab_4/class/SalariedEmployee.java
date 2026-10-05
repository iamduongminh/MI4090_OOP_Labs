public class SalariedEmployee extends Employee {

    // Attributes:
    private double monthlySalary;
    private double responsibilityAllowance;

    // Constructors

    public SalariedEmployee(String employeeId, String fullName, double monthlySalary) {
        this(employeeId, fullName, "Unassigned", monthlySalary, 0);
    }

    public SalariedEmployee(String employeeId, String fullName, String department,
            double monthlySalary, double responsibilityAllowance) {
        super(employeeId, fullName, department);

        if (monthlySalary < 0)
            throw new IllegalArgumentException("Luong thang khong duoc am.");

        if (responsibilityAllowance < 0)
            throw new IllegalArgumentException("Phu cap trach nhiem khong duoc am.");

        this.monthlySalary = monthlySalary;
        this.responsibilityAllowance = responsibilityAllowance;
    }

    // Overided Methods:

    @Override
    public double calculateGrossPay() {
        return monthlySalary + responsibilityAllowance + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "SalariedEmployee";
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  %-13s: %,.0f VND%n", "Luong thang", monthlySalary);
        System.out.printf("  %-13s: %,.0f VND%n", "Phu cap TN", responsibilityAllowance);
    }

    // Getters
    public double getMonthlySalary() {
        return monthlySalary;
    }

    public double getResponsibilityAllowance() {
        return responsibilityAllowance;
    }
}
