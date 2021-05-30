[English](README.md) | 简体中文

# FragmentVisibility

适用于 Android 的统一 Fragment 可见性库。

[![Maven Central](https://img.shields.io/maven-central/v/cc.taylorzhang/fragment-visibility.svg?style=flat)](https://search.maven.org/artifact/cc.taylorzhang/fragment-visibility)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![License](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg?style=flat)](LICENSE)

支持的 Fragment 使用场景：

- 在 xml 文件中声明 Fragment，或者在代码中通过 add 或 replace 动态载入 Fragment。
- 在 FragmentContainerView/FrameLayout 中使用Fragment，使用 show 和 hide 控制显示和隐藏 Fragment。
- 在 FragmentContainerView/FrameLayout 中使用Fragment，使用 show 和 hide 控制显示和隐藏 Fragment，同时使用 setMaxLifecycle 控制Fragment周期。
- 在 ViewPager 中使用 Fragment，Behavior 为 BEHAVIOR_SET_USER_VISIBLE_HINT 或 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT。
- 在 ViewPager2 中使用 Fragment。
- 多层嵌套Fragment。

## 下载

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'cc.taylorzhang:fragment-visibility:1.0.0'
}
```

## 使用

### 可见性回调

```kotlin
class YourFragment : VisibilityFragment() {

    override fun onVisible() {
        super.onVisible()

        // Fragment可见时调用。
    }

    override fun onInvisible() {
        super.onInvisible()

        // Fragment不可见时调用。
    }

    override fun onVisibleFirst() {
        super.onVisibleFirst()
        
        // Fragment第一次可见时调用。
        // 你可以在此处加载数据以实现懒加载。
    }

    override fun onVisibleExceptFirst() {
        super.onVisibleExceptFirst()

        // Fragment可见时（第一次除外）调用。
    }
}
```

### 可见性状态

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