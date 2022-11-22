package pl.ciborowski.konrad.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.ciborowski.konrad.entities.commands.*
import kotlin.test.*
import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Instruction
import pl.ciborowski.konrad.entities.Operand

class CommandParserTest {

    @Test
    fun test_correct_instructions() {
        assertEquals(Instruction(NullCommand(), Operand(IMMEDIATE, 0)), parse("NULL  . 0"))
        assertEquals(Instruction(LoadCommand(), Operand(IMMEDIATE, 12)), parse("LOAD .  12"))
        assertEquals(Instruction(SubCommand(), Operand(DIRECT, 12)), parse("SUB  @  12"))
        assertEquals(Instruction(StoreCommand(), Operand(IMMEDIATE, 13)), parse("STORE  . 13"))
        assertEquals(Instruction(StopCommand(), Operand(IMMEDIATE, 0)), parse("STOP .   0"))
        assertEquals(Instruction(JZeroCommand(), Operand(IMMEDIATE, 5)), parse("JZERO  . 5"))
        assertEquals(Instruction(ShlCommand(), Operand(INDIRECT, 10)), parse("SHL  * 10"))
        assertEquals(Instruction(LoadCommand(), Operand(OFFSET, -5)), parse("LOAD +   -5"))
    }

    @Test
    fun test_incorrect_instructions() {
        assertThrows<IllegalArgumentException> { parse("LOAD+   -5") }
        assertThrows<IllegalArgumentException> { parse("SUB  @12") }
        assertThrows<IllegalArgumentException> { parse("SAB . 12") }
        assertThrows<IllegalArgumentException> { parse("LoaD + 5") }
    }

}