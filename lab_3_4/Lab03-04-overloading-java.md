# 📘 Hướng Dẫn Thực Hành Lab 3-4: Nhóm Dự Án và Nhân Sự (Java)

> **Chủ đề:** Constructor Overloading · Method Overloading · Kế thừa · Đa hình · Kết tập (Aggregation)

---

## 📁 Cấu Trúc Project

```
lab_3_4/
└── class/
    ├── Employee.java
    ├── SoftwareEngineer.java
    ├── ProjectTeam.java
    └── Main.java
```

---

## 🔷 Sơ Đồ Lớp (Class Diagram)

```
         ┌──────────────────────────────┐
         │          Employee            │
         ├──────────────────────────────┤
         │ - id: String                 │
         │ - fullName: String           │
         │ - baseSalary: double         │
         ├──────────────────────────────┤
         │ + Employee()                 │  ← Constructor 1
         │ + Employee(id, fullName)     │  ← Constructor 2
         │ + Employee(id, name, salary) │  ← Constructor 3
         │ + increaseSalary(amount)     │  ← Overload 1
         │ + increaseSalary(val, bool%) │  ← Overload 2
         │ + calculateMonthlyCost()     │  ← virtual
         │ + displayInfo()              │  ← virtual
         └──────────────┬───────────────┘
                        │ kế thừa (extends)
         ┌──────────────▼───────────────┐
         │       SoftwareEngineer       │
         ├──────────────────────────────┤
         │ - primaryLanguage: String    │
         │ - technicalAllowance: double │
         ├──────────────────────────────┤
         │ + SoftwareEngineer(...)      │  ← Constructor 1
         │ + SoftwareEngineer(...)      │  ← Constructor 2
         │ + calculateMonthlyCost()     │  ← @Override
         │ + displayInfo()              │  ← @Override
         └──────────────────────────────┘

         ┌──────────────────────────────────┐
         │           ProjectTeam            │
         ├──────────────────────────────────┤
         │ - projectCode: String            │
         │ - projectName: String            │
         │ - leader: Employee  (ref)        │
         │ - members: List<Employee> (ref)  │
         ├──────────────────────────────────┤
         │ + ProjectTeam(code, name)        │  ← Constructor 1
         │ + ProjectTeam(code, name, leader)│  ← Constructor 2
         │ + addMember(employee)            │  ← Overload 1
         │ + addMember(employee, makeLeader)│  ← Overload 2
         │ + removeMember(id)               │
         │ + changeLeader(employee)         │
         │ + contains(id)                   │
         │ + calculateTotalMonthlyCost()    │
         │ + displayTeam()                  │
         └──────────────────────────────────┘
```

> **Quan hệ Kết Tập (Aggregation):** `ProjectTeam` **tham chiếu** đến `Employee` nhưng **không sở hữu** — khi `ProjectTeam` không còn dùng nữa, các `Employee` vẫn tồn tại.

---

## ✅ Bất Biến của Mô Hình (Invariants)

| # | Bất biến |
|---|----------|
| 1 | `id` của nhân sự không được rỗng |
| 2 | `baseSalary` không được âm |
| 3 | Không có hai thành viên cùng `id` trong một nhóm |
| 4 | Trưởng nhóm phải nằm trong danh sách thành viên |
| 5 | Một nhân sự không xuất hiện hai lần trong cùng nhóm |

---

## 🏗️ Lớp 1: `Employee.java`

### Khái niệm chính: **Nạp chồng Constructor (Constructor Overloading)**

Trong Java, một lớp có thể có **nhiều constructor** với tham số khác nhau. Java phân biệt chúng dựa vào **kiểu và số lượng tham số**.

> 💡 **Khác với C++:** Không có destructor trong Java. Thay vào đó, GC tự động thu hồi bộ nhớ.

