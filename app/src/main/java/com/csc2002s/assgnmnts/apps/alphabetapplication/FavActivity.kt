package com.csc2002s.assgnmnts.apps.alphabetapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.FragmentFavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView<FragmentFavBinding>(this, R.layout.activity_home)
    }
}
