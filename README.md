**Tóm tắt:**
Đây là một dự án cá nhân nhằm hỗ trợ việc tự động hóa gửi ý tưởng cho CỔNG THÔNG TIN Ý TƯỞNG SÁNG TẠO THÀNH PHỐ HỒ CHÍ MINH.
Dự án cung cấp 2 cách gửi ý tưởng:
1. Gửi ý tưởng bằng API (nhanh hơn)
2. Gửi ý tưởng thông qua UI giao diện web


**Cách sử dụng:**
1. Tạo một gradle project trong IntelliJ và pull dự án về
2. Thiết lập lại các thông tin nếu cần (authorName, email, phone, ward) ở trong 2 class ApiRequestAdd và UiAutomation. Biến ward ở ApiRequestAdd phải chính xác, còn ở UiAutomation có thể tương đối
3. Sửa lại data ở file excel (.xlsx) nếu cần
4. Chạy UiAutomation hoặc ApiRequestAdd
