
// 20237362
// Dương Quang Minh
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Khoi tao thiet bi & phong lab
        Computer pc1 = new Computer("PC01", "PC i5", 2022, 15000000, DeviceStatus.Active, 16, "i5", false);
        Computer pc2 = new Computer("PC02", "PC i9 GPU", 2018, 35000000, DeviceStatus.Active, 32, "i9", true);

        Printer pr1 = new Printer("PR01", "Canon 2900", 2023, 4000000, DeviceStatus.Active, PrinterType.LASER, 50000,
                false);
        Printer pr2 = new Printer("PR02", "HP Color", 2021, 12000000, DeviceStatus.UnderMaintenance, PrinterType.LASER,
                120000, true);

        Projector pj1 = new Projector("PJ01", "Sony", 2020, 20000000, DeviceStatus.Active, 3500, 3500);

        LabRoom r1 = new LabRoom("R101", "Lab 1", 30);
        LabRoom r2 = new LabRoom("R201", "Lab 2", 30);

        // 1. Them vao phong
        r1.addDevice(pc1);
        r1.addDevice(pr1);
        r1.addDevice(pj1);

        r2.addDevice(pc2);
        r2.addDevice(pr2);

        // 2. Thu them trung ma
        try {
            r1.addDevice(new Computer("PC01", "PC trung", 2023, 10000000, DeviceStatus.Active, 8, "i3", false));
        } catch (Exception e) {
            System.out.println("Loi add trung ma: " + e.getMessage());
        }

        // 3. In danh sach
        System.out.println("\n Devices Room 1 ");
        for (Device d : r1.getDevices())
            System.out.println(d);

        System.out.println("\n Devices Room 2 ");
        for (Device d : r2.getDevices())
            System.out.println(d);

        // 4. Tong chi phi bao tri
        System.out.println("\nChi phi R1: " + r1.calculateAnnualMaintenanceCost());
        System.out.println("Chi phi R2: " + r2.calculateAnnualMaintenanceCost());

        // 5. Thiet bi cant bao tri
        System.out.println("\n Cant bao tri R1 ");
        for (Device d : r1.getDevicesRequiringMaintenance())
            System.out.println(d);

        System.out.println("\n Cant bao tri R2 ");
        for (Device d : r2.getDevicesRequiringMaintenance())
            System.out.println(d);

        // 6. Connect network
        pc1.connect("192.168.1.10");
        pc2.connect("192.168.1.11");
        pr1.connect("192.168.1.20");
        pr2.connect("192.168.1.21");

        // 7. Duyet kieu INetworkable
        System.out.println("\n Network Devices ");
        List<INetworkable> netList = new ArrayList<>();
        netList.add(pc1);
        netList.add(pc2);
        netList.add(pr1);
        netList.add(pr2);

        for (INetworkable net : netList) {
            System.out.println("IP: " + net.getIpAddress() + " | Connected: " + net.isConnected());
        }
    }
}