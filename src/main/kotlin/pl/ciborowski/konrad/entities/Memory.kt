package pl.ciborowski.konrad.entities

class Memory(val size: Int) {
    private val cells = Array<MemoryCell>(size) { _ -> DataMemoryCell(0) }

    fun insert(cell: Int, value: Int) {
        require(cell >= 0 && cell < size)
        cells[cell] = DataMemoryCell(value)
    }

    fun insert(cell: Int, command: Command, operand: Operand) {
        require(cell >= 0 && cell < size)
        cells[cell] = CommandMemoryCell(command, operand)
    }

    fun readNumericValue(cell: Int) : Int {
        require(cell >= 0 && cell < size)
        return cells[cell].numericValue()
    }

    fun readCell(cell: Int) : MemoryCell {
        return cells[cell]
    }

}