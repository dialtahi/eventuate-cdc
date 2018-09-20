package io.eventuate.local.polling;

import io.eventuate.javaclient.spring.jdbc.EventuateSchema;
import io.eventuate.local.common.BinlogEntry;
import io.eventuate.local.common.BinlogEntryHandler;

import java.util.function.Consumer;

public class PollingEntryHandler extends BinlogEntryHandler {

  private String publishedField;
  private String idField;

  public PollingEntryHandler(EventuateSchema eventuateSchema,
                             String sourceTableName,
                             Consumer<BinlogEntry> eventConsumer,
                             String publishedField,
                             String idField) {

    super(eventuateSchema, sourceTableName, eventConsumer);

    this.publishedField = publishedField;
    this.idField = idField;
  }

  public String getQualifiedTable() {
    return eventuateSchema.qualifyTable(sourceTableName);
  }

  public void accept(BinlogEntry binlogEntry) {
    eventConsumer.accept(binlogEntry);
  }

  public EventuateSchema getEventuateSchema() {
    return eventuateSchema;
  }

  public String getSourceTableName() {
    return sourceTableName;
  }

  public String getPublishedField() {
    return publishedField;
  }

  public String getIdField() {
    return idField;
  }
}