```java
public class Employee {

    // ── Thuộc tính (Fields) ──────────────────────────────────────
    private String id;
    private String fullName;
    private double baseSalary;

    // ── Constructor 1: Mặc định ──────────────────────────────────
    public Employee() {
        this.id = "UNKNOWN";
        this.fullName = "Unnamed employee";
        this.baseSalary = 0.0;
        System.out.println("[Employee] Constructor mặc định: " + this.id);
    }

    // ── Constructor 2: id + tên ──────────────────────────────────
    public Employee(String id, String fullName) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Mã nhân sự không được rỗng!");
        if (fullName == null || fullName.isEmpty())
            throw new IllegalArgumentException("Họ tên không được rỗng!");
        this.id = id;
        this.fullName = fullName;
        this.baseSalary = 0.0;
        System.out.println("[Employee] Tạo: " + this.id);
    }

    // ── Constructor 3: Đầy đủ ────────────────────────────────────
    public Employee(String id, String fullName, double baseSalary) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Mã nhân sự không được rỗng!");
        if (fullName == null || fullName.isEmpty())
            throw new IllegalArgumentException("Họ tên không được rỗng!");
        if (baseSalary < 0)
            throw new IllegalArgumentException("Lương cơ bản không được âm!");
        this.id = id;
        this.fullName = fullName;
        this.baseSalary = baseSalary;
        System.out.println("[Employee] Tạo: " + this.id);
    }

    // ── Getters ──────────────────────────────────────────────────
    public String getId()          { return id; }
    public String getFullName()    { return fullName; }
    public double getBaseSalary()  { return baseSalary; }

    // ── Nạp chồng phương thức (Method Overloading) ───────────────
    // Phiên bản 1: Tăng số tiền cố định
    public void increaseSalary(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Giá trị tăng phải dương!");
        this.baseSalary += amount;
    }

    // Phiên bản 2: byPercentage=true → tăng theo %, false → tăng cố định
    public void increaseSalary(double value, boolean byPercentage) {
        if (value <= 0)
            throw new IllegalArgumentException("Giá trị tăng phải dương!");
        if (byPercentage)
            this.baseSalary += this.baseSalary * (value / 100.0);
        else
            this.baseSalary += value;
    }

    // ── Phương thức có thể ghi đè (Overridable) ──────────────────
    public double calculateMonthlyCost() {
        return baseSalary;
    }

    public void displayInfo() {
        System.out.println("=== Nhân sự ===");
        System.out.println("  Mã       : " + id);
        System.out.println("  Họ tên   : " + fullName);
        System.out.println("  Lương CB : " + baseSalary);
    }
}
```

### Bảng giải thích

| Phần | Mục đích |
|------|----------|
| `private` fields | Đóng gói dữ liệu, chỉ truy cập qua getter |
| 3 constructors | Nạp chồng — cùng tên, khác tham số |
| `IllegalArgumentException` | Thay thế cho `throw std::invalid_argument` của C++ |
| 2 `increaseSalary` | Nạp chồng phương thức — Java chọn đúng phiên bản theo kiểu tham số |
| Không có `virtual` | Java mặc định cho phép override mọi phương thức (trừ `final`) |

---

## 🏗️ Lớp 2: `SoftwareEngineer.java`

### Khái niệm chính: **Kế Thừa (Inheritance) & Ghi Đè (Override)**

