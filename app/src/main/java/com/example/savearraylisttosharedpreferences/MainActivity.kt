package com.example.savearraylisttosharedpreferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private var courseNameEdt: EditText? = null
    private var courseDescEdt: EditText? = null
    private var addBtn: Button? = null
    private var saveBtn: Button? = null
    private var courseRV: RecyclerView? = null
    private var adapter: CourseAdapter? = null
    private var courseModalArrayList: ArrayList<CourseModal>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        courseNameEdt = findViewById(R.id.idEdtCourseName)
        courseDescEdt = findViewById(R.id.idEdtCourseDescription)
        addBtn = findViewById(R.id.idBtnAdd)
        saveBtn = findViewById(R.id.idBtnSave)
        courseRV = findViewById(R.id.idRVCourses)

        loadData()
        buildRecyclerView()


        addBtn!!.setOnClickListener {
            courseModalArrayList!!.add(
                CourseModal(courseNameEdt!!.text.toString(),courseDescEdt!!.text.toString()))

            adapter!!.notifyItemInserted(courseModalArrayList!!.size)
        }

        saveBtn!!.setOnClickListener {
            saveData()
        }
    }

    private fun buildRecyclerView() {

        adapter = CourseAdapter(courseModalArrayList!!, this@MainActivity)

        val manager = LinearLayoutManager(this)
        courseRV!!.setHasFixedSize(true)
        courseRV!!.layoutManager = manager
        courseRV!!.adapter = adapter
    }

    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("courses", null)
        val type = object : TypeToken<ArrayList<CourseModal?>?>() {}.type
        courseModalArrayList = gson.fromJson(json, type)


        if (courseModalArrayList == null) {
            courseModalArrayList = ArrayList()
        }
    }

    private fun saveData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(courseModalArrayList)

        editor.putString("courses", json)
        editor.apply()
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }
}
