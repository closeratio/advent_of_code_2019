package com.aoc2019.day10

import com.aoc2019.common.math.Vec2i
import kotlin.math.round

class AsteroidBelt private constructor(
        val asteroids: Set<Asteroid>
) {

    private fun groupAsteroidsByAngle(originAsteroid: Asteroid) = asteroids
            .filter { it != originAsteroid }
            .groupBy({
                val angle = originAsteroid.position.angleTo(it.position)
                // This is the hacky bit - because we're using the actual angle rather than a "chess piece" algorithm,
                // we can't have the angle be too precise otherwise it'll separate asteroids that are considered to be
                // part of the same line of sight.
                (round(angle * 1000) / 1000)
            }) { it }
            .mapValues { (_, asteroids) ->
                asteroids.sortedBy { originAsteroid.position.manhattan(it.position) }
            }

    fun getOptimalPosition(): Pair<Vec2i, Int> {
        val optimal = asteroids
                .map { asteroid ->
                    asteroid.position to groupAsteroidsByAngle(asteroid).size
                }
                .maxBy { it.second }!!

        return optimal
    }

    fun getNthDestroyedAsteroidPosition(n: Int): Vec2i {


        return Vec2i.ZERO
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

