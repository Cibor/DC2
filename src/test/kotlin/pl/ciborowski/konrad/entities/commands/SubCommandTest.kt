package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class SubCommandTest : CommandTest() {

    @Test
    fun test_immediate_address_mode() {
        computer.accumulator = -3
        val operand = Operand(IMMEDIATE, 7)
        computer.invokeCommand(SubCommand(), operand)
        assertEquals(-10, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

    @Test
    fun test_direct_address_mode() {
        computer.accumulator = 11
        computer.memory.insert(3, -11)
        val operand = Operand(DIRECT, 3)
        computer.invokeCommand(SubCommand(), operand)
        assertEquals(22, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, -11, 0))
    }

    @Test
    fun test_indirect_address_mode() {
        computer.accumulator = 33
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 9)
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(SubCommand(), operand)
        assertEquals(24, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 4, 0, 9, 0))
    }

    @Test
    fun test_offset_address_mode() {
        computer.memory.insert(4, 5)
        computer.accumulator = 3
        val operand = Operand(OFFSET, 1)
        computer.invokeCommand(SubCommand(), operand)
        assertEquals(-2, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 5))
    }
}