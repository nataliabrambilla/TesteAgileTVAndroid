package com.example.testeagiletv_android

private const val BASE_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"

object PokemonUtil {

    fun getPokemonImageUrl(url: String): String? {
        val pattern = Regex("""/(\d+)/""")
        val matchResult = pattern.find(url)
        val id =  matchResult?.groupValues?.get(1)?.toIntOrNull() ?: return null
        return BASE_IMAGE_URL.format(id)
    }
}
