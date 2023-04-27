package com.core.common.utils

import com.core.common.utils.Constants.CHAR_DETAIL_ARG

sealed class Destinations(val route: String) {
    object CharacterListRoute : Destinations(route = "character_list_route")
    object CharacterDetailRoute : Destinations(
        route = "character_detail_route?$CHAR_DETAIL_ARG={$CHAR_DETAIL_ARG}"
    ) {
        fun passCharId(charId: Int) = "character_detail_route?$CHAR_DETAIL_ARG=$charId"
    }
}