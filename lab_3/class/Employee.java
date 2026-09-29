// Họ và tên: Dương Quang Minh
// MSSV: 20237362

public class Employee {
    // Attributes:
    private String id;
    private String fullName;
    private double baseSalary;

    // Constructors:
    // Default Constructor:
    public Employee() {
        this.id = "UNKNOWN";
        this.fullName = "Unnamed employee";
        this.baseSalary = 0.0;
        System.out.println("Khởi tạo mặc định Employee với id = " + this.id);
    }

    // Constructor = id + fullName
    public Employee(String id, String fullName) {

        // Kiểm tra 2 ràng buộc:
        // 1. id khác rỗng:
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Mã nhân sự phải khác rỗng!");
        }

        // 2. fullName khác rỗng:
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Họ tên nhân sự phải khác rỗng!");
        }

        this.id = id;
        this.fullName = fullName;
        this.baseSalary = 0.0;
        System.out.println("Khởi tạo Employee với id = " + this.id + ": " + this.fullName);
    }

    // Constructor đầy đủ
    public Employee(String id, String fullName, double baseSalary) {

        // Kiểm tra 3 ràng buộc:
        // 1. id khác rỗng:
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Mã nhân sự phải khác rỗng!");
        }

        // 2. fullName khác rỗng:
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Họ tên nhân sự phải khác rỗng!");
        }

        // 3. baseSalary không âm:
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Lương cơ bản của nhân sự không đươc âm!");
        }

        this.id = id;
        this.fullName = fullName;
        this.baseSalary = baseSalary;
        System.out.println("Khởi tạo Employee đầy đủ với id = " + this.id + ": " + this.fullName + " với LCB là"
                + this.baseSalary);
    }

    // Getters:
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Overloading Method tăng lương:
    // Tăng mức cố định:
    public void increaseSalary(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Lương phải tăng theo số dương!");
        }
        this.baseSalary += amount;
    }

    // Tăng theo phần trăm lương cũ
    public void increaseSalary(double value, boolean isPercentage) {
        if (value <= 0) {
            throw new IllegalArgumentException("Giá trị tăng phải dương!");
        }
        if (isPercentage) {
            this.baseSalary += this.baseSalary * (value / 100);
        }

        else {
            this.baseSalary += value;
        }
    }

    // Hàm cho phép override (trong Java không có virtual)
    public double calculateMonthlyCost() {
        return baseSalary;
    }

    public void displayInfo() {
        System.out.println("Thông tin nhân sự:");
        System.out.println("- Mã: " + this.id);
        System.out.println("- Họ tên: " + this.fullName);
        System.out.println("- Lương cơ bản: " + this.baseSalary);
    }

    // Destructor virtual để hiển thị thứ tự hủy lớp xem đã đúng chưa (Java và C#
    // không cần), Khi virtual có trong lớp cha, thứ tự hủy sẽ là lớp con -> lớp cha
    // còn không có virtual thì chỉ lớp cha bị hủy, lớp con không đc giải phóng.
}
