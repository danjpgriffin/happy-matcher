package com.dan.happy

import java.sql.DriverManager
import java.sql.ResultSet

class SqlBackedMatchService: MatchService {

    override fun findMatches(restrictions: Restrictions, originCity: City?): List<Match> {
        return DriverManager.getConnection("jdbc:mysql://localhost/spark?user=root&password=root").use {
            it.createStatement().use {
                it.executeQuery("SELECT * FROM matches;").use { rs ->
                    rs.map {
                        Match(
                            display_name = rs.getString("display_name"),
                            age = -1,
                            job_title = rs.getString("job_title"),
                            height_in_cm = rs.getInt("height_in_cm"),
                            city = City(rs.getString("city_name"), rs.getDouble("lat"), rs.getDouble("lon")),
                            main_photo = rs.getString("main_photo"),
                            compatibility_score = rs.getDouble("compatibility_score"),
                            contacts_exchanged = rs.getInt("contacts_exchanged"),
                            favourite = rs.getBoolean("favourite"),
                            religion = rs.getString("religion")
                        )
                    }
                }
            }
        }.filterAllBy(restrictions, originCity)
    }

    private fun ResultSet.map(fn: (ResultSet) -> Match): List<Match> {
        val results = mutableListOf<Match>()
        while(this.next()) {
            results.add(fn(this))
        }
        return results
    }

}
