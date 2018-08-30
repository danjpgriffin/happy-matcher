package com.dan.happy

interface UserService {
    fun getCityForCurrentUser(): City?
}

class DemoUserService: UserService {
    override fun getCityForCurrentUser(): City? {
        return City("Birmingham", 52.489471, -1.898575)
    }
}