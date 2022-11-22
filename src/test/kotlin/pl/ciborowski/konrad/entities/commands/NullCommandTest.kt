package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class NullCommandTest : CommandTest() {

    @Test
    fun test_test() {
        val operand = Operand(AddressingMode.OFFSET, 7)
        val nullCommand = NullCommand()
        computer.invokeCommand(NullCommand(), operand)
        assertEquals(0, computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }
}