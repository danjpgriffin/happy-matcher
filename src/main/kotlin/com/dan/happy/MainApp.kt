package com.dan.happy

fun main(args: Array<String>) {
    WebServer(SqlBackedMatchService(), DemoUserService()).create(9090).start()
}
