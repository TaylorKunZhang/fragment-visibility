package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import cc.taylorzhang.fragmentvisibility.sample.databinding.FragmentChildBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Child Fragment.
 *     version: 1.0.0
 * </pre>
 */
class ChildFragment : LogFragment(R.layout.fragment_child) {
    companion object {
        private const val PARAM_TEXT = "text"

        fun newInstance(text: String): ChildFragment {
            val args = Bundle()
            args.putString(PARAM_TEXT, text)

            val fragment = ChildFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val mTag: String get() = "CFragment"

    override val mFragmentName: String get() = mText

    private val mText by lazy { requireArguments().getString(PARAM_TEXT) ?: "" }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = FragmentChildBinding.bind(requireView())
        binding.tvText.text = mText
    }
}