public class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesRevenue;
    private double commissionRate;

    // Constructors
    public SalesEmployee(String employeeId, String fullName, String department,
            double baseSalary, double salesRevenue, double commissionRate) {

        super(employeeId, fullName, department);
        // Ràng buộc 1: Lương cơ bản dương
        if (baseSalary < 0)
            throw new IllegalArgumentException("Luong co ban khong duoc am.");

        // Ràng buộc 2: Doanh số dương
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh so khong duoc am.");

        // Ràng buộc 3: Tỷ lệ hoa hồng nằm trong khoảng từ 0 đến 0,3.
        if (commissionRate <= 0 || commissionRate > 0.3)
            throw new IllegalArgumentException("Ty le hoa hong phai nam trong (0, 0.3].");

        this.baseSalary = baseSalary;
        this.salesRevenue = salesRevenue;
        this.commissionRate = commissionRate;
    }

    public SalesEmployee(String employeeId, String fullName, double baseSalary, double salesRevenue) {
        this(employeeId, fullName, "Unassigned", baseSalary, salesRevenue, 0.05);
    }

    // Overrided Methods

    @Override
    public double calculateGrossPay() {
        return baseSalary + salesRevenue * commissionRate + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "SalesEmployee";
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  %-13s: %,.0f VND%n", "Luong co ban", baseSalary);
        System.out.printf("  %-13s: %,.0f VND%n", "Doanh so", salesRevenue);
        System.out.printf("  %-13s: %.0f%%%n", "Ty le hoa hong", commissionRate * 100);
        System.out.printf("  %-13s: %,.0f VND%n", "Hoa hong", salesRevenue * commissionRate);
    }

    // Method
    public void updateSalesRevenue(double salesRevenue) {
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh so khong duoc am.");
        this.salesRevenue = salesRevenue;
    }

    // Getters
    public double getBaseSalary() {
        return baseSalary;
    }

    public double getSalesRevenue() {
        return salesRevenue;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}
