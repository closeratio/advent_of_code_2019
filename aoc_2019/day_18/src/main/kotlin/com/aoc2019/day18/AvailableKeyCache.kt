package com.aoc2019.day18

import com.aoc2019.day18.PlayerState.SubState

class AvailableKeyCache {

    val cache = HashMap<SubState, Set<Pair<Key, Long>>>()

}