package cc.taylorzhang.fragmentvisibility

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commitNow

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/27
 *     desc   : Use fragment directly test activity.
 *     version: 1.0.0
 * </pre>
 */
class DirectlyTestActivity : FragmentActivity() {

    companion object {
        const val VIEW_ID = Int.MAX_VALUE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentContainerView = FragmentContainerView(this).apply { id = VIEW_ID }
        setContentView(fragmentContainerView)
    }

    fun initFragment(fragment: VisibilityFragment) {
        supportFragmentManager.commitNow { add(VIEW_ID, fragment) }
    }
}