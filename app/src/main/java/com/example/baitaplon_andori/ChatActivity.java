package com.example.baitaplon_andori;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplon_andori.adapter.ChatAdapter;
import com.example.baitaplon_andori.model.ChatMessage;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    private EditText edtMessage;

    private Button btnSend;

    private RecyclerView rvChat;

    private ChatAdapter adapter;

    private ArrayList<ChatMessage> messages;

    // Emulator
    private static final String API_URL =
            "http://10.0.2.2:3001/api/ai/chat";

    // Nếu dùng điện thoại thật:
    // private static final String API_URL =
    // "http://192.168.1.10:3001/api/ai/chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        rvChat = findViewById(R.id.rvChat);

        messages = new ArrayList<>();

        adapter = new ChatAdapter(messages);

        rvChat.setLayoutManager(
                new LinearLayoutManager(this)
        );

        rvChat.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {

            String message =
                    edtMessage.getText()
                            .toString()
                            .trim();

            if (message.isEmpty()) {
                return;
            }

            // Hiển thị tin nhắn user

            messages.add(
                    new ChatMessage(
                            message,
                            true
                    )
            );

            adapter.notifyItemInserted(
                    messages.size() - 1
            );

            rvChat.smoothScrollToPosition(
                    messages.size() - 1
            );

            edtMessage.setText("");

            callAI(message);
        });
    }

    private void callAI(String message) {

        try {

            OkHttpClient client =
                    new OkHttpClient();

            JSONObject json =
                    new JSONObject();

            json.put(
                    "message",
                    message
            );

            RequestBody body =
                    RequestBody.create(
                            json.toString(),
                            MediaType.parse(
                                    "application/json"
                            )
                    );

            Request request =
                    new Request.Builder()
                            .url(API_URL)
                            .post(body)
                            .build();

            client.newCall(request)
                    .enqueue(new Callback() {

                        @Override
                        public void onFailure(
                                Call call,
                                IOException e
                        ) {

                            runOnUiThread(() -> {

                                messages.add(
                                        new ChatMessage(
                                                "Lỗi: " + e.getMessage(),
                                                false
                                        )
                                );

                                adapter.notifyItemInserted(
                                        messages.size() - 1
                                );

                                rvChat.smoothScrollToPosition(
                                        messages.size() - 1
                                );
                            });
                        }

                        @Override
                        public void onResponse(
                                Call call,
                                Response response
                        ) throws IOException {

                            String result =
                                    response.body()
                                            .string();

                            try {

                                JSONObject obj =
                                        new JSONObject(result);

                                String answer =
                                        obj.getString(
                                                "answer"
                                        );

                                runOnUiThread(() -> {

                                    messages.add(
                                            new ChatMessage(
                                                    answer,
                                                    false
                                            )
                                    );

                                    adapter.notifyItemInserted(
                                            messages.size() - 1
                                    );

                                    rvChat.smoothScrollToPosition(
                                            messages.size() - 1
                                    );

                                });

                            } catch (Exception e) {

                                runOnUiThread(() -> {

                                    messages.add(
                                            new ChatMessage(
                                                    result,
                                                    false
                                            )
                                    );

                                    adapter.notifyItemInserted(
                                            messages.size() - 1
                                    );

                                    rvChat.smoothScrollToPosition(
                                            messages.size() - 1
                                    );

                                });

                            }
                        }
                    });

        } catch (Exception e) {

            messages.add(
                    new ChatMessage(
                            e.getMessage(),
                            false
                    )
            );

            adapter.notifyItemInserted(
                    messages.size() - 1
            );
        }
    }
}