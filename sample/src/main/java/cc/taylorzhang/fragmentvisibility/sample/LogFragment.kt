package cc.taylorzhang.fragmentvisibility.sample

import android.util.Log
import androidx.annotation.LayoutRes
import cc.taylorzhang.fragmentvisibility.VisibilityFragment

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/26
 *     desc   : Log Fragment
 *     version: 1.0.0
 * </pre>
 */
abstract class LogFragment(@LayoutRes contentLayoutId: Int) : VisibilityFragment(contentLayoutId) {

    protected abstract val mTag: String

    protected abstract val mFragmentName: String

    override fun onVisible() {
        super.onVisible()

        Log.i(mTag, "$mFragmentName onVisible")
    }

    override fun onInvisible() {
        super.onInvisible()

        Log.i(mTag, "$mFragmentName onInvisible")
    }

    override fun onVisibleFirst() {
        super.onVisibleFirst()

        Log.i(mTag, "$mFragmentName onVisibleFirst")
    }

    override fun onVisibleExceptFirst() {
        super.onVisibleExceptFirst()

        Log.i(mTag, "$mFragmentName onVisibleExceptFirst")
    }

    override fun onResume() {
        Log.d(mTag, "$mFragmentName onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(mTag, "$mFragmentName onPause")
        super.onPause()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Log.d(mTag, "$mFragmentName onHiddenChanged: $hidden")
        super.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.d(mTag, "$mFragmentName setUserVisibleHint: $isVisibleToUser")
        super.setUserVisibleHint(isVisibleToUser)
    }
}