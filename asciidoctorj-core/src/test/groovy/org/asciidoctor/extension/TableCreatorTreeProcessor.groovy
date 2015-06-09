package org.asciidoctor.extension

import groovy.transform.CompileStatic
import org.asciidoctor.ast.Cell
import org.asciidoctor.ast.Column
import org.asciidoctor.ast.DocumentRuby
import org.asciidoctor.ast.Row
import org.asciidoctor.ast.Table

@CompileStatic
class TableCreatorTreeProcessor extends Treeprocessor {

    @Override
    DocumentRuby process(DocumentRuby document) {

        // Create and add the table
        Table table = createTable(document, [:])
        document.blocks.add(table)

        // Create and add a column
        Column column = createTableColumn(table, 0, [:])
        column.setHAlign(Table.HAlign.CENTER)
        column.setVAlign(Table.VAlign.BOTTOM)
        table.columns.add(column)

        // Create and add a row to the table
        Row row = createTableRow(table)
        table.body.add(row)

        // Create and add a cell to the row
        Cell cell = createTableCell(column, 'A1')
        row.cells.add(cell)

        // Create and add a row to the table
        Row headerRow = createTableRow(table)
        table.header << headerRow

        // Create and add a cell to the row
        Cell headerCell = createTableCell(column, 'A')
        headerRow.cells.add(headerCell)

        // Create and add a row to the table
        Row footerRow = createTableRow(table)
        table.footer << footerRow

        // Create and add a cell to the row
        Cell footerCell = createTableCell(column, 'Sum of A')
        footerRow.cells.add(footerCell)

        document
    }
}
