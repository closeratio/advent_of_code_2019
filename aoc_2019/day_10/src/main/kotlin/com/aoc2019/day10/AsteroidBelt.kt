package com.aoc2019.day10

import com.aoc2019.common.math.Vec2i
import kotlin.math.round

class AsteroidBelt private constructor(
        val asteroids: Set<Asteroid>
) {

    fun getOptimalPosition(): Pair<Vec2i, Int> {
        val optimal = asteroids
                .map { asteroid ->
                    val detectableCount = asteroids
                            .filter { it != asteroid }
                            .groupBy({
                                val angle = asteroid.position.angleTo(it.position)
                                (round(angle * 1000) / 1000)
                            }) { it }
//                            .mapValues { (_, asteroids) ->
//                                asteroids.minBy {
//                                    asteroid.position.manhattan(it.position)
//                                }
//                            }
                            .size

                    asteroid.position to detectableCount
                }
                .maxBy { it.second }!!

        return optimal
    }

    companion object {
        fun from(input: String): AsteroidBelt = AsteroidBelt(input.trim()
                .lines()
                .mapIndexed { y, line ->
                    line.mapIndexedNotNull { x, char ->
                        if (char == '#') {
                            Asteroid(Vec2i(x, y))
                        } else {
                            null
                        }
                    }
                }
                .flatten()
                .toSet()
        )
    }

}

