package com.aoc2019.common.computer

class Memory(
        private val memory: LinkedHashMap<Long, Long>
) {

    operator fun get(address: Long): Long {
        require(address >= 0L)
        return memory.getOrPut(address) { 0 }
    }

    operator fun set(address: Long, value: Long) {
        require(address >= 0L)
        memory[address] = value
    }

    fun values(): List<Long> = memory.values.toList()

}