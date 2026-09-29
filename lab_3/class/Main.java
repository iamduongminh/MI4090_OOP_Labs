// Họ và tên: Dương Quang Minh
// MSSV: 20237362

public class Main {
    public static void main(String[] args) {

        // ================================================================
        // KỊCH BẢN 1: Tạo hai Employee bằng hai constructor khác nhau
        // ================================================================
        System.out.println("===== KỊCH BẢN 1: Tạo Employee =====");
        Employee e1 = new Employee("NV001", "Nguyễn Văn An"); // Constructor 2 tham số
        Employee e2 = new Employee("NV002", "Trần Thị Bình", 15_000_000); // Constructor 3 tham số

        // ================================================================
        // KỊCH BẢN 2: Tạo hai SoftwareEngineer bằng hai constructor khác nhau
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 2: Tạo SoftwareEngineer =====");
        SoftwareEngineer se1 = new SoftwareEngineer("KS001", "Lê Văn Cường", "Java");
        SoftwareEngineer se2 = new SoftwareEngineer("KS002", "Phạm Thị Dung", 20_000_000, "Python", 3_000_000);

        // ================================================================
        // KỊCH BẢN 3: Tăng lương một nhân sự bằng số tiền cố định
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 3: Tăng lương cố định =====");
        System.out.println("Lương e1 trước: " + e1.getBaseSalary());
        e1.increaseSalary(2_000_000); // Overload 1 tham số
        System.out.println("Lương e1 sau khi tăng 2,000,000: " + e1.getBaseSalary());

        // Thiết lập lương cơ bản cho se1 (vì Constructor 2 tham số đang để lương mặc
        // định là 0)
        // Để Kịch bản 4 tăng theo % hoạt động hợp lý
        se1.increaseSalary(10_000_000);

        // ================================================================
        // KỊCH BẢN 4: Tăng lương một nhân sự khác theo phần trăm
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 4: Tăng lương theo % =====");
        System.out.println("Lương se1 trước: " + se1.getBaseSalary());
        se1.increaseSalary(10, true); // Overload 2 tham số, tăng 10%
        System.out.println("Lương se1 sau khi tăng 10%: " + se1.getBaseSalary());

        // ================================================================
        // KỊCH BẢN 5: Tạo nhóm dự án không có trưởng nhóm
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 5: Tạo nhóm chưa có trưởng =====");
        ProjectTeam team1 = new ProjectTeam("DA001", "Hệ thống quản lý nhân sự");

        // ================================================================
        // KỊCH BẢN 6: Thêm một nhân sự vào nhóm bằng addMember(employee)
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 6: addMember(employee) =====");
        team1.addMember(e1);

        // ================================================================
        // KỊCH BẢN 7: Thêm kỹ sư bằng addMember(employee, true) làm trưởng nhóm
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 7: addMember(employee, true) =====");
        team1.addMember(se1, true);

        // ================================================================
        // KỊCH BẢN 8: Thử thêm lại một thành viên đã tồn tại (phải bị từ chối)
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 8: Thêm lại thành viên đã có =====");
        boolean result = team1.addMember(e1);
        System.out.println("Kết quả (false = bị từ chối): " + result);

        // Thêm e2 và se2 để nhóm đầy đủ
        team1.addMember(e2);
        team1.addMember(se2);

        // ================================================================
        // KỊCH BẢN 9: Hiển thị danh sách bằng lời gọi đa hình
        // displayInfo() sẽ tự gọi đúng: Employee.displayInfo() hoặc
        // SoftwareEngineer.displayInfo()
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 9: Hiển thị bằng đa hình =====");
        team1.displayTeam();

        // ================================================================
        // KỊCH BẢN 10: Tính tổng chi phí nhân sự hằng tháng
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 10: Tổng chi phí hằng tháng =====");
        System.out.println("Tổng chi phí: " + team1.calculateTotalMonthlyCost());

        // ================================================================
        // KỊCH BẢN 11: Thử xóa trưởng nhóm hiện tại (phải bị từ chối)
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 11: Xóa trưởng nhóm (phải bị từ chối) =====");
        boolean removed = team1.removeMember("KS001"); // se1 đang là trưởng nhóm
        System.out.println("Kết quả (false = bị từ chối): " + removed);

        // ================================================================
        // KỊCH BẢN 12: Đổi trưởng nhóm rồi xóa người từng là trưởng nhóm
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 12: Đổi trưởng nhóm rồi xóa người cũ =====");
        team1.changeLeader(e2); // e2 trở thành trưởng nhóm mới
        team1.removeMember("KS001"); // Giờ se1 không còn là trưởng → xóa được

        // ================================================================
        // KỊCH BẢN 13: Nhóm thứ 2 dùng lại nhân sự đã có ở nhóm 1 (Aggregation)
        // ================================================================
        System.out.println("\n===== KỊCH BẢN 13 + 14 + 15: Kết tập nhiều nhóm =====");

        // Dùng khối lệnh {} để mô phỏng vòng đời của team2
        {
            ProjectTeam team2 = new ProjectTeam("DA002", "Ứng dụng mobile", se2); // se2 đã ở team1
            team2.addMember(e1); // e1 cũng đã ở team1 — một nhân sự có thể ở nhiều nhóm

            // KỊCH BẢN 14: Hiển thị team2 trước khi hủy
            team2.displayTeam();
            System.out.println(">>> Kết thúc khối lệnh — team2 mất tham chiếu (bị GC thu hồi)");
        }

        // KỊCH BẢN 15: Chứng minh se2 vẫn tồn tại dù team2 đã biến mất
        System.out.println("\n===== KỊCH BẢN 15: se2 vẫn tồn tại sau khi team2 bị hủy =====");
        System.out.println("se2 vẫn sống: " + se2.getId());
        se2.displayInfo();

        // ================================================================
        // CÁC TRƯỜNG HỢP BIÊN
        // ================================================================
        System.out.println("\n===== TRƯỜNG HỢP BIÊN =====");

        // Biên 1: Tăng lương bằng số âm (phải ném exception)
        System.out.println("\n[Biên 1] Tăng lương bằng giá trị âm:");
        try {
            e2.increaseSalary(-500_000);
        } catch (IllegalArgumentException ex) {
            System.out.println("Lỗi bắt được: " + ex.getMessage());
        }

        // Biên 2: Tạo Employee với mã rỗng (phải ném exception)
        System.out.println("\n[Biên 2] Tạo Employee với mã rỗng:");
        try {
            Employee eRong = new Employee("", "Họ Tên Nào Đó");
        } catch (IllegalArgumentException ex) {
            System.out.println("Lỗi bắt được: " + ex.getMessage());
        }

        // Biên 3: Tạo SoftwareEngineer với ngôn ngữ rỗng (phải ném exception)
        System.out.println("\n[Biên 3] Tạo SoftwareEngineer với ngôn ngữ rỗng:");
        try {
            SoftwareEngineer seRong = new SoftwareEngineer("KS099", "Nguyễn Văn X", "");
        } catch (IllegalArgumentException ex) {
            System.out.println("Lỗi bắt được: " + ex.getMessage());
        }

        // Biên 4: Tạo SoftwareEngineer với phụ cấp âm (phải ném exception)
        System.out.println("\n[Biên 4] Tạo SoftwareEngineer với phụ cấp âm:");
        try {
            SoftwareEngineer seAm = new SoftwareEngineer("KS098", "Trần Văn Y", 10_000_000, "C++", -1_000_000);
        } catch (IllegalArgumentException ex) {
            System.out.println("Lỗi bắt được: " + ex.getMessage());
        }

        // Biên 5: changeLeader với người chưa có trong nhóm (phải tự động add vào)
        System.out.println("\n[Biên 5] changeLeader với người ngoài nhóm:");
        Employee eNgoai = new Employee("NV099", "Người Ngoài Nhóm", 12_000_000);
        System.out.println("eNgoai có trong team1 trước: " + team1.contains("NV099"));
        team1.changeLeader(eNgoai); // Phải tự add eNgoai vào nhóm rồi mới đặt làm TN
        System.out.println("eNgoai có trong team1 sau: " + team1.contains("NV099"));

        // Biên 6: Tăng lương theo % bằng 0 (phải ném exception)
        System.out.println("\n[Biên 6] Tăng lương theo 0%:");
        try {
            e1.increaseSalary(0, true);
        } catch (IllegalArgumentException ex) {
            System.out.println("Lỗi bắt được: " + ex.getMessage());
        }
    }
}
