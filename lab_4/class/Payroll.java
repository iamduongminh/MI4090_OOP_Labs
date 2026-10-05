import java.util.ArrayList;
import java.util.List;

public class Payroll {

    private String period;
    private List<Employee> employeeList;

    // Constructors
    public Payroll(String period) {

        if (period == null || period.isBlank())
            throw new IllegalArgumentException("Ky luong khong duoc rong.");
        this.period = period;
        this.employeeList = new ArrayList<>();
    }

    // Methods:
    public boolean addEmployee(Employee employee) {
        // Ràng buộc 1: Không có hai nhân sự cùng mã trong một bảng lương.
        if (findEmployee(employee.getEmployeeId()) != null) {
            System.out.println("Loi: Ma nhan su " + employee.getEmployeeId() + " da ton tai trong bang luong.");
            return false;
        }
        employeeList.add(employee);
        return true;
    }

    public Employee findEmployee(String employeeId) {
        for (Employee e : employeeList) {
            if (e.getEmployeeId().equals(employeeId))
                return e;
        }
        return null;
    }

    public double calculateTotalPayroll() {
        double total = 0;
        for (Employee e : employeeList)
            total += e.calculateGrossPay();
        return total;
    }

    public double calculatePayrollByDepartment(String department) {
        if (employeeList.isEmpty()) {
            System.out.println("Danh sach nhan su trong.");
            return 0;
        }
        double total = 0;
        boolean found = false;
        for (Employee e : employeeList) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                total += e.calculateGrossPay();
                found = true;
            }
        }
        if (!found)
            System.out.println("Khong co nhan su nao thuoc phong: " + department);
        return total;
    }

    public Employee findHighestPaidEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("Danh sach nhan su trong.");
            return null;
        }
        Employee highest = employeeList.get(0);
        for (int i = 1; i < employeeList.size(); i++) {
            if (employeeList.get(i).calculateGrossPay() > highest.calculateGrossPay())
                highest = employeeList.get(i);
        }
        return highest;
    }

    public void displayPayroll() {
        System.out.println("\n==================================================");
        System.out.println("BANG LUONG KY: " + period);
        System.out.println("So nhan su: " + employeeList.size());
        System.out.println("==================================================");
        if (employeeList.isEmpty()) {
            System.out.println("(Chua co nhan su nao.)");
        } else {
            for (Employee e : employeeList) {
                e.displayPayrollInfo(); // Da hinh
                System.out.println("--------------------------------------------------");
            }
        }
        System.out.printf("TONG BANG LUONG: %,.0f VND%n", calculateTotalPayroll());
        System.out.println("==================================================\n");
    }

    // Getter
    public String getPeriod() {
        return period;
    }

    public int getEmployeeCount() {
        return employeeList.size();
    }
}
