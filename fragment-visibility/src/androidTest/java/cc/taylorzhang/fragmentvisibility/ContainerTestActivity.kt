package cc.taylorzhang.fragmentvisibility

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/28
 *     desc   : Use fragment by show and hide test Activity.
 *     version: 1.0.0
 * </pre>
 */
class ContainerTestActivity : FragmentActivity() {

    companion object {
        const val VIEW_ID = Int.MAX_VALUE
    }

    private lateinit var mFragmentList: ArrayList<VisibilityFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentContainerView = FragmentContainerView(this).apply { id = VIEW_ID }
        setContentView(fragmentContainerView)
    }

    fun initFragmentInOldWay(vararg fragments: VisibilityFragment) {
        mFragmentList = fragments.toMutableList() as ArrayList<VisibilityFragment>
        ContainerUtil.initFragmentInOldWay(supportFragmentManager, *fragments)
    }

    fun initFragmentInNewWay(vararg fragments: VisibilityFragment) {
        mFragmentList = fragments.toMutableList() as ArrayList<VisibilityFragment>
        ContainerUtil.initFragmentInNewWay(supportFragmentManager, *fragments)
    }

    fun showFragmentByPositionInOldWay(position: Int) {
        ContainerUtil.showFragmentByPositionInOldWay(
            supportFragmentManager, mFragmentList, position
        )
    }

    fun showFragmentByPositionInNewWay(position: Int) {
        ContainerUtil.showFragmentByPositionInNewWay(
            supportFragmentManager, mFragmentList, position
        )
    }
}