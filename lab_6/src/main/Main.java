package main;

import models.user.*;
import models.device.*;
import models.borrow.*;
import enums.*;
import exceptions.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("        KIEM THU HE THONG QUAN LY MUON TRA THIET BI");
        System.out.println("====================================================");

        // Khoi tao nguoi dung
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        TeachingAssistant ta = new TeachingAssistant("TA01", "Tran Thi B");

        // ====================================================
        // PHAN 1: LUONG THANH CONG (HAPPY PATH)
        // ====================================================
        System.out.println();
        System.out.println("--- [1] KIEM THU LUONG THANH CONG ---");

        ProjectorRemote prj1 = new ProjectorRemote("PRJ01", "DK May Chieu", 2023, 500000, DeviceStatus.Available, 5);
        Microphone mic1 = new Microphone("MIC01", "Micro Khong Day", 2023, 1500000, DeviceStatus.Available, 3);

        BorrowingSlip slipSuccess = new BorrowingSlip("SLIP01", lecturer, "D9-101", LocalDate.of(2026, 10, 10));

        try {
            slipSuccess.addDevice(prj1, 5);
            slipSuccess.addDevice(mic1, 1);
            System.out.println("[THANH CONG] Them PRJ01 (5 ngay) va MIC01 (1 ngay) vao phieu SLIP01");

            // Tra PRJ01 dung han (Han: 15/10, Tra: 15/10)
            long fee1 = slipSuccess.returnDevice("PRJ01", LocalDate.of(2026, 10, 15));
            System.out.println("[THANH CONG] Tra PRJ01 dung han. Phi phat: " + fee1 + " VND");

            // Tra MIC01 tre han 2 ngay (Han: 11/10, Tra: 13/10)
            long fee2 = slipSuccess.returnDevice("MIC01", LocalDate.of(2026, 10, 13));
            System.out.println("[THANH CONG] Tra MIC01 tre 2 ngay. Phi phat: " + fee2 + " VND (50.000 x 2)");

        } catch (BorrowingException e) {
            System.out.println("[LOI KHONG MONG MUON]: " + e.getMessage());
        }

        // ====================================================
        // PHAN 2: KIEM THU TOAN BO CAC TRUONG HOP NGOAI LE
        // ====================================================
        System.out.println();
        System.out.println("====================================================");
        System.out.println("        KIEM THU TOAN BO CAC NGOAI LE (EXCEPTIONS)");
        System.out.println("====================================================");

        testDeviceUnavailable();
        testDuplicateDevice();
        testBorrowLimitExceeded();
        testInvalidBorrowPeriodExceeded();
        testInvalidBorrowPeriodNonPositive();
        testDeviceNotInSlip();
        testDeviceAlreadyReturned();
        testReturnBeforeBorrowDate();
    }

    // [Loi 1]: DeviceUnavailableException - Thiet bi dang bao tri hoac dang duoc muon
    private static void testDeviceUnavailable() {
        System.out.println();
        System.out.println("[Test 1] DeviceUnavailableException (Thiet bi dang UnderMaintenance):");
        TeachingAssistant ta = new TeachingAssistant("TA01", "Tran Thi B");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST1", ta, "D9-102", LocalDate.now());
        AirConditionerRemote ac = new AirConditionerRemote("AC01", "DK Dieu Hoa", 2022, 300000, DeviceStatus.UnderMaintenance, 5);

        try {
            slip.addDevice(ac, 2);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (DeviceUnavailableException e) {
            System.out.println("  -> PASS: Bat duoc DeviceUnavailableException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 2]: DuplicateDeviceException - Thiet bi da co trong phieu muon
    private static void testDuplicateDevice() {
        System.out.println();
        System.out.println("[Test 2] DuplicateDeviceException (Them trung thiet bi vao cung phieu):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST2", lecturer, "D9-102", LocalDate.now());
        Microphone mic = new Microphone("MIC02", "Micro Khong Day", 2023, 1500000, DeviceStatus.Available, 3);

        try {
            slip.addDevice(mic, 2);
            slip.addDevice(mic, 1);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (DuplicateDeviceException e) {
            System.out.println("  -> PASS: Bat duoc DuplicateDeviceException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 3]: BorrowLimitExceededException - Vuot qua gioi han so luong muon (Tro giang toi da 2)
    private static void testBorrowLimitExceeded() {
        System.out.println();
        System.out.println("[Test 3] BorrowLimitExceededException (Tro giang muon qua gioi han 2 thiet bi):");
        TeachingAssistant ta = new TeachingAssistant("TA01", "Tran Thi B");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST3", ta, "D9-102", LocalDate.now());

        ProjectorRemote prj = new ProjectorRemote("PRJ02", "DK May Chieu", 2023, 500000, DeviceStatus.Available, 5);
        Microphone mic = new Microphone("MIC03", "Micro Khong Day", 2023, 1500000, DeviceStatus.Available, 3);
        AirConditionerRemote ac = new AirConditionerRemote("AC02", "DK Dieu Hoa", 2023, 300000, DeviceStatus.Available, 5);

        try {
            slip.addDevice(prj, 1);
            slip.addDevice(mic, 1);
            slip.addDevice(ac, 1);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (BorrowLimitExceededException e) {
            System.out.println("  -> PASS: Bat duoc BorrowLimitExceededException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 4]: InvalidBorrowPeriodException - So ngay muon vuot qua so ngay toi da cua thiet bi
    private static void testInvalidBorrowPeriodExceeded() {
        System.out.println();
        System.out.println("[Test 4] InvalidBorrowPeriodException (Muon 5 ngay trong khi toi da chi cho phep 1 ngay):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST4", lecturer, "D9-103", LocalDate.now());
        Microphone mic = new Microphone("MIC04", "Micro 1 Ngay", 2024, 1500000, DeviceStatus.Available, 1);

        try {
            slip.addDevice(mic, 5);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (InvalidBorrowPeriodException e) {
            System.out.println("  -> PASS: Bat duoc InvalidBorrowPeriodException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 5]: InvalidBorrowPeriodException - So ngay muon <= 0
    private static void testInvalidBorrowPeriodNonPositive() {
        System.out.println();
        System.out.println("[Test 5] InvalidBorrowPeriodException (So ngay muon <= 0):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST5", lecturer, "D9-103", LocalDate.now());
        Microphone mic = new Microphone("MIC05", "Micro Test", 2024, 1500000, DeviceStatus.Available, 3);

        try {
            slip.addDevice(mic, 0);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (InvalidBorrowPeriodException e) {
            System.out.println("  -> PASS: Bat duoc InvalidBorrowPeriodException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 6]: DeviceNotInSlipException - Tra thiet bi khong thuoc phieu muon
    private static void testDeviceNotInSlip() {
        System.out.println();
        System.out.println("[Test 6] DeviceNotInSlipException (Tra ma thiet bi khong ton tai trong phieu):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST6", lecturer, "D9-101", LocalDate.now());
        ProjectorRemote prj = new ProjectorRemote("PRJ06", "DK May Chieu", 2023, 500000, DeviceStatus.Available, 5);
        slip.addDevice(prj, 2);

        try {
            slip.returnDevice("UNKNOWN_ID", LocalDate.now());
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (DeviceNotInSlipException e) {
            System.out.println("  -> PASS: Bat duoc DeviceNotInSlipException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 7]: DeviceAlreadyReturnedException - Tra thiet bi da tra truoc do
    private static void testDeviceAlreadyReturned() {
        System.out.println();
        System.out.println("[Test 7] DeviceAlreadyReturnedException (Tra 1 thiet bi 2 lan):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST7", lecturer, "D9-101", LocalDate.now());
        ProjectorRemote prj = new ProjectorRemote("PRJ07", "DK May Chieu", 2023, 500000, DeviceStatus.Available, 5);
        slip.addDevice(prj, 2);

        try {
            slip.returnDevice("PRJ07", LocalDate.now());
            slip.returnDevice("PRJ07", LocalDate.now());
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (DeviceAlreadyReturnedException e) {
            System.out.println("  -> PASS: Bat duoc DeviceAlreadyReturnedException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }

    // [Loi 8]: IllegalArgumentException - Ngay tra truoc ngay muon
    private static void testReturnBeforeBorrowDate() {
        System.out.println();
        System.out.println("[Test 8] IllegalArgumentException (Ngay tra truoc ngay muon):");
        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        LocalDate borrowDate = LocalDate.of(2026, 10, 20);
        BorrowingSlip slip = new BorrowingSlip("SLIP_TEST8", lecturer, "D9-101", borrowDate);
        ProjectorRemote prj = new ProjectorRemote("PRJ08", "DK May Chieu", 2023, 500000, DeviceStatus.Available, 5);
        slip.addDevice(prj, 2);

        try {
            LocalDate returnDate = LocalDate.of(2026, 10, 15);
            slip.returnDevice("PRJ08", returnDate);
            System.out.println("  -> FAIL: Khong nem ra ngoai le mong doi!");
        } catch (IllegalArgumentException e) {
            System.out.println("  -> PASS: Bat duoc IllegalArgumentException!");
            System.out.println("     Thong bao loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  -> FAIL: Sai loai ngoai le: " + e.getClass().getSimpleName());
        }
    }
}
