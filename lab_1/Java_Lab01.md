# Java - Lab 1 – Kiểu dữ liệu, Nhập/Xuất, Ép kiểu, Format specifier

**Môn học:** Lập Trình Hướng Đối Tượng  
**Ngôn ngữ:** Java  
*(Chuyển đổi từ tài liệu C# - Lab 1, FPT Aptech)*

---

## Mục tiêu

Sau buổi học này, sinh viên có thể hiểu được:

- 📌 Kiểu dữ liệu trong Java
- 📌 Nhập và xuất dữ liệu trong Java
- 📌 Format số và ngày giờ trong Java

---

## Phần I: Workshop – 15 phút

Sinh viên tự tìm hiểu, chạy thử và suy nghĩ về các đoạn code mẫu trong tài liệu.

---

## Phần II: Hướng dẫn từng bước – 45 phút

### Bài tập 1: Hiển thị thông báo

**Bước 1:** Mở IDE Java (IntelliJ IDEA / Eclipse / VS Code)  
**Bước 2:** Tạo một project Java mới, đặt tên là `First_prg`  
**Bước 3:** Tạo file `FirstPrg.java` bên trong project  
**Bước 4:** Nhập đoạn code sau vào file `FirstPrg.java`

```java
public class FirstPrg {
    public static void main(String[] args) {
        System.out.println("This is my first program using Java");
    }
}
```

**Bước 5:** Lưu file  
**Bước 6:** Biên dịch và chạy chương trình  
**Kết quả mong đợi:**
```
This is my first program using Java
```

> **So sánh C# → Java:**
> | C# | Java |
> |---|---|
> | `Console.WriteLine(...)` | `System.out.println(...)` |
> | `Console.ReadLine()` | `scanner.nextLine()` |
> | `class Example { static void Main(...) }` | `public class Example { public static void main(...) }` |

---

### Bài tập 2: Sử dụng kiểu dữ liệu

**Bước 1:** Tạo class mới `DataTypes.java` trong project  
**Bước 2:** Nhập đoạn code sau:

```java
public class DataTypes {
    public static void main(String[] args) {
        int intVal;
        double dblVal;
        String strVal;

        intVal = 10;
        dblVal = 3.142;
        strVal = "Fpt Aptech";

        System.out.println(intVal + " is an integer value");
        System.out.println(dblVal + " is a double value");
        System.out.println(strVal + " is a string");
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy chương trình  
**Kết quả mong đợi:**
```
10 is an integer value
3.142 is a double value
Fpt Aptech is a string
```

> **Bảng so sánh kiểu dữ liệu C# → Java:**
>
> | C# | Java | Mô tả |
> |---|---|---|
> | `int` | `int` | Số nguyên 32-bit |
> | `double` | `double` | Số thực 64-bit |
> | `string` | `String` | Chuỗi ký tự |
> | `float` | `float` | Số thực 32-bit |
> | `byte` | `byte` | Số nguyên 8-bit |
> | `char` | `char` | Ký tự đơn |
> | `bool` | `boolean` | Giá trị logic |
> | `long` | `long` | Số nguyên 64-bit |

---

### Bài tập 3a: Value Type (Kiểu giá trị)

**Bước 1:** Tạo file `ValueTypeDemo.java`  
**Bước 2:** Nhập đoạn code sau:

```java
public class ValueTypeDemo {
    public static void main(String[] args) {
        int valueVal = 5;
        test(valueVal);
        System.out.println("The value of the variable is " + valueVal);
    }

    static void test(int valueVal) {
        int temp = 5;
        valueVal = temp * 2; // Chỉ thay đổi bản sao cục bộ, không ảnh hưởng biến gốc
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi:**
```
The value of the variable is 5
```

> **Giải thích:** Trong Java, các kiểu nguyên thủy (`int`, `double`, `float`, `char`, `boolean`, ...) được truyền theo **giá trị (pass-by-value)**. Tương tự Value Type trong C#. Sự thay đổi bên trong phương thức **không ảnh hưởng** đến biến gốc bên ngoài.

---

### Bài tập 3b: Reference Type (Kiểu tham chiếu)

**Bước 1:** Tạo file `ReferenceTypeDemo.java`  
**Bước 2:** Nhập đoạn code sau:

```java
class ReferenceType {
    public int valueVal;
}

public class ReferenceTypeDemo {
    public static void main(String[] args) {
        ReferenceType refer = new ReferenceType();
        refer.valueVal = 5;
        test(refer);
        System.out.println("The value of the variable is " + refer.valueVal);
    }

    static void test(ReferenceType refer) {
        int temp = 5;
        refer.valueVal = temp * 2; // Thay đổi trực tiếp đối tượng được tham chiếu
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi:**
```
The value of the variable is 10
```

> **Giải thích:** Trong Java, các đối tượng (object) được truyền theo **tham chiếu**. Khi truyền một object vào phương thức, sự thay đổi trên thuộc tính của object **sẽ ảnh hưởng** đến đối tượng gốc.

---

### Bài tập 4: Xuất dữ liệu (Output)

**Bước 1:** Tạo file `Output.java`  
**Bước 2:** Nhập đoạn code sau:

```java
/* This program demonstrates the output operations in Java */
public class Output {
    public static void main(String[] args) {
        // Khai báo và khởi tạo các biến lưu thông tin sinh viên
        int id = 1;
        String name = "David George";
        byte age = 18;
        char gender = 'M';
        float percent = 75.50f;

        // Hiển thị thông tin sinh viên
        System.out.println("Student ID : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Gender : " + gender);
        System.out.printf("Percentage : %.2f%n", percent);
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi:**
```
Student ID : 1
Student Name : David George
Age : 18
Gender : M
Percentage : 75.50
```

> **So sánh cú pháp xuất dữ liệu C# → Java:**
>
> | C# | Java |
> |---|---|
> | `Console.WriteLine("text")` | `System.out.println("text")` |
> | `Console.Write("text")` | `System.out.print("text")` |
> | `Console.WriteLine("{0:F2}", x)` | `System.out.printf("%.2f%n", x)` |
> | `Console.WriteLine("{0}", x)` | `System.out.println(x)` hoặc `System.out.printf("%s%n", x)` |

---

### Bài tập 5: Nhập dữ liệu (Input)

**Bước 1:** Tạo file `InputDemo.java`  
**Bước 2:** Nhập đoạn code sau:

```java
import java.util.Scanner;

/* The program demonstrates the input and output operations. */
public class InputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hằng số lưu giá trị 100
        final int PERCENT_CONST = 100;

        // Khai báo biến lưu tên sinh viên
        String studentName;

        // Khai báo biến lưu điểm các môn
        int english, maths, science;

        // Khai báo và khởi tạo biến lưu phần trăm
        float percent = 0.0f;

        // Nhập thông tin sinh viên
        System.out.print("Enter name of the student : ");
        studentName = scanner.nextLine();

        System.out.print("Enter marks for english : ");
        english = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter marks for maths : ");
        maths = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter marks for science : ");
        science = Integer.parseInt(scanner.nextLine());

        // Tính phần trăm của sinh viên
        percent = ((english + maths + science) * PERCENT_CONST) / 300.0f;

        // Hiển thị thông tin sinh viên
        System.out.println("Student Name : " + studentName);
        System.out.println("Marks obtained in English : " + english);
        System.out.println("Marks obtained in Maths : " + maths);
        System.out.println("Marks obtained in Science : " + science);
        System.out.printf("Percent : %.2f%n", percent);

        scanner.close();
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi (ví dụ):**
```
Enter name of the student : Nguyen Van A
Enter marks for english : 80
Enter marks for maths : 90
Enter marks for science : 85
Student Name : Nguyen Van A
Marks obtained in English : 80
Marks obtained in Maths : 90
Marks obtained in Science : 85
Percent : 85.00
```

> **So sánh nhập dữ liệu C# → Java:**
>
> | C# | Java |
> |---|---|
> | `Console.ReadLine()` | `scanner.nextLine()` |
> | `Convert.ToInt32(Console.ReadLine())` | `Integer.parseInt(scanner.nextLine())` |
> | `Convert.ToDouble(Console.ReadLine())` | `Double.parseDouble(scanner.nextLine())` |
> | `const int x = 100` | `final int X = 100` |
> | Không cần import | `import java.util.Scanner;` |

---

### Bài tập 6: Định dạng số (Number Format Specifier)

**Bước 1:** Tạo file `NumberFormat.java`  
**Bước 2:** Nhập đoạn code sau:

```java
/* This program demonstrates the numeric formatting in Java */
public class NumberFormat {
    public static void main(String[] args) {
        // Định dạng tiền tệ (Currency)
        System.out.printf("Currency formatting     - $%.2f   $%.4f%n", 88.8, 888.8);

        // Định dạng số nguyên với padding
        System.out.printf("Integer formatting      - %05d%n", 88);

        // Định dạng số mũ (Exponential)
        System.out.printf("Exponential formatting  - %E%n", 888.8);

        // Định dạng số thực với 3 chữ số thập phân
        System.out.printf("Fixed-point formatting  - %.3f%n", 888.8888);

        // Định dạng tổng quát
        System.out.printf("General formatting      - %g%n", 888.8888);

        // Định dạng số với dấu phân cách nghìn
        System.out.printf("Number formatting       - %,.1f%n", 8888888.8);

        // Định dạng thập lục phân
        System.out.printf("Hexadecimal formatting  - %04X%n", 88);
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi:**
```
Currency formatting     - $88.20   $888.8000
Integer formatting      - 00088
Exponential formatting  - 8.888000E+02
Fixed-point formatting  - 888.889
General formatting      - 888.889
Number formatting       - 8,888,888.8
Hexadecimal formatting  - 0058
```

> **Bảng so sánh Format Specifier C# → Java (`printf`):**
>
> | C# Format | Java `printf` | Mô tả |
> |---|---|---|
> | `{0:C}` | `$%.2f` | Tiền tệ |
> | `{0:D5}` | `%05d` | Số nguyên với padding 0 |
> | `{0:E}` | `%E` | Ký hiệu khoa học |
> | `{0:F3}` | `%.3f` | Số thực 3 chữ số thập phân |
> | `{0:G}` | `%g` | Định dạng tổng quát |
> | `{0:N}` | `%,.1f` | Số với dấu phân cách |
> | `{0:X4}` | `%04X` | Thập lục phân |

---

### Bài tập 7: Định dạng ngày giờ (Datetime Format Specifier)

**Bước 1:** Tạo file `DateTimeFormat.java`  
**Bước 2:** Nhập đoạn code sau:

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.now(); // Lấy thời gian hiện tại

        // Định dạng ngày ngắn: dd/MM/yyyy
        System.out.println("Short date  : " + dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Định dạng ngày dài: EEEE, dd MMMM yyyy
        System.out.println("Long date   : " + dt.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));

        // Định dạng giờ ngắn: HH:mm
        System.out.println("Short time  : " + dt.format(DateTimeFormatter.ofPattern("HH:mm")));

        // Định dạng giờ dài: HH:mm:ss
        System.out.println("Long time   : " + dt.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        // Định dạng đầy đủ
        System.out.println("Full        : " + dt.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm")));

        // Định dạng tổng quát ngắn
        System.out.println("General     : " + dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        // Định dạng tháng-ngày
        System.out.println("Month-Day   : " + dt.format(DateTimeFormatter.ofPattern("MMMM dd")));

        // Định dạng ISO
        System.out.println("Sortable    : " + dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

        // Định dạng năm-tháng
        System.out.println("Year-Month  : " + dt.format(DateTimeFormatter.ofPattern("MMMM, yyyy")));
    }
}
```

**Bước 3:** Lưu, biên dịch và chạy  
**Kết quả mong đợi (ví dụ):**
```
Short date  : 14/09/2026
Long date   : Monday, 14 September 2026
Short time  : 19:17
Long time   : 19:17:43
Full        : Monday, 14 September 2026 19:17
General     : 14/09/2026 19:17
Month-Day   : September 14
Sortable    : 2026-09-14T19:17:43
Year-Month  : September, 2026
```

> **So sánh định dạng ngày giờ C# → Java:**
>
> | C# Format | Java Pattern | Mô tả |
> |---|---|---|
> | `{0:d}` | `"dd/MM/yyyy"` | Ngày ngắn |
> | `{0:D}` | `"EEEE, dd MMMM yyyy"` | Ngày dài |
> | `{0:t}` | `"HH:mm"` | Giờ ngắn |
> | `{0:T}` | `"HH:mm:ss"` | Giờ dài |
> | `{0:f}` | `"EEEE, dd MMMM yyyy HH:mm"` | Ngày giờ đầy đủ |
> | `{0:s}` | `"yyyy-MM-dd'T'HH:mm:ss"` | Định dạng ISO |
> | `{0:y}` | `"MMMM, yyyy"` | Tháng-Năm |
> | `DateTime.Now` | `LocalDateTime.now()` | Lấy thời gian hiện tại |

---

## Phần III: Tự thực hành – 60 phút

### Bài tập 1
Viết chương trình cho phép người dùng nhập: **tên, địa chỉ, số điện thoại** và hiển thị lại những thông tin đó.

### Bài tập 2
Viết chương trình nhận vào **3 số nguyên** và tìm số **lớn nhất** trong 3 số đó.

### Bài tập 3
Viết chương trình nhận vào một số trong khoảng **1 đến 7** từ người dùng và trả về **tên ngày trong tuần** tương ứng  
*(1 - Monday, 2 - Tuesday, ..., 7 - Sunday)*.

### Bài tập 4
Viết chương trình hiển thị **9 bội số đầu tiên** của một số nguyên `N` nhập từ bàn phím.

### Bài tập 5
Viết chương trình in ra **giai thừa** của các số nguyên từ 1 đến 20.

---

## Phần IV: Bài tập về nhà

- Bài tập 1: Tìm hiểu và thực hành về các kiểu dữ liệu nguyên thủy trong Java
- Bài tập 2: Tìm hiểu và thực hành về Wrapper Class trong Java (`Integer`, `Double`, `Boolean`, ...)
- Bài tập 3: Tìm hiểu về lớp `String` và các phương thức thông dụng trong Java

---

## Tài liệu tham khảo

1. [Java Documentation - Oracle](https://docs.oracle.com/en/java/)
2. [Java Tutorial - W3Schools](https://www.w3schools.com/java/)
3. [Java Tutorial - Tutorialspoint](https://www.tutorialspoint.com/java/index.htm)
4. [Java printf formatting](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html)
5. [Java DateTimeFormatter](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html)

---

*Tài liệu này được chuyển đổi từ C# Lab 1 - FPT Aptech (© 2009 FPT Aptech) sang Java*
