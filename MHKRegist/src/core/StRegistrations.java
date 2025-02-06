

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyPC
 */
package core;
import java.util.ArrayList;
import java.util.List;
import tool.ConsoleInputter;
import java.io.PrintWriter; // Giúp nhập file text
import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.Arrays;

public class StRegistrations extends ArrayList<StudentRegist> {
    private static final long serialVersionUID = 1L;
   private final List<String> discountPrefixPhone = Arrays.asList("030", "809");
    private final MountainList mL;

    // Tạo danh sách đăng ký sau khi đã biết MountainList
    public StRegistrations(MountainList mL) {
        this.mL = mL;
    }

    // Thêm 1 đăng ký của sinh viên thông qua bàn phím
    public void addRegist() {
        String name, contact, email, stId, campus;
        int mountainCode, fee;

// Nhập stID: SE, HE, DE, QE, CE và 6 ký tự số
do {
    stId = ConsoleInputter.getString(
        "Enter stID", 
        "^[a-zA-Z]{2}\\d{6}$",  // Chỉ kiểm tra đúng 2 chữ cái đầu + 6 số
        "Invalid stID format. Please enter 2 letters (SE, HE, DE, QE, CE) followed by 6 digits."
    ).toUpperCase(); // Tự động chuyển 2 ký tự đầu thành chữ in hoa
} while (!stId.matches("^(SE|HE|DE|QE|CE)\\d{6}$"));

        // Nhập tên
        name = ConsoleInputter.getString("Student name", "^[A-Za-z]+( [A-Za-z]+)*$", "Invalid name format. Only letters and spaces allowed.");


        // Nhập số điện thoại
        contact = ConsoleInputter.getString("Student contact", "^(0[2-9][0-9]{8})$", "Phone must be a valid 10-digit number starting with 0.");



        // Nhập email
        email = ConsoleInputter.getString("Student email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email format.");

        // Nhập campus
        campus = ConsoleInputter.getString("Student campus", "HCM|HN|CT|QN|DN", "Campus: HCM|HN|CT|QN|DN");

        // Nhập mountainCode thông qua menu các mountain trong MountainList
        Object objChoice = ConsoleInputter.objMenu(mL.toArray(new Mountain[0]), "Mountain code");
        mountainCode = ((Mountain) objChoice).getCode();

        // Tính fee
        fee = 6000000;
        if (discountPrefixPhone.contains(contact.substring(0, 3))) {
            fee = (int) (fee * 0.65);
        }

        // Tạo một đăng ký từ dữ liệu đã nhập
        StudentRegist st = new StudentRegist(name, stId, contact, email, campus, mountainCode, fee);

        // Thêm đăng ký vào danh sách
        this.add(st);
    }

    // Cập nhật thông tin đăng ký
    public void update() {
        // Nhập stID
        String stId = ConsoleInputter.getString("Enter stID: ");

        // Tìm phiếu đăng ký của sinh viên này
        int pos = this.indexOf(new StudentRegist(stId, 0));
        StudentRegist reg = (pos >= 0) ? this.get(pos) : null;

        if (reg == null) {
            System.out.println("Student registration not found.");
        } else {
            // Nhập thông tin mới
            String name = ConsoleInputter.getString("Student name", "[a-zA-Z][.]*", "Invalid name format");
            String contact = ConsoleInputter.getString("Student contact", "\\d{10}", "Phone: 10 digits");
            String email = ConsoleInputter.getString("Student email", "\\w+@\\w+\\.com", "Invalid email format");

            // Cập nhật thông tin
            reg.setStName(name);
            reg.setStContact(contact);
            reg.setStEmail(email);

            System.out.println("Student registration updated successfully.");
        }
    }

