package cc.taylorzhang.fragmentvisibility

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/28
 *     desc   : Use fragment in ViewPager test Activity.
 *     version: 1.0.0
 * </pre>
 */
class ViewPagerTestActivity : FragmentActivity() {

    companion object {
        const val VIEW_ID = Int.MAX_VALUE
    }

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager = ViewPager(this).apply { id = VIEW_ID }
        setContentView(viewPager)
    }

    fun initFragment(behavior: Int, vararg fragments: VisibilityFragment) {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager, behavior) {

            override fun getCount(): Int = fragments.size

            override fun getItem(position: Int): Fragment = fragments[position]
        }
    }
}