# BÁO CÁO KẾT QUẢ KIỂM THỬ — LAB 3 & 4
**Môn học:** Lập trình Hướng đối tượng  
**Ngôn ngữ:** Java  
**Các lớp:** `Employee`, `SoftwareEngineer`, `ProjectTeam`  

---

## Tổng quan kiến trúc

```
Employee                        (Lớp cha)
│   - id, fullName, baseSalary
│   + 3 Constructors (overload)
│   + increaseSalary() (overload)
│   + calculateMonthlyCost()
│   + displayInfo()
│
└── SoftwareEngineer            (Kế thừa Employee)
        - primaryLanguage, technicalAllowance
        + 2 Constructors
        + calculateMonthlyCost()  ← @Override
        + displayInfo()           ← @Override

ProjectTeam                     (Kết tập Employee — Aggregation)
        - projectCode, projectName
        - teamLeader, teamMembers (List<Employee>)
        + addMember() (overload)
        + removeMember()
        + changeLeader()
        + calculateTotalMonthlyCost()
        + displayTeam()
```

---

## I. CÁC KỊCH BẢN KIỂM THỬ CHÍNH (15 Kịch bản)

---

### Kịch bản 1 — Tạo hai Employee bằng hai Constructor khác nhau

**Mục tiêu:** Kiểm tra tính năng **Nạp chồng Constructor** (Constructor Overloading).

**Code kiểm thử:**
```java
Employee e1 = new Employee("NV001", "Nguyễn Văn An");             // 2 tham số
Employee e2 = new Employee("NV002", "Trần Thị Bình", 15_000_000); // 3 tham số
```

**Kết quả thực tế:**
```
Khởi tạo Employee với id = NV001: Nguyễn Văn An
Khởi tạo Employee đầy đủ với id = NV002: Trần Thị Bình với LCB là1.5E7
```

**Phân tích:**
- `e1` dùng Constructor 2 tham số → lương cơ bản mặc định = `0.0`
- `e2` dùng Constructor 3 tham số → lương cơ bản = `15,000,000`
- Hai constructor khác nhau được phân biệt qua số lượng tham số truyền vào

**Kết quả:** ✅ PASS

---

### Kịch bản 2 — Tạo hai SoftwareEngineer bằng hai Constructor khác nhau

**Mục tiêu:** Kiểm tra **Kế thừa** và **Nạp chồng Constructor** ở lớp con.

**Code kiểm thử:**
```java
SoftwareEngineer se1 = new SoftwareEngineer("KS001", "Lê Văn Cường", "Java");
SoftwareEngineer se2 = new SoftwareEngineer("KS002", "Phạm Thị Dung", 20_000_000, "Python", 3_000_000);
```

**Kết quả thực tế:**
```
Khởi tạo Employee với id = KS001: Lê Văn Cường
Tạo SE KS001 : Lê Văn Cường dùng Java
Khởi tạo Employee đầy đủ với id = KS002: Phạm Thị Dung với LCB là2.0E7
Tạo SE KS002 : Phạm Thị Dung với LCB là 2.0E7 dùng Python
```

**Phân tích:**
- Mỗi lần tạo `SoftwareEngineer`, Constructor lớp cha `Employee` được gọi trước (qua `super()`), sau đó mới đến Constructor lớp con — đúng thứ tự kế thừa.
- `se1` dùng Constructor 3 tham số (không có lương, không có phụ cấp)
- `se2` dùng Constructor 5 tham số (đầy đủ thông tin)

**Kết quả:** ✅ PASS

---

### Kịch bản 3 — Tăng lương một nhân sự bằng số tiền cố định

**Mục tiêu:** Kiểm tra **Nạp chồng phương thức** `increaseSalary(double amount)`.

**Code kiểm thử:**
```java
System.out.println("Lương e1 trước: " + e1.getBaseSalary());
e1.increaseSalary(2_000_000);
System.out.println("Lương e1 sau khi tăng 2,000,000: " + e1.getBaseSalary());
```

