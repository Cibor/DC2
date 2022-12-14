package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.JUMP
import pl.ciborowski.konrad.entities.Operand

class JumpCommand : Command() {

    override val mnemonic get() = JUMP

    override fun execute(computer: DC2, operand: Operand) {
        computer.programCounter = calculateOperandValue(operand, computer.memory, computer.accumulator)
    }
}