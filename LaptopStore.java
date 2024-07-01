import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LaptopStore {
    private Set<Laptop> laptops = new HashSet<>();

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Dell", 16, 512, "Windows", "Black"));
        store.addLaptop(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        store.addLaptop(new Laptop("HP", 16, 1024, "Windows", "Gray"));
        store.addLaptop(new Laptop("Lenovo", 8, 512, "Linux", "Black"));

        store.filterLaptops();
    }

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        System.out.println("Введите минимальный объем ОЗУ (в ГБ) или -1, чтобы пропустить: ");
        int ram = scanner.nextInt();
        if (ram > 0) {
            criteria.put("ram", ram);
        }

        System.out.println("Введите минимальный объем ЖД (в ГБ) или -1, чтобы пропустить: ");
        int storage = scanner.nextInt();
        if (storage > 0) {
            criteria.put("storage", storage);
        }

        scanner.nextLine();  // Consume newline

        System.out.println("Введите операционную систему или нажмите Enter, чтобы пропустить: ");
        String os = scanner.nextLine();
        if (!os.isEmpty()) {
            criteria.put("os", os);
        }

        System.out.println("Введите цвет или нажмите Enter, чтобы пропустить: ");
        String color = scanner.nextLine();
        if (!color.isEmpty()) {
            criteria.put("color", color);
        }

        Set<Laptop> filteredLaptops = filterByCriteria(criteria);
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    private Set<Laptop> filterByCriteria(Map<String, Object> criteria) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            result.removeIf(laptop -> {
                switch (key) {
                    case "ram":
                        return laptop.getRam() < (int) value;
                    case "storage":
                        return laptop.getStorage() < (int) value;
                    case "os":
                        return !laptop.getOs().equalsIgnoreCase((String) value);
                    case "color":
                        return !laptop.getColor().equalsIgnoreCase((String) value);
                    default:
                        return true;
                }
            });
        }

        return result;
    }
}
