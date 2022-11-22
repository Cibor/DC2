package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class StoreCommandTest : CommandTest() {

    @Test
    fun test_immediate_address_mode() {
        computer.accumulator = -22
        val operand = Operand(IMMEDIATE, 3)
        computer.invokeCommand(StoreCommand(), operand)
        assertEquals(-22, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, -22, 0))
    }

    @Test
    fun test_direct_address_mode() {
        computer.memory.insert(4, 0)
        computer.accumulator = -22
        val operand = Operand(DIRECT, 4)
        computer.invokeCommand(StoreCommand(), operand)
        assertEquals(-22, computer.accumulator)
        assertMemoryEquals(intArrayOf(-22, 0, 0, -11, 0))
    }

    @Test
    fun test_indirect_address_mode() {
        computer.memory.insert(1, 3)
        computer.memory.insert(3, 4)
        computer.accumulator = -22
        val operand = Operand(INDIRECT, 1)
        computer.invokeCommand(StoreCommand(), operand)
        assertEquals(-22, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 3, 0, 4, -22))
    }

    @Test
    fun test_offset_address_mode() {
        computer.accumulator = 1
        val operand = Operand(OFFSET, 2)
        computer.invokeCommand(StoreCommand(), operand)
        assertEquals(1, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 1, 5))
    }

    @Test
    fun expect_exception_for_cell_out_of_range() {
        val operand = Operand(IMMEDIATE, 7)
        assertThrows<IllegalArgumentException> { computer.invokeCommand(StoreCommand(), operand) }
    }
}