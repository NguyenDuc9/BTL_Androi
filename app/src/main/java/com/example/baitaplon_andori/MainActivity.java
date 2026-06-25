package com.example.baitaplon_andori;

import android.content.Intent;
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
                String tk = Account.getText().toString().trim();
                String mk = Password.getText().toString().trim();

                if (tk.isEmpty() || mk.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Tài khoản và mật khẩu không được để trống",
                            Toast.LENGTH_SHORT
                    ).show();

                }
                else if (tk.equals("admin")
                        && mk.equals("123456")) {

                    Toast.makeText(
                            MainActivity.this,
                            "Đăng nhập Admin thành công!",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent =
                            new Intent(
                                    MainActivity.this,
                                    HomeActivity.class
                            );

                    startActivity(intent);
                    finish();

                }
                else if (tk.equals("user")
                        && mk.equals("123456")) {

                    Toast.makeText(
                            MainActivity.this,
                            "Đăng nhập User thành công!",
                            Toast.LENGTH_SHORT
                    ).show();

                    Intent intent =
                            new Intent(
                                    MainActivity.this,
                                    HomeUserActivity.class
                            );

                    startActivity(intent);
                    finish();

                }
                else {

                    Toast.makeText(
                            MainActivity.this,
                            "Tài khoản hoặc mật khẩu không chính xác",
                            Toast.LENGTH_SHORT
                    ).show();

                }
            }
        });
    }
}