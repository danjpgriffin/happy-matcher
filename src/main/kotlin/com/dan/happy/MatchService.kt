package com.dan.happy

interface MatchService {
    fun findMatches(restrictions: Restrictions = must, originCity: City? = null): List<Match>
}

val must = Restrictions()

data class Restrictions(
    val hasPhoto: Boolean? = null,
    val inContact: Boolean? = null,
    val distance: Int? = null
) {
    fun havePhoto() = copy(hasPhoto = true)
    fun notHavePhoto() = copy(hasPhoto = false)
    fun beInContact() = copy(inContact = true)
    fun notBeInContact() = copy(inContact = false)
    fun within(km: Int) = copy(distance = km)
}