package main;
import enums.DeviceStatus;
import exceptions.DeviceNotReadyException;
import exceptions.DuplicateIdException;
import exceptions.NoSuitableItemException;
import models.device.AirConditionerRemote;
import models.device.ProjectorRemote;
import models.device.TeachingDevice;
import models.device.WirelessMicrophone;
import repository.InMemoryRepository;
import repository.IRepository;
import service.AllocationService;
import strategy.FirstAvailableStrategy;
import strategy.HighestPriorityStrategy;
import strategy.ISelectionStrategy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        IRepository<ProjectorRemote> projRepo = new InMemoryRepository<>();
        IRepository<AirConditionerRemote> acRepo = new InMemoryRepository<>();
        IRepository<WirelessMicrophone> micRepo = new InMemoryRepository<>();

        try {
            projRepo.add(new ProjectorRemote("PRJ01", "May chieu 1", "Tu 1", 15)); 
            projRepo.add(new ProjectorRemote("PRJ02", "May chieu 2", "Tu 1", 80));
            projRepo.add(new ProjectorRemote("PRJ03", "May chieu 3", "Tu 1", 95));

            Set<String> roomsAC1 = new HashSet<>(List.of("A201", "A202"));
            Set<String> roomsAC2 = new HashSet<>(List.of("A203"));
            acRepo.add(new AirConditionerRemote("AC01", "Dieu hoa 1", "Tu 2", roomsAC1));
            acRepo.add(new AirConditionerRemote("AC02", "Dieu hoa 2", "Tu 2", roomsAC2));

            micRepo.add(new WirelessMicrophone("MIC01", "Micro 1", "Tu 3", 80, 90));
            micRepo.add(new WirelessMicrophone("MIC02", "Micro 2", "Tu 3", 25, 80)); 
            micRepo.add(new WirelessMicrophone("MIC03", "Micro 3", "Tu 3", 60, 30)); 
            
            System.out.println("1. Them thiet bi thanh cong.");
        } catch (DuplicateIdException e) {
            System.out.println("Loi them thiet bi: " + e.getMessage());
        }

        try {
            projRepo.add(new ProjectorRemote("PRJ01", "May chieu Trung", "Tu 1", 100));
        } catch (DuplicateIdException e) {
            System.out.println("2. Bat loi trung ma: " + e.getMessage());
        }

        ProjectorRemote prj = projRepo.findById("PRJ02");
        System.out.println("3. Tim thiet bi PRJ02: " + (prj != null ? prj.getName() : "Khong thay"));

        List<ProjectorRemote> readyProj = projRepo.findAll(ProjectorRemote::isReadyForUse);
        System.out.println("4. So may chieu san sang: " + readyProj.size()); 

        ISelectionStrategy<ProjectorRemote> hpStrategyProj = new HighestPriorityStrategy<>(ProjectorRemote::getDefaultPriorityScore);
        AllocationService<ProjectorRemote> projService = new AllocationService<>(projRepo, hpStrategyProj);
        try {
            ProjectorRemote bestProj = projService.allocate(device -> true);
            System.out.println("5. May chieu tot nhat: " + bestProj.getId() + " (Pin: " + bestProj.getBatteryLevel() + ")");
            System.out.println("10. Trang thai sau cap phat: " + bestProj.getStatus());
        } catch (Exception e) {
             System.out.println("Loi cap phat: " + e.getMessage());
        }

        ISelectionStrategy<AirConditionerRemote> firstACStrategy = new FirstAvailableStrategy<>();
        AllocationService<AirConditionerRemote> acService = new AllocationService<>(acRepo, firstACStrategy);
        try {
            AirConditionerRemote ac = acService.allocate(device -> device.isCompatibleWith("A203"));
            System.out.println("6. Dieu hoa cho A203: " + ac.getId());
        } catch (Exception e) {
            System.out.println("Loi cap phat: " + e.getMessage());
        }

        ISelectionStrategy<WirelessMicrophone> firstMicStrategy = new FirstAvailableStrategy<>();
        AllocationService<WirelessMicrophone> micService = new AllocationService<>(micRepo, firstMicStrategy);
        try {
            WirelessMicrophone mic = micService.allocate(device -> true);
            System.out.println("7. Micro dau tien san sang: " + mic.getId());
        } catch (Exception e) {
            System.out.println("Loi cap phat: " + e.getMessage());
        }
        
        System.out.println("8. Doi chien luoc cho Micro sang diem cao nhat...");
        ISelectionStrategy<WirelessMicrophone> hpMicStrategy = new HighestPriorityStrategy<>(WirelessMicrophone::getDefaultPriorityScore);
        micService.changeStrategy(hpMicStrategy);
        micRepo.findById("MIC01").returnDevice();
        try {
            WirelessMicrophone bestMic = micService.allocate(device -> true);
            System.out.println("   Micro tot nhat sau khi doi: " + bestMic.getId());
        } catch (Exception e) {
            System.out.println("Loi cap phat: " + e.getMessage());
        }

        try {
            System.out.println("9. Thu cap phat DH cho phong A999 (khong ton tai)...");
            acService.allocate(device -> device.isCompatibleWith("A999"));
        } catch (NoSuitableItemException e) {
            System.out.println("   Loi bat duoc: " + e.getMessage());
        } catch (DeviceNotReadyException e) {
            System.out.println("   Loi thiet bi khong san sang: " + e.getMessage());
        }
        
        System.out.println("11. Chung minh Repository tong quat dung duoc cho nhieu loai thiet bi ma khong lap lai ma.");
    }
}