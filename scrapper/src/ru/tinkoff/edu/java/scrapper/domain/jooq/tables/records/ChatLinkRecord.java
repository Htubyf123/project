/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;

import ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLink;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ChatLinkRecord extends TableRecordImpl<ChatLinkRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.chat_link.chat_id</code>.
     */
    public void setChatId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.chat_link.chat_id</code>.
     */
    public Integer getChatId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.chat_link.link_id</code>.
     */
    public void setLinkId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.chat_link.link_id</code>.
     */
    public Integer getLinkId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ChatLink.CHAT_LINK.CHAT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ChatLink.CHAT_LINK.LINK_ID;
    }

    @Override
    public Integer component1() {
        return getChatId();
    }

    @Override
    public Integer component2() {
        return getLinkId();
    }

    @Override
    public Integer value1() {
        return getChatId();
    }

    @Override
    public Integer value2() {
        return getLinkId();
    }

    @Override
    public ChatLinkRecord value1(Integer value) {
        setChatId(value);
        return this;
    }

    @Override
    public ChatLinkRecord value2(Integer value) {
        setLinkId(value);
        return this;
    }

    @Override
    public ChatLinkRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ChatLinkRecord
     */
    public ChatLinkRecord() {
        super(ChatLink.CHAT_LINK);
    }

    /**
     * Create a detached, initialised ChatLinkRecord
     */
    public ChatLinkRecord(Integer chatId, Integer linkId) {
        super(ChatLink.CHAT_LINK);

        setChatId(chatId);
        setLinkId(linkId);
    }
}
