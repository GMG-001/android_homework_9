package gio.megrela.davaleba

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var api: API

    private lateinit var btnBack: MaterialButton
    private lateinit var ivAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        api = API.create()

        btnBack = findViewById(R.id.btnBack)
        ivAvatar = findViewById(R.id.ivAvatar)
        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)

        btnBack.setOnClickListener { finish() }

        val userId = intent.getIntExtra("user_id", -1)

        api.getUserById(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val data = response.body()?.data
                if (data != null) {
                    Glide.with(applicationContext)
                        .load(data.avatar)
                        .into(ivAvatar)

                    tvName.text = "${data.first_name} ${data.last_name}"
                    tvEmail.text = data.email
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@UserDetailsActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}