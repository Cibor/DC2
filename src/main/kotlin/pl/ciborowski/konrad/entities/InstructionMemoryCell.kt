package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.MemoryCellType.INSTRUCTION

class InstructionMemoryCell(val instruction: Instruction) : MemoryCell() {

    override fun numericValue(): Int = instruction.operand.argument

    override fun type(): MemoryCellType = INSTRUCTION
}