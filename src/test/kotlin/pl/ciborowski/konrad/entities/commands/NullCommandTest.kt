package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.OFFSET
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class NullCommandTest : CommandTest() {

    @Test
    fun test_command() {
        val operand = Operand(OFFSET, 7)
        computer.invokeCommand(NullCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }
}