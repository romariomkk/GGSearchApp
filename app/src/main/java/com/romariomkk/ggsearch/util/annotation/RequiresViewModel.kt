package com.romariomkk.ggsearch.util.annotation

import com.romariomkk.ggsearch.view.base.AbsViewModel
import kotlin.reflect.KClass

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(value = AnnotationRetention.RUNTIME)
annotation class RequiresViewModel(val value : KClass<out AbsViewModel>)
