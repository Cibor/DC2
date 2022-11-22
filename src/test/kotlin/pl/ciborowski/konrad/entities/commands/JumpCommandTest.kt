package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class JumpCommandTest : CommandTest() {

    @Test
    fun test_immediate_address_mode() {
        val operand = Operand(IMMEDIATE, 4)
        computer.invokeCommand(JumpCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(4, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

    @Test
    fun test_direct_address_mode() {
        computer.memory.insert(3, 1)
        val operand = Operand(DIRECT, 3)
        computer.invokeCommand(JumpCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(1, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 0, 0, 1, 0))
    }

    @Test
    fun test_indirect_address_mode() {
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 2)
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(JumpCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertEquals(2, computer.programCounter)
        assertMemoryEquals(intArrayOf(0, 4, 0, 2, 0))
    }

    @Test
    fun test_offset_address_mode() {
        computer.memory.insert(4, 1)
        computer.accumulator = 3
        val operand = Operand(OFFSET, 1)
        computer.invokeCommand(JumpCommand(), operand)
        assertEquals(1, computer.programCounter)
        assertEquals(3, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 1))
    }
}