```java
public class SoftwareEngineer extends Employee {

    // ── Thuộc tính bổ sung ────────────────────────────────────────
    private String primaryLanguage;     // Không được rỗng
    private double technicalAllowance;  // Không được âm

    // ── Constructor 1: id, tên, ngôn ngữ chính ───────────────────
    public SoftwareEngineer(String id, String fullName, String primaryLanguage) {
        super(id, fullName);  // Gọi constructor của Employee
        if (primaryLanguage == null || primaryLanguage.isEmpty())
            throw new IllegalArgumentException("Ngôn ngữ lập trình không được rỗng!");
        this.primaryLanguage = primaryLanguage;
        this.technicalAllowance = 0.0;
        System.out.println("[SoftwareEngineer] Tạo kỹ sư: " + id + " | " + primaryLanguage);
    }

    // ── Constructor 2: Đầy đủ ────────────────────────────────────
    public SoftwareEngineer(String id, String fullName, double baseSalary,
                            String primaryLanguage, double technicalAllowance) {
        super(id, fullName, baseSalary);  // Gọi constructor 3 của Employee
        if (primaryLanguage == null || primaryLanguage.isEmpty())
            throw new IllegalArgumentException("Ngôn ngữ lập trình không được rỗng!");
        if (technicalAllowance < 0)
            throw new IllegalArgumentException("Phụ cấp kỹ thuật không được âm!");
        this.primaryLanguage = primaryLanguage;
        this.technicalAllowance = technicalAllowance;
        System.out.println("[SoftwareEngineer] Tạo kỹ sư (đầy đủ): " + id);
    }

    // ── Getters ──────────────────────────────────────────────────
    public String getPrimaryLanguage()    { return primaryLanguage; }
    public double getTechnicalAllowance() { return technicalAllowance; }

    // ── Ghi đè calculateMonthlyCost ──────────────────────────────
    @Override
    public double calculateMonthlyCost() {
        return getBaseSalary() + technicalAllowance;
    }

    // ── Ghi đè displayInfo ───────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("=== Kỹ sư phần mềm ===");
        System.out.println("  Mã         : " + getId());
        System.out.println("  Họ tên     : " + getFullName());
        System.out.println("  Lương CB   : " + getBaseSalary());
        System.out.println("  Ngôn ngữ   : " + primaryLanguage);
        System.out.println("  Phụ cấp    : " + technicalAllowance);
        System.out.println("  Tổng/tháng : " + calculateMonthlyCost());
    }
}
```

### Bảng giải thích

| Phần | Mục đích |
|------|----------|
| `extends Employee` | Kế thừa — `SoftwareEngineer` là một `Employee` |
| `super(...)` | Gọi constructor của lớp cha (bắt buộc phải gọi đầu tiên) |
| `@Override` | Annotation xác nhận đang ghi đè — Java báo lỗi nếu viết sai tên |
| `getBaseSalary()` | Dùng getter vì `baseSalary` là `private` trong `Employee` |

---

## 🏗️ Lớp 3: `ProjectTeam.java`

### Khái niệm chính: **Kết Tập (Aggregation) & Nạp Chồng Phương Thức**

> QUAN TRỌNG: `ProjectTeam` lưu **tham chiếu** đến `Employee`. Khi `ProjectTeam` không còn dùng nữa, các `Employee` vẫn tồn tại trong bộ nhớ (GC).

