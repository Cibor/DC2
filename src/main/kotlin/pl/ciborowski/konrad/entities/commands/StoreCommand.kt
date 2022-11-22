package pl.ciborowski.konrad.entities.commands

import pl.ciborowski.konrad.controller.calculateOperandValue
import pl.ciborowski.konrad.entities.Command
import pl.ciborowski.konrad.entities.DC2
import pl.ciborowski.konrad.entities.Mnemonic.STORE
import pl.ciborowski.konrad.entities.Operand

class StoreCommand : Command() {

    override val mnemonic = STORE
    override fun execute(computer: DC2, operand: Operand) {
        val cellNumber = calculateOperandValue(operand, computer.memory, computer.accumulator)
        computer.memory.insert(cellNumber, computer.accumulator)
    }
}