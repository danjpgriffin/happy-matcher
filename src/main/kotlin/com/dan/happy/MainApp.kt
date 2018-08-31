package com.dan.happy

fun main(args: Array<String>) {
    DbMigrations.ensureUpToData()
    WebServer(SqlBackedMatchService(), DemoUserService()).create(9090).start()
}
