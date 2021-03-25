package com.diseno.demo.common;

/**
 * date 2021-03-11 16:28
 *
 * @author Phạm Ngọc Thắng
 */
public interface ErrorsConstant {

    String ERROR_SYSTEM = "Không thành công. Bạn vui lòng thử lại sau!";
    String ERROR_COMMON = "Không thành công. Bạn vui lòng thử lại sau!!!";
    String ERROR_TOKEN = "Lỗi xác thực người dùng";
    String BAD_CREDENTIALS = "Thông tin đăng nhập không đúng";
    String ERROR_INPUT = "Lỗi dữ liệu truyền vào";
    String ERROR_INPUT_PARAMETER = "Lỗi dữ liệu truyền vào ở tham số";
    String ERROR_MAX_FILE = "Kích thước vượt quá 5MB";
    String ERROR_INPUT_SQL = "Lỗi thao tác với dữ liệu";
    String NOT_SUPPORT_METHOD = "Không hỗ trợ cách truy vấn này";
    String NOT_FOUND_DATABASE_FIND = "Không tìm thấy dữ liệu trong hệ thống";
    String NOT_FOUND_DATABASE_DELETE = "Không tồn tại dữ liệu trong hệ thống";
    String ERROR_FIREBASE = "Lỗi gửi thông báo qua ứng dụng";

    String ERROR_NO_USER="Không tồn tại tài khoản trong hệ thống.";
}
