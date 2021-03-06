/**
 * This class is generated by jOOQ
 */
package assets.sr25food.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "3.0.1"},
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LangdescRecord extends org.jooq.impl.UpdatableRecordImpl<assets.sr25food.tables.records.LangdescRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1954135682;

	/**
	 * Setter for <code>sr25food.langdesc.Factor</code>. 
	 */
	public void setFactor(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>sr25food.langdesc.Factor</code>. 
	 */
	public java.lang.String getFactor() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>sr25food.langdesc.Description</code>. 
	 */
	public void setDescription(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>sr25food.langdesc.Description</code>. 
	 */
	public java.lang.String getDescription() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return assets.sr25food.tables.Langdesc.LANGDESC.FACTOR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return assets.sr25food.tables.Langdesc.LANGDESC.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getFactor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getDescription();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached LangdescRecord
	 */
	public LangdescRecord() {
		super(assets.sr25food.tables.Langdesc.LANGDESC);
	}
}
