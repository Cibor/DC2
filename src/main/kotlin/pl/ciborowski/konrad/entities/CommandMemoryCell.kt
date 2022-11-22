package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.MemoryCellType.COMMAND

class CommandMemoryCell(val command: Command, val operand: Operand) : MemoryCell() {

    override fun numericValue(): Int = operand.argument

    override fun type(): MemoryCellType = COMMAND
}