package com.example.gmail_demo

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.gmail_demo.EmailAdapter
import com.example.gmail_demo.Email

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    // Tạo danh sách email mẫu
    private val emailList = listOf(
        Email("hoang1", "Chào bạn!", "Đây là một email mẫu.", "10:15 AM"),
        Email("hoang2", "Cuộc họp tuần tới", "Đừng quên cuộc họp vào thứ Hai.", "9:45 AM"),
        Email("hoang3", "Tin tức mới", "Có tin mới từ công ty.", "8:30 AM"),
        Email("hoang4", "Cập nhật dự án", "Dự án đang tiến triển tốt.", "Yesterday"),
        Email("hoang5", "Lịch hẹn", "Nhắc bạn về lịch hẹn vào thứ Tư.", "Yesterday"),
        Email("hoang6", "Hóa đơn", "Hóa đơn của bạn đã được gửi.", "2 days ago"),
        Email("hoang7", "Lời mời", "Mời bạn tham dự tiệc vào thứ Bảy.", "2 days ago"),
        Email("Alice Smith", "Chào bạn!", "Đây là một email mẫu.", "10:15 AM"),
        Email("Bob Johnson", "Cuộc họp tuần tới", "Đừng quên cuộc họp vào thứ Hai.", "9:45 AM"),
        Email("Carol Williams", "Tin tức mới", "Có tin mới từ công ty.", "8:30 AM"),
        Email("Dave Brown", "Cập nhật dự án", "Dự án đang tiến triển tốt.", "Yesterday"),
        Email("Eve Davis", "Lịch hẹn", "Nhắc bạn về lịch hẹn vào thứ Tư.", "Yesterday"),
        Email("Frank Wilson", "Hóa đơn", "Hóa đơn của bạn đã được gửi.", "2 days ago"),
        Email("Grace Lee", "Lời mời", "Mời bạn tham dự tiệc vào thứ Bảy.", "2 days ago")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        // Tạo và thiết lập adapter cho ListView
        val emailAdapter = EmailAdapter(this, emailList)
        listView.adapter = emailAdapter
    }
}
