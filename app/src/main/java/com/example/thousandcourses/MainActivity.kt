package com.example.thousandcourses

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.thousandcourses.databinding.ActivityMainBinding
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        val bottomNav = binding.bottomNav

        val activeIndicator = MaterialShapeDrawable().apply {
            setTint(Color.parseColor("#32333A"))
            shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
                .setAllCornerSizes(16f)
                .build()
            alpha = (0.25 * 255).toInt() // прозрачность
        }

        bottomNav.background = activeIndicator
    }
}