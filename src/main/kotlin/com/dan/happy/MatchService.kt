package com.dan.happy

interface MatchService {
    fun findMatches(restrictions: Restrictions = must): List<Match>
}

val must = Restrictions()

data class Restrictions(val hasPhoto: Boolean? = null) {
    fun havePhoto() = copy(hasPhoto = true)
    fun notHavePhoto() = copy(hasPhoto = false)
}