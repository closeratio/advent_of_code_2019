rootProject.name = "advent_of_code_2019"

include(":aoc_2019:common")

(1..25).forEach {
    include(":aoc_2019:day_$it")
}