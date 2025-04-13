# Hướng dẫn chạy dự án Spring Boot

***Nhớ nhấn `Ctrl + Alt + L` (hoặc `Command + Option + L` trên macOS) để format code trước khi đẩy lên Git.***
## Mục lục API
* [Xác thực (Auth)](#xác-thực-auth)
    * [API đăng nhập](#api-đăng-nhập-login)
* [Người dùng (User)](#người-dùng-user)
    * [API tạo giảng viên](#api-tạo-giảng-viên)
    * [API lấy danh sách giảng viên](#api-lấy-danh-sách-giảng-viên)
    * [API cập nhật thông tin giảng viên](#api-cập-nhật-thông-tin-giảng-viên)
    * [API xóa thông tin giảng viên](#api-xóa-thông-tin-giảng-viên)
    * [API tìm kiếm giảng viên theo tên](#api-tìm-kiếm-giảng-viên-theo-tên)
* [Khóa học (Course)](#khóa-học-course)
    * [API lấy tất cả học phần](#api-lấy-tất-cả-học-phần)
    * [API tạo học phần](#api-tạo-học-phần)
    * [API cập nhật thông tin học phần](#api-cập-nhật-thông-tin-học-phần)
    * [API xóa học phần](#api-xóa-học-phần)
    * [API lấy học phần theo mã học phần](#api-lấy-học-phần-theo-mã-học-phần)
    * [API tìm kiếm học phần theo tên](#api-tìm-kiếm-học-phần-theo-tên)
    * [API thêm đề cương học phần](#api-thêm-đề-cương-học-phần)
    * [API lấy toàn bộ đề cương học phần](#api-lấy-toàn-bộ-đề-cương-học-phần)
    * [API cập nhật đề cương cho học phần](#api-cập-nhật-đề-cương-cho-học-phần)
    * [API xóa đề cương của học phần](#api-xóa-đề-cương-của-học-phần)
    * [API thêm học phần bắt buộc cho 1 học phần](#api-thêm-học-phần-bắt-buộc-cho-1-học-phần)
    * [API xóa học phần bắt buộc](#api-xóa-học-phần-bắt-buộc)
    * [API tìm kiếm đề cương học phần theo tên](#api-tìm-kiếm-để-cương-học-phần-theo-tên)
    * [API lấy đề cương học phần theo id](#api-lấy-để-cương-học-phần-theo-id)
* [Đào tạo (Training)](#đào-tạo-training)
    * [API tạo kế hoạch đào tạo](#api-tạo-kế-hoạch-đào-tạo)
    * [API cập nhật chương trình đào tạo](#api-cập-nhật-chương-trình-đào-tạo)
    * [API lấy danh sách chương trình đào tạo](#api-lấy-danh-sách-chương-trình-đào-tạo)
    * [API lấy chương trình đào tạo theo id](#api-lấy-chương-trình-đào-tạo-theo-id)
    * [API xóa chương trình đào tạo](#api-xóa-chương-trình-đào-tạo)
    * [API thêm khối kiến thức vào chương trình đào tạo](#api-thêm-khối-kiến-thức-vào-chương-trình-đào-tạo)
    * [API xóa khối kiến thức của chương trình đào tạo](#api-xóa-khối-kiến-thức-của-chương-trình-đào-tạo)
    * [API tìm kiếm chương trình đào tạo theo tên](#api-tìm-kiếm-chương-trình-đào-tạo-theo-tên)
    * [API tạo kế hoạch giảng dạy](#api-tạo-kế-hoạch-giảng-dạy)
    * [API lấy danh sách kế hoạch đào tạo](#api-lấy-danh-sách-kế-hoạch-đào-tạo)
## Yêu cầu

* Java 21
* Maven
* MySQL Server (XAMPP)

## Cấu hình

1.  **Tạo file `application.yaml` trong thư mục `resources`:**

    ![Hình ảnh minh họa](access/img.png)

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

* Sau khi chạy thành công, ứng dụng có thể được truy cập tại `http://localhost:3001`.

## Lưu ý

* Đảm bảo rằng MySQL Server đang chạy và bạn đã tạo cơ sở dữ liệu với tên được chỉ định trong `application.yaml`.
* Nếu bạn gặp lỗi kết nối cơ sở dữ liệu, hãy kiểm tra lại thông tin kết nối trong `application.yaml`.
* Nếu bạn có các thông tin cấu hình khác cần thiết, hãy thêm chúng vào file `application.yaml` theo đúng cú pháp YAML.

## API Endpoint

* **URL mặc định:** `http://localhost:3001/api`
### Xác thực (Auth)

* ***API đăng ký (register):*** Ứng dụng chỉ dùng cho 1 tài khoản admin duy nhất được tạo sẵn khi khởi động server.
* ***API đăng nhập (login):*** Tài khoản: `admin`, Mật khẩu: `123`
    * **Endpoint:** `/auth/login`
    * **Method:** `POST`
    * **Body:**

        ```json
        {
            "username": "admin",
            "password": "123"
        }
        ```

    * **Response:**

        ```json
        {
            "statusCode": 200,
            "message": "OK",
            "data": {
                "token": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ0cmFpbmluZy5jb20iLCJzdWIiOiJhZG1pbiIsImV4cCI6MTc0NDYwMDg5MywiaWF0IjoxNzQ0NTE0NDkzLCJqdGkiOiJjMDVlMWZiNS04ZWQxLTRhMDYtYWI3NS0wOGQwNDU1YTY2M2YiLCJzY29wZSI6ImFkbWluIn0.hGZz4K09keyA3_eT_xruHn2k6kwDO_XJ6nMOnHReDisq7_9Py86ND4FbwEeKuKPkCLRs2CojoDGObg-8Q3EsBw"
            },
            "success": true
        }
        ```

### Người dùng (User)

* Gồm các API CRUD và tìm kiếm cho chức năng quản lý giảng viên.
* Bắt buộc phải có token khi gửi yêu cầu, trả về lỗi `401` nếu không có.
* ***API tạo giảng viên:*** Tạo thông tin giảng viên (không bao gồm tạo tài khoản đăng nhập cho giảng viên).
    * **Endpoint:** `/users/lecturer-account`
    * **Method:** `POST`
    * **Body:**

        ```json
        {
            "name": "nguyen van a",
            "lecturerCode": "3122410003",
            "gender": "nam",
            "titleAcademicRank": "tiến sĩ",
            "avatar": "ạhaljflsk",
            "department": "CNTT",
            "status": true,
            "dob": "2000-10-10",
            "startDateOfTeaching": "2010-10-10"
        }
        ```

    * **Response:** Trả về lỗi `409` nếu `lecturerCode` (mã giảng viên) đã có trong hệ thống.

        ```json
        {
            "statusCode": 200,
            "data": {
                "id": "d026d798-7fa8-4e82-b3b5-27e766a95789",
                "role": "lecturer",
                "name": "nguyen van a",
                "lecturerCode": "3122410004",
                "gender": "nam",
                "titleAcademicRank": "tiến sĩ",
                "avatar": "ạhaljflsk",
                "department": "CNTT",
                "status": true,
                "dob": "2000-10-10T00:00:00.000+00:00",
                "startDateOfTeaching": "2025-04-13T03:22:34.928+00:00",
                "endDateOfTeaching": null,
                "createAt": "2025-04-13T10:22:34.928+00:00",
                "updateAt": "2025-04-13T10:22:34.928+00:00"
            },
            "success": true
        }
        ```

* ***API lấy danh sách giảng viên:*** Lấy tất cả giảng viên đang có.
    * **Endpoint:** `/users/lecturer/get-all`
    * **Method:** `GET`
* ***API cập nhật thông tin giảng viên***
    * **Endpoint:** `/users/lecturer/{id giảng viên}`
    * **Method:** `PUT`
    * **Body:**

        ```json
        {
            "name": "nguyen van b",
            "lecturerCode": "3122410003",
            "gender": "nữ",
            "status": true,
            "dob": "2000-10-10",
            "startDateOfTeaching": "2000-10-10",
            "endDateOfTeaching": "2020-10-10"
        }
        ```

    * **Response:** Nếu mã giảng viên mới đã có trong hệ thống sẽ trả về lỗi `409`.

        ```json
        {
            "statusCode": 200,
            "data": {
                "id": "e87603a8-5702-4cbe-95f5-4e3b92265d1e",
                "role": "lecturer",
                "name": "nguyen van b",
                "lecturerCode": "3122410003",
                "gender": "nữ",
                "titleAcademicRank": null,
                "avatar": null,
                "department": null,
                "status": true,
                "dob": "2000-10-10T00:00:00.000+00:00",
                "startDateOfTeaching": "2000-10-10T00:00:00.000+00:00",
                "endDateOfTeaching": "2020-10-10T00:00:00.000+00:00",
                "createAt": "2025-04-08T15:20:50.000+00:00",
                "updateAt": "2025-04-13T10:37:43.000+00:00"
            },
            "success": true
        }
        ```

* ***API xóa thông tin giảng viên:*** Xóa tạm thời, thông tin giảng viên vẫn còn trong database và vẫn có thể khôi phục sau khi xóa.
    * **Endpoint:** `/users/lecturer/{id giảng viên}`
    * **Method:** `DELETE`
* ***API tìm kiếm giảng viên theo tên***
    * **Endpoint:** `/users/lecturer/search?name={tên giảng viên}`
    * **Method:** `GET`

### Khóa học (Course)

* Gồm các API cho chức năng quản lý học phần và đề cương học phần của học phần đó.
* Cần token.
* ***API lấy tất cả học phần:*** Trả về thông tin học phần và danh sách học phần tiên quyết của học phần đó.
    * **Endpoint:** `/courses`
    * **Method:** `GET`
    * **Response:** Học phần tiên quyết (prerequisites) gồm mã học phần và tên học phần.

        ```json
        {
            "statusCode": 200,
            "data": [
                {
                    "courseCode": "CS101",
                    "courseName": "Nhập môn Lập trình",
                    "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
                    "status": true,
                    "prerequisites": [
                        {
                            "courseCode": "CS201",
                            "courseName": "Nhập môn Lập trình java"
                        }
                    ]
                }
            ],
            "success": true
        }
        ```

* ***API tạo học phần***
    * **Endpoint:** `/courses`
    * **Method:** `POST`
    * **Body:**

        ```json
        {
            "courseCode": "CS202",
            "courseName": "Nhập môn Lập trình java",
            "credits": 3,
            "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
            "status": true,
            "prerequisites": ["CS101"]
        }
        ```

    * **Response:** Trả về lỗi `409` nếu mã học phần đã tồn tại.
* ***API cập nhật thông tin học phần***
    * **Endpoint:** `/courses/{mã học phần}`
    * **Method:** `PUT`
    * **Body:**

        ```json
        {
            "courseName": "Nhập môn Lập trình",
            "credits": 3,
            "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
            "status": true
        }
        ```

* ***API xóa học phần:*** Xóa tạm thời học phần.
    * **Endpoint:** `/courses/{mã học phần}`
    * **Method:** `DELETE`
* ***API lấy học phần theo mã học phần***
    * **Endpoint:** `/courses/{mã học phần}`
    * **Method:** `GET`
* ***API tìm kiếm học phần theo tên***
    * **Endpoint:** `/courses/search?name={tên học phần}`
    * **Method:** `GET`
* ***API thêm đề cương học phần***
    * **Endpoint:** `/courses/course-syllabus/{mã học phần}`
    * **Method:** `POST`
    * **Body:**

        ```json
        {
            "syllabusContent": "Nội dung chi tiết giới thiệu về học phần này, bao gồm mục tiêu, cấu trúc, tài liệu tham khảo, và các yêu cầu khác.",
            "theory": 30,
            "practice": 45,
            "credit": 3,
            "evaluationComponents": {
                "componentName": "Bài kiểm tra giữa kỳ",
                "ratio": 30
            }
        }
        ```

* ***API lấy toàn bộ đề cương học phần***
    * **Endpoint:** `/courses/course-syllabus`
    * **Method:** `GET`
* ***API cập nhật đề cương cho học phần***
    * **Endpoint:** `/courses/course-syllabus/{id đề cương}`
    * **Method:** `PUT`
    * **Body:**

        ```json
        {
            "syllabusContent": "Nội dung chi tiết giới thiệu về học phần nàyc.",
            "theory": 30,
            "practice": 45,
            "credit": 3,
            "evaluationComponents": {
                "componentName": "Bài kiểm tra giữa kỳ",
                "ratio": 30
            }
        }
        ```

    * **Response:** status = true: chưa bị xóa

        ```json
        {
            "statusCode": 200,
            "data": {
                "syllabusId": "6aa48c2b-52c2-48ff-a94a-c40363306a02",
                "syllabusContent": "Nội dung chi tiết giới thiệu về học phần nàyc.",
                "theory": 30,
                "practice": 45,
                "credit": 3,
                "status": true,
                "evaluationComponents": {
                    "id": "154d5693-54b7-4295-b833-d75576df1437",
                    "componentName": "Bài kiểm tra giữa kỳ",
                    "ratio": 30
                },
                "courseResponse": {
                    "courseCode": "CS101",
                    "courseName": "Nhập môn Lập trình",
                    "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
                    "status": true,
                    "prerequisites": [
                        {
                            "courseCode": "CS201",
                            "courseName": "Nhập môn Lập trình java"
                        }
                    ]
                },
                "createAt": "2025-04-08T15:21:28.000+00:00",
                "updateAt": "2025-04-08T21:00:29.000+00:00"
            },
            "success": true
        }
        ```

* ***API xóa để cương của học phần***
    * **Endpoint:** `/courses/course-syllabus/ id đề cương học phần`
    * **Method:** `DELETE`
* ***API thêm học phần bắt buộc cho 1 học phần:*** Thêm 1 hoặc nhiều học phần phải học trước để học học phần tiếp theo
    * **Endpoint:** `/courses/prerequisites/mã học phần`
    * **Method:** `POST`
    * **Body:** mảng các mã học phần bắt buộc

        ```json
         {
          "prerequisites":["CS201"]     
      }
        ```

    * **Response:**

        ```json
          {
          "statusCode": 200,
          "data": {  
         "courseCode": "CS101",
           "courseName": "Nhập môn Lập trình",
          "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
         "status": true,
         "prerequisites": [
               {
             "courseCode": "CS201",
             "courseName": "Nhập môn Lập trình java"
                      }
                      ]
                      },
                "success": true   
               }
         ```

* ***API xóa học phần bắt buộc***
    * **Endpoint:** `/courses/prerequisites/mã học phần chứa học phần bắt buộc đó`
    * **Method:** `POST`
    * **Body:**

        ```json
         {
         "prerequisiteCode":"CS201"
          }
        ```

    * **Response:** Trả về học phần chứa mảng các học phần bắt buộc còn lại.
* ***API tìm kiếm để cương học phần theo tên***
    * **Endpoint:** `/courses/course-syllabus/search?name={Tên học phần}`
    * **Method:** `GET`
    * **Response:**

        ```json
         { 
             "statusCode": 200,
               "data": [
               {
        "syllabusId": "b9fbaa6e-aecf-4367-8eb1-e28952dc6db8",
        "syllabusContent": "Nội dung chi tiết giới thiệu về học phần này, bao gồm mục tiêu, cấu trúc, tài liệu tham khảo, và các yêu cầu khác.",
        "theory": 30,
        "practice": 45,
        "credit": 3,
        "status": true,
        "evaluationComponents": null,
        "courseResponse": {
        "courseCode": "CS103",
        "courseName": "Nhập môn Lập trình java",
        "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
        "status": true,
        "prerequisites": []
        },
        "createAt": "2025-04-08T16:31:55.000+00:00",
        "updateAt": "2025-04-08T16:31:55.000+00:00"
        }
             ],
        "success": true
          }
        ```

* ***API lấy để cương học phần theo id***
    * **Endpoint:** `/courses/course-syllabus/id để cương học phần`
    * **Method:** `GET`
* ***API thêm học phần vào để cương học phần*** đang bổ xung
### Đào tạo(training)

* gồm các api cho chức năng quản chương trình đào tạo
* các api phần công giảng dạy(chưa có).
* can token để truy cập.
* ***API tạo kế hoạch đào tạo***
    * **Endpoint:** `/trainings`
    * **Method:** `POST`
    * **Body:**

        ```json
      {
       "trainingProgramId": "TP002",
       "trainingProgramName": "Chương trình đào tạo Cử nhân Khoa học Máy tính",
       "educationLevel": "Đại học",
       "degreeType": "Cử nhân",
       "trainingMode": "Chính quy",
       "trainingDuration": 4,
       "totalCredits": 140,
       "managingFaculty": "Khoa Công nghệ Thông tin",
       "teachingLanguage": "Tiếng Việt",
       "issuingDecision": "Quyết định số 123/QĐ-ĐH ngày 15/03/2025"
       }
        ```

* ***API cập nhật trương trình đào tạo***
    * **Endpoint:** `/trainings`
    * **Method:** `PUT`
    * **Body:**

        ```json
           {
        "trainingProgramId": "TP002",
        "trainingProgramName": "Chương trình đào tạo Cử nhân Khoa học Máy tính 2",
        "educationLevel": "Đại học",
        "degreeType": "Cử nhân",
        "trainingMode": "Chính quy",
        "trainingDuration": 4,
        "totalCredits": 140,
        "managingFaculty": "Khoa Công nghệ Thông tin",
        "teachingLanguage": "Tiếng Việt",
        "issuingDecision": "Quyết định số 123/QĐ-ĐH ngày 15/03/2025",
        "website": "[https://fit.sgu.edu.vn/site/](https://fit.sgu.edu.vn/site/)"
       }
        ```

* ***API Lấy danh sách chương trình đào tạo***
    * **Endpoint:** `/trainings`
    * **Method:** `GET`
    * **Response:** trả về thông tin chương trinh đạo tạo, kế hoạch giảng dạy và khối kiến thức.
* ***API lấy chương trình đào tạo theo id***
    * **Endpoint:** `/trainings/ id  của chương trình đào tạo`
    * **Method:** `GET`
* ***API xóa chương trình đào tạo***
    * **Endpoint:** `/trainings/ id  của chương trình đào tạo`
    * **Method:** `DELETE`
* ***API Thêm khối kiến thức vào chương trình đào tạo***
    * **Endpoint:** `/trainings/knowledge-block`
    * **Method:** `POST`
    * **Body:**

        ```json
               {
       "blockCode": "GDDC-CNTT",
       "blockName": "Khối kiến thức giáo dục đại cương",
       "requiredCredits": 34,
       "electiveCredits": 0,
       "trainingCode": "TP001",
       "knowledgeBlockDetailList": [
       {
       "typeName": "Kiến thức Giáo dục thể chất và Giáo dục quốc phòng và an ninh.",
       "requiredCredits": 12,
       "electiveCredits": 2
       },
       {
       "typeName": "Kiến thức Ngoại ngữ",
       "requiredCredits": 9,
       "electiveCredits": 0
       },
       {
       "typeName": "Kiến thức Lý luận chính trị",
       "requiredCredits": 11,
       "electiveCredits": 0
       },
       {
       "typeName": "Kiến thức giáo dục đại cương khác",
       "requiredCredits": 14,
       "electiveCredits": 0
       }
       ]
       }
        ```
* ***API xóa khối kiến thức của trương trình đào tạo***
    * **Endpoint:** `/trainings/knowledge/ mã chương trình đạo tạo(blockCode)`
    * **Method:** `DELETE`
* ***API tìm kiếm chương trình đào tạo theo tên***
    * **Endpoint:** `/trainings/search?name={ký tự có trong tên chương trình đào tạo}`
    * **Method:** `GET`
* ***API Tạo kế hoạch giảng dạy***
    * **Endpoint:** `/teaches`
    * **Method:** `POST`
    * **Body:**

        ```json
          {
          "teachingPlanId":"CNTT16",
          "trainingProgramId":"TP001",
           "semester":1,
            "academicYear":2025,
              "courseId":["CS101"]
                }
        ```

* ***API Lấy danh sách kế hoạch đào tạo***
    * **Endpoint:** `/teaches`
    * **Method:** `GET`
    * **Response:**

        ```json
          {
            "teachingPlanId": "CNTT16",
            "semester": 1,
            "academicYear": 2025,
            "academicYearString": "2025-2026",
            "courseResponse": [
                {
                    "courseCode": "CS101",
                    "courseName": "Nhập môn Lập trình",
                    "description": "Khóa học này cung cấp các kiến thức cơ bản về lập trình.",
                    "status": true,
                    "prerequisites": []
                }
            ]
        }
        ```
