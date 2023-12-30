package com.github.johnnysc.practicetdd

interface FeatureChain {

    interface Check : FeatureChain {

        fun canHandle(message: String): Boolean

    }

    interface Handle : FeatureChain {

        suspend fun handle(message: String): MessageUI

    }

    interface CheckAndHandle : Handle, Check

    object Empty : Handle {
        override suspend fun handle(message: String): MessageUI {
            return MessageUI.Empty
        }
    }
}