package cc.taylorzhang.fragmentvisibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/28
 *     desc   : Use nested fragment test Activity.
 *     version: 1.0.0
 * </pre>
 */
class NestedTestActivity : FragmentActivity() {

    companion object {
        const val VIEW_ID = Int.MAX_VALUE
    }

    private lateinit var mFragmentList: ArrayList<VisibilityFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentContainerView = FragmentContainerView(this).apply { id =
            ContainerTestActivity.VIEW_ID
        }
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

    class ParentFragment : VisibilityFragment() {

        private lateinit var mFragmentList: ArrayList<VisibilityFragment>

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            return FragmentContainerView(requireContext()).apply {
                id = VIEW_ID
            }
        }

        fun initFragmentInOldWay(vararg fragments: VisibilityFragment) {
            mFragmentList = fragments.toMutableList() as ArrayList<VisibilityFragment>
            ContainerUtil.initFragmentInOldWay(childFragmentManager, *fragments)
        }

        fun initFragmentInNewWay(vararg fragments: VisibilityFragment) {
            mFragmentList = fragments.toMutableList() as ArrayList<VisibilityFragment>
            ContainerUtil.initFragmentInNewWay(childFragmentManager, *fragments)
        }

        fun showFragmentByPositionInOldWay(position: Int) {
            ContainerUtil.showFragmentByPositionInOldWay(
                childFragmentManager, mFragmentList, position
            )
        }

        fun showFragmentByPositionInNewWay(position: Int) {
            ContainerUtil.showFragmentByPositionInNewWay(
                childFragmentManager, mFragmentList, position
            )
        }
    }
}