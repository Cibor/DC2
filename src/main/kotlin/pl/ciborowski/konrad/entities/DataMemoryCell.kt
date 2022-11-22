package pl.ciborowski.konrad.entities

class DataMemoryCell(val value: Int) : MemoryCell() {

    override fun numericValue(): Int = value
}