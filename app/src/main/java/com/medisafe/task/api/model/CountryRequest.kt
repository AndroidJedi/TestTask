package com.medisafe.task.api.model


interface CountryRequest {

    companion object {
        private const val Name        = "name"
        private const val NativeName  = "nativeName"
        private const val Flag        = "flag"
        private const val Capital     = "capital"
        private const val Population  = "population"
        private const val Region      = "region"
        private const val Currencies  = "currencies"

        const val ALL     = "$Name;$NativeName;$Flag;$Population"
        const val DETAILS = "$Name;$NativeName;$Flag;$Population;$Capital;$Region;$Currencies"

    }

}
