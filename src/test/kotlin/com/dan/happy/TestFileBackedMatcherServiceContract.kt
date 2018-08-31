package com.dan.happy


class TestFileBackedMatcherServiceContract: TestMatcherServiceContract(FileBackedMatchService("/matches.json"))