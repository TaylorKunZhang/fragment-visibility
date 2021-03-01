package cc.taylorzhang.fragmentvisibility

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Visibility fragment.
 *     version: 1.0.0
 * </pre>
 */
open class VisibilityFragment(
    @LayoutRes contentLayoutId: Int = 0
) : Fragment(contentLayoutId), IFragmentVisibility {

    // True if the fragment is visible to the user.
    private var mIsFragmentVisible = false

    // True if the fragment is visible to the user for the first time.
    private var mIsFragmentVisibleFirst = true

    override fun onResume() {
        super.onResume()

        determineFragmentVisible()
    }

    override fun onPause() {
        super.onPause()

        determineFragmentInvisible()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (hidden) {
            determineFragmentInvisible()
        } else {
            determineFragmentVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            determineFragmentVisible()
        } else {
            determineFragmentInvisible()
        }
    }

    override fun isVisibleToUser(): Boolean = mIsFragmentVisible

    private fun determineFragmentVisible() {
        val parent = parentFragment
        if (parent != null && parent is VisibilityFragment) {
            if (!parent.isVisibleToUser()) {
                // Parent Fragment is invisible, child fragment must be invisible.
                return
            }
        }

        if (isResumed && !isHidden && userVisibleHint && !mIsFragmentVisible) {
            mIsFragmentVisible = true
            onVisible()
            if (mIsFragmentVisibleFirst) {
                mIsFragmentVisibleFirst = false
                onVisibleFirst()
            } else {
                onVisibleExceptFirst()
            }
            determineChildFragmentVisible()
        }
    }

    private fun determineFragmentInvisible() {
        if (mIsFragmentVisible) {
            mIsFragmentVisible = false
            onInvisible()
            determineChildFragmentInvisible()
        }
    }

    private fun determineChildFragmentVisible() {
        childFragmentManager.fragments.forEach {
            if (it is VisibilityFragment) {
                it.determineFragmentVisible()
            }
        }
    }

    private fun determineChildFragmentInvisible() {
        childFragmentManager.fragments.forEach {
            if (it is VisibilityFragment) {
                it.determineFragmentInvisible()
            }
        }
    }
}