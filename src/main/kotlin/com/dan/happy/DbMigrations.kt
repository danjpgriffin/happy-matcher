package com.dan.happy

import org.flywaydb.core.Flyway

object DbMigrations {
    private var checked = false

    fun ensureUpToData() {
        synchronized(this) {
            if (!checked) {
                val flyway = Flyway()
                flyway.setDataSource("jdbc:mysql://localhost", "root", "root")
                flyway.setSchemas("spark")
                flyway.migrate()
                checked = true
            }
        }
    }
}
