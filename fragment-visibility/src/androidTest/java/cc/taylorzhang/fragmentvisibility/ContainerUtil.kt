package cc.taylorzhang.fragmentvisibility

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/28
 *     desc   : Container util.
 *     version: 1.0.0
 * </pre>
 */
object ContainerUtil {

    fun initFragmentInOldWay(fm: FragmentManager, vararg fragments: VisibilityFragment) {
        fm.commitNow {
            fragments.indices.forEach {
                val fragment = fragments[it]
                add(ContainerTestActivity.VIEW_ID, fragment)
                if (it != 0) {
                    hide(fragment)
                }
            }
        }
    }

    fun initFragmentInNewWay(fm: FragmentManager, vararg fragments: VisibilityFragment) {
        fm.commitNow {
            fragments.indices.forEach {
                val fragment = fragments[it]
                add(ContainerTestActivity.VIEW_ID, fragment)
                if (it == 0) {
                    setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                } else {
                    hide(fragment)
                    setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                }
            }
        }
    }

    fun showFragmentByPositionInOldWay(
        fm: FragmentManager,
        fragmentList: ArrayList<VisibilityFragment>,
        position: Int
    ) {
        val targetFragment = fragmentList[position]
        if (!targetFragment.isHidden) {
            return
        }

        fm.commitNow {
            fragmentList.forEach { fragment ->
                if (!fragment.isHidden) {
                    hide(fragment)
                }
            }
            show(targetFragment)
        }
    }

    fun showFragmentByPositionInNewWay(
        fm: FragmentManager,
        fragmentList: ArrayList<VisibilityFragment>,
        position: Int
    ) {
        val targetFragment = fragmentList[position]
        if (targetFragment.isResumed) {
            return
        }

        fm.commitNow {
            fragmentList.forEach { fragment ->
                if (fragment.isResumed) {
                    hide(fragment)
                    setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                }
            }
            show(targetFragment)
            setMaxLifecycle(targetFragment, Lifecycle.State.RESUMED)
        }
    }
}