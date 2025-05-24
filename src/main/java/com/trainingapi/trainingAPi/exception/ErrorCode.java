package com.trainingapi.trainingAPi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi không xác định", false),
    INVALID_KEY(HttpStatus.BAD_REQUEST, "Lỗi không xác định", false),
    DATA_REQUEST_INVALID(HttpStatus.BAD_REQUEST, "Dữ liệu gửi lên không đúng định dạng!", false),
    USER_EXISTED(HttpStatus.BAD_REQUEST, "Tài khoản đã tồn tại", false),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "Tên người dùng không được ngắn hơn {min} ký tự", false),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Mật khẩu không được ngắn hơn {min} ký tự", false),
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND, "Người dùng không tồn tại", false),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Không thể xác thực", false),
    UNAUTHORIZED(HttpStatus.FORBIDDEN, "Bạn không có quyền truy cập", false),
    LECTURER_CODE_EXISTED(HttpStatus.CONFLICT, "Mã giảng viên đã tồn tại", false),
    LECTURER_NOT_EXIST(HttpStatus.CONFLICT, "Mã giảng viên không tồn tại", false),
    COURSE_EXISTED(HttpStatus.CONFLICT, "Học phần đã tồn tại!", false),
    COURSE_NOT_EXIST(HttpStatus.BAD_REQUEST, "Học phần không tồn tại!", false),
    COURSE_SYLLABUS_NOT_EXIST(HttpStatus.BAD_REQUEST, "Đề cương học phần không tồn tại! ", false),
    TRAINING_PROGRAM_EXISTED(HttpStatus.BAD_REQUEST, "Đề cương học phần đã tồn tại! ", false),
    TRAINING_PROGRAM_NOT_EXIST(HttpStatus.BAD_REQUEST, "Đề cương học phần không tồn tại! ", false),
    KNOWLEDGE_BLOCK_EXISED(HttpStatus.BAD_REQUEST, "Đề cương đã tồn tại! ", false),
    TEACHING_PLAN_EXISTED(HttpStatus.CONFLICT, "Kế hoạch đào tạo đã tồn tại!", false),
    TEACHING_PLAN_NOT_EXIST(HttpStatus.CONFLICT, "Kế hoạch đào tạo không tồn tại!", false),
    INVALID_FILE(HttpStatus.BAD_REQUEST, "File không hợp lệ", false),
    TRAINING_PROGRAM_ID_MISMATCH(HttpStatus.BAD_REQUEST, "Mã chương trình đào tạo không khớp", false); // Thêm hằng số mới

    private HttpStatusCode statusCode;
    private String message;
    private boolean isSuccess;

    ErrorCode(HttpStatusCode statusCode, String message, boolean isSuccess) {
        this.statusCode = statusCode;
        this.message = message;
        this.isSuccess = isSuccess;
    }
}