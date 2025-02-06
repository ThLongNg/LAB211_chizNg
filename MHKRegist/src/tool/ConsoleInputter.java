/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

/**
 *
 * @author MyPC
 */
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ConsoleInputter{
    public static Scanner scanner = new Scanner(System.in);
    public static boolean getBoolean(String prompt){
        System.out.print(prompt + "Y/N, T/F, 1/0: ");
        String data = scanner.nextLine().trim().toUpperCase();
        char c=data.charAt(0);
        return c== 'Y' || c=='T' || c=='1';
    }
    public static int getInt(String prompt, int min, int max){
        int result = 0;
        do{
            System.out.print(prompt + "[" + min + "," + max + "]: ");
            result = scanner.nextInt();
            scanner.nextLine();
        }while(result < min || result > max);
        return result;
    }
    public static double getDouble(String prompt, double min, double max){
        double result = 0;
        do{
            System.out.print(prompt + "[" + min + "," + max + "]: ");
            result = scanner.nextDouble();
            scanner.nextLine();
        }while(result < min || result > max);
        return result;
    }
    //Nhập một chuỗi bất kỳ
    //Nhập một chuỗi theo pattern
    public static String getString(String prompt, String pattern, String errorMessage){
        String data;
        boolean valid;
        do{
            System.out.print(prompt + ": ");
            data = scanner.nextLine().trim();
            valid = data.matches(pattern);
            if(!valid)
                System.out.println(errorMessage);
        } while(!valid);
        return data;
    }
    //Nhập dât theo mẫu dd-MM-yyyy hoặc MM-dd-yyyy
    public static Date getDate(String prompt, String dateFormat){
        String dataString;
        Date d;
        //Tạo DateFormat formatter với date format trong tham sô
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        do{
            System.out.print(prompt + ": ");
            dataString = scanner.nextLine().trim();
            try{
                d = formatter.parse(dataString);
                break;
            }catch(ParseException e){
                System.out.println("Date format should be"+ dateFormat+".");
                d=null;
            }
        }while(d==null);
        return d;
    }
    //Đổi Date sang chuỗi dd-MM-yyyy/ MM-dd-yyyy
    public static String DateString(Date date, String dateFormat){
        if(date==null)return null;
        //Tạo DateFormat obj làm việc với định dạng trong tham số thứ 2
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        //trả kết quả sau khi format
        return formatter.format(date);
    }
    //Lấy 1 thành phần trong Date. Lớp Calender giúp truy xuấy các thành phần
    public static int getPart(Date d, int calendarPart){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }
    //Xây dựng Menu trả về số người dùng chọn
    public static int intMenu(Object...options){
        int n=options.length;
        for(int i=0;i<n;i++)
            System.out.println((i+1)+". "+options[i]);
        return getInt("Choose", 1, n);
    }
    //Xây dựng Menu trả về obj user chọn
public static Object objMenu(Object[] options, String prompt) {
    System.out.println(prompt);
    int choice = intMenu(options);
    return options[choice - 1]; // Trả về đúng đối tượng được chọn
}
public static String getString(String prompt) {
    System.out.print(prompt + ": ");
    return scanner.nextLine().trim();
}
}// class ConsoleInputter