**Kết quả thực tế:**
```
Lương e1 trước: 0.0
Lương e1 sau khi tăng 2,000,000: 2000000.0
```

**Phân tích:**
- Lương `e1` trước = `0.0` (vì được tạo bằng Constructor 2 tham số)
- Sau khi tăng thêm `2,000,000` → lương = `2,000,000` ✓

**Kết quả:** ✅ PASS

---

### Kịch bản 4 — Tăng lương một nhân sự khác theo phần trăm

**Mục tiêu:** Kiểm tra **Nạp chồng phương thức** `increaseSalary(double percent, boolean isPercent)`.

**Code kiểm thử:**
```java
se1.increaseSalary(10_000_000); // Thiết lập lương ban đầu cho se1
System.out.println("Lương se1 trước: " + se1.getBaseSalary());
se1.increaseSalary(10, true);   // Tăng 10%
System.out.println("Lương se1 sau khi tăng 10%: " + se1.getBaseSalary());
```

**Kết quả thực tế:**
```
Lương se1 trước: 1.0E7
Lương se1 sau khi tăng 10%: 1.1E7
```

**Phân tích:**
- Lương `se1` trước = `10,000,000`
- Tăng 10% → `10,000,000 × 10% = 1,000,000` → Lương sau = `11,000,000` ✓
- Phân biệt được với Kịch bản 3 nhờ chữ ký hàm khác (overload)

**Kết quả:** ✅ PASS

---

### Kịch bản 5 — Tạo nhóm dự án không có trưởng nhóm

**Mục tiêu:** Kiểm tra Constructor 2 tham số của `ProjectTeam` (không có leader).

**Code kiểm thử:**
```java
ProjectTeam team1 = new ProjectTeam("DA001", "Hệ thống quản lý nhân sự");
```

**Kết quả thực tế:**
```
(không có output — constructor chưa có lệnh in)
```

**Phân tích:**
- Nhóm được tạo thành công với danh sách thành viên rỗng (`ArrayList` mới)
- `teamLeader = null` (chưa có trưởng nhóm)
- ⚠️ Constructor chưa in ra thông báo (có thể bổ sung `System.out.println()` để rõ hơn)

**Kết quả:** ✅ PASS (logic đúng)

---

### Kịch bản 6 — Thêm một nhân sự vào nhóm bằng `addMember(employee)`

**Mục tiêu:** Kiểm tra **Nạp chồng** `addMember` phiên bản 1 tham số.

**Code kiểm thử:**
```java
team1.addMember(e1);
```

**Kết quả thực tế:**
```
Đã thêm thành viên: NV001
```

**Phân tích:**
- `e1` chưa có trong nhóm → được thêm vào thành công
- Hàm trả về `true`

**Kết quả:** ✅ PASS

---

### Kịch bản 7 — Thêm kỹ sư và bổ nhiệm làm trưởng nhóm

**Mục tiêu:** Kiểm tra **Nạp chồng** `addMember(employee, boolean)` phiên bản 2 tham số.

**Code kiểm thử:**
```java
team1.addMember(se1, true); // true = bổ nhiệm làm trưởng nhóm
```

**Kết quả thực tế:**
```
KS001 đã được bổ nhiệm làm Trưởng nhóm.
```

**Phân tích:**
- `se1` được thêm vào danh sách thành viên
- Đồng thời `teamLeader = se1` được gán
- Tham số `true` kích hoạt logic bổ nhiệm trưởng nhóm

**Kết quả:** ✅ PASS

---

### Kịch bản 8 — Thử thêm lại thành viên đã tồn tại (phải bị từ chối)

**Mục tiêu:** Kiểm tra ràng buộc **không được thêm thành viên trùng lặp**.

**Code kiểm thử:**
```java
boolean result = team1.addMember(e1); // e1 đã có trong nhóm từ KB 6
System.out.println("Kết quả (false = bị từ chối): " + result);
```

**Kết quả thực tế:**
```
Thành viên NV001 đã tồn tại trong nhóm.
Kết quả (false = bị từ chối): false
Đã thêm thành viên: NV002
Đã thêm thành viên: KS002
```

