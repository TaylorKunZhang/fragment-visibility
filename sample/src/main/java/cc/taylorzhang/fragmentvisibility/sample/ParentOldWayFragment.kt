package cc.taylorzhang.fragmentvisibility.sample

import android.annotation.SuppressLint
import android.os.Bundle
import cc.taylorzhang.fragmentvisibility.VisibilityFragment
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityContainerBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/26
 *     desc   : Parent fragment in old way.
 *     version: 1.0.0
 * </pre>
 */
class ParentOldWayFragment : LogFragment(R.layout.activity_container) {
    companion object {
        private const val PARAM_POSITION = "position"

        fun newInstance(position: Int): ParentOldWayFragment {
            val args = Bundle()
            args.putInt(PARAM_POSITION, position)

            val fragment = ParentOldWayFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val mTag: String get() = "PFragment"

    override val mFragmentName: String get() = "Fragment-${mPosition + 1}"

    private val mPosition by lazy { requireArguments().getInt(PARAM_POSITION) }

    private val mFragmentList = ArrayList<VisibilityFragment>()

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val binding = ActivityContainerBinding.bind(requireView())
        binding.btn1.text = "Fragment-${mPosition + 1}-1"
        binding.btn2.text = "Fragment-${mPosition + 1}-2"

        mFragmentList.add(ChildFragment.newInstance("Fragment-${mPosition + 1}-1"))
        mFragmentList.add(ChildFragment.newInstance("Fragment-${mPosition + 1}-2"))
        ContainerUtil.initFragmentInOldWay(
            childFragmentManager, R.id.fragmentContainerView, mFragmentList
        )

        binding.btn1.setOnClickListener {
            ContainerUtil.showFragmentByPositionInOldWay(childFragmentManager, mFragmentList, 0)
        }
        binding.btn2.setOnClickListener {
            ContainerUtil.showFragmentByPositionInOldWay(childFragmentManager, mFragmentList, 1)
        }
    }
}