package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityDirectlyBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Use fragment directly.
 *     version: 1.0.0
 * </pre>
 */
class DirectlyActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Use fragment directly"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDirectlyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = TITLE

        supportFragmentManager.commitNow {
            add(R.id.fragmentContainerView, ChildFragment.newInstance("Directly"))
        }
    }
}