**Phân tích:**
- Thêm lại `e1` → hàm `contains()` phát hiện trùng → trả về `false` ✓
- `e2` và `se2` chưa có → được thêm thành công

**Kết quả:** ✅ PASS

---

### Kịch bản 9 — Hiển thị danh sách nhóm bằng lời gọi Đa hình

**Mục tiêu:** Kiểm tra **Đa hình** (Polymorphism) — `displayInfo()` tự động gọi đúng phiên bản.

**Code kiểm thử:**
```java
team1.displayTeam(); // Bên trong gọi e.displayInfo() cho mỗi thành viên
```

**Kết quả thực tế:**
```
Thông tin nhóm DA001 : Hệ thống quản lý nhân sự
Trưởng nhóm: KS001
Danh sách 4 thành viên:
Thông tin nhân sự:
- Mã: NV001
- Họ tên: Nguyễn Văn An
- Lương cơ bản: 2000000.0
Thông tin nhân sự:
- Mã: KS001
- Họ tên: Lê Văn Cường
- Lương cơ bản: 1.1E7
- Ngôn ngữ lập trình Java
- Phụ cấp kỹ thuật 0.0
- Tổng lương tháng: 1.1E7
Thông tin nhân sự:
- Mã: NV002
- Họ tên: Trần Thị Bình
- Lương cơ bản: 1.5E7
Thông tin nhân sự:
- Mã: KS002
- Họ tên: Phạm Thị Dung
- Lương cơ bản: 2.0E7
- Ngôn ngữ lập trình Python
- Phụ cấp kỹ thuật 3000000.0
- Tổng lương tháng: 2.3E7
Tổng chi phí nhóm: 5.1E7
```

**Phân tích:**
- `Employee` (`NV001`, `NV002`) → in 3 dòng ← gọi `Employee.displayInfo()`
- `SoftwareEngineer` (`KS001`, `KS002`) → in 6 dòng ← gọi `SoftwareEngineer.displayInfo()`
- Cùng một lời gọi `e.displayInfo()` nhưng tự phân biệt được kiểu đối tượng → **đa hình hoạt động đúng** ✓

**Kết quả:** ✅ PASS

---

### Kịch bản 10 — Tính tổng chi phí nhân sự hằng tháng

**Mục tiêu:** Kiểm tra `calculateTotalMonthlyCost()` bằng **đa hình** `calculateMonthlyCost()`.

**Kết quả thực tế:** `Tổng chi phí: 5.1E7`

**Phân tích (kiểm chứng bằng tay):**

| Nhân sự | Loại | Lương cơ bản | Phụ cấp | Tổng/tháng |
|---|---|---|---|---|
| NV001 | Employee | 2,000,000 | — | 2,000,000 |
| KS001 | SoftwareEngineer | 11,000,000 | 0 | 11,000,000 |
| NV002 | Employee | 15,000,000 | — | 15,000,000 |
| KS002 | SoftwareEngineer | 20,000,000 | 3,000,000 | 23,000,000 |
| **Tổng** | | | | **51,000,000 (= 5.1E7)** |

**Kết quả:** ✅ PASS

---

### Kịch bản 11 — Thử xóa trưởng nhóm hiện tại (phải bị từ chối)

**Mục tiêu:** Kiểm tra ràng buộc **không được xóa trưởng nhóm** khi chưa đổi người thay thế.

**Kết quả thực tế:**
```
Hãy đổi leader trước khi xóa nhân sự này.
Kết quả (false = bị từ chối): false
```

**Phân tích:** `KS001` đang là `teamLeader` → `removeMember` phát hiện và từ chối, trả về `false` ✓

**Kết quả:** ✅ PASS

---

### Kịch bản 12 — Đổi trưởng nhóm rồi xóa người từng là trưởng nhóm

**Mục tiêu:** Kiểm tra luồng đổi trưởng nhóm rồi mới xóa thành viên cũ.

**Kết quả thực tế:**
```
Đã thay đổi leader thành: NV002
Đã xóa nhân sự: KS001
```

