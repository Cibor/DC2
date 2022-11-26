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
        assertEquals(Instruction(NullCommand(), Operand(IMMEDIATE, 0)), readFileContent("NULL  . 0"))
        assertEquals(Instruction(LoadCommand(), Operand(IMMEDIATE, 12)), readFileContent("LOAD .  12"))
        assertEquals(Instruction(SubCommand(), Operand(DIRECT, 12)), readFileContent("SUB  @  12"))
        assertEquals(Instruction(StoreCommand(), Operand(IMMEDIATE, 13)), readFileContent("STORE  . 13"))
        assertEquals(Instruction(StopCommand(), Operand(IMMEDIATE, 0)), readFileContent("STOP .   0"))
        assertEquals(Instruction(JZeroCommand(), Operand(IMMEDIATE, 5)), readFileContent("JZERO  . 5"))
        assertEquals(Instruction(ShlCommand(), Operand(INDIRECT, 10)), readFileContent("SHL  * 10"))
        assertEquals(Instruction(LoadCommand(), Operand(OFFSET, -5)), readFileContent("LOAD +   -5"))
    }

    @Test
    fun test_incorrect_instructions() {
        assertThrows<IllegalArgumentException> { readFileContent("LOAD+   -5") }
        assertThrows<IllegalArgumentException> { readFileContent("SUB  @12") }
        assertThrows<IllegalArgumentException> { readFileContent("SAB . 12") }
        assertThrows<IllegalArgumentException> { readFileContent("LoaD + 5") }
    }

}