package cc.taylorzhang.fragmentvisibility

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * <pre>
 *     author : Taylor Zhang
 *     time   : 2021/02/27
 *     desc   : Use fragment directly test.
 *     version: 1.0.0
 * </pre>
 */
@RunWith(AndroidJUnit4::class)
class DirectlyTest {

    @get:Rule
    val rule = ActivityScenarioRule(DirectlyTestActivity::class.java)

    private lateinit var scenario: ActivityScenario<DirectlyTestActivity>

    private lateinit var fragment: VisibilityFragment

    @Before
    fun setup() {
        scenario = rule.scenario
        fragment = spyk()
        scenario.onActivity { it.initFragment(fragment) }
    }

    @Test
    fun testVisible() {
        verifyOrder {
            fragment.onVisible()
            fragment.onVisibleFirst()
        }
        verify(exactly = 0) { fragment.onVisibleExceptFirst() }
        assertEquals(true, fragment.isVisibleToUser())
    }

    @Test
    fun testInvisible() {
        verify(exactly = 0) { fragment.onInvisible() }
        scenario.moveToState(Lifecycle.State.STARTED)
        verify(exactly = 1) { fragment.onInvisible() }
        assertEquals(false, fragment.isVisibleToUser())
    }

    @Test
    fun testVisibleExceptFirst() {
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.RESUMED)
        verifyOrder {
            fragment.onInvisible()
            fragment.onVisible()
            fragment.onVisibleExceptFirst()
        }
        assertEquals(true, fragment.isVisibleToUser())
    }
}