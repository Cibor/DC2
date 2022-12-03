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
        assertEquals(Instruction(NullCommand(), Operand(IMMEDIATE, 0)), readFileContent("1: NULL  . 0").second)
        assertEquals(Instruction(LoadCommand(), Operand(IMMEDIATE, 12)), readFileContent("5: LOAD .  12").second)
        assertEquals(Instruction(SubCommand(), Operand(DIRECT, 12)), readFileContent("1000: SUB  @  12").second)
        assertEquals(Instruction(StoreCommand(), Operand(IMMEDIATE, 13)), readFileContent("3: STORE  . 13").second)
        assertEquals(Instruction(StopCommand(), Operand(IMMEDIATE, 0)), readFileContent("7: STOP .   0").second)
        assertEquals(Instruction(JZeroCommand(), Operand(IMMEDIATE, 5)), readFileContent("11: JZERO  . 5").second)
        assertEquals(Instruction(ShlCommand(), Operand(INDIRECT, 10)), readFileContent("12: SHL  * 10").second)
        assertEquals(Instruction(LoadCommand(), Operand(OFFSET, -5)), readFileContent("33: LOAD +   -5").second)
    }

    @Test
    fun test_incorrect_instructions() {
        assertThrows<IllegalArgumentException> { readFileContent("LOAD+   -5") }
        assertThrows<IllegalArgumentException> { readFileContent("SUB  @12") }
        assertThrows<IllegalArgumentException> { readFileContent("SAB . 12") }
        assertThrows<IllegalArgumentException> { readFileContent("LoaD + 5") }
    }

}