package cc.taylorzhang.fragmentvisibility

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/27
 *     desc   : Use fragment in ViewPager2 test.
 *     version: 1.0.0
 * </pre>
 */
@RunWith(AndroidJUnit4::class)
class ViewPager2Test {

    @get:Rule
    val rule = ActivityScenarioRule(ViewPager2TestActivity::class.java)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    private lateinit var scenario: ActivityScenario<ViewPager2TestActivity>

    private lateinit var fragment1: VisibilityFragment

    private lateinit var fragment2: VisibilityFragment

    @Before
    fun setup() {
        scenario = rule.scenario
        fragment1 = spyk()
        fragment2 = spyk()
        instrumentation.runOnMainSync {
            scenario.onActivity { it.initFragment(fragment1, fragment2) }
        }
        instrumentation.waitForIdleSync()
    }

    @Test
    fun testFragment1Visible() {
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
    fun testFragment2Visible() {
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
        }
    }

    @Test
    fun testFragment1VisibleAgain() {
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(0, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment2.onInvisible()
            fragment1.onVisible()
            fragment1.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment2VisibleAgain() {
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(1, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(0, false) }
        }
        instrumentation.runOnMainSync {
            scenario.onActivity { it.viewPager2.setCurrentItem(1, false) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
        }
    }
}