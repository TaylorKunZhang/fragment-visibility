package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.taylorzhang.fragmentvisibility.VisibilityFragment
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityContainerBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/26
 *     desc   : Use nested fragment in new way.
 *     version: 1.0.0
 * </pre>
 */
class NestedNewWayActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Use nested fragment in new way"
    }

    private val mFragmentList = ArrayList<VisibilityFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = TITLE

        mFragmentList.add(ParentNewWayFragment.newInstance(0))
        mFragmentList.add(ParentNewWayFragment.newInstance(1))
        ContainerUtil.initFragmentInNewWay(
            supportFragmentManager, R.id.fragmentContainerView, mFragmentList
        )

        binding.btn1.setOnClickListener {
            ContainerUtil.showFragmentByPositionInNewWay(supportFragmentManager, mFragmentList, 0)
        }
        binding.btn2.setOnClickListener {
            ContainerUtil.showFragmentByPositionInNewWay(supportFragmentManager, mFragmentList, 1)
        }
    }
}