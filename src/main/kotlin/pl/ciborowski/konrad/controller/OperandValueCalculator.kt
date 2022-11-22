package pl.ciborowski.konrad.controller

import pl.ciborowski.konrad.entities.Memory
import pl.ciborowski.konrad.entities.MemoryCell
import pl.ciborowski.konrad.entities.Operand
import pl.ciborowski.konrad.entities.AddressingMode.*

fun calculateOperandValue(operand: Operand, memory: Memory, accumulator: Int) : Int = when (operand.addressingMode) {
    IMMEDIATE -> operand.argument
    DIRECT -> memory.read(operand.argument)
    INDIRECT -> memory.read(memory.read(operand.argument))
    OFFSET -> memory.read(operand.argument + accumulator)
}
