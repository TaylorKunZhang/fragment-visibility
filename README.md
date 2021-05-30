English | [简体中文](README_CN.md)

# FragmentVisibility

Unified fragment visibility library for Android.

[![Maven Central](https://img.shields.io/maven-central/v/cc.taylorzhang/fragment-visibility.svg?style=flat)](https://search.maven.org/artifact/cc.taylorzhang/fragment-visibility)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg?style=flat)](LICENSE)

Support Fragment usage scenarios:

- Declare the Fragment in the xml file, or dynamically load the Fragment through add or replace in the code.
- Use Fragment in FragmentContainerView/FrameLayout, Use show and hide to control the display and hide Fragment.
- Use Fragment in FragmentContainerView/FrameLayout, Use show and hide to control the display and hide Fragment, and use setMaxLifecycle to control the life cycle of fragment.
- Use Fragment in ViewPager, Behavior is BEHAVIOR_SET_USER_VISIBLE_HINT or BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT.
- Use Fragment in ViewPager2.
- Multi-level nested Fragment.

## Download

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'cc.taylorzhang:fragment-visibility:1.0.0'
}
```

## Usage

### Visibility Callback

```kotlin
class YourFragment : VisibilityFragment() {

    override fun onVisible() {
        super.onVisible()

        // Called when the fragment is visible.
    }

    override fun onInvisible() {
        super.onInvisible()

        // Called when the Fragment is not visible.
    }

    override fun onVisibleFirst() {
        super.onVisibleFirst()

        // Called when the fragment is visible for the first time.
        // You can load data here for lazy loading.
    }

    override fun onVisibleExceptFirst() {
        super.onVisibleExceptFirst()

        // Called when the fragment is visible except first time.
    }
}
```

### Visibility State

```kotlin
class YourFragment : VisibilityFragment() {

    fun yourFunction() {
        if (isVisibleToUser()) {
            // do something
        }
    }
}
```

## License

[Apache license 2.0](LICENSE) © Taylor Zhang