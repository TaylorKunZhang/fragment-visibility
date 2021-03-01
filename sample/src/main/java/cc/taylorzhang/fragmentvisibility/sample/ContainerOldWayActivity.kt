package cc.taylorzhang.fragmentvisibility.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.taylorzhang.fragmentvisibility.VisibilityFragment
import cc.taylorzhang.fragmentvisibility.sample.databinding.ActivityContainerBinding

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/24
 *     desc   : Use fragment by show and hide in old way.
 *     version: 1.0.0
 * </pre>
 */
class ContainerOldWayActivity : AppCompatActivity() {

    companion object {
        const val TITLE = "Use fragment by show and hide in old way"
    }

    private val mFragmentList = ArrayList<VisibilityFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = TITLE

        mFragmentList.add(ChildFragment.newInstance("Fragment-1"))
        mFragmentList.add(ChildFragment.newInstance("Fragment-2"))
        ContainerUtil.initFragmentInOldWay(
            supportFragmentManager, R.id.fragmentContainerView, mFragmentList
        )

        binding.btn1.setOnClickListener {
            ContainerUtil.showFragmentByPositionInOldWay(supportFragmentManager, mFragmentList, 0)
        }
        binding.btn2.setOnClickListener {
            ContainerUtil.showFragmentByPositionInOldWay(supportFragmentManager, mFragmentList, 1)
        }
    }
}