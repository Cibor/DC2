package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class JZeroCommandTest : CommandTest() {

    @Test
    fun test_immediate_address_mode_accumulator_non_zero() {
        computer.accumulator = 7
        val operand = Operand(IMMEDIATE, 4)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(7, computer.accumulator)
        assertEquals(0, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

    @Test
    fun test_direct_address_mode_accumulator_non_zero() {
        computer.accumulator = 2
        computer.memory.insert(3, 1)
        val operand = Operand(DIRECT, 3)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(2, computer.accumulator)
        assertEquals(0, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 1, 0))
    }

    @Test
    fun test_indirect_address_mode_accumulator_non_zero() {
        computer.accumulator = -11
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 2)
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(-11, computer.accumulator)
        assertEquals(0, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 4, 0, 2, 0))
    }

    @Test
    fun test_offset_address_mode_accumulator_non_zero() {
        computer.accumulator = 4
        computer.memory.insert(4, 1)
        computer.accumulator = 3
        val operand = Operand(OFFSET, 1)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(0, computer.programCounter)
        assertEquals(3, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 1))
    }

    @Test
    fun test_immediate_address_mode_accumulator_zero() {
        computer.accumulator = 0
        val operand = Operand(IMMEDIATE, 4)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(4, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

    @Test
    fun test_direct_address_mode_accumulator_zero() {
        computer.accumulator = 0
        computer.memory.insert(3, 1)
        val operand = Operand(DIRECT, 3)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(1, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 1, 0))
    }

    @Test
    fun test_indirect_address_mode_accumulator_zero() {
        computer.accumulator = 0
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 2)
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(2, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 4, 0, 2, 0))
    }

    @Test
    fun test_offset_address_mode_accumulator_zero() {
        computer.accumulator = 0
        computer.memory.insert(4, 1)
        val operand = Operand(OFFSET, 4)
        computer.invokeCommand(JZeroCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(1, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 1))
    }
}