public class Main {
    public static void main(String[] args) {

        // ================================================================
        // BUOC 1: Tao cac doi tuong nhan su theo du lieu kiem thu trong PDF
        // ================================================================

        // E001 — Nhan vien luong co dinh
        SalariedEmployee e1 = new SalariedEmployee(
            "E001", "Nguyen Minh An", "Dao tao", 15_000_000, 2_000_000);
        e1.addBonus(1_000_000, "Thuong hoan thanh du an");
        // Thu nhap mong doi: 15.000.000 + 2.000.000 + 1.000.000 = 18.000.000

        // E002 — Nhan vien theo gio, khong co gio vuot nguong
        HourlyEmployee e2 = new HourlyEmployee(
            "E002", "Tran Thu Binh", "Ho tro", 100_000, 150);
        e2.addBonus(500_000);
        // Thu nhap mong doi: 150 x 100.000 + 500.000 = 15.500.000

        // E003 — Nhan vien theo gio, co gio vuot nguong
        HourlyEmployee e3 = new HourlyEmployee(
            "E003", "Le Hoang Chi", "Ho tro", 100_000, 170);
        // Khong co thuong
        // Thu nhap mong doi: 160x100.000 + 10x100.000x1.5 = 17.500.000

        // E004 — Nhan vien kinh doanh
        SalesEmployee e4 = new SalesEmployee(
            "E004", "Pham Quoc Dung", "Kinh doanh",
            8_000_000, 200_000_000, 0.05);
        e4.addBonus(0.02, 50_000_000, "Thuong vuot chi tieu doanh so");
        // Thu nhap mong doi: 8.000.000 + 200.000.000x5% + 50.000.000x2% = 19.000.000

        // ================================================================
        // BUOC 2: Them vao bang luong
        // ================================================================
        Payroll payroll = new Payroll("2026-10");
        payroll.addEmployee(e1);
        payroll.addEmployee(e2);
        payroll.addEmployee(e3);
        payroll.addEmployee(e4);

        // ================================================================
        // BUOC 3: Hien thi toan bo bang luong (da hinh)
        // ================================================================
        payroll.displayPayroll();

        // ================================================================
        // BUOC 4: Kiem tra tong theo phong ban
        // ================================================================
        System.out.println("\n--- Tong luong phong 'Ho tro' ---");
        double supportTotal = payroll.calculatePayrollByDepartment("Ho tro");
        System.out.printf("Tong phong Ho tro: %,.0f VND (mong doi: 33.000.000)%n", supportTotal);

        // ================================================================
        // BUOC 5: Tim nguoi thu nhap cao nhat
        // ================================================================
        System.out.println("\n--- Nguoi thu nhap cao nhat ---");
        Employee highest = payroll.findHighestPaidEmployee();
        if (highest != null) {
            System.out.printf("Ma: %s | Ho ten: %s | Thu nhap: %,.0f VND%n",
                highest.getEmployeeId(), highest.getFullName(),
                highest.calculateGrossPay());
        }

        // ================================================================
        // KIEM THU BIEN VA LOI — 10 tinh huong
        // ================================================================
        System.out.println("\n========== KIEM THU BIEN VA LOI ==========");

        // Bien 1: Ma nhan su rong
        System.out.println("\n[Bien 1] Ma nhan su rong:");
        try { new Employee("", "Ten Nao Do"); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 2: Luong thang am (SalariedEmployee)
        System.out.println("\n[Bien 2] Luong thang am:");
        try { new SalariedEmployee("X001", "Test", "Phong A", -1, 0); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 3: So gio lam vuot 250
        System.out.println("\n[Bien 3] So gio lam > 250:");
        try { new HourlyEmployee("X002", "Test", "Phong A", 100_000, 251); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 4: So gio am
        System.out.println("\n[Bien 4] So gio am:");
        try { new HourlyEmployee("X003", "Test", "Phong A", 100_000, -1); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 5: Ty le hoa hong > 0.3
        System.out.println("\n[Bien 5] Ty le hoa hong > 0.3:");
        try { new SalesEmployee("X004", "Test", "Kinh doanh", 5_000_000, 100_000_000, 0.31); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 6: addBonus so am
        System.out.println("\n[Bien 6] addBonus so am:");
        try { e1.addBonus(-500_000); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 7: addBonus ty le > 0.5
        System.out.println("\n[Bien 7] addBonus ty le > 0.5:");
        try { e1.addBonus(0.6, 10_000_000, "Test"); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 8: addBonus ly do rong
        System.out.println("\n[Bien 8] addBonus ly do rong:");
        try { e1.addBonus(500_000, ""); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        // Bien 9: Them nhan su trung ma
        System.out.println("\n[Bien 9] Them nhan su trung ma E001:");
        SalariedEmployee dup = new SalariedEmployee("E001", "Nguoi Trung Ma", 10_000_000);
        boolean added = payroll.addEmployee(dup);
        System.out.println("Ket qua (false = bi tu choi): " + added);

        // Bien 10: updateSalesRevenue am
        System.out.println("\n[Bien 10] Cap nhat doanh so am:");
        try { e4.updateSalesRevenue(-1_000); }
        catch (IllegalArgumentException e) { System.out.println("Bat duoc: " + e.getMessage()); }

        System.out.println("\n========== KET THUC KIEM THU ==========");
    }
}
