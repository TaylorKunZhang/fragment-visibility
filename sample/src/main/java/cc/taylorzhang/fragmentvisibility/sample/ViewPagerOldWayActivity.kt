package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityViewPagerBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Use fragment in ViewPager in old way.
 *     version: 1.0.0
 * </pre>
 */
class ViewPagerOldWayActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Use fragment in ViewPager in old way"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = TITLE

        binding.viewPager.adapter = object : FragmentPagerAdapter(
            supportFragmentManager, BEHAVIOR_SET_USER_VISIBLE_HINT
        ) {

            override fun getCount(): Int = 3

            override fun getItem(position: Int): Fragment {
                return ChildFragment.newInstance("Fragment-$position")
            }
        }
    }
}