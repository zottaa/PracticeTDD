package com.github.johnnysc.practicetdd

abstract class ViewModelChain(
    private val feature: FeatureChain.CheckAndHandle
) : FeatureChain.CheckAndHandle {

    private var nextFeatureChain: FeatureChain.Handle = FeatureChain.Empty

    fun nextFeatureChain(featureChain: FeatureChain.Handle) {
        nextFeatureChain = featureChain
    }

    override fun canHandle(message: String): Boolean = feature.canHandle(message)

    override suspend fun handle(message: String): MessageUI {
        return if (feature.canHandle(message)) {
            feature.handle(message)
        } else {
            nextFeatureChain.handle(message)
        }
    }
}