import java.util.ArrayList;
import java.util.List;

public class ProjectTeam {
    // Attributes:
    private String projectCode;
    private String projectName;
    private Employee teamLeader;
    private List<Employee> teamMembers;
    // Liên kết không sở hữu là aggregation (khác với composition, nó sẽ không cho
    // phép lớp cha được gọi constructor lớp con ngay trong chính nó mà chỉ được lưu
    // lại biến tham chiếu truyền vào.

    // Constructors:
    public ProjectTeam(String projectCode, String projectName) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.teamMembers = new ArrayList<>();
    }

    // 2. Tạo nhóm có sẵn trưởng nhóm
    public ProjectTeam(String projectCode, String projectName, Employee teamLeader) {
        // Gọi lại Constructor 1 ở trên để tái sử dụng code gán code/name và khởi tạo
        // List
        this(projectCode, projectName);
        this.teamLeader = teamLeader;
        this.teamMembers.add(teamLeader);
    }

    // Methods

    // Hàm kiểm tra nhân viên đã có trong nhóm chưa
    public boolean contains(String employeeId) {
        for (Employee e : teamMembers) {
            if (e.getId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    // Overloading Methods:
    public boolean addMember(Employee employee) {
        // Kiểm tra xem đã có trong nhóm chưa
        if (contains(employee.getId())) {
            System.out.println("Thành viên " + employee.getId() + " đã tồn tại trong nhóm.");
            return false;
        }

        this.teamMembers.add(employee);
        System.out.println("Đã thêm thành viên: " + employee.getId());
        return true;
    }

    public boolean addMember(Employee employee, boolean leaderFlag) {
        // Nếu chưa có trong nhóm thì mới add vào List
        if (!contains(employee.getId())) {
            this.teamMembers.add(employee);
        }

        // Nếu leaderFlag = true thì đặt làm trưởng nhóm
        if (leaderFlag) {
            this.teamLeader = employee;
            System.out.println(employee.getId() + " đã được bổ nhiệm làm Trưởng nhóm.");
        } else {
            System.out.println("Đã thêm thành viên: " + employee.getId());
        }

        return true;
    }

    // Methods:
    // Ràng buộc: Không được xóa trưởng nhóm khi chưa chọn trưởng nhóm thay thế
    public boolean removeMember(String employeeId) {
        if (teamLeader != null && teamLeader.getId().equals(employeeId)) {
            System.out.println("Hãy đổi leader trước khi xóa nhân sự này.");
            return false;
        }
        boolean isRemoved = teamMembers.removeIf(e -> e.getId().equals(employeeId));
        if (isRemoved) {
            System.out.println("Đã xóa nhân sự: " + employeeId);
        } else {
            System.out.println("Không tìm thấy nhân sự: " + employeeId);
        }
        return isRemoved;
    }

    public void changeLeader(Employee employee) {
        // Ràng buộc: Trưởng nhóm mới phải được thêm vào nhóm nếu chưa phải thành viên
        if (!contains(employee.getId())) {
            (this.teamMembers).add(employee);
        }
        this.teamLeader = employee;
        System.out.println("Đã thay đổi leader thành: " + employee.getId());
    }

    public double calculateTotalMonthlyCost() {
        double totalCost = 0.0;
        for (Employee e : teamMembers) {
            // Polymorphism Method:
            // Tự động gọi hàm calculateMonthlyCost của Employee hoặc SoftwareEngineer
            totalCost += e.calculateMonthlyCost();
        }
        return totalCost;
    }

    public void displayTeam() {
        System.out.println("Thông tin nhóm " + projectCode + " : " + projectName);

        if (teamLeader != null) {
            System.out.println("Trưởng nhóm: " + teamLeader.getId());
        } else {
            System.out.println("Trưởng nhóm: Chưa có");
        }

        System.out.println("Danh sách " + teamMembers.size() + " thành viên:");
        for (Employee e : teamMembers) {
            e.displayInfo(); // Đa hình
        }

        System.out.println("Tổng chi phí nhóm: " + calculateTotalMonthlyCost());
    }
}
