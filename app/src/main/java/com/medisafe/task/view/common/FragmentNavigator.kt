package com.medisafe.task.view.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction

open class FragmentNavigator(val containerId: Int, val fragmentManager: FragmentManager) {

    /**
     * presents fragment without arguments
     *
     * example:
     *
     * navigator.show<DummyFragment>()
     *
     */

    inline fun <reified T : Fragment> show() {
        fragmentManager.show<T>()
    }

    /**
     * presents fragment with arguments
     *
     * example:
     *
     * navigator.show { DummyFragment.newInstance(params) }
     *
     */

    inline fun <reified T : Fragment> show(createFragment: () -> T) {
        fragmentManager.showWithArgs { createFragment() }
    }

    /**
     * clears stack and executes transaction without adding to backStack
     *
     * example:
     *
     * navigator.showOnly<DummyFragment>()
     *
     */

    inline fun <reified T : Fragment> showOnly() {
        fragmentManager.showOnly<T>()
    }


    inline fun <reified T : Fragment> showOnly(createFragment: () -> T) {
        fragmentManager.showOnly { createFragment() }
    }


    inline fun <reified T : Fragment> FragmentManager.show() {
        takeIf { it.notDisplayed<T>() }?.transaction {
            replace(containerId, T::class.java.newInstance())
            addToBackStack(null)
        }
    }


    inline fun <reified T : Fragment> FragmentManager.showWithArgs(createFragment: () -> T) {
        takeIf { it.notDisplayed<T>() }?.transaction(allowStateLoss = true) {
            replace(containerId, createFragment.invoke())
            addToBackStack(null)
        }
    }


    inline fun <reified T : Fragment> FragmentManager.showOnly() {
        takeIf { it.notDisplayed<T>() }?.clearStack()?.transaction {
            replace(containerId, T::class.java.newInstance())
        }
    }

    inline fun <reified T : Fragment> FragmentManager.showOnly(createFragment: () -> T) {
        clearStack()?.transaction {
            replace(containerId, createFragment.invoke())
        }
    }

    fun FragmentManager.extractByTag(tag: String): Fragment? {
        return findFragmentByTag(tag)?.also { clearStack() }
    }

    inline fun <reified T : Fragment> FragmentManager.notDisplayed(): Boolean {
        val currentFragment: Fragment? = findFragmentById(containerId)
        return with(currentFragment) { this?.javaClass != T::class.java }
    }

    fun FragmentManager.clearStack(): FragmentManager {
        for (i in 0 until this.backStackEntryCount) {
            this.popBackStack()
        }
        return this
    }

}