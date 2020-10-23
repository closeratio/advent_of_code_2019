package com.aoc2019.day18

class StateTransitionCache {

    val cache = HashMap<StateTransitionKey, Pair<Boolean, WorldState?>>()

    data class StateTransitionKey(
            val playerState: PlayerState,
            val targetKey: Key
    )
}