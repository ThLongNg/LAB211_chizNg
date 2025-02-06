/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author MyPC
 */
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class MountainList extends ArrayList<Mountain> {

    public MountainList() {
        super();
    }

    // Nạp danh sách núi từ file
public void loadFromFile(String filename) {
    try {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;  // Dùng để bỏ qua header
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Bỏ qua dòng header nếu phát hiện dòng bắt đầu với "Code"
                if (isFirstLine) {
                    if (line.toLowerCase().startsWith("code")) {
                        isFirstLine = false;
                        continue;
                    }
                    isFirstLine = false;
                }

                // Sử dụng split với limit âm để bao gồm các trường rỗng
                String[] tokens = line.split(",", -1);
                if (tokens.length < 4) {
                    System.out.println("Invalid line format (missing fields): " + line);
                    continue;
                }
                try {
                    // Lấy mã núi từ token đầu tiên
                    int code = Integer.parseInt(tokens[0].trim());
                    // Lấy tên núi và tỉnh
                    String mountain = tokens[1].trim();
                    String province = tokens[2].trim();
                    // Các token còn lại tạo thành mô tả (có thể rỗng)
                    StringBuilder descriptionBuilder = new StringBuilder();
                    for (int i = 3; i < tokens.length; i++) {
                        if (i > 3) {
                            descriptionBuilder.append(", ");
                        }
                        descriptionBuilder.append(tokens[i].trim());
                    }
                    String description = descriptionBuilder.toString();
                    // Thêm đối tượng Mountain vào danh sách
                    this.add(new Mountain(code, mountain, province, description));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format in line: " + line);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error while loading mountains: " + e.getMessage());
    }
}}