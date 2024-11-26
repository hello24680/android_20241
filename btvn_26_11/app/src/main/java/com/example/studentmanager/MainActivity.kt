package com.example.studentmanager


import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private lateinit var listView: ListView
    private val studentList = mutableListOf(
        StudentModel("Nas", "SV01"),
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )
    // Lưu danh sách Họ tên, MSSV
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        listView = findViewById(R.id.ListView)

        // Adapter hiển thị danh sách sinh viên
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList.map { "${it.studentName} - ${it.studentId}" })
        listView.adapter = adapter

        // Đăng ký context menu
        registerForContextMenu(listView)
    }

    // Tạo OptionMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Xử lý OptionMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                // Mở Activity thêm sinh viên
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivityForResult(intent, REQUEST_ADD)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Tạo ContextMenu
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    // Xử lý ContextMenu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.Edit -> {
                // Mở Activity chỉnh sửa
                val intent = Intent(this, AddStudentActivity::class.java)
                intent.putExtra("name", studentList[info.position].studentName)
                intent.putExtra("mssv", studentList[info.position].studentId)
                intent.putExtra("position", info.position)
                startActivityForResult(intent, REQUEST_EDIT)
            }
            R.id.Remove -> {
                // Xóa sinh viên
                studentList.removeAt(info.position)
                updateList()
            }
        }
        return super.onContextItemSelected(item)
    }

    // Nhận kết quả từ Activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val name = data.getStringExtra("name") ?: return
            val mssv = data.getStringExtra("mssv") ?: return

            when (requestCode) {
                REQUEST_ADD -> {
                    studentList.add(StudentModel(name, mssv))
                }
                REQUEST_EDIT -> {
                    val position = data.getIntExtra("position", -1)
                    if (position >= 0) {
                        studentList[position] = StudentModel(name, mssv)
                    }
                }
            }
            updateList()
        }
    }

    private fun updateList() {
        adapter.clear()
        adapter.addAll(studentList.map { "${it.studentName} - ${it.studentId}" })
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val REQUEST_ADD = 1
        const val REQUEST_EDIT = 2
    }


}
