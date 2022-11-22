package pl.ciborowski.konrad.entities

abstract class MemoryCell {

    abstract fun numericValue() : Int

    abstract fun type() : MemoryCellType
}