```java
import java.util.ArrayList;
import java.util.List;

public class ProjectTeam {

    // ── Thuộc tính ────────────────────────────────────────────────
    private String projectCode;
    private String projectName;
    private Employee leader;               // Tham chiếu – không sở hữu
    private List<Employee> members;        // Tham chiếu – không sở hữu

    // ── Constructor 1: Chưa có trưởng nhóm ───────────────────────
    public ProjectTeam(String projectCode, String projectName) {
        if (projectCode == null || projectCode.isEmpty())
            throw new IllegalArgumentException("Mã dự án không được rỗng!");
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.leader = null;
        this.members = new ArrayList<>();
        System.out.println("[ProjectTeam] Tạo nhóm: " + projectCode);
    }

    // ── Constructor 2: Có trưởng nhóm ngay khi tạo ───────────────
    public ProjectTeam(String projectCode, String projectName, Employee leader) {
        this(projectCode, projectName);    // Gọi Constructor 1
        this.leader = leader;
        this.members.add(leader);          // Trưởng nhóm tự động vào danh sách
        System.out.println("[ProjectTeam] Trưởng nhóm: " + leader.getId());
    }

    // ── addMember (Overload 1): Thêm thành viên thường ───────────
    public boolean addMember(Employee employee) {
        if (contains(employee.getId())) {
            System.out.println("  [!] " + employee.getId() + " đã có trong nhóm!");
            return false;
        }
        members.add(employee);
        return true;
    }

    // ── addMember (Overload 2): Thêm và tùy chọn đặt làm trưởng nhóm
    public boolean addMember(Employee employee, boolean makeLeader) {
        if (!contains(employee.getId())) {
            members.add(employee);
        }
        if (makeLeader) {
            this.leader = employee;
            System.out.println("  [*] " + employee.getId() + " được đặt làm trưởng nhóm.");
        }
        return true;
    }

    // ── removeMember ─────────────────────────────────────────────
    public boolean removeMember(String employeeId) {
        if (leader != null && leader.getId().equals(employeeId)) {
            System.out.println("  [!] Không thể xóa trưởng nhóm! Hãy đổi trưởng nhóm trước.");
            return false;
        }
        boolean removed = members.removeIf(e -> e.getId().equals(employeeId));
        if (removed)
            System.out.println("  [+] Đã xóa thành viên: " + employeeId);
        else
            System.out.println("  [!] Không tìm thấy: " + employeeId);
        return removed;
    }

    // ── changeLeader ─────────────────────────────────────────────
    public void changeLeader(Employee employee) {
        if (!contains(employee.getId())) {
            members.add(employee);
        }
        this.leader = employee;
        System.out.println("  [*] Trưởng nhóm mới: " + employee.getId());
    }

    // ── contains ─────────────────────────────────────────────────
    public boolean contains(String employeeId) {
        return members.stream().anyMatch(e -> e.getId().equals(employeeId));
    }

    // ── calculateTotalMonthlyCost ─────────────────────────────────
    public double calculateTotalMonthlyCost() {
        double total = 0;
        for (Employee e : members) {
            total += e.calculateMonthlyCost();  // Đa hình!
        }
        return total;
    }

    // ── displayTeam ──────────────────────────────────────────────
    public void displayTeam() {
        System.out.println("\n=== Nhóm dự án: " + projectCode + " - " + projectName + " ===");
        System.out.println("  Trưởng nhóm: " + (leader != null ? leader.getId() : "(Chưa có)"));
        System.out.println("  Thành viên (" + members.size() + "):");
        for (Employee e : members) {
            e.displayInfo();  // Đa hình — gọi đúng phiên bản
        }
        System.out.printf("  Tổng chi phí/tháng: %.2f%n", calculateTotalMonthlyCost());
    }
}
```

### Bảng giải thích

| Phần | Mục đích |
|------|----------|
| `List<Employee> members` | Lưu tham chiếu → Kết tập, không phải thành phần |
| `this(...)` trong Constructor 2 | Gọi Constructor 1 — tránh lặp code |
| `members.removeIf(...)` | Lambda expression để xóa theo điều kiện |
| `e.calculateMonthlyCost()` | **Đa hình** — tự động gọi phiên bản đúng |
| Không `new Employee` ở đây | Không tạo đối tượng → không sở hữu → kết tập |

---

## 🧪 Lớp Test: `Main.java`

Kiểm thử theo đúng 15 kịch bản của đề bài:

```java
public class Main {
    public static void main(String[] args) {

        System.out.println("══════════════════════════════════════");
        System.out.println("  BƯỚC 1-2: Tạo Employee & SoftwareEngineer");
        System.out.println("══════════════════════════════════════");

        // 1. Hai Employee với hai constructor khác nhau
        Employee e1 = new Employee("NV001", "Nguyễn Văn An");           // Constructor 2
        Employee e2 = new Employee("NV002", "Trần Thị Bình", 15000000); // Constructor 3

        // 2. Hai SoftwareEngineer với hai constructor khác nhau
        SoftwareEngineer se1 = new SoftwareEngineer("KS001", "Lê Văn Cường", "Java");
        SoftwareEngineer se2 = new SoftwareEngineer("KS002", "Phạm Thị Dung",
                                                     20000000, "Python", 3000000);

        System.out.println("\n══════════════════════════════════════");
        System.out.println("  BƯỚC 3-4: Tăng lương");
        System.out.println("══════════════════════════════════════");

        // 3. Tăng cố định (overload 1)
        e1.increaseSalary(2000000);
        System.out.println("  e1 lương sau tăng: " + e1.getBaseSalary());

        // 4. Tăng theo % (overload 2)
        se1.increaseSalary(10, true);  // tăng 10%
        System.out.println("  se1 lương sau tăng: " + se1.getBaseSalary());

        System.out.println("\n══════════════════════════════════════");
        System.out.println("  BƯỚC 5-10: Tạo nhóm và thêm thành viên");
        System.out.println("══════════════════════════════════════");

        // 5. Nhóm không có trưởng nhóm
        ProjectTeam team1 = new ProjectTeam("DA001", "Hệ thống quản lý nhân sự");

        // 6. Thêm e1 bình thường
        team1.addMember(e1);

        // 7. Thêm se1 và đặt làm trưởng nhóm
        team1.addMember(se1, true);

        // 8. Thử thêm lại e1 (đã tồn tại — phải bị từ chối)
        team1.addMember(e1);

        team1.addMember(e2);
        team1.addMember(se2);

        // 9. Hiển thị bằng đa hình
        team1.displayTeam();

        // 10. Tổng chi phí
        System.out.printf("%n  Tổng chi phí nhóm 1: %.2f%n", team1.calculateTotalMonthlyCost());

        System.out.println("\n══════════════════════════════════════");
        System.out.println("  BƯỚC 11-12: Xóa và đổi trưởng nhóm");
        System.out.println("══════════════════════════════════════");

        // 11. Thử xóa trưởng nhóm (phải bị từ chối)
        team1.removeMember("KS001");

        // 12. Đổi trưởng nhóm rồi xóa người cũ
        team1.changeLeader(e2);
        team1.removeMember("KS001");  // Giờ xóa được

        System.out.println("\n══════════════════════════════════════");
        System.out.println("  BƯỚC 13-15: Kết tập nhiều nhóm");
        System.out.println("══════════════════════════════════════");

        // 13. Nhóm 2 dùng lại se2 đã ở nhóm 1
        {
            ProjectTeam team2 = new ProjectTeam("DA002", "Ứng dụng mobile", se2);
            team2.addMember(e1);
            team2.displayTeam();

            // 14. Kết thúc khối lệnh → team2 mất tham chiếu, GC sẽ thu hồi
            System.out.println("\n  [Khối kết thúc → team2 mất tham chiếu]");
        }

        // 15. se2 vẫn còn sống sau khi team2 mất tham chiếu
        System.out.println("\n  [Kiểm tra] se2 vẫn tồn tại: " + se2.getId());
        se2.displayInfo();
    }
}
```

---

## 🔄 So Sánh C++ vs Java

| Khái niệm | C++ | Java |
|-----------|-----|------|
| Destructor | `~ClassName()` | Không có (GC tự động) |
| Virtual method | `virtual void foo()` | Mặc định đã override được |
| Override annotation | Không bắt buộc | `@Override` (khuyến nghị) |
| Pointer/Reference | `Employee*`, `Employee&` | Mọi object đều là reference |
| Kết tập | `Employee*` (không sở hữu) | Lưu reference bình thường |
| Kiểm tra null | Con trỏ null | `null` |
| Exception | `throw std::invalid_argument` | `throw new IllegalArgumentException` |
| Thừa kế | `class SE : public Employee` | `class SE extends Employee` |
| Gọi constructor cha | Trong initializer list | `super(...)` dòng đầu tiên |

---

## ⚡ Lưu Ý Quan Trọng

**Java không có destructor** như C++. Java dùng **Garbage Collector** tự động thu hồi bộ nhớ khi không còn tham chiếu nào đến object. Đây là lý do bước 14-15 trong kịch bản kiểm thử vẫn hoạt động đúng.

**ProjectTeam không được** tạo `new Employee(...)` bên trong — phải nhận đối tượng từ bên ngoài để thể hiện **kết tập** (aggregation), không phải **thành phần** (composition).

**Dùng `@Override`** trước mọi phương thức ghi đè. Nếu bạn viết sai tên phương thức, Java sẽ báo lỗi compile-time thay vì tạo phương thức mới không mong muốn.

---

## 🚀 Biên Dịch và Chạy

```bash
# Từ thư mục lab_3_4/class/
javac *.java

# Chạy
java Main
```

---

*Lab 3-4 — Lập Trình Hướng Đối Tượng*
