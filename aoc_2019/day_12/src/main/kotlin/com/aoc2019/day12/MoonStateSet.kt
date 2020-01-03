package com.aoc2019.day12

import com.aoc2019.day12.Dimension.*

data class MoonStateSet(
        val state: Set<MoonState>
) {

    fun equalDimension(other: MoonStateSet, dim: Dimension): Boolean {
        return !state
                .zip(other.state)
                .any { (first, second) ->
                    val (posEqual, velEqual) = when (dim) {
                        X -> (first.position.x == second.position.x) to (first.velocity.x == second.velocity.x)
                        Y -> (first.position.y == second.position.y) to (first.velocity.y == second.velocity.y)
                        Z -> (first.position.z == second.position.z) to (first.velocity.z == second.velocity.z)
                    }

                    !posEqual || !velEqual
                }
    }
}