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
 *     time   : 2021/02/28
 *     desc   : Use fragment by show and hide test.
 *     version: 1.0.0
 * </pre>
 */
@RunWith(AndroidJUnit4::class)
class ContainerTest {
    @get:Rule
    val rule = ActivityScenarioRule(ContainerTestActivity::class.java)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    private lateinit var scenario: ActivityScenario<ContainerTestActivity>

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
        initFragment(false)
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
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
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
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(0) }
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
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(0) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
        }

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment1VisibleInNewWay() {
        initFragment(true)
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
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
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
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(0) }
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
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(0) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
        }
    }

    private fun initFragment(isNewWay: Boolean) {
        instrumentation.runOnMainSync {
            scenario.onActivity {
                if (isNewWay) {
                    it.initFragmentInNewWay(fragment1, fragment2)
                } else {
                    it.initFragmentInOldWay(fragment1, fragment2)
                }
            }
        }
        instrumentation.waitForIdleSync()
    }
}