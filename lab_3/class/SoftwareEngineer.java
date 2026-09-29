// Họ và tên: Dương Quang Minh
// MSSV: 20237362

public class SoftwareEngineer extends Employee {
    // Attributes:
    // Các attributes đã có của Employee.
    private String primaryLanguage;
    private double technicalAllowance;

    // Constructors:
    public SoftwareEngineer(String id, String fullName, String primaryLanguage) {

        super(id, fullName);

        // Ràng buộc phải có ngôn ngữ lập trình
        if (primaryLanguage == null || primaryLanguage.isEmpty()) {
            throw new IllegalArgumentException("Ngôn ngữ lập trình không được trống!");
        }

        this.primaryLanguage = primaryLanguage;
        this.technicalAllowance = 0.0;
        System.out.println("Tạo SE " + id + " : " + fullName + " dùng " + primaryLanguage);
    }

    public SoftwareEngineer(String id, String fullName, double baseSalary, String primaryLanguage,
            double technicalAllowance) {

        super(id, fullName, baseSalary);

        // Ràng buộc phải có ngôn ngữ lập trình
        if (primaryLanguage == null || primaryLanguage.isEmpty()) {
            throw new IllegalArgumentException("Ngôn ngữ lập trình không được trống!");
        }

        // Ràng buộc phụ cấp không âm
        if (technicalAllowance < 0) {
            throw new IllegalArgumentException("Phụ cấp kỹ thuật không âm!");
        }

        this.primaryLanguage = primaryLanguage;
        this.technicalAllowance = technicalAllowance;
        System.out
                .println("Tạo SE " + id + " : " + fullName + " với LCB là " + baseSalary + " dùng " + primaryLanguage);
    }

    // Getters:
    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public double getTechnicalAllowance() {
        return technicalAllowance;
    }

    // Override các methods của class Employee
    @Override
    public double calculateMonthlyCost() {
        return getBaseSalary() + technicalAllowance;
    }

    @Override
    public void displayInfo() {
        System.out.println("Thông tin nhân sự:");
        System.out.println("- Mã: " + getId());
        System.out.println("- Họ tên: " + getFullName());
        System.out.println("- Lương cơ bản: " + getBaseSalary());
        System.out.println("- Ngôn ngữ lập trình " + getPrimaryLanguage());
        System.out.println("- Phụ cấp kỹ thuật " + getTechnicalAllowance());
        System.out.println("- Tổng lương tháng: " + calculateMonthlyCost());

    }

}
