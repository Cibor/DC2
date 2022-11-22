package pl.ciborowski.konrad.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Memory
import pl.ciborowski.konrad.entities.MemoryCell

class OperandValueCalculatorTest {

    private lateinit var memory: Memory

    @BeforeEach
    fun setUp() : Unit {
        memory = Memory(10)
    }

    @Test
    fun should_return_argument_value_for_immediate_addressing_mode() {
        assertEquals(5, calculateOperandValue(Operand(IMMEDIATE, 5), memory, 0))
    }

    @Test
    fun should_throw_exception_if_exceeds_size() {
        assertThrows<IllegalArgumentException> { calculateOperandValue(Operand(DIRECT, 11), memory, 0) }
    }

    @Test
    fun should_return_value_in_correct_cell_for_direct_addressing_mode() {
        memory.insert(5, 9)
        assertEquals(9, calculateOperandValue(Operand(DIRECT, 5), memory, 0))
    }

    @Test
    fun should_return_value_in_correct_cell_for_indirect_addressing_mode() {
        memory.insert(5, 9)
        memory.insert(9, -17)
        assertEquals(-17, calculateOperandValue(Operand(INDIRECT, 5), memory, 0))
    }

    @Test
    fun should_return_value_in_correct_cell_for_offset_addressing_mode() {
        memory.insert(5, 9)
        assertEquals(9, calculateOperandValue(Operand(OFFSET, 3), memory, 2))
    }
}