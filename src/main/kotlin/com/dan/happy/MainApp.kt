package com.dan.happy

fun main(args: Array<String>) {
    WebServer(FileBackedMatchService()).create(9090).start()
}
