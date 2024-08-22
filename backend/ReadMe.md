**TOUR BOOKING**

1. Service:
    + Gateway
    + Registry
    + Auth
    + Agency
    + Customer
    + Tour
    + Booking
    + Payment
    + Search

---

2. Database:
    + Auth + Agency + Customer: mysql
    + Tour: mysql
    + Booking: postgres
    + Payment: postgres
    + Search: elasticsearch

---

3. Communicate:
    + RESTful API
    + Kafka

---

4. Cache:
    + Redis

---

5. Danh sách Actors:
    + WebAdmin:
        + CRUD Agency
        + CRUD Customers
        + Ban Tour
      
    + Agency:
        + CRUD Employee Admin (ADMIN_EMPLOYEE_MANAGEMENT)
        + CRUD Tour Admin (ADMIN_TOUR_MANAGEMENT)
        + Quản lý doanh thu (REVENUE_MANAGEMENT)
        + Hướng dẫn viên du lịch (TOUR_GUIDE)
      
    + Customer:
        + User (USER)

---

6. Danh sách API:
    + Auth:
        + Login: /auth/login
        + Logout: /auth/logout
        + Change password: /auth/change-password
        + Reset password: /auth/reset-password

    + Agency:
        + Đăng Ký account agency: /agency/register [POST]
          -> ADMIND verify -> create và cấp tài khoản
        + CRUD account employees: /agency/employees [CRUD]

    + Customer:
        + Đăng ký account customers: /customer/register [POST]
        + Change user info: /customers/info [GET + PUT]

    + Tour:
        + Tạo tour: /tours
        + RUD tour: /tours/{id}  -  nhớ check agency
        + Verify: /tours/{id}/verify
        + Đánh giá: /tours/{id}/rating

    + Booking:
        + User-Booking: /booking [POST]
        + User-RUD info booking: /booking/{id} [PATCH + DELETE]
        + Agency-Hoàn thành tour: /booking/{id}/success-confirm [PATCH]
        + Agency-Hủy tour: /booking/{id}/cancel-confirm [PATCH]

    + Payment:
        + Tạo request thanh toán: /payments [POST]
        + Xem thông tin thanh toán: /payments/{bookingCode} [GET]

7. Danh sách Job:
    + Job thông báo trước ngày khởi hành [Booking]
    + Job thông báo booking/payment cọc tour [Booking + Payment]
    + Job thông báo hủy tour [Booking]
    + Job thông báo cho customer đánh giá [Booking]