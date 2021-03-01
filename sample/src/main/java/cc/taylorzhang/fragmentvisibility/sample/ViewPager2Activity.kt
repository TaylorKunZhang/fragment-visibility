package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityViewPager2Binding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Use fragment in ViewPager2.
 *     version: 1.0.0
 * </pre>
 */
class ViewPager2Activity : AppCompatActivity() {

    companion object {
        const val TITLE = "Use fragment in ViewPager2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = TITLE

        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return ChildFragment.newInstance("Fragment-$position")
            }
        }
    }
}