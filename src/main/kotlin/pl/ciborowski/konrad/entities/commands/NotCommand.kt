package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.NOT
import pl.ciborowski.konrad.entities.Operand

class NotCommand : Command() {

    override val mnemonic = NOT
    override fun execute(computer: DC2, operand: Operand) {
        computer.accumulator = computer.accumulator.inv()
    }
}