package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.XOR
import pl.ciborowski.konrad.entities.Operand

class XorCommand : Command() {

    override val mnemonic get() = XOR
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = computer.accumulator xor calculateOperandValue(operand, computer.memory, computer.accumulator)
        computer.programCounter++
    }
}