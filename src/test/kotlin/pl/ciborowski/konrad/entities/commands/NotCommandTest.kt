package pl.ciborowski.konrad.entities.commands

import org.junit.jupiter.api.Test
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Operand
import kotlin.test.assertEquals

class NotCommandTest : CommandTest() {

    @Test
    fun test_command() {
        computer.accumulator = -3
        val operand = Operand(OFFSET, 7)
        computer.invokeCommand(NotCommand(), operand)
        assertEquals((-3).inv(), computer.accumulator)
        assertMemoryEquals(intArrayOf(0, 0, 0, 0, 0))
    }

}