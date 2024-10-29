package com.example.gmail_demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gmail_demo.Email

class EmailAdapter(context: Context, private val emailList: List<Email>) : ArrayAdapter<Email>(context, 0, emailList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.email_item, parent, false)

        val email = emailList[position]

        //các thành phần trong layout
        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)
        val senderTextView: TextView = view.findViewById(R.id.senderTextView)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
        val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val starImageView: ImageView = view.findViewById(R.id.starImageView)

        //Thiết lập dữ liệu cho các thành phần
        senderTextView.text = email.sender
        timeTextView.text = email.time
        subjectTextView.text = email.subject
        messageTextView.text = email.message

        //Thiết lập ảnh đại diện
        avatarImageView.setImageResource(R.drawable.img)

        //Xử lý sự kiện khi nhấn vào ngôi sao
        starImageView.setOnClickListener {
            //Cập nhật giao diện ngôi sao
            starImageView.setImageResource(R.drawable.ic_star_yellow)
        }

        return view
    }
}
