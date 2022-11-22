package pl.ciborowski.konrad.entities

abstract class Command {

    abstract val mnemonic: Mnemonic

    abstract fun execute(computer: DC2, operand: Operand)
}