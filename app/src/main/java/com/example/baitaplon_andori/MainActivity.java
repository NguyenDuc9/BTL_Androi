package com.example.baitaplon_andori;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Account, Password;
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Account = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Lấy dữ liệu từ EditText và xóa khoảng trắng thừa
                String tk = Account.getText().toString().trim();
                String mk = Password.getText().toString().trim();

                // 2. Kiểm tra xem có trường nào bị trống không
                if (tk.isEmpty() || mk.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Tài khoản và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                }
                // 3. Nếu người dùng đã nhập đầy đủ, tiến hành kiểm tra thông tin
                else {
                    // Ví dụ kiểm tra tài khoản mật khẩu cố định (Bạn có thể thay bằng logic của bạn hoặc gọi API)
                    if (tk.equals("admin") && mk.equals("123456")) {
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        // Code chuyển sang màn hình chính (HomeActivity) nếu cần:
                        // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        // startActivity(intent);
                        // finish(); // Đóng màn hình đăng nhập lại nếu không muốn người dùng quay lại bằng nút Back
                    } else {
                        // Thông báo nếu sai tài khoản hoặc mật khẩu
                        Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}