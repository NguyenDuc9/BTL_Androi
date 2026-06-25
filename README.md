# XÂY DỰNG ỨNG DỤNG MOBILE HỌC TOEIC

## Giới thiệu đề tài

### Bài toán

Trong quá trình học TOEIC, người học thường gặp khó khăn trong việc ghi nhớ từ vựng, ôn tập theo chủ đề và kiểm tra kiến thức. Việc học bằng giấy hoặc tài liệu rời rạc khiến quá trình ôn tập thiếu hiệu quả.

### Mục tiêu

Xây dựng ứng dụng Android hỗ trợ học từ vựng TOEIC với các chức năng:

* Quản lý chủ đề từ vựng.
* Quản lý từ vựng theo từng chủ đề.
* Học bằng thẻ ghi nhớ (Flashcard).
* Làm bài kiểm tra trắc nghiệm.
* Lưu trữ dữ liệu bằng SQLite.
* Giao diện đơn giản, dễ sử dụng.

---

# Dataset

Ứng dụng sử dụng dữ liệu được lưu trực tiếp trong cơ sở dữ liệu SQLite.

## Các bảng dữ liệu

### Bảng ChuDe

| Cột      | Kiểu dữ liệu | Mô tả      |
| -------- | ------------ | ---------- |
| MaChuDe  | INTEGER      | Khóa chính |
| TenChuDe | TEXT         | Tên chủ đề |

Ví dụ:

* Kinh doanh
* Du lịch
* Văn phòng

---

### Bảng TuVung

| Cột            | Kiểu dữ liệu | Mô tả            |
| -------------- | ------------ | ---------------- |
| MaTu           | INTEGER      | Khóa chính       |
| TuTiengAnh     | TEXT         | Từ tiếng Anh     |
| NghiaTiengViet | TEXT         | Nghĩa tiếng Việt |
| HinhAnh        | TEXT         | Đường dẫn ảnh    |
| MaChuDe        | INTEGER      | Mã chủ đề        |

---

### Bảng TheGhiNho

| Cột   | Kiểu dữ liệu | Mô tả              |
| ----- | ------------ | ------------------ |
| MaThe | INTEGER      | Khóa chính         |
| MaTu  | INTEGER      | Mã từ              |
| DaNho | INTEGER      | Trạng thái ghi nhớ |

---

### Bảng BaiTest

| Cột        | Kiểu dữ liệu | Mô tả            |
| ---------- | ------------ | ---------------- |
| MaBaiTest  | INTEGER      | Khóa chính       |
| TenBaiTest | TEXT         | Tên bài kiểm tra |
| TongSoCau  | INTEGER      | Tổng số câu hỏi  |

---

### Bảng CauHoi

| Cột       | Kiểu dữ liệu | Mô tả            |
| --------- | ------------ | ---------------- |
| MaCauHoi  | INTEGER      | Khóa chính       |
| NoiDung   | TEXT         | Nội dung câu hỏi |
| DapAnA    | TEXT         | Đáp án A         |
| DapAnB    | TEXT         | Đáp án B         |
| DapAnC    | TEXT         | Đáp án C         |
| DapAnD    | TEXT         | Đáp án D         |
| DapAnDung | TEXT         | Đáp án đúng      |
| GiaiThich | TEXT         | Giải thích       |

---

# Chức năng chính

## Quản lý chủ đề

* Thêm chủ đề
* Sửa chủ đề
* Xóa chủ đề
* Hiển thị danh sách chủ đề

## Quản lý từ vựng

* Thêm từ vựng
* Sửa từ vựng
* Xóa từ vựng
* Gắn hình ảnh
* Tìm kiếm từ vựng

## Flashcard

* Hiển thị từ tiếng Anh
* Hiển thị nghĩa tiếng Việt
* Đánh dấu đã nhớ

## Bài kiểm tra

* Hiển thị câu hỏi trắc nghiệm
* Chọn đáp án
* Kiểm tra kết quả
* Hiển thị giải thích

---

# Kiến trúc dự án

Ứng dụng được xây dựng theo mô hình:

DAO Pattern

Luồng xử lý:

SQLite Database
↓
DAO Layer
↓
Activity
↓
RecyclerView Adapter
↓
User Interface

---

# Công nghệ sử dụng

* Java
* Android Studio
* SQLite
* RecyclerView
* CardView
* Material Design

---

# Hướng dẫn chạy

## Yêu cầu

* Android Studio Hedgehog hoặc mới hơn
* Android SDK 24+
* JDK 17

## Clone dự án

```bash
git clone https://github.com/NguyenDuc9/BTL_Androi.git
```

## Mở dự án

1. Mở Android Studio
2. Chọn Open Project
3. Chọn thư mục BTL_Androi

## Build Project

```bash
Build → Rebuild Project
```

## Chạy ứng dụng

```bash
Run → Run app
```

Hoặc nhấn:

```text
Shift + F10
```

---

# Cấu trúc thư mục

```text
app/
│
├── adapter/
│   ├── ChuDeAdapter.java
│   ├── TuVungAdapter.java
│   └── CauHoiAdapter.java
│
├── database/
│   └── DatabaseHelper.java
│
├── data/
│   ├── ChuDeDAO.java
│   ├── TuVungDAO.java
│   ├── CauHoiDAO.java
│   └── BaiTestDAO.java
│
├── dialog/
│   ├── ThemChuDeDialog.java
│   ├── SuaChuDeDialog.java
│   ├── ThemTuVungDialog.java
│   └── SuaTuVungDialog.java
│
├── model/
│   ├── ChuDe.java
│   ├── TuVung.java
│   ├── CauHoi.java
│   └── BaiTest.java
│
├── MainActivity.java
├── ChuDeActivity.java
├── TuVungActivity.java
├── CauHoiActivity.java
└── ...
```

---

# Kết quả đạt được

* Hoàn thành CRUD Chủ đề.
* Hoàn thành CRUD Từ vựng.
* Hoàn thành CRUD Câu hỏi.
* Lưu dữ liệu bằng SQLite.
* Hiển thị danh sách bằng RecyclerView.
* Hỗ trợ thêm hình ảnh cho từ vựng.
* Tích hợp Flashcard học từ vựng.

---

# Tác giả

Họ và tên: Nguyễn Văn Đức

Mã sinh viên: 10123102

Lớp: 12523W2

Họ và tên: Tạ Đức Huy

Mã sinh viên: 12523118

Lớp: 12523W2

Ngành: Software Engineering

Trường: Hung Yen University of Technology and Education

---

# Giấy phép

Dự án được xây dựng phục vụ mục đích học tập và nghiên cứu.
