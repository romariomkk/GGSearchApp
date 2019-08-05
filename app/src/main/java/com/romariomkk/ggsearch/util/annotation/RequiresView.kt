package com.romariomkk.ggsearch.util.annotation

import androidx.annotation.LayoutRes

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(value = AnnotationRetention.RUNTIME)
annotation class RequiresView(@LayoutRes val value: Int)
