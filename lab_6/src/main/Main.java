package main;

import models.user.*;
import models.device.*;
import models.borrow.*;
import enums.*;
import exceptions.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("TEST CASES:");

        Lecturer lecturer = new Lecturer("GV01", "Nguyen Van A");
        TeachingAssistant ta = new TeachingAssistant("TA01", "Tran Thi B");

        // Khởi tạo thiết bị
        ProjectorRemote projRemote = new ProjectorRemote("PRJ01", "ĐK Máy Chiếu", 2023, 500000, DeviceStatus.Available,
                5);
        AirConditionerRemote acRemote = new AirConditionerRemote("AC01", "ĐK Điều Hòa", 2022, 300000,
                DeviceStatus.Available, 5);
        Microphone mic = new Microphone("MIC01", "Micro Không Dây", 2023, 1500000, DeviceStatus.Available, 1);

        // LUỒNG THÀNH CÔNG
        System.out.println("\n[1] LUỒNG THÀNH CÔNG");
        BorrowingSlip slip1 = new BorrowingSlip("SLIP01", lecturer, "D9-101", LocalDate.of(2026, 10, 10));

        try {
            slip1.addDevice(projRemote, 5);
            slip1.addDevice(mic, 1);
            System.out.println("Thêm thiết bị PRJ01 và MIC01 thành công.");

            // Trả PRJ01 đúng hạn
            long fee1 = slip1.returnDevice("PRJ01", LocalDate.of(2026, 10, 15));
            System.out.println("Trả PRJ01 đúng hạn. Phí phạt: " + fee1);

            // Trả MIC muộn 2 ngày (Hạn là 11/10, trả 13/10)
            long fee2 = slip1.returnDevice("MIC01", LocalDate.of(2026, 10, 13));
            System.out.println("Trả MIC01 muộn. Phí phạt: " + fee2);

        } catch (BorrowingException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        // LUỒNG PHÁT SINH NGOẠI LỆ
        System.out.println("\n[2] KIỂM THỬ NGOẠI LỆ");

        // Setup lại thiết bị cho các test
        acRemote.setStatus(DeviceStatus.UnderMaintenance);
        mic.setStatus(DeviceStatus.Available);

        BorrowingSlip slip2 = new BorrowingSlip("SLIP02", ta, "D9-102", LocalDate.of(2026, 10, 20));

        // Tình huống 1: Mượn AC đang bảo trì
        try {
            slip2.addDevice(acRemote, 1);
        } catch (DeviceUnavailableException e) {
            System.out.println("[Test 1 - PASS]: " + e.getMessage());
        }

        // Tình huống 2 & 3: Trợ giảng mượn thiết bị thứ 3 + Thêm trùng Micro
        try {
            slip2.addDevice(projRemote, 2);
            slip2.addDevice(mic, 1); // Trợ giảng được mượn 2 thiết bị

            // Cố thêm trùng Micro
            try {
                slip2.addDevice(mic, 1);
            } catch (DuplicateDeviceException e) {
                System.out.println("[Test 3 - PASS]: " + e.getMessage());
            }

            // Cố thêm thiết bị thứ 3
            AirConditionerRemote ac2 = new AirConditionerRemote("AC02", "AC Mới", 2024, 300, DeviceStatus.Available, 5);
            slip2.addDevice(ac2, 1);
        } catch (BorrowLimitExceededException e) {
            System.out.println("[Test 2 - PASS]: " + e.getMessage());
        }

        // Tình huống 4: Mượn Micro 3 ngày trong khi max là 1
        BorrowingSlip slip3 = new BorrowingSlip("SLIP03", lecturer, "D9-103", LocalDate.now());
        Microphone mic2 = new Microphone("MIC02", "Micro 2", 2024, 1500, DeviceStatus.Available, 1);
        try {
            slip3.addDevice(mic2, 3);
        } catch (InvalidBorrowPeriodException e) {
            System.out.println("[Test 4 - PASS]: " + e.getMessage());
        }

        // Tình huống 5: Trả thiết bị không thuộc phiếu
        try {
            slip2.returnDevice("AC99", LocalDate.of(2026, 10, 21));
        } catch (DeviceNotInSlipException e) {
            System.out.println("[Test 5 - PASS]: " + e.getMessage());
        }

        // Tình huống 6: Trả thiết bị 2 lần
        try {
            slip2.returnDevice("PRJ01", LocalDate.of(2026, 10, 21));
            slip2.returnDevice("PRJ01", LocalDate.of(2026, 10, 22)); // Lần 2
        } catch (DeviceAlreadyReturnedException e) {
            System.out.println("[Test 6 - PASS]: " + e.getMessage());
        }

        // Tình huống 7: Trả trước ngày mượn
        try {
            slip2.returnDevice("MIC01", LocalDate.of(2026, 10, 10)); // Ngày mượn slip2 là 20/10
        } catch (IllegalArgumentException e) {
            System.out.println("[Test 7 - PASS]: " + e.getMessage());
        }
    }
}