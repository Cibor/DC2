package pl.ciborowski.konrad.entities

class CommandMemoryCell(val command: Command, val operand: Operand) : MemoryCell() {

    override fun numericValue(): Int = operand.argument
}