package pl.ciborowski.konrad.entities

import pl.ciborowski.konrad.entities.MemoryCellType.DATA

class DataMemoryCell(val value: Int) : MemoryCell() {

    override fun numericValue(): Int = value

    override fun type(): MemoryCellType = DATA
}