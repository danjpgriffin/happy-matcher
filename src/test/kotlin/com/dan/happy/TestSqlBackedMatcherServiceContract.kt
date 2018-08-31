package com.dan.happy


class TestSqlBackedMatcherServiceContract: TestMatcherServiceContract(SqlBackedMatchService()) {
    init { DbMigrations.ensureUpToData() }
}