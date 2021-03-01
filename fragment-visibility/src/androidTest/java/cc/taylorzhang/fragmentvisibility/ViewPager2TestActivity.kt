package cc.taylorzhang.fragmentvisibility

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/27
 *     desc   : Use fragment in ViewPager2 test Activity.
 *     version: 1.0.0
 * </pre>
 */
class ViewPager2TestActivity : FragmentActivity() {

    companion object {
        const val VIEW_ID = Int.MAX_VALUE
    }

    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager2 = ViewPager2(this).apply { id = VIEW_ID }
        setContentView(viewPager2)
    }

    fun initFragment(vararg fragments: VisibilityFragment) {
        viewPager2.adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = fragments.size

            override fun createFragment(position: Int): Fragment = fragments[position]
        }
    }
}