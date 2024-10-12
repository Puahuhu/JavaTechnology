import java.util.List;

public class Program {
    public static void main(String[] args) {
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();

        Manufacture manufacture1 = new Manufacture("Tech Manufacturer", "Hanoi", 200);
        Manufacture manufacture2 = new Manufacture("Mobile Manufacturer", "HCMC", 150);
        Manufacture manufacture3 = new Manufacture("Computer Manufacturer", "Da Nang", 300);
        Manufacture manufacture4 = new Manufacture("Gadget Manufacturer", "Can Tho", 100);
        Manufacture manufacture5 = new Manufacture("Appliance Manufacturer", "Hai Phong", 400);
        Manufacture manufacture6 = new Manufacture("TV Manufacturer", "Hanoi", 250);
        Manufacture manufacture7 = new Manufacture("Audio Manufacturer", "HCMC", 350);
        Manufacture manufacture8 = new Manufacture("Camera Manufacturer", "Da Nang", 500);
        Manufacture manufacture9 = new Manufacture("Tablet Manufacturer", "Can Tho", 200);
        Manufacture manufacture10 = new Manufacture("Smartwatch Manufacturer", "Hai Phong", 150);

        manufactureDAO.add(manufacture1);
        manufactureDAO.add(manufacture2);
        manufactureDAO.add(manufacture3);
        manufactureDAO.add(manufacture4);
        manufactureDAO.add(manufacture5);
        manufactureDAO.add(manufacture6);
        manufactureDAO.add(manufacture7);
        manufactureDAO.add(manufacture8);
        manufactureDAO.add(manufacture9);
        manufactureDAO.add(manufacture10);

        Phone phone1 = new Phone("Phone X", 15000000, "Black", "Vietnam", 10, manufacture1);
        Phone phone2 = new Phone("Phone Y", 12000000, "Pink", "Vietnam", 5, manufacture2);
        Phone phone3 = new Phone("Phone Z", 18000000, "White", "Vietnam", 8, manufacture3);
        Phone phone4 = new Phone("Phone A", 11000000, "Blue", "Vietnam", 15, manufacture4);
        Phone phone5 = new Phone("Phone B", 20000000, "Red", "Vietnam", 12, manufacture5);
        Phone phone6 = new Phone("Phone C", 13000000, "Green", "Vietnam", 9, manufacture6);
        Phone phone7 = new Phone("Phone D", 14000000, "Black", "Vietnam", 7, manufacture7);
        Phone phone8 = new Phone("Phone E", 17000000, "Silver", "Vietnam", 6, manufacture8);
        Phone phone9 = new Phone("Phone F", 16000000, "Gold", "Vietnam", 4, manufacture9);
        Phone phone10 = new Phone("Phone G", 19000000, "Gray", "Vietnam", 11, manufacture10);

        phoneDAO.add(phone1);
        phoneDAO.add(phone2);
        phoneDAO.add(phone3);
        phoneDAO.add(phone4);
        phoneDAO.add(phone5);
        phoneDAO.add(phone6);
        phoneDAO.add(phone7);
        phoneDAO.add(phone8);
        phoneDAO.add(phone9);
        phoneDAO.add(phone10);

        List<Phone> phones = phoneDAO.getAll();

        System.out.println("Phone List:");
        for (Phone phone : phones) {
            System.out.println(phone);
        }

        List<Manufacture> manufactures = manufactureDAO.getAll();
        System.out.println();
        System.out.println("Manufacture List:");
        for (Manufacture manufacture : manufactures) {
            System.out.println(manufacture);
        }

        Phone highestPricePhone = phoneDAO.getPhoneWithHighestPrice();
        System.out.println("Phone with highest price: " + highestPricePhone.getName() + " - " + highestPricePhone.getPrice());

        List<Phone> phonesInVietnam = phoneDAO.getPhonesByCountry("Vietnam");
        System.out.println("Phones in Vietnam sorted by price:");
        for (Phone phone : phonesInVietnam) {
            System.out.println(phone.getName() + " - " + phone.getPrice());
        }

        boolean hasPhoneAbove50M = phoneDAO.hasPhoneAbovePrice(50000000);
        System.out.println("Is there any phone priced above 50 million? " + (hasPhoneAbove50M ? "Yes" : "No"));

        Phone pinkPhone = phoneDAO.getPhoneWithCriteria();
        if (pinkPhone != null) {
            System.out.println("First pink phone priced over 15 million: " + pinkPhone.getName());
        } else {
            System.out.println("No pink phone priced over 15 million found.");
        }

        boolean allManufacturesAbove100 = manufactureDAO.hasManufacturersWithMoreThan100Employees();
        System.out.println("All manufacturers have more than 100 employees? " + (allManufacturesAbove100 ? "Yes" : "No"));

        int totalEmployees = manufactureDAO.getTotalEmployees();
        System.out.println("Total number of employees: " + totalEmployees);

        try {
            Manufacture lastUsaManufacture = manufactureDAO.getLastManufactureMeetingCriteria();
            System.out.println("Last USA-based manufacturer: " + lastUsaManufacture.getName());
        } catch (InvalidOperationException e) {
            System.out.println("No manufacturer based in the US meets the criteria.");
        }
    }
}