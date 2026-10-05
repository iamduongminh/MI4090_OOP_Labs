public class HourlyEmployee extends Employee {

    // Attributes
    private double hourlyRate;
    private double workedHours;

    // Static attributes (tiện cho việc thay đổi các giá trị ngưỡng)
    private static final double STANDARD_HOURS = 160.0;
    private static final double OVERTIME_FACTOR = 1.5;
    private static final double MAX_HOURS = 250.0;

    // Constructors
    public HourlyEmployee(String employeeId, String fullName, String department,
            double hourlyRate, double workedHours) {

        super(employeeId, fullName, department);
        // Ràng buộc 1: Đơn giá dương
        if (hourlyRate <= 0)
            throw new IllegalArgumentException("Don gia gio phai lon hon 0.");
        // Ràng buộc 1: Số giờ làm dương, < 160 giờ
        if (workedHours < 0 || workedHours > MAX_HOURS)
            throw new IllegalArgumentException(
                    "So gio lam hop le tu 0 den " + (int) MAX_HOURS + ".");

        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;
    }

    public HourlyEmployee(String employeeId, String fullName,
            double hourlyRate, double workedHours) {
        this(employeeId, fullName, "Unassigned", hourlyRate, workedHours);
    }

    // Overrided Methods
    @Override
    public double calculateGrossPay() {
        double basePay;

        if (workedHours <= STANDARD_HOURS) {
            basePay = workedHours * hourlyRate;
        }

        else {
            double overtime = workedHours - STANDARD_HOURS;
            basePay = STANDARD_HOURS * hourlyRate + overtime * hourlyRate * OVERTIME_FACTOR;
        }
        return basePay + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "HourlyEmployee";
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  %-13s: %,.0f VND%n", "Don gia gio", hourlyRate);
        System.out.printf("  %-13s: %.1f gio%n", "So gio lam", workedHours);
        if (workedHours > STANDARD_HOURS) {
            double overtime = workedHours - STANDARD_HOURS;
            System.out.printf("  %-13s: %.1f gio%n", "Gio thuong", STANDARD_HOURS);
            System.out.printf("  %-13s: %.1f gio (x%.1f)%n", "Gio vuot", overtime, OVERTIME_FACTOR);
        }
    }

    // Setter:
    public void setWorkedHours(double workedHours) {
        if (workedHours < 0 || workedHours > MAX_HOURS)
            throw new IllegalArgumentException("So gio lam hop le tu 0 den " + (int) MAX_HOURS + ".");
        this.workedHours = workedHours;
    }

    // Getters
    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getWorkedHours() {
        return workedHours;
    }
}
