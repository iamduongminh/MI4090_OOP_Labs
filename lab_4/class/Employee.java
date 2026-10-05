import java.util.ArrayList;
import java.util.List;

public class Employee {

    // Attributes:
    private String employeeId;
    private String fullName;
    private String department;
    private double monthlyBonus;
    private List<String> bonusHistory;

    // Constructors:
    public Employee(String employeeId, String fullName, String department) {
        // Constraint 1: Mã nhân sự ko được rỗng
        if (employeeId == null || employeeId.isBlank())
            throw new IllegalArgumentException("Ma nhan su khong rong.");
        // Constraint 2: Họ tên ko được rỗng
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Ho ten khong rong.");
        // Constraint 3: Phòng ban ko được rỗng
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Phong ban khong rong.");

        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
        this.monthlyBonus = 0;
        this.bonusHistory = new ArrayList<>();
    }

    public Employee(String employeeId, String fullName) {
        this(employeeId, fullName, "Unassigned");
        // Constructor rút gọn
    }

    // Helper Methods:

    private void validateAmount(double amount) {
        // Constraint 4: Thưởng không được âm
        if (amount <= 0)
            throw new IllegalArgumentException("Thuong khong duoc am.");
    }

    private void validateReason(String reason) {
        // Constraint 5: Lý do thưởng không được trống
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Ly do thuong khong duoc rong.");
    }

    // Method Overloading:

    public void addBonus(double amount) {
        validateAmount(amount);
        monthlyBonus += amount;
        bonusHistory.add(String.format("Thuong co dinh: %,.0f VND", amount));
    }

    public void addBonus(double amount, String reason) {
        validateAmount(amount);
        validateReason(reason);
        monthlyBonus += amount;
        bonusHistory.add(String.format("Thuong co dinh: %,.0f VND | Ly do: %s", amount, reason));
    }

    public void addBonus(double rate, double referenceAmount, String reason) {
        // Ràng buộc 1: rate nằm trong khoảng lớn hơn 0 và không quá 0,5.
        if (rate <= 0 || rate > 0.5)
            throw new IllegalArgumentException("Ty le thuong phai nam trong (0, 0.5].");
        // Ràng buộc 2: referenceAmount phải lớn hơn 0.
        if (referenceAmount <= 0)
            throw new IllegalArgumentException("Gia tri tham chieu phai duong.");
        validateReason(reason);
        double amount = rate * referenceAmount;
        monthlyBonus += amount;
        bonusHistory.add(String.format("Thuong ty le %.0f%% x %,.0f = %,.0f VND | Ly do: %s", rate * 100,
                referenceAmount, amount, reason));
    }

    public void resetBonus() {
        monthlyBonus = 0;
        bonusHistory.clear();
    }

    // Method Overriding:

    public double calculateGrossPay() {
        return monthlyBonus;
    }

    public String getEmployeeType() {
        return "Employee";
    }

    public void displayPayrollInfo() {
        System.out.printf("%-15s: %s%n", "Loai", getEmployeeType());
        System.out.printf("%-15s: %s%n", "Ma NV", employeeId);
        System.out.printf("%-15s: %s%n", "Ho ten", fullName);
        System.out.printf("%-15s: %s%n", "Phong ban", department);
        System.out.printf("%-15s: %,.0f VND%n", "Thuong", monthlyBonus);
        if (!bonusHistory.isEmpty()) {
            System.out.println("  Chi tiet thuong:");
            for (String entry : bonusHistory)
                System.out.println("    - " + entry);
        }
        System.out.printf("%-15s: %,.0f VND%n", "Thu nhap", calculateGrossPay());
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }

}