**Phân tích:** Sau `changeLeader(e2)`, `KS001` chỉ còn là thành viên thường → `removeMember` cho phép xóa ✓

**Kết quả:** ✅ PASS

---

### Kịch bản 13, 14, 15 — Kết tập nhiều nhóm (Aggregation)

**Mục tiêu:** Chứng minh một nhân sự có thể thuộc nhiều nhóm và vẫn tồn tại khi nhóm bị hủy.

**Kết quả thực tế:**
```
Đã thêm thành viên: NV001
Thông tin nhóm DA002 : Ứng dụng mobile
Trưởng nhóm: KS002
Danh sách 2 thành viên:
  [KS002 - Phạm Thị Dung — tổng lương 2.3E7]
  [NV001 - Nguyễn Văn An — lương 2000000.0]
Tổng chi phí nhóm: 2.5E7
>>> Kết thúc khối lệnh — team2 mất tham chiếu (bị GC thu hồi)

se2 vẫn sống: KS002
[Thông tin đầy đủ của se2 vẫn in ra bình thường]
```

**Phân tích:**
- `se2` và `e1` tham gia đồng thời cả `team1` và `team2` ✓
- Sau khi `team2` ra khỏi scope, `se2` **vẫn tồn tại** → Chứng minh liên kết **không sở hữu** (Aggregation) ✓

**Kết quả:** ✅ PASS

---

## II. CÁC TRƯỜNG HỢP BIÊN

| # | Mô tả | Code | Kết quả |
|---|---|---|---|
| Biên 1 | Tăng lương bằng giá trị âm | `increaseSalary(-500_000)` | `Lương phải tăng theo số dương!` ✅ |
| Biên 2 | Tạo Employee với mã rỗng | `new Employee("", ...)` | `Mã nhân sự phải khác rỗng!` ✅ |
| Biên 3 | SoftwareEngineer với ngôn ngữ rỗng | `new SoftwareEngineer(..., "")` | `Ngôn ngữ lập trình không được trống!` ✅ |
| Biên 4 | SoftwareEngineer với phụ cấp âm | `new SoftwareEngineer(..., -1_000_000)` | `Phụ cấp kỹ thuật không âm!` ✅ |
| Biên 5 | `changeLeader` với người ngoài nhóm | `changeLeader(eNgoai)` | Tự động thêm vào nhóm trước ✅ |
| Biên 6 | Tăng lương theo 0% | `increaseSalary(0, true)` | `Giá trị tăng phải dương!` ✅ |

> **Lưu ý Biên 3 & 4:** Constructor `Employee` (lớp cha) sẽ in ra trước khi exception được ném từ lớp con `SoftwareEngineer`. Đây là hành vi **đúng** của Java — `super()` bắt buộc phải được gọi trước mọi validation ở lớp con.

---

## III. TỔNG KẾT

| Hạng mục | Số lượng | Kết quả |
|---|---|---|
| Kịch bản chính | 15 | ✅ 15/15 PASS |
| Trường hợp biên | 6 | ✅ 6/6 PASS |
| **Tổng cộng** | **21** | **✅ 21/21 PASS** |

### Các khái niệm OOP được minh chứng

| Khái niệm | Nơi minh chứng |
|---|---|
| **Kế thừa** (Inheritance) | `SoftwareEngineer extends Employee` |
| **Đa hình** (Polymorphism) | `displayInfo()`, `calculateMonthlyCost()` tự gọi đúng phiên bản (KB 9, 10) |
| **Nạp chồng** (Overloading) | `addMember()` 2 phiên bản (KB 6, 7), `increaseSalary()` 2 phiên bản (KB 3, 4) |
| **Kết tập** (Aggregation) | `ProjectTeam` lưu tham chiếu `Employee`, không sở hữu (KB 13-15) |
| **Đóng gói** (Encapsulation) | Tất cả thuộc tính `private` + Getter |
| **Ràng buộc dữ liệu** | Validation qua `IllegalArgumentException` (Biên 1-4, 6) |
