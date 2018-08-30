package com.dan.happy

fun main(args: Array<String>) {
    WebServer(MatchService()).create(9090).start()
}
