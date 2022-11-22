package pl.ciborowski.konrad.entities

abstract class Command(val mnemonic: Mnemonic)  {

    abstract fun execute(operand: Operand)
}