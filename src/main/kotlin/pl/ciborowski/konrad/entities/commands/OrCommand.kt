package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.OR
import pl.ciborowski.konrad.entities.Operand

class OrCommand : Command() {

    override val mnemonic get() = OR
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = computer.accumulator or calculateOperandValue(operand, computer.memory, computer.accumulator)
    }
}