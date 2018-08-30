package com.dan.happy

fun main(args: Array<String>) {
    WebServer(FileBackedMatchService("/matches.json")).create(9090).start()
}
