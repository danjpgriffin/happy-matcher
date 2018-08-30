package com.dan.happy

interface MatchService {
    fun findMatches(restrictions: Restrictions = must): List<Match>
}

val must = Restrictions()

data class Restrictions(
    val hasPhoto: Boolean? = null,
    val inContact: Boolean? = null
) {
    fun havePhoto() = copy(hasPhoto = true)
    fun notHavePhoto() = copy(hasPhoto = false)
    fun beInContact() = copy(inContact = true)
    fun notBeInContact() = copy(inContact = false)
}