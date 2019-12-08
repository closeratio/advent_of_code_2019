package com.aoc2019.day4

import kotlin.math.pow

fun Array<Int>.passwordValue(): Int = mapIndexed { index, value ->
    value * (10.0).pow(size - index - 1).toInt()
}.sum()