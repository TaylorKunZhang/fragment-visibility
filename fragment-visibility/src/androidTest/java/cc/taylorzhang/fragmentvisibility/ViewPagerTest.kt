package cc.taylorzhang.fragmentvisibility

import androidx.fragment.app.FragmentPagerAdapter
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/28
 *     desc   : Use fragment in ViewPager test.
 *     version: 1.0.0
 * </pre>
 */
class ViewPagerTest {

    @get:Rule
    val rule = ActivityScenarioRule(ViewPagerTestActivity::class.java)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    private lateinit var scenario: ActivityScenario<ViewPagerTestActivity>

    private lateinit var fragment1: VisibilityFragment

    private lateinit var fragment2: VisibilityFragment

    @Before
    fun setup() {
        scenario = rule.scenario
        fragment1 = spyk()
        fragment2 = spyk()
    }

    @Test
    fun testFragment1VisibleInOldWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        verifyOrder {
            fragment1.onVisible()
            fragment1.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment2.onVisible()
            fragment2.onVisibleFirst()
        }
    }

    @Test
    fun testFragment2VisibleInOldWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
        }
    }

    @Test
    fun testFragment1VisibleAgainInOldWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(0, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment2.onInvisible()
            fragment1.onVisible()
            fragment1.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment2VisibleAgainInOldWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(0, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment1VisibleInNewWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        verifyOrder {
            fragment1.onVisible()
            fragment1.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment2.onVisible()
            fragment2.onVisibleFirst()
        }
    }

    @Test
    fun testFragment2VisibleInNewWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
        }
    }

    @Test
    fun testFragment1VisibleAgainInNewWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(0, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment2.onInvisible()
            fragment1.onVisible()
            fragment1.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment2VisibleAgainInNewWay() {
        initFragment(FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(0, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
        }
    }

    private fun initFragment(behavior: Int) {
        instrumentation.runOnMainSync {
            scenario.onActivity { it.initFragment(behavior, fragment1, fragment2) }
        }
        instrumentation.waitForIdleSync()
    }
}