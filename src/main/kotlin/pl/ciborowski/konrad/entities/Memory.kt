package pl.ciborowski.konrad.entities

class Memory(val size: Int) {
    private val cells = Array<MemoryCell>(size) { _ -> DataMemoryCell(0) }

    fun insert(cell: Int, value: Int) {
        require(cell >= 0 && cell < size) { "Trying to insert $value into $cell " }
        cells[cell] = DataMemoryCell(value)
    }

    fun insert(cell: Int, instruction: Instruction) {
        require(cell >= 0 && cell < size) { "Trying to insert instruction $instruction into $cell " }
        cells[cell] = InstructionMemoryCell(instruction)
    }

    fun readNumericValue(cell: Int) : Int {
        require(cell >= 0 && cell < size) { "Trying to read from $cell " }
        return cells[cell].numericValue()
    }

    fun readCell(cell: Int) : MemoryCell {
        return cells[cell]
    }

}