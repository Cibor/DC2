package pl.ciborowski.konrad.controller

import pl.ciborowski.konrad.entities.AddressingMode.*
import pl.ciborowski.konrad.entities.Instruction
import pl.ciborowski.konrad.entities.Mnemonic
import pl.ciborowski.konrad.entities.Operand

fun readFileContent(line: String) : Pair<Int, Instruction> {
    val instructionRegex = """(\d+): ([A-Z]+)\s+([.*@+])\s+([+-]*\d+)""".toRegex()
    require(instructionRegex.matches(line)) { " Instruction $line has incorrect format " }

    val matchResult = instructionRegex.find(line)
    val cellNumber = matchResult!!.groups[1]?.value
    val mnemonicName = matchResult.groups[2]?.value
    val mnemonic = Mnemonic.values().firstOrNull { it.name == mnemonicName }
    require(mnemonic != null)
    require(cellNumber != null)

    val addressingModeSymbol = matchResult.groups[3]?.value
    require(addressingModeSymbol != null)
    val addressingMode = when {
        addressingModeSymbol == "." -> IMMEDIATE
        addressingModeSymbol == "@" -> DIRECT
        addressingModeSymbol == "*" -> INDIRECT
        addressingModeSymbol == "+" -> OFFSET
        else -> throw IllegalArgumentException("Addressing mode incorrect in $line")
    }

    val argument = matchResult.groups[4]?.value?.toInt()
    require(argument != null)

    return Pair(cellNumber.toInt(), Instruction(mnemonic.command, Operand(addressingMode, argument)))
}
