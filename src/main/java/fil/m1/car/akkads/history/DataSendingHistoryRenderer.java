package fil.m1.car.akkads.history;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.row.ContentRow;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class DataSendingHistoryRenderer {
    
    private static final String SENDER_LABEL = "Sender";
    private static final String RECIPIENT_LABEL = "Recipient";
    private static final String MESSAGE_LABEL = "Message";
    
    public RenderedTable renderHistory(DataSendingHistory dataSendingHistory) {
        final V2_AsciiTable asciiTable = new V2_AsciiTable();
        
        final V2_AsciiTableRenderer renderer = new V2_AsciiTableRenderer();
        renderer.setTheme(V2_E_TableThemes.PLAIN_7BIT_STRONG.get());
        renderer.setWidth(new WidthLongestLine());
        
        final char[] columnsAlignments = { 'c', 'c', 'c' };
        
        asciiTable.addRule();
        final ContentRow firstRow = asciiTable.addRow(SENDER_LABEL, RECIPIENT_LABEL, MESSAGE_LABEL);
        firstRow.setAlignment(columnsAlignments);
        asciiTable.addRule();
        
        // adding records
        System.out.println(dataSendingHistory.getRecords());
        dataSendingHistory.getRecords().forEach(record -> {
            final ContentRow row = asciiTable.addRow(record.getSender().path().name(), record.getRecipient().path().name(), record.getMessage());
            row.setAlignment(columnsAlignments);
            asciiTable.addRule();
        });
        
        return renderer.render(asciiTable);
    }

}
