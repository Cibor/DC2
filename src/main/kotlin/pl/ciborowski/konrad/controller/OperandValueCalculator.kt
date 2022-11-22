package pl.ciborowski.konrad.controller

import pl.ciborowski.konrad.entities.Memory
import pl.ciborowski.konrad.entities.Operand
import pl.ciborowski.konrad.entities.AddressingMode.*

fun calculateOperandValue(operand: Operand, memory: Memory, accumulator: Int) : Int = when (operand.addressingMode) {
    IMMEDIATE -> operand.argument
    DIRECT -> memory.readNumericValue(operand.argument)
    INDIRECT -> memory.readNumericValue(memory.readNumericValue(operand.argument))
    OFFSET -> memory.readNumericValue(operand.argument + accumulator)
}
