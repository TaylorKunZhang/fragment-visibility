package cc.taylorzhang.fragmentvisibility

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
 *     desc   : Use nested fragment test.
 *     version: 1.0.0
 * </pre>
 */
class NestedTest {

    @get:Rule
    val rule = ActivityScenarioRule(NestedTestActivity::class.java)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    private lateinit var scenario: ActivityScenario<NestedTestActivity>

    private lateinit var fragment1: NestedTestActivity.ParentFragment

    private lateinit var fragment11: VisibilityFragment

    private lateinit var fragment12: VisibilityFragment

    private lateinit var fragment2: NestedTestActivity.ParentFragment

    private lateinit var fragment21: VisibilityFragment

    private lateinit var fragment22: VisibilityFragment

    @Before
    fun setup() {
        scenario = rule.scenario
        fragment1 = spyk()
        fragment11 = spyk()
        fragment12 = spyk()
        fragment2 = spyk()
        fragment21 = spyk()
        fragment22 = spyk()
    }

    @Test
    fun testFragment11VisibleInOldWay() {
        initFragment(false)
        verifyOrder {
            fragment1.onVisible()
            fragment1.onVisibleFirst()
            fragment11.onVisible()
            fragment11.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment12.onVisible()
            fragment12.onVisibleFirst()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
            fragment21.onVisible()
            fragment21.onVisibleFirst()
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment12VisibleInOldWay() {
        initFragment(false)
        instrumentation.runOnMainSync {
            fragment1.showFragmentByPositionInOldWay(1)
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment11.onInvisible()
            fragment12.onVisible()
            fragment12.onVisibleFirst()
        }
    }

    @Test
    fun testFragment21VisibleInOldWay() {
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment11.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
            fragment21.onVisible()
            fragment21.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment22VisibleInOldWay() {
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
            fragment2.showFragmentByPositionInOldWay(1)
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment21.onInvisible()
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment11VisibleAgainInOldWay() {
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(0) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment2.onInvisible()
            fragment21.onInvisible()
            fragment1.onVisible()
            fragment1.onVisibleExceptFirst()
            fragment11.onVisible()
            fragment11.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment21VisibleAgainInOldWay() {
        initFragment(false)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(0) }
            scenario.onActivity { it.showFragmentByPositionInOldWay(1) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment11.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
            fragment21.onVisible()
            fragment21.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment11VisibleInNewWay() {
        initFragment(true)
        verifyOrder {
            fragment1.onVisible()
            fragment1.onVisibleFirst()
            fragment11.onVisible()
            fragment11.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment12.onVisible()
            fragment12.onVisibleFirst()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
            fragment21.onVisible()
            fragment21.onVisibleFirst()
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment12VisibleInNewWay() {
        initFragment(true)
        instrumentation.runOnMainSync {
            fragment1.showFragmentByPositionInNewWay(1)
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment11.onInvisible()
            fragment12.onVisible()
            fragment12.onVisibleFirst()
        }
    }

    @Test
    fun testFragment21VisibleInNewWay() {
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment11.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleFirst()
            fragment21.onVisible()
            fragment21.onVisibleFirst()
        }
        verify(exactly = 0) {
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment22VisibleInNewWay() {
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
            fragment2.showFragmentByPositionInNewWay(1)
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment21.onInvisible()
            fragment22.onVisible()
            fragment22.onVisibleFirst()
        }
    }

    @Test
    fun testFragment11VisibleAgainInNewWay() {
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(0) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment2.onInvisible()
            fragment21.onInvisible()
            fragment1.onVisible()
            fragment1.onVisibleExceptFirst()
            fragment11.onVisible()
            fragment11.onVisibleExceptFirst()
        }
    }

    @Test
    fun testFragment21VisibleAgainInNewWay() {
        initFragment(true)
        instrumentation.runOnMainSync {
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(0) }
            scenario.onActivity { it.showFragmentByPositionInNewWay(1) }
        }
        instrumentation.waitForIdleSync()

        verifyOrder {
            fragment1.onInvisible()
            fragment11.onInvisible()
            fragment2.onVisible()
            fragment2.onVisibleExceptFirst()
            fragment21.onVisible()
            fragment21.onVisibleExceptFirst()
        }
    }

    private fun initFragment(isNewWay: Boolean) {
        instrumentation.runOnMainSync {
            scenario.onActivity {
                if (isNewWay) {
                    it.initFragmentInNewWay(fragment1, fragment2)
                    fragment1.initFragmentInNewWay(fragment11, fragment12)
                    fragment2.initFragmentInNewWay(fragment21, fragment22)
                } else {
                    it.initFragmentInOldWay(fragment1, fragment2)
                    fragment1.initFragmentInOldWay(fragment11, fragment12)
                    fragment2.initFragmentInOldWay(fragment21, fragment22)
                }
            }
        }
        instrumentation.waitForIdleSync()
    }
}