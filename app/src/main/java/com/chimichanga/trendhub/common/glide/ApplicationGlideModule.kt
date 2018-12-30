package com.chimichanga.trendhub.common.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Subclass of [AppGlideModule] needed for [GlideApp] to be generated
 */
@GlideModule
class ApplicationGlideModule : AppGlideModule()