    // Hiển thị danh sách đã đăng ký theo dạng bảng
    public void printList() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-11s | %-15s | %-13s | %-20s | %-10s | %-10s%n",
                    "stID", "Name", "Contact", "Email", "MountainCode", "Fee");
            System.out.println("---------------------------------------------------------------");

            for (StudentRegist reg : this) {
                System.out.printf("%-11s | %-15s | %-13s | %-20s | %-10d | %-10d%n",
                        reg.getStId(), reg.getStName(), reg.getStContact(), reg.getStEmail(), reg.getMountainCode(), reg.getFee());
            }

            System.out.println("---------------------------------------------------------------");
        }
    }

    // Xóa một thông tin đã đăng ký
    public void delete() {
        String stID = ConsoleInputter.getString("Enter stID: ", "SE[\\d]{6}|HE[\\d]{6}|DE[\\d]{6}|QE[\\d]{6}|CE[\\d]{6}", "Invalid stID format");

        int pos = this.indexOf(new StudentRegist(stID, 0));

        if (pos >= 0) {
            boolean response = ConsoleInputter.getBoolean("Do you want to delete this registration?");
            if (response) {
                this.remove(pos);
                System.out.println("Deleted successfully!");
            }
        } else {
            System.out.println("Student registration not found.");
        }
    }

    // Xuất sinh viên đăng ký theo tên
    public void printByName() {
        String name = ConsoleInputter.getString("Enter name: ").toUpperCase();

        boolean found = false;
        for (StudentRegist reg : this) {
            if (reg.getStName().toUpperCase().contains(name)) {
                found = true;
                System.out.printf("%-11s | %-15s | %-13s | %-20s | %-10d | %-10d%n",
                        reg.getStId(), reg.getStName(), reg.getStContact(), reg.getStEmail(), reg.getMountainCode(), reg.getFee());
            }
        }

        if (!found) {
            System.out.println("No students found with this name.");
        }
    }

    // Xuất sinh viên đăng ký theo campus
    public void printByCampus() {
        String campus = ConsoleInputter.getString("Enter campus: ").toUpperCase();

        boolean found = false;
        for (StudentRegist reg : this) {
            if (reg.getCampus().toUpperCase().contains(campus)) {
                found = true;
                System.out.printf("%-11s | %-15s | %-13s | %-20s | %-10d | %-10d%n",
                        reg.getStId(), reg.getStName(), reg.getStContact(), reg.getStEmail(), reg.getMountainCode(), reg.getFee());
            }
        }

        if (!found) {
            System.out.println("No students found in this campus.");
        }
    }

    // Xuất số đăng ký theo mountain
    public void printByMountain() {
        for (Mountain m : mL) {
            System.out.println(m.getCode() + ", " + m.getMountain() + ", Count:");
            int count = 0;

            for (StudentRegist reg : this) {
                if (reg.getMountainCode() == m.getCode()) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    // Thêm logic in danh sách đăng ký theo vị trí
    public void printByLocation() {
        String location = ConsoleInputter.getString("Enter location: ");
        boolean found = false;
        for (StudentRegist reg : this) {
            if (reg.getCampus().toUpperCase().contains(location)) {
                found = true;
                System.out.printf("%-11s | %-15s | %-13s | %-20s | %-10d | %-10d%n",
                        reg.getStId(), reg.getStName(), reg.getStContact(), reg.getStEmail(), reg.getMountainCode(), reg.getFee());
            }
        }

        if (!found) {
            System.out.println("No students found in this location.");
        }
    }

    // Lưu các đăng ký vào file
    public void saveFile(String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (StudentRegist reg : this) {
                pw.println(reg.toString());
            }
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e);
        }
    }

    // Nạp danh sách từ file
    public void loadFromFile(String filename) {
        // Check if file exists
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7) { // Phải có 7 phần tử (bao gồm `fee`)
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String name = parts[0].trim();
                String stId = parts[1].trim();
                String contact = parts[2].trim();
                String email = parts[3].trim();
                String campus = parts[4].trim();
                int mountainCode = Integer.parseInt(parts[5].trim());
                int fee = Integer.parseInt(parts[6].trim());

                StudentRegist reg = new StudentRegist(name, stId, contact, email, campus, mountainCode, fee);
                this.add(reg);
            }
        } catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
