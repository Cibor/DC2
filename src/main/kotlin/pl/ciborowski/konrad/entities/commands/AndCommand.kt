package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.AND
import pl.ciborowski.konrad.entities.Operand

class AndCommand : Command() {

    override val mnemonic = AND
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = computer.accumulator and calculateOperandValue(operand, computer.memory, computer.accumulator)
    }
}