package com.trainingapi.trainingAPi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION( HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi không xác định",false),
    INVALID_KEY(HttpStatus.BAD_REQUEST, "Lỗi không xác định",false ),
    DATA_REQUEST_INVALID(HttpStatus.BAD_REQUEST,"Dữ  liệu giửi lên không đúng định dạng!",false),
    USER_EXISTED(HttpStatus.BAD_REQUEST, "Tài khoản đã tồn tại", false),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "Tên người dùng không được ngắn hơn {min} ký tự", false),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Mật khẩu không được ngắn hơn {min} ký tự", false),
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND, "Người dùng không tồn tại",false ),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Không thể xác thực", false),
    UNAUTHORIZED(HttpStatus.FORBIDDEN, "Bạn không có quyền truy cập",false ),
    LECTURER_CODE_EXISTED(HttpStatus.CONFLICT,"Mã giảng viên đã tồn tại",false),
    COURSE_EXISTED(HttpStatus.CONFLICT,"Học phần đã tồn tại!",false),
    COURSE_NOT_EXIST(HttpStatus.BAD_REQUEST,"Học phần không tồn tại!",false),
    ;

    private HttpStatusCode statusCode;
   private String message;
   private boolean isSuccess;

    ErrorCode(HttpStatusCode statusCode, String message, boolean isSuccess) {
        this.statusCode = statusCode;
        this.message = message;
        this.isSuccess = isSuccess;
    }
}
