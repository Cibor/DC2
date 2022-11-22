package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class ShlCommandTest : CommandTest() {

    @Test
    fun test_immediate_address_mode() {
        computer.accumulator = -3
        val operand = Operand(IMMEDIATE, 7)
        computer.invokeCommand(ShlCommand(), operand)
        assertEquals(-3 shl 7, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

    @Test
    fun test_direct_address_mode() {
        computer.accumulator = 11
        computer.memory.insert(3, -11)
        val operand = Operand(DIRECT, 3)
        computer.invokeCommand(ShlCommand(), operand)
        assertEquals(11 shl -11, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, -11, 0))
    }

    @Test
    fun test_indirect_address_mode() {
        computer.accumulator = 33
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 9)
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(ShlCommand(), operand)
        assertEquals(33 shl 9, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 4, 0, 9, 0))
    }

    @Test
    fun test_offset_address_mode() {
        computer.memory.insert(4, 5)
        computer.accumulator = 3
        val operand = Operand(OFFSET, 1)
        computer.invokeCommand(ShlCommand(), operand)
        assertEquals(3 shl 5, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 5))
    }
}