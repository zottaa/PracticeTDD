package com.github.johnnysc.practicetdd

interface FeatureChain {

    interface Check : FeatureChain {
        fun canHandle(message: String): Boolean
    }

    interface Handle : FeatureChain {
        suspend fun handle(message: String): MessageUI
    }

    interface CheckAndHandle : Check, Handle

    object Empty : FeatureChain.Handle {
        override suspend fun handle(message: String): MessageUI =
            MessageUI.Empty()
    }

}