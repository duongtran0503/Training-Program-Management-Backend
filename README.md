# Hướng dẫn chạy dự án Spring Boot
 ***Nhớ nhấn ctrl +alt + L để format code trước khi đầy lên git***
## Yêu cầu

* Java 21
* Maven 
* MySQL Server (xampp)

## Cấu hình

1.  **Tạo file `application.yaml` trong thư mục `resources`:**

    ![Hình ảnh minh họa](img.png)

2.  **Thêm JWT Secret Key:**

    ```yaml
    jwt:
      signerKey: "1TjXchw5FloESb63Kc+DFhTARvpWL4jUGCwfGWxuG5SIf/1y/LgJxHnMqaF6A/ij"
    ```

3.  **Thêm cấu hình kết nối database:**

    ```yaml
    spring:
      datasource:
        url: "jdbc:mysql://<your_database_url>?createDatabaseIfNotExist=true&serverTimezone=UTC"
        driverClassName: "com.mysql.cj.jdbc.Driver"
        password: "<your_database_password>"
        username: "root"
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```

    * Thay thế `<your_database_url>` và `<your_database_password>` bằng thông tin kết nối cơ sở dữ liệu thực tế của bạn.
    * **Lưu ý:** Tránh lưu trữ mật khẩu trực tiếp trong file cấu hình. Sử dụng biến môi trường hoặc Spring Cloud Config Server để quản lý thông tin nhạy cảm.

4.  **Thêm cấu hình port:**

    ```yaml
    server:
      port: 3001
    ```

## Chạy dự án

1.  **Sử dụng Maven:**

    ```bash
    mvn spring-boot:run
    ```



## Truy cập ứng dụng

* Sau khi chạy thành công, ứng dụng sẽ có thể truy cập tại `http://localhost:3001`.

## Lưu ý

* Đảm bảo rằng MySQL Server đang chạy và bạn đã tạo cơ sở dữ liệu với tên được chỉ định trong `application.yaml`.
* Nếu bạn gặp lỗi kết nối cơ sở dữ liệu, hãy kiểm tra lại thông tin kết nối trong `application.yaml`.
* Nếu bạn có các thông tin cấu hình khác cần thiết, hãy thêm chúng vào file `application.yaml` theo đúng cú pháp YAML.